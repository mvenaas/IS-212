package mandatory3;


public class Hp11cEngine implements CalculatorEngine {
    private String title = "Mandatory 3 - IS-211";
    private String author = "Christiand og Erlend";
    private String version = "1.1";
    private Stack<String> stack;
    private Stack<String> decimal;
    private String displayValue;
    private int currentNumber;
    private boolean enteringDecimal;

    public Hp11cEngine() {
        this.stack = new Stack<>(10);
        this.decimal = new Stack<>(10);
        this.enteringDecimal = false;
    }

    @Override
    public void numberPressed(int number) {
        currentNumber = number;
        displayValue = number +"";
    }

    @Override
    public void pointPressed() {
        if (getStackNumbers() % 1 == 0 && decimal.getSize() > 0) { // check if decimal is in there already
            if (enteringDecimal) enteringDecimal = false;
            else enteringDecimal = true;
        }
    }

    @Override
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

    @Override
    public void clearPressed() {
        this.stack = new Stack<>(10);
        this.decimal = new Stack<>(10);
        displayValue = "";
        this.enteringDecimal = false;
    }

    @Override
    public void plusPressed() {
        enteringDecimal = false;                                // now, prevent entering decimal for ever..
        if (currentNumber != 0) {
            double result = getStackNumbers() + currentNumber;     // get numbers from stack and add the current number
            displayValue = result + "";                         // for displaying the number
            parseIntoStack(result);                             // parse it into the stack again
            currentNumber = 0;
        }
    }

    @Override
    public void minusPressed() {
        enteringDecimal = false;                                // now, prevent entering decimal for ever..
        if (currentNumber != 0) {
            double result = getStackNumbers() - currentNumber;     // get numbers from stack and subtract the current number
            displayValue = result + "";                         // for displaying the number
            parseIntoStack(result);                             // parse it into the stack again
            currentNumber = 0;
        }
    }

    @Override
    public void multiplyPressed() {
        enteringDecimal = false;                                // now, prevent entering decimal for ever..
        if (currentNumber != 0) {
            double result = getStackNumbers() * currentNumber;     // get numbers from stack and multiply the current number
            displayValue = result + "";                         // for displaying the number
            parseIntoStack(result);                             // parse it into the stack again
            currentNumber = 0;
        }
    }

    @Override
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

    @Override
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

    @Override
    public String getAuthor() {
        return this.author;
    }

    @Override
    public String getDisplayValue() {
        return this.displayValue;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getVersion() {
        return this.version;
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
}
