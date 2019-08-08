package app.grand.a8oson.models.orders;

 import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CreateOrderResponse implements Serializable {

	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private OrderData data;

	@SerializedName("status")
	private int status;

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(OrderData data){
		this.data = data;
	}

	public OrderData getData(){
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
			"CreateOrderResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}