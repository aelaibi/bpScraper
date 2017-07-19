package ma.ael.bank.crawler.bp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import ma.ael.bank.data.GetStatementsResp;
import ma.ael.bank.data.Operation;
import ma.ael.bank.data.Statement;
import ma.ael.bank.utils.ConvertException;
import ma.ael.bank.utils.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BPCrawler {
    private final Logger LOGGER = LoggerFactory.getLogger(BPCrawler.class);
    private final String HEADER = "Datedeb, Datefin, SldDeb, SldFin, DateSoldeFin, Cumul, CumulJour, Montant, CodeOpib, CodeOpi, NumCompte, Dateope, Dateval, LibOpe, RefOpe, SensOpe, DateEnr";
    @Value("${data.path}")
    private String dataPATH;
    final String FILE_NAME_PATTERN = "ops-ddmmyyyy.txt";
    @Autowired
    BpConnector bpConnector ;

    public BPCrawler() {
    }

    public String getDataPATH() {
        return this.dataPATH;
    }

    public void setDataPATH(String dataPATH) {
        this.dataPATH = dataPATH;
    }

    public  void loadOperations(String identifiantContrat, String pwd) throws IOException, URISyntaxException, ConvertException {

        bpConnector.login(pwd,identifiantContrat);

        GetStatementsResp statements = bpConnector.getStatements();
        String compteDataPath = dataPATH+"/"+identifiantContrat ;
        if(!Files.exists(Paths.get(compteDataPath))){
            new File(compteDataPath).mkdirs();
        }
        String now = DateHelper.toString(new Date(), "yyyyMMdd-HHmmss.SSS");
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(compteDataPath+"/"+"ops-"+now+".txt"))) {
            writer.write(HEADER);
            for (Statement statement : statements.getStatements()){
                writer.newLine();
                writer.write(statement.toString());
            }

        }


    }

    @Deprecated
    private  void loadOperationsForCompte(String identifiantContrat, String cpt) throws ConvertException,
            IOException, URISyntaxException {
        String compteDataPath = dataPATH+"/"+identifiantContrat +"/"+cpt;
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

    private boolean isLastLoadIsToday(String lastDate) throws ConvertException {
        return DateHelper.toString(new Date()).equals(lastDate);
    }

    private String convert2BPDate(String fnameDate) {
        String ret = fnameDate.substring(6) + "/" + fnameDate.substring(4, 6) + "/" + fnameDate.substring(0, 4);
        return ret;
    }

    private  String getLastLoadingDate(String compteDataPath) {

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

    private String extractDate(Path path) {
        String fnameDate = path.getFileName().toString().substring(4, 12);
        return fnameDate;
    }
}