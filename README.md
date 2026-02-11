## Changelog
- 0.8.0 
  - added question counter
- 0.7.0 
  - no historical data...

## Running in dev mode
* Start a database container with `docker-compose-mysql.yml`
* Run `mvn install` on module domain.
* Run `mvn liberty:dev` on module importer_service. 
* Run `mvn liberty:dev` on module question_service. 
  * Note: Rancher Desktop runs on 9080 & 9443, so choose other ports for liberty. 

To import initial questions, use the importer service swagger ui (e.g. http://localhost:9082/openapi/ui/) to post the initial set from `importer_service/src/main/resources/sample-data.json`.
                
To create an admin user, start the user service (other repo) and use its swagger ui to post a user to the system with admin true.

To build and push a docker image for a module, use:
```console
mvn clean package
mvn docker:build
mvn docker:push
```
The labels for each image are listed in the `io.fabric8:docker-maven-plugin` configuration.
