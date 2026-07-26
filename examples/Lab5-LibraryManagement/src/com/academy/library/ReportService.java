package com.academy.library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;


public class ReportService {

    private final LibraryService libraryService;

    public ReportService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public void displaySummaryReport() {

        int totalBooks = libraryService.getBooks().size();
        int borrowedBooks = 0;
        int availableBooks = 0;
        int totalMembers = libraryService.getMembers().size();

        for(Book book : libraryService.getBooks()){
            if(book.isAvailable()){
                availableBooks++;
            }
            else{
                borrowedBooks++;
            }
        }
        System.out.println("Books: " + totalBooks);
        System.out.println("Borrowed: " + borrowedBooks);
        System.out.println("Available: " + availableBooks);
        System.out.println("Members: " + totalMembers);
        System.out.println("Most popular category: " + findMostPopularCategory());

    }

    public Path exportReportToFile(String fileName) throws IOException {
        int totalBooks = libraryService.getBooks().size();
        int borrowedBooks = 0;
        int availableBooks = 0;
        int totalMembers = libraryService.getMembers().size();

        for(Book book : libraryService.getBooks()){
            if(book.isAvailable()){
                availableBooks++;
            }
            else{
                borrowedBooks++;
            }
        }

        String report = "Books: " + totalBooks + "\nBorrowed: " + borrowedBooks +
                "\nAvailable: " + availableBooks + "\nMembers: " + totalMembers +
                "\nMost popular category: " + findMostPopularCategory();

        Path path = Path.of(fileName);
        Files.writeString(path, report);
        return path;
    }

    private String findMostPopularCategory() {

        if(libraryService.getBooks().size() == 0){
            return  "N/A";
        }
        int max = -1;
        String category = "";
        TreeMap<String, Integer> categoryBookCount = libraryService.getCategoryBookCount();
        for(Map.Entry<String,Integer> cat : categoryBookCount.entrySet()){
            if(cat.getValue() > max){
                max = cat.getValue();
                category = cat.getKey();
            }
        }
        return category;
    }
}