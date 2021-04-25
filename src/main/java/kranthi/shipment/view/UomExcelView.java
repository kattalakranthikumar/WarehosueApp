package kranthi.shipment.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import kranthi.shipment.model.Uom;



public class UomExcelView extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, 
			Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Uom>list = (List<Uom>) model.get("List");
		Sheet sheet = workbook.createSheet("Uom");
		setHead(sheet);
		setBody(sheet,list);
		
	}
	private void setHead(Sheet sheet) {
		Row row = sheet.createRow(0);
		row.createCell(0).setCellValue("id");
		row.createCell(1).setCellValue("uomType");
		row.createCell(2).setCellValue("uomModel");
		row.createCell(3).setCellValue("uomDesc");
	}

	private void setBody(Sheet sheet,List<Uom> list) {
		Integer rownum =1;
		for(Uom uom:list) {
		Row row = sheet.createRow(rownum);
		row.createCell(0).setCellValue(uom.getId());
		row.createCell(1).setCellValue(uom.getUomType());
		row.createCell(2).setCellValue(uom.getUomModel());
		row.createCell(3).setCellValue(uom.getUomDesc());
		rownum++;
		}
		
	}

}
