package pfa.ebanking.service;


import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import pfa.ebanking.entities.Compte;
import pfa.ebanking.repositories.CompteRepository;
import pfa.ebanking.web.dto.CompteRegistrationDto;

@Service
public class CompteServiceImpl implements CompteService{
	
private CompteRepository compteRepository;

int cpt = ThreadLocalRandom.current().nextInt(100000000, 1000000000 + 1);
Long codeCompte=Long.valueOf(cpt);
	
	public CompteServiceImpl(CompteRepository compteRepository) {
		super();
		this.compteRepository = compteRepository;
	}

	
	@Override
	public Compte save(CompteRegistrationDto registrationDto) {
		Compte compte = new Compte(codeCompte,new Date(),registrationDto.getSolde(),registrationDto.getType(),registrationDto.getDecouvert(),registrationDto.getTaux(),registrationDto.getClient());
		return compteRepository.save(compte);
	}

}
