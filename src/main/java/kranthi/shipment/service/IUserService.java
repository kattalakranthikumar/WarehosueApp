package kranthi.shipment.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import kranthi.shipment.model.User;

public interface IUserService {

	public Integer saveUser(User user);
	
	public Optional<User> findByEmail(String Email);
	
	@Transactional
	public void updatePasswordById(Integer uid,String pwd);
	
	@Transactional
	public void updateOtpById(Integer uid,String newotp);

	@Transactional
	public void updateStatusById(Integer uid,boolean active);
	
	public List<User> findAllUsers();

}
