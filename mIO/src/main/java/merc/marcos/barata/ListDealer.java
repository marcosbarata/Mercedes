package merc.marcos.barata;

public class ListDealer extends Command<Mercedes> {
	
	ListDealer(Mercedes receiver){
		super(Label.DEAL,receiver);
	}
	
	public void execute() {
		_receiver.listDeal();
	}
	
	@Override
	public String toString() {
		return _title;
	}
}
