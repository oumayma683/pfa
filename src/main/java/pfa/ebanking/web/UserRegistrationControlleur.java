/*
 * package pfa.ebanking.web;
 * 
 * import org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestMapping;
 * 
 * import pfa.ebanking.service.UserService; import
 * pfa.ebanking.web.dto.UserRegistrationDto;
 * 
 * @Controller
 * 
 * @RequestMapping("/registration") public class UserRegistrationControlleur {
 * 
 * 
 * private UserService userService;
 * 
 * public UserRegistrationControlleur(UserService userService) { super();
 * this.userService = userService; }
 * 
 * @ModelAttribute("user") public UserRegistrationDto userRegistrationDto() {
 * return new UserRegistrationDto(); }
 * 
 * @GetMapping public String showRegistrationForm() { return "registration"; }
 * 
 * @PostMapping public String registerUserAccount(@ModelAttribute("user")
 * UserRegistrationDto registrationDto) { userService.save(registrationDto);
 * return "redirect:/registration?success"; }
 * 
 * }
 */