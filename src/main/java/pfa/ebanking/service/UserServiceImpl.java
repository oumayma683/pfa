/*
 * package pfa.ebanking.service;
 * 
 * import java.util.Arrays;
 * 
 * import org.springframework.stereotype.Service;
 * 
 * import pfa.ebanking.entities.Role; import pfa.ebanking.entities.User; import
 * pfa.ebanking.repositories.UserRepository; import
 * pfa.ebanking.web.dto.UserRegistrationDto;
 * 
 * @Service public class UserServiceImpl implements UserService{
 * 
 * private UserRepository userRepository;
 * 
 * public UserServiceImpl(UserRepository userRepository) { super();
 * this.userRepository = userRepository; }
 * 
 * @Override public User save(UserRegistrationDto registrationDto) { User user =
 * new User(registrationDto.getFirstName(), registrationDto.getLastName(),
 * registrationDto.getEmail(), registrationDto.getPassword(),
 * registrationDto.getAdress(), registrationDto.getPhone(),
 * registrationDto.getZip(), registrationDto.getCin(),
 * registrationDto.getCity(), registrationDto.getUsername(), Arrays.asList(new
 * Role("ROLE_USER")));
 * 
 * return userRepository.save(user); }
 * 
 * }
 */