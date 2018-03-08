package merc.marcos.barata;

import java.util.Scanner;

public class DeleteBooking extends Command<Mercedes>{
	
	Scanner _scan;
	
	DeleteBooking(Mercedes receiver, Scanner s){
		super(Label.CANCEL, receiver);
		_scan = s;
	}
	
	public void execute() {
		_receiver.deleteBooking(_scan);
	}
	
	@Override
	public String toString() {
		return _title;
	}
}
