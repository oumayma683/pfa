package pfa.ebanking.service;

import pfa.ebanking.entities.Compte;
import pfa.ebanking.entities.User;
import pfa.ebanking.web.dto.CompteRegistrationDto;
import pfa.ebanking.web.dto.UserRegistrationDto;


public interface CompteService {
	Compte save(CompteRegistrationDto registrationDto);

}
