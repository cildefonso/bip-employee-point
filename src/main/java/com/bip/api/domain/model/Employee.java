package com.bip.api.domain.model;

import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "employees")
public class Employee implements Serializable {
	
		
	private static final long serialVersionUID = -1914440069962674601L;
	
	@org.springframework.data.annotation.Id
	@Field("_id")
	private ObjectId _id;
	@Field("fullname")
	private String fullname;
	@Field("cpf")
	private String cpf;
	@Field("email")
	private String email;
	@Field("idcompany")
	private String idcompany;
	@Field("idclient")
	private String idclient;
	@Field("idaddress")
	private String idaddress;
	@Field("loginId")
	private ObjectId loginId;
	@Field("userTypeAccess")
	private String userTypeAccess;
	@Field("numberAddress")
	private String numberAddress;
	@Field("complementAddress")
	private String complementAddress;
	@Field("userId")
	private ObjectId userId;
	@Field("phones")
	private Phones phones;
	@Field("launch")
	private ArrayList<Launch> launch;
	//private Launch launch;
	
	 //this.age = getDiffYears(dateOfBirth, new Date());
	 private int getDiffYears(Date first, Date last) {
	       Calendar a = getCalendar(first);
	       Calendar b = getCalendar(last);
	       int diff = b.get(YEAR) - a.get(YEAR);
	       if (a.get(MONTH) > b.get(MONTH) ||
	               (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
	           diff--;
	       }
	       return diff;
	   }
	   private Calendar getCalendar(Date date) {
	       Calendar cal = Calendar.getInstance(Locale.US);
	       cal.setTime(date);
	       return cal;
	   }
	   
	
	
	
	public ObjectId get_id() {
		return _id;
	}
	public void set_Id(ObjectId _id) {
		this._id = _id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdcompany() {
		return idcompany;
	}
	public void setIdcompany(String idcompany) {
		this.idcompany = idcompany;
	}
	public String getIdclient() {
		return idclient;
	}
	public void setIdclient(String idclient) {
		this.idclient = idclient;
	}
	public String getIdaddress() {
		return idaddress;
	}
	public void setIdaddress(String idaddress) {
		this.idaddress = idaddress;
	}
	
	public ObjectId getLoginId() {
		return loginId;
	}
	public void setLoginId(ObjectId loginId) {
		this.loginId = loginId;
	}
	public String getUserTypeAccess() {
		return userTypeAccess;
	}
	public void setUserTypeAccess(String userTypeAccess) {
		this.userTypeAccess = userTypeAccess;
	}
	public String getNumberAddress() {
		return numberAddress;
	}
	public void setNumberAddress(String numberAddress) {
		this.numberAddress = numberAddress;
	}
	public String getComplementAddress() {
		return complementAddress;
	}
	public void setComplementAddress(String complementAddress) {
		this.complementAddress = complementAddress;
	}
	public ObjectId getUserId() {
		return userId;
	}
	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}
	public Phones getPhones() {
		return phones;
	}
	public void setPhones(Phones phones) {
		this.phones = phones;
	}
	public ArrayList<Launch> getLaunch() {
		return launch;
	}
	public void setLaunch(ArrayList<Launch> launch) {
		this.launch = launch;
	}
	//	public Launch getLaunch() {
//		return launch;
//	}
//	public void setLaunch(Launch launch) {
//		this.launch = launch;
//	}
	@Override
	public String toString() {
		return "Employee [_id=" + _id + ", fullname=" + fullname + ", cpf=" + cpf + ", email=" + email + ", idcompany="
				+ idcompany + ", idclient=" + idclient + ", idaddress=" + idaddress + ", loginId=" + loginId
				+ ", userTypeAccess=" + userTypeAccess + ", numberAddress=" + numberAddress + ", complementAddress="
				+ complementAddress + ", userId=" + userId + ", phones=" + phones + ", launch=" + launch + "]";
	}
		
		 	
}
