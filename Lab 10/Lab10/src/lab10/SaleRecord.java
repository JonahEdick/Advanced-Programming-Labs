package lab10;

import java.util.Scanner;

public class SaleRecord {
    private String itemNumber;
    private int quantityOnHand;
    
    public String GetItemNumber(){
        return itemNumber;
    }
    
    public int GetQuantityOnHand(){
        return quantityOnHand;
    }
    
    public void LoadSalesData(Scanner saleFileSC){
        itemNumber = saleFileSC.nextLine();
        quantityOnHand = saleFileSC.nextInt();
        saleFileSC.nextLine();
    }
}
