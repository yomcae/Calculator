public class Parser {
    private String []operands;
    private String[] operations={"-","+","*","/"};
    private String operation;

    public Parser(String input) throws Exception {
        for(String str:operations)
            if(input.contains(str)) {
                operation=str;
                break;
            }


        if(operation==null)
            throw new Exception("Введенная операция не поддерживается!");

        String split=operation;
        if(operation=="+")
            split="\\+";
        if(operation=="*")
            split="\\*";

        operands=input.toUpperCase().trim().split(split);
    }

    public String getOperation() {
        return operation;
    }

    public String getLeft() {
        return operands[0];
    }

    public String getRight() {
        return operands[1];
    }
}
