package app.grand.a8oson.models.orders.orderDetail;

 import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

 import app.grand.a8oson.models.orders.ImagesItem;

public class Representative implements Serializable {

	@SerializedName("image")
	private ImagesItem image;

	@SerializedName("name")
	private String name;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("id")
	private int id;

	public void setImage(ImagesItem image){
		this.image = image;
	}

	public ImagesItem getImage(){
		return image;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"Representative{" + 
			"image = '" + image + '\'' + 
			",name = '" + name + '\'' + 
			",mobile = '" + mobile + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}