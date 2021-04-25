package kranthi.shipment.utils;

import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class UserUtil{
/*
 * To generate password we use predefined class UUID which will generate 32bit immutable value.
 */
	public String generatePassword(){
		return UUID.randomUUID().toString().replace("-", "").substring(0, 8);
	}
	
	public String getOtp() {
		return String.format("5525", new Random().nextInt(1000));
	}
	
	
}
