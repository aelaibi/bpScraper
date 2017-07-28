

This is a simple crawler for https://bpnet.gbp.ma, it simulates a real user  authentication then export the last 3 months statements into a csv file.


Quick start
----------

    git clone https://github.com/aelaibi/bpScraper.git
    cd bpScraper
    mvn clean package
    java -jar target/bpcrawler-1.0-SNAPSHOT.jar --i <identifiant> --p <password>
    ls -alt data

    
Tech Stack
----------

The project is based on org.springframework.boot 1.5.4.RELEASE and org.apache.httpcomponents:httpclient
