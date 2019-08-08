package app.grand.a8oson.models.chat;

 import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

 import app.grand.a8oson.models.orders.ImagesItem;

public class FromUser implements Serializable {

	@SerializedName("image")
	private ImagesItem image;

	@SerializedName("latitude")
	private String latitude;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("longitude")
	private String longitude;

	public void setImage(ImagesItem image){
		this.image = image;
	}

	public ImagesItem getImage(){
		return image;
	}

	public void setLatitude(String latitude){
		this.latitude = latitude;
	}

	public String getLatitude(){
		return latitude;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setLongitude(String longitude){
		this.longitude = longitude;
	}

	public String getLongitude(){
		return longitude;
	}

	@Override
 	public String toString(){
		return 
			"FromUser{" + 
			"image = '" + image + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",longitude = '" + longitude + '\'' + 
			"}";
		}
}