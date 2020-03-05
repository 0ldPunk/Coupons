package com.max.couponsprojectphase2.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "purchases")
public class Purchase implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "amount", nullable = false)
	private int amount;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "couponId", foreignKey = @ForeignKey(name = "FK_PURCHASE_COUPON_ID"))
	private Coupon coupon;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "customerId", foreignKey = @ForeignKey(name = "FK_PURCHASE_CUSTOMER_ID"))
	private Customer customer;

	public Purchase() {
	}

	public Purchase(int amount, Coupon coupon, Customer customer) {
		this.amount = amount;
		this.coupon = coupon;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return this.id.equals(purchase.id) &&
                amount == purchase.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", amount=" + amount + ", coupon=" + coupon + ", customer=" + customer + "]";
	}

}