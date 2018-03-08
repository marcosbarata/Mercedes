package merc.marcos.barata;

public class ListModel extends Command<Mercedes> {
	
	ListModel(Mercedes receiver){
		super(Label.MODEL,receiver);
	}
	
	public void execute() {
		_receiver.listModel();
	}
	
	@Override
	public String toString() {
		return _title;
	}
}
