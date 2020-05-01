package ma.ael.lab.bankcrawler.bp.data;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aelaibi on 16/06/2017.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Statements",
        "HeaderDateOPEList",
        "HeaderDateVALList"
})
public class GetStatementsResp {


    @JsonProperty("Statements")
    private List<Statement> statements = null;
    @JsonProperty("HeaderDateOPEList")
    private List<HeaderDateOPEList> headerDateOPEList = null;
    @JsonProperty("HeaderDateVALList")
    private List<HeaderDateVALList> headerDateVALList = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Statements")
    public List<Statement> getStatements() {
        return statements;
    }

    @JsonProperty("Statements")
    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }

    @JsonProperty("HeaderDateOPEList")
    public List<HeaderDateOPEList> getHeaderDateOPEList() {
        return headerDateOPEList;
    }

    @JsonProperty("HeaderDateOPEList")
    public void setHeaderDateOPEList(List<HeaderDateOPEList> headerDateOPEList) {
        this.headerDateOPEList = headerDateOPEList;
    }

    @JsonProperty("HeaderDateVALList")
    public List<HeaderDateVALList> getHeaderDateVALList() {
        return headerDateVALList;
    }

    @JsonProperty("HeaderDateVALList")
    public void setHeaderDateVALList(List<HeaderDateVALList> headerDateVALList) {
        this.headerDateVALList = headerDateVALList;
    }



    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
