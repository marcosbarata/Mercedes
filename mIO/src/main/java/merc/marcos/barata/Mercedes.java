package merc.marcos.barata;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.UUID;
import java.lang.Math;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;


public class Mercedes{
	private List<Dealer> _dealers;
	private List<Booking> _bookings;
	private List<String> _models;
	private List<String> _fuelType;
	private List<String> _transType;

    Mercedes(){
        _dealers = new ArrayList<Dealer>();
        _bookings = new ArrayList<Booking>();
        _models = new ArrayList<String>();
        _fuelType = new ArrayList<String>();
        _transType = new ArrayList<String>();
    }

    public void addDealer(Dealer d){
        _dealers.add(d);
    }

    public void addBooking(Booking b){
        _bookings.add(b);
    }

	public void importFile(String file) throws IOException,JSONException,FileNotFoundException{
		String Jdata = "";
		BufferedReader in = null;
       	try{
        	in = new BufferedReader(new FileReader(file));
        	String s;
        	while((s = in.readLine()) != null){
        		Jdata += s +"\n";
        	}
       		}catch (FileNotFoundException e) {
       			throw new FileNotFoundException();}
       		catch(JSONException f) {
       			throw new JSONException("Error");}
       		catch (IOException g) {
       			throw new IOException();
       		}
			finally {
				try {
					if (in != null)
						in.close();
				} catch (IOException ex) {
					ex.printStackTrace();}
				}
       		
       	JSONObject obj = new JSONObject(Jdata);
       	registerDealers(obj.getJSONArray("dealers"));
       	registerBookings(obj.getJSONArray("bookings"));
    }
	
	public void registerDealers(JSONArray dlrs) {
		JSONObject temp = null;
		for (int i = 0; i < dlrs.length(); i++) {
			  temp= dlrs.getJSONObject(i);
			  _dealers.add(new Dealer(temp.getString("id"),temp.getString("name"),temp.getDouble("latitude"),temp.getDouble("longitude"),registerClosed(temp.getJSONArray("closed")), registerVehicles(temp.getJSONArray("vehicles"))));
		}
	}
	public void registerBookings(JSONArray bkgs) {	
		JSONObject temp = null;
		for (int i = 0; i < bkgs.length(); i++) {
			  temp= bkgs.getJSONObject(i);
			  _bookings.add(new Booking(temp.getString("id"),temp.getString("vehicleId"),temp.getString("firstName"),temp.getString("lastName"),LocalDateTime.parse(temp.getString("pickupDate")),LocalDateTime.parse(temp.getString("createdAt")),null,null));
		}
	}
	
	public List<Vehicle> registerVehicles(JSONArray vcls) {
		List<Vehicle> v = new ArrayList<Vehicle>();
		JSONObject tmp = null;
		for(int i = 0;i<vcls.length();i++) {
			tmp = vcls.getJSONObject(i);
			v.add(new Vehicle(tmp.getString("id"),tmp.getString("model"),tmp.getString("fuel"),tmp.getString("transmission"),registerAvailability(tmp.getJSONObject("availability"))));
		if(!_models.contains(tmp.getString("model")))
			_models.add(tmp.getString("model"));
		if(!_fuelType.contains(tmp.getString("fuel")))
			_fuelType.add(tmp.getString("fuel"));
		if(!_transType.contains(tmp.getString("transmission")))
			_transType.add(tmp.getString("transmission"));
		}
		return v;
			
	}
	
	public Map<String,Availability> registerAvailability(JSONObject avai){
		Map<String,Availability> a = new HashMap<String,Availability>();
		Iterator<String> keys = avai.keys();
		while(keys.hasNext()) {
			String key = (String)keys.next();
		    JSONArray hours = avai.getJSONArray(key);
		    List<String> thour = new ArrayList<String>();
		    for(int i = 0; i<hours.length();i++) {
		    	thour.add(hours.getString(i));
		    }
		    a.put(key,new Availability(key,thour));
		}
		return a;
	}
	
	public List<String> registerClosed(JSONArray close){
		List<String> clse = new ArrayList<String>();
		for(int i = 0;i<close.length();i++) {
			clse.add((String) close.get(i));	
			}
		return clse;
		}
	
	public void listModel() {
		Collections.sort(_models, String.CASE_INSENSITIVE_ORDER);
		for(String m : _models) {
			System.out.println("------------- "+m+" --------------");
			for(Dealer d : _dealers)
				for(Vehicle v : d.getVehicles()) {
					if(m.equals(v.getModel()))
						System.out.println(v);
				}
		}
	}
	
	public void listFuel() {
		Collections.sort(_fuelType, String.CASE_INSENSITIVE_ORDER);
		for(String f : _fuelType) {
			System.out.println("------------- "+f+" --------------");
			for(Dealer d : _dealers)
				for(Vehicle v : d.getVehicles()) {
					if(f.equals(v.getFuel()))
						System.out.println(v);
				}
		}
	}
	
	public void listTrans() {
		Collections.sort(_transType, String.CASE_INSENSITIVE_ORDER);
		for(String t : _transType) {
			System.out.println("------------- "+t+" --------------");
			for(Dealer d : _dealers)
				for(Vehicle v : d.getVehicles()) {
					if(t.equals(v.getTrans()))
						System.out.println(v);
				}
		}
	}
	public void listDeal() {
		for(Dealer d: _dealers) {
			System.out.println("------------- "+d.getName()+" --------------");
			for (Vehicle v : d.getVehicles())
				System.out.println(v);
		}
	}
	
	public void findClosest(Scanner scan) {
		double lat = 91;
		double lon = 181;
		System.out.print("Insert the Model: ");
		String model = scan.nextLine();
		System.out.print("Insert the Fuel Type: ");
		String fuel = scan.nextLine();
		System.out.print("Insert the Transmission Type: ");
		String trans = scan.nextLine();
		System.out.print("Insert the latitude: ");
		try {
			lat = scan.nextDouble();
			}catch (InputMismatchException e) {
				scan.nextLine();
			}
		System.out.print("Insert the longitude: ");
		try {
			lon = scan.nextDouble();
			}catch (InputMismatchException e) {
				scan.nextLine();
			}
		if(fc_checkArgs(model,fuel,trans,lat,lon)) {
			Dealer resD = null;
			double distance = Math.sqrt(Math.pow(180,2) + Math.pow(360,2));
			for(Dealer d: _dealers) {
				if(Math.sqrt(Math.pow(lat-d.getLatitude(),2) + Math.pow(lon-d.getLongitude(),2))<distance) {
					for(Vehicle v: d.getVehicles()) {
						if(v.getModel().equals(model) && v.getFuel().equals(fuel) && v.getTrans().equals(trans)) {
							distance = Math.sqrt(Math.pow(lat-d.getLatitude(),2) + Math.pow(lon-d.getLongitude(),2));
							resD = d;
							break;
						}
					}
				}
			}
		if(resD != null)
				System.out.println("The Closest Dealer is: " + resD.getName());
		else
				System.out.println("No Dealer has a car with that specifications.");
		}
}
	
	public void createBooking(Scanner scan) {
		Booking nBook = null;
		System.out.print("Insert your first name: ");
		String fname = scan.nextLine();
		System.out.print("Insert your last name: ");
		String lname = scan.nextLine();
		System.out.print("Insert the vehicleId: ");
		String vId = scan.nextLine();
		Vehicle v = cb_checkArgs(fname,lname,vId);
		if(v!=null) {
			System.out.println("----Availability----");
			for (Map.Entry<String, Availability> entry : v.getAvailability().entrySet()) {
				System.out.println("Day: " + entry.getKey());
				for(String h : entry.getValue().getHours())
					System.out.println(h);
			}
			System.out.println("-------Insert Date and Hours of your Booking-------");
			System.out.print("Insert the Date of your Booking: ");
			String date = scan.nextLine(); 
			System.out.print("Insert the Hours of your Booking: ");
			String hour = scan.nextLine();
			try {
				LocalDateTime time = LocalDateTime.parse(date+"T"+hour);
				for (Map.Entry<String, Availability> entry : v.getAvailability().entrySet()) {
					if(entry.getKey().equals(time.getDayOfWeek().toString().toLowerCase())) {
						for(String h : entry.getValue().getHours()) {
							if(h.equals(hour.replace(":","")) && time.compareTo(LocalDateTime.now())>0) {
									nBook = new Booking(generateString(),vId,fname,lname,time,LocalDateTime.now(),null,null);
									if(checkBooking(nBook)) {
										_bookings.add(nBook);
										System.out.println("Booking Created with success!!");}
							}
							}
						}
					}
				if(nBook ==null)
					System.out.println("Booking failed to create: Invalid Date");
				} catch(DateTimeParseException e) {
					System.out.println("Invalid Date");
				}
	}
}
	
	public String generateString() {
		return UUID.randomUUID().toString();
	}
	
	public boolean checkBooking(Booking b) {
		for(Booking it: _bookings) {
			if(it.getVId().equals(b.getVId()) && it.getpickupDate().equals(b.getpickupDate())) {
				System.out.println("The date for your Booking is already pickedup...");
				return false;}
			}
		return true;
	}
	
	public void deleteBooking(Scanner scan) {
		System.out.print("Insert the Booking Id: ");
		String  bId = scan.nextLine();
		System.out.print("Insert the Cancelation Reason: ");
		String reason = scan.nextLine();
		Booking resB=null;
		for (Booking b : _bookings)
			if(bId.equals(b.getId())) {
				resB = b;
				b.setCancTime(LocalDateTime.now());
				b.setReason(reason);
			}
		if(resB == null)
			System.out.println("Booking doesn't exist");
		else
			System.out.println("Booking cancelled with success!!");
			
	}
	
	
	public Vehicle cb_checkArgs(String fn,String ln,String vId) {
		Vehicle vehicle = null;
		for(int i = 0; i<fn.length();i++)
			if( (int) fn.charAt(i)>48 && (int)fn.charAt(i)<57 ) {
				System.out.println("Invalid First Name");
			}
		for (int i=0; i<ln.length();i++)
			if( (int) fn.charAt(i)>48 && (int)fn.charAt(i)<57 ) {
				System.out.println("Invalid Last Name");
			}
		for(Dealer d: _dealers) {
			for(Vehicle v : d.getVehicles()) {
				if (vId.equals(v.getId())) {
					vehicle = v;
					break;
				}
			}
		}
		
		if(vehicle == null) {
			System.out.println("Invalid Vehicle Id");
		}
		
		return vehicle;
	}
	
	public boolean fc_checkArgs(String m, String f, String t, double lat, double lon) {
		boolean res = true;
		if(!_models.contains(m)) {
			res=false;
			System.out.println("Non-Existent Model");
		}
		if(!_fuelType.contains(f)) {
			res=false;
			System.out.println("Non-Existent Fuel Type");
		}
		if(!_transType.contains(t)) {
			res=false;
			System.out.println("Non-Existent Transmission Type");
		}
		if(lat>90 || lat <-90) {
			res = false;
			System.out.println("Invalid Latitude");
		}
		if (lon>180 || lon <-180) {
			res = false;
			System.out.println("Invalid Longitude");
		}
		return res;
	}
	
	@Override
	public String toString() {
		String merc = "DEALERS: \n";
		for (Dealer d: _dealers)
			merc += d.toString();
		for(Booking b : _bookings)
			merc+=b.toString();
		return merc;
	}

	
}