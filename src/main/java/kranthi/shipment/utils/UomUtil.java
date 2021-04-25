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
public class UomUtil{

	public void uomPieChart(String path, List<Object []> list) {
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		for(Object[] ob:list) {
			dataset.setValue(ob[0].toString(), Double.valueOf(ob[1].toString()));
			
		}
		
		
		JFreeChart chart = ChartFactory.createPieChart("UOM",dataset);
		
		try {
			ChartUtils.saveChartAsPNG(new File(path+"/uomp.png"), chart, 500, 500);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
public void uombarChart(String path, List<Object []> list) {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(Object[] ob:list) {
			dataset.setValue(Double.valueOf(ob[1].toString()), ob[0].toString(), "");
			
		}
		JFreeChart chart = ChartFactory.createBarChart("Chart", "", "count", dataset);
		
		try {
			ChartUtils.saveChartAsPNG(new File(path+"/uomb.png"), chart, 500, 500);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
