package app.grand.a8oson.models.auth.register;

 import com.google.gson.annotations.Expose;
 import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

 import app.grand.a8oson.models.auth.UserData;

public class RegisterResponse {

	@SerializedName("msg")
	@Expose
	private String msg;

	@SerializedName("status")
	@Expose
	public int status;

	@SerializedName("data")
	@Expose
	private UserData data;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(UserData data){
		this.data = data;
	}

	public UserData getData(){
		return data;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"RegisterResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}