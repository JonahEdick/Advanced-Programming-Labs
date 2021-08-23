package lab07;

import java.util.Scanner;
import java.io.*;

// PROGRAM NAME:    Laboratory Exercise - Meal Order Process (1.2)
// PROGRAMMER:      Jonah C. Edick
// DATE DUE:        Tuesday, March 31, 2020
// PROGRAM PURPOSE: This program is designed to take order information form the
//                  user and process a bill as well as take in and calculate
//                  the change and then save the transaction to a transaction
//                  log for the user. This program should be modified to allow
//                  the user to purchase multiples of an item as well as use
//                  parallel arrays to contain meal information.

public class Lab07 {
    
    public static void main(String[] args) throws IOException{

        double [] entreePrices, drinkPrices, dessertPrices;
        String [] entreeNames, drinkNames, dessertNames;
        
        //Process Restaurant Bill
        
         //Load Meal Pricing Info
        entreePrices = new double [10];
        entreeNames = new String [10];
        drinkPrices = new double [4];
        drinkNames = new String [4];
        dessertPrices = new double [3];
        dessertNames = new String [3];
        
        loadMealPricingInfo(entreePrices, drinkPrices, dessertPrices, 
                            entreeNames, drinkNames, dessertNames);
        
         //Process Each Resturant Customer
        
        processEachOrder(entreePrices, drinkPrices, dessertPrices,
                         entreeNames, drinkNames, dessertNames); 
    }
    
    public static void loadMealPricingInfo(double[] entreePrices, 
                                           double[] drinkPrices, 
                                           double[] dessertPrices,
                                           String[] entreeNames,
                                           String[] drinkNames,
                                           String[] dessertNames)
                                           throws IOException{
        int cnt;
        File mealInfoFile;
        Scanner mealInfoFileSC;
        
        mealInfoFile = new File("mealInfoFile.txt");
        mealInfoFileSC = new Scanner(mealInfoFile);
        
        cnt = 0;
        
        do{
            entreeNames[cnt] = mealInfoFileSC.nextLine();
            entreePrices[cnt] = mealInfoFileSC.nextDouble();
            mealInfoFileSC.nextLine();
            cnt++;
        }while(cnt < 10);
        
        cnt = 0;
        
        do{
            drinkNames[cnt] = mealInfoFileSC.nextLine();
            drinkPrices[cnt] = mealInfoFileSC.nextDouble();
            mealInfoFileSC.nextLine();
            cnt++;
        }while(cnt < 4);
        
        cnt = 0;
        
        do{
            dessertNames[cnt] = mealInfoFileSC.nextLine();
            dessertPrices[cnt] = mealInfoFileSC.nextDouble();
            mealInfoFileSC.nextLine();
            cnt++;
        }while(cnt < 3);
        
        mealInfoFileSC.close();
    }
    
    public static void processEachOrder(double[] entreePrices, 
                                        double[] drinkPrices, 
                                        double[] dessertPrices,
                                        String[] entreeNames,
                                        String[] drinkNames,
                                        String[] dessertNames)
                                        throws IOException{
        
        int changeDue, amountTendered;                                    
        char processCustomer;
        double totalDue;
        Scanner kbd;
        int[] mealOrderInfo, orderQuantities;
        double[] paymentInfo;
        
        kbd = new Scanner(System.in);
        
        processCustomer = 'O';
        
        while(processCustomer != 'N'){
            
            //Set Order Quantity Amounts
            orderQuantities = new int[3];
            
            SetOrderQuantities(orderQuantities);
            
            // Get Customer Meal Order
            mealOrderInfo = new int[3];
            
            getCustomerMealOrder(kbd, mealOrderInfo, entreePrices, drinkPrices,
                                 dessertPrices, entreeNames, drinkNames,
                                 dessertNames, orderQuantities);
            
            //Calculate Order Price
            totalDue = calculateOrderPrice(mealOrderInfo, entreePrices,
                                           drinkPrices, dessertPrices,
                                           orderQuantities);
            
            //Retrieve Due Payment
            paymentInfo = new double[2];
            
            retrieveDuePayment(kbd, totalDue, paymentInfo);         
            
            //Record Resaurant Bill
            recordRestaurantBill(mealOrderInfo, totalDue, paymentInfo,
                                 orderQuantities, entreeNames, drinkNames,
                                 dessertNames);
            
            do{
                System.out.print("Would you like to process another customer "
                                +"(Y/N): ");
                processCustomer = kbd.next().toUpperCase().charAt(0);
                System.out.println();
            }while(processCustomer != 'Y' && processCustomer != 'N');
        }
    }
    
    public static void SetOrderQuantities(int[] orderQuantities){  
        orderQuantities[0] = 0;
        orderQuantities[1] = 0;
        orderQuantities[2] = 0;
    }
    
    public static void getCustomerMealOrder(Scanner kbd, int[] mealOrderInfo,
                                            double[] entreePrices,
                                            double[] drinkPrices, 
                                            double[] dessertPrices,
                                            String[] entreeNames,
                                            String[] drinkNames,
                                            String[] dessertNames,
                                            int[] orderQuantities){
        
        mealOrderInfo[0] = getEntreeOrder(kbd, entreePrices, entreeNames,
                orderQuantities);
        mealOrderInfo[1] = getDrinkOrder(kbd, drinkPrices, drinkNames,
                orderQuantities);
        mealOrderInfo[2] = getDessertOrder(kbd, dessertPrices, dessertNames,
                orderQuantities);
    }
    
    public static double calculateOrderPrice(int [] mealOrderInfo,
                                            double[] entreePrices,
                                            double[] drinkPrices, 
                                            double[] dessertPrices,
                                            int[] orderQuantities){
        double subTotal, totalDue;
        
        //Calculate Sub Total
        subTotal = calculateSubtotal(mealOrderInfo, entreePrices, drinkPrices,
                                dessertPrices, orderQuantities);
        
        //Calculate Total
        totalDue = calculateTotal(subTotal);
        
        return totalDue;  
    }
    
    public static void retrieveDuePayment(Scanner kbd, double totalDue,
                                          double[] paymentInfo){
        paymentInfo[0] = getTenderAmount(kbd, totalDue);
        
        paymentInfo[1] = returnChange(paymentInfo[0], totalDue);
    }
    
    public static void recordRestaurantBill(int [] mealOrderInfo,
                                            double totalDue,
                                            double [] paymentInfo,
                                            int[] orderQuantities,
                                            String[] entreeNames,
                                            String[] drinkNames,
                                            String[] dessertNames)
                                            throws IOException{
        File transactionLog;
        FileWriter transactionLogFW;
        PrintWriter transactionLogPW;
               
        transactionLog = new File("transactionLog.txt");
        transactionLogFW = new FileWriter(transactionLog, true);
        transactionLogPW = new PrintWriter(transactionLogFW);
        
        transactionLogPW.printf("Entree #%35d\n"
                              + "Entree:%36s\n"
                              + "Entree Amount:%29d\n"
                              + "Drink #%36d\n"
                              + "Drink:%37s\n"
                              + "Drink Amount:%30d\n"
                              + "Dessert #%34d\n"
                              + "Dessert:%35s\n"
                              + "Dessert Amount:%28d\n"
                              + "Amount Due: $%30.2f\n"
                              + "Amount Tendered: $%25.2f\n"
                              + "Change Due: $%30.2f\n",
                               mealOrderInfo[0],
                               entreeNames[mealOrderInfo[0] -1],
                               orderQuantities[0],
                               mealOrderInfo[1],
                               drinkNames[mealOrderInfo[1] -1],
                               orderQuantities[1],
                               mealOrderInfo[2],
                               dessertNames[mealOrderInfo[2] -1],
                               orderQuantities[2],
                               + totalDue,
                               paymentInfo[0],
                               paymentInfo[1]);
        
        transactionLogPW.close();
    }
    
    public static int getEntreeOrder(Scanner kbd, double[] entreePrices,
                                     String[] entreeNames,
                                     int[] orderQuantity){
        
        int entreeOrder, cnt;
        
        cnt = 0;
        
        System.out.println("Please select one of the entrees bellow");
        
        do{
            System.out.println((cnt + 1) + ". " + entreeNames[cnt] + ": $" +
                               entreePrices[cnt]);
            cnt++;
        }while(cnt < 10);
        
        do{
            System.out.print("Entree Order # ");
            entreeOrder = kbd.nextInt();    
        }while(entreeOrder <1 || entreeOrder >10);
        
        do{
            System.out.print("Enter Entree Ammount: ");
            orderQuantity[0] = kbd.nextInt();
        }while(orderQuantity[0] < 1);
        
        System.out.println();
        
        return entreeOrder;
    }
    
    public static int getDrinkOrder(Scanner kbd, double[] drinkPrices,
                                    String[] drinkNames,
                                    int[] orderQuantity){
        
        int drinkOrder, cnt;
        
        cnt = 0;
        
        System.out.println("Please select one of the drinks bellow");
        
        do{
            System.out.println((cnt + 1) + ". " + drinkNames[cnt] + ": $" +
                               drinkPrices[cnt]);
            cnt++;
        }while(cnt < 4);
        
        do{
            System.out.print("Drink Order # ");
            drinkOrder = kbd.nextInt();
        }while(drinkOrder <1 || drinkOrder >4);
        
        do{
            System.out.print("Enter Drink Ammount: ");
            orderQuantity[1] = kbd.nextInt();
        }while(orderQuantity[1] < 1);
        
        System.out.println();
        
        return drinkOrder;
    }
    
    public static int getDessertOrder(Scanner kbd, double[] dessertPrices,
                                      String[] dessertNames,
                                      int[] orderQuantity){
        int dessertOrder, cnt;
        
        cnt = 0;
        
        System.out.println("Please select one of the desserts bellow");
        
        do{
            System.out.println((cnt + 1) + ". " + dessertNames[cnt] + ": $" +
                               dessertPrices[cnt]);
            cnt++;   
        }while(cnt < 3);
        
        do{
            System.out.print("Dessert Order # ");
            dessertOrder = kbd.nextInt();
        }while(dessertOrder <1 || dessertOrder >3);
        
        do{
            System.out.print("Enter Dessert Ammount: ");
            orderQuantity[2] = kbd.nextInt();
        }while(orderQuantity[2] < 1);
        
        System.out.println();
        
        return dessertOrder;
    }
    
    public static double calculateSubtotal(int [] mealOrderInfo,
                                            double[] entreePrices,
                                            double[] drinkPrices, 
                                            double[] dessertPrices,
                                            int[] orderQuantities){
        double subTotal;
        
        subTotal = (entreePrices[mealOrderInfo[0]-1] * orderQuantities[0]) 
                 + (drinkPrices[mealOrderInfo[1]-1] * orderQuantities[1])
                 + (dessertPrices[mealOrderInfo[2]-1] * orderQuantities[2]);
        
        return subTotal;
    }
    
    public static double calculateTotal(double subTotal){
        double totalDue, SALES_TAX;
        
        SALES_TAX = 0.08;
        
        totalDue = subTotal * (1 + SALES_TAX);
        
        return totalDue;
    }
    
    public static double getTenderAmount(Scanner kbd, double totalDue){
        double tenderAmount, tenderAdd;
        
        tenderAmount = 0.00;
        
        do{
            tenderAdd = 0.00;
            System.out.printf("Amount Due: $%-6.2f\n",totalDue);
            System.out.print("Tender Amount: $");
            do{
            tenderAdd = kbd.nextDouble();    
            }while(tenderAdd <= 0);
            
            System.out.println();
            totalDue = totalDue - tenderAdd;
            tenderAmount += tenderAdd;
        }while(totalDue > 0) ;
        
        return tenderAmount;
    }
    
    public static double returnChange(double tenderAmount, double totalDue){
        double totalChange;
        
        totalChange = calculateChange(tenderAmount, totalDue);
        
        displayChangeDue(totalChange);
        
        return totalChange;
    }
    
    public static double calculateChange(double tenderAmount, double totalDue){
        double totalChange;
        
        totalChange = tenderAmount - totalDue;
        
        return totalChange;        
    }
    
    public static void displayChangeDue(double changeDue){
        System.out.printf("Your Change is: $%6.2f\n\n",changeDue);
    }  
}