package com.bip.api.response;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {

	private T data;
	private ArrayList<T> dataArray;
	private List<String> errors;

	public Response() {
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public ArrayList<T> getDataArray() {
		return dataArray;
	}

	public void setDataArray(ArrayList<T> dataArray) {
		this.dataArray = dataArray;
	}

	public List<String> getErrors() {
		if (this.errors == null) {
			this.errors = new ArrayList<String>();
		}
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
