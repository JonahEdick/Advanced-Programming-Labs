package lab12;

import java.util.Scanner;
import java.io.*;

public class Lab12 {

    public static void main(String[] args)throws IOException{
        int numJobs;
        String[] technicianInfo;
        RepairJob[] techJobs;
        
        
        technicianInfo = new String[2];
        
        technicianInfo = GetTechnicianInformation();
        
        techJobs = new RepairJob[100];
        
        for(int cnt = 0; cnt < techJobs.length; cnt++)
            techJobs[cnt] = new RepairJob();
        
        numJobs = AddEachRepairJob(techJobs);
        
        DisplayJobs(techJobs, numJobs);
        
        ModifyJobs(techJobs, numJobs);
        
        DisplayJobs(techJobs, numJobs);
        
        numJobs = RemoveRepairJobs(techJobs, numJobs);
        
        LogRepairJob(techJobs, numJobs, technicianInfo);
        
        DisplaySummaryReport(techJobs, numJobs, technicianInfo);
    }
    
    public static String[] GetTechnicianInformation(){
        String[] technicianInfo;
        Scanner kbd;
        
        technicianInfo = new String[2];
        
        kbd = new Scanner(System.in);
        
        System.out.print("Enter name: ");
        technicianInfo[0] = kbd.nextLine();
        System.out.println();
        
        System.out.print("Enter Pay-Rate: ");
        technicianInfo[1] = kbd.nextLine();
        System.out.println();
        
        return technicianInfo;
    }
    
    public static int AddEachRepairJob(RepairJob[] techJobs){
        int numJobs;
        char another;
        String givenName, givenDate;
        double givenHours;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        numJobs = 0;
        
        do{
            System.out.println("Enter Repair Job Info Below");
            System.out.print("Job Name: ");
            givenName = kbd.nextLine();
            
            System.out.print("Job Date: ");
            givenDate = kbd.nextLine();
            
            System.out.print("Hours of Job: ");
            givenHours = kbd.nextDouble();
            System.out.println();
            
            techJobs[numJobs].Set(givenName, givenDate, givenHours);
            
            numJobs++;
                        
            System.out.print("Process Another Job (Y/N): ");
            another = kbd.next().toUpperCase().charAt(0);
            kbd.nextLine();
            System.out.println();
            
        }while(another == 'Y' && numJobs < techJobs.length);
        
        return numJobs;
    }
    
    public static void DisplayJobs(RepairJob[] techJobs, int numJobs){
        
        System.out.printf("%-45s%10s%8s\n", "Job Name", "Date", "Hours");
        
        for(int cnt = 0; cnt < numJobs; cnt++){
            System.out.printf("%-45s%10s%8.2f\n", techJobs[cnt].GetName(),
                                                techJobs[cnt].GetDate(),
                                                techJobs[cnt].GetHours());
        }
    }
    
    public static void ModifyJobs(RepairJob[] techJobs, int numJobs){
        int loc;
        char another;
        String searchValue;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        System.out.println("\nModify A Job");
        
        do{
            searchValue = GetSearchValue();
            
            loc = PerformSearch(searchValue, techJobs, numJobs);
            
            ModifyFoundJob(techJobs, numJobs, loc);
            
            System.out.print("Modify Another Job (Y/N)");
            another = kbd.next().toUpperCase().charAt(0);
            System.out.println();
        }while(another == 'Y');
        
    }
    
    public static String GetSearchValue(){
        String searchValue;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        System.out.print("Enter Job To Find: ");
        searchValue = kbd.nextLine();
        System.out.println();
        
        return searchValue;        
    }
    
    public static int PerformSearch(String searchValue, RepairJob[] techJobs,
                                    int numJobs){
        int loc;
        
        loc = 0;
        
        while(loc < numJobs &&
                searchValue.compareTo(techJobs[loc].GetName()) != 0){
            loc++;
        }
        return loc;        
    }
    
    public static void ModifyFoundJob(RepairJob[] techJobs, int numJobs,
                                      int loc){
        double givenHours;
        String givenName, givenDate;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        if(loc == numJobs)
        System.out.println("Job could not be found\n");
        else{
            if(loc < numJobs){
                System.out.println("Enter Repair Job Info Below");
                System.out.print("Job Name: ");
                givenName = kbd.nextLine();
            
                System.out.print("Job Date: ");
                givenDate = kbd.nextLine();
            
                System.out.print("Hours of Job: ");
                givenHours = kbd.nextDouble();
                System.out.println();
            
                techJobs[loc].Set(givenName, givenDate, givenHours);  
            }
        }
        
    }
    
    public static int RemoveRepairJobs(RepairJob[] techJobs, int numJobs){
        int loc;
        char another;
        String searchValue;
        Scanner kbd;
        
        kbd = new Scanner(System.in);
        
        
        System.out.println("\nRemove A Job");
        
        do{
            searchValue = GetSearchValue();
            
            loc = PerformSearch(searchValue, techJobs, numJobs);
            
            numJobs = DeleteFoundJob(techJobs, numJobs, loc);
            
            System.out.print("Delete Another Job (Y/N): ");
            another = kbd.next().toUpperCase().charAt(0);
            System.out.println();
        }while(another == 'Y');
        
        return numJobs;
    }
    
    public static int DeleteFoundJob(RepairJob[] techJobs, int numJobs,
                                     int loc){
        
        if(loc == numJobs)
            System.out.println("Job Not Found\n");
        else{
            while(loc < numJobs){
                techJobs[loc].Set(techJobs[loc+1].GetName(),
                                  techJobs[loc+1].GetDate(),
                                  techJobs[loc+1].GetHours());
                loc++;
            }
            numJobs--;
        }
        return numJobs;
    }
    
    public static void LogRepairJob(RepairJob[] techJobs, int numJobs,
                                    String[] technicianInfo)throws IOException{
        File logFile;
        FileWriter logFileFW;
        PrintWriter logFilePW;
        
        logFile = new File(technicianInfo[0] + ".txt");
        logFileFW = new FileWriter(logFile, true);
        logFilePW = new PrintWriter(logFileFW);
        
        for(int cnt = 0; cnt < numJobs; cnt++){
            logFilePW.println(techJobs[cnt].GetName());
            logFilePW.println(techJobs[cnt].GetDate());
            logFilePW.println(techJobs[cnt].GetHours());
        }
        
        logFilePW.close();
    }
    
    public static void DisplaySummaryReport(RepairJob[] techJobs, int numJobs,
                                            String[] technicianInfo){
        double[] totals;
        
        totals = new double[2];
        
        DisplayReportHeading(technicianInfo);
        
        totals = ProcessEachJob(techJobs, numJobs, technicianInfo);
        
        DisplayTotals(totals);
        
    }
    
    public static void DisplayReportHeading(String[] technicianInfo){
        System.out.println("                     Edick's Tech Support\n");
        System.out.printf("Technician: %10s\nPay-Rate: %16s\n\n",
                           technicianInfo[0], technicianInfo[1]);
        System.out.printf("%-45s%10s%8s%10s\n", "Job Name", "Date", "Hours",
                                                "Amount Due");
    }
    
    public static double[] ProcessEachJob(RepairJob[] techJobs, int numJobs,
                                        String[] technicianInfo){
        double[] totals;
        
        totals = new double[2];
        
        totals[0] = 0;
        totals[1] = 0;
        
        for(int cnt = 0; cnt < numJobs; cnt++){
            UpdateTotals(totals, techJobs, cnt, technicianInfo);
            
            DisplayJobSummary(techJobs, cnt, technicianInfo);
        }
        return totals;
    }
    
    public static void UpdateTotals(double[] totals, RepairJob[] techJobs,
                                    int cnt, String[] technicianInfo){
        
        totals[0] += techJobs[cnt].GetHours();
        
        totals[1] += techJobs[cnt].GetAmountDue(
                                   Double.parseDouble(technicianInfo[1]));
        
    }
    
    public static void DisplayJobSummary(RepairJob[] techJobs, int cnt,
                                         String[] technicianInfo){
        System.out.printf("%-45s%10s%8.2f%10.2f\n", techJobs[cnt].GetName(),
                                                    techJobs[cnt].GetDate(),
                                                    techJobs[cnt].GetHours(),
                                                    techJobs[cnt].GetAmountDue(
                                       Double.parseDouble(technicianInfo[1])));
    }
    
    public static void DisplayTotals(double[] totals){
        
        System.out.printf("Totals Hours: %15.2f\n", totals[0]);
        System.out.printf("Totals Amount Due: %10.2f\n", totals[1]);
    }
}
