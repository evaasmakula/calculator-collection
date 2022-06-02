import java.util.*;

public class calc {

    /*
     * 1.0 Yang pertama kita lakukan adalah mengubah inputan menjadi list
     * 1.1 Yups kita akan menggunakan linked list seperti di SMT 1
     */

    public List<String> toList(String problem) {
        List<String> listResult = new ArrayList<>();
        String str; // * <-- Pemisah multi bit
        int i = 0; // * <-- untuk looping
        char c; // * <-- karakter perpindahan

        // * kita akan looping setiap karakter di problem dan mengubahnya mmenjadi list
        do {
            /*
             * 2.0 kita pisahkan disini jika c (karakter problem di index i) lebih kecil
             * 2.1 dari 48 dan lebih besar dari 57 maka langsung saja dimasukkan
             * 2.2 ini digunakan untuk memisahkan antara angka yang ada dengan
             * 2.3 operator
             */
            if ((c = problem.charAt(i)) < 48 || (c = problem.charAt(i)) > 57) {
                listResult.add(("" + c));
                i++;
            } else {
                str = "";

                /*
                 * 2.4 Sekarang kita pisahkan angkanya
                 */
                while (i < problem.length() && (c = problem.charAt(i)) >= 48 && (c = problem.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                listResult.add(str);
            }
        } while (i < problem.length()); // * <-- looping
        return listResult; // * <-- Kita kembalikan list nya
    }

    // ! mari kita parse list nya

    public List<String> parseList(List<String> list) {
        /*
         * 3.0 Mari kita persiapkan 2 buah stack untuk menampung operator
         * 3.1 dan untuk menampung hasil nya
         * =================
         * 3.2 Kita pakai deque agar bisa memasukkan dan mengambil data dari depan
         * 3.3 belakang.
         */
        Deque<String> stack1 = new LinkedList<>(); // * <-- pakai linked list

        // * 3.4 Stack kedua hanya berupa list biasa karena akan menampung hasil jadi
        List<String> stack2 = new ArrayList<>(); // * <-- pakai array biasa

        for (String item : list) {

            // * 3.5 kita check apakah sesuai

            if (item.matches("\\d+")) { // * <-- check apakah desimal / number pakai regex
                stack2.add(item);

            } else if (item.equals("(")) {
                stack1.push(item); // * <-- ingat kan bagaimana ubah infix ke suffix

            } else if (item.equals(")")) {

                // * 3.6 kalo tutup kurung kita massukan semua stack 1 ke stack 2
                while (!stack1.peek().equals("(")) {
                    stack2.add(stack1.pop());
                }

                stack1.pop();
            } else {
                // * 3.7 disini rada ribet soalnya kita harus menghitung prioritas operator
                // * 3.8 terlebih dahulu
                while (stack1.size() != 0 && Operation.getValue(stack1.peek()) >= Operation.getValue(item)) {

                    stack2.add(stack1.pop());
                }
                stack1.push(item);
            }
        }

        // * 3.9 nah stack 1 tidak boleh kosnong karena semua operator bakalan mampir
        while (stack1.size() != 0) {
            // * 3.10 kita loop sampai selesai
            stack2.add(stack1.pop());
        }

        return stack2;
    }

    // * 4.0 Untuk menghitung bobot cukup mudah sih kalian pasti paham kok
    class Operation {

        private static int ADD = 1; // Tambah
        private static int SUB = 1; // Kurang
        private static int MUL = 2; // Kali
        private static int DIV = 2; // Bagi

        public static int getValue(String operation) {

            int result = 0;
            switch (operation) {

                case "+":
                    // result = 1
                    result = ADD;
                    break;
                case "-":
                    // result = 1
                    result = SUB;
                    break;
                case "*":
                    // result = 2
                    result = MUL;
                    break;
                case "/":
                    // result = 2
                    result = DIV;
                    break;
                default:
                    break;
            }
            return result;
        }
    }

    // ! HITUNG NILAINYA

    // * 5.0 kita return float karena bisa saja hasilnya desimal
    public Double eval(List<String> list) {

        Deque<String> stack = new LinkedList<>();
        for (String s : list) {

            if (s.matches("\\d+")) {

                stack.push(s);
            } else {

                Double num2 =  Double.parseDouble(stack.pop());
                Double num1 =  Double.parseDouble(stack.pop());
                Double result = 0.0;
                if (s.equals("+")) {

                    result = num1 + num2;
                } else if (s.equals("-")) {

                    result = num1 - num2;
                } else if (s.equals("*")) {

                    result = num1 * num2;
                } else if (s.equals("/")) {

                    result = num1 / num2;
                }

                stack.push("" + result);
            }
        }
        return Double.parseDouble(stack.pop());
    }

}
