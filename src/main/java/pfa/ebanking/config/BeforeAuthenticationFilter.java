package pfa.ebanking.config;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import pfa.ebanking.entities.User;
import pfa.ebanking.service.CustomerServices;

@Component
public class BeforeAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	@Autowired
	private CustomerServices customerService;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		String email = request.getParameter("email");
		User user = customerService.getCustomerByEmail(email);
	     
	    if (user != null) {
	        if (user.isOTPRequired()) {
	            return super.attemptAuthentication(request, response);
	        }
	         
	        System.out.println("attemptAuthentication - email: " + email);
	        float spamScore = getGoogleRecaptchaScore();
	 
	        if (spamScore < 0.5) {
	            try {
	                customerService.generateOneTimePassword(user);
	                throw new InsufficientAuthenticationException("OTP");
	            } catch (MessagingException | UnsupportedEncodingException ex) {
	                throw new AuthenticationServiceException(
	                            "Error while sending OTP email.");
	            }
	             
	        }
	    }
	     
	    return super.attemptAuthentication(request, response);
	}
	private float getGoogleRecaptchaScore() {
		return 0.43f;
	}
	@Autowired
	@Override
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		// TODO Auto-generated method stub
		super.setAuthenticationManager(authenticationManager);
	}
	@Autowired
	@Override
	public void setAuthenticationSuccessHandler(AuthenticationSuccessHandler successHandler) {
		// TODO Auto-generated method stub
		super.setAuthenticationSuccessHandler(successHandler);
	}
	@Autowired
	@Override
	public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
		// TODO Auto-generated method stub
		super.setAuthenticationFailureHandler(failureHandler);
	}
	
	 public BeforeAuthenticationFilter() {
	        setUsernameParameter("email");
	        super.setRequiresAuthenticationRequestMatcher(
	                new AntPathRequestMatcher("/login", "POST"));
	    }
	

}
