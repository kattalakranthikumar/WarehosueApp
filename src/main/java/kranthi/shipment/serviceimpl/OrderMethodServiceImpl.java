package kranthi.shipment.serviceimpl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kranthi.shipment.model.OrderMethod;
import kranthi.shipment.repo.OrderMethodRepository;
import kranthi.shipment.service.IOrderMethodService;

@Service
public class OrderMethodServiceImpl implements IOrderMethodService {

	@Autowired
	private OrderMethodRepository repo;
	
	@Override
	public Integer saveOm(OrderMethod om) {
		OrderMethod o = repo.save(om);
		Integer id =o.getId();
		return id;
	}
	
	@Override
	public List<OrderMethod> getAllOms() {
		List<OrderMethod> list=repo.findAll();
		return list;
	}
	@Override
	public void deleteOm(Integer id) {
		repo.deleteById(id);
		
	}
	
	@Override
	public Optional<OrderMethod> getOmById(Integer id) {
		Optional<OrderMethod> opt = repo.findById(id);
		
		return opt;
	}
	
	@Override
	public boolean validateOm(String orderCode) {
		
		return repo.getCountOfOmCode(orderCode)>0;
	}
	
	@Override
	public Map<Integer, String> getIdAndorderCodeforSale() {
		List<Object[]> list= repo.getIdAndorderCodeSale();
		LinkedHashMap<Integer,String> omap = new LinkedHashMap<>();
		for(Object[] ob:list) {
			omap.put(Integer.parseInt(ob[0].toString()), ob[1].toString());
		}
		return omap;
	}
	
	@Override
	public Map<Integer, String> getIdAndorderCodeforPurchase() {
		List<Object[]> list =repo.getIdAndorderCodePurchase();
		LinkedHashMap<Integer,String> omap = new LinkedHashMap<>();
		for(Object[] ob:list) {
			omap.put(Integer.parseInt(ob[0].toString()), ob[1].toString());
		}
		return omap;
	}
	
	
}
	
