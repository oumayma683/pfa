package pfa.ebanking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pfa.ebanking.entities.Role;
import pfa.ebanking.entities.User;

public interface RoleRepository extends JpaRepository <Role, Long> {
	
	@Query("SELECT r FROM Role r WHERE r.name = ?1")
	Role findByName(String name);

}
