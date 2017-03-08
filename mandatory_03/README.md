        <h1>IS-211 Mandatory 3</h1>
        <p>This mandatory is an exercise in using stacks. Your task is to
           complete a simple calculator program, by implementing the
           CalculatorEngine interface. The user interface code is based
           on the Calculator example in the BlueJ book. In this exercise,
        however, you will make a Reverse Polish Notation (RPN) calculator.</p>
        <p>The visible difference between an RPN calculator and an ordinary
            calculator is that the RPN calculator does not have 
            the '=', '(', and ')' keys. Instead it has an enter key that
            is used to enter numbers. To compute 2*(3+5)/4 with an RPN
            calculator you have to press the following buttons:
            <kbd>3 enter 5 + 2 * 4 /</kbd>. This what happens:</p>
            <ul>
                <li><kbd>3</kbd> This is typing a number, just like any other calculator</li>
                <li><kbd>enter</kbd> The enter key ends the number input and pushes the number onto a stack</li>
                <li><kbd>5</kbd> Enters a second number</li>
                <li><kbd>+</kbd> Pressing an operator key (+ - * or /)
                    implicitly pushes the number that is being entered. Then
                    two numbers are popped, the operation is carried out. In
                this case 3 and 5 are popped, and added together. The result
                is pushed back onto the stack</li>
                <li><kbd>2</kbd> enters yet another number</li>
                <li><kbd>*</kbd> This will push 2, then pop 2 and 8 (the result
                from the first addition), and push 16 (2*8)</li>
                <li><kbd>4</kbd> enter a number</li>
                <li><kbd>/</kbd> a final operator</li>
            </ul>
        <p>To get this to work it is necessary to use a stack, when the enter 
            key is pressed a number is pushed onto the stack, when an
            operator key (+-*/) is pressed two numbers are popped, and the
            result of using the operator on the numbers is pushed back on the
            stack..
            The comments in the CalculatorEngine interface provide some
            more detail on how the calculator is supposed to work. There is an
            <a href="https://epxx.co/ctb/hp11c.html">emulation of the full
            functionality of the HP11C at https://epxx.co/ctb/hp11c.html</a>.
            You can ignore most of the keys to the left of the enter key, 
            except the left arrow which is the delete key.</p>
        <h2>Innlevering</h2>
        <p>Innleveringsfristen er torsdag 9. mars. Lever en zip/jar fil med kildekoden i innleveringsmappa i fronter

