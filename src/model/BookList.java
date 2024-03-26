
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author nhs
 */
public class BookList {

    private Book  bookList [];

    public Book[] getBookList() {
        return bookList;
    }

    public void setBookList(Book[] bookList) {
        this.bookList = bookList;
    }

    

    public void readFile(String filePath) {
        try {
            File file = new File(filePath);
            FileReader fileReader = null;
            BufferedReader bufferedReader = null;
            try {
                fileReader = new FileReader(file);
                bufferedReader = new BufferedReader(fileReader);
                String line = bufferedReader.readLine();
                bookList = new Book[countLines(filePath)];
                int i = 0;
                while (line != null) {
                    line = line.replace("\"", "");
                    String[] parts = line.split(";");
                    String isbn = parts[0];
                    String title = parts[1].trim();
                    String author = parts[2];
                    int yearPublic;
                    try {
                        yearPublic = Integer.parseInt(parts[3].trim());
                    } catch (NumberFormatException e) {
                        yearPublic = 0;
                    }
                    String publisher = parts[4];
                    String imaS = parts[5];
                    String imaM = parts[6];
                    String imaL = parts[7];
                    Book book = new Book(isbn, title, author, yearPublic, publisher, imaS, imaM, imaL);
                    bookList[i] = book;
                    i++;
                    line = bufferedReader.readLine();

                }
                bufferedReader.close();
                fileReader.close();
            } catch (FileNotFoundException ex) {
                System.out.println("File not found.");
            } 
        } catch (IOException e) {
            System.err.println("Failed when reading file.");
        }

    }
    
    

    public void writeFile(String filePath) {
        try {
            File file = new File(filePath);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Book book : bookList) {

                String line = book.toString();
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("Error when reading file!");
        }
        System.out.println("Saved successfully!");
    }
    
    
    private int countLines(String filePath) {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            while (reader.readLine() != null) {
                lines++;
            }
        } catch (IOException e) {
            System.err.println("Failed when counting lines.");
        }
        return lines;
    }
}
