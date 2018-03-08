package merc.marcos.barata;

public abstract class Command<Receiver> {
	protected String _title;
	protected Receiver _receiver;
	
	public Command(String t,Receiver r) {
		_title = t;
		_receiver = r;
	}
	
	public abstract void execute();
	
	@Override
	public String toString() {
		return _title;
	}
}
