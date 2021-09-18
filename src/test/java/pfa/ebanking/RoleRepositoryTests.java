package pfa.ebanking;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import pfa.ebanking.entities.Role;
import pfa.ebanking.repositories.RoleRepository;
import pfa.ebanking.repositories.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {
 
    @Autowired
    private TestEntityManager entityManager;
      
    @Autowired
    private RoleRepository roleRepo;
     
    // test methods go here...
    
    @Test
    public void testCreateRoles() {
        Role user = new Role("user");
        Role admin = new Role("admin");
         
      roleRepo.saveAll(List.of(user,admin));
      List<Role> listRoles = roleRepo.findAll();
       assertThat(listRoles.size()).isEqualTo(2);
       

}} 