package kranthi.shipment.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kranthi.shipment.model.ShipmentType;
import kranthi.shipment.service.IshipmentTypeService;
import kranthi.shipment.utils.ShipmentTypeUtil;
import kranthi.shipment.view.ShipmentTypeExcelOneView;
import kranthi.shipment.view.ShipmentTypeExcelView;
import kranthi.shipment.view.ShipmentTypepdfView;


@Controller
@RequestMapping("/st")
public class ShipmentTypeController 
{
	@Autowired	
	private IshipmentTypeService service;
	@Autowired	
	private ShipmentTypeUtil util;
	@Autowired
	private ServletContext sc;
	
	//1.Show the Register page
	
	@GetMapping("/register")
	public String showReg() 
	{
		return "ShipmentTypeRegister";
		
	}
	//2. Click on submit /read the date and save.
	
	@PostMapping("/save")
	public String saveshipmentType(@ModelAttribute ShipmentType shipmentType,Model model) 
	{
		Integer id= service.saveShipmentType(shipmentType);
		
		String msg = "Shipment Type "+id+" saved";
		model.addAttribute("message",msg );
		return "ShipmentTypeRegister";
		
	}
	//3. Display all rows.
	
	@GetMapping("/all")
	public String showAllShipmentTypes(@PageableDefault(page=0,size=3)Pageable pageable, Model model) {
		List
		<ShipmentType> s=service.getAllShipmentTypes();
		//send date
		model.addAttribute("List",s);
		
		return "ShipmentTypeData";
		
	}
	//4. Delete Id
	
	@GetMapping("/delete")
	public String deleteShipmentType(@RequestParam("id") Integer id,Model model) {
		service.deleteShipmentType(id);
		model.addAttribute("List", service.getAllShipmentTypes());
		model.addAttribute("message", "shipment "+id+ " deleted");
		return "ShipmentTypeData";
		
	}
	//5. Show Edit page.
	
	@GetMapping("/edit")
	public String editShipmentType(@RequestParam("id") Integer id,Model model) {
		String page;
		 Optional<ShipmentType> opt=service.getShipmentTypeById(id);
		 if(opt.isPresent()) {
			 page="ShipmentTypeEdit";
			 model.addAttribute("shipmentType",opt.get());
		 }else
		 {
			 page="redirect:all";
		 }
			
		
		return page;
		
	}
	//6. Update data.
	
	@PostMapping("/update")
	public String updateShipmentType(@ModelAttribute ShipmentType shipmentType,Model model) 
	{
		Integer id= service.updateShipmentTypes(shipmentType);
		
		String msg = "Shipment Type "+id+" saved";
		model.addAttribute("message",msg );
		return "redirect:all";
	}
	//7. Ajax validation.
	
	@GetMapping("/validateCode")
	public @ResponseBody String validateShipmentCode(@RequestParam("code") String shipmentCode) {
		String message="";
		if(service.getCountOfShipmentCode(shipmentCode)) {
			message = "ShipmentType -->"+shipmentCode+" already exist";
		}
		return message;
		
		
	}
	//8. Simple Excel export with Data.
	@GetMapping("/excel")
	public ModelAndView showExcel() {
		ModelAndView m = new ModelAndView();
		m.setView(new ShipmentTypeExcelView());
		List<ShipmentType> list =service.getAllShipmentTypes();
		m.addObject("List", list);
		return m;
		
	}
	
	//9. Get excel with only one shipment type.
	@GetMapping("/excelexport")
	public ModelAndView oneShipmentTypeExcel(@RequestParam("id") Integer id) {
		ModelAndView m = new ModelAndView();
		Optional<ShipmentType> opt= service.getShipmentTypeById(id);
		m.addObject("st", opt.get());
		m.setView(new ShipmentTypeExcelOneView());
		return m;
		
	}
	
	//10. Get pdf with shipment data.
	@GetMapping("/pdfexport")
	public ModelAndView pdfShipmentType() {
		List <ShipmentType> list = service.getAllShipmentTypes();
		ModelAndView m =new ModelAndView();
		m.setView(new ShipmentTypepdfView());
		m.addObject("list", list);
		return m;
		
	}
	//11. Charts
	@GetMapping("/charts")
	public String getShipmentCharts() {
		
		List<Object[]> list =service.getShipmentModeCount();
		String path = sc.getRealPath("/");
		util.generateshipmentchart(path, list);
		util.generatebarchart(path, list);
		return "ShipmentCharts.html";
	}
	
	
	
}
