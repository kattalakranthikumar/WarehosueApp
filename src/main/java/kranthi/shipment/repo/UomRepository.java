package kranthi.shipment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kranthi.shipment.model.Uom;

public interface UomRepository extends JpaRepository<Uom, Integer>{
	
	@Query("select count(uom.uomModel) from Uom uom where uom.uomModel=:uomModel")
	public Integer getCountOfUomModel(String uomModel);
	
	@Query("SELECT uom.uomType, COUNT(uom.uomType) from Uom uom GROUP BY uom.uomType")
	 public List<Object[]> getCountUomType();
	 
	 @Query("select uom.id, uom.uomModel from Uom uom")
	 List<Object[]> getIdUomModel();

}
