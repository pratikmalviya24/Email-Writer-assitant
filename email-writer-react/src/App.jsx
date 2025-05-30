import { useState } from 'react'
import { Container, Typography, Box, TextField, FormControl, InputLabel, MenuItem, Select, CircularProgress, Button } from '@mui/material'
import axios from 'axios'
import './App.css'



function App() {
  const [emailContent, setEmailContent] = useState('');
  const [tone, setTone] = useState('');
  const [generatedReply, setGeneratedReply] = useState('');
  const [loading, setLoading] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async () => {
    setLoading(true);
    setError('');
    setGeneratedReply('');

    try {
      const response = await axios.post('http://localhost:8080/api/email/generate', {
        emailContent,
        tone
      });
 
      setGeneratedReply(typeof response.data === 'string' ? response.data : JSON.stringify(response.data));
    } catch (err) {
      setError('Failed to generate reply. Please try again.');
      console.error('Error generating reply:', err);
    } finally {
      setLoading(false);
    }
  } 

  return (
    <Container maxWidth="md">
      <Box className="app-container">
      <Typography variant='h3' component="h1" gutterBottom className="title">
        Email Reply Generator
      </Typography>
      <Box>
        <TextField 
            fullWidth 
            multiline 
            rows={6} 
            variant='outlined' 
            label="Original Email Content" 
            value={emailContent || ''}
            onChange={(e)=>setEmailContent(e.target.value)}
            sx={{mb : 2}}/>

            <FormControl fullWidth sx={{mb: 2}}>
              <InputLabel>Tone (optional)</InputLabel>
              <Select
              value={tone || ' '}
              label={'Tone (optional)'}
              onChange={(e) => setTone(e.target.value)}
              >
                <MenuItem value="">None</MenuItem>
                <MenuItem value="professional">Professional</MenuItem>
                <MenuItem value="casual">Casual</MenuItem>
                <MenuItem value="friendly">Friendly</MenuItem>
              </Select>
            </FormControl>

            <Button 
              variant='contained' 
              onClick={handleSubmit} 
              disabled={!emailContent || loading} 
              fullWidth
              className="submit-button">
              {loading ? <CircularProgress size={24}></CircularProgress> : 'Generate Reply'}
            </Button>
      </Box>
      {error && <Typography color="error" variant="body1">{error}</Typography>}
      {generatedReply && (
        <Box className="response-card">
          <Typography variant='h6' gutterBottom>
            Generated Reply
          </Typography>
          <TextField 
            fullWidth 
            multiline 
            rows={6} 
            variant='outlined' 
            value={generatedReply} 
            InputProps={{ readOnly: true }} />
            <Button
            variant='outlined'
            className="copy-button"
            onClick={() => {
              navigator.clipboard.writeText(generatedReply);
              alert('Reply copied to clipboard!');
            }
            }
            >
              Copy to Clipboard
            </Button>
        </Box>
      )}
      </Box>
    </Container>
  )
}

export default App
