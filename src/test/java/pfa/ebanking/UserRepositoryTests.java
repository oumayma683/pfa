package pfa.ebanking;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import pfa.ebanking.entities.Role;
import pfa.ebanking.entities.User;
import pfa.ebanking.repositories.RoleRepository;
import pfa.ebanking.repositories.UserRepository;

@DataJpaTest

@AutoConfigureTestDatabase(replace = Replace.NONE)

@Rollback(false) 
public class UserRepositoryTests {

@Autowired private TestEntityManager entityManager;

@Autowired private UserRepository userRepo;
@Autowired private RoleRepository roleRepo;

 @Test
public void testAddRoleToNewUser() {
    Role roleAdmin = roleRepo.findByName("admin");
     
    User user = new User();
    user.setEmail("ss@gmail.com");
    user.setPassword("ss2020");
    user.setFirstName("safae");
    user.setLastName("Gates");
    user.setAdress("12345");
    user.setCin("rt678");
    user.setCity("tazaa");
    user.setPhone("069567970");
    user.setZip("hrllo");
    user.setUsername("sss");
    user.addRole(roleAdmin);       
     
    User savedUser = userRepo.save(user);
     
    assertThat(savedUser.getRoles().size()).isEqualTo(1);
}
 
 @Test
 public void testAddRoleToExistingUser() {
     User user = userRepo.findById(1L).get();
     Role roleUser = roleRepo.findByName("User");

      
     user.addRole(roleUser);
      
     User savedUser = userRepo.save(user);
      
     assertThat(savedUser.getRoles().size()).isEqualTo(1);      
 }

}

