package pfa.ebanking.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import pfa.ebanking.entities.User;
import pfa.ebanking.service.CustomUserDetails;
import pfa.ebanking.service.CustomerServices;
@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	@Autowired
    private CustomerServices userService;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		CustomUserDetails customerDetails = (CustomUserDetails) authentication.getPrincipal();     
		User user = customerDetails.getUser();
 
		if (user.isOTPRequired()) {userService.clearOTP(user);}
		super.onAuthenticationSuccess(request, response, authentication);}
		

	}