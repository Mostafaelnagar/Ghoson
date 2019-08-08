package app.grand.a8oson.models.chat;

 import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChatResponse implements Serializable {

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private ChatData data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(ChatData data){
		this.data = data;
	}

	public ChatData getData(){
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
			"ChatResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}