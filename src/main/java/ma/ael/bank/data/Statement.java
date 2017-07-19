package ma.ael.bank.data;

/**
 * Created by aelaibi on 16/06/2017.
 */
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Datedeb",
        "Datefin",
        "SldDeb",
        "SldFin",
        "DateSoldeFin",
        "Cumul",
        "CumulJour",
        "Montant",
        "CodeOpib",
        "CodeOpi",
        "NumCompte",
        "Dateope",
        "Dateval",
        "LibOpe",
        "RefOpe",
        "SensOpe",
        "DateEnr"
})
public class Statement {

    @JsonProperty("Datedeb")
    private String datedeb;
    @JsonProperty("Datefin")
    private String datefin;
    @JsonProperty("SldDeb")
    private String sldDeb;
    @JsonProperty("SldFin")
    private String sldFin;
    @JsonProperty("DateSoldeFin")
    private String dateSoldeFin;
    @JsonProperty("Cumul")
    private String cumul;
    @JsonProperty("CumulJour")
    private String cumulJour;
    @JsonProperty("Montant")
    private String montant;
    @JsonProperty("CodeOpib")
    private String codeOpib;
    @JsonProperty("CodeOpi")
    private String codeOpi;
    @JsonProperty("NumCompte")
    private String numCompte;
    @JsonProperty("Dateope")
    private String dateope;
    @JsonProperty("Dateval")
    private String dateval;
    @JsonProperty("LibOpe")
    private String libOpe;
    @JsonProperty("RefOpe")
    private String refOpe;
    @JsonProperty("SensOpe")
    private String sensOpe;
    @JsonProperty("DateEnr")
    private String dateEnr;


    @JsonProperty("Datedeb")
    public String getDatedeb() {
        return datedeb;
    }

    @JsonProperty("Datedeb")
    public void setDatedeb(String datedeb) {
        this.datedeb = datedeb;
    }

    @JsonProperty("Datefin")
    public String getDatefin() {
        return datefin;
    }

    @JsonProperty("Datefin")
    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    @JsonProperty("SldDeb")
    public String getSldDeb() {
        return sldDeb;
    }

    @JsonProperty("SldDeb")
    public void setSldDeb(String sldDeb) {
        this.sldDeb = sldDeb;
    }

    @JsonProperty("SldFin")
    public String getSldFin() {
        return sldFin;
    }

    @JsonProperty("SldFin")
    public void setSldFin(String sldFin) {
        this.sldFin = sldFin;
    }

    @JsonProperty("DateSoldeFin")
    public String getDateSoldeFin() {
        return dateSoldeFin;
    }

    @JsonProperty("DateSoldeFin")
    public void setDateSoldeFin(String dateSoldeFin) {
        this.dateSoldeFin = dateSoldeFin;
    }

    @JsonProperty("Cumul")
    public String getCumul() {
        return cumul;
    }

    @JsonProperty("Cumul")
    public void setCumul(String cumul) {
        this.cumul = cumul;
    }

    @JsonProperty("CumulJour")
    public String getCumulJour() {
        return cumulJour;
    }

    @JsonProperty("CumulJour")
    public void setCumulJour(String cumulJour) {
        this.cumulJour = cumulJour;
    }

    @JsonProperty("Montant")
    public String getMontant() {
        return montant;
    }

    @JsonProperty("Montant")
    public void setMontant(String montant) {
        this.montant = montant;
    }

    @JsonProperty("CodeOpib")
    public String getCodeOpib() {
        return codeOpib;
    }

    @JsonProperty("CodeOpib")
    public void setCodeOpib(String codeOpib) {
        this.codeOpib = codeOpib;
    }

    @JsonProperty("CodeOpi")
    public String getCodeOpi() {
        return codeOpi;
    }

    @JsonProperty("CodeOpi")
    public void setCodeOpi(String codeOpi) {
        this.codeOpi = codeOpi;
    }

    @JsonProperty("NumCompte")
    public String getNumCompte() {
        return numCompte;
    }

    @JsonProperty("NumCompte")
    public void setNumCompte(String numCompte) {
        this.numCompte = numCompte;
    }

    @JsonProperty("Dateope")
    public String getDateope() {
        return dateope;
    }

    @JsonProperty("Dateope")
    public void setDateope(String dateope) {
        this.dateope = dateope;
    }

    @JsonProperty("Dateval")
    public String getDateval() {
        return dateval;
    }

    @JsonProperty("Dateval")
    public void setDateval(String dateval) {
        this.dateval = dateval;
    }

    @JsonProperty("LibOpe")
    public String getLibOpe() {
        return libOpe;
    }

    @JsonProperty("LibOpe")
    public void setLibOpe(String libOpe) {
        this.libOpe = libOpe;
    }

    @JsonProperty("RefOpe")
    public String getRefOpe() {
        return refOpe;
    }

    @JsonProperty("RefOpe")
    public void setRefOpe(String refOpe) {
        this.refOpe = refOpe;
    }

    @JsonProperty("SensOpe")
    public String getSensOpe() {
        return sensOpe;
    }

    @JsonProperty("SensOpe")
    public void setSensOpe(String sensOpe) {
        this.sensOpe = sensOpe;
    }

    @JsonProperty("DateEnr")
    public String getDateEnr() {
        return dateEnr;
    }

    @JsonProperty("DateEnr")
    public void setDateEnr(String dateEnr) {
        this.dateEnr = dateEnr;
    }



    public String toString() {
        return
                org.apache.commons.lang.builder.ToStringBuilder.reflectionToString(this, org.apache.commons.lang.builder.ToStringStyle.SIMPLE_STYLE);
    }

}
