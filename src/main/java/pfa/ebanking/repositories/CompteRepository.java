package pfa.ebanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pfa.ebanking.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long> {
	 
}
