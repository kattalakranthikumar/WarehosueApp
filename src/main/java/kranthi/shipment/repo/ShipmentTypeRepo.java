package kranthi.shipment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kranthi.shipment.model.ShipmentType;

public interface ShipmentTypeRepo extends JpaRepository<ShipmentType, Integer>
{
	@Query("SELECT COUNT(st.shipmentCode) from ShipmentType st WHERE st.shipmentCode=:shipmentCode")
 public Integer getshipmentCodeCountByShipmentCode(String shipmentCode);
	
	//@Query("SELECT ST.shipmentMode, count(ST.shipmentMode) FROM
	//		ShipmentType ST GROUP BY ST.shipmentMode")
	@Query("SELECT st.shipmentMode, COUNT(st.shipmentMode) FROM ShipmentType st GROUP BY st.shipmentMode")
	public List<Object[]> getShipmentModeCount();
	
	
	@Query("select id,shipmentCode from ShipmentType where enableShipment='yes'")
	List<Object[]> getIdAndShipmentCode();
}
