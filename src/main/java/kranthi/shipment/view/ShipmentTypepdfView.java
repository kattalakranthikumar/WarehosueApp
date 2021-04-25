package kranthi.shipment.view;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;

import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import kranthi.shipment.model.ShipmentType;

public class ShipmentTypepdfView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, 
			Document document, PdfWriter writer,
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Font f = new Font(Font.TIMES_ROMAN, 15, Font.BOLD, Color.blue);
		//response.addHeader("Content-Disposition", "attachments;filename=Shipments.pdf");
		Paragraph title = new Paragraph("Shipment Type Data",f);
		title.setAlignment(Element.ALIGN_CENTER);
		@SuppressWarnings("unchecked")
		List<ShipmentType> list =(List<ShipmentType>) model.get("list");
		document.add(title);
		PdfPTable t = new PdfPTable(6);
		
		setHead(t);
		setBody(t,list);
		document.add(t);
		
		
	}

	

	private void setHead(PdfPTable t) {
		t.addCell("id");
		t.addCell("Mode");
		t.addCell("Code");
		t.addCell("enableShipment");
		t.addCell("Grade");
		t.addCell("Description");		
	}
	
	private void setBody(PdfPTable t, List<ShipmentType> list) {
		for(ShipmentType st:list) {
			t.addCell(st.getId().toString());
			t.addCell(st.getShipmentMode());
			t.addCell(st.getShipmentCode());
			t.addCell(st.getEnableShipment());
			t.addCell(st.getShipmentGrade());
			t.addCell(st.getShipmentDesc());
		}
		
	}
	

}
