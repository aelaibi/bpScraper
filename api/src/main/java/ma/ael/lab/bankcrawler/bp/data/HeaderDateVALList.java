package ma.ael.lab.bankcrawler.bp.data;

/**
 * Created by aelaibi on 16/06/2017.
 */

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Id",
        "Key",
        "Value",
        "Selected"
})
public class HeaderDateVALList {

    @JsonProperty("Id")
    private Integer id;
    @JsonProperty("Key")
    private String key;
    @JsonProperty("Value")
    private String value;
    @JsonProperty("Selected")
    private Object selected;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("Id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("Key")
    public String getKey() {
        return key;
    }

    @JsonProperty("Key")
    public void setKey(String key) {
        this.key = key;
    }

    @JsonProperty("Value")
    public String getValue() {
        return value;
    }

    @JsonProperty("Value")
    public void setValue(String value) {
        this.value = value;
    }

    @JsonProperty("Selected")
    public Object getSelected() {
        return selected;
    }

    @JsonProperty("Selected")
    public void setSelected(Object selected) {
        this.selected = selected;
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
