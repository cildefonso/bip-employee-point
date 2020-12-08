package com.bip.api.dtos;

public class LaunchDto {
	
	private Integer id;
	private String inputwork;
	private String outputwork;
	private Double valuehour;
	private String typelauch;
	private String description;
	private String location;
	private String datelaunch;
	private String cpf;
	

	public LaunchDto() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getInputwork() {
		return inputwork;
	}


	public void setInputwork(String inputwork) {
		this.inputwork = inputwork;
	}


	public String getOutputwork() {
		return outputwork;
	}


	public void setOutputwork(String outputwork) {
		this.outputwork = outputwork;
	}


	public Double getValuehour() {
		return valuehour;
	}


	public void setValuehour(Double valuehour) {
		this.valuehour = valuehour;
	}


	public String getTypelauch() {
		return typelauch;
	}


	public void setTypelauch(String typelauch) {
		this.typelauch = typelauch;
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

	
	public String getDatelaunch() {
		return datelaunch;
	}

	public void setDatelaunch(String datelaunch) {
		this.datelaunch = datelaunch;
	}

	@Override
	public String toString() {
		return "LaunchDto [id=" + id + ", inputwork=" + inputwork + ", outputwork=" + outputwork + ", valuehour="
				+ valuehour + ", typelauch=" + typelauch + ", description=" + description + ", location=" + location
				+ ", datelaunch=" + datelaunch + ", cpf=" + cpf + "]";
	}


	
}
