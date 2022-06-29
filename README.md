# Miles Pay
 
Miles Pay is a simplified application for interfacing with Extend for virtual cards


[https://developer.paywithextend.com/#extend-api](https://developer.paywithextend.com/#extend-api)

### Backend
- Build for docker:

   `./gradlew bootJar`

### Frontend
- Install dependencies

   `./gradlew npmInstall`


- Copy to static directory in project

   `./gradlew appNpmBuild`


### Docker
#### Build
`docker build -t milespay:latest .`

#### Run
`docker run -p 8000:8080 milespay:latest`

Open browser to [localhost:8000](http://localhost:8000)
