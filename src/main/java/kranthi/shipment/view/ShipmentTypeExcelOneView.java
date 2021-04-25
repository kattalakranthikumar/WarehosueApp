package kranthi.shipment.view;

import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import kranthi.shipment.model.ShipmentType;

public class ShipmentTypeExcelOneView extends AbstractXlsxView{

	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, 
			Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Sheet sheet = workbook.createSheet();
		
		createHead(sheet);
		ShipmentType kk = (ShipmentType)model.get("st");
		createData(sheet, kk);
		
		
	}

	

	private void createData(Sheet sheet, ShipmentType st) {
		Row row = sheet.createRow(1);
		row.createCell(0).setCellValue(st.getId().toString());
		row.createCell(1).setCellValue(st.getShipmentCode());
		row.createCell(2).setCellValue(st.getShipmentMode());
		row.createCell(3).setCellValue(st.getEnableShipment());
		row.createCell(4).setCellValue(st.getShipmentGrade());
		row.createCell(5).setCellValue(st.getShipmentDesc());
		
	}

	private void createHead(Sheet sheet) {
		Row row=sheet.createRow(0);
		row.createCell(0).setCellValue("Id");
		row.createCell(1).setCellValue("Code");
		row.createCell(2).setCellValue("Mode");
		row.createCell(3).setCellValue("Enable");
		row.createCell(4).setCellValue("Grade");
		row.createCell(5).setCellValue("Description");
		
	//	Row row1 =sheet.createRow(1);
		
		
		
		
	}
	
	
	
	

}
