package merc.marcos.barata;

import java.util.Map;

public class Vehicle{
	private String _id;
	private	String _model;
	private String _fuel;
	private String _transmission;
	private Map<String,Availability> _availability;

	Vehicle(String id, String model,String fuel, String trans,Map<String,Availability> avai){
		_id = id;
		_model = model;
		_fuel = fuel;
		_transmission = trans;
		_availability = avai;
	}
	
	public String getId() {
		return _id;
	}
	
	public String getModel() {
		return _model;
	}
	public String getFuel() {
		return _fuel;
	}
	public String getTrans() {
		return _transmission;
	}
	
	public Map<String,Availability> getAvailability(){
		return _availability;
	}
	
	@Override
	public String toString() {
		return "ID: " + _id + " MODEL: " + _model + " FUEL: " +  _fuel + " TRANSMISSION: " + _transmission;
	}
}