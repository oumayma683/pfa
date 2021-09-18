package pfa.ebanking.web.dto;

import java.util.Date;

import pfa.ebanking.entities.User;

public class CompteRegistrationDto {
	private String codeCompte;
	private Date dateCreation;
	private double solde;
	private String type;
	private double decouvert;
	private double taux;
	private User client;
	public CompteRegistrationDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompteRegistrationDto(String codeCompte, Date dateCreation, double solde,String type,double decouvert,double taux,User client) {
		super();
		this.codeCompte = codeCompte;
		this.dateCreation = dateCreation;
		this.solde = solde;
		this.decouvert=decouvert;
		this.type=type;
		this.taux=taux;
		this.client=client;
	}
	
	public User getClient() {
		return client;
	}
	public void setClient(User client) {
		this.client = client;
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
	public String getCodeCompte() {
		return codeCompte;
	}
	public void setCodeCompte(String codeCompte) {
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
