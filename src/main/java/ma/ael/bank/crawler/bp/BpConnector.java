package ma.ael.bank.crawler.bp;

import com.fasterxml.jackson.databind.ObjectMapper;
import ma.ael.bank.data.GetStatementsResp;
import ma.ael.bank.utils.RegExHelper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.Asserts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

@org.springframework.stereotype.Component
public class BpConnector {
    public static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36";
    public static final String HEADER_ACCEPT = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";
    private static Logger LOGGER = LoggerFactory.getLogger(BpConnector.class);
    BasicCookieStore cookieStore;
    CloseableHttpClient httpclient;

    public BpConnector() {
        this.cookieStore = new BasicCookieStore();


        this.httpclient = HttpClients.custom().setDefaultCookieStore(this.cookieStore).build();
    }




    public void login(String pwd, String identifiantContrat) throws URISyntaxException, IOException {
        String homeAnonymUrl = "https://bpnet.gbp.ma/Dashboard";

        String loginHtml = getHttp(homeAnonymUrl);
        LOGGER.debug("login page {}",loginHtml);

        String reqToken = RegExHelper.findFirst("name=\"__RequestVerificationToken\" type=\"hidden\"( *)value=\"([a-zA-Z0-9_-]*?)\"", loginHtml,false,2);
        LOGGER.debug(reqToken);
        String cid = RegExHelper.findFirst("name=\"cid\" type=\"hidden\"( *)value=\"([a-zA-Z0-9_-]*?)\"", loginHtml,false,2);
        LOGGER.debug(cid);
        String ck = RegExHelper.findFirst("name=\"ck\" type=\"hidden\"( *)value=\"([a-zA-Z0-9_-]*?)\"", loginHtml,false,2);
        LOGGER.debug(ck);
        String assfid = RegExHelper.findFirst("name=\"as_sfid\"( *)value=\"([a-zA-Z0-9_\\-\\=]*?)\"", loginHtml,false,2);
        LOGGER.debug(assfid);
        String asfid = RegExHelper.findFirst("name=\"as_fid\"( *)value=\"([a-zA-Z0-9_\\-\\=]*?)\"", loginHtml,false,2);
        LOGGER.debug(asfid);



        HttpUriRequest login = RequestBuilder
                .post()
                .setUri(new URI("https://oauth2.gbp.ma/Auth/Login"))
                .addParameter("__RequestVerificationToken",reqToken)
                .addParameter("cid",cid)
                .addParameter("ck",ck)
                .addParameter("UserName", identifiantContrat)
                .addParameter("Password", pwd)
                .addParameter("as_sfid",assfid)
                .addParameter("as_fid",asfid)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Upgrade-Insecure-Requests", "1")
                .addHeader("User-Agent", USER_AGENT)
                .build();
        String loginOut = postHttp(login);
        LOGGER.debug("login out {}",loginOut);

        String acs = RegExHelper.findFirst("name='acs'( *)value='([a-zA-Z0-9_\\-\\=\\.]*?)'", loginOut,false,2);
        LOGGER.debug(acs);
        String act = RegExHelper.findFirst("name='act'( *)value='([a-zA-Z0-9_\\-\\=\\.]*?)'", loginOut,false,2);
        LOGGER.debug(act);
        String senderid = RegExHelper.findFirst("name='senderid'( *)value='([a-zA-Z0-9_\\-\\=\\.]*?)'", loginOut,false,2);
        LOGGER.debug(senderid);
        String activityID = RegExHelper.findFirst("name='activityID'( *)value='([a-zA-Z0-9_\\-\\=\\.]*?)'", loginOut,false,2);
        LOGGER.debug(activityID);
        String returnURL = RegExHelper.findFirst("name='returnURL'( *)value='([a-zA-Z0-9_\\-\\.\\:\\/]*?)'", loginOut,false,2);
        LOGGER.debug(returnURL);
         assfid = RegExHelper.findFirst("name=\"as_sfid\"( *)value=\"([a-zA-Z0-9_\\-\\=]*?)\"", loginOut,false,2);
        LOGGER.debug(assfid);
         asfid = RegExHelper.findFirst("name=\"as_fid\"( *)value=\"([a-zA-Z0-9_\\-\\=]*?)\"", loginOut,false,2);
        LOGGER.debug(asfid);


        HttpUriRequest afterLogin = RequestBuilder
                .post()
                .setUri(new URI("https://bpnet.gbp.ma//Account/AfterLogin"))
                .addParameter("acs",acs)
                .addParameter("act",act)
                .addParameter("senderid",senderid)
                .addParameter("returnURL", returnURL)
                .addParameter("activityID", activityID)
                .addParameter("as_sfid",assfid)
                .addParameter("as_fid",asfid)
                .addHeader("Accept", HEADER_ACCEPT)
                .addHeader("Referer","https://oauth2.gbp.ma/Auth/Login")
                .addHeader("Origin","https://oauth2.gbp.ma")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Upgrade-Insecure-Requests", "1")
                .addHeader("User-Agent", USER_AGENT)
                .addHeader("Accept-Language", "en-US,en;q=0.8,fr;q=0.6,ar;q=0.4")
                .addHeader("Cache-Control", "max-age=0")
                .build();

        String afterLoginOut = postHttp(afterLogin);
        LOGGER.debug("after login out {}", afterLoginOut);


        Asserts.check(afterLoginOut.contains("Object moved to <a href=\"/Dashboard\">here</a>"), " error login");
        LOGGER.info("login with success");

        //getHome();
    }

    public GetStatementsResp getStatements() throws URISyntaxException, IOException {
        LOGGER.info("Loading statements");
        HttpUriRequest tmp = RequestBuilder
                .post()
                .setUri(new URI("https://bpnet.gbp.ma/Compte/Compte/GetStatements"))
                .addParameter("x-ms-request-id","zwWF")
                .addParameter("x-ms-request-root-id","2SFvy")
                .addParameter("X-Requested-With","XMLHttpRequest")
                .addHeader("Content-Type", "application/json")
                .build();
        String opsJson = postHttp(tmp);
        GetStatementsResp pojo = new ObjectMapper()
                .readerFor(GetStatementsResp.class)
                .readValue(opsJson);
        LOGGER.info("Statements loaded {}",opsJson);
        return pojo;
    }






    String postHttp(HttpUriRequest login) throws IOException {
        String out = "";
        CloseableHttpResponse response2 = this.httpclient.execute(login);
        try {
            HttpEntity entity = response2.getEntity();
            out = httpResp2String(response2, false);

            EntityUtils.consume(entity);
        } finally {
            response2.close();
        }
        return out;
    }

    String getHttp(String homeAnonymUrl) throws IOException {
        String out = "";
        HttpGet httpget = new HttpGet(homeAnonymUrl);
        CloseableHttpResponse response1 = this.httpclient.execute(httpget);
        try {
            HttpEntity entity = response1.getEntity();
            out = httpResp2String(response1, false);

            EntityUtils.consume(entity);
        } finally {
            response1.close();
        }
        return out;
    }




    String httpResp2String(HttpResponse loginresp, boolean show)
            throws IOException {
        String encoding = loginresp.getEntity().getContentEncoding() == null ? "iso-8859-1" : loginresp.getEntity().getContentEncoding().getValue();


        BufferedReader rd = new BufferedReader(new InputStreamReader(loginresp.getEntity().getContent(), encoding));

        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            if (show)
                LOGGER.debug(line);
            result.append(line);
        }
        return result.toString();
    }


}