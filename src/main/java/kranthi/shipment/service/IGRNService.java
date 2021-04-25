package kranthi.shipment.service;

import java.util.List;

import kranthi.shipment.model.GRN;

public interface IGRNService {

	public Integer saveGRN(GRN grn);
	
	public List<GRN> getAll();
}
