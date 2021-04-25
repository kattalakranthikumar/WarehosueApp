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
@Table(name="Part_tab")
public class Part {
	
	
	
	@Id
	@GeneratedValue
	@Column(name="part_id_col")
	private Integer id;
	
	@Column(name="part_code_col")
	private String partCode;
	
	@Column(name="part_width_col")
	private Double partWidth;
	
	@Column(name="part_length_col")
	private Double partLength;
	
	@Column(name="part_height_col")
	private Double partHeight;
	
	@Column(name="part_cost_col")
	private Double partCost;

	@Column(name="part_currency_col")
	private String partCurrency;
	
	
	//Integration part
	
	@ManyToOne
	@JoinColumn(name="uom_id_fk_col")
	private Uom uom;
	
	@ManyToOne
	@JoinColumn(name="om_id_fk_col")
	private OrderMethod om;
	
	

}
