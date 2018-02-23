# Mercedes-Benz.io - SINFO Test Drive Challenge API (backend)

### Description:

We, at **Mercedes-Benz.io**, build products for our customers.

These products go from the site that sells our vehicles to the applications that control them.

One of the web applications that we built for our customers is **Test Drive**. It allows a customer to book a test drive on a vehicle and dealer of his choice, simplifying the whole process for him.

Taking that into account, we challenge you to try to build a similar service. By creating a Test Drive (TD) backend application, your challenge is to consider that clients want to book test drives and you ought to match them with the available vehicles on Mercedes-Benz dealers.

On the business side, it's simple. Usually, customers choose a vehicle with a given set of attributes (fuel type, transmission, etc) and then they choose a retailer near them, booking the vehicle for an available time slot.

We provided you with sample data to make your task easier. The sample data contains a collection of dealers, each dealer contains a set of vehicles that are available on his dealership, where each vehicle presents its own attributes (eg: fuel type, transmission type and days of the week that it's available for a test drive).

### Data Structure:

A file in JSON format is provided and this data should be loaded at application startup. Below is a description of it:

###### Root:

| Field     | Format     | Description |
| :-------- | :--------- | :---------- |
| dealers   | List       | List of available dealers |
| bookings  |  List      | List of bookings made |

###### Dealer:

| Field     | Format     | Description |
| :-------- | :--------- | :---------- |
| id | String | Unique identifier for a dealer |
| name  | String | Name of the Dealer |
| latitude | Number | Latitude of dealer's location  |
| longitude | Number | Longitude of dealer's location  |
| vehicles   | List  | List of vehicles in that dealer  |

###### Vehicle:

| Field     | Format     | Description |
| :-------- | :--------- | :---------- |
| id | String | Unique identifier for a vehicle |
| model  | String  | Vehicle model (eg: 'A-Class', 'C-Class', 'E-Class', 'S-Class')  |
|fuel   | String  | Fuel type of the vehicle (eg: 'petrol', 'diesel', 'hybrid')  |
| transmission   | String  | Vehicle transmission type (eg: 'manual', 'automatic')  |
| availability   | List  | List with the hours available for booking by weekday. This represents the general availability of a vehicle and is not changed when a booking is made.  |

###### Availability:

| Field     | Format     | Description |
| :-------- | :--------- | :---------- |
| day | String | Day of the week when the booking should be available (eg: monday, tuesday) |
| timeslots | List[String] | List of slots in a day where the car is available for a booking  |

###### Booking:

| Field     | Format     | Description |
| :-------- | :--------- | :---------- |
| id | String | Unique identifier for a booking |
| vehicleId | String | Vehicle identifier |
| firstName | String   | Customer's first name  |
| lastName | String   | Customer's last name  |
| pickupDate   | Datetime  | Day and time of the booking  |  
| createdAt   | Datetime  | Day and Time that the booking entry was created  |  
| cancelledAt   | Datetime  | Day and Time that this booking was cancelled  |  
| cancelledReason   | Text  | Reason for the booking cancelation |  


###### Sample of provided JSON (data source):
```
{
  "dealers": [
    {
      "id": "D0001",
      "name": "Mercedes-Benz Lisbon Retail",
      "latitude": 38.748347,
      "longitude": -9.148398,
      "vehicles": [
        {
          "id": "D0001C1",
          "model": "A-Class",
          "fuel": "petrol",
          "transmission": "manual",
          "availability": [
            {
              "day": "monday",
              "timeslots": [
                "0900",
                "0930",
                "1030"
              ]
            },
            {
              "day": "tuesday",
              "timeslots": [
                "0830",
                "0930",
                "1030",
                "1100"
              ]
            }
          ]
        }
      ]
    }
  ],
  "bookings": [
    {
      "id": "B0001",
      "vehicleId": "D0001C1",
      "firstName": "John",
      "lastName": "Doe",
      "pickupDate": "2018-01-06T10:30:00",
      "createdAt": "2018-01-05T00:30:00",
      "cancelledAt": null,
      "cancelledReason": null
    }
  ]
}
```


### The API:

We expect that you build a Restful API that should implement the following features:

- List all vehicles by:
    - Model
    - Fuel type
    - Transmission
    - Dealer
- Find the closest dealer according to my location that has a vehicle with specific attributes (Model, Fuel Type, Transmission) [returns zero or one dealer]
- Create a new booking (Once a booking is made you can't double book a vehicle for that time slot)
- Cancel a booking (giving a reason for cancelation)


##### API improvements:

- Find the dealers sorted by distance according to my location that have a vehicle with specific attributes (Model, Fuel Type, Transmission) [returns any number of dealers]
- Find the dealers inside a given polygon that have a vehicle with specific attributes (Model, Fuel Type, Transmission) [returns any number of dealers]

### Implementation Requirements:

- At MB.io our stack is Java and maven for dependency management but you are free to choose any open source language/tool you want.
- Load provided data (dealers, vehicles and existing bookings) at application startup.
- Your application should not depend on any external datasource (eg: databases, servers, etc.). Everything should run in memory.
- You are free to use any framework you think appropriate.
- Implement Unit Tests (we don't expect 100% coverage, use your best judgement).
- Submit a README file with your solution, with the following content:
    - How to build and run your application
    - How to run your tests
    - API documentation
    - Explanation about your choices, their limitations and possible improvements
    - Any additional information you may think is relevant

## Questions regarding the challenge

If you have any questions regarding the challenge or find a bug with the exercise, please use GitHub issues.

To talk with us and other developers about the API [open a support ticket](https://github.com/mercedesbenzio/SINFO-backend-challenge/issues) or mail us at `goncalo.sequeira@mercedes-benz.io` if you need to talk to us.
