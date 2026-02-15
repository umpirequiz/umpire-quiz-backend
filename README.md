## Changelog
- 0.8.1
  - update question count to include only enabled questions
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

To build and push a docker image for a module
- first `mvn clean install` everything (from root)
- then in a module dir, use:
	```console
	mvn docker:build
	mvn docker:push
	```
 
The labels for each image are listed in the `io.fabric8:docker-maven-plugin` configuration.
