package com.bip.api.security.entities;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bip.api.enums.PerfilEnum;


@Document(collection = "users")
public class Usuario implements Serializable {

	
	private static final long serialVersionUID = -8089268931917698872L;
	
	@org.springframework.data.annotation.Id
	private ObjectId _id;
	//private String _class;
	private boolean  isActivated;
	private String   email;
	private String   hashedPassword;
	private PerfilEnum   userTypeAccess;
	private Date     createdAt;
	

	public Usuario() {
	}

	//@GeneratedValue(strategy = GenerationType.AUTO)
	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public PerfilEnum getUserTypeAccess() {
		return userTypeAccess;
	}

	public void setUserTypeAccess(PerfilEnum userTypeAccess) {
		this.userTypeAccess = userTypeAccess;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Usuario [_id=" + _id + ", isActivated=" + isActivated + ", email=" + email + ", hashedPassword="
				+ hashedPassword + ", userTypeAccess=" + userTypeAccess + ", createdAt=" + createdAt + "]";
	}


	
}
