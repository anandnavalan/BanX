package com.tis.in.BanX.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "q_student_qualification")
public class StudentQualification {

	@Id
	@Column(name = "qualification_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long qualificationId;

	@Column(name = "student_id")
	private long studentId;

	@Column(name = "qualification_name")
	@NotBlank(message = "QUALIFICATION_NAME_NOT_BLANK")
	@Size(min = 2, max = 255, message = "QUALIFICATION_NAME_SIZE_CRITERIA_ERROR")
	private String qualificationName;

	@Column(name = "qualification_institute")
	@NotBlank(message = "QUALIFICATION_INSTITUDE_NOT_BLANK")
	@Size(min = 2, max = 255, message = "QUALIFICATION_INSTITUDE_SIZE_CRITERIA_ERROR")
	private String qualificationInstitute;

	@Column(name = "qualification_percentage")
	private int qualificationPercentage;

	@Column(name = "qualification_year")
	private int qualificationYear;

	@Embedded
	private AuditInfo auditInfo;

	public long getQualificationId() {
		return qualificationId;
	}

	public void setQualificationId(long qualificationId) {
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

	public String getQualificationInstitute() {
		return qualificationInstitute;
	}

	public void setQualificationInstitute(String qualificationInstitute) {
		this.qualificationInstitute = qualificationInstitute;
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

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

}
