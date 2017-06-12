package ma.ael.bank.crawler.bp;

import ma.ael.bank.data.Operation;
import ma.ael.bank.utils.ConvertException;
import ma.ael.bank.utils.DateHelper;
import ma.ael.bank.utils.Numbers;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.Asserts;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

public class BpConnector {

    private static Logger LOGGER = LoggerFactory.getLogger(BpConnector.class);

    BasicCookieStore cookieStore ;
    CloseableHttpClient httpclient;

    public BpConnector(){
       cookieStore = new BasicCookieStore();
        httpclient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();
    }

    public  List<Operation> getOperations(String cpt, String dateDebu, String today) throws URISyntaxException, IOException, ConvertException {
        HttpUriRequest opReq = RequestBuilder.post()
                .setUri(new URI("https://bpnet.gbp.ma/Compte/His_Cpt_Rep.asp"))
                .addParameter("lib_dev", "")
                .addParameter("cpt", cpt)
                .addParameter("controle", "N")
                .addParameter("DateOperD", dateDebu)
                .addParameter("DateOperF", today)
                .addParameter("DatevalD", "")
                .addParameter("DatevalF", "")
                .addParameter("montantMin", "0")
                .addParameter("montantMax", "")
                .addParameter("sens", "")
                .addParameter("ref", "")
                .addParameter("lib", "")
                .addParameter("trier", "ASC")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        String ops = postHttp(opReq);

        LOGGER.debug(ops);
        Document doc2 = Jsoup.parse(ops);
        Elements inputElements2 = doc2.select("tr[valign]");
        LOGGER.debug("ops : ");
        List<Operation> operations = new ArrayList<Operation>();
        inputElements2.remove(0); // to skip header
        for (Element inputElement : inputElements2) {
            //LOGGER.debug(inputElement.text());
            Operation o = new Operation();
            o.setDateOperation(DateHelper.toDate(inputElement.child(0).text()));
            o.setDateValeur(DateHelper.toDate(inputElement.child(1).text()));
            o.setLibelleOperation(inputElement.child(2).text());
            o.setRef(inputElement.child(3).text());// on skip le libelle répété
            o.setMontantDebit(Numbers.toDouble(inputElement.child(4).text()));
            o.setMontantCredit(Numbers.toDouble(inputElement.child(5).text()));
            o.setMontantSolde(Numbers.toDouble(inputElement.child(6).text()));
            o.setCompte(cpt);
            LOGGER.debug("o : {}",o);
            operations.add(o);
        }

        return operations;
    }

    public  void login(String pwd, String identifiantContrat) throws URISyntaxException, IOException {
        //étape obligatoire , mais pourquoi ?
        String homeAnonymUrl = "https://bpnet.gbp.ma/";
        getHttp(homeAnonymUrl);
        HttpUriRequest login = RequestBuilder.post()
                .setUri(new URI("https://bpnet.gbp.ma/identification/controle.asp"))
                .addParameter("Password", pwd)
                .addParameter("Contrat", identifiantContrat)
                .addParameter("id", "0")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        String out = postHttp(login);
        LOGGER.debug(out);
        Asserts.check(out.contains("action=\"../compte/index2.asp?page=\"")," error login");
        getHome();
    }

    public  void getHome() throws URISyntaxException, IOException {
        HttpUriRequest home = RequestBuilder.post()
                .setUri(new URI("https://bpnet.gbp.ma/compte/index2.asp?page="))
                .addParameter("cache", "")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        String out = postHttp(home);
        LOGGER.debug(out);
        Asserts.check(out.contains("<title>::: Chaabi Net - Accueil :::</title>"), " error home");
    }

    public List<String>  getComptes() throws IOException {
        List<String> comptes = new ArrayList<String>();
        String comptesUrl = "https://bpnet.gbp.ma/Compte/Sit_Cpt_Req.asp";
        Document doc = Jsoup.parse(getHttp(comptesUrl));
        Elements inputElements = doc.select("option");

        for (Element inputElement : inputElements) {
            comptes.add(inputElement.text());
        }
        return comptes;
    }

     String postHttp( HttpUriRequest login) throws IOException {
        String out = "";
        CloseableHttpResponse response2 = httpclient.execute(login);

        try {
            HttpEntity entity = response2.getEntity();
            out = httpResp2String(response2, false);

            EntityUtils.consume(entity);
        } finally {
            response2.close();
        }
        return out;
    }

     String getHttp( String homeAnonymUrl) throws IOException {
        String out = "";
        HttpGet httpget = new HttpGet(homeAnonymUrl);
        CloseableHttpResponse response1 = httpclient.execute(httpget);
        try {
            HttpEntity entity = response1.getEntity();
            out = httpResp2String(response1, false);
            //LOGGER.debug("get: " + response1.getStatusLine());
            EntityUtils.consume(entity);

        } finally {
            response1.close();
        }
        return out;
    }

     void manageCookies(BasicCookieStore cookieStore) {
        List<Cookie> cookies = cookieStore.getCookies();
        if (cookies.isEmpty()) {
            //LOGGER.debug("None");
        } else {
            for (int i = 0; i < cookies.size(); i++) {
                //LOGGER.debug("- " + cookies.get(i).toString());
            }
        }
    }

     String httpResp2String(HttpResponse loginresp, boolean show) throws IOException {

        String encoding = loginresp.getEntity().getContentEncoding() == null ? "iso-8859-1" : loginresp.getEntity().getContentEncoding().getValue();

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(loginresp.getEntity().getContent(), encoding));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            if (show)
                LOGGER.debug(line);
            result.append(line);
        }
        return result.toString();
    }

    public void close() throws IOException {
        httpclient.close();
    }
}