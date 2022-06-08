export default class calculator {
    constructor() {}

    // Convert to list
    toList(str) {
        let list = [...str].filter(el => el != ' ');

        list.forEach(el => {
            // Check apakah operator didukung
            if (!this.operator(el) && !el.match(/[0-9]/)) {
                throw new Error('Invalid operator - ' + el);
            }
        });

        return list;
    }

    operator(op) {
        // Daftar operatro yang didukung
        if (op == '+' || op == '-' ||
            op == '^' || op == '*' ||
            op == '/' || op == '(' ||
            op == ')') {
            return true;
        } else
            return false;
    }

    toPostfix(arr) {
        let stack = []; // stack untuk menyimpan operator
        let postfix = []; // stack untuk menyimpan hasil postfix
        let temp = 0; // variabel penampung untuk operanter
        let top = -1; // top untuk menyimpan index stack

        for (let i = 0; i < arr.length; i++) {
            let el = arr[i]; 
    
            if (el == '(') { // jika operator "(" maka masukkan ke stack
                top++;
                stack[top] = el;

            } else if (el == ')') {

                // jika operator ")" maka pop dari stack sampai "(" 
                while (stack[top] != '(') {
                    postfix.push(stack[top]);
                    top--;
                }

                // turunkan indeks
                top--;
            } else if (this.operator(el)) {

                // check prioritas operator kemudian masukkan ke stack sesuai dengan prioritas
                while (top != -1 && stack[top] != '(' && this.precedency(el) <= this.precedency(stack[top])) {
                    postfix.push(stack[top]);
                    top--;
                }

                top++;
                stack[top] = el;
            } else {

                // jika operanter maka masukkan ke stack
                postfix.push(el);
            }
        }

        // masukkan sisa stack ke postfix
        while (top != -1) {
            postfix.push(stack[top]);
            top--;
        }

        return postfix;
    }

    // Check precedency atau prioritas operator
    precedency(pre) {
        if (pre == '@' || pre == '(' || pre == ')') {
            return 1;
        } else if (pre == '+' || pre == '-') {
            return 2;
        } else if (pre == '/' || pre == '*') {
            return 3;
        } else if (pre == '^') {
            return 4;
        } else
            return 0;
    }

    // Evaluate postfix
    eval(postfix) {
        let stack = []; // stack untuk menyimpan operanter

        // kalo kosong retrun 0
        if (postfix === '') {
            return 0;
        }

        // loop untuk mengambil operanter dari postfix
        for (let i = 0; i < postfix.length; i++) {

            // jika operanter maka masukkan ke stack
            if (!isNaN(postfix[i]) && isFinite(postfix[i])) {
                stack.push(postfix[i]);

            } else {

                // pisahkan operanter pertama dan kedua dari stack
                let a = stack.pop();
                let b = stack.pop();

                // kalkulasi operator 
                if (postfix[i] === "+") {
                    stack.push(parseInt(a) + parseInt(b));
                } else if (postfix[i] === "-") {
                    stack.push(parseInt(b) - parseInt(a));
                } else if (postfix[i] === "*") {
                    stack.push(parseInt(a) * parseInt(b));
                } else if (postfix[i] === "/") {
                    stack.push(parseInt(b) / parseInt(a));
                } else if (postfix[i] === "^") {
                    stack.push(Math.pow(parseInt(b), parseInt(a)));
                }
            }
        }

        // return hasil kalkulasi
        if (stack.length > 1) { // jika masih ada sisa di stack maka error
            return "ERROR";
        } else { 
            return stack[0]; // jika tidak maka return hasil kalkulasi
        }
    }
}