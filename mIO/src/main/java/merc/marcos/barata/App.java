package merc.marcos.barata;

import java.util.Scanner;

import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;

public class App{

	public static void main(String[] args) {
		
		Mercedes _mercedes = new Mercedes();
		String file = "src/main/java/merc/marcos/barata/dataset.json";
		if(args.length>0)
			file = args[0];
		try {
			_mercedes.importFile(file);}
			catch(FileNotFoundException e) {
				System.out.println("Error Opening the Import File: File Not Found");
				return;
			}
			catch(JSONException f) {
				System.out.println("Erro Opening the Import File~: Not a JSON file");
				return;
			}
			catch(IOException g) {
				System.out.println("Erro Opening the Import File");
				return;
			}
		
		int command=-1;
		Scanner scan= new Scanner(System.in);
		MainMenu menu = new MainMenu(Label.TITLE,new Command<?>[] {
			new ListModel(_mercedes),
			new ListFuel(_mercedes),
			new ListTransmission(_mercedes),
			new ListDealer(_mercedes),
			new FindClosest(_mercedes,scan),
			new CreateBooking(_mercedes,scan),
			new DeleteBooking(_mercedes,scan)
		});
		while (command != 0) {
			menu.show();
			System.out.print("Insert a Command: ");
			try {
				command = scan.nextInt();
				}catch(InputMismatchException e ) {
					command = -1;
				}
			scan.nextLine();
			if(command<0 ||command>menu.getLenght())
				System.out.println("Invalid Command");
			else if(command == 0)
				break;
			else
				menu.getCommands()[command-1].execute();
			
		}
		scan.close();
	}
}