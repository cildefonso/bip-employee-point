package com.bip.api.domain.model;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.bip.api.enums.PerfilEnum;

@Document(collection = "users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 720499229387943770L;
	
	@org.springframework.data.annotation.Id
	private ObjectId _id;
	private boolean  isActivated;
	private String   email;
	private PerfilEnum   userTypeAccess;
	private Date     createdAt;
	private String password;
	//private Master master;
	
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
	

	public Date getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public PerfilEnum getUserTypeAccess() {
		return userTypeAccess;
	}

	public void setUserTypeAccess(PerfilEnum userTypeAccess) {
		this.userTypeAccess = userTypeAccess;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [_id=" + _id + ", isActivated=" + isActivated + ", email=" + email + ", userTypeAccess="
				+ userTypeAccess + ", createdAt=" + createdAt + ", password=" + password + "]";
	}

		
}
