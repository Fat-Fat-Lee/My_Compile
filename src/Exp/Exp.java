package Exp;

public class Exp {
    //四元式
    public String op;//运算符
    public String arg1;//左运算数
    public String arg2;//右运算数
    public String result;//运算结果存储位置
    public String type;//表达式类型

    public Exp() {
    }

    public Exp(String op, String arg1, String arg2, String result, String type) {
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.result = result;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Exp{" +
                "op='" + op + '\'' +
                ", arg1='" + arg1 + '\'' +
                ", arg2='" + arg2 + '\'' +
                ", result='" + result + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String generExpll(){
        String arg1_=new String();
        String arg2_=new String();
        if(arg1.startsWith("Number"))
            arg1_=arg1.substring(7,arg1.length()-1);
        else
            arg1_=arg1;

        if(arg2.startsWith("Number"))
            arg2_=arg2.substring(7,arg2.length()-1);
        else
            arg2_=arg2;


        if(op.equals("Plus"))
            return result+" = add i32 "+arg1_+","+arg2_+"\n";
        else if(op.equals("Minus"))
            return result+" = sub i32 "+arg1_+","+arg2_+"\n";
        else if(op.equals("Mult"))
            return result+" = mul i32 "+arg1_+","+arg2_+"\n";
        else if(op.equals("Div"))
            return result+" = sdiv i32 "+arg1_+","+arg2_+"\n";
        else if(op.equals("Percent"))
            return result+" = srem i32 "+arg1_+","+arg2_+"\n";
        return "";
    }

}