package kranthi.shipment.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.springframework.web.servlet.view.document.AbstractXlsxView;

import kranthi.shipment.model.OrderMethod;


public class OrderMethodExcelView extends AbstractXlsxView{

	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, 
			Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
				@SuppressWarnings("unchecked")
				List<OrderMethod>list =(List<OrderMethod>) model.get("List");
		Sheet sheet = workbook.createSheet("OrderMethod");
		
		
		setHead(sheet);
		setBody(sheet,list);
		
		
	}
	private void setHead(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("Order Id");
		row.createCell(1).setCellValue("Order Mode");
		row.createCell(2).setCellValue("Order Code");
		row.createCell(3).setCellValue("Order Type");
		row.createCell(4).setCellValue("Order Accept");
		row.createCell(5).setCellValue("Order Description");
	}
	private void setBody(Sheet sheet, List<OrderMethod> list) {
		Integer rownum = 1;
		for(OrderMethod om: list) {
			rownum++;
			Row row =sheet.createRow(rownum);
			row.createCell(0).setCellValue(om.getId());
			row.createCell(1).setCellValue(om.getOrderMode());
			row.createCell(2).setCellValue(om.getOrderCode());
			row.createCell(3).setCellValue(om.getOrderType());
	//row.createCell(4).setcell
			row.createCell(5).setCellValue(om.getOrderDesc());
		}
		
		
	}

}
