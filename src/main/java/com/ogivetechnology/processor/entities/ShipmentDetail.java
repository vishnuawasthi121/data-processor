package com.ogivetechnology.processor.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor // <--- THIS is it
@ToString
@EqualsAndHashCode

//@SequenceGenerator(name = "seq_shipment_detail_id", sequenceName = "seq_shipment_detail_id", allocationSize = 1)
@Table(name = "shipment_detail")
@Entity
public class ShipmentDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	// "seq_shipment_detail_id")
	private Long id;

	@Column(name = "sno")
	private String sno;

	@Column(name = "reciptId")
	private String reciptId;

	@Column(name = "userId")
	private String userId;

	// Ship Name
	@Column(name = "shipName")
	private String shipName;

	@Column(name = "occasion")
	// Occasion
	private String occasion;

	@Column(name = "raOrSaOrFxNumber")
	// RA no of SDRS or DL no of SDRS or Fax / OPDEF signal reference
	private String raOrSaOrFxNumber;

	@Column()
	// Equipment Name
	private String equipmentName;

	@Column()
	// Sub equipment
	private String subEquipmentName;

	@Column
	// Technical Specifications of the item
	private String nameDescriptionOfItem;
	@Column(name = "technical_specification")
	// Location (where it is fitted onboard)
	private String technicalSpecificationDescription;

	// Location (where it is fitted onboard)
	@Column(name = "location")
	private String location;

	@Column(name = "landingDepartment")
	// Department of NSRY (Kar) where it is to be landed
	private String deptTo;
	@Column
	// Centre No of the department
	private String centreNoOfTheDepartment;
	@Column
	// Description of defect
	private String descriptionOfDefect;
	@Column
	// Any other details
	private String anyOtherDetails;
	// Created Time
	@Column(name = "created_time")
	private Date createdDate;

	@Column
	// Updated Time
	private Date updatedDate;

	@Column(name = "shipStaffContactDetails")
	private String shipStaffContactDetails;

	@Column(name = "statusTracking")
	private String statusTracking;

	@Column(name = "extra_field_1")
	private String extraField1;

	@Column(name = "extra_field_2")
	private String extraField2;

	@Column(name = "extra_field_3")
	private String extraField3;

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getCentreNoOfTheDepartment() {
		return centreNoOfTheDepartment;
	}

	public void setCentreNoOfTheDepartment(String centreNoOfTheDepartment) {
		this.centreNoOfTheDepartment = centreNoOfTheDepartment;
	}

	public String getTechnicalSpecificationDescription() {
		return technicalSpecificationDescription;
	}

	public void setTechnicalSpecificationDescription(String technicalSpecificationDescription) {
		this.technicalSpecificationDescription = technicalSpecificationDescription;
	}

	public String getRaOrSaOrFxNumber() {
		return raOrSaOrFxNumber;
	}

	public void setRaOrSaOrFxNumber(String raOrSaOrFxNumber) {
		this.raOrSaOrFxNumber = raOrSaOrFxNumber;
	}

	public String getShipStaffContactDetails() {
		return shipStaffContactDetails;
	}

	public void setShipStaffContactDetails(String shipStaffContactDetails) {
		this.shipStaffContactDetails = shipStaffContactDetails;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public String getOccasion() {
		return occasion;
	}

	public void setOccasion(String occasion) {
		this.occasion = occasion;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getSubEquipmentName() {
		return subEquipmentName;
	}

	public void setSubEquipmentName(String subEquipmentName) {
		this.subEquipmentName = subEquipmentName;
	}

	public String getNameDescriptionOfItem() {
		return nameDescriptionOfItem;
	}

	public void setNameDescriptionOfItem(String nameDescriptionOfItem) {
		this.nameDescriptionOfItem = nameDescriptionOfItem;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDeptTo() {
		return deptTo;
	}

	public void setDeptTo(String deptTo) {
		this.deptTo = deptTo;
	}

	public String getDescriptionOfDefect() {
		return descriptionOfDefect;
	}

	public void setDescriptionOfDefect(String descriptionOfDefect) {
		this.descriptionOfDefect = descriptionOfDefect;
	}

	public String getAnyOtherDetails() {
		return anyOtherDetails;
	}

	public void setAnyOtherDetails(String anyOtherDetails) {
		this.anyOtherDetails = anyOtherDetails;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getStatusTracking() {
		return statusTracking;
	}

	public void setStatusTracking(String statusTracking) {
		this.statusTracking = statusTracking;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReciptId() {
		return reciptId;
	}

	public void setReciptId(String reciptId) {
		this.reciptId = reciptId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getExtraField1() {
		return extraField1;
	}

	public void setExtraField1(String extraField1) {
		this.extraField1 = extraField1;
	}

	public String getExtraField2() {
		return extraField2;
	}

	public void setExtraField2(String extraField2) {
		this.extraField2 = extraField2;
	}

	public String getExtraField3() {
		return extraField3;
	}

	public void setExtraField3(String extraField3) {
		this.extraField3 = extraField3;
	}

}
