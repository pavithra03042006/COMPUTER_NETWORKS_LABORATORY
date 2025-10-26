import java.util.*;

public class AIMD {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Enter initial congestion window size: ");
        int cwnd = sc.nextInt();

        System.out.print("Enter probability of ACK (0 to 1): ");
        double ackProb = sc.nextDouble();

        System.out.print("Enter probability of packet loss (0 to 1): ");
        double lossProb = sc.nextDouble();

        int ssthresh = Integer.MAX_VALUE;
        int ackCount = 0;

        System.out.println("\nSimulation started (Press Ctrl+C to stop)\n");

        while (true) {
            System.out.println("cwnd = " + cwnd + ", ssthresh = " + ssthresh);

            for (int i = 0; i < cwnd; i++)
                System.out.println("Packet sent.");

            if (rand.nextDouble() <= ackProb) {
                System.out.println("ACK received.");
                ackCount++;
                if (ackCount >= cwnd) {
                    cwnd++;
                    ackCount = 0;
                }
            }

            if (rand.nextDouble() <= lossProb) {
                System.out.println("Packet loss! Reducing cwnd...");
                ssthresh = cwnd / 2;
                cwnd = Math.max(1, cwnd / 2);
                ackCount = 0;
            }

            if (cwnd < ssthresh)
                System.out.println("Slow Start Phase.");
            else
                System.out.println("Congestion Avoidance Phase.");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Simulation stopped.");
                break;
            }
        }
    }
}
