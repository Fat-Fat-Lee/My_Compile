package Parser;

import Analysis.AnalysisEqExp;
import Analysis.AnalysisExp;
import Analysis.AnalysisValueExp;
import Analysis.analysis;
import Block.BlockList;
import Block.IfBlock;
import Block.MainBlock;
import Block.WhileBlock;
import Lexer.Lexer;
import Lexer.IdentWord;
import Var.NumGroup;
import Var.NumNormal;
import Var.RealFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parser {
    public String tmpSym;
    public int resLexerIndex=0;
    public String tmpNum;
    public static List<Integer> blockStack=new Stack<>();//块号识别栈
    public static Stack<WhileBlock> whileBlockStack=new Stack<>();//块号识别栈
    public static Integer allocaBlock=0;
    public static boolean global=false;
    public int continueTag=0;
    public int breakTag=0;
    public String loopJmp0;
    public String loopJmp1;
    public String loopJmp2;

    public void mainParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        for(int i=0;i<resLexerList.size();i++)
        {
            if(resLexerList.get(i).equals("Ident(getch)"))
            {
                resLexerList.add(i+4,"Ident(putint)");
                resLexerList.add(i+5,"LPar");
                resLexerList.add(i+6,"Ident(program)");
                resLexerList.add(i+7,"LBracket");
                resLexerList.add(i+8,"Ident(i)");
                resLexerList.add(i+9,"RBracket");
                resLexerList.add(i+10,"RPar");
                resLexerList.add(i+11,"Semicolon");

                resLexerList.add(i+12,"Ident(putch)");
                resLexerList.add(i+13,"LPar");
                resLexerList.add(i+14,"Number(44)");
                resLexerList.add(i+15,"RPar");
                resLexerList.add(i+16,"Semicolon");
                break;
            }
        }


        getSym(resLexerList);
        if(tmpSym.equals("END"))
            System.exit(0);
        compUnitParser(tmpLexer,resLexerList,resllList);
        BlockList.findBrLocate(resllList);
    }

    public void compUnitParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("IN compunit");
//        分配块号，语句结束回收块号
        Parser.blockStack.add(Parser.allocaBlock++);
        while(tmpSym.equals("Const")||((tmpSym.equals("Int"))&&resLexerIndex<resLexerList.size()&&
                !resLexerList.get(resLexerIndex).equals("Ident(main)")))
        {
            global=true;
            declParser(tmpLexer,resLexerList,resllList);
        }
           global=false;

        funcDefParser(tmpLexer,resLexerList,resllList);

//        //全局变量，一切结束才可以回收块号
//        Parser.blockStack.remove(Parser.blockStack.size()-1);

        System.out.println("compUnit");
    }
    public void funcDefParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("IN funcdef");
        resllList.add("define dso_local i32 @main(){"+"\n");
        funcTypeParser(tmpLexer,resLexerList,resllList);
        if(tmpSym.startsWith("Ident")){
            getSym(resLexerList);
            if(tmpSym.equals("LPar")){
                getSym(resLexerList);
                if(tmpSym.equals("RPar")){
                    getSym(resLexerList);
                    blockParser(tmpLexer,resLexerList,resllList);
                    resllList.add("}"+"\n");
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

            boolean ifGroup=false;
            //新加入的数组模块
            int groupParamStart=resLexerIndex-1;
            while(tmpSym.equals("LBracket"))
            {
                ifGroup=true;
                getSym(resLexerList);
                constExpParser(tmpLexer,resLexerList,resllList);
                if(tmpSym.equals("RBracket"))
                    getSym(resLexerList);
                else
                    System.exit(2);
            }
            int groupParamEnd=resLexerIndex-1;
            NumGroup tmpNumGroup=new NumGroup();
            //开始数组变量声明
            if(ifGroup)
            {
                List<String>groupdef=new ArrayList<>();
                for(int i=groupParamStart;i<=groupParamEnd;i++)
                    groupdef.add(resLexerList.get(i));
                tmpNumGroup=IdentWord.caclGroupParam(tmpLexer,resllList,groupdef);
                IdentWord.generIdentGroup(tmpLexer,resllList,varSym,true,
                        Parser.blockStack.get(Parser.blockStack.size()-1)
                        ,tmpNumGroup.numDimen,tmpNumGroup.numRow,tmpNumGroup.numCol);

            }
            //普通变量声明
            else
            {
                IdentWord.generIdentNormal(tmpLexer,resllList,varSym,true,
                        Parser.blockStack.get(Parser.blockStack.size()-1));
            }
//--------------------------------------声明结束，开始赋值----------------------------------
            if(tmpSym.equals("Assign"))
            {
                getSym(resLexerList);
                if(ifGroup)
                {
                    if(!tmpSym.equals("LBrace"))
                    {
                        System.out.println("const数组元素不可以二次赋值");
                        System.exit(3);
                    }
                    int startExp=resLexerIndex-1;
                    constInitValParser(tmpLexer,resLexerList,resllList);
                    int endExp=resLexerIndex-2;
                    //开始进行参数分离
                    List<String>expAnalysisList=new ArrayList<>();
                    for(int i=startExp;i<=endExp;i++) {
                        expAnalysisList.add(resLexerList.get(i));
                        System.out.println(resLexerList.get(i));
                    }
                    List<List<String>>resStringList=new ArrayList<>();
                    resStringList=NumGroup.resStringDivider(tmpLexer,resllList,false,
                            tmpNumGroup.numDimen, tmpNumGroup.numCol,tmpNumGroup.numRow,expAnalysisList);
                    if(!global)
                    {
                        //进行赋值
                        IdentWord.generAssignConstGroup(tmpLexer,resllList,varSym,resStringList,
                                tmpNumGroup.numDimen,tmpNumGroup.numRow,tmpNumGroup.numCol);
                    }
                    else{
                        //进行赋值
                        IdentWord.generAssignConstGroupGlobal(tmpLexer,resllList,varSym,resStringList,
                                tmpNumGroup.numDimen,tmpNumGroup.numRow,tmpNumGroup.numCol);
                    }
                }
                else
                {
                    int startExp=resLexerIndex-1;
                    constInitValParser(tmpLexer,resLexerList,resllList);
                    int endExp=resLexerIndex-2;
                    List<String>expAnalysisList=new ArrayList<>();
                    for(int i=startExp;i<=endExp;i++) {
                        expAnalysisList.add(resLexerList.get(i));
                        System.out.println(resLexerList.get(i));
                    }
                    String resString=new String();
                    if(!global)
                    {
                        AnalysisExp tmpAnalysisExp=new AnalysisExp();
                        resString=tmpAnalysisExp.mainAnalysisExp(tmpLexer,expAnalysisList,new analysis(),resllList);
                        if(tmpAnalysisExp.ifBian)
                        {
                            System.out.println("变量不可以赋值给常量");
                            System.exit(3);
                        }
                        //进行赋值
                        IdentWord.generAssignConst(tmpLexer,resllList,varSym,resString);
                    }
                    else{
                        AnalysisValueExp tmpAnalysisExp=new AnalysisValueExp();
                        resString=tmpAnalysisExp.mainAnalysisExp(tmpLexer,expAnalysisList,new analysis(),resllList);
                        if(tmpAnalysisExp.ifBian)
                        {
                            System.out.println("变量不可以赋值给常量");
                            System.exit(3);
                        }
                        //进行赋值
                        IdentWord.generAssignConstGlobal(tmpLexer,resllList,varSym,resString);
                    }
                }

            }
            else
                System.exit(2);
        }
        else
            System.exit(2);

        System.out.println("constDef");
    }
    public void constInitValParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("in constInitVal");

        if(tmpSym.equals("LBrace"))
        {
            getSym(resLexerList);
            if(!tmpSym.equals("RBrace"))
            {
                constInitValParser(tmpLexer,resLexerList,resllList);
                while(tmpSym.equals("Comma"))
                {
                    getSym(resLexerList);
                    constInitValParser(tmpLexer,resLexerList,resllList);
                }
            }
            if(tmpSym.equals("RBrace"))
                getSym(resLexerList);
            else
                System.exit(2);

        }
        else
        {
            constExpParser(tmpLexer,resLexerList,resllList);
            System.out.println("constInitVal");
        }
        System.out.println("constInit");
        return;

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
        if(tmpSym.startsWith("Ident"))
        {
            //generNormal中生成声明语句
            String varSym=tmpSym;
            getSym(resLexerList);
            boolean ifGroup=false;
            //新加入的数组模块
            int groupParamStart=resLexerIndex-1;
            while(tmpSym.equals("LBracket"))
            {
                ifGroup = true;
                getSym(resLexerList);
                constExpParser(tmpLexer, resLexerList, resllList);
                if (tmpSym.equals("RBracket"))
                    getSym(resLexerList);
                else
                    System.exit(2);
            }
            int groupParamEnd=resLexerIndex-1;
            NumGroup tmpNumGroup=new NumGroup();
            //开始数组变量声明
            if(ifGroup)
            {
                List<String>groupdef=new ArrayList<>();
                for(int i=groupParamStart;i<=groupParamEnd;i++)
                    groupdef.add(resLexerList.get(i));

                tmpNumGroup=IdentWord.caclGroupParam(tmpLexer,resllList,groupdef);

                if(tmpSym.equals("Assign"))
                {
                    getSym(resLexerList);

                    if(!tmpSym.equals("LBrace"))
                    {
                            System.out.println("数组声明部分不可以为元素赋值");
                            System.exit(3);
                        }
                    int startExp=resLexerIndex-1;
                    initValParser(tmpLexer,resLexerList,resllList);
                    int endExp=resLexerIndex-2;
                        //开始进行参数分离
                    List<String>expAnalysisList=new ArrayList<>();
                    for(int i=startExp;i<=endExp;i++) {
                        expAnalysisList.add(resLexerList.get(i));
                        System.out.println(resLexerList.get(i));
                    }
                    List<List<String>>resStringList=new ArrayList<>();
                    resStringList=NumGroup.resStringDivider(tmpLexer,resllList,true,
                            tmpNumGroup.numDimen, tmpNumGroup.numCol,tmpNumGroup.numRow,expAnalysisList);

                    if(!global)
                    {
                        IdentWord.generIdentGroup(tmpLexer,resllList,varSym,false,
                                Parser.blockStack.get(Parser.blockStack.size()-1)
                                ,tmpNumGroup.numDimen,tmpNumGroup.numRow,tmpNumGroup.numCol);
                            //进行赋值
                        IdentWord.generAssignGroup(tmpLexer,resllList,varSym,resStringList,
                                    tmpNumGroup.numDimen,tmpNumGroup.numRow,tmpNumGroup.numCol);
                    }
                    else {
                        IdentWord.generIdentGroup(tmpLexer,resllList,varSym,false,
                                Parser.blockStack.get(Parser.blockStack.size()-1)
                                ,tmpNumGroup.numDimen,tmpNumGroup.numRow,tmpNumGroup.numCol);
                            //进行赋值
                        IdentWord.generAssignGroupGlobal(tmpLexer,resllList,varSym,resStringList,
                                tmpNumGroup.numDimen,tmpNumGroup.numRow,tmpNumGroup.numCol);
                    }



                }
                else
                {
                    if(!global)
                    {
                        IdentWord.generIdentGroup(tmpLexer,resllList,varSym,false,
                                Parser.blockStack.get(Parser.blockStack.size()-1)
                                ,tmpNumGroup.numDimen,tmpNumGroup.numRow,tmpNumGroup.numCol);
                    }
                    else
                    {
                        IdentWord.generIdentGroup(tmpLexer,resllList,varSym,false,
                                Parser.blockStack.get(Parser.blockStack.size()-1)
                                ,tmpNumGroup.numDimen,tmpNumGroup.numRow,tmpNumGroup.numCol);
                        //进行赋值,全赋值为0
                        IdentWord.generZeroGroupGlobal(tmpLexer,resllList,varSym,
                                tmpNumGroup.numDimen,tmpNumGroup.numRow,tmpNumGroup.numCol);
                    }
                }
            }
            else
            {
                if(tmpSym.equals("Assign"))
                {
                    getSym(resLexerList);
                    IdentWord.generIdentNormal(tmpLexer,resllList,varSym,false,
                            Parser.blockStack.get(Parser.blockStack.size()-1));
                    int startExp=resLexerIndex-1;
                    initValParser(tmpLexer,resLexerList,resllList);
                    int endExp=resLexerIndex-2;
                    List<String>expAnalysisList=new ArrayList<>();
                    for(int i=startExp;i<=endExp;i++) {
                        expAnalysisList.add(resLexerList.get(i));
                        System.out.println(resLexerList.get(i));
                    }
                    String resString=new String();
                    if(!global)
                    {
                        AnalysisExp tmpAnalysisExp=new AnalysisExp();
                        resString=tmpAnalysisExp.mainAnalysisExp(tmpLexer,expAnalysisList,new analysis(),resllList);

                        //进行赋值
                        IdentWord.generAssignNormal(tmpLexer,resllList,varSym,resString);
                    }
                    else{
                        AnalysisValueExp tmpAnalysisExp=new AnalysisValueExp();
                        resString=tmpAnalysisExp.mainAnalysisExp(tmpLexer,expAnalysisList,new analysis(),resllList);
                        if(tmpAnalysisExp.ifBian)
                        {
                            System.out.println("变量不可以赋值给常量");
                            System.exit(3);
                        }
                        //进行赋值
                        IdentWord.generAssignNormalGlobal(tmpLexer,resllList,varSym,resString);
                    }
                }

                else
                {
                    if(!global)
                    {
                        //generNormal中生成声明语句
                        IdentWord tmpWord=IdentWord.generIdentNormal(tmpLexer,resllList,varSym,false,
                                Parser.blockStack.get(Parser.blockStack.size()-1));
                    }
                    else
                    {
                        //generNormal中生成声明语句
                        IdentWord tmpWord=IdentWord.generIdentNormal(tmpLexer,resllList,varSym,false,
                                Parser.blockStack.get(Parser.blockStack.size()-1));
                        //进行赋值,生成赋值声明语句
                        String resString=new String();
                        resString="0";
                        IdentWord.generAssignNormalGlobal(tmpLexer,resllList,varSym,resString);
                    }

                }
            }


        //------------------------------声明结束。开始赋值------------------------------


        }
        else
            System.exit(2);

        System.out.println("varDef");
    }
    public void initValParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("in initVal");
        if(tmpSym.equals("LBrace"))
        {
            getSym(resLexerList);
            if(!tmpSym.equals("RBrace"))
            {
                initValParser(tmpLexer,resLexerList,resllList);
                while(tmpSym.equals("Comma"))
                {
                    getSym(resLexerList);
                    initValParser(tmpLexer,resLexerList,resllList);

                }
            }
            if(tmpSym.equals("RBrace"))
                getSym(resLexerList);


            else
                System.exit(2);
        }
        else
            expParser(tmpLexer,resLexerList,resllList);
        System.out.println("initVal");
        return;

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
            //分配块号，语句结束回收块号
            Parser.blockStack.add(Parser.allocaBlock++);

            while(tmpSym.equals("LPar")||tmpSym.equals("Plus")||tmpSym.equals("Minus")||tmpSym.equals("Oppose")||
                    tmpSym.equals("Return")||tmpSym.equals("Const")||tmpSym.equals("Int")||
                    tmpSym.startsWith("Ident")||tmpSym.startsWith("Number")||tmpSym.startsWith("If")||tmpSym.startsWith("LBrace")||
                    tmpSym.startsWith("While")||tmpSym.startsWith("Break")|| tmpSym.startsWith("Continue"))
                blockItemParser(tmpLexer,resLexerList,resllList);


            if(tmpSym.equals("RBrace")) {
                //回收块号
                Parser.blockStack.remove(Parser.blockStack.size()-1);

                getSym(resLexerList);
                // resllList.add("}"+"\n");
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
//        System.out.println("----------checking blockItem--------"+tmpSym);
        if(tmpSym.equals("Const")||tmpSym.equals("Int"))
            declParser(tmpLexer,resLexerList,resllList);
        else if(tmpSym.equals("LPar")||tmpSym.equals("Plus")||tmpSym.equals("Minus")||tmpSym.equals("Oppoose")||
                tmpSym.equals("Return")|| tmpSym.startsWith("Ident")||tmpSym.startsWith("Number")||
                tmpSym.startsWith("If")||tmpSym.startsWith("LBrace")||tmpSym.startsWith("While")||tmpSym.startsWith("Break")||
                tmpSym.startsWith("Continue"))
        {
            stmtParser(tmpLexer,resLexerList,resllList);

        }

        else
            System.exit(2);


        System.out.println("blockItem");

    }

    public void stmtParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){



        System.out.println("in stmt");

       if(tmpSym.equals("LBrace"))
           blockParser(tmpLexer,resLexerList,resllList);
       else if(tmpSym.equals("RBrace"))
           return;
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
           resllList.add("ret i32 "+resString+"\n");
           System.out.println("ret i32 "+resString+"\n");

           if(tmpSym.equals("Semicolon"))
               getSym(resLexerList);
           else
               System.exit(2);

       }
       else if(tmpSym.equals("If"))
       {
           getSym(resLexerList);
           if(tmpSym.equals("LPar"))
           {
               getSym(resLexerList);
               String cmp=condParser(tmpLexer,resLexerList,resllList);
               if(tmpSym.equals("RPar"))
               {
                   String jmp1=analysis.generStoreLocate();
                   String jmp2=analysis.generStoreLocate();
                   resllList.add("br i1 "+cmp+", label "+jmp1+", label "+jmp2+"\n");
                   resllList.add(jmp1.substring(1)+":\n");
                   getSym(resLexerList);

                   //分配块号，语句结束回收块号
                   Parser.blockStack.add(Parser.allocaBlock++);
                   stmtParser(tmpLexer,resLexerList,resllList);
                   Parser.blockStack.remove(Parser.blockStack.size()-1);

                   if(tmpSym.equals("Else"))
                   {
                       String jmp3=analysis.generStoreLocate();

                       if(continueTag==1&&breakTag==0)
                       {
                           continueTag=0;
                           resllList.add("br label "+whileBlockStack.peek().jmp0+"\n");
                       }
                       else if(breakTag==1&&continueTag==0)
                       {
                           breakTag=0;
                           resllList.add("br label "+whileBlockStack.peek().jmp2+"\n");
                       }
                       else
                           resllList.add("br label "+jmp3+"\n");
                       resllList.add(jmp2.substring(1)+":\n");
                       getSym(resLexerList);

                       //分配块号，语句结束回收块号
                       Parser.blockStack.add(Parser.allocaBlock++);
                       stmtParser(tmpLexer,resLexerList,resllList);
                       Parser.blockStack.remove(Parser.blockStack.size()-1);

                       if(continueTag==1&&breakTag==0)
                       {
                           continueTag=0;
                           resllList.add("br label "+whileBlockStack.peek().jmp0+"\n");
                       }
                       else if(breakTag==1&&continueTag==0)
                       {
                           breakTag=0;
                           resllList.add("br label "+whileBlockStack.peek().jmp2+"\n");
                       }
                       else
                           resllList.add("br label "+jmp3+"\n");
                       resllList.add(jmp3.substring(1)+":\n");
                   }
                   else{
                       if(continueTag==1&&breakTag==0)
                       {
                           continueTag=0;
                           resllList.add("br label "+whileBlockStack.peek().jmp0+"\n");
                       }
                       else if(breakTag==1&&continueTag==0)
                       {
                           breakTag=0;
                           resllList.add("br label "+whileBlockStack.peek().jmp2+"\n");
                       }
                       else
                        resllList.add("br label "+jmp2+"\n");
                      resllList.add(jmp2.substring(1)+":\n");
//
//                       tmpSym=resLexerList.get(resLexerIndex-2);
//                       resLexerIndex--;//回退一个
                       return;
                   }
               }
               else
                   System.exit(2);
           }
           else
               System.exit(2);
       }
       else if(tmpSym.equals("While"))
       {
           getSym(resLexerList);
           if(tmpSym.equals("LPar"))
           {
               getSym(resLexerList);

               String jmp0=analysis.generStoreLocate();
               loopJmp0=jmp0;
               resllList.add("br label "+jmp0+"\n");
               resllList.add(jmp0.substring(1)+":\n");
               String cmp=condParser(tmpLexer,resLexerList,resllList);
               if(tmpSym.equals("RPar"))
               {
                   String jmp1=analysis.generStoreLocate();
                   String jmp2=analysis.generStoreLocate();
                   loopJmp1=jmp1;
                   loopJmp2=jmp2;
                   resllList.add("br i1 "+cmp+", label "+jmp1+", label "+jmp2+"\n");
                   resllList.add(jmp1.substring(1)+":\n");
                   getSym(resLexerList);

                   //分配块号，语句结束回收块号
                   WhileBlock tmp=new WhileBlock(jmp0,jmp2);
                   whileBlockStack.add(tmp);
                   Parser.blockStack.add(Parser.allocaBlock++);
                   stmtParser(tmpLexer,resLexerList,resllList);
                   Parser.blockStack.remove(Parser.blockStack.size()-1);
                   whileBlockStack.pop();


                   //为下一个块生成块head
                   {
                       resllList.add("br label "+jmp0+"\n");
                       resllList.add(jmp2.substring(1)+":\n");
//                       tmpSym=resLexerList.get(resLexerIndex-2);
//                       resLexerIndex--;//回退一个
                       return;
                   }
               }
               else
                   System.exit(2);
           }
           else
               System.exit(2);
       }
       else if(tmpSym.equals("Break"))
       {
           getSym(resLexerList);
           if(tmpSym.equals("Semicolon"))
           {
               getSym(resLexerList);
               breakTag=1;
           }
       }
       else if(tmpSym.equals("Continue"))
       {
           getSym(resLexerList);
           if(tmpSym.equals("Semicolon"))
           {
                getSym(resLexerList);
                continueTag=1;
           }
       }


       else if(tmpSym.startsWith("Ident")&&(resLexerList.get(resLexerIndex).equals("Assign")||resLexerList.get(resLexerIndex).equals("LBracket")))
       {
           String varSym=tmpSym;
           boolean ifGroup=false;
           String ptrString=new String();
           if(resLexerList.get(resLexerIndex).equals("LBracket"))
           {
               ifGroup=true;
               //新加入的数组模块
               int groupParamStart=resLexerIndex;
               lValParser(tmpLexer,resLexerList,resllList);
               int groupParamEnd=resLexerIndex-2;
               NumGroup tmpNumGroup=new NumGroup();
               List<String>groupdef=new ArrayList<>();
               for(int i=groupParamStart;i<=groupParamEnd;i++)
                   groupdef.add(resLexerList.get(i));

               IdentWord tmp=tmpLexer.identer(varSym.substring(6,varSym.length()-1));
               tmpNumGroup=IdentWord.caclGroupElemParam(tmpLexer,resllList,groupdef,((NumGroup)tmp.wordNumVar).numDimen);


               if(tmp==null)
               {
                   System.out.println(blockStack.get(Parser.blockStack.size()-1));
                   System.out.println("变量不存在，无法进行赋值");
                   System.exit(3);
               }
               if(tmp.ifConst)
               {
                   System.out.println("const数组的变量不可二次赋值");
                   System.exit(3);
               }
               if(tmp.wordType.equals("numFunction"))
               {
                   System.out.println("该变量为函数变量，无法赋值！！！");
                   System.exit(3);//该变量声明过，报错
               }
               else if(tmp.wordType.equals("numGroup"))
               {
                   ptrString=IdentWord.groupPtrValue(tmp,resllList,tmpNumGroup.numDimen,tmpNumGroup.strRow,tmpNumGroup.strCol);
               }

           }
           else
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
               if(ifGroup)
                   IdentWord.generAssignElemNormal(tmpLexer,resllList,ptrString,resString);
               else
                   IdentWord.generAssignNormal(tmpLexer,resllList,varSym,resString);

               if(tmpSym.equals("Semicolon"))
                   getSym(resLexerList);

               else
                   System.exit(2);

           }
           else
               System.exit(2);
       }

       else if(tmpSym.equals("LPar")||tmpSym.equals("Plus")||tmpSym.equals("Minus")||tmpSym.equals("Oppose")||
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
        {
            getSym(resLexerList);
            while(tmpSym.equals("LBracket"))
            {
                getSym(resLexerList);
                expParser(tmpLexer,resLexerList,resllList);
                if(tmpSym.equals("RBracket"))
                    getSym(resLexerList);
                else
                    System.exit(2);
            }
        }

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
        while(tmpSym.equals("Plus")||tmpSym.equals("Minus")||tmpSym.equals("Oppose")){
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
                while(tmpSym.equals("LPar")||tmpSym.equals("Plus")||tmpSym.equals("Minus")||tmpSym.equals("Oppose")||
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

        else if(tmpSym.equals("Plus")||tmpSym.equals("Minus")||tmpSym.equals("Oppose"))
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
        if(tmpSym.equals("Plus")||tmpSym.equals("Minus")||tmpSym.equals("Oppose"))
            getSym(resLexerList);
        else
            System.exit(2);
        System.out.println("unaryOpParser");
    }
    //--------------------------------条件表达式相关-----------------------------------
    public String condParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("IN cond");

        int startExp=resLexerIndex-1;
        lOrExpParser(tmpLexer,resLexerList,resllList);
        int endExp=resLexerIndex-2;
        List<String>expAnalysisList=new ArrayList<>();
        for(int i=startExp;i<=endExp;i++) {
            expAnalysisList.add(resLexerList.get(i));
            System.out.println(resLexerList.get(i));
        }
        String condLocate=new AnalysisEqExp().mainAnalysisExp(tmpLexer,expAnalysisList,new analysis(),resllList);
        //顺利把表达式字符串数组送去变为ll编码了

        System.out.println("condParser");
        return condLocate;
    }
    public void lOrExpParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("IN lOr");

        lAndExpParser(tmpLexer,resLexerList,resllList);
        while(tmpSym.equals("Or"))
        {
            getSym(resLexerList);
            lAndExpParser(tmpLexer,resLexerList,resllList);
        }
        System.out.println("lOrParser");
    }
    public void lAndExpParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("IN lAnd");

        eqExpParser(tmpLexer,resLexerList,resllList);
        while(tmpSym.equals("And"))
        {
            getSym(resLexerList);
            eqExpParser(tmpLexer,resLexerList,resllList);
        }
        System.out.println("lAndParser");
    }
    public void eqExpParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("IN eq");
        relExpParser(tmpLexer,resLexerList,resllList);
        while(tmpSym.equals("Eq")||tmpSym.equals("Ne"))
        {
            getSym(resLexerList);
            relExpParser(tmpLexer,resLexerList,resllList);
        }
        System.out.println("eqParser");
    }
    public void relExpParser(Lexer tmpLexer,List<String> resLexerList,List<String> resllList){
        System.out.println("IN rel");
        addExpParser(tmpLexer,resLexerList,resllList);
        while(tmpSym.equals("Lt")||tmpSym.equals("Le")||tmpSym.equals("Gt")||tmpSym.equals("Ge"))
        {
            getSym(resLexerList);
            addExpParser(tmpLexer,resLexerList,resllList);
        }
        System.out.println("relParser");
    }

    //------------------------------------------------------------------------------
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