package pfa.ebanking.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pfa.ebanking.entities.Role;
import pfa.ebanking.entities.User;
import pfa.ebanking.repositories.UserRepository;
import pfa.ebanking.service.CustomerServices;
 
@Controller

public class UserController {
	@Autowired
    private UserRepository userRepo;
    @Autowired
    private CustomerServices service;
     
    @GetMapping("/home")
    public String viewHomePage() {
        return "home";
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
         
        return "signup_form";
    }
    
    @PostMapping("/process_register")
    public String processRegister(User user) {
		/*
		 * BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); String
		 * encodedPassword = passwordEncoder.encode(user.getPassword());
		 * user.setPassword(encodedPassword);
		 */
        
        service.saveUserWithDefaultRole(user);
         
        //userRepo.save(user);
         
        return "register_success";
    }
    
    @GetMapping("/users")
    public String listUsers(Model model) {
        //List<User> listUsers = service.listAll();
    	List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
         
        return "users";
    }
    
	/*
	 * @GetMapping("/users/edit/{id}") public String editUser(@PathVariable("id")
	 * Long id, Model model) { User user = service.get(id); List<Role> listRoles =
	 * service.listRoles(); model.addAttribute("user", user);
	 * model.addAttribute("listRoles", listRoles); return "user_form"; }
	 */
    
    @GetMapping("/login")
    public String login() {
    	return "login";}
    
    @GetMapping("/users/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = service.get(id);
        List<Role> listRoles = service.listRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "users_form";
    }
    @RequestMapping("/users/delete/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return "redirect:/users";       
    }
    
    @PostMapping("/users/save")
    public String saveUser(User user) {
        service.save(user);
         
        return "redirect:/users";
    }
    
    @GetMapping("/403")
    	public String error403() {
    	return "403";
    }
    }



