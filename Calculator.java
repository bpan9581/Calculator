/*Brian Pan 112856241 Recitation 2*/

import java.util.*;

/**
 * Calculator class uses classes Equation and HistoryStack to simulate a calculator through a menu
 */
public class Calculator {
    public static void main(String [] args){
        Scanner stdin = new Scanner(System.in);
        HistoryStack calculatorHistory = new HistoryStack();
        HistoryStack memory = new HistoryStack();
        Equation equation;
        String selection, augmentedEquation;
        boolean quit = false;
        System.out.println("Welcome to calculat0r.");
        do{
            System.out.println("\n[A] Add new equation\n" +
                "[F] Change equation from history\n" +
                "[B] Print previous equation\n" +
                "[P] Print full history\n" +
                "[U] Undo\n" +
                "[R] Redo\n" +
                "[C] Clear history\n" +
                "[Q] Quit \n" +
                "\n" +
                "Select an option:");
            selection = stdin.nextLine().toUpperCase();
            switch (selection){
                case "A":
                    try {
                        System.out.println("Please enter an equation (in-fix notation):");
                        equation = new Equation(stdin.nextLine());
                        calculatorHistory.add(equation);
                        if (calculatorHistory.get(calculatorHistory.size() - 1).isBalanced()) {
                            System.out.printf("The equation is balanced and the answer is %.3f \n",
                                    calculatorHistory.get(calculatorHistory.size() - 1).getAnswer());
                        }
                        else
                            System.out.println("The equation is not balanced.");
                    }catch (IllegalArgumentException ex){
                        System.out.println("The equation is not balanced");
                    }

                    break;
                case "F":
                    if(calculatorHistory.size() == 0){
                        System.out.println("There is no equation to change.");
                        break;
                    }
                    try {
                        int position;
                        System.out.println("Which equation would you like to change?");
                        position = stdin.nextInt();
                        stdin.nextLine();
                        if(position > calculatorHistory.size()){
                            System.out.println("No equation at that position");
                            break;
                        }
                        String changeEq;
                        boolean change = false;
                        int equationPos;
                        StringBuilder newEquation =
                                new StringBuilder(calculatorHistory.getEquation(position).getEquation());
                        System.out.println("Equation: " + newEquation);
                        do {
                            System.out.println("What would you like to do to the equation (Replace / remove / add)?");
                            changeEq = stdin.nextLine().toUpperCase();
                            switch (changeEq) {
                                case "REPLACE":
                                    System.out.println("What position would you like to change?");
                                    equationPos = stdin.nextInt();
                                    stdin.nextLine();
                                    if(equationPos < 0){
                                        System.out.println("Invalid position");
                                        break;
                                    }
                                    System.out.println("What would you like to replace with it?");
                                    char replace = stdin.nextLine().charAt(0);
                                    newEquation.setCharAt(equationPos - 1, replace);
                                    break;
                                case "REMOVE":
                                    System.out.println("What position would you like to remove?");
                                    equationPos = stdin.nextInt();
                                    if(equationPos < 0 || equationPos > newEquation.length()){
                                        System.out.println("Invalid position");
                                    }
                                    stdin.nextLine();
                                    newEquation.replace(equationPos-1, equationPos, "");
                                    break;
                                case "ADD":
                                    System.out.println("What position would you like to add to?");
                                    equationPos = stdin.nextInt();
                                    stdin.nextLine();
                                    System.out.println("What would you like to add?");
                                    char add = stdin.nextLine().charAt(0);
                                    newEquation.insert(equationPos - 1, add);
                                    break;
                                default:
                                    System.out.println("Not a valid selection");
                                    break;
                            }
                            boolean flag = false;
                            while(!flag) {
                                System.out.println("Equation: " + newEquation);
                                System.out.println("Would you like to make any more changes");
                                String continueChange = stdin.nextLine().toUpperCase();
                                if (continueChange.equals("NO") || continueChange.equals("N")) {
                                    augmentedEquation = newEquation.toString();
                                    equation = new Equation(augmentedEquation);
                                    calculatorHistory.add(equation);
                                    if (calculatorHistory.get(calculatorHistory.size() - 1).isBalanced())
                                        System.out.printf("The equation is balanced and the answer is %.3f\n",
                                                calculatorHistory.get(calculatorHistory.size() - 1).getAnswer());
                                    else
                                        System.out.println("The equation is not balanced.");
                                    change = true;
                                    flag = true;
                                } else if (continueChange.equals("YES") || continueChange.equals("Y")) {
                                    change = false;
                                    flag = true;
                                } else {
                                    System.out.println("Invalid input. Equation was not changed");
                                }
                            }
                        } while (!change);
                    }catch (InputMismatchException ex){
                        System.out.println("Invalid Input");
                    }
                    break;
                case "B":
                    if(calculatorHistory.size() == 0) {
                        System.out.println("There is no equation in the calculator");
                        break;
                    }
                    System.out.println("#   Equation                           Pre-Fix " +
                            "                           " +
                            "Post-Fix                           Answer          Binary       " +
                            "Hexadecimal\n" +
                            "----------------------------------------------------------------" +
                            "-------------------------------------------------------------------" +
                            "------------------");
                    System.out.printf("%-4d%s", calculatorHistory.size(),
                            calculatorHistory.getEquation(calculatorHistory.size()));
                    break;
                case "P":
                    if(calculatorHistory.size() == 0){
                        System.out.println("There is no equation in the calculator");
                        break;
                    }
                    System.out.println("#   Equation                           Pre-Fix " +
                            "                           " +
                            "Post-Fix                           Answer          Binary       " +
                            "Hexadecimal\n" +
                            "--------------------------------------------------------------" +
                            "---------------------------------------------------------------" +
                            "------------------------");
                    System.out.println(calculatorHistory);
                    break;
                case "U":
                    if(calculatorHistory.size() == 0)
                        System.out.println("There is nothing to undo");
                    else {
                        memory.add(calculatorHistory.pop());
                        System.out.println("Equation " + memory.getEquation(memory.size()).getEquation() + " undone");
                    }
                    break;
                case "R":
                    if(memory.size() == 0){
                        System.out.println("There is nothing to redo");
                    }
                    else {
                        calculatorHistory.add(memory.pop());
                        System.out.println("Redoing equation " +
                                calculatorHistory.getEquation(calculatorHistory.size()).getEquation());
                    }
                    break;
                case "C":
                    calculatorHistory = new HistoryStack();
                    memory = new HistoryStack();
                    System.out.println("Resetting Calculator");
                    break;
                case "Q":
                    System.out.println("System terminating normally...");
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid menu option.");
                    break;
            }
        }
        while (!quit);
    }
}
