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
import org.springframework.web.servlet.ModelAndView;

import com.sun.xml.bind.v2.TODO;

import kranthi.shipment.constants.PurchaseOrderStatus;
import kranthi.shipment.model.PurchaseDtl;
import kranthi.shipment.model.PurchaseOrder;
import kranthi.shipment.service.IPartService;
import kranthi.shipment.service.IPurchaseOrderService;
import kranthi.shipment.service.IWhUserTypeService;
import kranthi.shipment.service.IshipmentTypeService;
import kranthi.shipment.view.PurchaseDtlView;

@Controller
@RequestMapping("/po")
public class PurchaseOrderController {

	@Autowired
	private IPurchaseOrderService service;

	@Autowired
	private IshipmentTypeService stservice;

	@Autowired
	private IWhUserTypeService whUTService;


	@Autowired
	private IPartService partservice;





	@GetMapping("/register")
	public String showRegister(@RequestParam(required=false) Integer id, Model model) {
		PurchaseOrder po = new PurchaseOrder();
		Integer countofparts = service.getCountOfPartsInPo(id);
		if(countofparts==0) {
			po.setPurchaseDefaultStatus("Open");
		}
		else if(countofparts>=1) {
			po.setPurchaseDefaultStatus("Picking");
		}
		model.addAttribute("purchaseOrder", po);

		childUI(model);
		return "PurchaseOrderRegister";
	}

	@PostMapping("/save")
	public String savePurchaseOrder(Model model,@ModelAttribute PurchaseOrder purchaseOrder) {
		childUI(model);
		Integer id = service.savePurchaseOrder(purchaseOrder);
		String message="PurchaseOrder saved with id"+id;
		model.addAttribute("message",message);
		PurchaseOrder po = new PurchaseOrder();
		po.setPurchaseDefaultStatus("OPEN");
		model.addAttribute("purchaseOrder", po);
		return "PurchaseOrderRegister";
	}

	@GetMapping("/all")
	public String showAllPurchaseOrders(Model model) {
		List<PurchaseOrder>list=service.getAllPurchaseOrders();
		model.addAttribute("List",list);
		return "PurchaseOrderData";

	}

	private void childUI(Model model) {
		Map<Integer,String>m=stservice.getIdAndShipmentCode();
		model.addAttribute("sts",m);

		Map<Integer,String> wmap= whUTService.getWhUserAndId();
		model.addAttribute("whuts",wmap);
	}


	//--screen 2--

	@GetMapping("/part")
	public String showPurchasePartPage(@RequestParam("poId") Integer orderId, Model model) {
		PurchaseOrder po = service.getOnePurchaseOrder(orderId);
		childUi(model);
		PurchaseDtl purchaseDtl = new PurchaseDtl();
		purchaseDtl.setPurchaseOrder(po);
		model.addAttribute("purchaseDtl",purchaseDtl);
		model.addAttribute("po",po);
		List<PurchaseDtl> pdetails = service.getPartDetails(po.getId());
		model.addAttribute("pdetails",pdetails);

		return "PurchaseParts";
	}

	@PostMapping("/addpart")
	public String savePoPart(@ModelAttribute PurchaseDtl purchaseDtl ) {
		Integer poId = purchaseDtl.getPurchaseOrder().getId();
		Integer partid = purchaseDtl.getPart().getId();

		/*
	System.out.println("##############################################");
	System.out.println(poId);
	System.out.println(partid);
	System.out.println(purchaseDtl.getQty());
	System.out.println("******************"); 
	//System.out.println(purchaseDtl);    //here we need qty, part id and po_id to get integration.
		 */

		/*
		 * Update qty when we add the same item in 
		 * purchassedtl.
		 */
		
		Optional<Integer> opt =  service.getPurchaseDtlByPartIdAndPoId(partid,poId);
		if(opt.isPresent()) {
			System.out.println(opt+"present");
			service.updatePurchaseDtlQtyByDtlId(opt.get(), purchaseDtl.getQty());
		}else {
			System.out.println("not present");
			Integer id = service.savePurchasePart(purchaseDtl);	
			if(id!=null) {
				service.updateStatusByOrderId(PurchaseOrderStatus.picking.getValue(), poId);
			}
		}
		return "redirect:part?poId="+poId;
	} 
	
	
	/*****************
	 * (+) operation.*
	 ****************/
	@GetMapping("/add")
	public String increaseQtyByOne(@RequestParam("dtlId") Integer dtlId,
			@RequestParam("poId") Integer poId) {
		service.updatePurchaseDtlQtyByDtlId(dtlId,1);
		return "redirect:part?poId="+poId;
	}
	
	
	/*****************
	 * (-) operation.*
	 ****************/
	@GetMapping("/sub")
	public String decreseQtyByOne(@RequestParam("dtlId") Integer dtlId,
			@RequestParam("poId") Integer poId) {
		service.updatePurchaseDtlQtyByDtlId(dtlId,-1);
		return "redirect:part?poId="+poId;
	}
	
	

	/*******************
	 * Remove operation.*
	 *******************/
	@GetMapping("/deletepdtl")
	public String deleteBYId(@RequestParam("poId") Integer orderid,
			@RequestParam("dtlId") Integer dtlId
			) {
		if(service.getCountOfPartsInPo(orderid)<2) {
			service.updateStatusByOrderId(PurchaseOrderStatus.OPEN.getValue(), orderid);
		}
		service.deleteByID(dtlId);
		return "redirect:part?poId="+orderid;
	}
@GetMapping("/placeorder")
	public String placeorder(@RequestParam("poId") Integer poId) {
		
		service.updateStatusByOrderId(PurchaseOrderStatus.ordered.getValue(), poId);
		
		return "redirect:part?poId="+poId;
	}
/*
 * To Generate Invoice
 */
@GetMapping("/geninv")
public ModelAndView genInvoice(@RequestParam("poId") Integer orderId) {
	ModelAndView m = new ModelAndView();
	List<PurchaseDtl> list =service.getPurchaseDtlByOrderId(orderId);
	service.updateStatusByOrderId(PurchaseOrderStatus.invoiced.getValue(), orderId);
	m.setView(new PurchaseDtlView());
	m.addObject("list",list);
	return m;
	
	
}
/*
 * To Print Invoice
 */
//TODO
public ModelAndView printInvoice(@RequestParam("poId") Integer orderId) {
	ModelAndView m = new ModelAndView();
	
	return null;
}
	//	create Child UI

	private void childUi(Model model) {
		Map<Integer,String> pmap = partservice.getPartIdAndCode();
		model.addAttribute("parts",pmap);
	}

}


