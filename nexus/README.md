# Nexus automation

To start nexus
```sh
docker-compose up -d
```

To stop nexus
```sh
docker-compose stop -d
```
To clean completely (Be careful , it will delete the volume as well)
```sh
docker-compose down -v 
```

### Repositories automation
By default script upload and run is disabled . To enable follow the below steps:

Edit $data-dir/etc/nexus.properties. Add the following on a new line, making sure the file is saved with an ending new line and with the original file permissions:
```
nexus.scripts.allowCreation=true
```
Restart Nexus to pick up the property change.

### upload a script
List all the script uploaded 
```sh
curl -X GET "http://localhost:8081/nexus/service/rest/v1/script" -H "accept: application/json"
```

to upload a script
```sh
curl -X POST "http://localhost:8081/nexus/service/rest/v1/script" -H "accept: application/json" -H "Content-Type: application/json" -d {
    "name": "maven-hosted-releases",
    "type": "groovy",
    "content": "repository.createMavenHosted('maven-hosted-releases')"
}
```
### run a script
```sh
curl -X POST "http://localhost:8081/nexus/service/rest/v1/script/{SCRIPT_NAME_FROM_ABOVE}/run" -H "accept: application/json" -H "Content-Type: text/plain" -d "string"
```