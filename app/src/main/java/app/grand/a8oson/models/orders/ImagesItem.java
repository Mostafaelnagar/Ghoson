package app.grand.a8oson.models.orders;

 import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

 public class ImagesItem implements Serializable {

	@SerializedName("imagable_id")
	private int imagableId;

	@SerializedName("imagable_type")
	private String imagableType;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	public void setImagableId(int imagableId){
		this.imagableId = imagableId;
	}

	public int getImagableId(){
		return imagableId;
	}

	public void setImagableType(String imagableType){
		this.imagableType = imagableType;
	}

	public String getImagableType(){
		return imagableType;
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

	@Override
 	public String toString(){
		return 
			"ImagesItem{" + 
			"imagable_id = '" + imagableId + '\'' + 
			",imagable_type = '" + imagableType + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}