package kranthi.shipment.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import kranthi.shipment.model.PurchaseDtl;
import kranthi.shipment.model.PurchaseOrder;

public interface IPurchaseOrderService {

	Integer savePurchaseOrder(PurchaseOrder po);
	
	List<PurchaseOrder> getAllPurchaseOrders();
	
	public PurchaseOrder   getOnePurchaseOrder(Integer id);
	
	Integer savePurchasePart(PurchaseDtl pdtl);
	
	List<PurchaseDtl> getPartDetails(Integer poid);
	
	Integer getCountOfPartsInPo(Integer poId);
	
	/*
	 * remove/delete operation.
	 */
	void deleteByID(Integer dtlid);	
	
	Optional<Integer>  getPurchaseDtlByPartIdAndPoId(Integer partId,Integer poId);
	
	void updatePurchaseDtlQtyByDtlId(Integer dtlId,Integer qty);
	
	public void updateStatusByOrderId(String status,Integer orderId);
	
	List<PurchaseDtl> getAllPurchaseDetails();
	
	List<PurchaseDtl> getPurchaseDtlByOrderId(Integer orderId);
	
	public Map<Integer,String> getAllPos();
	
	
}
