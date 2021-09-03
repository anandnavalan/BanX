package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="q_student_qualification")
public class StudentQualification {
	
	@Id
	@Column(name="qualification_id")
	private int qualificationId;
	
	@Column(name = "student_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long studentId;
	
	@Column(name="qualification_name")
	private String qualificationName;
	
	@Column(name="qualification_institude")
	private String qualificationInstitude;
	
	@Column(name="qualification_percentage")
	private int qualificationPercentage;
	
	@Column(name="qualification_year")
	private int qualificationYear;
	
	public int getQualificationId() {
		return qualificationId;
	}
	public void setQualificationId(int qualificationId) {
		this.qualificationId = qualificationId;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public String getQualificationName() {
		return qualificationName;
	}
	public void setQualificationName(String qualificationName) {
		this.qualificationName = qualificationName;
	}
	public String getQualificationInstitude() {
		return qualificationInstitude;
	}
	public void setQualificationInstitude(String qualificationInstitude) {
		this.qualificationInstitude = qualificationInstitude;
	}
	public int getQualificationPercentage() {
		return qualificationPercentage;
	}
	public void setQualificationPercentage(int qualificationPercentage) {
		this.qualificationPercentage = qualificationPercentage;
	}
	public int getQualificationYear() {
		return qualificationYear;
	}
	public void setQualificationYear(int qualificationYear) {
		this.qualificationYear = qualificationYear;
	}

}
