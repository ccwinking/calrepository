class Calculator {
    private int total = 0;
    public void doCalculate(char oper, int num) {
        switch (oper) {
            case '+':
                total += num;
                break;
            case '-':
                total -= num;
                break;
            case '*':
                total *= num;
                break;
            case '/':
                total /= num;
                break;
        }
    }
}


abstract class Command {
   abstract public void execute();
   abstract public void reverse();
}


class CalculatorCommand extends Command {
    char oper;
    int num;
    Calculator calculator;
 
    public CalculatorCommand(Calculator calculator, char oper, int num) {
        this.Calculator = calculator;
        this.oper = oper;
        this.num = num;
    }
 
    public char getOper() {
        return oper;
    }
 
    public void setOper(char oper) {
        this.oper = oper;
    }
 
    public int getNum() {
        return num;
    }
 
    public void setNum(int num) {
        this.num = num;
    }
 
    public Calculator getCalculator() {
        return Calculator;
    }
 
    public void setCalculator(Calculator Calculator) {
        this.Calculator = Calculator;
    }
 
    @Override
    public void execute() {
        Calculator.doCalculate(oper, num);
    }
 
    @Override
    public void reverse() {
        Calculator.doCalculate(undo(oper), num);
    }
 
    private char undo(char op) {
        char undo = ' ';
        switch (op) {
            case '+':
                undo = '-';
                break;
            case '-':
                undo = '+';
                break;
            case '*':
                undo = '/';
                break;
            case '/':
                undo = '*';
                break;
            }
        return undo;
    }
}


public class EnhanceCalculator {
    private Calculator calculator = new Calculator();
    private List<Command> commands = new ArrayList<Command>();
    private int curIndex = 0;

    //计算doCal
    public void doCalculate(char oper, int num) {
        Command command = new CalculatorCommand(calculator, oper, num);
        command.execute();
        commands.add(command);
        curIndex++;
    }

    //重做redo
    public void doRedoCal(int levels) {
        for (int i = 0; i < levels; i++) {
            if (curIndex < commands.size() - 1) {
                ((Command) commands.get(curIndex++)).execute();
            }
        }
    }

    //撤销undo
    public void doUndoCal(int levels) {
        for (int i = 0; i < levels; i++) {
            if (curIndex > 0) {
                int index = --curIndex;
                Command command= (Command)commands.get(index);
                command.reverse();
            }
        }
    }

}

