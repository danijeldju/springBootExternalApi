package com.externalrestsrping.springbootextrernalapi.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "City")

@XmlRootElement
public class City implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	// @PrimaryKeyJoinColumn
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = false, nullable = false)
	private Integer id;

//	@Basic(optional = true)
//	
//	@Column(name = "callingCodes", unique = false)
////	@OneToMany(mappedBy ="method" , cascade = CascadeType.ALL)
//	@ElementCollection(targetClass=String.class) 
//	private List<String> callingCodes;

	@Basic(optional = false)
	@JsonManagedReference
	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
	private List<Docs> docs;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "capital", unique = true)
	private String capital;

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "name", unique = true)
	private String name;

//	public List<String> getCallingCodes() {
//		return callingCodes;
//	}
//
//
//	public void setCallingCodes(List<String> callingCodes) {
//		this.callingCodes = callingCodes;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Docs> getDocs() {
		return docs;
	}

	public void setDocs(List<Docs> docs) {
		this.docs = docs;
	}

}
