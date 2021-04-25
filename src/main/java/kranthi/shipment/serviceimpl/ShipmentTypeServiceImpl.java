package kranthi.shipment.serviceimpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import kranthi.shipment.model.ShipmentType;
import kranthi.shipment.repo.ShipmentTypeRepo;
import kranthi.shipment.service.IshipmentTypeService;
import kranthi.shipment.utils.AppUtil;


@Service
public class ShipmentTypeServiceImpl implements IshipmentTypeService
{
	@Autowired
	private ShipmentTypeRepo repo;
	
	@Override
	public Integer saveShipmentType(ShipmentType st) {
		st=repo.save(st);
		Integer id=st.getId();
		return id;
		
		
	}

	
		@Override
		public List<ShipmentType> getAllShipmentTypes() 
		{
		List<ShipmentType> list= repo.findAll();
			return list;
		}
	@Override
	public void deleteShipmentType(Integer id) 
	{
		repo.deleteById(id);
		
	}
	@Override
	public Optional<ShipmentType> getShipmentTypeById(Integer id) {
		Optional<ShipmentType> opt =repo.findById(id);
		return opt;
	}
	@Override
	public Integer updateShipmentTypes(ShipmentType st) {
		st =repo.save(st);
		Integer id = st.getId();
		return id;
	}
	
	@Override
	public boolean getCountOfShipmentCode(String shipmentCode) {
		/*   Integer count=repo.getshipmentCodeCountByShipmentCode(orderCode);
		boolean b=count>0; 
		return b;  */
		return repo.getshipmentCodeCountByShipmentCode(shipmentCode)>0;
	}
	
	@Override
	public List<Object[]> getShipmentModeCount() {
		
		return repo.getShipmentModeCount();
	}


	

	@Override
	public Page<ShipmentType> getShipmentTypesByPage(org.springframework.data.domain.Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	@Override
	public Map<Integer, String> getIdAndShipmentCode() {
		List<Object[]> list = repo.getIdAndShipmentCode();
		Map<Integer,String> map=AppUtil.convertToMap(list);
		return map;
	}
	
}
