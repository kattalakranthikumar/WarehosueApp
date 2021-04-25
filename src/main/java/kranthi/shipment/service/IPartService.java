package kranthi.shipment.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import kranthi.shipment.model.Part;

public interface IPartService {

	void savePart(Part part);
	List<Part> getAllParts();
	
	Optional<Part> getOnePartById(Integer id);
	
	void deleteOnePartById(Integer id);
	
	Map<Integer,String> getPartIdAndCode();
	
	
	
	
	
	
}
