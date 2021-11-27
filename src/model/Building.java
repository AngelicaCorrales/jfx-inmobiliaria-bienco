package model;

public class Building {

	private String address;
	private String neighborhood;
	private Zone zone;
	private TypeOfBuilding type;
	private double price;
	private boolean forSale;
	private String observations;
	
	public Building(String address, String neighborhood,String zone,String type, double price, String purpose,String observations) {
		  this.address=address;
		  this.neighborhood=neighborhood;
		  if(purpose.equals("Venta")) {
			  forSale = true;
		  }else {
			  forSale = false; 
		  }
		  switch(zone) {
		  case "Sur":
			  this.zone=Zone.SUR;
			  break;
		  case "Norte":
			  this.zone=Zone.NORTE;
			  break;
		  case "Centro":
			  this.zone=Zone.CENTRO;
			  break;
		  case "Este":
			  this.zone=Zone.ESTE;
			  break;
		  case "Oeste":
			  this.zone=Zone.OESTE;
			  break;
		  }
		  switch(type) {
		  case "Apartaestudio":
			  this.type=TypeOfBuilding.APARTAESTUDIO;
			  break;
		  case "Apartamento":
			  this.type=TypeOfBuilding.APARTAMENTO;
			  break;
		  case "Casa":
			  this.type=TypeOfBuilding.CASA;
			  break;  
		  case "Local":
			  this.type=TypeOfBuilding.LOCAL;
			  break;
		  case "Edificio":
			  this.type=TypeOfBuilding.EDIFICIO;
			  break;
		  case "Finca":
			  this.type=TypeOfBuilding.FINCA;
			  break;
		  case "Lote":
			  this.type=TypeOfBuilding.LOTE;
			  break;
		  case "Oficina":
			  this.type=TypeOfBuilding.OFICINA;
			  break;
		  }			  
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
	
}
