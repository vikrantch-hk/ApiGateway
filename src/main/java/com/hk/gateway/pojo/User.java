package com.hk.gateway.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * @author adlakha.vaibhav
 */
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = { "login",
		"store_id" }), indexes = @Index(columnList = ("email")))
@NamedQueries({ @NamedQuery(name = "findUserByEmail", query = "from User u where u.email = :email"),
		@NamedQuery(name = "findUserByLogin", query = "from User u where u.login = :login"),
		@NamedQuery(name = "findUserByContactNumber", query = "from User u where u.contactNumber = :contactNumber"),
		@NamedQuery(name = "findUserByLoginAndStore", query = "from User u where u.login = :login and u.storeId = :storeId"),
		@NamedQuery(name = "findByUserEmailAndPassword", query = "from User u where u.email = :email and u.passwordChecksum = :passwordEncrypted"),
		@NamedQuery(name = "findUserById", query = "from User u where u.id = :userId "),
		@NamedQuery(name = "findUsersById", query = "from User u where u.id in (:userIds) "),
		@NamedQuery(name = "findUserByBirth", query = "from User u where month(u.birthDate) = month(:birthDay) and day(u.birthDate) = day(:birthDay)"),
		@NamedQuery(name = "findUserByContactNumberAndStore", query = "from User u where u.contactNumber = :contactNumber and u.isNumberVerified = true and u.storeId = :storeId"),
		@NamedQuery(name = "findAllUsersByContactNumber", query = "from User u where u.contactNumber = :contactNumber and u.storeId = :storeId"),
		@NamedQuery(name = "findAllUnverifiedUsersByContactNumber", query = "from User u where u.contactNumber = :contactNumber and u.isNumberVerified = false and u.storeId = :storeId"),
		@NamedQuery(name = "findAllUserIdsByParentId", query = "select u.id from User u where u.parentId = :parentId"),
		@NamedQuery(name = "findVerifiedUserByMobile", query = "from User u where u.contactNumber = :mobile and u.isNumberVerified = true") })
public class User implements Serializable {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "login", nullable = false, length = 80)
	private String login;

	@Column(name = "email", nullable = true, length = 80)
	private String email;

	@Column(name = "name", nullable = true, length = 80)
	private String name;

	@Column(name = "password_checksum", nullable = false)
	private String passwordChecksum;

	@Temporal(TemporalType.DATE)
	@Column(name = "birth_date", nullable = true, length = 19)
	private Date birthDate;

	@Column(name = "gender", nullable = true, length = 6)
	private String gender;

	@Column(name = "contact_number", length = 20)
	private String contactNumber;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_dt", nullable = false, length = 19)
	private Date createDate = new Date();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_login_date", nullable = false, length = 19)
	private Date lastLoginDate;

	@Transient
	private String password;

	@Column(name = "unsubscribe_token", length = 512)
	private String unsubscribeToken;

	@Transient
	private String confirmPassword;

	@ManyToMany(fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SELECT)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_name", referencedColumnName = "name"))
	private Set<Role> roles = new HashSet<Role>(0);

	@Column(name = "user_hash", nullable = false, length = 32, unique = true)
	private String userHash;

	// @ManyToOne(fetch = FetchType.LAZY)
	@Column(name = "store_id", nullable = false)
	private Long storeId;

	@Column(name = "referred_by", nullable = true)
	private Long referredByUserId;

	@Column(name = "subscribed_mask", nullable = false)
	private Integer subscribedMask;

	@Column(name = "badge_id")
	private Long badgeId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "badge_update_dt", nullable = true)
	private Date badgeUpdateDate;

	@Column(name = "is_number_verified")
	private boolean isNumberVerified;

	@Column(name = "parent_id")
	private Long parentId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public String getPassword() {
		return password;
	}

	/*
	 * public Date getUpdateDate() { return this.updateDate; }
	 */

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public Set<String> getRoleStrings() {
		Set<String> roleStrings = new HashSet<String>();
		for (Role role : roles) {
			roleStrings.add(role.getName());
		}
		return roleStrings;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getPasswordChecksum() {
		return passwordChecksum;
	}

	public void setPasswordChecksum(String passwordChecksum) {
		this.passwordChecksum = passwordChecksum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		String firstName = "";
		try {
			if (name != null) {
				String[] nameArr = name.split(" ");
				if (nameArr.length > 0)
					firstName = nameArr[0];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return firstName;
	}

	public String toString() {
		return id == null ? "" : id.toString();
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getUserHash() {
		return userHash;
	}

	public void setUserHash(String userHash) {
		this.userHash = userHash;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(Long badgeId) {
		this.badgeId = badgeId;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUnsubscribeToken() {
		return unsubscribeToken;
	}

	public void setUnsubscribeToken(String unsubscribeToken) {
		this.unsubscribeToken = unsubscribeToken;
	}

	public Integer getSubscribedMask() {
		return subscribedMask;
	}

	public void setSubscribedMask(Integer subscribedMask) {
		this.subscribedMask = subscribedMask;
	}

	public Long getReferredByUserId() {
		return referredByUserId;
	}

	public void setReferredByUserId(Long referredByUserId) {
		this.referredByUserId = referredByUserId;
	}

	public Date getBadgeUpdateDate() {
		return badgeUpdateDate;
	}

	public void setBadgeUpdateDate(Date badgeUpdateDate) {
		this.badgeUpdateDate = badgeUpdateDate;
	}

	/*
	 * public boolean hasPermission(EnumPermission enumPermission) { if (roles ==
	 * null || roles.isEmpty()) { return false; } Permission permission = new
	 * Permission(); permission.setName(enumPermission.getPermissionName()); for
	 * (Role role : roles) { if (role.getPermissions().contains(permission)) {
	 * return true; } } return false; }
	 */

	public boolean isNumberVerified() {
		return isNumberVerified;
	}

	public void setNumberVerified(boolean numberVerified) {
		isNumberVerified = numberVerified;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o instanceof User) {
			User user = (User) o;
			return new EqualsBuilder().append(this.login, user.getLogin()).append(this.storeId, user.getStoreId())
					.isEquals();
		}

		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.login).append(this.storeId).toHashCode();
	}

	public String toStringUser() {
		return "User{" + "id=" + id + ", login='" + login + '\'' + ", email='" + email + '\'' + ", name='" + name + '\''
				+ ", contactNumber='" + contactNumber + '\'' + ", createDate=" + createDate + ", lastLoginDate="
				+ lastLoginDate + ", isNumberVerified=" + isNumberVerified + '}';
	}
	/*
	 * @Override protected String[] getKeys() { return new String[]{"id", "login",
	 * "email", "name"}; }
	 * 
	 * @Override protected Object[] getValues() { return new Object[]{this.id,
	 * this.login, this.email != null ? this.email : "", this.name != null ?
	 * this.name : ""}; }
	 */
}
