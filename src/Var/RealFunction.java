package Var;

import Analysis.analysis;
import Lexer.IdentWord;
import Lexer.Lexer;

import java.util.ArrayList;
import java.util.List;

//NumFunction只是声明壳子，调用在RealFunction
public class RealFunction {
    //调用函数， 根据输入的函数位置名和参数名生成相关ll代码
    public static void generFunctionll(List<String> resllList,Lexer tmpLexer, String functionSymbol, ArrayList<String>params)
    {
        String locate;//函数结果存储位置
        int length;//参数个数,无需存储参数类型，因为只会是int
        ArrayList<String> paramList=new ArrayList<>();//参数存储位置
        String returnType;//返回值类型int或void

        IdentWord tmp;
        String tmpfunctionName=functionSymbol.substring(6,functionSymbol.length()-1);
        if(tmpLexer.identer(tmpfunctionName)!=null)
        {
            tmp = tmpLexer.identer(tmpfunctionName);
            if(tmp.wordNumVar instanceof NumFunction)
            {
                returnType=((NumFunction) tmp.wordNumVar).returnType;
                paramList=params;
                if(paramList.size()!=((NumFunction) tmp.wordNumVar).length)
                {
                    System.out.println("函数参数数量有误");
                    System.exit(3);
                }
                //参数表里面全都是合法参数了
                String paramThing=new String();
                for(String k:paramList) {
                    if(k.startsWith("Number"))
                        k=k.substring(7,k.length()-1);//要是普通的Number要去掉外壳
                    paramThing+="i32 " + k+",";
                }
                if(paramThing.endsWith(","))
                    paramThing=paramThing.substring(0,paramThing.length()-1);
                if(returnType.equals("int")) {
                    returnType = "i32";
                    locate=analysis.generStoreLocate();
                    resllList.add(locate+"= call "+returnType+" "+"@"+tmpfunctionName+"("+paramThing+")"+"\n");
                    System.out.println(locate+"= call "+returnType+" "+"@"+tmpfunctionName+"("+paramThing+")"+"\n");
                }
                else {
                    resllList.add("call " + "void" + " " + "@" + tmpfunctionName + "(" + paramThing + ")" + "\n");
                    System.out.println("call " + "void" + " " + "@" + tmpfunctionName + "(" + paramThing + ")" + "\n");
                }


                //调用函数ll语句输出完毕
            }
            else
            {
                System.out.println("该变量不是函数");
                System.exit(3);
            }
        }
        else
        {
            System.out.println("函数未声明，不可调用");
            System.exit(3);
        }
       return;
    }

}
