package Exp;

import Analysis.analysis;

import java.util.List;

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
    public static String generZextll(String arg, List<String> resllList){
        String locate=analysis.generStoreLocate();
        resllList.add(locate+" = zext i1 "+arg+" to i32\n");
        System.out.println(locate+" = zext i1 "+arg+" to i32");
        return locate;
    }
    public static String generI1ll(String arg, List<String> resllList){
        String locate=analysis.generStoreLocate();
        resllList.add(locate+"= icmp ne i32 "+arg+", 0\n");
        System.out.println(locate+" = zext i1 "+arg+" to i32");
        return locate;
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

        else if(op.equals("Lt"))
            return result+" = icmp slt i32 "+arg1_+","+arg2_+"\n";
        else if(op.equals("Le"))
            return result + " = icmp sle i32 " + arg1_ + "," + arg2_ + "\n";
        else if(op.equals("Gt"))
            return result + " = icmp sgt i32 " + arg1_ + "," + arg2_ + "\n";
        else if(op.equals("Ge"))
            return result + " = icmp sge i32 " + arg1_ + "," + arg2_ + "\n";

        else if(op.equals("Eq"))
            return result + " = icmp eq i32 " + arg1_ + "," + arg2_ + "\n";
        else if(op.equals("Ne"))
            return result + " = icmp ne i32 " + arg1_ + "," + arg2_ + "\n";

        else if(op.equals("And"))
            return result+" = and i32 "+arg1_+","+arg2_+"\n";
        else if(op.equals("Or"))
            return result+" = or i32 "+arg1_+","+arg2_+"\n";

        return "";
    }

}
