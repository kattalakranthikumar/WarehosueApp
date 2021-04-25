package kranthi.shipment.serviceimpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kranthi.shipment.model.User;
import kranthi.shipment.repo.UserRepository;
import kranthi.shipment.service.IUserService;

@Service
public class UserServiceImpl implements IUserService,UserDetailsService {

	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	
	@Override
	public Integer saveUser(User user) {
		String pwd = encoder.encode(user.getPassword());
		user.setPassword(pwd);
	User usr =	repo.save(user);
	
		return usr.getId();
	}
	
	@Override
	public Optional<User> findByEmail(String Email) {
		Optional<User> opt = repo.findByEmail(Email);
		return opt;
	}
	
	@Override
	@Transactional
	public void updateOtpById(Integer uid, String newotp) {
		repo.updateOtpById(uid, newotp);
		
	}
	
	@Override
	@Transactional
	public void updatePasswordById(Integer uid, String pwd) {
		
		repo.updatePasswordById(uid, pwd);
	}
	
	@Override
	@Transactional
	public void updateStatusById(Integer uid, boolean active) {
		repo.updateStatusById(uid, active);
	}
	
	@Override
	public List<User> findAllUsers() {
		List<User> users = repo.findAll();
		return users;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//load one user details from db
		Optional<User> opt = repo.findByEmail(username);
		if(opt.isEmpty() || !opt.get().isActive() ) {
		throw new UsernameNotFoundException("User Not found");
		}
		else{
		
			//load one user detials
			User user = opt.get(); 
			Set<GrantedAuthority> roles = new HashSet<>();
			for(String role: user.getRoles()) {
				roles.add(new SimpleGrantedAuthority(role));
		}
			
		
		return new org.springframework.security.core.userdetails.User(username, user.getPassword(), roles);
				}

	
	}

	
	
	
}
