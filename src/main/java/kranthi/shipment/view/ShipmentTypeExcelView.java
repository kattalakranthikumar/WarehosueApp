package kranthi.shipment.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import kranthi.shipment.model.ShipmentType;

public class ShipmentTypeExcelView extends AbstractXlsxView{

	public static void main(String[] args) {
		

	}

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, 
			HttpServletRequest request,	HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<ShipmentType> list = (List<ShipmentType>) model.get("List");
	Sheet sheet =workbook.createSheet("ShipmentTypes");
	setHead(sheet);
	setBody(sheet,list);

		
	}

	
	private void setHead(Sheet sheet) {
		Row row=sheet.createRow(0);
		row.createCell(0).setCellValue("Id");
		row.createCell(1).setCellValue("Code");
		row.createCell(2).setCellValue("Mode");
		row.createCell(3).setCellValue("Enable");
		row.createCell(4).setCellValue("Grade");
		row.createCell(5).setCellValue("Description");
		
	}
	
	private void setBody(Sheet sheet, List<ShipmentType> list) {
		
		int rownum=1;
		for(ShipmentType st:list) {
		Row row =sheet.createRow(rownum);
		row.createCell(0).setCellValue(st.getId());
		row.createCell(1).setCellValue(st.getShipmentCode());
		row.createCell(2).setCellValue(st.getShipmentMode());
		row.createCell(3).setCellValue(st.getEnableShipment());
		row.createCell(4).setCellValue(st.getShipmentGrade());
		row.createCell(5).setCellValue(st.getShipmentDesc());
		rownum++;
		
		
		}
		
	}

	

}
