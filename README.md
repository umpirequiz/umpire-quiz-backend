To import initial questions, start the importer service and use swagger ui (e.g. http://localhost:9082/openapi/ui/) to post the initial set (see `src/main/resources/sample-data.json` in the importer service repo) to the system.

To create an admin user, start the user service (other repo) and use swagger ui to post a user to the system.

To build and push a docker image for a module, use:
```console
mvn clean package
mvn docker:build
mvn docker:push
```
The labels for each image are listed in the `io.fabric8:docker-maven-plugin` configuration.
