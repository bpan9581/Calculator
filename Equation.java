/*Brian Pan 112856241 Recitation 2*/
import java.util.ArrayList;
/**
 * This class carries a String representation of an mathematical formala
 * The class carries values for Strings representations of
 * equation, prefix, postfix, binary, and hexadecimals
 * The class also carries a double value for the answer of an equation
 */
public class Equation extends ArrayList<Double>{
    private String equation;
    private String prefix, postfix;
    private double answer = 0.0;
    private String binary, hex;
    private boolean balanced = false;

    /**
     *
     * @return
     * a String representation of an equation
     */
    public String getEquation() {
        return equation;
    }

    /**
     *
     * @param equation
     * The string representation of an equation
     */
    public void setEquation(String equation) {
        this.equation = equation;
    }

    /**
     *
     * @return
     * A String representation of prefix notation for an equation
     */
    public String getPrefix() {
        return prefix;
    }

    public void setBalanced(boolean balanced) {
        this.balanced = balanced;
    }

    /**
     *
     * @param prefix
     * A string presentation of prefix notation for an equation
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     *
     * @return
     * A String representation for the postfix notation of an equation
     */
    public String getPostfix() {
        return postfix;
    }

    /**
     *
     * @param postfix
     * A String representation of postfix notation of an equation
     */
    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    /**
     *
     * @return
     * A double value for the final answer of an equation
     */
    public double getAnswer() {
        String numEq = this.postfix();
        String number = "";

        for(int i = 0; i < numEq.length(); i++){
            if (!isOperator(numEq.charAt(i)) && !(numEq.charAt(i) == ' ')){
                number += numEq.charAt(i);
            }
            else if (numEq.charAt(i) == ' ') {
                if(number != "") {
                    this.add(Double.parseDouble(number));
                }
                number = "";
            }
            else if(isOperator(numEq.charAt(i))){
                double op2 = this.get(this.size() - 1);
                this.remove(this.size()-1);
                double op1 = this.get(this.size() - 1);
                this.remove(this.size()-1);
                switch (numEq.charAt(i)){
                    case '+':
                        answer = op1 + op2;
                        break;
                    case '-':
                        answer = op1 - op2;
                        break;
                    case '*':
                        answer = op1 * op2;
                        break;
                    case '/':
                        if(op2 == 0){
                            throw new IllegalArgumentException();
                        }
                        answer = op1 / op2;
                        break;
                    case '%':
                        if(op2 == 0){
                            throw new IllegalArgumentException();
                        }
                        answer = op1 % op2;
                        break;
                    case '^':
                        answer = Math.pow(op1,op2);
                        break;
                }
                this.add(answer);
            }
        }

        return answer;
    }

    /**
     *
     * @param answer
     * The double value of an answer to an equation
     */
    public void setAnswer(double answer) {
        this.answer = answer;
    }

    /**
     *
     * @return
     * Binary notation for a integer
     */
    public String getBinary() {
        return binary;
    }

    /**
     *
     * @param binary
     * Binary notation of an integer
     */
    public void setBinary(String binary) {
        this.binary = binary;
    }

    /**
     *
     * @return
     * Hexadecimal notation of an integer
     */
    public String getHex() {
        return hex;
    }

    /**
     *
     * @param hex
     * The hexadecimal notation of an integer
     */
    public void setHex(String hex) {
        this.hex = hex;
    }

    /**
     *
     * @param equation
     * The String of an equation
     */
    public Equation(String equation) {
        this.equation = equation;
    }

    /**
     *
     * @return
     * Checks whether an equation is in the proper form
     */
    public boolean isBalanced() {
        if(equation.contains(" ")) {
            equation = equation.replaceAll(" ", "");
        }
        int order = 0, operatorAmount = 0;
        if (equation.length() == 0)
            return balanced;
        if(operatorPrecedence(equation.charAt(0)) == 1 ||
                operatorPrecedence(equation.charAt(0)) == 2 ||
                operatorPrecedence(equation.charAt(0)) == 3 ||
                operatorPrecedence(equation.charAt(equation.length() - 1)) == 1 ||
                operatorPrecedence(equation.charAt(equation.length() - 1)) == 2 ||
                operatorPrecedence(equation.charAt(equation.length() - 1)) == 3)
            return balanced;
        if(equation.contains("(") || equation.contains(")")) {
            for (int i = 0; i < equation.length(); i++) {
                if (isOperator(equation.charAt(i)))
                    operatorAmount++;
                if (!(equation.charAt(i) >= '0'
                        && equation.charAt(i) <= '9') && !isOperator(equation.charAt(i))) {
                    return balanced;
                }

                if (equation.charAt(i) == '(') {
                    if (isOperator(equation.charAt(i)) && isOperator(equation.charAt(i + 1))
                            && equation.charAt(i + 1) != '(') {
                        return balanced;
                    }
                    order++;
                } else if (equation.charAt(i) == ')') {
                    if (isOperator(equation.charAt(i)) && isOperator(equation.charAt(i - 1))
                            && equation.charAt(i - 1) != ')') {
                        return balanced;
                    }
                    order--;
                }
                if (order < 0) {
                    return balanced;
                }
            }
        }
        else {
            for(int i = 0; i < equation.length(); i++){
                if (!(equation.charAt(i) >= '0'
                        && equation.charAt(i) <= '9') && !isOperator(equation.charAt(i))) {
                    return balanced;
                }
                if(isOperator(equation.charAt(i)))
                    operatorAmount++;
                if (isOperator(equation.charAt(i)) && isOperator(equation.charAt(i + 1))) {
                    return balanced;
                }
            }
        }
        if(operatorAmount == 0){
            answer = Double.parseDouble(equation);
        }
        if (order != 0) {
            return balanced;
        }
        balanced = true;
        return balanced;
    }

    /**
     *
     * @param number
     * The individual numerical values in a equation String
     * @return
     * Binary notation of an integer answer of the equation
     */
    public String decToBin(int number) {
        String binary = "";
        while (number > 0) {
            binary = (number % 2) + binary;
            number /= 2;
        }
        return binary;
    }

    public String decToHex(int number) {
        String hexadecimal = "";
        while (number > 0) {
            if (number % 16 < 10)
                hexadecimal = number % 16 + hexadecimal;
            else {
                char hexCode = (char) (55 + (number % 16));
                hexadecimal = hexCode + hexadecimal;
            }
            number /= 16;
        }
        return hexadecimal;
    }

    /**
     *
     * @param operator
     * Mathematical operators
     * @return
     * Checks to see if a character is a valid operator
     */
    public boolean isOperator(char operator) {
        if (operator == '+' || operator == '-' || operator == '*' || operator == '/' ||
                operator == '(' || operator == ')' || operator == '%' || operator == '^')
            return true;
        return false;
    }

    /**
     *
     * @param operator
     * Mathematical operators
     * @return
     * Gives an integer value of the precedence of an operator in an equation
     */
    public int operatorPrecedence(char operator) {
        if (operator == '+' || operator == '-')
            return 1;
        if (operator == '*' || operator == '/' || operator == '%')
            return 2;
        if (operator == '^')
            return 3;
        return -1;
    }

    /**
     *
     * @return
     * The postfix notation of an equation String
     */
    public String postfix() {
        if (!isBalanced())
            return "N/A";
        String postfix = "", number = "";
        EquationStack operators = new EquationStack();
        for (int i = 0; i < equation.length(); i++) {
            String operator = "";
            if (isOperator(equation.charAt(i))) {
                postfix = postfix.trim();
                postfix += " " + number + " ";
                number = "";
                if (operators.size() == 0) {
                    operator += equation.charAt(i);
                    operators.push(operator);
                } else {
                    if (equation.charAt(i) == ')' || equation.charAt(i) == '(') {
                        if (equation.charAt(i) == ')') {
                            while ((operators.get(operators.size()-1) != '(')) {
                                if ((operators.get(operators.size() -1) != ')')) {
                                    postfix += operators.pop();
                                }
                            }
                            operators.pop();

                        } else {
                            operator += equation.charAt(i);
                            operators.push(operator);
                        }
                    } else if (operatorPrecedence(operators.get(operators.size()- 1)) -
                            operatorPrecedence(equation.charAt(i)) <= 0) {
                        operator += equation.charAt(i);
                        operators.push(operator);
                    } else {
                        if (operatorPrecedence(operators.get(operators.size() - 1)) == 1 ||
                                operatorPrecedence(operators.get(operators.size() - 1)) == 2||
                                operatorPrecedence(operators.get(operators.size() - 1)) == 3)
                            postfix += operators.get(operators.size() - 1);
                        operators.set(operators.size() - 1, equation.charAt(i));
                    }
                }
            } else {
                number += equation.charAt(i);
            }
        }
        if(!operators.isEmpty()){
            postfix = postfix.trim();
            postfix += " " + number + " ";
            number = "";
        }
        while (!operators.isEmpty()){
            postfix += operators.pop();
        }
        if (number != ""){
            postfix += number;
        }
        return postfix;
    }

    /**
     *
     * @return
     * The prefix notation of an equation String
     */
    public String prefix() {
        if (!isBalanced()) {
            return "N/A";
        }
        String prefix = "", number = "";
        EquationStack operators = new EquationStack();
        for (int i = equation.length() - 1; i >= 0; i--) {
            String operator = "";
            if (isOperator(equation.charAt(i))) {
                prefix = prefix.trim();
                prefix = number + " " + prefix;
                number = "";
                if (operators.size() == 0) {
                    operator += equation.charAt(i);
                    operators.push(operator);
                } else {
                    if (equation.charAt(i) == ')' || equation.charAt(i) == '(') {
                        if (equation.charAt(i) == '(') {
                            while (operators.get(operators.size() - 1) != ')') {
                                if (operators.get(operators.size() - 1) != '(') {
                                    prefix = operators.pop() + " " +prefix;
                                }
                            }
                            operators.pop();
                        } else {
                            operator += equation.charAt(i);
                            operators.push(operator);
                        }
                    } else if (operatorPrecedence(operators.get(operators.size() - 1)) -
                            operatorPrecedence(equation.charAt(i)) < 0) {
                        operator += equation.charAt(i);
                        operators.push(operator);
                    } else {
                        if (operatorPrecedence(operators.get(operators.size() - 1)) == 1 ||
                                operatorPrecedence(operators.get(operators.size() - 1)) == 2 ||
                                operatorPrecedence(operators.get(operators.size() - 1)) == 3)
                            prefix = operators.pop() + " " + prefix;
                        operator += equation.charAt(i);
                        operators.push(operator);
                    }
                }
            } else {
                number = equation.charAt(i) + number;
            }
        }
        if(!number.equals(""))
            prefix = number + " " + prefix;
        while (!operators.isEmpty()){
            prefix = operators.pop() + " " +prefix;
        }
        return prefix;
    }

    /**
     *
     * @return
     * Converts the Equation class into String form
     */
    public String toString() {
        try {
            if (!balanced)
                return String.format("%-35s%-35s%-35s%-16s%-19s%5s\n", equation, prefix(), postfix()
                        , "0.000", "0", "0");
            else
                return String.format("%-35s%-35s%-35s%-16.3f%-19s%5s\n", equation, prefix(), postfix(), getAnswer(),
                        decToBin((int) Math.round(getAnswer())), decToHex((int) Math.round(getAnswer())));
        } catch (IllegalArgumentException ex){
            return String.format("%-35s%-35s%-35s%-16s%-19s%5s\n", equation, "N/A", "N/A"
                    , "0.000", "0", "0");
        }
    }
}

