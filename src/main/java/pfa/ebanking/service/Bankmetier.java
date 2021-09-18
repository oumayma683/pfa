package pfa.ebanking.service;

import org.springframework.data.domain.Page;

import pfa.ebanking.entities.Compte;
import pfa.ebanking.entities.Operation;

public interface Bankmetier {

	public Compte consulter(Long codeCompte);
	  public void verser(Long codeCompte,double montant);
	  public void retirer(Long codeCompte,double montant);
	  public void virement(Long codeCompte1,Long codeCompte2,double montant);
	  public Page<Operation> listOperation(Long codeCompte,int page,int size);
}
