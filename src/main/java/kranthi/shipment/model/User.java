package kranthi.shipment.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="user_details_tab")
public class User {

	@Id
	@GeneratedValue
	@Column(name="user_id")
	private Integer id;
	
	@Column(name="uname_id_col")
	private String uname;
	
	@Column(name="email_id_col")
	private String email;
	
	@Column(name="pwd_id_col")
	private String password;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="user_roles_tab",
	joinColumns = @JoinColumn(name="user_id_col"))
	private List<String> roles;
	
	@Column(name="usr_active_col")
	private boolean active;
	
	@Column(name="usr_otp_col")
	private String otp;
}
