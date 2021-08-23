package lab11;

import java.util.Scanner;
import java.io.*;

public class Lab11 {

// PROGRAM NAME:    Laboratory 11 - Pie Sales System
// PROGRAMMER:      Jonah C. Edick
// DATE DUE:        Tuesday, May 5, 2020
// PROGRAM PURPOSE: This application is a Point of Sale System that gathers
//                  information regarding the pies available and then processes
//                  a customer's sale. The program can recieve any amount of
//                  orders from any amount of customers. This application uses
//                  a Pie object array and a Customer object to process an
//                  pass infromation wherever necessary.
    
    public static void main(String[] args)throws IOException {
        Pie[] thePies;
        
        thePies = new Pie[10];
        
        for(int cnt = 0; cnt < thePies.length; cnt++){
            thePies[cnt] = new Pie();
        }
        
        LoadEachPieInformation(thePies);
        
        ProcessEachCustomer(thePies);
    }
    
    public static void LoadEachPieInformation(Pie[] thePies)throws IOException{
        File pieFile;
        Scanner pieFileSC;
        
        pieFile = new File("pieFile.txt");
        pieFileSC = new Scanner(pieFile);
        
        for(int cnt = 0; cnt < 10; cnt++){
            thePies[cnt].Load(pieFileSC);
        }
        
        pieFileSC.close();
    }
    
    public static void ProcessEachCustomer(Pie[] thePies)throws IOException{
        char again;
        Scanner kbd;
        Customer theCustomer;
        
        kbd = new Scanner(System.in);
        
        do{
            theCustomer = new Customer();
            
            GetCustomerInformation(theCustomer);
            
            ResetPieQuantities(thePies);
            
            ProcessEachPiePurchase(thePies);
            
            DisplayBill(thePies, theCustomer);
            
            StoreSummary(theCustomer, thePies);
            
            do{
               System.out.println("Would you like to order again(Y/N)");
               again = kbd.nextLine().toUpperCase().charAt(0);
            }while(again != 'Y' && again != 'N');
            
        }while(again == 'Y');
        
        
    }
    
    public static void GetCustomerInformation(Customer theCustomer){
        String uName, uStreet, uCityStateZip, uPhone;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        System.out.println("Enter the Following Information.");
        System.out.print("Name: ");
        uName = kbd.nextLine();
        System.out.print("Street: ");
        uStreet = kbd.nextLine();
        System.out.print("City/State/Zip: ");
        uCityStateZip = kbd.nextLine();
        System.out.print("Phone #: ");
        uPhone = kbd.nextLine();
        System.out.println();
        
        theCustomer.Set(uName, uStreet, uCityStateZip, uPhone);
    }
    
    public static void ResetPieQuantities(Pie[] thePies){
        
        for(int cnt = 0; cnt < 10; cnt++){
            thePies[cnt].Reset();
        }
        
    }
    
    public static void ProcessEachPiePurchase(Pie[] thePies){
        int pieNumber, addedQuantity;
        char morePies;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        System.out.println("Here are our available pies and prices.");
        for(int cnt = 0; cnt < 10; cnt++){
            System.out.println((cnt + 1) + ". " + thePies[cnt].GetPieName() 
                              + " (" + thePies[cnt].GetPrice() + ")");
        }
        
        System.out.println();
        
        do{
            pieNumber = GetValidPieNumber();
            
            addedQuantity = GetValidPieQuantity();
            
            thePies[pieNumber].IncreaseQuantity(addedQuantity);
            
            do{
               System.out.println("Would you like to order more pies(Y/N)");
               morePies = kbd.nextLine().toUpperCase().charAt(0);
            }while(morePies != 'Y' && morePies != 'N');
            
            System.out.println();
            
        }while(morePies == 'Y');
        
    }
    
    public static int GetValidPieNumber(){
        int pieNumber;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        do{
            System.out.print("Enter Desired Pie #: ");
            pieNumber = kbd.nextInt();
            pieNumber -= 1;
        }while(pieNumber < 0 || pieNumber > 9);
        
        System.out.println();
        
        return pieNumber;
    }
    
    public static int GetValidPieQuantity(){
        int pieQuantity;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        do{
            System.out.print("Enter Desired Pie Quantity: ");
            pieQuantity = kbd.nextInt();
        }while(pieQuantity < 1);
        
        System.out.println();
            
        return pieQuantity;
    }
    
    public static void DisplayBill(Pie[] thePies, Customer theCustomer){
        double totalDue;
        
        DisplayHeading(theCustomer);
        
        totalDue = 0.00;
        totalDue = ProcessEachPurchase(thePies, totalDue);
        
        DisplayTotalDue(totalDue);
    }
    
    public static void DisplayHeading(Customer theCustomer){
        
        System.out.println("*Customer information*");
        System.out.println("Name: " + theCustomer.GetName());
        System.out.println("Street: " + theCustomer.GetStreet());
        System.out.println("CSZ: " + theCustomer.GetCSZ());
        System.out.println("Phone #: " + theCustomer.GetPhone());
        System.out.println("*Pie Orders*");
        System.out.printf("%-20s%15s%15s%25s\n",
                          "Pie Name", "Pie Quantity",
                          "Pie Price", "Pie Extended Price");
        
    }
    
    public static double ProcessEachPurchase(Pie[] thePies,
                                             double totalDue){
        int quantity;
        for(int cnt = 0; cnt < 10; cnt++){
            quantity = thePies[cnt].GetQuantity();
            
            if(quantity > 0){
                DisplayOrderedPie(thePies[cnt]);
                
                totalDue = UpdateTotalDue(thePies[cnt], totalDue);
            }
        }
        return totalDue;
    } 
    
    public static void DisplayOrderedPie(Pie givenPie){
        System.out.printf("%-20s%15d%15.2f%25.2f\n",
                          givenPie.GetPieName(), givenPie.GetQuantity(),
                          givenPie.GetPrice(), givenPie.GetExtendedPrice());
    }
    
    public static double UpdateTotalDue(Pie givenPie, double totalDue){
        
        totalDue += givenPie.GetExtendedPrice();
        
        return totalDue;
    }
    
    public static void DisplayTotalDue(double totalDue){
        System.out.printf("Total Due: $%-100.2f\n\n", totalDue);
    }
    
    public static void StoreSummary(Customer theCustomer, Pie[] thePies)
                                    throws IOException{
        
        StoreCustomerInformation(theCustomer);
        
        StoreEachOrderedPieInformation(thePies);
    }
    
    public static void StoreCustomerInformation(Customer theCustomer)
                                                throws IOException{
        File storeFile;
        FileWriter storeFileFW;
        PrintWriter storeFilePW;
        
        storeFile = new File("storeFile.txt");
        storeFileFW = new FileWriter(storeFile, true);
        storeFilePW = new PrintWriter(storeFileFW);
        
        storeFilePW.println(theCustomer.GetName());
        storeFilePW.println(theCustomer.GetStreet());
        storeFilePW.println(theCustomer.GetCSZ());
        storeFilePW.println(theCustomer.GetPhone());
        
        storeFilePW.close();
    }
    
    public static void StoreEachOrderedPieInformation(Pie[] thePies)
                                                      throws IOException{
        int quantity;
        File storeFile;
        FileWriter storeFileFW;
        PrintWriter storeFilePW;
        
        storeFile = new File("storeFile.txt");
        storeFileFW = new FileWriter(storeFile, true);
        storeFilePW = new PrintWriter(storeFileFW);
        
        for(int cnt = 0; cnt < 10; cnt++){
            quantity = thePies[cnt].GetQuantity();
            if(quantity > 0){
                storeFilePW.println(thePies[cnt].GetPieName());
                storeFilePW.println(thePies[cnt].GetQuantity());
            }
        }
        
        storeFilePW.println();
        
        storeFilePW.close();
    }
    
}
