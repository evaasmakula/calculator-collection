export default class calculator {
    constructor() {}

    toList(str) {
        let list = [...str].filter(el => el != ' ');

        list.forEach(el => {
            if (!this.operator(el) && !el.match(/[0-9]/)) {
                throw new Error('Invalid operator - ' + el);
            }
        });

        return list;
    }

    operator(op) {
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

        let expr = postfix
        let stack = [];
        if (expr === '') {
            return 0;
        }

        for (let i = 0; i < expr.length; i++) {
            if (!isNaN(expr[i]) && isFinite(expr[i])) {
                stack.push(expr[i]);

            } else {
                let a = stack.pop();
                let b = stack.pop();
                if (expr[i] === "+") {
                    stack.push(parseInt(a) + parseInt(b));
                } else if (expr[i] === "-") {
                    stack.push(parseInt(b) - parseInt(a));
                } else if (expr[i] === "*") {
                    stack.push(parseInt(a) * parseInt(b));
                } else if (expr[i] === "/") {
                    stack.push(parseInt(b) / parseInt(a));
                } else if (expr[i] === "^") {
                    stack.push(Math.pow(parseInt(b), parseInt(a)));
                }
            }
        }

        if (stack.length > 1) {
            return "ERROR";
        } else {
            return stack[0];
        }
    }
}