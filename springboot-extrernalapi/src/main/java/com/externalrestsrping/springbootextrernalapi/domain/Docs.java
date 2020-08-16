package com.externalrestsrping.springbootextrernalapi.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Docs")

@XmlRootElement
public class Docs implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	// @PrimaryKeyJoinColumn
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = false, nullable = false)
	private Integer id;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "name", unique = true)
	private String name;

	@JsonBackReference
	@ManyToOne
	private City city;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
