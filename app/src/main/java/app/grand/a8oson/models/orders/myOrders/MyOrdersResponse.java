package app.grand.a8oson.models.orders.myOrders;

import java.io.Serializable;
import java.util.List;
 import com.google.gson.annotations.SerializedName;

public class MyOrdersResponse implements Serializable {
	@SerializedName("msg")
	private String msg;

	@SerializedName("data")
	private List<MyOrders> data;

	@SerializedName("status")
	private int status;


	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setData(List<MyOrders> data){
		this.data = data;
	}

	public List<MyOrders> getData(){
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
			"MyOrdersResponse{" + 
			"msg = '" + msg + '\'' + 
			",data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}