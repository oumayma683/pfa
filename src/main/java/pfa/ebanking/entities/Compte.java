package pfa.ebanking.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class Compte implements Serializable {
	@Id
	
	private Long codeCompte;
	private Date dateCreation;
	private double solde;
	private String type;
	private double decouvert;
	private double taux;
	private User client;
	public Compte(Long codeCompte, Date dateCreation, double solde,String type,double decouvert,double taux,User client) {
		super();
		this.codeCompte = codeCompte;
		this.dateCreation = dateCreation;
		this.solde = solde;
		this.decouvert=decouvert;
		this.type=type;
		this.taux=taux;
		this.client=client;
	}
	
	
	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getDecouvert() {
		return decouvert;
	}
	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
	public double getTaux() {
		return taux;
	}
	public void setTaux(double taux) {
		this.taux = taux;
	}
	public Long getCodeCompte() {
		return codeCompte;
	}
	public void setCodeCompte(Long codeCompte) {
		this.codeCompte = codeCompte;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	
	public String toString(){ 
		  return this.codeCompte+" "+this.dateCreation+" "+this.solde;  
		 }  


}
