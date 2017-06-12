package ma.ael.bank;

import ma.ael.bank.crawler.bp.BPCrawler;

/**
 * Created by ael on 14/03/2016.
 */
public class Application {



    public static void main(String[] args) throws Exception {

        if(args.length>0){
            BPCrawler crawler = new BPCrawler();

            String identifiantContrat = args[0];
            String pwd = args[1];
            crawler.loadOperations(identifiantContrat, pwd);
        }else{
            System.err.println("Arguments  are missing!, her is an exemple : java ma.ael.bank.Application login password");
        }

    }
}
