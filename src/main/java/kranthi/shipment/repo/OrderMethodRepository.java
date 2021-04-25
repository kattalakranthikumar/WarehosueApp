package kranthi.shipment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kranthi.shipment.model.OrderMethod;

public interface OrderMethodRepository extends JpaRepository<OrderMethod, Integer>{
	
	@Query("select count(om.orderCode) from OrderMethod om where om.orderCode=:orderCode")
	Integer getCountOfOmCode(String orderCode);

	@Query("select id,orderCode from OrderMethod WHERE orderMode='sale'")
	List<Object[]> getIdAndorderCodeSale();
	
	@Query("select id,orderCode from OrderMethod where orderMode='purchase'")
	List<Object[]> getIdAndorderCodePurchase();
	
}
