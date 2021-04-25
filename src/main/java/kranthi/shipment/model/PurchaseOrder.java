package kranthi.shipment.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="purchaseorder_tab")
public class PurchaseOrder {

	@Id
	@GeneratedValue
	@Column(name="purchase_id_col")
	private Integer id;
	
	@Column(name="purchase_ordercode_col")
	private String purchaseOrderCode;
	
	@Column(name="purchase_referencenumber_col")
	private String purchaseReferenceNumber;
	
	@Column(name="purchase_qualitycheck_col")
	private String purchaseQualityCheck;
	
	@Column(name="purchase_defaulstatus_col")
	private String purchaseDefaultStatus;
	
	@Column(name="purchase_desc_col")
	private String purchaseDesc;
	
	@ManyToOne
	@JoinColumn(name="st_id_fk_col")
	private ShipmentType shipmentType;
	
	@ManyToOne
	@JoinColumn(name="whut_id_fk_col")
	private WhUserType whUserType;
	
	
}
