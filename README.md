

This is a simple crawler for https://bpnet.gbp.ma, 
it simulates a real user  authentication then export the last 3 months statements into a csv file.
it contains also a rest api module and some python script to analyse data

```shell
git clone https://github.com/aelaibi/bpScraper.git
cd bpScraper
```


#### CMD-LINE quick start

    cd cmd-line
    mvn clean package
    java -jar target/bpcrawler-1.0-SNAPSHOT.jar --i <identifiant> --p <password>
    ls -alt data

#### API quick start

    cd api
    ./gradlew clean bootRun
    curl --location --request POST 'localhost:8080/api/v1/bp/<identifiant>/statements' \
		--header 'Content-Type: application/json' \
		--data-raw '{
			"password" : "password"
		}'
    

    
#### Tech Stack

The project is based on spring-boot (JDK8)
