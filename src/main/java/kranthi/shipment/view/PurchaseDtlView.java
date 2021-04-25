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

import kranthi.shipment.model.PurchaseDtl;

public class PurchaseDtlView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, 
			Document document, 
			PdfWriter writer,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		@SuppressWarnings("unchecked")
		List<PurchaseDtl> pdtl = (List<PurchaseDtl>) model.get("list");
		Font titleFont = new Font(Font.HELVETICA, 20, Font.BOLD, Color.BLUE);
		Paragraph p = new Paragraph("Vendor Invoice",titleFont);
		p.setAlignment(Element.ALIGN_CENTER);
		Paragraph vInvoice = new Paragraph("VendorNo:");
		PdfPTable table = new PdfPTable(5);
		table.setWidths(new float[]{1.0f,3.0f,1.5f,1.0f,1.5f});
		table.setSpacingBefore(20);
		table.setSpacingAfter(20);
		
		
		document.addTitle("Vendor Invoice");
		document.add(p);
		
		
		
		
		sethead(table);
		setbody(table,pdtl);
		document.add(table);
		
		double finalCost =0.0;
		/*for(PurchaseDtl dtl:poDtls) {
			finalCost += (dtl.getQty()* dtl.getPart().getPartBaseCost());
		}*/
		finalCost = 
				pdtl
				.stream()
				.mapToDouble(
						dtl->
						dtl.getQty()* dtl.getPart().getPartCost()
						)
				.sum();
Paragraph p1 = new Paragraph("Final Cost :"+Double.valueOf(finalCost).toString());	
p1.setAlignment(Element.ALIGN_BOTTOM);
document.add(p1);

	}

	private void sethead(PdfPTable table) {
		table.addCell("Code");
		table.addCell("Product");
		table.addCell("Base Cost");
		table.addCell("Qty");
		table.addCell("Value");
				
	}

	private void setbody(PdfPTable table, List<PurchaseDtl> list) {
		Double value=0.0;
		for(PurchaseDtl tab:list) {
			table.addCell(tab.getId().toString());
			table.addCell(tab.getPart().getPartCode());
			table.addCell(tab.getPart().getPartCost().toString());
			table.addCell(tab.getQty().toString());
			table.addCell(String.valueOf((tab.getPart().getPartCost())*tab.getQty()));
			
			 value = value+tab.getPart().getPartCost();
		}
		
		
	}
}
