# ReadMe of Twitter Engine

## Runtime Configuration

1. Java 8
2. Node 9 or above

## Build Configuration

1. Maven 3.3.9
2. npm 5.6.0 or above

## Running pre-built artifacts

1. Navigate to twitter-engine/artifacts
2. run start-backend.sh to start the backend service
3. run start-frontend.sh to start the frontend service

## Building the source

### Building the frontend

1. Navigate to twitter-engine/twitter-engine-frontend
2. Build the source with command 'npm install'
3. To run the source use command 'npm start'

### Building the backend

1. Navigate to twitter-engine/twitter-engine-backend
2. Build the source with command 'mvn clean install'
3. To run the artifact that was build use command 'java -jar target/twitter-engine-backend.jar'

## Logging

* Server logs will be available on twitter-engine/artifacts/application.log