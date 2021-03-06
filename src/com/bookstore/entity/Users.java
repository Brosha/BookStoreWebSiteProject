package com.bookstore.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@NamedQueries({
	@NamedQuery(name = "Users.findAll", query= "select u from Users u Order by u.fullName"),
	@NamedQuery(name = "Users.findByEmail", query= "select u from Users u Where u.email = :email"),
	@NamedQuery(name = "Users.checkLogin", query= "select u from Users u Where u.email = :email AND u.password=:password"),
	@NamedQuery(name = "Users.countAll", query = "select Count(*) from Users u")
})
@Table(name = "users", catalog = "bookstoredb", uniqueConstraints = @UniqueConstraint(columnNames = "email"))


public class Users implements java.io.Serializable {

	private Integer userId;
	private String email;
	private String fullName;
	private String password;

	public Users() {
	}
	public Users(String email, String fullName, String password) {
		this.email = email;
		this.password = password;
		this.fullName = fullName;
	}


	
	

	
	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "user_id", unique = true, nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "email", unique = true, nullable = false, length = 30)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "password", nullable = false, length = 16)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "full_name", nullable = false, length = 30)
	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}
