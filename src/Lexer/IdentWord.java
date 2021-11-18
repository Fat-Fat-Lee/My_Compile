package Lexer;

import Analysis.analysis;
import Parser.Parser;
import Var.NumFunction;
import Var.NumNormal;
import Var.NumVar;

import java.util.List;

public class IdentWord {
    public String wordName;
    public String wordSymbol;
     public int wordValue;
    public String wordType;//numNormal是数字，numGroup是数组，numFunction是函数
    public boolean ifConst=false;//是否为固定标识符，只能赋值一次
    public NumVar wordNumVar;//根据类型进行标识符存储
    public int belongBlock;

    //生成函数标识符,声明函数
    public static IdentWord generIdentFunction(Lexer tmpLexer,List<String> resllList,String functionSymbol,int length,String returnType)
    {
        String tmpfunctionName=functionSymbol.substring(6,functionSymbol.length()-1);
        if(tmpLexer.identer(tmpfunctionName)!=null)
        {
            System.out.println("函数未声明");
            System.exit(3);
        }

        //该函数没有被声明过，加入
        IdentWord tmp=new IdentWord();
        tmp.wordName=functionSymbol.substring(6,functionSymbol.length()-1);
        tmp.wordSymbol=functionSymbol;
        tmp.wordType="numFunction";
        tmp.wordNumVar=new NumFunction(length,returnType);
        tmpLexer.identWordList.add(tmp);

        String tmpReturn=returnType;
        if(returnType.equals("int"))
            tmpReturn="i32";
        String params=new String();
        for(int i=0;i<length;i++)
            params+="i32"+",";
        if(params.endsWith(","))
            params=params.substring(0,params.length()-1);
        //生成规范参数列表形式和返回形式


        resllList.add("declare "+tmpReturn+" @"+tmp.wordName+"("+params+")\n");
        System.out.println("declare "+tmpReturn+" @"+tmp.wordName+"("+params+")\n");//打印一下
        return tmp;
    }
    //生成变量标识符,声明变量，该变量已定下来存储位置
    public static IdentWord generIdentNormal(Lexer tmpLexer, List<String> resllList,String functionSymbol, boolean ifConst,int belongBlock)
    {
        String tmpNormalName=functionSymbol.substring(6,functionSymbol.length()-1);
        if(tmpLexer.identerBlock(tmpNormalName)!=null) {
            System.out.println("该变量声明过");
            System.exit(3);//该变量声明过，报错
        }
        //该变量没有被声明过，加入
        IdentWord tmp=new IdentWord();
        tmp.wordName=functionSymbol.substring(6,functionSymbol.length()-1);
        tmp.wordSymbol=functionSymbol;
        tmp.wordType="numNormal";
        tmp.ifConst=ifConst;
        tmp.belongBlock=belongBlock;
        if(!Parser.global)
            tmp.wordNumVar=NumNormal.identNumNormal();//指定存储位置
        else
            tmp.wordNumVar=NumNormal.identNumGlobalNormal(tmp.wordName);//指定全局变量存储位置


        tmpLexer.identWordList.add(tmp);

        if(!Parser.global)
        {
            resllList.add(((NumNormal)tmp.wordNumVar).locate+"=alloca i32\n");//生成声明语句
            System.out.println(((NumNormal)tmp.wordNumVar).locate+"=alloca i32");//打印一下
        }

        return tmp;
    }
    //非const变量赋值函数
    public static IdentWord generAssignNormal(Lexer tmpLexer, List<String> resllList,String functionSymbol,String resString)
    {
        if(!resString.startsWith("i32"))
            resString="i32 "+resString;

        String tmpNormalName=functionSymbol.substring(6,functionSymbol.length()-1);
        if(tmpLexer.identer(tmpNormalName)==null) {
            System.out.println("该变量未声明过，无法赋值！！！");
            System.exit(3);//该变量声明过，报错
        }
        //该变量没有被声明过，加入
        IdentWord tmp=tmpLexer.identer(tmpNormalName);
        if(tmp.ifConst==true)
        {
            System.out.println("该变量为const变量，无法二次赋值！！！");
            System.exit(3);//该变量声明过，报错
        }
        if(tmp.wordType.equals("numFunction"))
        {
            System.out.println("该变量为函数变量，无法赋值！！！");
            System.exit(3);//该变量声明过，报错
        }

        resllList.add("store "+resString+","+"i32* "+((NumNormal)tmp.wordNumVar).locate+"\n");//生成赋值语句

        System.out.println("store "+resString+","+"i32* "+((NumNormal)tmp.wordNumVar).locate+"\n");//打印一下
        return tmp;
    }

    //非const global变量赋值函数
    public static IdentWord generAssignNormalGlobal(Lexer tmpLexer, List<String> resllList,String functionSymbol,String resString)
    {
        String tmpNormalName=functionSymbol.substring(6,functionSymbol.length()-1);
        if(tmpLexer.identer(tmpNormalName)==null) {
            System.out.println("该变量未声明过，无法赋值！！！");
            System.exit(3);//该变量声明过，报错
        }
        //该变量没有被声明过，加入
        IdentWord tmp=tmpLexer.identer(tmpNormalName);
        if(tmp.ifConst==true)
        {
            System.out.println("该变量为const变量，无法二次赋值！！！");
            System.exit(3);//该变量声明过，报错
        }
        if(tmp.wordType.equals("numFunction"))
        {
            System.out.println("该变量为函数变量，无法赋值！！！");
            System.exit(3);//该变量声明过，报错
        }
        tmp.wordValue=Integer.parseInt(resString);

        resllList.add(((NumNormal)tmp.wordNumVar).locate+"= dso_local global i32 "+resString+"\n");//生成赋值语句

        System.out.println(((NumNormal)tmp.wordNumVar).locate+"= dso_local global i32 "+resString+"\n");//打印一下
        return tmp;
    }







    //const变量赋值函数
    public static IdentWord generAssignConst(Lexer tmpLexer, List<String> resllList,String functionSymbol,String resString)
    {
        if(!resString.startsWith("i32"))
            resString="i32 "+resString;
        String tmpNormalName=functionSymbol.substring(6,functionSymbol.length()-1);
        if(tmpLexer.identer(tmpNormalName)==null) {
            System.out.println("该变量未声明过，无法赋值！！！");
            System.exit(3);//该变量声明过，报错
        }
        //该变量没有被声明过，加入
        IdentWord tmp=tmpLexer.identer(tmpNormalName);
        if(tmp.wordType.equals("numFunction"))
        {
            System.out.println("该变量为函数变量，无法赋值！！！");
            System.exit(3);//该变量声明过，报错
        }

        resllList.add("store "+resString+","+"i32* "+((NumNormal)tmp.wordNumVar).locate+"\n");//生成赋值语句

        System.out.println("store "+resString+","+"i32* "+((NumNormal)tmp.wordNumVar).locate+"\n");//打印一下
        return tmp;
    }

    //const  global变量赋值函数
    public static IdentWord generAssignConstGlobal(Lexer tmpLexer, List<String> resllList,String functionSymbol,String resString)
    {
        String tmpNormalName=functionSymbol.substring(6,functionSymbol.length()-1);
        if(tmpLexer.identer(tmpNormalName)==null) {
            System.out.println("该变量未声明过，无法赋值！！！");
            System.exit(3);//该变量声明过，报错
        }
        //该变量没有被声明过，加入
        IdentWord tmp=tmpLexer.identer(tmpNormalName);
        if(tmp.wordType.equals("numFunction"))
        {
            System.out.println("该变量为函数变量，无法赋值！！！");
            System.exit(3);//该变量声明过，报错
        }
        tmp.wordValue=Integer.parseInt(resString);

        resllList.add(((NumNormal)tmp.wordNumVar).locate+"= dso_local global i32 "+resString+"\n");//生成赋值语句

        System.out.println(((NumNormal)tmp.wordNumVar).locate+"= dso_local global i32 "+resString+"\n");//打印一下
        return tmp;
    }





    //变量加载函数
    public static String generLoadNormal(Lexer tmpLexer, List<String> resllList,String functionSymbol)
    {

        String tmpNormalName=functionSymbol.substring(6,functionSymbol.length()-1);
        if(tmpLexer.identer(tmpNormalName)==null) {
            System.out.println("该变量未声明过，无法赋值！！！");
            System.exit(3);//该变量声明过，报错
        }
        //该变量没有被声明过，加入
        IdentWord tmp=tmpLexer.identer(tmpNormalName);
        if(tmp.wordType.equals("numFunction"))
        {
            System.out.println("该变量为函数变量，无法用变量加载函数加载！！！");
            System.exit(3);//该变量声明过，报错
        }
        else
        {
            String retString=new String();
            ((NumNormal)tmp.wordNumVar).loadLocate= analysis.generStoreLocate();
            retString=((NumNormal)tmp.wordNumVar).loadLocate;
            String source=((NumNormal)tmp.wordNumVar).locate;
            resllList.add(retString+" = load i32, i32* "+source+"\n");//生成加载语句
            System.out.println(retString+" = load i32, i32* "+source+"\n");//打印一下
            return retString;
        }
        return "";
    }

}