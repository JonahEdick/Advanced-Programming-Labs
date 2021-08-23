package lab08;

import java.io.*;
import java.util.Scanner;

public class Lab08 {


    public static void main(String[] args) throws IOException {
        
        int[] productQuantity;
        double[] productPrice;
        String[] productNames;
        File productFile;
        
        productQuantity = new int[30];
        productPrice = new double[30];
        productNames = new String[30];
        productFile = new File("productFile.txt");
        
        LoadOfferings(productNames, productQuantity, productPrice, productFile);
        
        PerformEachTask(productNames, productQuantity, productPrice);
        
        SaveOfferings(productNames, productQuantity, productPrice, productFile);
        
    }
    
    public static void LoadOfferings(String[] productNames,
                                     int[] productQuantity,
                                     double[] productPrice,
                                     File productFile)throws IOException{
        int cnt;
        Scanner productFileSC;
        
        productFile = new File("productFile.txt");
        productFileSC = new Scanner(productFile);
        
        cnt = 0;
        while(productFileSC.hasNext()){
            productNames[cnt] = productFileSC.nextLine();
            productQuantity[cnt] = productFileSC.nextInt();
            productPrice[cnt] = productFileSC.nextDouble();
            productFileSC.nextLine();
            cnt++;
        }
        
        productFileSC.close();
    }
    
    public static void PerformEachTask(String[] productNames,
                                     int[] productQuantity,
                                     double[] productPrice){
        char taskChoice;
                
        do{
            taskChoice = GetTaskChoice();
            
            PerformTaskChoice(taskChoice, productNames, productQuantity,
                              productPrice);
            
        }while(taskChoice != 'Q');
        
    }
    
    public static void SaveOfferings(String[] productNames,
                                     int[] productQuantity,
                                     double[] productPrice,
                                     File productFile)throws IOException{
        PrintWriter productFilePW;
        
        productFilePW = new PrintWriter(productFile);
        
        for(int cnt = 0; productNames[cnt] != null; cnt++){
            productFilePW.println(productNames[cnt]);
            productFilePW.println(productQuantity[cnt]);
            productFilePW.println(productPrice[cnt]);
        }
        productFilePW.close();
    }
    
    public static char GetTaskChoice(){
        char taskChoice;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        System.out.print("Display (O)ne\n"
                         + "Display (E)ach\n"
                         + "(A)dd Product\n"
                         + "(D)elete Product\n"
                         + "(C)hange Price\n"
                         + "(Q)uit\n"
                         + "Enter Choice: ");
        
        taskChoice = kbd.nextLine().toUpperCase().charAt(0);
        
        System.out.println();
        
        return taskChoice;
    }
    
    public static void PerformTaskChoice(char taskChoice,
                                         String[] productNames,
                                         int[] productQuantity,
                                         double[] productPrice){
        
        switch (taskChoice){
            case 'O': DisplayOneProduct(productNames, productQuantity,
                                        productPrice);
                break;
            case 'E': DisplayEachProduct(productNames, productQuantity,
                                         productPrice);
                break;
            case 'A': AddProduct(productNames, productQuantity, productPrice);
                break;
            case 'D': RemoveProduct(productNames, productQuantity,
                                    productPrice);
                break;
            case 'C': ChangeProductPrice(productNames, productPrice);
                break;
            case'Q' : break;
            default: System.out.println("Please Select Valid Choice");
            
        }
        
        
    }
    
    public static void DisplayOneProduct(String[] productNames,
                                         int[] productQuantity,
                                         double[] productPrice){
        int searchResults;
        String searchValue;
        
        searchValue = GetSearchValue();
        
        searchResults = PerformSearch(searchValue, productNames);
        
        DisplayMatchingItem(searchResults, searchValue, productNames,
                            productQuantity, productPrice);
        
    }

    public static void DisplayEachProduct(String[] productNames,
                                          int[] productQuantity,
                                          double[] productPrice){
        int cnt;
        
        cnt = 0;
        while(productNames[cnt] != null){
            System.out.printf("%s\n%d\n%-10.2f\n",
                              productNames[cnt],
                              productQuantity[cnt],
                              productPrice[cnt]);
            cnt++;
        }
        System.out.println();
    }
    
    public static void AddProduct(String[] productNames, int[] productQuantity,
                                  double[] productPrice){
        int numProducts;
        Scanner kbd;
        
        numProducts = 0;
        while(productNames[numProducts] != null && 
              numProducts < productNames.length)
            numProducts++;
        
        if(numProducts < productNames.length){
            kbd = new Scanner(System.in);
            System.out.print("Enter New Product Name: ");
            productNames[numProducts] = kbd.nextLine();
            System.out.print("Enter New Product Quantity: ");
            productQuantity[numProducts] = kbd.nextInt();
            System.out.print("Enter New Product Price: ");
            productPrice[numProducts] = kbd.nextDouble();
            System.out.println();
        }
        else
            System.out.println("Inventory is full, cannot add new product.");
        
    }
    
    public static void RemoveProduct(String[] productNames,
                                     int[] productQuantity,
                                     double[] productPrice){
        int searchResults;
        String searchValue;
        
        searchValue = GetSearchValue();
        
        searchResults = PerformSearch(searchValue, productNames);
        
        RemoveMatchingProduct(searchResults, searchValue, productNames,
                              productQuantity, productPrice);
        
    }
    
    public static void ChangeProductPrice(String[] productNames,
                                          double[] productPrice){
        
        int searchResults;
        String searchValue;
        
        searchValue = GetSearchValue();
        
        searchResults = PerformSearch(searchValue, productNames);
        
        ChangeMatchingPrice(searchResults, searchValue, productPrice);
        
    }
    
    public static String GetSearchValue(){
        String searchValue;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        System.out.print("Enter Product Name: ");
        searchValue = kbd.nextLine();
        System.out.println();
        
        return searchValue;
    }
       
    public static int PerformSearch(String searchValue, String[] productNames){
        
        int searchResults;
        
        searchResults = productNames.length;
        
        for(int cnt = 0; productNames[cnt] != null; cnt++){
            
            if(productNames[cnt].equals(searchValue))
                searchResults = cnt;
        }
        return searchResults;        
    }
    
    public static void DisplayMatchingItem(int searchResults, String searchValue,
                                           String[] productNames,
                                           int[] productQuantity,
                                           double[] productPrice){
        
        if(searchResults == productNames.length){
            System.out.println(searchValue + " was not found.");
        }
        else{
            System.out.printf("%s\n%d\n%-10.2f\n\n",
                              productNames[searchResults],
                              productQuantity[searchResults],
                              productPrice[searchResults]);
        }
    }
    
    public static void RemoveMatchingProduct(int searchResults,
                                             String searchValue,
                                             String[] productNames,
                                             int[] productQuantity,
                                             double[] productPrice){
        if(searchResults == productNames.length){
            System.out.println(searchValue + " was not found.\n");
        }
        else{
            productNames[searchResults] = null;
            productQuantity[searchResults] = 0;
            productPrice[searchResults] = 0.00;
            
            while(searchResults < (productNames.length - 1)){
                productNames[searchResults] = productNames[searchResults + 1];
                productQuantity[searchResults] = productQuantity
                                                          [searchResults + 1];
                productPrice[searchResults] = productPrice[searchResults + 1];
                searchResults++;
            }
        }
    }
    
    public static void ChangeMatchingPrice(int searchResults,
                                           String searchValue,
                                           double[] productPrice){
        double newPrice;
        Scanner kbd;
        
        
        if(searchResults == productPrice.length){
            System.out.println(searchValue + " was not found.");
        }
        else{
            kbd = new Scanner(System.in);
            do{
                System.out.print("Enter new price: ");
                newPrice = kbd.nextDouble();
                System.out.println();
            }while(newPrice < 0.00);
            
            productPrice[searchResults] = newPrice;
        }
    }
}