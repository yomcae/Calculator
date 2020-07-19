

enum Roman {
    I(1),II(2),III(3),IV(4),V(5),
    VI(6),VII(7),VIII(8),IX(9),X(10),
    XX(20),XXX(30),XL(40),L(50),LX(60),
    LXX(70),LXXX(80),XC(90),C(100);

    int value;
    Roman(int number){
        this.value =number;
    }

    static String getNames(){
        StringBuilder stringBuilder=new StringBuilder();
        for(Roman rim :values())
            stringBuilder.append(rim.name());

        return stringBuilder.toString();
    }

    static int getValueByName(String str){
        for(Roman roman:values())
            if(roman.name().equals(String.valueOf(str)))
                return roman.value;

        return 0;
    }



    static Roman getByName(String name){
        for(Roman roman:values())
            if(roman.name().equals(name))
                return roman;

        return null;
    }

    static Roman getByValue(int value){
        for(Roman roman:values())
            if(roman.value==value)
                return roman;

        return null;
    }
}


public class Convertor {
    private String arabNumbers="1234567890";
    private String romanNumbers;
    private String operand;

    public Convertor(String input){
        romanNumbers=Roman.getNames();
        operand=input.trim();
    }

    public boolean isRoman(){
        for(char ch:operand.toCharArray())
            if(!romanNumbers.contains(String.valueOf(ch)))
                return false;

        return true;
    }

    public boolean isArab() {
        for(char ch:operand.toCharArray())
            if(!arabNumbers.contains(String.valueOf(ch)))
                return false;

        return true;
    }

    public int romanToArab() throws Exception{
        Roman roman=Roman.getByName(operand);
        if(roman!=null)
            return Roman.getValueByName(operand);


        int result=0;
        StringBuilder stringBuilder=new StringBuilder(operand);

        String cutRomanName;
        String fullRomanName="";

        int index=1;
        int indexLoop=0;
        int[]romNumbers={15,15};

        while(indexLoop!=2 ){
            do {
                if(stringBuilder.toString().equals(fullRomanName))
                    break;

                cutRomanName=stringBuilder.substring(0,index);
                roman=Roman.getByName(cutRomanName);

                if(roman==null)
                    break;
                index++;
                fullRomanName=cutRomanName;
            }while (true);

            int temp=Roman.getValueByName(fullRomanName);
            result+=temp;

            stringBuilder.delete(0,index-1);
            index=1;
            romNumbers[indexLoop]=temp;
            indexLoop++;
        }
        if(romNumbers[0]<10 && romNumbers[1]<10)
            throw new Exception("Неверный формат ввода!");

        return result;
    }

    public static String arabToRoman(int arab){
        if(arab==0)
            return "nulla";

        String negative="";
        if(arab<0){
            negative="-";
            arab=arab*(-1);
        }

        Roman roman=Roman.getByValue(arab);
        if(roman!=null)
            return negative+roman.name();

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(Roman.getByValue(arab/10*10));
        stringBuilder.append(Roman.getByValue(arab%10));

        stringBuilder.insert(0,negative);

        return stringBuilder.toString();
    }

    public int getArabNumber(){
        return Integer.parseInt(operand);
    }


}