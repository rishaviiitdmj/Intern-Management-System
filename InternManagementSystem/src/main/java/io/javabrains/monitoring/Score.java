package io.javabrains.monitoring;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Score {

	@Id
	int internId;
	String name;
	String mentorName;
	String buddyName;
	int totalScore;
	int rank;
	@OneToOne
	SkillWiseScore skillWiseScore;
	Score(){
		
	}
	public Score(int internId, String name, String mentorName, String buddyName, int totalScore, int rank,
			SkillWiseScore skillWiseScore) {
		super();
		this.internId = internId;
		this.name = name;
		this.mentorName = mentorName;
		this.buddyName = buddyName;
		this.totalScore = totalScore;
		this.rank = rank;
		this.skillWiseScore = skillWiseScore;
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
	public String getMentorName() {
		return mentorName;
	}
	public void setMentorName(String mentorName) {
		this.mentorName = mentorName;
	}
	public String getBuddyName() {
		return buddyName;
	}
	public void setBuddyName(String buddyName) {
		this.buddyName = buddyName;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public SkillWiseScore getSkillWiseScore() {
		return skillWiseScore;
	}
	public void setSkillWiseScore(SkillWiseScore skillWiseScore) {
		this.skillWiseScore = skillWiseScore;
	}
	
	
}
