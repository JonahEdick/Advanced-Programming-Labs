package lab06;

import java.util.Scanner;
import java.io.*;

// PROGRAM NAME:    Laboratory Exercise - Meal Order Process
// PROGRAMMER:      Jonah C. Edick
// DATE DUE:        Tuesday, March 31, 2020
// PROGRAM PURPOSE: This program is designed to take order information form the
//                  user and process a bill as well as take in and calculate
//                  the change and then save the transaction to a transaction
//                  log for the user.
    
public class Lab06 {
    
    public static void main(String[] args) throws IOException{

        double [] entreePrices, drinkPrices, dessertPrices;
        
        //Process Restaurant Bill
        
         //Load Meal Pricing Info
        entreePrices = new double [10];
        drinkPrices = new double [4];
        dessertPrices = new double [3];
        
        loadMealPricingInfo(entreePrices, drinkPrices, dessertPrices);
        
         //Process Each Resturant Customer
        
        processEachOrder(entreePrices, drinkPrices, dessertPrices); 
    }
    
    public static void loadMealPricingInfo(double[] entreePrices, 
                                           double[] drinkPrices, 
                                           double[] dessertPrices)
                                           throws IOException{
        int cnt;
        File mealPriceFile;
        Scanner mealPriceFileSC;
        
        mealPriceFile = new File("mealPriceFile.txt");
        mealPriceFileSC = new Scanner(mealPriceFile);
        
        cnt = 0;
        
        do{
            entreePrices[cnt] = mealPriceFileSC.nextDouble();
            cnt++;
        }while(cnt < 10);
        
        cnt = 0;
        
        do{
            drinkPrices[cnt] = mealPriceFileSC.nextDouble();
            cnt++;
        }while(cnt < 4);
        
        cnt = 0;
        
        do{
            dessertPrices[cnt] = mealPriceFileSC.nextDouble();
            cnt++;
        }while(cnt < 3);
        
        mealPriceFileSC.close();
    }
    
    public static void processEachOrder(double[] entreePrices, 
                                        double[] drinkPrices, 
                                        double[] dessertPrices)
                                        throws IOException{
        
        int changeDue, amountTendered;                                    
        char processCustomer;
        double totalDue;
        Scanner kbd;
        int[] mealOrderInfo;
        double[] paymentInfo;
        
        kbd = new Scanner(System.in);
        
        processCustomer = 'O';
        
        while(processCustomer != 'N'){
            
            //Set Order Quantity Amounts
            
            
            // Get Customer Meal Order
            mealOrderInfo = new int[3];
            
            getCustomerMealOrder(kbd, mealOrderInfo, entreePrices, drinkPrices,
                                    dessertPrices);
            
            //Calculate Order Price
            totalDue = calculateOrderPrice(mealOrderInfo, entreePrices,
                                           drinkPrices, dessertPrices);
            
            //Retrieve Due Payment
            paymentInfo = new double[2];
            
            retrieveDuePayment(kbd, totalDue, paymentInfo);
                        
            
            //Record Resaurant Bill
            recordRestaurantBill(mealOrderInfo, totalDue, paymentInfo);
            
            do{
                System.out.print("Would you like to process another customer "
                                +"(Y/N): ");
                processCustomer = kbd.next().toUpperCase().charAt(0);
                System.out.println();
            }while(processCustomer != 'Y' && processCustomer != 'N');
        }
    }
    
    public static void getCustomerMealOrder(Scanner kbd, int[] mealOrderInfo,
                                            double[] entreePrices,
                                            double[] drinkPrices, 
                                            double[] dessertPrices){
        
        mealOrderInfo[0] = getEntreeOrder(kbd, entreePrices);
        mealOrderInfo[1] = getDrinkOrder(kbd, drinkPrices);
        mealOrderInfo[2] = getDessertOrder(kbd, dessertPrices);
    }
    
    public static double calculateOrderPrice(int [] mealOrderInfo,
                                            double[] entreePrices,
                                            double[] drinkPrices, 
                                            double[] dessertPrices){
        double subTotal, totalDue;
        
        //Calculate Sub Total
        subTotal = calculateSubtotal(mealOrderInfo, entreePrices, drinkPrices,
                                dessertPrices);
        
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
                                            double [] paymentInfo)
                                            throws IOException{
        File transactionLog;
        FileWriter transactionLogFW;
        PrintWriter transactionLogPW;
        
        transactionLog = new File("transactionLog.txt");
        transactionLogFW = new FileWriter(transactionLog, true);
        transactionLogPW = new PrintWriter(transactionLogFW);
        
        transactionLogPW.printf("Entree #%35d\nDrink #%36d\nDessert #%34d\n"
                               + "Amount Due: $%30.2f\nAmount Tendered: $%25.2f"
                               + "\nChange Due: $%30.2f\n", mealOrderInfo[0],
                               mealOrderInfo[1], mealOrderInfo[2], totalDue,
                               paymentInfo[0], paymentInfo[1]);
        
        transactionLogPW.close();
    }
    
    public static int getEntreeOrder(Scanner kbd, double[] entreePrices){
        
        int entreeOrder;
        
        System.out.println("Please select one of the entrees bellow\n"
                         + "1. Burger $" + entreePrices[0] + "\n"
                         + "2. Cheese Burger $" + entreePrices[1] + "\n"
                         + "3. Bacon Burger $" + entreePrices[2] + "\n"
                         + "4. Bacon Cheese Burger $" + entreePrices[3] + "\n"
                         + "5. Double Burger $" + entreePrices[4] + "\n"
                         + "6. Double Cheese Burger $" + entreePrices[5] + "\n"
                         + "7. Double Bacon Burger $" + entreePrices[6] + "\n"
                         + "8. Double Bacon Cheese Burger $" + entreePrices[7]
                         + "\n"
                         + "9. The Defibrillator $" + entreePrices[8] + "\n"
                         + "10. Burger King Foot Lettuce $" + entreePrices[9]);
        
        do{
            System.out.print("Entree Order # ");
            entreeOrder = kbd.nextInt();    
        }while(entreeOrder <1 || entreeOrder >10);
        System.out.println();
        
        return entreeOrder;  
    }
    
    public static int getDrinkOrder(Scanner kbd, double[] drinkPrices){
        
        int drinkOrder;
        
        System.out.println("Please select one of the drinks bellow\n"
                         + "1. Water $" + drinkPrices[0] + "\n"
                         + "2. Pepsi $" + drinkPrices[1] + "\n"
                         + "3. Dr. Pepper $" + drinkPrices[2] + "\n"
                         + "4. The Jar $" + drinkPrices[3]);
        
        do{
            System.out.print("Drink Order # ");
            drinkOrder = kbd.nextInt();
        }while(drinkOrder <1 || drinkOrder >4);
        System.out.println();
        
        return drinkOrder;
    }
    
    public static int getDessertOrder(Scanner kbd, double[] dessertPrices){
        int dessertOrder;
        
        System.out.println("Please select one of the desserts bellow\n"
                         + "1. Chocolate Peanut Butter Pie $" + dessertPrices[0]
                         + "\n"
                         + "2. Oreo Cheesecake $" + dessertPrices[1] + "\n"
                         + "3. Dessert Burger $" + dessertPrices[2]);
        
        do{
            System.out.print("Dessert Order # ");
            dessertOrder = kbd.nextInt();
        }while(dessertOrder <1 || dessertOrder >3);
        System.out.println();
        
        return dessertOrder;
    }
    
    public static double calculateSubtotal(int [] mealOrderInfo,
                                            double[] entreePrices,
                                            double[] drinkPrices, 
                                            double[] dessertPrices){
        double subTotal;
        
        subTotal = entreePrices[mealOrderInfo[0]-1] 
                 + drinkPrices[mealOrderInfo[1]-1]
                 + dessertPrices[mealOrderInfo[2]-1];
        
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
