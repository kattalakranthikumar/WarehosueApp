package kranthi.shipment.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kranthi.shipment.model.Part;
import kranthi.shipment.service.IOrderMethodService;
import kranthi.shipment.service.IPartService;
import kranthi.shipment.service.IUomService;

@Controller
@RequestMapping("/part")
public class PartController {

	@Autowired
	private IPartService service;

	@Autowired
	private IUomService uomservice;

	@Autowired
	private IOrderMethodService omservice;

	// 1. show register page.

	@GetMapping("/register")
	public String showRegister(Model model) {
		commonUI( model);
		return "PartRegister";
	}

	@PostMapping("/save")
	public String savePart(@ModelAttribute Part part, Model model) {
		commonUI(model);
		service.savePart(part);
		return "PartRegister";
	}

	@GetMapping("/all")
	public String getAllParts(Model model) {
		List<Part> list = service.getAllParts();
		model.addAttribute("List", list);
		return "PartData";

	}
	
	@GetMapping("/edit")
	public String editPart(@RequestParam("id") Integer id, Model model) {
		String page=null;
		Optional<Part>opt = service.getOnePartById(id);
		if(opt.isPresent()) {
			page="PartEdit";
		model.addAttribute("part",opt.get());
		commonUI(model);
		
		}
		else {
			page="redirect:all";
		}
		
		return page;
	}
	
	@PostMapping("/update")
	public String updatePart(Model model,@ModelAttribute Part part) {
		service.savePart(part);
		commonUI(model);
		return "redirect:all";
	}
	
	@GetMapping("/delete")
	public String deletePartById(@RequestParam("id") Integer id) {
		service.deleteOnePartById(id);
		return "redirect:all";
	}

	private void commonUI(Model model) {

		
		Map<Integer, String> umap = uomservice.getIdandUomModel();
		model.addAttribute("uoms", umap);

		Map<Integer, String> omapsale = omservice.getIdAndorderCodeforSale();
		model.addAttribute("omssale", omapsale);
		
		Map<Integer, String> omappurchase = omservice.getIdAndorderCodeforPurchase();
		model.addAttribute("omspurchase",omappurchase);
		
		

	}

}
