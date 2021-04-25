package kranthi.shipment.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import kranthi.shipment.model.OrderMethod;
	
public interface IOrderMethodService {
	
	Integer saveOm(OrderMethod om);
	
	List<OrderMethod> getAllOms();
	
	void deleteOm(Integer id);
	
	Optional<OrderMethod> getOmById(Integer id);
	
	public boolean validateOm(String orderCode);
	
	Map<Integer,String> getIdAndorderCodeforSale();
	
	Map<Integer,String> getIdAndorderCodeforPurchase();

}
