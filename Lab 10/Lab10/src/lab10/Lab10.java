package lab10;

import java.util.Scanner;
import java.io.*;

public class Lab10 {

// PROGRAM NAME:    Laboratory 10 - Inventory System
// PROGRAMMER:      Jonah C. Edick
// DATE DUE:        Tuesday, April 28, 2020
// PROGRAM PURPOSE: This program is designed to take in items from BOTH an
//                  inventory record and a sales record and determines weather
//                  or not there needs to be a refil in that item. If it sees
//                  that it needs to be restocks, it calculates the restock
//                  value and then logs the reorder request. 
    
    public static void main(String[] args)throws IOException{
        
        DisplayReorderReportHeading();
        
        ProcessEachItem();
    }
    
    public static void ProcessEachItem()throws IOException{
        File inventoryRecordFile, saleRecordFile;
        Scanner inventoryRecordFileSC, saleRecordFileSC;
        InventoryRecord anInventoryRecord;
        SaleRecord aSaleRecord;
        
        
        inventoryRecordFile = new File("inventoryRecordFile.txt");
        inventoryRecordFileSC = new Scanner(inventoryRecordFile);
        
        
        saleRecordFile = new File("saleRecordFile.txt");
        saleRecordFileSC = new Scanner(saleRecordFile);
        
        
        while(inventoryRecordFileSC.hasNext() || saleRecordFileSC.hasNext()){
            
            anInventoryRecord = new InventoryRecord();
            anInventoryRecord.LoadInventoryData(inventoryRecordFileSC);
            
            aSaleRecord = new SaleRecord();
            aSaleRecord.LoadSalesData(saleRecordFileSC);
            
            DisplayItemToReorder(anInventoryRecord, aSaleRecord);
            
            LogReorderPurchase(anInventoryRecord, aSaleRecord);
        }
        
        inventoryRecordFileSC.close();
        saleRecordFileSC.close();
    }
    
    public static void DisplayItemToReorder(InventoryRecord anItem,
                                            SaleRecord aSale){
        int quantityOnHand, reorderPoint;
        
        quantityOnHand = aSale.GetQuantityOnHand();
        reorderPoint = anItem.GetReorderPoint();
                                
        if(quantityOnHand < reorderPoint){
            System.out.printf("%-15s%-30s%16d\n", anItem.GetItemNumber(),
                                                 anItem.GetItemDescription(),
                                        anItem.ComputeAmountToReorder(aSale));
        }
    }
    
    public static void LogReorderPurchase(InventoryRecord anItem,
                                          SaleRecord aSale)throws IOException{
        int quantityOnHand, reorderPoint;
        File reorderLog;
        FileWriter reorderLogFW;
        PrintWriter reorderLogPW;
        
        
        quantityOnHand = aSale.GetQuantityOnHand();
        reorderPoint = anItem.GetReorderPoint();
                                
        if(quantityOnHand < reorderPoint){
            reorderLog = new File("reorderLog.txt");
            reorderLogFW = new FileWriter(reorderLog, true);
            reorderLogPW = new PrintWriter(reorderLogFW);
            
            reorderLogPW.printf("%s\n%s\n%-100.2f\n", anItem.GetItemNumber(),
                                        anItem.ComputeAmountToReorder(aSale),
                                        anItem.ComputeReorderCost(aSale));
            reorderLogPW.close();
        }
    }
    
    public static void DisplayReorderReportHeading(){
        System.out.printf("%36s\n%50s","Edick's Product Shop", "We have what "
                + "could be considered product.\n\n");
        System.out.printf("%-15s%-30s%10s", "Item Number", "Description",
                          "Reorder Quantity\n");
    }
}
