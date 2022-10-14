package com.ogivetechnology.processor.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "record_meta_data")
@Entity
public class RecordMetaData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_record_meta_data")
	private Long id;

	@Column(name = "counter_position")
	private Integer counterPosition;

	@Column(name = "createdDate")
	private Date createdDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCounterPosition() {
		return counterPosition;
	}

	public void setCounterPosition(Integer counterPosition) {
		this.counterPosition = counterPosition;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "RecordMetaData [id=" + id + ", counterPosition=" + counterPosition + ", createdDate=" + createdDate
				+ "]";
	}

}
