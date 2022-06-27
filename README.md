### Frontend
- Install dependencies

   `gradle npmInstall`


- Copy to static directory in project

   `gradle appNpmBuild`


### Docker
#### Build
`docker build -t milespay:latest .`

#### Run
`docker run -p 8000:8080 milespay:latest`

Open browser to [localhost:8000](http://localhost:8000)
