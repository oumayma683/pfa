package pfa.ebanking.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pfa.ebanking.entities.Compte;
import pfa.ebanking.entities.Operation;
import pfa.ebanking.service.Bankmetier;

@Controller
public class BankController {
	@Autowired
	  private Bankmetier bankmetier;
		/*
		 * @RequestMapping("/home") public String home() { return"home.html"; }
		 */

		/*@GetMapping("/login")
		public String login() {
			return "login";
		}*/
		@RequestMapping("/")
		public String index() {
			return"tamplate1.html";
		}
		@RequestMapping("/tamplate1")
		public String tamplate() {
			return"login.html";
		}
		
		@RequestMapping("/consulterCompte")
		public String consulter(Model model,Long codeCompte,
				@RequestParam(name="page",defaultValue="0")int page,
				@RequestParam(name="size",defaultValue="6")int size) 
				{
			model.addAttribute("codeCompte", codeCompte);
			try {
				Compte cpt=bankmetier.consulter(codeCompte);
				Page<Operation> pageOperations=bankmetier.listOperation(codeCompte, page, size);
				model.addAttribute("listOperations", pageOperations.getContent());
				int[] pages=new int[pageOperations.getTotalPages()];
				model.addAttribute("pages",pages);
				model.addAttribute("compte", cpt);
				
			}catch (Exception e) {
				model.addAttribute("exception", e);
			}
			
			
			return"tamplate1.html";
		}
		@RequestMapping(value="/saveOperation",method=RequestMethod.POST)
		public String saveOperation(Model model,String typeOperation,Long codeCompte,double montant,Long codeCompte2) {
			
			try {
			if(typeOperation.equals("VERS")) {
				bankmetier.verser(codeCompte, montant); 
			}
			else if(typeOperation.equals("RET")) {
				bankmetier.retirer(codeCompte, montant);
			}
			if(typeOperation.equals("VIR")) {
				bankmetier.virement(codeCompte,codeCompte2, montant);
			}
			} catch (Exception e) {
				model.addAttribute("ERROR", e);
				return "redirect:/consulterCompte?codeCompte="+codeCompte+"&ERROR="+e.getMessage();
			}
			return "redirect:/consulterCompte?codeCompte="+codeCompte;
		}
}
