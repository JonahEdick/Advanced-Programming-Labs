package lab02;

import java.util.Scanner;
import java.io.*;
import java.text.DecimalFormat;

// PROGRAM NAME:    Laboratory 2 - Pool Chip Volume & Cost
// PROGRAMMER:      Jonah C. Edick
// DATE DUE:        Tuesday, February 11, 2020
// PROGRAM PURPOSE: This program is designed to gather information from
//                  the user as well as data from a file to help calculate how
//                  many bags of marble the user needs to buy for his pool.

public class Lab02 {

    public static void main(String[] args) throws IOException {
        
        double poolDiameter, chipWidth, chipInnerDepth, chipOuterDepth,
               chipBagCost, chipBagVolume, salesTax, poolRadius, totalRadius,
               upperHeight, cylinderVolume, coneVolume, upperChipVolume,
               lowerVolume, lowerPoolVolume, lowerChipVolume, totalChipVolume,
               totalBags, subTotalCost, totalCost;
        
        Scanner kbd, chipFileSC;
        
        File chipFile;
        
        kbd = new Scanner(System.in);
        
        chipFile = new File("chipFile.txt");
        
        // Get Pool Data From User
        
        System.out.print("Enter Pool Diameter in Feet: ");
        poolDiameter = kbd.nextDouble();
        
        // Get Chip Width and Depth from User
        
        System.out.print("Enter Chip Width in Feet: ");
        chipWidth = kbd.nextDouble();
        System.out.print("Enter Chip Inner Depth in Feet: ");
        chipInnerDepth = kbd.nextDouble();
        System.out.print("Enter Chip Outer Depth in Feet: ");
        chipOuterDepth = kbd.nextDouble();
        
        // Get Chip Data from File
        
        chipFileSC = new Scanner(chipFile);
        
        chipBagCost = chipFileSC.nextDouble();
        chipBagVolume = chipFileSC.nextDouble();
        salesTax = chipFileSC.nextDouble();
        
        chipFileSC.close();
        
        // Calculate Volume
        
        poolRadius = poolDiameter / 2;
        totalRadius = poolRadius + chipWidth;
        upperHeight = chipInnerDepth - chipOuterDepth;
        cylinderVolume = Math.PI * (Math.pow(poolRadius, 2)) * upperHeight;
        coneVolume = (1.0/3.0) * Math.PI * (Math.pow(totalRadius, 2) 
                        + totalRadius + poolRadius + (Math.pow(poolRadius, 2)));
        upperChipVolume = coneVolume - cylinderVolume;
        lowerVolume = Math.PI * Math.pow(totalRadius, 2) * chipOuterDepth;
        lowerPoolVolume = Math.PI * Math.pow(poolRadius, 2) * chipOuterDepth;
        lowerChipVolume = lowerVolume - lowerPoolVolume;
        totalChipVolume = upperChipVolume + lowerChipVolume;
        
        // Calculate Chip Sales
        
        totalBags = ((int)((totalChipVolume / chipBagVolume) + 0.999));
        subTotalCost = totalBags * chipBagCost;
        totalCost = subTotalCost * (1 + salesTax);
        
        // Display Results to User
        DecimalFormat dollarFormat = new DecimalFormat("#0.00");
        
        
        System.out.println("\nNumber of Bags Needed: " + (int)totalBags 
                            + " bags");
        System.out.println("Overal Costs: $" + dollarFormat.format(totalCost));
        
    }
}
