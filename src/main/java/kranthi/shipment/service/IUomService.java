package kranthi.shipment.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import kranthi.shipment.model.Uom;

public interface IUomService 
{
	Integer saveUom(Uom uom);
	
	List<Uom> getAllUoms();
	
	void deleteUom(Integer id);
	
	Optional<Uom> getUombyid(Integer id);
	Integer updateUom(Uom uom);
	
	boolean uomValidation(String uomModel);
	
	List<Object[]> getCountUomType();
	
	Map<Integer,String> getIdandUomModel();
	
	
}
