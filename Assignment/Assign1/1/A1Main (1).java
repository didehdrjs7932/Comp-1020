      //main class
    public class A1Main {
 
      public static String code;
      public static String city;
      public static int fee;
      public static String name;
      public static String citizenCountry;
      public static int year;

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
       return null;
        }
      
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

   // Airport Class
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