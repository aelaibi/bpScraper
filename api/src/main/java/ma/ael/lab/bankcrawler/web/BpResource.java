package ma.ael.lab.bankcrawler.web;

import lombok.extern.slf4j.Slf4j;
import ma.ael.lab.bankcrawler.bp.BpConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("api/v1/bp")
@Slf4j
public class BpResource {


    @Autowired
    BpConnector bpConnector ;


    @PostMapping(value = "/{account}/statements" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity statements(@PathVariable String account, @RequestBody Credential credential){
        log.info("get statements for {}", account);
        try {
            bpConnector.login(credential.getPassword(),account);
            return ResponseEntity.ok(bpConnector.getStatements());
        } catch (URISyntaxException | IOException e) {
            log.error("error getting statements", e);
        }
        return ResponseEntity.badRequest().body("no data found");
    }

    @PostMapping(value = "/{account}/balance" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity balanceEvolution(@PathVariable String account, @RequestBody Credential credential){
        log.info("get balance evolution for {}", account);
        try {
            bpConnector.login(credential.getPassword(),account);
            return ResponseEntity.ok(bpConnector.balanceEvolution());
        } catch (URISyntaxException | IOException e) {
            log.error("error getting balance evolution", e);
        }
        return ResponseEntity.badRequest().body("no data found");
    }
}
