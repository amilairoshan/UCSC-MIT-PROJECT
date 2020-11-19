package com.tm.DTO;

import java.io.Serializable;
import java.util.List;

public class ResponseDTO implements Serializable {
	
	public boolean status;
	public String message;
	public Object data;
	public List<Object> datas;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public List<Object> getDatas() {
		return datas;
	}
	public void setDatas(List<Object> datas) {
		this.datas = datas;
	}
	
	
	

}
