package kranthi.shipment.view;

import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import kranthi.shipment.model.Uom;

public class UomExcelOneVeiw extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, 
			Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Uom uom = (Uom)model.get("uom");
		Sheet sheet = workbook.createSheet("Uom");
		
		setHead(sheet);
		setBody(sheet, uom);
		
	}

	
	private void setHead(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("id");
		row.createCell(1).setCellValue("uomType");
		row.createCell(2).setCellValue("uomModel");
		row.createCell(3).setCellValue("uomDesc");
		
	}
	private void setBody(Sheet sheet, Uom uom) {
	
		
			Row row = sheet.createRow(1);
			row.createCell(0).setCellValue(uom.getId());
			row.createCell(1).setCellValue(uom.getUomType());
			row.createCell(2).setCellValue(uom.getUomModel());
			row.createCell(3).setCellValue(uom.getUomDesc());
		
	}

}
