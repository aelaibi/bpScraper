package ma.ael.bank.data;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.ToStringBuilder;

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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
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
