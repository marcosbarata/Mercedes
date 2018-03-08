package merc.marcos.barata;

import java.util.List;

public class Dealer{
	private String _id;
	private String _name;
	private double _latitude;
	private double _longitude;
	private List<Vehicle> _vehicles;
	private List<String> _closed;

	Dealer(String id, String name, double lat, double lon,List<String> close, List<Vehicle> vcls){
		_id = id;
		_name = name;
		_latitude = lat;
		_longitude = lon;		
		_closed = close;
		_vehicles = vcls;

	}
	
	public List<Vehicle> getVehicles(){
		return _vehicles;
	}
	
	public String getName() {
		return _name;
	}
	
	public double getLatitude() {
		return _latitude;
	}
	
	public double getLongitude() {
		return _longitude;
	}
	
	@Override
	public String toString() {
		String deal = "ID: " + _id + " NOME: " + _name + " LATITUDE: " + _latitude + " LONGITUDE: " + _longitude +"\n FECHADO: "; 
		for(String d: _closed)
			deal+=d +"\n";
		deal +="VEICULOS: ";
		for(Vehicle v: _vehicles)
			deal+=v.toString()+"\n";
		return deal;
	}
 }