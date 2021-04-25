package kranthi.shipment.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kranthi.shipment.model.GRN;
import kranthi.shipment.repo.GRNRepository;
import kranthi.shipment.service.IGRNService;

@Service
public class GRNServiceImpl implements IGRNService{

	@Autowired
	private GRNRepository repo;
	
	@Override
	public Integer saveGRN(GRN grn) {
		
		GRN g = repo.save(grn);
		return g.getId();
		}
	
	@Override
	public List<GRN> getAll() {
	 List<GRN> list = repo.findAll();
		return list;
	}
	
	
}
