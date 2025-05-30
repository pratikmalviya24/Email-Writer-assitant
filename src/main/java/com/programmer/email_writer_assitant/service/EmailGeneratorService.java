package com.programmer.email_writer_assitant.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.programmer.email_writer_assitant.dto.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class EmailGeneratorService {

    private final WebClient webClient;

    @Value("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=")
    private  String GEMINI_API_URL;

    @Value("AIzaSyCmcl65er6ROrht2QGfKhh0yd6Geb7BAIY")
    private  String GEMINI_API_KEY;

    @Autowired
    public EmailGeneratorService(WebClient.Builder webClientBuilder) {
         this.webClient = webClientBuilder.build();
    }


    public String generateEmailReply(EmailRequest emailRequest){

        String prompt = buildPrompt(emailRequest);

        Map<String,Object> requestBody = Map.of(
          "contents",new Object[]{
                  Map.of("parts",new Object[]{
                          Map.of("text",prompt)
                  })
                }
        );

        String response =  webClient
                .post()
                .uri(GEMINI_API_URL+GEMINI_API_KEY)
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return extractResponseContent(response);

    }

    private String extractResponseContent(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            return rootNode
                    .path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

        }catch (Exception e){
            return "Error processing request : " + e.getMessage();
        }
    }

    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Generate a professional email reply for the following email content. Please don't generate a subject line");
        if(emailRequest.getTone()!=null && !emailRequest.getTone().isEmpty()){
            prompt.append("Usa a ")
                    .append(emailRequest.getTone())
                    .append(" tone.");
        }
        prompt.append("\nOriginal Email : \n")
                .append(emailRequest.getEmailContent());
        return prompt.toString();
    }
}
