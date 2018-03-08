package merc.marcos.barata;

import java.util.Scanner;

public class CreateBooking extends Command<Mercedes> {
	
	private Scanner _scan;
	
	CreateBooking(Mercedes receiver, Scanner s){
		super(Label.CREATE,receiver);
		_scan = s;
	}
	
	public void execute() {
		_receiver.createBooking(_scan);
	}
	
	@Override
	public String toString() {
		return _title;
	}

}
