package io.javabrains.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Intern")
public class Intern {

	@Id
	private int internId;
	private String name;
	private String address;
	private String mobile;
	private Date created;
	private Date updated;
	@ManyToOne
	private Buddy buddy;
	@ManyToOne
	private Mentor mentor;
	public Intern(int internId, String name, String address, String mobile, Date created, Date updated, Buddy buddy,
			Mentor mentor) {
		super();
		this.internId = internId;
		this.name = name;
		this.address = address;
		this.mobile = mobile;
		this.created = created;
		this.updated = updated;
		this.buddy = buddy;
		this.mentor = mentor;
	}
	public Intern() {
		super();
	}
	public int getInternId() {
		return internId;
	}
	public void setInternId(int internId) {
		this.internId = internId;
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
	public Buddy getBuddy() {
		return buddy;
	}
	public void setBuddy(Buddy buddy) {
		this.buddy = buddy;
	}
	public Mentor getMentor() {
		return mentor;
	}
	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}
	
}
