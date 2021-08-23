package lab11;

import java.util.Scanner;

public class Pie {
    private String name;
    private double price;
    private int quantity;
    
    public void Load(Scanner pieFileSC){
        name = pieFileSC.nextLine();
        price = pieFileSC.nextDouble();
        pieFileSC.nextLine();
    }
    
    public String GetPieName(){
        return name;
    }
    
    public double GetPrice(){
        return price;
    }
    
    public int GetQuantity(){
        return quantity;
    }
    
    public void IncreaseQuantity(int addedQuantity){
        quantity += addedQuantity;
    }
    
    public double GetExtendedPrice(){
        double extendedPrice;
        
        extendedPrice = quantity * price;
        
        return extendedPrice;
    }
    
    public void Reset(){
        quantity = 0;
    }
    
}
