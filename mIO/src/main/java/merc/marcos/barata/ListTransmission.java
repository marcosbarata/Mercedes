package merc.marcos.barata;

public class ListTransmission extends Command<Mercedes> {
	
	ListTransmission(Mercedes receiver){
		super(Label.TRANS,receiver);
	}
	
	public void execute() {
		_receiver.listTrans();
	}
	
	@Override
	public String toString() {
		return _title;
	}
}
