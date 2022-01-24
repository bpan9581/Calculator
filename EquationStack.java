/*Brian Pan 112856241 Recitation 2*/

import java.util.ArrayList;

/**
 * Creates a stack that carries Mathematical operators
 */
public class EquationStack extends ArrayList<Character>{
    /**
     * No args constructor for EquationStack
     */
    public EquationStack(){}

    /**
     *
     * @param s
     * String s which carries a operator character
     */
    public void push(String s){this.add(s.charAt(0));}

    /**
     *
     * @return
     * Removes the top of the stack and returns the operator character on the top
     */
    public String pop(){return this.remove(this.size()-1).toString();}

    /**
     *
     * @return
     * Checks to see if the stack is empty
     */
    public boolean isEmpty(){
        return this.size() == 0;
    }

    /**
     *
     * @return
     * Returns the size of the stack
     */
    public int size(){
        return super.size();
    }
}
