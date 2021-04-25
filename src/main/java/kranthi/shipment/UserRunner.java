package kranthi.shipment;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import kranthi.shipment.model.User;
import kranthi.shipment.service.IUserService;

@Component
public class UserRunner implements CommandLineRunner {
	
	@Autowired
	private IUserService service;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		if(service.findByEmail("kranthi@gmail.com").isEmpty()) {
			User user = new User();
			user.setEmail("kranthi@gmail.com");
			user.setPassword("1234");
			user.setActive(true);
			List<String> roles =new LinkedList<>();
			roles.add("ADMIN");
			roles.add("APPUSER");
			user.setRoles(roles);
			service.saveUser(user);
		}

	}

}
