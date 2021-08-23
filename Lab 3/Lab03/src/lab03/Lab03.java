package lab03;

import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;

// PROGRAM NAME:    Laboratory 3 - Mailing File List Manipulation
// PROGRAMMER:      Jonah C. Edick
// DATE DUE:        Tuesday, February 18, 2020
// PROGRAM PURPOSE: This program is designed to perfom a request from a user
//                  that the program will perform in order to manipulate and
//                  retrieve certain properties of a file.

public class Lab03 {

    public static void main(String[] args) throws IOException {
        
        char usserPrefferedChoice;
        
        String personName, personAddress, personCity, personState, personZip,
                personPhoneNumber, personEMail, personSearchName, tempString,
                usserPrefferedChoiceS;
        
        Scanner kbd, mailFileSC;
        
        File mailFile;
        
        PrintWriter mailFilePW;
        
        FileWriter mailFileFW;
        
        mailFile = new File("mailFile.txt");
                
        kbd = new Scanner(System.in);
        
        usserPrefferedChoice = 'O';
        
    //Modify Mailing File
        
        //Get User Preferred Choice
        while(usserPrefferedChoice != 'Q')
        {
            
            usserPrefferedChoiceS = JOptionPane.showInputDialog
                                ("What would you like to do?"
                                 + "\n(A)dd Person"
                                 + "\n(L)ookup Person"
                                 + "\n(D)isplay All People in File"
                                 + "\n(Q)uit");
            usserPrefferedChoice = 
                    usserPrefferedChoiceS.toUpperCase().charAt(0);
            
            //Perform User Choice
            if(usserPrefferedChoice == 'A')
            {
                //Add Person to Mailing File
                personName =JOptionPane.showInputDialog
                                ("Enter Person's Name: ");
                personName = personName.toUpperCase();
                personAddress =JOptionPane.showInputDialog
                                ("Enter Person's Address: ");
                personAddress = personAddress.toUpperCase();
                personCity =JOptionPane.showInputDialog
                                ("Enter Person's City: ");
                personCity = personCity.toUpperCase();
                personState =JOptionPane.showInputDialog
                                ("Enter Person's State: ");
                personState = personState.toUpperCase();
                personZip =JOptionPane.showInputDialog
                                ("Enter Person's Zip Code: ");
                personZip = personZip.toUpperCase();
                personPhoneNumber =JOptionPane.showInputDialog
                                ("Enter Person's Phone Number: ");
                personPhoneNumber = personPhoneNumber.toUpperCase();
                personEMail =JOptionPane.showInputDialog
                                ("Enter Person's e-mail: ");
                personEMail = personEMail.toUpperCase();
                
                mailFileFW = new FileWriter(mailFile, true);
                mailFilePW = new PrintWriter(mailFileFW);
                
                mailFilePW.println(personName);
                mailFilePW.println(personAddress);
                mailFilePW.println(personCity);
                mailFilePW.println(personState);
                mailFilePW.println(personZip);
                mailFilePW.println(personPhoneNumber);
                mailFilePW.println(personEMail);
                
                mailFilePW.close();
            }
            if(usserPrefferedChoice == 'L')
            {
                //Lookup Person from Mailing List
                mailFileSC = new Scanner(mailFile);
                
                personSearchName = JOptionPane.showInputDialog
                                    ("Enter person's name: ");
                personSearchName = personSearchName.toUpperCase();
                
                tempString = "\n";
                
                while(mailFileSC.hasNext())
                {
                personName = mailFileSC.nextLine();
                personAddress = mailFileSC.nextLine();
                personCity = mailFileSC.nextLine();
                personState = mailFileSC.nextLine();
                personZip = mailFileSC.nextLine();
                personPhoneNumber = mailFileSC.nextLine();
                personEMail = mailFileSC.nextLine();
                
                    if(personSearchName.compareTo(personName) == 0)
                    {
                    tempString = tempString + personName + "\n" + personAddress
                             + "\n" + personCity + "\n" + personState + "\n" + 
                             personZip + "\n" + personPhoneNumber + "\n" + 
                             personEMail + "\n\n";
                    }
                }                
                if(tempString.compareTo("\n") == 0)
                    {
                        JOptionPane.showMessageDialog(null, "No users found"
                                + "under that name.");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, tempString);
                    }
                mailFileSC.close();
            }
            if(usserPrefferedChoice == 'D')
            {
                //Display Entire List to User
                mailFileSC = new Scanner(mailFile);
                tempString = "\n";
                
                while(mailFileSC.hasNext()){
                personName = mailFileSC.nextLine();
                personAddress = mailFileSC.nextLine();
                personCity = mailFileSC.nextLine();
                personState = mailFileSC.nextLine();
                personZip = mailFileSC.nextLine();
                personPhoneNumber = mailFileSC.nextLine();
                personEMail = mailFileSC.nextLine();
                tempString = tempString + personName + "\n" + personAddress
                             + "\n" + personCity + "\n" + personState + "\n" + 
                             personZip + "\n" + personPhoneNumber + "\n" + 
                             personEMail + "\n\n";
            }
                JOptionPane.showMessageDialog(null, tempString);
                mailFileSC.close();
            }
            
        }
        System.exit(0);
    }
}
