import java.io.*;

class CRC {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter Generator: ");
        String gen = br.readLine();
        System.out.print("Enter Data: ");
        String data = br.readLine();

        String code = data + divide(data + "0".repeat(gen.length() - 1), gen);
        System.out.println("Transmitted Code Word: " + code);

        System.out.print("Enter received Code Word: ");
        String rec = br.readLine();
        System.out.println(Integer.parseInt(divide(rec, gen)) == 0 ?
                "Received code word contains no errors." :
                "Received code word contains errors.");
    }

    static String divide(String dividend, String divisor) {
        String rem = dividend.substring(0, divisor.length());
        int ptr = divisor.length();
        while (ptr < dividend.length()) {
            if (rem.charAt(0) == '0') rem = rem.substring(1) + dividend.charAt(ptr++);
            String temp = "";
            for (int i = 0; i < divisor.length(); i++)
                temp += rem.charAt(i) == divisor.charAt(i) ? "0" : "1";
            rem = temp;
        }
        return rem.substring(1);
    }
}
