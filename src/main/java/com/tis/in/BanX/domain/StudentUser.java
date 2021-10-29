package com.tis.in.BanX.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "q_student")
public class StudentUser {

	@Id
	@Column(name = "student_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long studentId;

	@Column(name = "user_id")
	private long userId;

	@Column(name = "student_first_name")
	@NotBlank(message = "studentFirstName is mandatory")
	@Size(min = 2, max = 255, message = "Student FirstName should be minimum of 2 chars and maximum of 255 chars")
	private String studentFirstName;

	@Column(name = "student_middle_name")
	@Size(min = 2, max = 255, message = "Student MiddleName should be minimum of 2 chars and maximum of 255 chars")
	private String studentMiddleName;

	@Column(name = "student_last_name")
	@Size(min = 2, max = 255, message = "Student LastName should be minimum of 2 chars and maximum of 255 chars")
	private String studentLastName;

	@Column(name = "student_address")
	private String studentAddress;

	@Column(name = "student_city")
	private String studentCity;

	@Column(name = "student_state")
	private String studentState;

	@Column(name = "student_pincode")
	@Range(min = 0, max = 1000000, message = "Student PinCode should be Valid")
	private long studentPincode;

	@Column(name = "student_aadhar")
	private long studentAadhar;

	@Column(name = "student_alternate_mobile")
	private String studentAlternateMobile;

	@Column(name = "student_father_name")
	@Size(min = 2, max = 255, message = "Student FatherName should be minimum of 2 chars and maximum of 255 chars")
	private String studentFatherName;

	@Column(name = "student_father_mobile")
	private String studentFatherMobile;

	@Column(name = "student_mother_name")
	private String studentMotherName;

	@Column(name = "student_mother_mobile")
	private String studentMotherMobile;

	@Column(name = "student_joining_date")
	private Date studentJoiningDate;

	@Column(name = "student_dob")
	@Past(message = "Student DOB should be a Past Date")
	private Date studentDob;

	@Column(name = "student_notes")
	private String studentNotes;

	@Embedded
	private AuditInfo auditInfo;
	
	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getStudentFirstName() {
		return studentFirstName;
	}

	public void setStudentFirstName(String studentFirstName) {
		this.studentFirstName = studentFirstName;
	}

	public String getStudentMiddleName() {
		return studentMiddleName;
	}

	public void setStudentMiddleName(String studentMiddleName) {
		this.studentMiddleName = studentMiddleName;
	}

	public String getStudentLastName() {
		return studentLastName;
	}

	public void setStudentLastName(String studentLastName) {
		this.studentLastName = studentLastName;
	}

	public String getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(String studentAddress) {
		this.studentAddress = studentAddress;
	}

	public String getStudentCity() {
		return studentCity;
	}

	public void setStudentCity(String studentCity) {
		this.studentCity = studentCity;
	}

	public String getStudentState() {
		return studentState;
	}

	public void setStudentState(String studentState) {
		this.studentState = studentState;
	}

	public long getStudentPincode() {
		return studentPincode;
	}

	public void setStudentPincode(long studentPincode) {
		this.studentPincode = studentPincode;
	}

	public long getStudentAadhar() {
		return studentAadhar;
	}

	public void setStudentAadhar(long studentAadhar) {
		this.studentAadhar = studentAadhar;
	}

	public String getStudentAlternateMobile() {
		return studentAlternateMobile;
	}

	public void setStudentAlternateMobile(String studentAlternateMobile) {
		this.studentAlternateMobile = studentAlternateMobile;
	}

	public String getStudentFatherName() {
		return studentFatherName;
	}

	public void setStudentFatherName(String studentFatherName) {
		this.studentFatherName = studentFatherName;
	}

	public String getStudentFatherMobile() {
		return studentFatherMobile;
	}

	public void setStudentFatherMobile(String studentFatherMobile) {
		this.studentFatherMobile = studentFatherMobile;
	}

	public String getStudentMotherName() {
		return studentMotherName;
	}

	public void setStudentMotherName(String studentMotherName) {
		this.studentMotherName = studentMotherName;
	}

	public String getStudentMotherMobile() {
		return studentMotherMobile;
	}

	public void setStudentMotherMobile(String studentMotherMobile) {
		this.studentMotherMobile = studentMotherMobile;
	}

	public Date getStudentJoiningDate() {
		return studentJoiningDate;
	}

	public void setStudentJoiningDate(Date studentJoiningDate) {
		this.studentJoiningDate = studentJoiningDate;
	}

	public Date getStudentDob() {
		return studentDob;
	}

	public void setStudentDob(Date studentDob) {
		this.studentDob = studentDob;
	}

	public String getStudentNotes() {
		return studentNotes;
	}

	public void setStudentNotes(String studentNotes) {
		this.studentNotes = studentNotes;
	}
	

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

}
