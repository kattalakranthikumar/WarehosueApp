package kranthi.shipment.repo;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kranthi.shipment.model.Part;

public interface PartRepository extends JpaRepository<Part, Integer>{
	
	@Query("select id,partCode from Part")
	List<Object[]> getPartIdAndCode();
	
}
