package com.tis.in.BanX.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "q_staff")
public class StaffUser {

	@Id
	@Column(name = "staff_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long staffId;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "staff_first_name")
	@NotBlank(message = "Staff FirstName is Mandatory")
	@Size(min = 2, max = 255, message = "Staff Firstname should be min of 2 chars and max of 255 chars")
	private String staffFirstName;

	@Column(name = "staff_middle_name")
	@Size(min = 2, max = 255, message = "Staff Middlename should be min of 2 chars and max of 255 chars")
	private String staffMiddleName;

	@Column(name = "staff_last_name")
	@Size(min = 2, max = 255, message = "Staff Lastname should be min of 2 chars and max of 255 chars")
	private String staffLastName;

	@Column(name = "staff_address")
	@Size(min = 2, max = 255, message = "Staff Address should be min of 2 chars and max of 255 chars")
	private String staffAddress;

	@Column(name = "staff_city")
	@Size(min = 2, max = 255, message = "Staff City should be min of 2 chars and max of 255 chars")
	private String staffCity;

	@Column(name = "staff_state")
	@Size(min = 2, max = 255, message = "Staff State should be min of 2 chars and max of 255 chars")
	private String staffState;

	@Column(name = "staff_pincode")
	@Range(min = 100000l, max = 1000000l, message = "Staff Pincode should be valid")
	private Integer staffPincode;

	@Column(name = "staff_aadhar")
	@Range(min=100000000000l ,max = 999999999999l ,message ="Staff Aadhar should be valid")
	private Long staffAadhar;

	@Column(name = "staff_alternate_mobile")
	@Range(min = 6000000000l , max = 9999999999l , message="Staff Mobile Number should be valid")
	private Long staffAlternateMobile;

	@Column(name = "staff_qualification")
	@Size(min = 2, max = 255, message = "Staff Qualification should be min of 2 chars and max of 255 chars")
	private String staffQualification;

	@Column(name = "staff_joining_date")
	@PastOrPresent(message = "Staff Joining Date should be past or present")
	private Date staffJoiningDate;

	@Column(name = "staff_notes")
	@Size(min = 2, max = 255, message = "Staff Notes should be min of 2 chars and max of 255 chars")
	private String staffNotes;

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getStaffFirstName() {
		return staffFirstName;
	}

	public void setStaffFirstName(String staffFirstName) {
		this.staffFirstName = staffFirstName;
	}

	public String getStaffMiddleName() {
		return staffMiddleName;
	}

	public void setStaffMiddleName(String staffMiddleName) {
		this.staffMiddleName = staffMiddleName;
	}

	public String getStaffLastName() {
		return staffLastName;
	}

	public void setStaffLastName(String staffLastName) {
		this.staffLastName = staffLastName;
	}

	public String getStaffAddress() {
		return staffAddress;
	}

	public void setStaffAddress(String staffAddress) {
		this.staffAddress = staffAddress;
	}

	public String getStaffCity() {
		return staffCity;
	}

	public void setStaffCity(String staffCity) {
		this.staffCity = staffCity;
	}

	public String getStaffState() {
		return staffState;
	}

	public void setStaffState(String staffState) {
		this.staffState = staffState;
	}

	public Integer getStaffPincode() {
		return staffPincode;
	}

	public void setStaffPincode(Integer staffPincode) {
		this.staffPincode = staffPincode;
	}

	public Long getStaffAadhar() {
		return staffAadhar;
	}

	public void setStaffAadhar(Long staffAadhar) {
		this.staffAadhar = staffAadhar;
	}

	public Long getStaffAlternateMobile() {
		return staffAlternateMobile;
	}

	public void setStaffAlternateMobile(Long staffAlternateMobile) {
		this.staffAlternateMobile = staffAlternateMobile;
	}

	public String getStaffQualification() {
		return staffQualification;
	}

	public void setStaffQualification(String staffQualification) {
		this.staffQualification = staffQualification;
	}

	public Date getStaffJoiningDate() {
		return staffJoiningDate;
	}

	public void setStaffJoiningDate(Date staffJoiningDate) {
		this.staffJoiningDate = staffJoiningDate;
	}

	public String getStaffNotes() {
		return staffNotes;
	}

	public void setStaffNotes(String staffNotes) {
		this.staffNotes = staffNotes;
	}

}
