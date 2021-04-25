package kranthi.shipment.serviceimpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kranthi.shipment.model.Part;
import kranthi.shipment.repo.PartRepository;
import kranthi.shipment.service.IPartService;
import kranthi.shipment.utils.AppUtil;


@Service
public class PartServiceImpl implements IPartService{
	@Autowired
	private PartRepository repo;

	@Override
public void savePart(Part part) {
	repo.save(part);
	
}
	@Override
	public List<Part> getAllParts() {
		List<Part> list = repo.findAll();
		return list;
	}
	
	@Override
	public Optional<Part> getOnePartById(Integer id) {
		Optional<Part> opt = repo.findById(id);
		return opt;
	}
	
	@Override
	public void deleteOnePartById(Integer id) {
		repo.deleteById(id);
		
	}
	
	@Override
	public Map<Integer, String> getPartIdAndCode() {
		List<Object[]> list = repo.getPartIdAndCode();
		return AppUtil.convertToMap(list);
	}
	
	}
