package ma.ael.bank;

import ma.ael.bank.crawler.bp.BPCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class Application implements ApplicationRunner {
    private final static Logger LOGGER = LoggerFactory.getLogger(Application.class);



    @Autowired
    private BPCrawler bpCrawler;

    @Value("${p}")
    private String pwd;
    @Value("${i}")
    private String identifiant;

    @Override
    public void run(ApplicationArguments args) {
        LOGGER.info("Application started with command-line arguments: {} ", args.getOptionNames());

        if (identifiant != null && pwd!=null) {

            try {
                bpCrawler.loadOperations(identifiant, pwd);
            } catch (Exception e) {
                LOGGER.error("",e);
            }
        } else {
            System.err.println("Arguments  are missing!, her is an exemple : java ma.ael.bank.Application login password");
        }
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }



}