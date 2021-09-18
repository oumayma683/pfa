package pfa.ebanking.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pfa.ebanking.entities.Retrait;
import pfa.ebanking.entities.Versement;
import pfa.ebanking.repositories.CompteRepository;
import pfa.ebanking.repositories.OperationRepository;
import pfa.ebanking.entities.Compte;
import pfa.ebanking.entities.Operation;
import pfa.ebanking.service.Bankmetier;

@Service //utiliser pour la couche metier
@Transactional //pour les transactions soit tous ce passe bien soit on annule tous
public class BankmetierImp implements Bankmetier {
	@Autowired
	private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;
	
	@Override
	public Compte consulter(Long codeCompte) {
		Compte cpt=compteRepository.findById(codeCompte).orElse(null);
		return cpt;
	}

	@Override
	public void verser(Long codeCompte, double montant) {
		Compte cpt=consulter(codeCompte);
		Versement ver=new Versement(new Date(),montant,cpt);
		operationRepository.save(ver);
		cpt.setSolde(cpt.getSolde()+montant);
		compteRepository.save(cpt);
		
	}

	@Override
	public void retirer(Long codeCompte, double montant) {
		Compte cpt=consulter(codeCompte);
		double facilitiesCaisse=0;
		if(cpt.getSolde()+facilitiesCaisse<montant)
			{throw new RuntimeException("Balance not enough");}
		Retrait re=new Retrait(new Date(),montant,cpt);
		operationRepository.save(re);
		cpt.setSolde(cpt.getSolde()-montant);
		compteRepository.save(cpt);
		
	}

	@Override
	public void virement(Long codeCompte1, Long codeCompte2, double montant) {
		if(codeCompte1.equals(codeCompte2))
		{throw new RuntimeException("Transfer to same account is impossible");}
		retirer(codeCompte1,montant);
		verser(codeCompte2,montant);
		
	}


	@Override
	public Page<Operation> listOperation(Long codeCompte, int page, int size) {
		return operationRepository.listOperation(codeCompte, PageRequest.of(page, size));
				
	}

}
