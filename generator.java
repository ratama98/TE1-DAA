import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class generator {

    public static void main(String[] args) {
        ArrayList<Integer> numbers = generateSortedNumbers(100000);
        saveToFile(numbers, "sorted_100000.txt");

        numbers = generateReverseNumbers(100000);
        saveToFile(numbers, "reverse_100000.txt");

        numbers = generateRandomNumbers(100000);
        saveToFile(numbers, "random_100000.txt");

        numbers = generateSortedNumbers(10000);
        saveToFile(numbers, "sorted_10000.txt");

        numbers = generateReverseNumbers(10000);
        saveToFile(numbers, "reverse_10000.txt");

        numbers = generateRandomNumbers(10000);
        saveToFile(numbers, "random_10000.txt");

        numbers = generateSortedNumbers(1000);
        saveToFile(numbers, "sorted_10000.txt");

        numbers = generateReverseNumbers(1000);
        saveToFile(numbers, "reverse_10000.txt");

        numbers = generateRandomNumbers(1000);
        saveToFile(numbers, "random_10000.txt");
    }

    public static ArrayList<Integer> generateSortedNumbers(int n) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }
        return numbers;
    }

    public static ArrayList<Integer> generateReverseNumbers(int n) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = n; i >= 1; i--) {
            numbers.add(i);
        }
        return numbers;
    }

    public static ArrayList<Integer> generateRandomNumbers(int n) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            numbers.add(rand.nextInt(100000));
        }
        return numbers;
    }

    public static void saveToFile(ArrayList<Integer> numbers, String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (int num : numbers) {
                writer.write(Integer.toString(num) + " ");
            }
            writer.close();
            System.out.println(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
