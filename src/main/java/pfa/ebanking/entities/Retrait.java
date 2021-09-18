package pfa.ebanking.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import pfa.ebanking.entities.Operation;
@Entity
@DiscriminatorValue(value = "R")
public class Retrait extends Operation{

	public Retrait() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Retrait(Date dateOperation, double montant, Compte compte) {
		super(dateOperation, montant, compte);
		// TODO Auto-generated constructor stub
	}

	
	

}
