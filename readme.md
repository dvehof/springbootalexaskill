## Spring Boot Project for a self/cloud hosted Alexa Skill Service
This project is a sample for a self hosted Spring Boot Serivce you can use for your Custom Alexa Skill instead of using AWS.
It just converts a given date to a day of the week.

When deployed it needs to be accessible via HTTPS as Amazon only accepts https endpoints as skill backends.

### Endpoint
The endpoint (per default) is **http://localhost:5050/dayofdateskill/alexaservice**

Port can be changed via **application.properties**

Just put it behind a https configured nginx or apache with letesencrypt certificate (trusted by Amazon)

### Skill Configuration
Sample intents and utterances can be found in directory **skillconfig**

### Build
Just run a **mvn package** to get an executable dayofdateskill.jar

### Run
**java -jar dayofdateskill.jar**

### Parameters for production
For production you should pass additional jvm parameters
* **-Dcom.amazon.speech.speechlet.servlet.supportedApplicationIds=YOURSKILLID**
* **-Dcom.amazon.speech.speechlet.servlet.timestampTolerance=TOLERANCE**

see Amazon documentation ([here](https://developer.amazon.com/public/solutions/alexa/alexa-skills-kit/docs/handling-requests-sent-by-alexa) and [here](https://developer.amazon.com/public/solutions/alexa/alexa-skills-kit/docs/developing-an-alexa-skill-as-a-web-service)) for further information

 
