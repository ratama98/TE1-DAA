import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class radixsort {
    public static void main(String[] args) throws IOException
    {
        int n = 10000; // Adjust sesuai ukuran dataset
        int[] arr = new int[n];
        int idx = 0;

        // Adjust sesuai nama input file
        String content = new String(Files.readAllBytes(
                                Paths.get("random_10000.txt")));
        String[] values = content.split(" ");
        for (String value : values) {
            int num = Integer.parseInt(value);
            arr[idx++] = num;
        }
        long startTime = System.nanoTime();
        sort(arr, 10000);
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Total Running Time: " + totalTime);
    }

    static void countSort(int arr[], int n, int digit)
    {
        int[] count = new int[10];
        int[] output = new int[n];
    
        for (int i = 0; i < n; i++) {
            int index = (arr[i] / digit) % 10;
            count[index]++;
        }
    
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
    
        for (int i = n - 1; i >= 0; i--) {
            int index = (arr[i] / digit) % 10;
            output[count[index] - 1] = arr[i];
            count[index]--;
        }
    
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }
 
    static void sort(int arr[], int n)
    {
        int m = getMax(arr, n);
        for (int digit = 1; m / digit > 0; digit *= 10)
            countSort(arr, n, digit);
    }

    static int getMax(int arr[], int n)
    {
        int max = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > max)
                max = arr[i];
        return max;
    }
}
