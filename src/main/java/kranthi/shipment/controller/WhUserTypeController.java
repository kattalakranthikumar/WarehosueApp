package kranthi.shipment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kranthi.shipment.model.WhUserType;
import kranthi.shipment.service.IWhUserTypeService;
import kranthi.shipment.utils.Email;



@Controller
@RequestMapping("/wh")
public class WhUserTypeController {
	
	@Autowired
	private IWhUserTypeService service;
	
	@Autowired
	private Email emailutil;
	
	@GetMapping("/register")
	public String showRegister() {
		return "WhUserTypeRegister";
	}

	@GetMapping("/validate")
	public @ResponseBody String validateUserEmail(@RequestParam("email") String userEmail) {
		String message="";
		if(service.validateEmail(userEmail)) {
		message="Email already Exist";}
		return message;
	}
	
	@PostMapping("/save")
	public String saveWhuserType(@ModelAttribute WhUserType whUserType,Model model) {
		Integer id =service.saveWhUserType(whUserType);
		String message = "WhUserType saved with"+ id;

			if(id!=null) {
				
			String text = "code is "+whUserType.getUserCode()+ ",Type is "+whUserType.getUserIdType() ;
			emailutil.sendEmail(whUserType.getUserEmail(), "WhUserTypeSave", text);
		}
		
		model.addAttribute(message);
		return "WhUserTypeRegister";
	}
	@GetMapping("/all")
	public String showAllWhUserTypes(Model model) {
		List<WhUserType> list = service.getAllWhUsers();
		model.addAttribute("List",list);
		
		return "WhUserTypeData";
	}
	
	
	
}







