package pfa.ebanking.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;
import pfa.ebanking.entities.Role;
import pfa.ebanking.entities.User;
import pfa.ebanking.repositories.RoleRepository;
import pfa.ebanking.repositories.UserRepository;

@Service
@Transactional
public class CustomerServices {
	
	@Autowired 
	UserRepository userRepo;
	
	@Autowired RoleRepository roleRepo;
	
	@Autowired 
	JavaMailSender mailSender;
    
    @Autowired 
    PasswordEncoder passwordEncoder;
    
    
	  public void saveUserWithDefaultRole(User user) { 
		  BCryptPasswordEncoder
	  passwordEncoder = new BCryptPasswordEncoder(); String encodedPassword =
	  passwordEncoder.encode(user.getPassword());
	  user.setPassword(encodedPassword);
	  
	  Role roleUser = roleRepo.findByName("user");
	  user.addRole(roleUser);
	  userRepo.save(user);}
	  
	  public List<User> listAll() { return userRepo.findAll(); }
	  
	  public User get(Long id) { return userRepo.findById(id).get(); }
	  
	  public List<Role> listRoles() { return roleRepo.findAll(); }
	  
    

    public void generateOneTimePassword(User user)throws UnsupportedEncodingException, MessagingException {
    	
    	String OTP = RandomString.make(8);
        String encodedOTP = passwordEncoder.encode(OTP);
         
        user.setOneTimePassword(encodedOTP);
        user.setOtpRequestedTime(new Date());
         
        userRepo.save(user);
         
        sendOTPEmail(user, OTP);
     
    }
     
    public void sendOTPEmail(User user, String OTP) throws UnsupportedEncodingException, MessagingException {
    	 MimeMessage message = mailSender.createMimeMessage();              
    	    MimeMessageHelper helper = new MimeMessageHelper(message);
    	     
    	    helper.setFrom("pfaebanking@gmail.com", "ebanking Support");
    	    helper.setTo(user.getEmail());
    	     
    	    String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";
    	     
    	    String content = "<p>Hello " + user.getFirstName() + "</p>"
    	            + "<p>For security reason, you're required to use the following "
    	            + "One Time Password to login:</p>"
    	            + "<p><b>" + OTP + "</b></p>"
    	            + "<br>"
    	            + "<p>Note: this OTP is set to expire in 5 minutes.</p>";
    	     
    	    helper.setSubject(subject);
    	     
    	    helper.setText(content, true);
    	     
    	    mailSender.send(message);
    }
 
    public void clearOTP(User user) {
    	user.setOneTimePassword(null);
        user.setOtpRequestedTime(null);
        userRepo.save(user);   
    }

	public User getCustomerByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepo.findByEmail(email);
	}

	public void save(User user) {
		 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); String encodedPassword =
		  passwordEncoder.encode(user.getPassword());
		  user.setPassword(encodedPassword);
		userRepo.save(user);
		
	}  
	
	public void delete(Long id) {
		  userRepo.deleteById(id);
	    }


}
