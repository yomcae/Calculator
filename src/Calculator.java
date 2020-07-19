

import java.util.Scanner;

public class Calculator {
    private int right;
    private int left;
    private String operation;

    Calculator(int left,int right, String operation) throws Exception{
        if(right>10 || right<1 || left>10 ||left<1)
            throw new Exception("Калькулятор принимает на вход числа от 1 до 10 включительно!");

        this.right=right;
        this.left=left;
        this.operation=operation;
    }

    public int result(){
        switch (operation){
            case "+":return left+right;
            case "-":return left-right;
            case "/":return left/right;
            case "*":return left*right;
            default:return -1;
        }
    }

    public static void main(String[] args) {
        try{
            Scanner scanner=new Scanner(System.in);

            System.out.println("Калькулятор: a + b, a - b, a * b, a / b.");
            System.out.println("Для выхода введите q.");
            System.out.println("Введите операцию:");
            String input="";

            while (true){
                input=scanner.nextLine();
                if(input.equals("q"))
                    break;
                Parser parser=new Parser(input);
                Convertor leftOperand=new Convertor(parser.getLeft());
                Convertor rightOperand =new Convertor(parser.getRight());

                boolean isArab=false;
                if(leftOperand.isArab() && rightOperand.isArab())
                    isArab=true;
                else if(leftOperand.isRoman() && rightOperand.isRoman())
                    isArab=false;
                else
                    throw new Exception("Калькулятор работает только с арабскими или римскими целыми числами одновременно!");

                Calculator calculator;

                if(isArab){
                    calculator=new Calculator(leftOperand.getArabNumber(),
                            rightOperand.getArabNumber(),
                            parser.getOperation());

                    System.out.println(calculator.result());
                }
                else{
                    calculator=new Calculator(leftOperand.romanToArab(),
                            rightOperand.romanToArab(),
                            parser.getOperation());

                    System.out.println(Convertor.arabToRoman(calculator.result()));
                }

            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
