package resources;

//enum is special class in java which has collection of constants & methods
public enum Enum_Usage {

	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
	private String resource; 
	

	Enum_Usage(String resource) {
		// TODO Auto-generated constructor stub
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}
}
