package merc.marcos.barata;


import java.util.List;

public class Availability{
	private String _key;
	private List<String> _value;

	Availability(String key, List<String> value){
		_key = key;
		_value = value;
	}
	
	public List<String> getHours(){
		return _value;
	}
	
}