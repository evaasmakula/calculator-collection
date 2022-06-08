// import clas dan dependensi
import calc from './calc.js';
import {
    createInterface
} from 'readline';

// inisialisasi objek
const Calc = new calc();
const rl = createInterface({
    input: process.stdin,
    output: process.stdout
});

// definisikan fungsi print agar menghemat penulisan
function print(argz) {
    console.log(argz);
}

// minta input dengan rl.question
rl.question('Masukkan soal: ', (soal) => {

	// tutup rl agar tidak ada input lagi
    rl.close()

	// jalankan fungsi eval
    let list = Calc.toList(soal);
    let postfix = Calc.toPostfix(list);
    let result = Calc.eval(postfix);

	// print hasil
    print(result)

	//* atau bisa juga dibuat seperti ini
	// print(Calc.eval(Calc.toPostfix(Calc.toList(soal))))
});