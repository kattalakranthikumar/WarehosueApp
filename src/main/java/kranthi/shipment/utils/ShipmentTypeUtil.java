package kranthi.shipment.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

@Component
public class ShipmentTypeUtil {
	
public void generateshipmentchart(String path,List<Object[]> list) {
	//1.create dataset by using List object.
	DefaultPieDataset dataset = new DefaultPieDataset();
	for(Object[] ob:list) {
		dataset.setValue(ob[0].toString(), Double.valueOf(ob[1].toString()));
	}
	
	//2. create jfree chart object by using chartfactory.
	
	JFreeChart chart = ChartFactory.createPieChart("shipments",dataset);
	
	//3. convert to image format by using chartutils.
	try {
		ChartUtils.saveChartAsPNG(new File(path +"/Shipment.png"), chart, 400, 400);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void generatebarchart(String path,List<Object[]> list) {
	//1.create dataset by using List object.
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	for(Object[] ob:list) {
		dataset.setValue(Double.valueOf(ob[1].toString()),ob[0].toString(),"");
	}
	//2. create jfree chart object by using chartfactory.

	JFreeChart chart =ChartFactory.createBarChart("SHIPMENT MODE", "MODES", "COUNT",dataset);
	
	//3. convert to image format by using chartutils.
	try {
		ChartUtils.saveChartAsPNG(new File(path+"/ShipmentB.png"), chart, 400, 450);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
