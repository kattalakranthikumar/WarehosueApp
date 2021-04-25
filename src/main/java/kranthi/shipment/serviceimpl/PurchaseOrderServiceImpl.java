package kranthi.shipment.serviceimpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kranthi.shipment.model.PurchaseDtl;
import kranthi.shipment.model.PurchaseOrder;
import kranthi.shipment.repo.PurchaseDtlRepository;
import kranthi.shipment.repo.PurchaseOrderRepository;
import kranthi.shipment.service.IPurchaseOrderService;
import kranthi.shipment.utils.AppUtil;


@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService{
	
	

	@Autowired
	private PurchaseOrderRepository repo;
	
	@Autowired
	private PurchaseDtlRepository dtlrepo;
	
	

	
	@Override
	public Integer savePurchaseOrder(PurchaseOrder po) {
		po = repo.save(po);
		Integer id = po.getId();
		return id;
	}
	@Override
	public List<PurchaseOrder> getAllPurchaseOrders() {
		List<PurchaseOrder> list =repo.findAll();
		return list;
	}
	
	@Override
	public PurchaseOrder getOnePurchaseOrder(Integer id) {
		Optional<PurchaseOrder> opt = repo.findById(id);
		return opt.get();

	
	}
	
	@Override
	public Integer savePurchasePart(PurchaseDtl pdtl) {
		PurchaseDtl purchaseDtl = dtlrepo.save(pdtl);
		return purchaseDtl.getId();
	}
	
	@Override
	public List<PurchaseDtl> getPartDetails(Integer poid) {
		List<PurchaseDtl>list = dtlrepo.getPurchaseDtlByOrderId(poid);
		return list;
	}
	
	@Override
	public Integer getCountOfPartsInPo(Integer poId) {
		Integer count = dtlrepo.getCountOfPartsInPo(poId);
		return count;
	}
	
	/*
	 * Not yet done for remove operation.
	 */
	@Override
	public void deleteByID(Integer dtlId) {
		dtlrepo.deleteById(dtlId);
		
	}

	@Override
	public Optional<Integer> getPurchaseDtlByPartIdAndPoId(Integer partId, Integer poId) {
		return dtlrepo.getPurchaseDtlByPartIdAndPoId(partId, poId);
	}
	
	@Override
	@Transactional
	public void updatePurchaseDtlQtyByDtlId(Integer dtlId, Integer newQty) {
		dtlrepo.updatePurchaseDtlQtyByDtlId(dtlId, newQty);
		
	}
	
	@Override
	@Transactional
	public void updateStatusByOrderId(String status, Integer orderId) {
		repo.updateStatusByOrderId(status, orderId);
		
	}
	
	@Override
	public List<PurchaseDtl> getAllPurchaseDetails() {
		List<PurchaseDtl> list = dtlrepo.findAll();
		return list;
		
	}
	@Override
	public List<PurchaseDtl> getPurchaseDtlByOrderId(Integer orderId) {
		return dtlrepo.getPurchaseDtlByOrderId(orderId);
	}
	
	@Override
	public Map<Integer, String> getAllPos() {
		List<Object[]> list = repo.getAllPos();
		return AppUtil.convertToMap(list);
	}
	
}
