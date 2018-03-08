package merc.marcos.barata;


import java.time.LocalDateTime;

public class Booking{
	private String _id;
	private String _vehicleId;
	private String _firstName;
	private String _lastName;
	private LocalDateTime _pickupDate;
	private LocalDateTime _createdAt;
	private LocalDateTime _cancelledAt;
	private String _cancelledReason;

	Booking(String id, String vid,String fn,String ln, LocalDateTime pd, LocalDateTime crtd, LocalDateTime canc, String cr){
		_id = id;
		_vehicleId = vid;
		_firstName = fn;
		_lastName =  ln;
		_pickupDate = pd;
		_createdAt = crtd;
		_cancelledAt = canc;
		_cancelledReason =cr;
	}
	
	public String getId() {
		return _id;
	}
	public String getVId() {
		return this._vehicleId;
	}
	public LocalDateTime getpickupDate() {
		return this._pickupDate;
	}
	
	public void setCancTime(LocalDateTime t) {
		_cancelledAt = t;
	}
	public void setReason(String r) {
		_cancelledReason = r;
	}
	
	@Override
	public String toString() {
		return "ID: " + this._id +" Vehicle ID: " +this._vehicleId + " First Name: " + this._firstName + " Last Name: " + this._lastName + " Pickup Date: "+ this._pickupDate + " Created At: " + this._createdAt + " Cancelled At: " + this._cancelledAt + " Reason: "+this._cancelledReason+"\n";}
}