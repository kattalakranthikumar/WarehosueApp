package kranthi.shipment.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kranthi.shipment.model.WhUserType;
import kranthi.shipment.repo.WhUserTypeRepository;
import kranthi.shipment.service.IWhUserTypeService;
import kranthi.shipment.utils.AppUtil;

@Service
public class WhUserTypeServiceImpl implements IWhUserTypeService {
	
	@Autowired
	private WhUserTypeRepository repo;
	
	@Override
	public boolean validateEmail(String userEmail) {
		
		return repo.getCountofEmail(userEmail)>0;
	}
	
	@Override
	public Integer saveWhUserType(WhUserType whUserType) {
		Integer id = repo.save(whUserType).getId(); //here directly we are getting id instead of (whUserType wh = repo.save(whUserType) 
																								// Integer id =wh.getId();
		return id;
	}
	
	@Override
	public List<WhUserType> getAllWhUsers() {
		List<WhUserType>list = repo.findAll();
		return list;
	}
	
	@Override
	public Map<Integer, String> getWhUserAndId() {
		List<Object[]> list = repo.getWhUserIdAndCode();
		return AppUtil.convertToMap(list);
	}

}
