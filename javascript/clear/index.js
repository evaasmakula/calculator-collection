import calc from './calc.js';


import {
    createInterface
} from 'readline';


const Calc = new calc();
const rl = createInterface({
    input: process.stdin,
    output: process.stdout
});

function print(argz) {
    console.log(argz);
}

rl.question('Masukkan soal: ', (soal) => {
    rl.close()

    let list = Calc.toList(soal);
    let postfix = Calc.toPostfix(list);
    let result = Calc.eval(postfix);

    print(result)
});