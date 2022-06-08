import java.util.List;

public class main {
    public static void print(String argz) {
        System.out.println(argz);
    }

    public static void main(String[] args) {
        calc clc = new calc();

        print("\033[H\033[2J");

        print("=========[ Kalkulator ]=========");
        print("Operator yang tersedia: \033[32m +, -, *, /\033[0m");
        print("Contoh: (1 + 2) * 3 - (4 / 5)");
        print("Tekan \033[33mCTRL + C\033[0m untuk keluar");
        print("================================");

        try {
            while (true) {
                System.out.print("Masukkan soal: ");

                String[] soal = System.console().readLine().split(" ");
                String expression = String.join("", soal);

                List<String> list = clc.toList(expression);
                List<String> parsedList = clc.parseList(list);

                Double calculate = clc.eval(parsedList);

                if (calculate.toString().equals("Infinity")) {
                    print("\033[31m[ ERROR ] \033[0mInfinity");
                    System.exit(0);
                } else {

                    System.out.println("hasil dari \033[33m"
                            + String.join(" ", soal)
                            + "\033[0m adalah \033[32m"
                            + calculate
                            + "\033[0m");
                }
            }
        } catch (Exception e) {

            String err = e.toString();
            String stack = "";

            if (err.contains("out of bound")) {
                stack = "Tidak ada inputan";
            } else if (err.contains("null")) {
                print("\n\033[32m[ SUCCESS ] \033[0mBerhasil keluar");
            } else if (err.equals("java.util.NoSuchElementException")) {
                stack = "Karakter ilegal atau input tidak valid";
            }else{
                stack = "Terjadi kesalahan";
            }

            System.out.println("\033[31m[ ERROR ] \033[0m" + stack);
        }
    }

}
