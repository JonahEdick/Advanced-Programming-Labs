package lab09;

import java.io.*;
import java.util.Scanner;

public class TaxPayer {
    private double cat1LowerLimit;
    private double cat2LowerLimit;
    private double cat3LowerLimit;
    private double cat4LowerLimit;
    private double cat1Percent;
    private double cat2Percent;
    private double cat3Percent;
    private double cat4Percent;
    private String name;
    private String address;
    private double houseAssessment;
    
    public void LoadTaxTable()throws IOException{
        File taxRateFile;
        Scanner taxRateFileSC;
        
        taxRateFile = new File("taxRateFile.txt");
        taxRateFileSC = new Scanner(taxRateFile);
        
        cat1LowerLimit = taxRateFileSC.nextDouble();
        cat1Percent = taxRateFileSC.nextDouble();
        
        cat2LowerLimit = taxRateFileSC.nextDouble();
        cat2Percent = taxRateFileSC.nextDouble();
        
        cat3LowerLimit = taxRateFileSC.nextDouble();
        cat3Percent = taxRateFileSC.nextDouble();
        
        cat4LowerLimit = taxRateFileSC.nextDouble();
        cat4Percent = taxRateFileSC.nextDouble();
        
        taxRateFileSC.close();
    }
    
    public void GetTaxpayerData(Scanner taxpayerFileSC){
        name = taxpayerFileSC.nextLine();
        address = taxpayerFileSC.nextLine();
        houseAssessment = taxpayerFileSC.nextDouble();
        taxpayerFileSC.nextLine();
    }
    
    public double DetermineTaxRate(){
        double applicableTaxRate;
        
        applicableTaxRate = 0.00;
        
        if(houseAssessment >= cat1LowerLimit)
            applicableTaxRate = cat1Percent;
        if(houseAssessment >= cat2LowerLimit)
            applicableTaxRate = cat2Percent;
        if(houseAssessment >= cat3LowerLimit)
            applicableTaxRate = cat3Percent;
        if(houseAssessment >= cat4LowerLimit)
            applicableTaxRate = cat4Percent;
        
        return applicableTaxRate;
    }
    
    public double DetermineTaxDue(){
        double taxDue, applicableTaxRate;
        
        applicableTaxRate = DetermineTaxRate();
        
        taxDue = houseAssessment * (applicableTaxRate/100);
        
        return taxDue;
    }
    
    public int IdentifyCategory(){
        int category;
        
        category = 0;
        
        if(houseAssessment >= cat1LowerLimit)
            category = 1;
        if(houseAssessment >= cat2LowerLimit)
            category = 2;
        if(houseAssessment >= cat3LowerLimit)
            category = 3;
        if(houseAssessment >= cat4LowerLimit)
            category = 4;
        
        return category;
    }
    
    public String GetName(){
        return name;
    }
    
    public String GetAddress(){
        return address;
    }
    
    public double GetAssessment(){
        return houseAssessment;
    }
}
