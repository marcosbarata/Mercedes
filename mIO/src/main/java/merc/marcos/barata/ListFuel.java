package merc.marcos.barata;

public class ListFuel extends Command<Mercedes> {
	
	ListFuel(Mercedes receiver){
		super(Label.FUEL,receiver);
	}
	
	public void execute() {
		_receiver.listFuel();
	}
	
	@Override
	public String toString() {
		return _title;
	}

}
