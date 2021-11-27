package model;

public class Building {

	private String address;
	private String neighborhood;
	private Zone zone;
	private TypeOfBuilding type;
	private double price;
	private boolean forSale;
	private String observations;
	
	public Building(String address, String neighborhood,Zone zone,TypeOfBuilding type, double price, boolean forSale,String observations) {
		  this.address=address;
		  this.neighborhood=neighborhood;
		  this.zone=zone;
		  this.type=type;
		  this.price=price;
		  this.forSale=forSale;
		  this.observations=observations;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public TypeOfBuilding getType() {
		return type;
	}

	public void setType(TypeOfBuilding type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public boolean isForSale() {
		return forSale;
	}

	public void setForSale(boolean forSale) {
		this.forSale = forSale;
	}
	
	public String toString() {
		return address;
	}
	
}
