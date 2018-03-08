Marcos Domingues Barata
marcosbarata@tecnico.ulisboa.pt

----------- Run the Application -----------
1)Open terminal 
2)Get to the mIO directory
3)run "mvn clean compile"
4)run "mvn exec:java -D"exec.mainClass"="merc.marcos.barata.App" "
5) now the app is running
----------- Test the App -------------------
I tested the app manually trying my inputs
----------- API ----------------------------
This App consists in a menu that can execute 7 actions:
-List all Vehicles by Model
-List all Vehicles by Fuel Type
-List all Vehicles by Transmission Type
-List all Vehicles by Dealer
-Find closest Dealer for especific Vehicle
-Create new Booking
-Cancel an already existing Booking
This app reads an import JSON file to get/load all the information about the Dealers,Bookings,Vehicles,Availability to the principal class that is Mercedes. This app implements the Command design pattern that gives us the power App class the power to send a command to the Mercedes class, that does all the computational work. All of the interaction between the user and the App is done by using the System.in and System.out. The inputs are read using Scanner and the outputs are shown using print and println.
----------Additional information -------------------
