package kranthi.shipment.utils;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//JAVA#8 static method allowed in interface
public class AppUtil {


	public static Map<Integer, String> convertToMap(
			List<Object[]> list) 
	{

		return list.stream()
				.collect(
						Collectors.toMap(
								ob->(Integer)ob[0], 
								ob->(String)ob[1]
								)
						);
	}


}
