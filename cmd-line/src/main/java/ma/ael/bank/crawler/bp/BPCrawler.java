package ma.ael.bank.crawler.bp;

import ma.ael.bank.data.GetStatementsResp;
import ma.ael.bank.data.Statement;
import ma.ael.bank.utils.ConvertException;
import ma.ael.bank.utils.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@Component
public class BPCrawler {
    private final Logger LOGGER = LoggerFactory.getLogger(BPCrawler.class);
    private final String HEADER = "Datedeb,Datefin,SldDeb,SldFin,DateSoldeFin,Cumul,CumulJour,Montant,CodeOpib,CodeOpi,NumCompte,Dateope,Dateval,LibOpe,RefOpe,SensOpe,DateEnr";
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




}