package com.bip.api.domain.model;

import org.hibernate.validator.constraints.NotEmpty;

public class Phones {


	 private String main;
	 private String secundary
;

	@NotEmpty(message = "É obrigatório o preenchimento do telefone.")
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}
	public String getSecundary() {
		return secundary;
	}
	public void setSecundary(String secundary) {
		this.secundary = secundary;
	}
	@Override
	public String toString() {
		return "Phones [main=" + main + ", secundary=" + secundary + "]";
	}
	
		 
}
