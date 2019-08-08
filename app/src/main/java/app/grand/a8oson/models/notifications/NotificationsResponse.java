package app.grand.a8oson.models.notifications;

import java.io.Serializable;
import java.util.List;
 import com.google.gson.annotations.SerializedName;

 public class NotificationsResponse implements Serializable {

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private List<NotificationsData> data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(List<NotificationsData> data){
		this.data = data;
	}

	public List<NotificationsData> getData(){
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
			"NotificationsResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}