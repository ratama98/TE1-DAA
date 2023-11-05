import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class peeksort {
    public static void main(String[] args) throws IOException
    {
        int n = 100000; // Adjust sesuai ukuran dataset
        int[] arr = new int[n];
        int idx = 0;

        // Adjust sesuai nama input file
        String content = new String(Files.readAllBytes(
                                Paths.get("random_100000.txt")));
        String[] values = content.split(" ");
        for (String value : values) {
            int num = Integer.parseInt(value);
            arr[idx++] = num;
        }

        long startTime = System.nanoTime();
        sort(arr, 0, arr.length - 1);
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Total Running Time: " + totalTime);
    }

    static void print(int arr[], int n)
    {
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
    }

    static void sort(int[] A, int e, int s) {
        if (e == s || s == e) {
            return;
        }

        int m = e + ((s - e) / 2);

        if (m <= e) {
            sort(A, e + 1, s);
            mergeRuns(A, e, m, s);
        } else if (m >= s) {
            sort(A, e, s - 1);
            mergeRuns(A, e, m, s);
        } else {
            int i = extendRunLeft(A, m, e);
            int j = extendRunRight(A, m, s);
            if (i == e && j == s) {
                return;
            }
            if (m - i < j - m) {
                sort(A, e, i - 1);
                sort(A, i, s);
                mergeRuns(A, e, i - 1, s);
            } else {
                sort(A, e, j);
                sort(A, j + 1, s);
                mergeRuns(A, e, j, s);
            }
        }
    }

    private static int extendRunLeft(int[] A, int m, int e) {
        int i = m - 1;
        while (i >= e && A[i] == A[m]) {
            i--;
        }
        return i + 1;
    }

    private static int extendRunRight(int[] A, int m, int s) {
        int j = m + 1;
        while (j <= s && A[j] == A[m]) {
            j++;
        }
        return j - 1;
    }

    private static void mergeRuns(int[] A, int left, int mid, int right) {
        int i = left; // Element pertama subarray kiri
        int j = mid + 1; // Element pertama subarray kanan
        int k = 0; // Pointer untuk indexing temp array

        int[] temp = new int[right - left + 1];

        // Compare element subarray, kemudian add ke temp array
        while (i <= mid && j <= right) {
            if (A[i] <= A[j]) {
                temp[k++] = A[i++];
            } else {
                temp[k++] = A[j++];
            }
        }

        // Copy sisa element array kiri
        while (i <= mid) {
            temp[k++] = A[i++];
        }

        // Copy sisa element array kanan
        while (j <= right) {
            temp[k++] = A[j++];
        }

        for (i = left; i <= right; i++) {
            A[i] = temp[i - left];
        }
    }
}