package kranthi.shipment.serviceimpl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kranthi.shipment.model.Uom;
import kranthi.shipment.repo.UomRepository;
import kranthi.shipment.service.IUomService;

@Service
public class UomServiceImpl implements IUomService{
	@Autowired
private UomRepository repo;

	@Override
public Integer saveUom(Uom uom) {
	uom =repo.save(uom);
	Integer id = uom.getId();
	return id;
}
  @Override
public List<Uom> getAllUoms() {
	List<Uom> list=repo.findAll();
	return list;
}
	
	@Override
	public void deleteUom(Integer id) {
		repo.deleteById(id);
		
	}
	
	@Override
	public Optional<Uom> getUombyid(Integer id) {
		Optional<Uom> opt = repo.findById(id);
		return opt;
	}
	@Override
	public Integer updateUom(Uom uom) {
		Uom u =repo.save(uom);
		Integer id = u.getId();
		return id;
	}
	
	@Override
	public boolean uomValidation(String uomModel) {
		
		return repo.getCountOfUomModel(uomModel)>0;
	}
	@Override
	public List<Object[]> getCountUomType() {
		
		return repo.getCountUomType();
	}
	
	@Override
	public Map<Integer, String> getIdandUomModel() {
		List<Object[]> list = repo.getIdUomModel();
		Map<Integer,String> m=new LinkedHashMap<Integer,String>();
		for(Object[] ob:list) {
			m.put(Integer.parseInt(ob[0].toString()), ob[1].toString());
		}
	
		return m;
	}
	
}
