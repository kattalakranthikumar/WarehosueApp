package kranthi.shipment.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import kranthi.shipment.model.Uom;

public class UompdfVeiw extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, 
			Document document, PdfWriter writer,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		@SuppressWarnings("unchecked")
		List<Uom> list = (List<Uom>)model.get("List");
		Paragraph p =new Paragraph("Uom Details");
		
		PdfPTable table = new PdfPTable(4);
		setHead(table);
		setBody(table,list);
		document.add(p);
		document.add(table);
	}

	

	private void setBody(PdfPTable table, List<Uom> list) {
		for(Uom uom:list) {
			table.addCell(uom.getId().toString());
			table.addCell(uom.getUomType());
			table.addCell(uom.getUomModel());
			table.addCell(uom.getUomDesc());
		}
		
	}



	private void setHead(PdfPTable table) {
		table.addCell("Id");
		table.addCell("Type");
		table.addCell("Mode");
		table.addCell("Desc");
		
	}
	

}
