import java.util.List;

public class main {
    public static void print(String argz) {
        System.out.println(argz);
    }

    public static void main(String[] args) {
        calc clc = new calc();

        //? Bersihkan terminal
        print("\033[H\033[2J");

        print("=========[ Kalkulator ]=========");
        print("Operator yang tersedia: \033[32m +, -, *, /\033[0m");
        print("Contoh: (1 + 2) * 3 - (4 / 5)");
        print("Tekan \033[33mCTRL + C\033[0m untuk keluar");
        print("================================");

        try {
            while (true) {
                System.out.print("Masukkan soal: ");

                /*
                 * 1 . Kita scan line nya kemudian kita pisah dengan menggunakan split
                 * 1.2 Disini kita pisahkan berdasarkan spasi (" ")
                 */
                String[] soal = System.console().readLine().split(" ");

                /*
                 * 2 Kemudian kita join atau gabungkan, kenapa gak digabungkan dari awa?
                 * 2.1 Ini untuk mempermudah kita dalam proses printing nanti
                 */
                String expression = String.join("", soal);

                // * 3. Ubah ke bentuk List dengan ini
                List<String> list1 = clc.toList(expression);

                /*
                 * 3.1 Kemudian kita pisahkan sesuai dengan prioritas
                 * 3.2 sehingga menghasilkan postfix
                 * 3.3 coba un-comment kode ini untuk mengetahui postfix nya
                 */
                // print(list1);
                List<String> list2 = clc.parseList(list1);

                // * 3.4 Kemudian kita hitung hasil pemisahan kita tadi
                Double calculate = clc.eval(list2);
                // * 3.5 Catatan: gunakan double atau float untuk mendapatkan nilai desimal

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
