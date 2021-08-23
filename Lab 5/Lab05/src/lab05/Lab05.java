package lab05;

import java.util.Scanner;
// PROGRAM NAME:    Laboratory 5 - Truck Cost Evaluation
// PROGRAMMER:      Jonah C. Edick
// DATE DUE:        Tuesday, February 4, 2020
// PROGRAM PURPOSE: This program is designed to gather information from
//                  the company user to calculate the total cost of shipping
//                  to a customer. This program also utilizes the use of 
//                  funtions.

public class Lab05 {

    public static void main(String[] args) {
        
        double[] truckDimensions, mileRates, convertedInfo, calcTruckInfo,
                calculatedCosts;
        double conversionFactor;
        String[] customerInfo;
        
        
        //Provide Pricing Information
        
        truckDimensions = SetTruckDimensions();
        
        conversionFactor = SetCubicFactor();
        
        mileRates = SetPerMileRates();
        
        customerInfo = GetCustomerInfo();
        
        convertedInfo = ConvertCustomerInfo(customerInfo[3], customerInfo[4]);
        
        calcTruckInfo = CalculateTruckInfo(convertedInfo[0],
                conversionFactor, truckDimensions[0], truckDimensions[1],
                truckDimensions[2],truckDimensions[3], truckDimensions[4], 
                truckDimensions[5]);
        
        calculatedCosts = CalculateCosts(mileRates[0], mileRates[1],
                calcTruckInfo[2], calcTruckInfo[3], convertedInfo[1]);
        
        DisplayPricingInformation(customerInfo[0], customerInfo[1],
                customerInfo[2], calcTruckInfo[0], calcTruckInfo[1],
                convertedInfo[0], calcTruckInfo[2], calcTruckInfo[3],
                convertedInfo[1], calculatedCosts[0], calculatedCosts[1],
                calculatedCosts[2]);
        
    }
    
    public static double[] SetTruckDimensions(){
        double[] truckDimensions;
        
        truckDimensions = new double[6];
        
        truckDimensions[0] = 42.46;
        truckDimensions[1] = 8.2;
        truckDimensions[2] = 12.4;
        truckDimensions[3] = 19.35;
        truckDimensions[4] = 7.54;
        truckDimensions[5] = 7.6;
        
        return truckDimensions;
    }
    
    public static double SetCubicFactor(){
        double conversionFactor;
        
        conversionFactor = 7.480543;
        
        return conversionFactor; 
    }
    
    public static double[] SetPerMileRates(){
        double[] mileRates;
        
        mileRates = new double[2];
        
        mileRates[0] = 1.398;
        mileRates[1] = 1.018;
        
        return mileRates;
    }
    
    public static String[] GetCustomerInfo(){
        String[] customerInfo;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        customerInfo = new String[5];
        
        System.out.print("Enter customer name: ");
        customerInfo[0] = kbd.nextLine();
        System.out.print("Enter customer street address: ");
        customerInfo[1] = kbd.nextLine();
        System.out.print("Enter customer city/state/zip: ");
        customerInfo[2] = kbd.nextLine();
        System.out.print("Enter amount of gallons: ");
        customerInfo[3] = kbd.nextLine();
        System.out.print("Enter distance travelled: ");
        customerInfo[4] = kbd.nextLine();
        
        return customerInfo;
    }
    
    public static double[] ConvertCustomerInfo(String ammountOfGallons,
            String distanceTraveled){
        double[] convertedInfo;
        
        convertedInfo = new double[2];
        
        convertedInfo[0] = Double.parseDouble(ammountOfGallons);
        convertedInfo[1] = Double.parseDouble(distanceTraveled);
        
        return convertedInfo;
    }
    
    public static double[] CalculateTruckInfo(double amountOfGallons,
            double conversionFactor, double trailerLength,
            double trailerHeight, double trailerWidth, double truckLength,
            double truckHeight, double truckWidth){
        
        double[] truckInfo;
        double trailerRadius, truckRadius, remainingGallons;
        
        truckInfo = new double[4];
        
        trailerRadius = ((trailerHeight + trailerWidth)/2.0)/2.0;
        truckInfo[0] = 3.1416 *(Math.pow(trailerRadius, 2))
                                * trailerLength * conversionFactor;
        truckRadius = ((truckHeight + truckWidth)/2.0)/2.0;
        truckInfo[1] = 3.1416 *(Math.pow(truckRadius, 2))
                                * truckLength * conversionFactor;
        truckInfo[2] = (int)(amountOfGallons / truckInfo[0]);
        remainingGallons = amountOfGallons % truckInfo[0];
        truckInfo[3] = (int)(remainingGallons / truckInfo[1] + 0.999);
        
        return truckInfo;
}
    
    public static double[] CalculateCosts(double trailerMileRate,
            double truckMileRate, double numberOfTrailers,
            double numberOfTrucks, double distanceTravelled){
        
        double[] calcCosts;
        
        calcCosts = new double[3];
        
        calcCosts[0] = numberOfTrailers * 
                (trailerMileRate * distanceTravelled);
        calcCosts[1] = numberOfTrucks * (truckMileRate * distanceTravelled);
        calcCosts[2] = calcCosts[0] + calcCosts[1];
        
        return calcCosts;
    }
    
    public static void DisplayPricingInformation(String customerName,
            String customerStreetAddress, String customerCityStateZip,
            double trailerVolumeGallons, double truckVolumeGallons,
            double amountOfGallons, double numberOfTrailers,
            double numberOfTrucks, double distanceTravelled,
            double trailerCosts, double truckCosts, double totalCosts){
        
        System.out.println(customerName);
        System.out.println(customerStreetAddress);
        System.out.println(customerCityStateZip);
        System.out.println("Trailer volume: " + trailerVolumeGallons);
        System.out.println("Truck Volume: " + truckVolumeGallons);
        System.out.println("Gallons Shipped: " + amountOfGallons);
        System.out.println("Number of trailers: " + numberOfTrailers);
        System.out.println("Number of tucks: " + numberOfTrucks);
        System.out.println("Distance travelled: " + distanceTravelled);
        System.out.println("Cost of trailers: " + trailerCosts);
        System.out.println("Cost of trucks: " + truckCosts);
        System.out.println("Total costs: " + totalCosts);
    }
    
}
