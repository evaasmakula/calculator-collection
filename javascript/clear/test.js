export default class calculator {
    constructor() {
        let date = new Date().getTime().toLocaleString('en-US', {
            timeZone: 'Asia/Jakarta'
        });
        console.log(`[ ${date} ] Calculator started`);
    }

    toList(str) {
        let list = [...str].filter(el => el != ' ');
        // check if opt valid
        for (items of list) {
            if (!this.operator(items)) {
                throw new Error('Invalid operator - ' + items);
            }
        }

        return list;
    }

    operator(opt) {
        if (op == '+' || op == '-' ||
            op == '^' || op == '*' ||
            op == '/' || op == '(' ||
            op == ')') {
            return true;
        } else
            return false;
    }

    toPostfix(arr) {
        let stack = [];
        let postfix = [];
        let temp = 0;
        let top = -1;

        for (let i = 0; i < arr.length; i++) {
            let el = arr[i];

            if (el == '(') {
                top++;
                stack[top] = el;
            } else if (el == ')') {
                while (stack[top] != '(') {
                    postfix.push(stack[top]);
                    top--;
                }
                top--;
            } else if (this.operator(el)) {
                while (top != -1 && stack[top] != '(' && this.precedency(el) <= this.precedency(stack[top])) {
                    postfix.push(stack[top]);
                    top--;
                }
                top++;
                stack[top] = el;
            } else {
                postfix.push(el);
            }
        }

        while (top != -1) {
            postfix.push(stack[top]);
            top--;
        }

        return postfix;
    }

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

    eval(postfix) {

        let calc = {
            "+": (a, b) => a + b,
            "-": (a, b) => a - b,
            "*": (a, b) => a * b,
            "/": (a, b) => a / b
        }

        let stack = []

        postfix.forEach(op => {
            stack.push(
                calc[op] ?
                calc[op](...stack.splice(-2)) :
                op
            )
        });

        return stack
    }
}