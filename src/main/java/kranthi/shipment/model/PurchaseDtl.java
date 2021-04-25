package kranthi.shipment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="PurchaseDtl_tab")
public class PurchaseDtl {
	
	@Id
	@GeneratedValue
	@Column(name="purchasedtl_id")
	private Integer id;
	
	@Column(name="purchasedtl_qty")
	private Integer qty;
	
	@ManyToOne
	@JoinColumn(name="part_id_fk_col")
	private Part part;
	
	@ManyToOne
	@JoinColumn(name="purchaseorder_id_fk_col")
	private PurchaseOrder purchaseOrder;

}
