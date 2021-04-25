package kranthi.shipment.service;

import java.util.List;
import java.util.Map;

import kranthi.shipment.model.WhUserType;

public interface IWhUserTypeService {
	
	boolean validateEmail(String userEmail);
	
	Integer saveWhUserType(WhUserType whUserType);
	
	List<WhUserType> getAllWhUsers();
	
	Map<Integer, String> getWhUserAndId();

}
