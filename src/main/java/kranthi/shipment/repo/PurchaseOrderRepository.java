package kranthi.shipment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kranthi.shipment.model.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
	
	@Modifying
	@Query("update PurchaseOrder set purchaseDefaultStatus=:status where id=:orderId")
	public void updateStatusByOrderId(String status,Integer orderId);
	
	@Query("select id, purchaseOrderCode from PurchaseOrder where purchaseDefaultStatus='Invoiced'")
	public List<Object[]> getAllPos();
}
	