# Email Writer Assistant

A full-stack application that helps users generate AI-powered email replies. The project consists of three main components: a Spring Boot backend, a React frontend, and a Chrome extension for Gmail integration.

## Project Components

### 1. Backend (Spring Boot)
- REST API for email generation
- Built with Spring Boot 3.x
- Handles email content generation requests
- Location: `/src` directory

### 2. Frontend (React)
- Modern React application built with Vite
- Material-UI components for the interface
- Axios for API communication
- Location: `/email-writer-react` directory

### 3. Browser Extension
- Chrome extension for Gmail integration
- Manifest V3 compliant
- Integrates directly with Gmail interface
- Location: `/email-writer-extension` directory

## Technology Stack

### Backend
- Java 17+
- Spring Boot 3.x
- Maven
- RESTful API architecture

### Frontend
- React 19
- Vite
- Material-UI
- Axios for HTTP requests

### Browser Extension
- Manifest V3
- JavaScript
- CSS
- Gmail API integration

## Setup Instructions

### Backend Setup
1. Ensure you have Java 17+ and Maven installed
2. Navigate to the root directory
3. Run:
```bash
./mvnw clean install
./mvnw spring-boot:run
```
4. Configure Gemini API credentials:
   - Open `src/main/java/com/programmer/email_writer_assitant/service/EmailGeneratorService.java`
   - Replace `{Write your Gemini API URL here}` with your Gemini API URL
   - Replace `{Write your Gemini API Key here}` with your Gemini API Key

The backend will start on `http://localhost:8080`

### Frontend Setup
1. Navigate to the React app directory:
```bash
cd email-writer-react
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm run dev
```
The frontend will be available at `http://localhost:5173`

### Browser Extension Setup
1. Open Chrome and navigate to `chrome://extensions/`
2. Enable "Developer mode"
3. Click "Load unpacked"
4. Select the `/email-writer-extension` directory

## Development

### Backend Development
- The main application class is `EmailWriterAssitantApplication.java`
- API endpoints are defined in `EmailGeneratorController.java`
- Service logic is in `EmailGeneratorService.java`

### Frontend Development
- Main application entry is in `src/main.jsx`
- Component files are in `src/` directory
- Available scripts:
  - `npm run dev`: Start development server
  - `npm run build`: Build for production
  - `npm run preview`: Preview production build
  - `npm run lint`: Run ESLint

### Extension Development
- Main content script: `content.js`
- Styling: `content.css`
- The extension is configured to work with Gmail and local development server
- Permissions include:
  - `activeTab`: For Gmail integration
  - `storage`: For saving user preferences

## Building for Production

### Backend
```bash
./mvnw clean package
```
This will create a JAR file in the `target` directory.

### Frontend
```bash
cd email-writer-react
npm run build
```
This will create a production build in the `dist` directory.

### Extension
The extension can be packaged through the Chrome Web Store Developer Dashboard once development is complete.

## Project Structure
```
├── src/                          # Backend source files
├── email-writer-react/           # Frontend React application
├── email-writer-extension/       # Chrome extension
├── pom.xml                       # Maven configuration
└── README.md                     # This file
```

## Contributing
1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push to the branch
5. Create a new Pull Request
