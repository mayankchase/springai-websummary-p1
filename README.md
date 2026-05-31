# Spring AI Web Content Summarization POC

A Spring Boot application that uses Spring AI to summarize external website content.

## 🚀 Features

- Extract content from any external website URL
- Summarize web content using AI (OpenAI, Anthropic, etc.)
- REST API endpoint for easy integration
- Error handling and content sanitization
- Token limit management
- Clean and modular architecture

## 📋 Prerequisites

- Java 17+
- Maven 3.8+
- API Key (OpenAI, Anthropic, or compatible service)
- Internet connection for web scraping

## 🔧 Setup

### 1. Clone the repository
```bash
git clone https://github.com/mayankchase/springai-websummary-p1.git
cd springai-websummary-p1
```

### 2. Set your API key
```bash
# For OpenAI
export OPENAI_API_KEY=your-api-key-here
```

### 3. Build and run
```bash
mvn clean install
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 📡 API Usage

### Summarize a Website

**Endpoint:**
```
GET /api/summarize?url=https://www.example.com
```

**Example Request:**
```bash
curl "http://localhost:8080/api/summarize?url=https://www.example.com"
```

**Response:**
```json
{
  "url": "https://www.example.com",
  "summary": "• Key point 1\n• Key point 2\n• Key point 3"
}
```

**Error Response:**
```json
{
  "url": "https://invalid-url.com",
  "summary": "Error summarizing content: Connection timeout"
}
```

## 📁 Project Structure

```
src/main/java/com/example/summarizer/
├── WebSummarizerApplication.java          # Main application class
├── controller/
│   └── SummarizationController.java        # REST API endpoints
├── service/
│   ├── SummarizationService.java           # Core AI summarization logic
│   └── WebContentExtractor.java            # Web scraping logic
├── dto/
│   └── SummarizationResponse.java          # Response DTO
└── config/
    └── AIConfig.java                      # Spring AI configuration

src/main/resources/
└── application.yml                        # Application configuration
```

## ⚙️ Configuration

### application.yml

```yaml
spring:
  application:
    name: spring-ai-web-summarizer
  ai:
    openai:
      api-key: ${OPENAI_API_KEY}
      model: gpt-3.5-turbo

server:
  port: 8080

logging:
  level:
    root: INFO
    com.example.summarizer: DEBUG
```

### Alternative AI Providers

For Anthropic Claude:
```yaml
spring:
  ai:
    anthropic:
      api-key: ${ANTHROPIC_API_KEY}
      model: claude-3-sonnet-20240229
```

## 🛠️ Development

### Build the project
```bash
mvn clean install
```

### Run tests
```bash
mvn test
```

### Run with debug mode
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--debug"
```

## 📝 Example Use Cases

1. **News Summarization**
   ```bash
   curl "http://localhost:8080/api/summarize?url=https://news.example.com/article"
   ```

2. **Documentation Summary**
   ```bash
   curl "http://localhost:8080/api/summarize?url=https://docs.example.com/guide"
   ```

3. **Blog Post Summary**
   ```bash
   curl "http://localhost:8080/api/summarize?url=https://blog.example.com/post"
   ```

## ⚠️ Limitations

- Maximum content length: 5000 characters (configurable)
- Request timeout: 10 seconds
- Respects robots.txt and rate limits
- Some websites may block automated access

## 🔐 Security Considerations

- Store API keys in environment variables, never in code
- Validate and sanitize URLs before processing
- Implement rate limiting for production use
- Use HTTPS for all API endpoints

## 📚 Dependencies

- **Spring Boot 3.1.5** - Application framework
- **Spring AI 0.8.1** - AI/ML framework
- **JSoup 1.15.3** - HTML parser and web scraper
- **OpenAI API** - Language model provider

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License

MIT License - see LICENSE file for details

## 📧 Contact

For questions or support, please open an issue on GitHub.
