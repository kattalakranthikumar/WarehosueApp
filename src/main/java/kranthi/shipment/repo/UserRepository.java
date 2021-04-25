package kranthi.shipment.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import kranthi.shipment.model.User;	

public interface UserRepository extends JpaRepository<User, Integer>{

	//find user details using emailId
	public Optional<User> findByEmail(String Email);

	//update pwd by userId.
	@Modifying
	@Query("update kranthi.shipment.model.User set password=:pwd where id=:uid")
	public void updatePasswordById(Integer uid,String pwd);

	//update otp by userId.
	@Modifying
	@Query("update kranthi.shipment.model.User set otp=:newotp where id=:uid")
	public void updateOtpById(Integer uid,String newotp);

	//update Status by userId
	@Modifying
	@Query("update kranthi.shipment.model.User set active=:newstatus where id=:uid")
	public void updateStatusById(Integer uid,boolean newstatus);
}
