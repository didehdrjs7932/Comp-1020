public class Car{
    public String Brend;
    private String Model;
    private int year;
    private double mileage;
    public boolean match = true;

    public Car(String Brend, String Model, int year, double mileage ){
        this.Brend = Brend;
        this.Model = Model;
        this.year = year;
        this.mileage = mileage;
    }

    public double getMileage(){
        return mileage;
    }

    public void setMileage(double mileage){
        this.mileage = mileage;
       
    }

    public int getYear(){
        return year;
    }

    public void setYear(int year){
        this.year = year;
        
    }

    public boolean matchMake(String make){
        if(make.equals(Brend)){
            return match = true;
        }
        else{
            return match = false;
        }
            
    }

    public String toString(){
        return "Brend: " + Brend +"Model:" + Model+ "Year: " + year + "Mileage: "+ mileage;
    }
    
}