package lab09;

import java.util.Scanner;
import java.io.*;

public class Lab09 {

// PROGRAM NAME:    Laboratory 9 - Taxpayer Program
// PROGRAMMER:      Jonah C. Edick
// DATE DUE:        Tuesday, April 21, 2020
// PROGRAM PURPOSE: This program is designed to use a TAXPAYER class to
//                  calculate how much taxes are due and then accumulates that
//                  info into a final report file that shows detailed info
//                  about all of the taxpayers and the amount of taxes paid.

    public static void main(String[] args)throws IOException{
        double[] totals;
        String taxFileName;
        
        TaxPayer theTaxPayer;
        
        theTaxPayer = new TaxPayer();
        theTaxPayer.LoadTaxTable();
        
        taxFileName = GetTaxFileName();
        
        totals = ProcessEachTaxPayer(theTaxPayer, taxFileName);
        
        DisplayBillSummary(totals);
    }
    
    public static String GetTaxFileName(){
        String tempString, taxFileName;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        System.out.print("Enter Tax File Name (Ignore .txt): ");
        tempString = kbd.nextLine();
        
        taxFileName = tempString + ".txt";
        
        System.out.println("\n\n\n");
        
        return taxFileName;
    }
    
    public static double[] ProcessEachTaxPayer(TaxPayer theTaxPayer,
                                           String taxFileName)
                                           throws IOException{
        double applicableTaxRate;
        double[] totals;
        File taxFile;
        Scanner taxpayerFileSC;
        
        taxFile = new File(taxFileName);
        taxpayerFileSC = new Scanner(taxFile);
        
        totals = new double[5];
        
        do{
        theTaxPayer.GetTaxpayerData(taxpayerFileSC);
        
        UpdateTotals(theTaxPayer, totals);
        
        DisplayTaxpayerBill(theTaxPayer);
        
        }while(taxpayerFileSC.hasNext());
        
        taxpayerFileSC.close();
        
        System.out.println("\n\n");
        
        return totals;
    }
    
    public static void UpdateTotals(TaxPayer theTaxPayer, double[] totals){
        int category;
        double taxDue;
        
        category = theTaxPayer.IdentifyCategory();
        
        taxDue = theTaxPayer.DetermineTaxDue();
        
        switch(category){
            case 1: totals[0]++;
            break;
            case 2: totals[1]++;
            break;
            case 3: totals[2]++;
            break;
            case 4: totals[3]++;
            break;
            default:
            break;
        }
        totals[4] += taxDue; 
    }
    
    public static void DisplayTaxpayerBill(TaxPayer theTaxPayer){
        
        System.out.println("Town of Cicely Alaska\n"
                         + "Property Tax Bill\n");
        System.out.println(theTaxPayer.GetName());
        System.out.println(theTaxPayer.GetAddress());
        System.out.println("Cicely, Alaska 98941\n");
        System.out.printf("Current assessed value: $%-50.2f\n\n",
                          theTaxPayer.GetAssessment());
        System.out.printf("Applicable Rate:   %-6s\n",
                         (theTaxPayer.DetermineTaxRate() + "%"));
        System.out.printf("Property Tax Due: $%-50.2f\n\n\n\n",
                          theTaxPayer.DetermineTaxDue());
    }
    
    public static void DisplayBillSummary(double[] totals){
        
        System.out.println("Town of Cicely Alaska\nProperty Tax Statistics\n");
        
        System.out.printf("Number of assessments below $10,001:%15d\n",
                          ((int)totals[0]));
        System.out.printf("Number of assessments between $10,001-$30,000:%5d\n",
                          ((int)totals[1]));
        System.out.printf("Number of assessments between $30,001-$60,000:%5d\n",
                          ((int)totals[2]));
        System.out.printf("Number of assessments above $60,000:%15d\n",
                          ((int)totals[3]));
        System.out.printf("Total Taxes:%39.2f\n\n", totals[4]);
    }
}