import java.util.*;

public class calc {
    public List<String> toList(String problem) {
        List<String> listResult = new ArrayList<>();
        String str; 
        int i = 0; 
        char c; 

        do {
            if ((c = problem.charAt(i)) < 48 || (c = problem.charAt(i)) > 57) {
                listResult.add(("" + c));
                i++;
            } else {
                str = "";

                while (i < problem.length() && (c = problem.charAt(i)) >= 48 && (c = problem.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                listResult.add(str);
            }
        } while (i < problem.length()); 

        System.out.println(listResult);
        return listResult;
    }

    public List<String> parseList(List<String> list) {

        Deque<String> stack1 = new LinkedList<>();

        List<String> stack2 = new ArrayList<>();

        for (String item : list) {

            if (item.matches("\\d+")) {
                stack2.add(item);

            } else if (item.equals("(")) {
                stack1.push(item);

            } else if (item.equals(")")) {

                while (!stack1.peek().equals("(")) {
                    stack2.add(stack1.pop());
                }

                stack1.pop();
            } else {
                while (stack1.size() != 0 && Operation.getValue(stack1.peek()) >= Operation.getValue(item)) {

                    stack2.add(stack1.pop());
                }
                stack1.push(item);
            }
        }

        while (stack1.size() != 0) {
            stack2.add(stack1.pop());
        }

        System.out.println(stack2);
        return stack2;

    }

    class Operation {

        private static int ADD = 1; 
        private static int SUB = 1; 
        private static int MUL = 2; 
        private static int DIV = 2; 

        public static int getValue(String operation) {

            int result = 0;
            switch (operation) {

                case "+":
                    result = ADD;
                    break;
                case "-":
                    result = SUB;
                    break;
                case "*":
                    result = MUL;
                    break;
                case "/":
                    result = DIV;
                    break;
                default:
                    break;
            }
            return result;
        }
    }

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
