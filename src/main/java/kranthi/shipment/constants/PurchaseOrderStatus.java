package kranthi.shipment.constants;

public enum PurchaseOrderStatus {

	OPEN("OPEN"), 
	picking("Picking"),
	ordered("Ordered"),
	invoiced("Invoiced"),
	received("Received");
	private String status;
	PurchaseOrderStatus(String status) {
		this.status=status;
	}
	public String getValue() {
		return status;
	}
}
