package ma.ael.bank.data;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;



public @Data class Operation {


    private String ref;
	private Date dateOperation;
	private Date dateValeur;
	private String libelleOperation;
	private Double montantDebit;
	private Double montantCredit;
    private Double montantSolde;
	private String categorie;
	private String compte;
	private String contrat;
	




    @Override
    public String toString() {
        return ToStringBuilder.
				reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }


}
