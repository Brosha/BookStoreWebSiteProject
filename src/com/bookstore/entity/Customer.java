package com.bookstore.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "customer", catalog = "bookstoredb", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@NamedQueries({
	@NamedQuery(name = "Customer.findAll", query =" select c from Customer c Order by c.registerDate DESC"),
	@NamedQuery(name = "Customer.countAll", query = "select Count(*) from Customer c"),
	@NamedQuery(name = "Customer.checkLogin", query= "select c from Customer c Where c.email = :email AND c.password=:password"),
	@NamedQuery(name = "Customer.findByEmail", query = "select c from Customer c where c.email=:email"),
})
public class Customer implements java.io.Serializable {

	private Integer customerId;
	private String email;
	private String fullname;
	private String address;
	private String city;
	private String country;
	private String zipcode;
	private String password;
	private Date registerDate;
	private String phone;
	private Set<Review> reviews = new HashSet<Review>(0);
	private Set<BookOrder> bookOrders = new HashSet<BookOrder>(0);

	public Customer() {
	}

	public Customer(String email, String fullname, String address, String city, String country, String zipcode,
			String password, Date registerDate, String phone) {
		this.email = email;
		this.fullname = fullname;
		this.address = address;
		this.city = city;
		this.country = country;
		this.zipcode = zipcode;
		this.password = password;
		this.registerDate = registerDate;
		this.phone = phone;
	}

	public Customer(String email, String fullname, String address, String city, String country, String zipcode,
			String password, Date registerDate, String phone , Set<Review> reviews, Set<BookOrder> bookOrders) {
		this.email = email;
		this.fullname = fullname;
		this.address = address;
		this.city = city;
		this.country = country;
		this.zipcode = zipcode;
		this.password = password;
		this.registerDate = registerDate;
		this.phone = phone;
		this.reviews = reviews;
		this.bookOrders = bookOrders;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "customer_id", unique = true, nullable = false)
	public Integer getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@Column(name = "email", unique = true, nullable = false, length = 64)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "fullname", nullable = false, length = 30)
	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@Column(name = "address", nullable = false, length = 128)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "city", nullable = false, length = 32)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "country", nullable = false, length = 64)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "zipcode", nullable = false, length = 24)
	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Column(name = "password", nullable = false, length = 16)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Column(name = "phone", nullable = false, length = 15)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "register_date", nullable = false, length = 19)
	public Date getRegisterDate() {
		return this.registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
	public Set<BookOrder> getBookOrders() {
		return this.bookOrders;
	}

	public void setBookOrders(Set<BookOrder> bookOrders) {
		this.bookOrders = bookOrders;
	}

}
