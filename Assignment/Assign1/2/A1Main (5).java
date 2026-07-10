import java.util.Scanner;

//main class
public class A1Main {
 
   public static String code;
   public static String city;
   public static int fee;
   public static String name;
   public static String citizenCountry;
   public static int year;

   public static final int YEAR = 2026;

  static Airport ap = new Airport();
  static Flight ft = new Flight();
  static Passenger ps = new Passenger();

 // #1
   public static Airport createAirport(String code, String city, String country, int fee){
      Airport ap = new Airport();
      ap.code = code;
      ap.city = city;
      ap.country = country;
      ap.fee = fee;
      return ap;
  }
  
  // #2
   public static Flight createFlight(String code, Airport depart, Airport arrive){
      Flight ft = new Flight();
      ft.code = code;
      ft.departure = depart;
      ft.arrival = arrive;
      return ft;
  }
  
  // #3
   public static Passenger createPassenger(String name, String citizenCountry, int year){
      Passenger ps = new Passenger();
      ps.passenger = name;
      ps.citizenCountry = citizenCountry;
      ps.birthYear = year;
      return ps;
  }
   
   // #4
   public static Airport[] createAirports(String[] code, String[] city, String[] country, int[] fee){

    Airport[] airports = new Airport[code.length];

    for(int i = 0; i < airports.length; i++){
        airports[i] = new Airport();
        airports[i].code = code[i];
        airports[i].city = city[i];
        airports[i].country = country[i];
        airports[i].fee = fee[i];
    }
     return airports;
  }

  public static Airport findAirportCode(String code,Airport[] airports){
      for(int i = 0; i < airports.length; i++){
          if(code.equals(airports[i].code)){
              return airports[i];
          }
      }
      return null;
  }

  public static Airport findAirportCity(String city, Airport[] airports){
       
       for(int i = 0; i < airports.length; i++){
          if(city.equals(airports[i].city)){
              return airports[i];
          }
      }
      return null;
  }
  
  // #5 
   public static Flight[] createFlights(String[] code, String[] departCode, String[] arriveCode, Airport[] airports){
      Flight[] flights  = new Flight[code.length];
   
   for(int i = 0; i < flights.length; i++){

       Airport depart = findAirportCode(departCode[i], airports);
       Airport arrive = findAirportCode(arriveCode[i], airports);
   
       if(depart == null || arrive == null){ 
       return null; }
      
       flights[i] = new Flight();
       flights[i].code = code[i];
       flights[i].departure = depart;
       flights[i].arrival = arrive; 
 
   }
   return flights;
  }

  // #6
  public static Flight findFlightCode(String code, Flight[] flights){
      

      for(int i = 0; i < flights.length; i++){
          if(code.equals(flights[i].code)){
              return flights[i];
          }
      }
      return null;
  }

  // #7
   public static Passenger findPassenger(String passenger, Passenger[] passengers, int size){
    
     for (int i = 0; i < size; i++) {
        
        if (passengers[i] != null && passenger.equalsIgnoreCase(passengers[i].passenger)) {
            
            return passengers[i];
        }
    }
    return null;

  }
  
  // #8
  public static int addPassenger(Passenger passenger, Passenger[] passengers, int size){
    
     if (size >= passengers.length) {
        return size; 
    }

    if (findPassenger(passenger.passenger, passengers, size) != null) {
        return size;
    }

    passengers[size] = passenger;
    
    return size + 1;
 }

 public static void printPassengerNames(Passenger[] passengers, int size) {
   
    for (int i = 0; i < size; i++) {
        if (passengers[i] != null) {
            System.out.println(passengers[i].passenger);
        }
    }
}

  // #9
  public static Ticket createTicket(Flight flight, int price){
      Ticket t = new Ticket();
      t.flight = flight;
      t.price = price;
      return t;
  }

  // #10
  public static Ticket[] createTickets(String[] flightCodes, int[] prices, Flight[] flights){
      Ticket[] tickets = new Ticket[flightCodes.length];

      for(int i = 0; i < tickets.length; i++){
          Flight f = findFlightCode(flightCodes[i], flights);
          tickets[i] = createTicket(f, prices[i]);
      }
      return tickets;
  }

  // #11
  public static int findAvailableDepartureTickets(Airport depart, Ticket[] in, int inSize, Ticket[] out){
      int size = 0;

      for(int i = 0; i < inSize; i++){
          if(size >= out.length){
              break;
          }
          if(in[i].flight.departure == depart && !in[i].isPurchased()){
              out[size] = in[i];
              size++;
          }
      }
      return size;
  }

  // #12
  public static int findAvailableArrivalTickets(Airport arrive, Ticket[] in, int inSize, Ticket[] out){
      int size = 0;

      for(int i = 0; i < inSize; i++){
          if(size >= out.length){
              break;
          }
          if(in[i].flight.arrival == arrive && !in[i].isPurchased()){
              out[size] = in[i];
              size++;
          }
      }
      return size;
  }

  // #13
  public static Ticket findCheapestTicket(Ticket[] in, int inSize){
      Ticket cheapest = null;

      for(int i = 0; i < inSize; i++){
          if(cheapest == null || in[i].price < cheapest.price){
              cheapest = in[i];
          }
      }
      return cheapest;
  }

  // #14
  public static void printTickets(Passenger passenger, Ticket[] tickets){

      for(int i = 0; i < tickets.length; i++){
          
          Ticket t = tickets[i];

          if(t.passenger == passenger){

              String tag = "";

              if(t.flight.isInternational()){
                  tag = "international";
              }
              else if(t.flight.departure.country.equalsIgnoreCase(passenger.citizenCountry)){
                  tag = "domestic";
              }

              System.out.println(t.getDepartureCity() + " to " + t.getArrivalCity() + "  $" + t.getFinalPrice() + " " + tag);
          }
      }
  }

  public static void main(String[] args){

      Scanner scan = new Scanner(System.in);

      Airport[] airports = createAirports(A1Data.AIRPORT_CODES, A1Data.AIRPORT_CITIES, A1Data.AIRPORT_COUNTRIES, A1Data.AIRPORT_FEES);
      Flight[] flights = createFlights(A1Data.FLIGHT_CODES, A1Data.FLIGHT_DEPART_CODES, A1Data.FLIGHT_ARRIVE_CODES, airports);
      Ticket[] tickets = createTickets(A1Data.TICKET_FLIGHT_CODES, A1Data.TICKET_PRICES, flights);

      Passenger[] passengers = new Passenger[100];
      int passengerSize = 0;

      for(int i = 0; i < A1Data.PASSENGER_NAMES.length; i++){
          Passenger p = createPassenger(A1Data.PASSENGER_NAMES[i], A1Data.PASSENGER_CITIZENSHIPS[i], A1Data.PASSENGER_YEARS[i]);
          passengerSize = addPassenger(p, passengers, passengerSize);
      }

      while(true){

          printPassengerNames(passengers, passengerSize);

          System.out.print("Enter passenger name: ");
          String nameInput = scan.nextLine();

          if(nameInput.isBlank()){
              System.out.println("End of processing");
              break;
          }

          Passenger chosen = findPassenger(nameInput, passengers, passengerSize);

          if(chosen == null){

              System.out.print("Enter citizenship: ");
              String citizenshipInput = scan.nextLine();

              System.out.print("Enter year of birth: ");
              String yearInput = scan.nextLine();

              int yearNum = -1;
              boolean validYear = true;

              try{
                  yearNum = Integer.parseInt(yearInput);
              }
              catch(NumberFormatException e){
                  validYear = false;
              }

              if(citizenshipInput.isBlank() || !validYear || yearNum < YEAR - 100 || yearNum > YEAR){
                  System.out.println("Invalid citizenship or year of birth");
                  continue;
              }

              chosen = createPassenger(nameInput, citizenshipInput, yearNum);
              passengerSize = addPassenger(chosen, passengers, passengerSize);
          }

          printTickets(chosen, tickets);

          System.out.print("Enter departure city: ");
          String departCity = scan.nextLine();

          if(departCity.isBlank()){
              continue;
          }

          Airport departAirport = findAirportCity(departCity, airports);
          Ticket[] departTickets = new Ticket[tickets.length];
          int departSize = 0;

          if(departAirport != null){
              departSize = findAvailableDepartureTickets(departAirport, tickets, tickets.length, departTickets);
          }

          if(departAirport == null || departSize == 0){
              System.out.println("No tickets available from that city");
              continue;
          }

          System.out.print("Enter arrival city: ");
          String arriveCity = scan.nextLine();

          if(arriveCity.isBlank()){
              continue;
          }

          Airport arriveAirport = findAirportCity(arriveCity, airports);
          Ticket[] arriveTickets = new Ticket[departSize];
          int arriveSize = 0;

          if(arriveAirport != null){
              arriveSize = findAvailableArrivalTickets(arriveAirport, departTickets, departSize, arriveTickets);
          }

          if(arriveAirport == null || arriveSize == 0){
              System.out.println("No tickets available to that city");
              continue;
          }

          Ticket cheapest = findCheapestTicket(arriveTickets, arriveSize);
          cheapest.purchaseTicket(chosen);

          printTickets(chosen, tickets);
      }

      scan.close();
  }

}

// Flight Class
class Flight{
    
    public String code;
    public Airport departure;
    public Airport arrival;
    
   boolean isInternational(){

       return !departure.country.equals(arrival.country);
}

   public String toString(){
      
      return "Flight " + code + " from " + departure.toString() + " to " + arrival.toString();
}

}

class Airport{
    
    public int fee;
    public boolean codematches = true;
    public String code;
    public String country;
    public String city;
    public boolean countryMatches = true;
    public boolean cityMatches = true;

   void setFee(int newfee){
       
       if(newfee > fee){
         
          this.fee = newfee;
     }
        else{
        
         this.fee = fee;
     }
 }

 boolean matchesCode(String code){
   
     if(code.equals(this.code)){
       
        this.codematches = true;
        
        return true;
     }
     else {
         
         this.codematches = false;
         
         return false;
     }
 }  
  boolean matchesCountry(String country){
      
      if(country.equals(this.country)){
        
        this.countryMatches = true;
        
        return true;
     }
       else {
          
          this.countryMatches = false;
         
         return false;
     }
 }  
 boolean matchesCity(String city){
     
     if(city.equals(this.city)){
        
        this.cityMatches = true;
        
        return true;
     }
     else {
       
         this.cityMatches = false;
        
         return false;
     }
 }  

  public String toString(){
      
      return code + " (" + city + ", " + country + ") fee: " + fee;
}

}

// Passenger Class
class Passenger{
    
    public String passenger;
    public String citizenCountry;
    public int birthYear;

    boolean isMinor(int currentYear){
        
        if((currentYear - birthYear) < 18 ){
            return true;
        }
        else{
            return false;
        }

    }

    boolean matchesName(String passenger){
        
        if(passenger.equalsIgnoreCase(this.passenger)){
            return true;
        }
        else{
            return false;
        }
    }

    public String toString(){
        
        return passenger + " (" + citizenCountry + "), born " + birthYear;
}

}

// Ticket Class
class Ticket{
    
    public Flight flight;
    public Passenger passenger;
    public int price;

   int getFinalPrice(){
     
       int finalPrice = price;

       if(passenger != null && passenger.isMinor(A1Main.YEAR)){
          
          finalPrice = finalPrice - (finalPrice * 20 / 100);
     }

       finalPrice = finalPrice + flight.arrival.fee;

       return finalPrice;
 }

   boolean isPurchased(){
      
       if(passenger != null){
           return true;
    }
       
       else{
           return false;
    }
}

   void purchaseTicket(Passenger passenger){
       
       if(this.passenger == null){
           
           this.passenger = passenger;
    }
}

   boolean matchesPassengerName(String name){
      
       if(passenger != null && name.equalsIgnoreCase(passenger.passenger)){
           return true;
    }
       else{
           return false;
    }
}

   String getDepartureCity(){
       
       return flight.departure.city;
}

    String getArrivalCity(){
       
       return flight.arrival.city;
}

   public String toString(){
       
       if(isPurchased()){
           return flight.toString() + " price " + price + " purchased by " + passenger.passenger;
    }
       else{
           return flight.toString() + " price " + price + " not purchased";
    }
}

}