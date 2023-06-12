package io.javabrains.Entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Buddy {

	@Id
	private int id;
	private String name;
	private String address;
	private String mobile;
	private Date created;
	private Date updated;
	
	@OneToMany(mappedBy="buddy")
	private List<Intern> intern;

	public Buddy(int id, String name, String address, String mobile, Date created, Date updated, List<Intern> intern) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.mobile = mobile;
		this.created = created;
		this.updated = updated;
		this.intern = intern;
	}

	public Buddy() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public List<Intern> getIntern() {
		return intern;
	}

	public void setIntern(List<Intern> intern) {
		this.intern = intern;
	}


	
}
