package io.javabrains.monitoring;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SkillWiseScore {

	@Id
	int internId;
	int java;
	int springBoot;
	int jpa;
	int sql;
	int junit;
	SkillWiseScore(){
		
	}
	public SkillWiseScore(int internId, int java, int springBoot, int jpa, int sql, int junit) {
		super();
		this.internId = internId;
		this.java = java;
		this.springBoot = springBoot;
		this.jpa = jpa;
		this.sql = sql;
		this.junit = junit;
	}
	public int getInternId() {
		return internId;
	}
	public void setInternId(int internId) {
		this.internId = internId;
	}
	public int getJava() {
		return java;
	}
	public void setJava(int java) {
		this.java = java;
	}
	public int getSpringBoot() {
		return springBoot;
	}
	public void setSpringBoot(int springBoot) {
		this.springBoot = springBoot;
	}
	public int getJpa() {
		return jpa;
	}
	public void setJpa(int jpa) {
		this.jpa = jpa;
	}
	public int getSql() {
		return sql;
	}
	public void setSql(int sql) {
		this.sql = sql;
	}
	public int getJunit() {
		return junit;
	}
	public void setJunit(int junit) {
		this.junit = junit;
	}
	
	
}
