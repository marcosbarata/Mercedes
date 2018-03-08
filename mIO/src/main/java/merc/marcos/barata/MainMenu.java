package merc.marcos.barata;

public class MainMenu {
	
	private String _title;
	private Command<?>[] _commands;
	
	MainMenu(String t, Command<?>[] commands){
		_title = t;
		_commands = commands;
		}
	
	public void show() {
		System.out.println(_title);
		for(int i = 0; i<_commands.length;i++)
			System.out.println(i+1+"."+_commands[i].toString());
		System.out.println("0." + Label.CLOSE);
	}
	public Command<?>[] getCommands(){
			return _commands;
		}
	public int getLenght() {
		return _commands.length;
	}
		
	}

