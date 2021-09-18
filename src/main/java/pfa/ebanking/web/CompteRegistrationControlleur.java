package pfa.ebanking.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pfa.ebanking.service.CompteService;
import pfa.ebanking.web.dto.CompteRegistrationDto;

@Controller
@RequestMapping("/compte")
public class CompteRegistrationControlleur {
	
	private CompteService compteService;

	public CompteRegistrationControlleur(CompteService compteService) {
		super();
		this.compteService = compteService;
	}
	
	@ModelAttribute("compte")
    public CompteRegistrationDto compteRegistrationDto() {
        return new CompteRegistrationDto();
    }
	
	@GetMapping
	public String showCptRegistrationForm() {
		return "compte";
	}
	
	@PostMapping
	public String registerAccount(@ModelAttribute("compte") CompteRegistrationDto registrationDto) {
		compteService.save(registrationDto);
		return "redirect:/tamplate1";
	}
}
