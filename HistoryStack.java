/*Brian Pan 112856241 Recitation 2*/

import java.util.ArrayList;

/**
 * A stack which carries instances of the Equation class
 */
public class HistoryStack extends ArrayList<Equation> {
    /**
     * No args constructor for the HistoryStack class
     */
    HistoryStack(){}

    /**
     * Adds a value on top of the stack
     * @param newEquation
     * Equation object which carries an equation String
     */
    public void push(Equation newEquation){
        this.add(newEquation);
    }

    /**
     * Removes the top value of the stack
     * @return
     * Removes and returns the top value of the stack
     */
    public Equation pop(){
        return this.remove(this.size() - 1);
    }

    /**
     * Removes the most recent added Equation object
     */
    public void undo(){
        System.out.println(this.remove(this.size() - 1));
    }

    /**
     * Adds back the last removed Equation object to this stack
     * @param newEquation
     * Equation object which carries and equation String
     */
    public void redo(Equation newEquation){
        this.add(newEquation);
    }

    /**
     *
     * @return
     * Returns the size of the stack
     */
    public int size(){
        return super.size();
    }

    /**
     *
     * @param position
     * The wanted position of an Equation object
     * @return
     * Position of an Equation object in the stack
     */
    public Equation getEquation(int position){
        return this.get(position-1);
    }

    /**
     * String notation of the HistoryStack object
     * @return
     * The a string of the Equation object held in this stack
     */
    public String toString(){
        String historyPrint = "";
        for(int i = this.size(); i > 0; i--){
            historyPrint += String.format("%-4d%s", i, this.get(i-1));
        }
        return historyPrint;
    }
}
