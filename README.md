# slack_bot_demo
Spring Boot application that sends a message to a Slack channel using Slack's  Incoming Webhooks.

## Table of Contents

1. [Project Structure](#project-structure)
2. [Development Decisions](#development-decisions)
3. [Setup Instructions](#setup-instructions)
4. [Running the Application](#running-the-application)
5. [Setting Up Slack Incoming Webhook](#setting-up-slack-incoming-webhook)
6. [Testing](#testing)

## Project Structure

The project follows the standard Spring Boot structure:
slack-bot-demo
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── slackbotdemo
│   │   │               ├── controller
│   │   │               │   └── SlackController.java
│   │   │               ├── service
│   │   │               │   ├── ISlackService.java
│   │   │               │   └── impl
│   │   │               │       └── SlackServiceImpl.java
│   │   │               ├── entity
│   │   │               │   └── SlackMessage.java
│   │   │               ├── configuration
│   │   │               │   └── SlackConfig.java
│   │   └── resources
│   │       └── application.properties
│   ├── test
│   │   └── java
│   │       └── com
│   │           └── example
│   │               └── slackbotdemo
│   │                   ├── SlackServiceTest.java
│   │                   ├── SlackControllerTest.java
├── .gitignore
├── README.md
└── pom.xml

### Main Components

- **Controller**: Contains the `SlackController` class which handles the REST API requests.
- **Service**: Contains the `SlackServiceImpl` class which implements the `ISlackService` interface and includes the business logic for sending messages to Slack.
- **Model**: Contains the `SlackMessage` class which represents the message payload.
- **Resources**: Contains the `application.properties` file for configuration.
- **Tests**: Contains unit and integration tests for the service and controller classes.

## Development Decisions

- **Spring Boot**: Chosen for its simplicity and ease of setup for creating RESTful services.
- **RestTemplate**: Used for making HTTP requests to the Slack Incoming Webhook.
- **ObjectMapper**: Used for converting Java objects to JSON.
- **JUnit and Mockito**: Used for writing unit and integration tests to ensure the correctness of the application.

## Setup Instructions

### Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher
- A Slack workspace with permission to create webhooks

### Cloning the Repository

git clone https://github.com/PavanFernandes/slack_bot_demo.git
cd slack_bot_demo

### Configuration

Slack Webhook URL:
Create a Slack Incoming Webhook (instructions below) and copy the webhook URL.
Add the webhook URL to the application.properties file:
  slack.webhook.url=YOUR_SLACK_WEBHOOK_URL

### Running the Application

Build the project: mvn clean install
Run the application: mvn spring-boot:run

### Send a test message:

Use a tool like Postman or curl to send a POST request to http://localhost:8080/slack/sendMessage with the following JSON payload:
json
Copy code
{
  "text": "Hello, Slack!"
}

### Setting Up Slack Incoming Webhook

Go to Slack API:

Navigate to Slack API.
Create a new app:

Click "Create New App".
Choose "From scratch" and give your app a name and select the workspace.
Set up Incoming Webhook:

Go to "Incoming Webhooks" on the left sidebar.
Turn on "Activate Incoming Webhooks".
Click "Add New Webhook to Workspace".
Select the channel you want to post messages to and click "Allow".
Copy the generated webhook URL.
Add Webhook URL to Application:

Open the application.properties file in your project.
Add the webhook URL:
properties
slack.webhook.url=https://hooks.slack.com/services/T00000000/B00000000/XXXXXXXXXXXXXXXXXXXXXXXX

### Testing

Unit Tests
Unit tests are provided for the service method that sends messages to Slack. These tests mock the RestTemplate and ObjectMapper to simulate sending messages without making actual HTTP requests.

Integration Tests
Integration tests are provided for the /slack/sendMessage endpoint to ensure the end-to-end functionality of the application.

Running Tests
To run the tests, use the following command:

mvn test

### Conclusion

This project demonstrates how to integrate a Spring Boot application with Slack using Incoming Webhooks. The application is designed to be simple and easily extensible for more advanced use cases.

If you encounter any issues or have questions, feel free to open an issue on the GitHub repository.
