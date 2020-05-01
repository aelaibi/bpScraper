package ma.ael.lab.bankcrawler.bp.data;

/**
 * Created by aelaibi on 16/06/2017.
 */
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
        return sldDeb.replace(",",".");
    }

    @JsonProperty("SldDeb")
    public void setSldDeb(String sldDeb) {
        this.sldDeb = sldDeb.replace(",",".");
    }

    @JsonProperty("SldFin")
    public String getSldFin() {
        return sldFin.replace(",",".");
    }

    @JsonProperty("SldFin")
    public void setSldFin(String sldFin) {
        this.sldFin = sldFin.replace(",",".");
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
        return cumul.replace(",",".");
    }

    @JsonProperty("Cumul")
    public void setCumul(String cumul) {
        this.cumul = cumul.replace(",",".");
    }

    @JsonProperty("CumulJour")
    public String getCumulJour() {
        return cumulJour.replace(",",".");
    }

    @JsonProperty("CumulJour")
    public void setCumulJour(String cumulJour) {
        this.cumulJour = cumulJour.replace(",",".");
    }

    @JsonProperty("Montant")
    public String getMontant() {
        return montant.replace(",",".");
    }

    @JsonProperty("Montant")
    public void setMontant(String montant) {
        this.montant = montant.replace(",",".");
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





}
