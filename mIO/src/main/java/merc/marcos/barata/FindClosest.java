package merc.marcos.barata;

import java.util.Scanner;

public class FindClosest extends Command<Mercedes> {
	
	private Scanner _scan;
	
	FindClosest(Mercedes receiver, Scanner s){
		super(Label.FIND,receiver);
		_scan = s;
	}
	
	public void execute() {
		_receiver.findClosest(_scan);
	}
	
	@Override
	public String toString() {
		return _title;
	}
}
