package ma.ael.bank.data;

import java.util.Date;

@Deprecated
public class Operation {
  private String ref;
  private Date dateOperation;
  private Date dateValeur;
  private String libelleOperation;
  private Double montantDebit;

  public void setRef(String ref) { this.ref = ref; } public void setDateOperation(Date dateOperation) { this.dateOperation = dateOperation; } public void setDateValeur(Date dateValeur) { this.dateValeur = dateValeur; } public void setLibelleOperation(String libelleOperation) { this.libelleOperation = libelleOperation; } public void setMontantDebit(Double montantDebit) { this.montantDebit = montantDebit; } public void setMontantCredit(Double montantCredit) { this.montantCredit = montantCredit; } public void setMontantSolde(Double montantSolde) { this.montantSolde = montantSolde; } public void setCategorie(String categorie) { this.categorie = categorie; } public void setCompte(String compte) { this.compte = compte; } public void setContrat(String contrat) { this.contrat = contrat; } public boolean equals(Object o) { if (o == this) return true; if (!(o instanceof Operation)) return false; Operation other = (Operation)o; if (!other.canEqual(this)) return false; Object this$ref = getRef();Object other$ref = other.getRef(); if (this$ref == null ? other$ref != null : !this$ref.equals(other$ref)) return false; Object this$dateOperation = getDateOperation();Object other$dateOperation = other.getDateOperation(); if (this$dateOperation == null ? other$dateOperation != null : !this$dateOperation.equals(other$dateOperation)) return false; Object this$dateValeur = getDateValeur();Object other$dateValeur = other.getDateValeur(); if (this$dateValeur == null ? other$dateValeur != null : !this$dateValeur.equals(other$dateValeur)) return false; Object this$libelleOperation = getLibelleOperation();Object other$libelleOperation = other.getLibelleOperation(); if (this$libelleOperation == null ? other$libelleOperation != null : !this$libelleOperation.equals(other$libelleOperation)) return false; Object this$montantDebit = getMontantDebit();Object other$montantDebit = other.getMontantDebit(); if (this$montantDebit == null ? other$montantDebit != null : !this$montantDebit.equals(other$montantDebit)) return false; Object this$montantCredit = getMontantCredit();Object other$montantCredit = other.getMontantCredit(); if (this$montantCredit == null ? other$montantCredit != null : !this$montantCredit.equals(other$montantCredit)) return false; Object this$montantSolde = getMontantSolde();Object other$montantSolde = other.getMontantSolde(); if (this$montantSolde == null ? other$montantSolde != null : !this$montantSolde.equals(other$montantSolde)) return false; Object this$categorie = getCategorie();Object other$categorie = other.getCategorie(); if (this$categorie == null ? other$categorie != null : !this$categorie.equals(other$categorie)) return false; Object this$compte = getCompte();Object other$compte = other.getCompte(); if (this$compte == null ? other$compte != null : !this$compte.equals(other$compte)) return false; Object this$contrat = getContrat();Object other$contrat = other.getContrat();return this$contrat == null ? other$contrat == null : this$contrat.equals(other$contrat); } protected boolean canEqual(Object other) { return other instanceof Operation; } public int hashCode() { int PRIME = 59;int result = 1;Object $ref = getRef();result = result * 59 + ($ref == null ? 43 : $ref.hashCode());Object $dateOperation = getDateOperation();result = result * 59 + ($dateOperation == null ? 43 : $dateOperation.hashCode());Object $dateValeur = getDateValeur();result = result * 59 + ($dateValeur == null ? 43 : $dateValeur.hashCode());Object $libelleOperation = getLibelleOperation();result = result * 59 + ($libelleOperation == null ? 43 : $libelleOperation.hashCode());Object $montantDebit = getMontantDebit();result = result * 59 + ($montantDebit == null ? 43 : $montantDebit.hashCode());Object $montantCredit = getMontantCredit();result = result * 59 + ($montantCredit == null ? 43 : $montantCredit.hashCode());Object $montantSolde = getMontantSolde();result = result * 59 + ($montantSolde == null ? 43 : $montantSolde.hashCode());Object $categorie = getCategorie();result = result * 59 + ($categorie == null ? 43 : $categorie.hashCode());Object $compte = getCompte();result = result * 59 + ($compte == null ? 43 : $compte.hashCode());Object $contrat = getContrat();result = result * 59 + ($contrat == null ? 43 : $contrat.hashCode());return result;
  }

  public String getRef() { return this.ref; }
  public Date getDateOperation() { return this.dateOperation; }
  public Date getDateValeur() { return this.dateValeur; }
  public String getLibelleOperation() { return this.libelleOperation; }
  public Double getMontantDebit() { return this.montantDebit; }
  public Double getMontantCredit() { return this.montantCredit; }
  public Double getMontantSolde() { return this.montantSolde; }
  public String getCategorie() { return this.categorie; }
  public String getCompte() { return this.compte; }
  public String getContrat() { return this.contrat; }

  private Double montantCredit;
  private Double montantSolde;
  private String categorie;
  private String compte;
  private String contrat;
  public String toString() {
    return
      org.apache.commons.lang.builder.ToStringBuilder.reflectionToString(this, org.apache.commons.lang.builder.ToStringStyle.SIMPLE_STYLE);
  }
}