import java.util.Scanner;

public class HammingCode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input data bits
        System.out.print("Enter number of data bits: ");
        int d = sc.nextInt();
        int[] data = new int[d];
        System.out.println("Enter data bits (0 or 1):");
        for (int i = 0; i < d; i++) data[i] = sc.nextInt();

        // Calculate number of parity bits
        int p = 0;
        while (Math.pow(2, p) < d + p + 1) p++;

        int n = d + p; // total bits
        int[] hamming = new int[n + 1]; // using 1-based index

        // Place data bits in hamming code (skip parity positions)
        for (int i = 1, j = 0; i <= n; i++) {
            if ((i & (i - 1)) != 0) hamming[i] = data[j++];
        }

        // Calculate parity bits
        for (int i = 0; i < p; i++) {
            int parityPos = (int)Math.pow(2, i);
            int sum = 0;
            for (int j = 1; j <= n; j++) {
                if ((j & parityPos) != 0) sum += hamming[j];
            }
            hamming[parityPos] = sum % 2;
        }

        // Print Hamming code
        System.out.print("Hamming code: ");
        for (int i = 1; i <= n; i++) System.out.print(hamming[i]);
        System.out.println();

        // Error detection
        System.out.println("\nEnter received Hamming code bits:");
        int[] received = new int[n + 1];
        for (int i = 1; i <= n; i++) received[i] = sc.nextInt();

        int errorPos = 0;
        for (int i = 0; i < p; i++) {
            int parityPos = (int)Math.pow(2, i);
            int sum = 0;
            for (int j = 1; j <= n; j++) {
                if ((j & parityPos) != 0) sum += received[j];
            }
            if (sum % 2 != 0) errorPos += parityPos;
        }

        if (errorPos == 0) System.out.println("No error detected.");
        else {
            System.out.println("Error detected at position: " + errorPos);
            // Correct error
            received[errorPos] = 1 - received[errorPos];
            System.out.print("Corrected Hamming code: ");
            for (int i = 1; i <= n; i++) System.out.print(received[i]);
        }
    }
}
