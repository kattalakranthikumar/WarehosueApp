package kranthi.shipment.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

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

import kranthi.shipment.model.Uom;
import kranthi.shipment.service.IUomService;
import kranthi.shipment.utils.UomUtil;
import kranthi.shipment.view.UomExcelOneVeiw;
import kranthi.shipment.view.UomExcelView;
import kranthi.shipment.view.UompdfVeiw;

@Controller
@RequestMapping("/uom")
public class UomController {
	
	@Autowired
private IUomService service;
	@Autowired
	private UomUtil util;
	
	
	@Autowired
	private ServletContext sc;
	
//1. Show Register page.
	@GetMapping("/register")
	public String showReg() {
		return "UomRegister";
//2. Save the data in DB.
	}
	@PostMapping("/save")
	public String saveUom(@ModelAttribute Uom uom, Model model) {
		 Integer id =service.saveUom(uom);
		model.addAttribute("message" , "saved uom with id-" +id);
		return "UomRegister";
//3. Display all the date in DB on ui.	
	}
	@GetMapping("/all")
	public String showAllUoms(Model model)
	{
		List<Uom> list =  service.getAllUoms();
		model.addAttribute("List", list);
		return "UomData";
//4.Delete data by id.
	}
	@GetMapping("/delete")
	public String deleteUom(@RequestParam("id") Integer id,Model model) {
		service.deleteUom(id);
		List<Uom> s=service.getAllUoms();
		model.addAttribute("List",s);
		model.addAttribute("message", "Uom deleted -"+id);
		return "UomData";
		
	}
//5. Edit data in DB by using edit link.
	@GetMapping("/edit")
	public String editUom(@RequestParam("id") Integer uid,Model model) {
		String page;
		Optional<Uom> opt = service.getUombyid(uid);
		if(opt.isPresent()) {
			model.addAttribute("uom",opt.get());
			page="UomEdit";
		}
		else {
			page="redirect:all";
		}
		return page;
		
	}
	
	//6. Update data.
	@PostMapping("/update")
	public String updateUOm(@ModelAttribute Uom uom,Model model) {
		service.updateUom(uom);
		
		return "redirect:all";
		
		
		
	}
	
	//7. Ajax validation.
	@GetMapping("/validateuomcode")
	public @ResponseBody String validateUomModel(@RequestParam("code") String uomModel)
	{
		String message="";
		if(service.uomValidation(uomModel)) {
			message="Uom type"+uomModel+"already exist";
		}
		return message;
		
	}
	//8. Simple Excel export with Data.
	@GetMapping("/excel")
	public ModelAndView exportUomExcel() {
		ModelAndView m = new ModelAndView();
		List<Uom>list = service.getAllUoms();
		m.addObject("List",list);
		m.setView(new UomExcelView());
		return m;
	}
	//9. Get excel with only one shipment type.
	@GetMapping("/excelone")
	public ModelAndView exportOneUomExcel(@RequestParam("id") Integer id) {
		ModelAndView m = new ModelAndView();
		Optional<Uom> list = service.getUombyid(id);
		m.setView(new UomExcelOneVeiw());
		m.addObject("uom",list.get());
		return m;
	}
	//10. Get pdf with shipment data.
	@GetMapping("/pdfveiw")
	public ModelAndView uompdfVeiw() {
		ModelAndView m = new ModelAndView();
		List<Uom> list = service.getAllUoms();
		m.setView(new UompdfVeiw());
		m.addObject("List",list);
		return m;
	}
	//11. Charts
	
	@GetMapping("/charts")
	public String uomCharts() {
		String path=null;
		List<Object[]> list=service.getCountUomType();
		path=sc.getRealPath("/");
		util.uomPieChart(path, list);
		util.uombarChart(path, list);
		
		return "UomCharts";
		
	}
		
	}

