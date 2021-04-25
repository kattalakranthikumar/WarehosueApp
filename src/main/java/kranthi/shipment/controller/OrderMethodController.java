package kranthi.shipment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kranthi.shipment.model.OrderMethod;
import kranthi.shipment.service.IOrderMethodService;
import kranthi.shipment.view.OrderMethodExcelView;

@Controller
@RequestMapping("/om")
public class OrderMethodController {
	
	@Autowired
	private IOrderMethodService service;
	
	//1.Show the Register page
	
	@GetMapping("/register")
	public String showReg () {
		return "OrderMethodRegister";
		
	}
	//2. Click on submit /read the date and save.
	
	@PostMapping("/save")
	public String saveOm(@ModelAttribute OrderMethod orderMethod,Model model) {
		Integer id=service.saveOm(orderMethod);
		String message = "Om save with id "+id;
		model.addAttribute("msg",message);
		return "OrderMethodRegister";
	}
	//3. Display all rows.
	
	@GetMapping("/all")
	public String displayOms(Model model) {
		List<OrderMethod> o = service.getAllOms();
		model.addAttribute("List", o);
		return "OrderMethodData";
		
	}
	//4. Delete Id

	@GetMapping("/delete")
	public String deleteOm(@RequestParam("id") Integer id ) {
		service.deleteOm(id);
		return "redirect:all";
		
	}
	//5. Show Edit page.
	
	@GetMapping("/edit")
	public String getOmById(@RequestParam("id") Integer id) {
		String page;
		Optional<OrderMethod>opt=service.getOmById(id);
		if(opt.isPresent()) {
			page="OrderMethodEdit";
		}
		else {
			page = "redirect:all";
		}
		return page;
		
	}
	//6. Update data.
	
	@PostMapping("/update")
	public String updateOm(@ModelAttribute OrderMethod orderMethod) {
		service.saveOm(orderMethod);
		
		return "redirect:all";
		
	}

	//7. Ajax validation.
	@GetMapping("/validateom")
	public @ResponseBody String validateOmCode(@RequestParam("code") String orderCode){
		String message=null;
		if(service.validateOm(orderCode)) {
			message = "Order code already exist";
		}
		
		return message;
		
	}
	
	//8. Simple Excel export with Data.
	@GetMapping("/excel")
	public ModelAndView exportExcelView() {
		ModelAndView m = new ModelAndView();
		List<OrderMethod> list = service.getAllOms();
		m.addObject("List" ,list);
		m.setView(new OrderMethodExcelView());
		return m;
	}

	//9. Get excel with only one shipment type.
	
	//10. Get pdf with shipment data.
	
	//11. Charts
	
	
	
	
	
	
	
	
	
	
}
