package ma.ael.bank.crawler.bp;

import ma.ael.bank.data.Operation;
import ma.ael.bank.utils.ConvertException;
import ma.ael.bank.utils.DateHelper;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;


public class BPCrawler {


    private static Logger LOGGER = LoggerFactory.getLogger(BPCrawler.class);

    private static final String HEADER = "ref,dateOperation,dateValeur,libelleOperation,montantDebit,montantCredit,montantSolde,categorie,compte,contrat";
    static String DATA_PATH ="/Users/admin/dev/bank/data/test"; // '/prod' for PRODUCTION
    static String FILE_NAME_PATTERN = "ops-ddmmyyyy.txt";


    static BpConnector bpConnector = new BpConnector();
    public static void main(String[] args) throws Exception {


        //getLastLoadingDate("/Users/admin/dev/bank/data/test/2204324/22043240005");




    }


    public  void loadOperations(String identifiantContrat, String pwd) throws IOException, URISyntaxException, ConvertException {

        bpConnector.login(pwd,identifiantContrat);

        List<String> comptes = bpConnector.getComptes();
        for (String cpt : comptes){
            loadOperationsForCompte(identifiantContrat, cpt);
        }


    }

    private static void loadOperationsForCompte(String identifiantContrat, String cpt) throws ConvertException,
            IOException, URISyntaxException {
        String compteDataPath = DATA_PATH+"/"+identifiantContrat +"/"+cpt;
        String lastDate = convert2BPDate(getLastLoadingDate(compteDataPath));
        if(isLastLoadIsToday(lastDate)){
            LOGGER.info("we have just loaded ops today for {} - {}", identifiantContrat,cpt);
            return;
        }
        if(null == lastDate){
            lastDate  = DateHelper.toString(DateHelper.add2Date(new Date(), -91));
        }

        String yesterday = DateHelper.yesterday();
        String now = DateHelper.toString(new Date(), "yyyyMMdd");
        List<Operation> ops = bpConnector.getOperations(cpt, lastDate, yesterday);
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(compteDataPath+"/"+"ops-"+now+".txt"))) {
            writer.write(HEADER);
            for (Operation op : ops){
                op.setContrat(identifiantContrat);
                writer.newLine();
                writer.write(op.toString());
            }

        }


    }

    private static boolean isLastLoadIsToday(String lastDate) throws ConvertException {
        return (DateHelper.toString(new Date())).equals(lastDate);
    }

    private static String convert2BPDate(String fnameDate) {
        String ret = fnameDate.substring(6)+"/"+fnameDate.substring(4,6)+"/"+fnameDate.substring(0,4);;
        return ret;
    }

    private static String getLastLoadingDate(String compteDataPath) {

        if(!Files.exists(Paths.get(compteDataPath))){
            new File(compteDataPath).mkdirs();
            return null;
        }//new StringBuilder(path.getFileName().toString()).reverse().toString()
        try (Stream<Path> stream = Files.list(Paths.get(compteDataPath))) {
            String lastDate = stream
                    .map(path -> extractDate(path))
                            //.filter(path -> !path.startsWith("."))
                            .sorted((p1, p2) -> p2.compareTo(p1))
                            .findFirst().orElse(null);
                            //.collect(Collectors.joining("; "));
            System.out.println("last date: " + lastDate);
            return lastDate;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String extractDate(Path path) {
        String fnameDate = path.getFileName().toString().substring(4,12);
        //String fname = fnameDate.substring(4)+fnameDate.substring(2,4)+fnameDate.substring(0,2);;
        return fnameDate;
    }


}
