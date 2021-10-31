package Parser;

import Analysis.AnalysisExp;
import Analysis.analysis;
import Lexer.Lexer;
import Lexer.IdentWord;
import Var.NumNormal;
import Var.RealFunction;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public String tmpSym;
    public int resLexerIndex=0;
    public String tmpNum;
    public void mainParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        getSym(resLexerList);
        if(tmpSym.equals("END"))
            System.exit(0);
        compUnitParser(tmpLexer,resLexerList,resllList);
    }

    public void compUnitParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        //  System.out.println("IN compunit");

        funcDefParser(tmpLexer,resLexerList,resllList);
        System.out.println("compUnit");
    }
    public void funcDefParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("IN funcdef");

        funcTypeParser(tmpLexer,resLexerList,resllList);
        if(tmpSym.startsWith("Ident")){
            getSym(resLexerList);
            if(tmpSym.equals("LPar")){
                getSym(resLexerList);
                if(tmpSym.equals("RPar")){
                    getSym(resLexerList);
                    blockParser(tmpLexer,resLexerList,resllList);
                }
                else
                    System.exit(2);
            }
            else
                System.exit(2);
        }
        System.out.println("funcDef");
    }



//-------------------------------------声明语句----------------------------------------------
    public void declParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("IN compunit");
        if(tmpSym.equals("Const"))
            constDeclParser(tmpLexer,resLexerList,resllList);
        else if(tmpSym.equals("Int"))
            varDeclParser(tmpLexer,resLexerList,resllList);
        else
            System.exit(2);

        System.out.println("delParser");
    }
    public void constDeclParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
          System.out.println("IN constDecl");
        if(tmpSym.equals("Const"))
        {
            getSym(resLexerList);
            bTypeParser(tmpLexer,resLexerList,resllList);
            constDefParser(tmpLexer,resLexerList,resllList);
            while(tmpSym.equals("Comma"))
            {
                getSym(resLexerList);
                constDefParser(tmpLexer,resLexerList,resllList);
            }
            if(tmpSym.equals("Semicolon"))
                getSym(resLexerList);
            else
                System.exit(2);
        }
        else
            System.exit(2);

        System.out.println("constDeclParser");
    }
    public void bTypeParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
          System.out.println("IN bType");
        if(tmpSym.equals("Int"))
            getSym(resLexerList);
        System.out.println("bType");
    }
    public void constDefParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
         System.out.println("IN constDef");
        if(tmpSym.startsWith("Ident"))
        {
            String varSym=tmpSym;//记录变量名，方便一会赋值
            getSym(resLexerList);
            if(tmpSym.equals("Assign"))
            {
                getSym(resLexerList);
                //语义动作alloca，并检查是否已声明
                IdentWord tmpWord=IdentWord.generIdentNormal(tmpLexer,resllList,varSym,true);
                String resString=constInitValParser(tmpLexer,resLexerList,resllList);
                //进行赋值
                IdentWord.generAssignConst(tmpLexer,resllList,varSym,resString);

            }
            else
                System.exit(2);
        }
        else
            System.exit(2);

        System.out.println("constDef");
    }
    public String constInitValParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("in constInitVal");
        int startExp=resLexerIndex-1;
        constExpParser(tmpLexer,resLexerList,resllList);
        int endExp=resLexerIndex-2;
        List<String>expAnalysisList=new ArrayList<>();
        for(int i=startExp;i<=endExp;i++) {
            expAnalysisList.add(resLexerList.get(i));
            System.out.println(resLexerList.get(i));
        }
        String resString=new AnalysisExp().mainAnalysisExp(tmpLexer,expAnalysisList,new analysis(),resllList);
        //顺利把表达式字符串数组送去变为ll编码了

        System.out.println("constInitVal");
        return resString;
    }
    public void constExpParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("IN constExp");
        addExpParser(tmpLexer,resLexerList,resllList);
        System.out.println("constExp");
    }
    public void varDeclParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("IN varDecl");
        bTypeParser(tmpLexer,resLexerList,resllList);
        varDefParser(tmpLexer,resLexerList,resllList);
        while(tmpSym.equals("Comma"))
        {
            getSym(resLexerList);
            varDefParser(tmpLexer,resLexerList,resllList);
        }
        if(tmpSym.equals("Semicolon"))
            getSym(resLexerList);
        else
            System.exit(3);
        System.out.println("varDecl");
    }
    public void varDefParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("IN varDef");
        if(tmpSym.startsWith("Ident")&&resLexerList.get(resLexerIndex).equals("Assign"))
        {
            //generNormal中生成声明语句
            String varSym=tmpSym;
            IdentWord tmpWord=IdentWord.generIdentNormal(tmpLexer,resllList,tmpSym,false);
            getSym(resLexerList);
            //进行赋值,生成赋值声明语句
            String resString=new String();
            getSym(resLexerList);
            resString=initValParser(tmpLexer,resLexerList,resllList);
            IdentWord.generAssignNormal(tmpLexer,resllList,varSym,resString);

        }
        else if(tmpSym.startsWith("Ident")&&!resLexerList.get(resLexerIndex).equals("Assign"))
        {
            String varSym=tmpSym;
            getSym(resLexerList);
            //generNormal中生成声明语句
            IdentWord tmpWord=IdentWord.generIdentNormal(tmpLexer,resllList,varSym,false);
        }

        else
            System.exit(2);
        System.out.println("varDef");
    }
    public String initValParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("in initVal");
        int startExp=resLexerIndex-1;
        expParser(tmpLexer,resLexerList,resllList);
        int endExp=resLexerIndex-2;
        List<String>expAnalysisList=new ArrayList<>();
        //System.out.println("表达式在这里！");
        for(int i=startExp;i<=endExp;i++) {
            expAnalysisList.add(resLexerList.get(i));
            System.out.println(resLexerList.get(i));
        }
        System.out.println("initVal");
        return new AnalysisExp().mainAnalysisExp(tmpLexer,expAnalysisList,new analysis(),resllList);
        //顺利把表达式字符串数组送去变为ll编码了


    }
//--------------------------声明赋值部分结束---------------------------------------

    public void funcTypeParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("IN functype");
        if(tmpSym.equals("Int"))
        {
            getSym(resLexerList);
            System.out.println("funcType");
        }
        else
            System.exit(2);
    }


    public void blockParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("IN block");

        if(tmpSym.equals("LBrace")){
            getSym(resLexerList);
            resllList.add("define dso_local i32 @main(){"+"\n");
            while(tmpSym.equals("LPar")||tmpSym.equals("Plus")||tmpSym.equals("Minus")||
                    tmpSym.equals("Return")||tmpSym.equals("Const")||tmpSym.equals("Int")||
                    tmpSym.startsWith("Ident")||tmpSym.startsWith("Number"))
                blockItemParser(tmpLexer,resLexerList,resllList);
            if(tmpSym.equals("RBrace")) {
                getSym(resLexerList);
                resllList.add("}"+"\n");
            }
            else
                System.exit(2);
        }
        else
            System.exit(2);
        System.out.println("block");
    }
    public void blockItemParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("IN blockItem");
        //System.out.println("----------checking blockItem--------"+tmpSym);
        if(tmpSym.equals("Const")||tmpSym.equals("Int"))
            declParser(tmpLexer,resLexerList,resllList);
        else if(tmpSym.equals("LPar")||tmpSym.equals("Plus")||tmpSym.equals("Minus")||
                tmpSym.equals("Return")|| tmpSym.startsWith("Ident")||tmpSym.startsWith("Number"))
            stmtParser(tmpLexer,resLexerList,resllList);
        else
            System.exit(2);
        System.out.println("blockItem");

    }

    public void stmtParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("in stmt");
        //若是Ident Assign就是第一个候选式
        if(tmpSym.startsWith("Ident")&&resLexerList.get(resLexerIndex).equals("Assign"))
        {
            String varSym=tmpSym;
            lValParser(tmpLexer,resLexerList,resllList);
            if(tmpSym.equals("Assign"))
            {
                getSym(resLexerList);
                int startExp=resLexerIndex-1;
                expParser(tmpLexer,resLexerList,resllList);
                int endExp=resLexerIndex-2;
                List<String>expAnalysisList=new ArrayList<>();
                for(int i=startExp;i<=endExp;i++) {
                    expAnalysisList.add(resLexerList.get(i));
                    System.out.println(resLexerList.get(i));
                }
                String resString=new String();
                resString=new AnalysisExp().mainAnalysisExp(tmpLexer,expAnalysisList,new analysis(),resllList);
                //顺利把表达式字符串数组送去变为ll编码了
                //生成赋值语句啦
                IdentWord.generAssignNormal(tmpLexer,resllList,varSym,resString);

                if(tmpSym.equals("Semicolon"))
                    getSym(resLexerList);
                else
                    System.exit(2);
            }
            else
                System.exit(2);
        }
        else if(tmpSym.equals("LPar")||tmpSym.equals("Plus")||tmpSym.equals("Minus")||
                tmpSym.startsWith("Ident")||tmpSym.startsWith("Number")||tmpSym.equals("Semicolon"))
        {
            if(tmpSym.equals("Semicolon"))
                getSym(resLexerList);
            else
            {
                int startExp=resLexerIndex-1;
                expParser(tmpLexer,resLexerList,resllList);
                int endExp=resLexerIndex-2;
                List<String>expAnalysisList=new ArrayList<>();
                for(int i=startExp;i<=endExp;i++) {
                    expAnalysisList.add(resLexerList.get(i));
                    System.out.println(resLexerList.get(i));
                }
                new AnalysisExp().mainAnalysisExp(tmpLexer,expAnalysisList,new analysis(),resllList);
                //顺利把表达式字符串数组送去变为ll编码了

                if(tmpSym.equals("Semicolon"))
                    getSym(resLexerList);
                else
                    System.exit(2);

            }
        }
        else if(tmpSym.equals("Return"))
        {
            getSym(resLexerList);

            int startExp=resLexerIndex-1;
            expParser(tmpLexer,resLexerList,resllList);
            int endExp=resLexerIndex-2;
            List<String>expAnalysisList=new ArrayList<>();
            for(int i=startExp;i<=endExp;i++) {
                expAnalysisList.add(resLexerList.get(i));
                System.out.println(resLexerList.get(i));
            }
            String resString=new String();
            resString=new AnalysisExp().mainAnalysisExp(tmpLexer,expAnalysisList,new analysis(),resllList);
            //顺利把表达式字符串数组送去变为ll编码了
            //接下来生成返回语句
            resllList.add("ret "+resString+"\n");
            System.out.println("ret "+resString+"\n");

            if(tmpSym.equals("Semicolon"))
                getSym(resLexerList);
            else
                System.exit(2);
        }
        else
            System.exit(2);

        System.out.println("stmt");
    }


    //--------------------------------------------表达式相关----------------------------------------------------
    public void expParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
          System.out.println("IN exp");
        addExpParser(tmpLexer,resLexerList,resllList);
        System.out.println("expParser");
    }

    public void lValParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        if(tmpSym.startsWith("Ident"))
            getSym(resLexerList);
        else
            System.exit(2);
        System.out.println("lVal");

    }
    public void funcParamsParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("IN funcParams");

        expParser(tmpLexer,resLexerList,resllList);
        while(tmpSym.equals("Comma"))
        {
            getSym(resLexerList);
            expParser(tmpLexer,resLexerList,resllList);
        }
        System.out.println("funcParams");

    }

    public void addExpParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("IN addExp");
        mulExpParser(tmpLexer,resLexerList,resllList);
        while(tmpSym.equals("Plus")||tmpSym.equals("Minus")){
            getSym(resLexerList);
            mulExpParser(tmpLexer,resLexerList,resllList);
        }
        System.out.println("addExpParser");
    }
    public void mulExpParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("IN mulExp");
        unaryExpParser(tmpLexer,resLexerList,resllList);
        while(tmpSym.equals("Mult")||tmpSym.equals("Div")||tmpSym.equals("Percent")){
            getSym(resLexerList);
            unaryExpParser(tmpLexer,resLexerList,resllList);
        }
       // System.out.println(tmpSym);
        System.out.println("mulExpParser");
    }
    public void unaryExpParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("IN unaryExp");
        System.out.println(tmpSym);
        String funcSym=tmpSym;
       if(tmpSym.startsWith("Ident")&&resLexerList.get(resLexerIndex).equals("LPar"))
        {
            getSym(resLexerList);
            getSym(resLexerList);
            if(tmpSym.equals("RPar"))
            {//函数参数为零，直接生成
                getSym(resLexerList);
            }
            else{
                funcParamsParser(tmpLexer,resLexerList,resllList);
                while(tmpSym.equals("LPar")||tmpSym.equals("Plus")||tmpSym.equals("Minus")||
                        tmpSym.equals("Return")||tmpSym.equals("Const")||tmpSym.equals("Int")||
                        tmpSym.startsWith("Ident")||tmpSym.startsWith("Number"))
                    funcParamsParser(tmpLexer,resLexerList,resllList);
                if(tmpSym.equals("RPar"))
                    getSym(resLexerList);
                else
                    System.exit(2);

            }

        }
        else if(tmpSym.equals("LPar")||tmpSym.startsWith("Number")||tmpSym.startsWith("Ident"))
            primaryExpParser(tmpLexer,resLexerList,resllList);

        else if(tmpSym.equals("Plus")||tmpSym.equals("Minus"))
        {
            unaryOpParser(tmpLexer,resLexerList,resllList);
            unaryExpParser(tmpLexer,resLexerList,resllList);
        }
        else
            System.exit(2);
        System.out.println("unaryExpParser");
    }
    public void primaryExpParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("IN primaryExp");
        if(tmpSym.equals("LPar"))
        {
            getSym(resLexerList);
            expParser(tmpLexer,resLexerList,resllList);
            if(tmpSym.equals("RPar"))
            {
                getSym(resLexerList);
                System.out.println("primaryExpParser");
            }
            else
                System.exit(2);
        }
        else{
            if(tmpSym.startsWith("Number"))
            {
                //tmpNum=tmpSym.substring(7,tmpSym.length()-1);
                getSym(resLexerList);
            }
            else if(tmpSym.startsWith("Ident"))
            {
                lValParser(tmpLexer,resLexerList,resllList);
            }
            else
                System.exit(2);
        }
        System.out.println("primaryExpParser");
    }
    public void unaryOpParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("IN unaryOp");
        if(tmpSym.equals("Plus")||tmpSym.equals("Minus"))
            getSym(resLexerList);
        else
            System.exit(2);
        System.out.println("unaryOpParser");
    }



    public boolean getSym(List<String> resLexerList){
        if(resLexerIndex<resLexerList.size())
        {
            tmpSym=resLexerList.get(resLexerIndex++);
            return true;
        }
        else
        {
            tmpSym="END";
            return false;
        }

    }

}
