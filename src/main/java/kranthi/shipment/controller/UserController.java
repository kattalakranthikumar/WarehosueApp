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

import kranthi.shipment.model.User;
import kranthi.shipment.service.IUserService;
import kranthi.shipment.utils.Email;
import kranthi.shipment.utils.UserUtil;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService service;
	
	@Autowired
	private UserUtil util;
	
	@Autowired
	private Email email;
	
	
	
	@GetMapping("/register")
	public String showRegister() {
		
		return "UserRegister";
	}
	
	@PostMapping("/save")
	public String saveUser(Model model,@ModelAttribute User user) {
		
		String pwd=util.generatePassword();
		String otp=util.getOtp();
		
		user.setPassword(pwd);
		user.setOtp(otp);
		user.setActive(false);
		Integer id = service.saveUser(user);
		String text = new StringBuffer()
				.append("Hello")
				.append(user.getUname())
				.append(" your otp: ")
				.append(otp)
				.append(" your password: ")
				.append(pwd)
				.append(" Roles: ")
				.append(user.getRoles())
				.append(" Status ")
				.append(user.isActive()?"ACTIVE":"IN-ACTIVE")  //here we are setting status as In-Active
				.toString();
		if(id != null) {
			email.sendEmail(user.getEmail(),"Welcome",text);
		}
		return "UserRegister";
	}
	
	@GetMapping("/all")
	public String showAllUsers(Model model) {
		List<User> list = service.findAllUsers();
		model.addAttribute("List",list);
		return "UserData";
	}
	
	@GetMapping("/activate")
	public String activeteUser(@RequestParam Integer id){
		service.updateStatusById(id, true);
		return "redirect:all";
	}
	
	@GetMapping("/inactivate")
public String inActiveteUser(@RequestParam Integer id){
		service.updateStatusById(id, false);
	return "redirect:all";
	}
	
	@GetMapping("/userlogin")
	public String loginpage() {
		return "CustUserLogin";
		
	}
	
}














