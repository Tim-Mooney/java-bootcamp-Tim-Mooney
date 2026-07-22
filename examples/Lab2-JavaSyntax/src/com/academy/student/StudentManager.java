package com.academy.student;

import java.util.Scanner;

public class StudentManager {

    private static final int MAX_STUDENTS = 20;

    private final Student[] students = new Student[MAX_STUDENTS];
    private int studentCount = 0;
    private final Scanner scanner;

    public StudentManager(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMenu() {
        System.out.println("====================================");
        System.out.println("Student Management System");
        System.out.println("====================================");
        System.out.println("1. Add Student");
        System.out.println("2. Display Students");
        System.out.println("3. Search Student");
        System.out.println("4. Average Marks");
        System.out.println("5. Exit");
        System.out.print("Enter Choice : ");
    }

    public void addStudent(){
        if(studentCount >= MAX_STUDENTS){
            System.out.println("Full"); //check if students is full
        }
        else{
            String choiceInput;
            System.out.print("Enter student ID: ");
            choiceInput = scanner.nextLine().trim();        //get ID
            int ID;
            try {
                ID = Integer.parseInt(choiceInput);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid Input");
                System.out.println("Please Try Again.");
                return;
            }
            if(ID <= 0){ //assuming 0 is not positive
                System.out.println("Student ID must be positive."); //check ID
                return;
            }
            for(Student s : students){
                if(s != null && s.getStudentId() == ID){
                    System.out.println("Student ID is already taken."); //check ID
                    return;
                }
            }
            System.out.print("Enter student name: ");
            choiceInput = scanner.nextLine().trim();
            if (choiceInput.isEmpty()) {
                System.out.println("Student must have a name.");    //get name
                return;
            }
            String name = choiceInput;
            System.out.print("Enter course: ");
            choiceInput = scanner.nextLine().trim();        //get course
            if (choiceInput.isEmpty()) {
                System.out.println("Must enter a course.");
                return;
            }
            String course = choiceInput;
            System.out.print("Enter marks: ");
            choiceInput = scanner.nextLine().trim();        //get marks
            double marks;
            try {
                marks = Double.parseDouble(choiceInput);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid Input");
                System.out.println("Please Try Again.");
                return;
            }
            if(marks < 0 || marks > 100){
                System.out.println("Marks must be between 0 and 100."); //check marks
                return;
            }
            students[studentCount] = new Student(ID, name, course, marks);      //add student
            studentCount++;
            System.out.println("Student added successfully.");

        }
    }

    public void displayStudents(){
        if(studentCount == 0){
            System.out.println("No students to display.");
        }
        else{
            for(int i = 0; i < studentCount - 1; i++){
                System.out.printf("%-8d %-20s %-15s %-8.2f%n",
                        students[i].getStudentId(),
                        students[i].getName(),
                        students[i].getCourse(),
                        students[i].getMarks());
            }
        }
    }

    public void searchStudent(){
        if(studentCount == 0){
            System.out.println("No students to search.");
        }
        else{
            String choiceInput;
            System.out.print("Enter student ID: ");
            choiceInput = scanner.nextLine().trim();        //get ID
            int ID;
            try {
                ID = Integer.parseInt(choiceInput);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid Input");
                System.out.println("Please Try Again.");
                return;
            }
            if(ID <= 0){ //assuming 0 is not positive
                System.out.println("Student ID must be positive."); //check ID
                return;
            }
            for(Student s : students){
                if(s != null && s.getStudentId() == ID){
                    s.display();
                    return;
                }
            }
            System.out.println("Student not found.");
        }
    }

    public void calculateAverage(){
        double avg = 0;
        for(Student s : students){
            if(s != null){
                avg += s.getMarks();
            }
        }
        avg = avg / (double)studentCount;
        System.out.println("Average marks: "+avg);
    }

    // Methods addStudent, displayStudents, searchStudent, calculateAverage
    // will be filled in later steps.
}