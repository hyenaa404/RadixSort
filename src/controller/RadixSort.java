package controller;

import java.util.Arrays;
import model.Book;

/**
 *
 * @author nhs
 */
public class RadixSort {

    private Book[] a;

    public RadixSort(Book[] array) {
        this.a = array;
    }

    public void sort() {
        radixSort(a.length);
    }

    private int getMax(Book arr[], int n) {
        int mx = arr[0].getValueToSort().length();
        for (int i = 1; i < n; i++)
            if (arr[i].getValueToSort().length() > mx)
                mx = arr[i].getValueToSort().length();
        return mx;
    }

    private void countSort(int n, int exp) {
        Book output[] = new Book[n]; 
        int count[] = new int[256];
        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++) {
            int index = (a[i].getValueToSort().length() - exp - 1 >= 0) ? a[i].getValueToSort().charAt(a[i].getValueToSort().length() - exp - 1) : 0;
            count[index]++;
        }
        
        for (int i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int index = (a[i].getValueToSort().length() - exp - 1 >= 0) ? a[i].getValueToSort().charAt(a[i].getValueToSort().length() - exp - 1) : 0;
            output[count[index] - 1] = a[i];
            count[index]--;
        }

        for (int i = 0; i < n; i++) {
            a[i] = output[i];
        }
    }

    public void radixSort(int n) {
       int m = getMax(a, n);

        for (int exp = 0; exp < m; exp++)
            countSort( n, exp);
    }

    public Book[] getSortedArray() {
        return a;
    }

}
