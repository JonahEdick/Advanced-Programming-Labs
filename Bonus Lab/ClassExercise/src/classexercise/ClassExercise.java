package classexercise;

import java.util.Scanner;
import java.text.DecimalFormat;

// PROGRAM NAME:    Laboratory Exercise - Midterm Average
// PROGRAMMER:      Jonah C. Edick
// DATE DUE:        Tuesday, January 28, 2020
// PROGRAM PURPOSE:
//    This program will calculate the midterm average for a student in
//          CIS 216 in the Spring 2020 semester. (maybe)

public class ClassExercise {

    public static void main(String[] args) {
        
        final double QUIZ_WEIGHT = 0.125;
        final double EXAM_WEIGHT = 0.3125;
        final double HOMEWORK_WEIGHT = 0.125;
        final double LAB_WEIGHT = 0.4375;
        
        
        String studentName;
        double quiz1Grade, quiz2Grade; //quiz grades in percents
        double examGrade; //exam grade as percent
        double homework1Grade, homework2Grade; //homework grades as percents
        double lab1Grade, lab2Grade, lab3Grade, 
                lab4Grade; //lab grades as percents
        
        double quizAverage, homeworkAverage, labAverage, midtermAverage;
        
        Scanner keyboard = new Scanner(System.in);
        DecimalFormat gradeFormat = new DecimalFormat("#0.0");
        
        
        //Get Student Name
        System.out.print("Enter the name of the student:  ");
        studentName = keyboard.nextLine();
        
        //Get Quiz Grades
        System.out.print("Enter quiz 1 grade:  ");
        quiz1Grade = keyboard.nextDouble();
        System.out.print("Enter quiz 2 grade:  ");
        quiz2Grade = keyboard.nextDouble();
        
        //Get Exam Grade
        System.out.print("Enter exam grade:  ");
        examGrade = keyboard.nextDouble();
        
        //Get Homework Grades
        System.out.print("Enter homework 1 grade:  ");
        homework1Grade = keyboard.nextDouble();
        System.out.print("Enter homework 2 grade:  ");
        homework2Grade = keyboard.nextDouble();
        
        //Get Lab Grades
        System.out.print("Enter lab 1 grade:  ");
        lab1Grade = keyboard.nextDouble();
        System.out.print("Enter lab 2 grade:  ");
        lab2Grade = keyboard.nextDouble();
        System.out.print("Enter lab 3 grade:  ");
        lab3Grade = keyboard.nextDouble();
        System.out.print("Enter lab 4 grade:  ");
        lab4Grade = keyboard.nextDouble();
        
        //Compute Quiz Average
        quizAverage = (quiz1Grade + quiz2Grade) / 2.0;
        
        //Compute Homework Average
        homeworkAverage = (homework1Grade + homework2Grade) / 2.0;
        
        //Compute Lab Average
        labAverage = (lab1Grade + lab2Grade + lab3Grade + lab4Grade) / 4.0;
        
        //Compute Midterm Grade
        midtermAverage = quizAverage * QUIZ_WEIGHT + examGrade * EXAM_WEIGHT
                + homeworkAverage * HOMEWORK_WEIGHT + labAverage * LAB_WEIGHT;
        
        //Display Average Summary
        System.out.println("Student:  " + studentName);
        System.out.println("Quiz average  : " +
                gradeFormat.format(quizAverage));
        System.out.println("Exam grade:  " + gradeFormat.format(examGrade));
        System.out.println("Homework average:  " +
                gradeFormat.format(homeworkAverage));
        System.out.println("Lab average:  " +
                gradeFormat.format(labAverage));
        System.out.println("Midterm average:  " +
                gradeFormat.format(midtermAverage));
    }
    
}
