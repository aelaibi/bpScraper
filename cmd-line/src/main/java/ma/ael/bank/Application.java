package ma.ael.bank;

import lombok.extern.slf4j.Slf4j;
import ma.ael.bank.crawler.bp.BPCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Set;


@SpringBootApplication
@Slf4j
public class Application implements ApplicationRunner {
    //private final static Logger LOGGER = LoggerFactory.getLogger(Application.class);



    @Autowired
    private BPCrawler bpCrawler;

    @Value("${p}")
    private String pwd;
    @Value("${i}")
    private String identifiant;

    @Override
    public void run(ApplicationArguments args) {
        log.info("Application started with command-line arguments: {} ", args.getOptionNames());
        final List<String> nonOptionArgs = args.getNonOptionArgs();
        final String[] sourceArgs = args.getSourceArgs();
        final Set<String> optionNames = args.getOptionNames();
        nonOptionArgs.forEach(nonOption -> log.info("## Non Option Args : "+nonOption));
        optionNames.forEach(option -> log.info("## Option Names    : "+option));
        Arrays.stream(sourceArgs).forEach(srcArgs ->log.info("## Source Args     : "+srcArgs));


        if (identifiant != null && pwd!=null) {

            try {
                bpCrawler.loadOperations(identifiant, pwd);
            } catch (Exception e) {
                log.error("",e);
            }
        } else {
            log.error("Arguments  are missing!, her is an exemple : java ma.ael.bank.Application login password");
        }
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }



}
