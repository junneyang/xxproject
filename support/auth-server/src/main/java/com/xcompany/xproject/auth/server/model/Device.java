package com.xcompany.xproject.auth.server.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Device {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotEmpty
	@Column(unique = true, nullable = false)
	private String imei;

	private String name;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "devices", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<User> users = new HashSet<User>();

	private boolean active;

	private Long activated_at;

	public Device() {
	}

	public Device(Device device) {
		super();
		this.id = device.getId();
		this.name = device.getName();
		this.imei = device.getImei();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String login) {
		this.imei = login;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public boolean is_active() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}