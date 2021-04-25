package kranthi.shipment.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kranthi.shipment.model.WhUserType;

public interface WhUserTypeRepository extends JpaRepository<WhUserType, Integer> {
	
	@Query("select count(wh.userEmail) from WhUserType wh where wh.userEmail=:userEmail")
	Integer getCountofEmail(String userEmail);
	
	@Query("select id,userCode from WhUserType where userType='Vendor'")
	List<Object[]> getWhUserIdAndCode();

}
