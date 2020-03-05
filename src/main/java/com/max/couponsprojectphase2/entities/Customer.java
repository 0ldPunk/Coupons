package com.max.couponsprojectphase2.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {


	@Id
	private Long id;

	@JoinColumn(name = "ID", foreignKey = @ForeignKey(name = "FK_CUSTOMER_USER_ID"))
	@OneToOne(cascade = CascadeType.REMOVE, orphanRemoval = true)
	private User user;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@Column(name = "Address", nullable = false)
	private String address;

	@Column(name = "Email", unique = true, nullable = false)
	private String email;

	@JsonIgnore
	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Purchase> purchases;

	public Customer() {
	}
	
	// Full constructor without customerId
    public Customer(User user, String firstName, String lastName, String address, String email) {
        this.user = user;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
    }

    // Full constructor without user
    public Customer(Long customerId, String firstName, String lastName, String address, String email) {
        this.id = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
    }

    // Full constructor
    public Customer(Long customerId, User user, String firstName, String lastName, String address, String email) {
        this(user, firstName, lastName, address, email);
        this.id = customerId;
    }
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [user=" + user + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", email=" + email + "]";
	}

}