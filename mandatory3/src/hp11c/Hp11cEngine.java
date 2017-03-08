package hp11c;

/**
 * Created by Christian on 3/7/2017.
 */
public class Hp11cEngine implements CalculatorEngine{

    String title;
    String author;
    String version;
    int currentNumber;
    Stack<String> stack;
    Stack<String> decimal;

    String displayValue;
    boolean enteringDecimal;

    public Hp11cEngine() {
        this.title = "kalkulator";
        this.author = "Christian";
        this.version = "1.0";
        this.stack = new Stack<>(10);
        this.decimal = new Stack<>(10);
        this.enteringDecimal = false;
    }


    public void clearPressed() {
        this.stack = new Stack<>(10);
        this.decimal = new Stack<>(10);
        displayValue = "";
        this.enteringDecimal = false;
    }

    public void dividePressed() {
        enteringDecimal = false;                                // now, prevent entering decimal for ever..
        if (currentNumber != 0) {
            double result = getStackNumbers() / currentNumber;     // get numbers from stack and divide the current number
            System.out.println(result);
            displayValue = String.valueOf(result);                 // for displaying the number
            parseIntoStack(result);                                // parse it into the stack again
            currentNumber = 0;
        }
    }

    public void chsPressed() {
        double numbers = getStackNumbers();
        if (numbers < 0) // make to postive
            numbers = Math.abs(numbers);
        else {
            numbers = Math.abs(numbers) * -1;
        }

        parseIntoStack(numbers);

        displayValue = String.valueOf(numbers);
    }

    public void multiplyPressed() {
        enteringDecimal = false;                                // now, prevent entering decimal for ever..
        if (currentNumber != 0) {
            double result = getStackNumbers() * currentNumber;     // get numbers from stack and multiply the current number
            displayValue = result + "";                         // for displaying the number
            parseIntoStack(result);                             // parse it into the stack again
            currentNumber = 0;
        }
    }

    public void minusPressed() {
        enteringDecimal = false;                                // now, prevent entering decimal for ever..
        if (currentNumber != 0) {
            double result = getStackNumbers() - currentNumber;     // get numbers from stack and subtract the current number
            displayValue = result + "";                         // for displaying the number
            parseIntoStack(result);                             // parse it into the stack again
            currentNumber = 0;
        }
    }

    public void enterPressed() {
        if (!enteringDecimal) {
            stack.add(Integer.toString(currentNumber));
            System.out.println("added to stack");
        }
        else {
            if (getStackNumbers() % 1 == 0 && decimal.getSize() > 0) decimal.getFirst();
            decimal.add(Integer.toString(currentNumber));
            System.out.println("added to decimal");
        }

        displayValue = getStackNumbers() + "";
        parseIntoStack(Double.parseDouble(displayValue));
        currentNumber = 0;

    }

    public void pointPressed() {
        if (getStackNumbers() % 1 == 0 && decimal.getSize() > 0) { // check if decimal is in there already
            if (enteringDecimal) enteringDecimal = false;
            else enteringDecimal = true;
        }
    }

    public void plusPressed() {
        enteringDecimal = false;                                // now, prevent entering decimal for ever..
        if (currentNumber != 0) {
            double result = getStackNumbers() + currentNumber;     // get numbers from stack and add the current number
            displayValue = result + "";                         // for displaying the number
            parseIntoStack(result);                             // parse it into the stack again
            currentNumber = 0;
        }


    }

    public void numberPressed(int digit) {
        currentNumber = digit;
        displayValue = digit +"";
    }

    private double getStackNumbers() {
        String number_formating = "";


            while (0 < decimal.getSize()) {
                number_formating = decimal.getFirst() + number_formating;
            }
            if (number_formating.length() > 0) number_formating = "." + number_formating;

        while (0 < stack.getSize()) {
            number_formating = stack.getFirst() + number_formating;
        }
        System.out.println(number_formating);
        return Double.parseDouble(number_formating);
    }
    private void parseIntoStack(double numbers) {
        String stringNumber = numbers + "";
        boolean isDecimal = false;
        for (char ch: stringNumber.toCharArray()) {
            if (!isDecimal)
                if (ch != '.')
                    stack.add(ch +"");
                else
                    isDecimal = true;
            else {
                decimal.add(ch +"");
            }
        }
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public String getVersion() {
        return version;
    }



}
