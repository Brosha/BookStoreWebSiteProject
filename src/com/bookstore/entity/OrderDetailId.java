package com.bookstore.entity;
// Generated 30.05.2019 22:28:41 by Hibernate Tools 5.2.12.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * OrderDetailId generated by hbm2java
 */
@Embeddable
public class OrderDetailId implements java.io.Serializable {

	private Integer orderId;
	private Integer bookId;
	private int quantity;
	private String subtotal;

	public OrderDetailId() {
	}

	public OrderDetailId(int quantity, String subtotal) {
		this.quantity = quantity;
		this.subtotal = subtotal;
	}

	public OrderDetailId(Integer orderId, Integer bookId, int quantity, String subtotal) {
		this.orderId = orderId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.subtotal = subtotal;
	}

	@Column(name = "order_id")
	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@Column(name = "book_id")
	public Integer getBookId() {
		return this.bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	@Column(name = "quantity", nullable = false)
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "subtotal", nullable = false, length = 45)
	public String getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof OrderDetailId))
			return false;
		OrderDetailId castOther = (OrderDetailId) other;

		return ((this.getOrderId() == castOther.getOrderId()) || (this.getOrderId() != null
				&& castOther.getOrderId() != null && this.getOrderId().equals(castOther.getOrderId())))
				&& ((this.getBookId() == castOther.getBookId()) || (this.getBookId() != null
						&& castOther.getBookId() != null && this.getBookId().equals(castOther.getBookId())))
				&& (this.getQuantity() == castOther.getQuantity())
				&& ((this.getSubtotal() == castOther.getSubtotal()) || (this.getSubtotal() != null
						&& castOther.getSubtotal() != null && this.getSubtotal().equals(castOther.getSubtotal())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getOrderId() == null ? 0 : this.getOrderId().hashCode());
		result = 37 * result + (getBookId() == null ? 0 : this.getBookId().hashCode());
		result = 37 * result + this.getQuantity();
		result = 37 * result + (getSubtotal() == null ? 0 : this.getSubtotal().hashCode());
		return result;
	}

}
