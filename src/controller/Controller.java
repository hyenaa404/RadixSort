
package controller;

import model.Book;
import model.BookList;

/**
 *
 * @author nhs
 */
public class Controller {
    public static void main(String[] args) {
        BookList bl = new BookList();
        bl.readFile("books.csv");
        Book [] bookArray = bl.getBookList();
        RadixSort radixSort = new RadixSort(bookArray);
        radixSort.sort();
        Book[] sortedArray = radixSort.getSortedArray();
        bl.setBookList(sortedArray);
        bl.writeFile("sorted_book.csv");
    }
}
