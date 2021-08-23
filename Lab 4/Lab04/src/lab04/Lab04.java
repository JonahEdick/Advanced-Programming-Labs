package lab04;

import java.io.*;
import java.util.Scanner;

// PROGRAM NAME:    Laboratory 4 - Bank Account Report System
// PROGRAMMER:      Jonah C. Edick
// DATE DUE:        Tuesday, March 10, 2020
// PROGRAM PURPOSE: This is program is designed to keep track of a banking
//                  account as well as all the transactions that happen to it
//                  in the current month, the program should be able to keep
//                  track of the current balance at all points as well as 
//                  it should be able to start a new month when the user wants

public class Lab04 {
    
    public static void main(String[] args) throws IOException{
        double startingBalance, currentBalance, transactionAmounts,
                transactionAmount;
        
        char transactionType, userChoice;
        
        String bankName, bankAddress, bankCityStateZip, bankSlogan,
                customerName, customerAddress, customerCity, customerState,
                customerZip, transactionDetails, transactionDate;
        
        File reportFile;
        
        PrintWriter reportFilePW;
        
        FileWriter reportFileFW;
        
        Scanner kbd, reportFileSC;
        
        final double LOW_BAL_FEE = 1.50;
        final double ATM_FEE = 4.50;
        final double TRANSFER_FEE = 0.75;
        
        kbd = new Scanner(System.in);
        
        reportFile = new File("reportFile.txt");
        
        reportFileSC = new Scanner(reportFile);

        //Get Opening Banking Info from File
        
        bankName = reportFileSC.nextLine();
        bankAddress = reportFileSC.nextLine();
        bankCityStateZip = reportFileSC.nextLine();
        bankSlogan = reportFileSC.nextLine();
        customerName = reportFileSC.nextLine();
        customerAddress = reportFileSC.nextLine();
        customerCity = reportFileSC.nextLine();
        customerState = reportFileSC.nextLine();
        customerZip = reportFileSC.nextLine();
        startingBalance = reportFileSC.nextDouble();
        reportFileSC.nextLine();

        
        //Calculate Current Balance
        
        transactionAmounts = 0.00;
        
        while(reportFileSC.hasNext()){
            reportFileSC.nextLine();
            transactionType = reportFileSC.nextLine().charAt(0);
            reportFileSC.nextLine();
            transactionAmount = reportFileSC.nextDouble();
            reportFileSC.nextLine();
            
            switch(transactionType){
                case 'D': transactionAmounts = transactionAmounts
                        + transactionAmount; break;
                case 'A': transactionAmounts = transactionAmounts
                        - transactionAmount; break;
                case 'T': transactionAmounts = transactionAmounts
                        - transactionAmount; break;
                case 'B': transactionAmounts = transactionAmounts
                        - transactionAmount; break;
                default: break;
            }
        }
        
        reportFileSC.close();
        currentBalance = startingBalance + transactionAmounts;
        
        //Process Each User Task
        
        userChoice = 'O';
        
        while(userChoice != 'Q'){
            //Get User Choice
            System.out.println("How can we help you today?\n"
                             + "(A)dd Transaction\n"
                             + "(P)rint Monthly Statement\n"
                             + "(D)isplay Current Balance\n"
                             + "(S)tart New Month\n"
                             + "(Q)uit");
            
            userChoice = kbd.nextLine().toUpperCase().charAt(0);
            
            switch(userChoice){
                case 'A': 
                
                //Get Transaction Info    
                    
                reportFileFW = new FileWriter("reportFile.txt", true);
                
                reportFilePW = new PrintWriter(reportFileFW);
                
                System.out.println("\nWhat Transaction Are You Doing?\n"
                                 + "(D)eposite\n"
                                 + "(C)heck\n"
                                 + "(W)ithdrawal\n"
                                 + "(T)ransfer");
                transactionType = kbd.nextLine().toUpperCase().charAt(0);
                
                System.out.print("\nHow much will be in your transaction: ");
                transactionAmount = kbd.nextDouble();
                kbd.nextLine();
                
                System.out.println("\nPlease Put the Details of Your "
                                 + "Transaction");
                transactionDetails = kbd.nextLine();

                System.out.print("\nToday's Date: ");
                transactionDate = kbd.nextLine();

                //Perform Transaction
                
                switch(transactionType){
                    
                    //Deposit
                    case 'D':                    
                    currentBalance += transactionAmount;
                    
                    reportFilePW.println(transactionDetails);
                    reportFilePW.println(transactionType);
                    reportFilePW.println(transactionDate);
                    reportFilePW.println(transactionAmount);
                        break;
                    
                    //Check    
                    case 'C':
                    currentBalance -= transactionAmount;
                    
                    reportFilePW.println(transactionDetails);
                    reportFilePW.println(transactionType);
                    reportFilePW.println(transactionDate);
                    reportFilePW.println(transactionAmount);
                        break;
                    
                        
                    //ATM Withdrawal    
                    case 'W':
                    currentBalance -= transactionAmount;
                    
                    reportFilePW.println(transactionDetails);
                    reportFilePW.println(transactionType);
                    reportFilePW.println(transactionDate);
                    reportFilePW.println(transactionAmount);
                    
                    reportFilePW.println("ATM Withdrawal Fee.");
                    reportFilePW.println('B');
                    reportFilePW.println(transactionDate);
                    reportFilePW.println(ATM_FEE);
                    currentBalance -= ATM_FEE;
                        break;
                    
                    //Transfer    
                    case 'T':
                    currentBalance -= transactionAmount;
                    
                    reportFilePW.println(transactionDetails);
                    reportFilePW.println(transactionType);
                    reportFilePW.println(transactionDate);
                    reportFilePW.println(transactionAmount);
                    
                    reportFilePW.println("Transfer Fee.");
                    reportFilePW.println('B');
                    reportFilePW.println(transactionDate);
                    reportFilePW.println(TRANSFER_FEE);
                    currentBalance -= TRANSFER_FEE;
                        break;
                    default:
                        break;
                }
                
                if(currentBalance < 250.00){
                    reportFilePW.println("Low Balance Fee.");
                    reportFilePW.println('B');
                    reportFilePW.println(transactionDate);
                    reportFilePW.println(LOW_BAL_FEE);
                    currentBalance -= LOW_BAL_FEE;
                }
                
                System.out.println("\n");
                reportFilePW.close();
                break;
                
                //Print Monthly Report
                
                case 'P': 
                reportFileSC = new Scanner(reportFile);
                System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                bankName = reportFileSC.nextLine();
                System.out.printf("%20s\n", bankName);
                bankAddress = reportFileSC.nextLine();
                System.out.printf("%23s\n", bankAddress);
                bankCityStateZip = reportFileSC.nextLine();
                System.out.printf("%25s\n", bankCityStateZip);
                bankSlogan = reportFileSC.nextLine();
                System.out.printf("%s\n", bankSlogan);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                customerName = reportFileSC.nextLine();
                System.out.printf("Name: %25s\n", customerName);
                customerAddress = reportFileSC.nextLine();
                System.out.printf("Address: %22s\n", customerAddress);
                customerCity = reportFileSC.nextLine();
                System.out.printf("City: %25s\n", customerCity);
                customerState = reportFileSC.nextLine();
                System.out.printf("State: %24s\n", customerState);
                customerZip = reportFileSC.nextLine();
                System.out.printf("Zip: %26s\n", customerZip);
                startingBalance = reportFileSC.nextDouble();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.printf("Starting Bal: $%-16.2f\n",
                                    startingBalance);
                reportFileSC.nextLine();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                
                while(reportFileSC.hasNext()){
                    transactionDetails = reportFileSC.nextLine();
                    System.out.printf("%-31s\n",transactionDetails);
                    transactionType = reportFileSC.nextLine().charAt(0);
                    System.out.printf("Transaction Type: %13c\n",
                                        transactionType);
                    transactionDate = reportFileSC.nextLine();
                    System.out.printf("Date: %25s\n", transactionDate);
                    transactionAmount = reportFileSC.nextDouble();
                    System.out.printf("Amount: %23.2f\n", transactionAmount);
                    reportFileSC.nextLine();
                }
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    System.out.printf("Current Bal: $%-17.2f\n",
                                        currentBalance);
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
                    
                    reportFileSC.close();
                break;
                
                //Display Current Balance
                
                case 'D':
                    
                    System.out.printf("\nCurrent Balance: $%-30.2f\n\n",
                                        currentBalance);
                    
                break;
                
                //Start New Month
                
                case 'S':
                reportFileFW = new FileWriter("reportFile.txt", false);
                
                reportFilePW = new PrintWriter(reportFileFW);
                
                reportFilePW.println(bankName);
                reportFilePW.println(bankAddress);
                reportFilePW.println(bankCityStateZip);
                reportFilePW.println(bankSlogan);
                reportFilePW.println(customerName);
                reportFilePW.println(customerAddress);
                reportFilePW.println(customerCity);
                reportFilePW.println(customerState);
                reportFilePW.println(customerZip);
                reportFilePW.println(currentBalance);
                
                reportFilePW.close();
                
                System.out.println("\n");
                
                break;
                
                case 'Q': System.out.println("\n\nThank You,"
                                            + " Have a Great Day!");
                break;
                
                default: System.out.println("\nPlease Put a Valid Input.\n");
                break;
            }
        }
    }
}
