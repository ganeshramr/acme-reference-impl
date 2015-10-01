package com.acme.reference.impl.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STOCK")
public class BenchmarkClientOracle implements Serializable {

	private static final long serialVersionUID = -2862671438138322400L;

	@Id
	@Column(name = "STOCK_ID")
	private Long id;

	@Column(name = "STOCK_CODE")
	private String code;

	@Column(name = "STOCK_NAME")
	private String name;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
