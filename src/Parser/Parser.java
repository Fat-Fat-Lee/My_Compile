package Parser;

import Analysis.AnalysisExp;
import Analysis.analysis;
import Lexer.Lexer;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public String tmpSym;
    public int resLexerIndex=0;
    public String tmpNum;
    public void mainParser(List<String> resLexerList,List<String> resllList){
        getSym(resLexerList);
        if(tmpSym.equals("END"))
            System.exit(0);
        compUnitParser(resLexerList,resllList);
    }
    public void compUnitParser(List<String> resLexerList,List<String> resllList){
      //  System.out.println("IN compunit");

        funcDefParser(resLexerList,resllList);
        System.out.println("compUnit");
    }
    public void funcDefParser(List<String> resLexerList,List<String> resllList){
        //System.out.println("IN funcdef");

        funcTypeParser(resLexerList,resllList);
        while(tmpSym.startsWith("Ident")){
            getSym(resLexerList);
            if(tmpSym.equals("LPar")){
                getSym(resLexerList);
                if(tmpSym.equals("RPar")){
                    getSym(resLexerList);
                    blockParser(resLexerList,resllList);
                }
                else
                    System.exit(2);
            }
            else
                System.exit(2);
        }
        System.out.println("funcDef");
    }
    public void blockParser(List<String> resLexerList,List<String> resllList){
        System.out.println("IN block");

        if(tmpSym.equals("LBrace")){
            getSym(resLexerList);
            resllList.add("define dso_local i32 @main(){"+"\n");
            stmtParser(resLexerList,resllList);
            if(tmpSym.equals("RBrace"))
            {
                getSym(resLexerList);
                resllList.add("}"+"\n");
                System.out.println("block");
            }
            else
                System.exit(2);
        }
        else
            System.exit(2);
    }
    public void funcTypeParser(List<String> resLexerList,List<String> resllList){
      //  System.out.println("IN functype");
        if(tmpSym.equals("Int"))
        {
            getSym(resLexerList);
            System.out.println("funcType");
        }
        else
            System.exit(2);
    }
    public void stmtParser(List<String> resLexerList,List<String> resllList){
        System.out.println("IN stmt");

        if(tmpSym.equals("Return"))
        {
            getSym(resLexerList);
            //记录表达式头尾位置，提取表达式字符串数组
            int startExp=resLexerIndex-1;
            expParser(resLexerList,resllList);
            int endExp=resLexerIndex-2;
            List<String>expAnalysisList=new ArrayList<>();
            for(int i=startExp;i<=endExp;i++) {
                expAnalysisList.add(resLexerList.get(i));
                System.out.println(resLexerList.get(i));
            }
            new AnalysisExp().mainAnalysisExp(expAnalysisList,new analysis(),resllList);
            //顺利把表达式字符串数组送去变为ll编码了


            if(tmpSym.equals("Semicolon"))
            {
                getSym(resLexerList);
                System.out.println("stmt");
            }
            else
                System.exit(2);
           /* if(tmpSym.startsWith("Number"))
            {
                tmpNum=tmpSym.substring(7,tmpSym.length()-1);
                getSym(resLexerList);
                if(tmpSym.equals("Semicolon"))
                {
                    getSym(resLexerList);
                  //  System.out.println("stmt");
                }
                else
                    System.exit(2);
            }
            else
                System.exit(2);

            */
        }
        else
            System.exit(2);

    }
    public void expParser(List<String> resLexerList,List<String> resllList){
      //  System.out.println("IN exp");
        addExpParser(resLexerList,resllList);
        System.out.println("expParser");
    }
    public void addExpParser(List<String> resLexerList,List<String> resllList){
       // System.out.println("IN addExp");
        mulExpParser(resLexerList,resllList);
        while(tmpSym.equals("Plus")||tmpSym.equals("Minus")){
            getSym(resLexerList);
            mulExpParser(resLexerList,resllList);
        }
        System.out.println("addExpParser");
    }
    public void mulExpParser(List<String> resLexerList,List<String> resllList){
       // System.out.println("IN mulExp");
        unaryExpParser(resLexerList,resllList);
        while(tmpSym.equals("Mult")||tmpSym.equals("Div")||tmpSym.equals("Percent")){
            getSym(resLexerList);
            unaryExpParser(resLexerList,resllList);
        }
        System.out.println(tmpSym);
        System.out.println("mulExpParser");
    }
    public void unaryExpParser(List<String> resLexerList,List<String> resllList){
       // System.out.println("IN unaryExp");
       // System.out.println(tmpSym);
        if(tmpSym.equals("LPar")||tmpSym.startsWith("Number"))
            primaryExpParser(resLexerList,resllList);
        else if(tmpSym.equals("Plus")||tmpSym.equals("Minus"))
        {
            unaryOpParser(resLexerList,resllList);
            unaryExpParser(resLexerList,resllList);
        }
        else
            System.exit(2);
        System.out.println("unaryExpParser");
    }
    public void primaryExpParser(List<String> resLexerList,List<String> resllList){
       // System.out.println("IN primaryExp");
        if(tmpSym.equals("LPar"))
        {
            getSym(resLexerList);
            expParser(resLexerList,resllList);
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
            else
                System.exit(2);
        }
        System.out.println("primaryExpParser");
    }
    public void unaryOpParser(List<String> resLexerList,List<String> resllList){
       // System.out.println("IN unaryOp");
        if(tmpSym.equals("Plus")||tmpSym.equals("Minus"))
            getSym(resLexerList);
        else
            System.exit(2);
        System.out.println("unaryOpParser");
    }



    public void getSym(List<String> resLexerList){
        if(resLexerIndex<resLexerList.size())
            tmpSym=resLexerList.get(resLexerIndex++);
        else
            tmpSym="END";
        return;
    }
}
