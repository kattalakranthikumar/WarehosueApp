package kranthi.shipment.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import kranthi.shipment.model.ShipmentType;

public interface IshipmentTypeService
{
	Integer saveShipmentType(ShipmentType st);
	
	List<ShipmentType> getAllShipmentTypes();
	
	void deleteShipmentType(Integer id);
	
	 Optional<ShipmentType> getShipmentTypeById(Integer id);
	 
	Integer updateShipmentTypes(ShipmentType st);
	
	boolean getCountOfShipmentCode(String shipmentCode);
	
	public List<Object[]> getShipmentModeCount();
	
	Page<ShipmentType> getShipmentTypesByPage(Pageable pageable);
	
	Map<Integer, String> getIdAndShipmentCode();
	
}
