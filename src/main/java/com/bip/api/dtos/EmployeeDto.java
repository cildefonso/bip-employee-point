package com.bip.api.dtos;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public class EmployeeDto {

	
	private ObjectId _id;
	private String fullnameclient;
	private String cnpj;
	private String cpf;
	private String email;
	private String typeName;
	private String idaddress;
	private String matrixEnterprise;
	private String idcompany;
	private String numberAddress;
	private String complementAddress;
	private String enterprise;
	private String userId;
	private PhonesDto phones;
	
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	
	@NotEmpty(message = "Nome não pode ser vazio.")
	@Length(min = 3, max = 200, message = "Nome deve conter entre 3 e 200 caracteres.")
	public String getFullnameclient() {
		return fullnameclient;
	}
	public void setFullnameclient(String fullnameclient) {
		this.fullnameclient = fullnameclient;
	}
	
	@NotEmpty(message = "CNPJ não pode ser vazio.")
	@CNPJ(message="CNPJ inválido.")
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	@NotEmpty(message = "CPF não pode ser vazio.")
	@CPF(message="CPF inválido")
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@NotEmpty(message = "Email não pode ser vazio.")
	@Length(min = 5, max = 200, message = "Email deve conter entre 5 e 200 caracteres.")
	@Email(message="Email inválido.")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getIdaddress() {
		return idaddress;
	}
	public void setIdaddress(String idaddress) {
		this.idaddress = idaddress;
	}
	public String getMatrixEnterprise() {
		return matrixEnterprise;
	}
	public void setMatrixEnterprise(String matrixEnterprise) {
		this.matrixEnterprise = matrixEnterprise;
	}
	public String getIdcompany() {
		return idcompany;
	}
	public void setIdcompany(String idcompany) {
		this.idcompany = idcompany;
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
	public String getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public PhonesDto getPhones() {
		return phones;
	}
	public void setPhones(PhonesDto phones) {
		this.phones = phones;
	}
	@Override
	public String toString() {
		return "EmployeeDto [_id=" + _id + ", fullnameclient=" + fullnameclient + ", cnpj=" + cnpj + ", cpf=" + cpf
				+ ", email=" + email + ", typeName=" + typeName + ", idaddress=" + idaddress + ", matrixEnterprise="
				+ matrixEnterprise + ", idcompany=" + idcompany + ", numberAddress=" + numberAddress
				+ ", complementAddress=" + complementAddress + ", enterprise=" + enterprise + ", userId=" + userId
				+ ", phones=" + phones + "]";
	}
	
	
	
	
	
}
