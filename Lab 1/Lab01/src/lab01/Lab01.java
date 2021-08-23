package lab01;

import java.util.Scanner;

// PROGRAM NAME:    Laboratory 1 - Truck Cost Evaluation
// PROGRAMMER:      Jonah C. Edick
// DATE DUE:        Tuesday, February 4, 2020
// PROGRAM PURPOSE: This program is designed to gather information from
//                  the company user to calculate the total cost of shipping
//                  to a customer

public class Lab01 {

    public static void main(String[] args) {

        double trailerLength, trailerHeight, trailerWidth, truckLength,
                truckHeight, truckWidth, conversionFactor, trailerMileRate,
                truckMileRate, amountOfGallons, distanceTravelled,
                trailerRadius, trailerVolumeGallons, truckRadius,
                truckVolumeGallons, remainingGallons, trailerCosts, truckCosts,
                totalCosts;
        
        int numberOfTrailers, numberOfTrucks;
        
        String customerName, customerStreetAddress, customerCityStateZip;
        
        Scanner keyboard = new Scanner(System.in);
        
        //Set Dimensions of Each Truck
        trailerLength = 42.46;
        trailerHeight = 8.2;
        trailerWidth = 12.4;
        truckLength = 19.35;
        truckHeight = 7.54;
        truckWidth = 7.6;
        
        //Set Cubic Feet to Volume Factor
        conversionFactor = 7.480543;
        
        //Set Per Mile Rates for Each Truck
        trailerMileRate = 1.398;
        truckMileRate = 1.018;
        
        //Get Customer Information
        System.out.print("Enter customer name: ");
        customerName = keyboard.nextLine();
        System.out.print("Enter customer street address: ");
        customerStreetAddress = keyboard.nextLine();
        System.out.print("Enter customer city/state/zip: ");
        customerCityStateZip = keyboard.nextLine();
        System.out.print("Enter amount of gallons: ");
        amountOfGallons = keyboard.nextDouble();
        System.out.print("Enter distance travelled: ");
        distanceTravelled = keyboard.nextDouble();
        
        //Calculate Number of Each Truck Needed
        trailerRadius = ((trailerHeight + trailerWidth)/2.0)/2.0;
        trailerVolumeGallons = 3.1416 *(Math.pow(trailerRadius, 2))
                                * trailerLength * conversionFactor;
        truckRadius = ((truckHeight + truckWidth)/2.0)/2.0;
        truckVolumeGallons = 3.1416 *(Math.pow(truckRadius, 2))
                                * truckLength * conversionFactor;
        numberOfTrailers = (int)(amountOfGallons / trailerVolumeGallons);
        remainingGallons = amountOfGallons % trailerVolumeGallons;
        numberOfTrucks = (int)(remainingGallons / truckVolumeGallons + 0.999);
        
        //Calculate Costs
        trailerCosts = numberOfTrailers * (trailerMileRate * distanceTravelled);
        truckCosts = numberOfTrucks * (truckMileRate * distanceTravelled);
        totalCosts = trailerCosts + truckCosts;
        
        //Display Pricing Information
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
