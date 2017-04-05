/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author evenal
 */
public interface CalculatorEngine {

    // Buttons used to input/edit numbers
    // input of numbers start with a numeric button
    // and continues until an operator button or enter
    // is pressed.
    /**
     * A numeric buttons was pressed. This will start entering a new number if
     * the previous key was an operator or enter. Adds a digit to the number, if
     * entering a number is already started
     *
     * @param number specifies which numeric button
     */
    void numberPressed(int number);


    /**
     * decimal point was pressed. If the decimal point is pressed more than once
     * during the input of a number the second and subsequent presses are
     * ignored
     */
    void pointPressed();


    /**
     * change the sign of the number. The CHS key can be pressed at any time
     * during the entering of a number.
     */
    void chsPressed();


    /**
     * resets the calculator. This key does not exist on a real HP11C, but may
     * be useful for testing/debugging.
     */
    void clearPressed();


    // mathematical operators:
    // if a number is being entered, and only then, it will be pushed
    // onto the stack.
    // Then the stack will be popped twice, the operation carried out
    // and the result will be pushed back on the stack
    //
    // make sure you get subtraction and division right
    // the input sequence 4 enter 1 - will display 3 (4-1)
    // 6 enter 3 / displays 2
    void plusPressed();


    void minusPressed();


    void multiplyPressed();


    void dividePressed();


    /**
     * pushes a number on the stack. If a number was being entered it will be
     * pushed on the stack. If not, e.g. if the previous keypress was enter, the
     * number at the top of the stack will be popped again
     */
    void enterPressed();


    /**
     * @return The author of this engine. This string is displayed as it is, so
     * it should say something like "Written by H. Simpson".
     */
    String getAuthor();


    /**
     * @return The value that should currently be displayed on the calculator
     * display.
     */
    String getDisplayValue();


    /**
     * @return The title of this calculation engine.
     */
    String getTitle();


    /**
     * @return The version number of this engine. This string is displayed as it
     * is, so it should say something like "Version 1.1".
     */
    String getVersion();

}
