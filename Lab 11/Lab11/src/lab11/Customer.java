package lab11;

public class Customer {
    private String name;
    private String street;
    private String cityStateZip;
    private String phone;
    
    public void Set(String uName, String uStreet, String uCityStateZip,
                    String uPhone){
        name = uName;
        street = uStreet;
        cityStateZip = uCityStateZip;
        phone = uPhone;
    }
    
    public String GetName(){
        return name;
    }
    
    public String GetStreet(){
        return street;
    }
    
    public String GetCSZ(){
        return cityStateZip;
    }
    
    public String GetPhone(){
        return phone;
    }
}
