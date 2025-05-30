package com.programmer.email_writer_assitant.dto;

import lombok.Data;

@Data
public class EmailRequest {
    private String emailContent;
    private String tone;
}
