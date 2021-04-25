package kranthi.shipment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kranthi.shipment.constants.PurchaseOrderStatus;
import kranthi.shipment.model.GRN;
import kranthi.shipment.service.IGRNService;
import kranthi.shipment.service.IPurchaseOrderService;

@Controller
@RequestMapping("/grn")
public class GRNController {

	@Autowired
	private IGRNService service;
	
	@Autowired
	private IPurchaseOrderService poservice;
	
	
	
	//show GRN Register page	
	@GetMapping("/register")
	public String showRegister(Model model) {
		childUi( model);
		return "GRN";
	}
	
	@PostMapping("/save")
	public String saveGrn(@ModelAttribute GRN gRN,Model model) {
		Integer id = service.saveGRN(gRN);
		Integer orderId = gRN.getPurchaseOrder().getId();
		if(id!=null) {
		poservice.updateStatusByOrderId(PurchaseOrderStatus.received.getValue(), orderId);
		}
		childUi(model);
		return "GRN";
	}
	
	@GetMapping("/all")
	public String showAllgrns(Model model) {
		List<GRN> list = service.getAll();
		model.addAttribute("List",list);
		return "GRNData";
		
	}
	private void childUi(Model model) {
		Map<Integer,String> po =poservice.getAllPos();
		model.addAttribute("po",po);
		
	}
	

}
