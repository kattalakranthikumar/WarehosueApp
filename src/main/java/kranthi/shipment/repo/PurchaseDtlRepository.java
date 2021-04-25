package kranthi.shipment.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kranthi.shipment.model.PurchaseDtl;



public interface PurchaseDtlRepository extends JpaRepository<PurchaseDtl, Integer>{


	@Query("SELECT pdtl FROM PurchaseDtl pdtl INNER JOIN pdtl.purchaseOrder as po WHERE po.id=:orderId")
	List<PurchaseDtl> getPurchaseDtlByOrderId(Integer orderId);

	@Query("select count(pdtl.id) from PurchaseDtl pdtl INNER JOIN pdtl.purchaseOrder as po where po.id=:poId")
	Integer getCountOfPartsInPo(Integer poId);

	@Query("SELECT pdtl.id from PurchaseDtl pdtl  JOIN pdtl.part as prt  JOIN pdtl.purchaseOrder as po where prt.id=:partId and po.id=:poId")
	Optional<Integer>  getPurchaseDtlByPartIdAndPoId(Integer partId,Integer poId);


	@Modifying
	@Query( "UPDATE PurchaseDtl SET qty=qty+:newQty WHERE id=:dtlId")
	void updatePurchaseDtlQtyByDtlId(Integer dtlId,Integer newQty);

	@Modifying
	@Query( "UPDATE PurchaseDtl SET qty=qty+1 WHERE id=:dtlId")
	void updatePurchaseDtlQtyByOneByDtlId(Integer dtlId);
	
	
	
			




}
