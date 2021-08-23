package lab12;

public class RepairJob {
    private String name;
    private String date;
    private double hours;
    
    public void Set(String givenName, String givenDate, double givenHours){
        name = givenName;
        date = givenDate;
        hours = givenHours;
    }
    
    public String GetName(){
        return name;
    }
    
    public String GetDate(){
        return date;
    }
    
    public double GetHours(){
        return hours;
    }
    
    public double GetAmountDue(double payrate){
        double amountDue;
        
        amountDue = hours * payrate;
        
        return amountDue;
    }
}
