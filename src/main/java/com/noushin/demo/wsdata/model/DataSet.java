package com.noushin.demo.wsdata.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonAutoDetect;

/**
 * This class represent a data object that can be both persisted and represented as a JSON object.
 * 
 * @author nbashir
 *
 */
@JsonAutoDetect
@javax.persistence.Entity
@Table(name = "testdata")
public class DataSet implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Need this constructor for JSON binding
	 */
	public DataSet() {}
	
	public DataSet(Integer id, String content) {
		this.id = id;
		this.content = content;
	}

	// this attribute needs to be Integer for auto-generating sequences as well as allowing Hibernate to automatically create/drop/update tables.
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	

	@Column(name = "content")
	private String content;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
