package lab10;

import java.util.Scanner;

public class InventoryRecord {
    private String itemNumber;
    private String itemDescription;
    private double itemCost;
    private double itemPrice;
    private double reorderLevel;
    private int reorderPoint;
    
    public void LoadInventoryData(Scanner inventoryFileSC){
        itemNumber = inventoryFileSC.nextLine();
        itemDescription = inventoryFileSC.nextLine();
        itemCost = inventoryFileSC.nextDouble();
        itemPrice = inventoryFileSC.nextDouble();
        reorderLevel = inventoryFileSC.nextDouble();
        reorderPoint = inventoryFileSC.nextInt();
        inventoryFileSC.nextLine();
    }
    
    public int ComputeAmountToReorder(SaleRecord aSale){
        int amountToReorder;
        
        amountToReorder = (int)((reorderLevel + 1.0) * (reorderPoint) 
                - (aSale.GetQuantityOnHand()));
        
        return amountToReorder;
    }
    
    public double ComputeReorderCost(SaleRecord aSale){
        int amountToReorder;
        double reorderCost;
        
        amountToReorder = ComputeAmountToReorder(aSale);
        
        reorderCost = amountToReorder * itemCost;
        
        return reorderCost;
    }
    
    public String GetItemNumber(){
        return itemNumber;
    }
    
    public String GetItemDescription(){
        return itemDescription;
    }
    
    public double GetItemCost(){
        return itemCost;
    }
    
    public double GetItemPrice(){
        return itemPrice;
    }
    
    public double GetReorderLevel(){
        return reorderLevel;
    }
    
    public int GetReorderPoint(){
        return reorderPoint;
    }
}
