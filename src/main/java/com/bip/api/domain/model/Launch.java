package com.bip.api.domain.model;

//import static java.util.Calendar.DATE;
//import static java.util.Calendar.MONTH;
//import static java.util.Calendar.YEAR;

import java.io.Serializable;
//import java.util.Calendar;
import java.util.Date;
//import java.util.Locale;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

import com.bip.api.enums.TipoEnum;

//@Document(collection = "launches")
public class Launch implements Serializable {
	
	private static final long serialVersionUID = 6524560251526772839L;
	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	
	
	@Field("id")
	private Integer id;
	private Date datelaunch;
	private String description;
	private String location;
	private Date dateupdate;
	private TipoEnum typelauch;
	private Double valuehour;
    private Employee employee;
    
	public Launch() {
		
	}

//	 private int getDiffYears(Date first, Date last) {
//	       Calendar a = getCalendar(first);
//	       Calendar b = getCalendar(last);
//	       int diff = b.get(YEAR) - a.get(YEAR);
//	       if (a.get(MONTH) > b.get(MONTH) ||
//	               (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
//	           diff--;
//	       }
//	       return diff;
//	   }
//	   private Calendar getCalendar(Date date) {
//	       Calendar cal = Calendar.getInstance(Locale.US);
//	       cal.setTime(date);
//	       return cal;
//	   }
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getDatelaunch() {
		return datelaunch;
	}

	public void setDatelaunch(Date datelaunch) {
		this.datelaunch = datelaunch;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDateupdate() {
		return dateupdate;
	}

	public void setDateupdate(Date dateupdate) {
		this.dateupdate = dateupdate;
	}

	public TipoEnum getTypelauch() {
		return this.typelauch;
	}

	public void setTypelauch(TipoEnum tipoEnum) {
		this.typelauch = tipoEnum;
	}

	public Double getValuehour() {
		return valuehour;
	}

	public void setValuehour(Double valuehour) {
		this.valuehour = valuehour;
	}


	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Launch [id=" + id + ", datelaunch=" + datelaunch + ", description=" + description + ", location="
				+ location + ", dateupdate=" + dateupdate + ", typelauch=" + typelauch + ", valuehour=" + valuehour
				+ ", employee=" + employee + "]";
	}
	
}
