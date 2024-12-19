To import initial questions, start the importer service and use swagger ui to post the initial set (included in the importer service repo) to the system.

To create an admin user, start the user service (other repo) and use swagger ui to post a user to the system.

To build and push a docker images, use:
```console
mvn clean package
docker build -t bramjanssens/umpire-quiz-question-service .
docker push bramjanssens/umpire-quiz-question-service
```
Here are the labels for each image:
- bramjanssens/umpire-quiz-front-end
- bramjanssens/umpire-quiz-question-service
- bramjanssens/umpire-quiz-importer-service
- bramjanssens/umpire-quiz-user-service
