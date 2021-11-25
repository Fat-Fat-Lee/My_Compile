package Analysis;

import Exp.Exp;
import Lexer.Lexer;
import Lexer.IdentWord;
import Var.NumFunction;
import Var.NumGroup;
import Var.NumNormal;
import Var.RealFunction;
import Analysis.analysis;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AnalysisEqExp {
    public Stack<String> afterStack=new Stack<>();//后缀表达式栈
    public Stack<String> symbolStack=new Stack<>();//符号栈
    public Stack<String> tmpStack=new Stack<>();//临时栈
    public Stack<String> llStack=new Stack<>();//输出ll的运算栈
    public boolean ifBian=false;

    public AnalysisEqExp() {
    }

    public void fixUnaryExp(List<String> expAnalysisList)//把形如（++++1--+2）的式子转换为正常形式
    {
        //处理连续+，连续-
        for(int i=0;i<expAnalysisList.size();i++)
        {
            String tmp0=new String();
            int numStart=i;
            int tag=0;
            int numPlus=0,numMinus=0,numOppose=0;
            tmp0=expAnalysisList.get(i);
            while(tmp0.equals("Plus")||tmp0.equals("Minus")||tmp0.equals("Oppose"))
            {
                tag++;
                if(tmp0.equals("Plus"))
                    numPlus++;
                else if(tmp0.equals("Minus"))
                    numMinus++;
                else
                    numOppose++;
                i++;
                tmp0=expAnalysisList.get(i);
            }
            if(tag>1)
            {
                if(numMinus%2==0&&numOppose%2==0)
                {
                    for(int j=numStart;j<i;j++)
                        if(expAnalysisList.get(j).equals("Minus")||expAnalysisList.get(j).equals("Oppose"))
                            expAnalysisList.set(j,"Plus");
                }
                else if(numMinus%2==1&&numOppose%2==0)
                {
                    for(int j=numStart;j<i;j++)
                        expAnalysisList.set(j,"Plus");
                    expAnalysisList.set(i-1,"Minus");
                }
                else if(numMinus%2==0&&numOppose%2==1)
                {
                    for(int j=numStart;j<i;j++)
                        expAnalysisList.set(j,"Plus");
                        expAnalysisList.set(i-1,"Oppose");
                }
                else if(numMinus%2==1&&numOppose%2==1)
                {
                    for(int j=numStart;j<i;j++)
                        expAnalysisList.set(j,"Plus");
                    expAnalysisList.set(i-2,"Minus");
                    expAnalysisList.set(i-1,"Oppose");//保证非距离数字最近
                }
                i--;
            }

        }

        //处理单个非
        for(int i=0;i<expAnalysisList.size();i++)
        {
            String tmp0=expAnalysisList.get(i);
            //System.out.println(tmp0);
            if(tmp0.equals("Oppose"))
            {
                expAnalysisList.set(i,"LPar");
                expAnalysisList.add(i+2,"Eq");
                expAnalysisList.add(i+3,"Number(0)");
                expAnalysisList.add(i+4,"RPar");
                i++;
            }
        }
        //处理单个+。-
        for(int i=0;i<expAnalysisList.size();i++)
        {
            String tmp0=expAnalysisList.get(i);
            //System.out.println(tmp0);
            if(i==0&&(tmp0.equals("Plus")||tmp0.equals("Minus")))
            {
                expAnalysisList.add(0,"Number(0)");
                i++;
                continue;
            }
            if(tmp0.equals("Plus")||tmp0.equals("Minus"))
            {
                String tmp=expAnalysisList.get(i-1);
                if(!tmp.startsWith("Number")&&!tmp.equals("RPar")&&!tmp.startsWith("Ident")
                        &&!tmp.startsWith("LBracket")&&!tmp.startsWith("RBracket"))
                {
                    //System.out.println("WHY!!!"+tmp);
                    expAnalysisList.add(i , "Number(0)");
                    i++;
                }
            }
        }
//        System.out.println("----------------表达式来啦！！---------------");
//        for(String tmp:expAnalysisList)
//            System.out.println(tmp);
        return;
    }
    public void generAfterStack(Lexer tmpLexer, List<String> expAnalysisList,List<String> resllList)
    {
        for(int i=0;i<expAnalysisList.size();i++)
        {
            String tmpi=expAnalysisList.get(i);
            IdentWord tmpIdent;
            // System.out.println("后缀栈！！"+tmpi);
            if(tmpi.startsWith("Number")||tmpi.startsWith("Ident"))
            {
                //数字和变量直接入栈，函数直接如符号栈
                if (tmpi.startsWith("Ident"))
                {
                    //找出变量
                    if(tmpLexer.identer(tmpi.substring(6,tmpi.length()-1))!=null) {
                        tmpIdent = tmpLexer.identer(tmpi.substring(6, tmpi.length() - 1));
                        if(tmpIdent.wordType.equals("numFunction")) {
                            if(((NumFunction)tmpIdent.wordNumVar).returnType.equals("void")&&i!=0)
                            {
                                System.out.println("该函数返回值为空，无法加减");
                                System.exit(3);
                            }//要是只有这个函数且它返回值为空没问题，但是返回值为空还加减就有问题
                            symbolStack.push(tmpi);
                        }
                        else {
                            if(!tmpIdent.ifConst)
                                ifBian=true;
                            if(tmpIdent.wordType.equals("numGroup"))
                            {
                                if(((NumGroup)tmpIdent.wordNumVar).numDimen==1)
                                {
                                    int j;
                                    int startIndex=i+1;
                                    int endIndex=findRBracket(startIndex,expAnalysisList);
                                    List<String>nextExpList=new ArrayList<>();
                                    //获得exp数组
                                    for( j=startIndex+1;j<endIndex;j++)
                                        nextExpList.add(expAnalysisList.get(j));

                                    AnalysisExp tmpAnalysisExp=new AnalysisExp();
                                    String resString=tmpAnalysisExp.mainAnalysisExp(tmpLexer,nextExpList,new analysis(),resllList);
                                    String ptrLocate=IdentWord.groupPtrLoad(tmpIdent,resllList,1,"1",resString);
                                    afterStack.push(ptrLocate);

                                    i=endIndex;
                                }
                                else if(((NumGroup)tmpIdent.wordNumVar).numDimen==2)
                                {
                                    //行
                                    int j;
                                    int startIndex=i+1;
                                    int endIndex=findRBracket(startIndex,expAnalysisList);
                                    List<String>nextExpList1=new ArrayList<>();
                                    //获得exp数组
                                    for( j=startIndex+1;j<endIndex;j++)
                                        nextExpList1.add(expAnalysisList.get(j));

                                    AnalysisExp tmpAnalysisExp1=new AnalysisExp();
                                    String resString1=tmpAnalysisExp1.mainAnalysisExp(tmpLexer,nextExpList1,new analysis(),resllList);
                                    i=endIndex;

                                    //列
                                    startIndex=i+1;
                                    endIndex=findRBracket(startIndex,expAnalysisList);
                                    List<String>nextExpList2=new ArrayList<>();
                                    //获得exp数组
                                    for( j=startIndex+1;j<endIndex;j++)
                                        nextExpList2.add(expAnalysisList.get(j));

                                    AnalysisExp tmpAnalysisExp2=new AnalysisExp();
                                    String resString2=tmpAnalysisExp2.mainAnalysisExp(tmpLexer,nextExpList2,new analysis(),resllList);

                                    String ptrLocate=IdentWord.groupPtrLoad(tmpIdent,resllList,2,resString1,resString2);
                                    afterStack.push(ptrLocate);

                                    i=endIndex;
                                }
                                else
                                {
                                    System.out.println("暂时不支持更高维度数组计算");
                                    System.exit(3);
                                }
                            }
                            else
                            {
                                IdentWord.generLoadNormal(tmpLexer,resllList,tmpi);
                                afterStack.push(((NumNormal) tmpIdent.wordNumVar).loadLocate);
                            }
                        }
                    }
                    else
                        System.exit(3);

                }
                else
                    afterStack.push(tmpi);
            }

            else if(tmpi.equals("Mult")||tmpi.equals("Div")||tmpi.equals("Percent"))
            {
                while(!symbolStack.empty())
                {
                    int tag=1;
                    String symbolTmp=symbolStack.pop();
                    if(symbolTmp.equals("LPar"))
                    {
                        symbolStack.push("LPar");
                        break;
                    }
                    else if(symbolTmp.equals("Mult")||symbolTmp.equals("Div")||symbolTmp.equals("Percent")||symbolTmp.startsWith("Ident"))
                        afterStack.push(symbolTmp);

                    else {
                        symbolStack.push(symbolTmp);
                        break;
                    }
                }
                symbolStack.push(tmpi);
            }
            else if(tmpi.equals("Plus")||tmpi.equals("Minus"))
            {
                while(!symbolStack.empty())
                {
                    String symbolTmp=symbolStack.pop();
                    if(symbolTmp.equals("LPar"))
                    {
                        symbolStack.push("LPar");
                        break;
                    }
                    else if(symbolTmp.equals("Mult")||symbolTmp.equals("Div")||symbolTmp.equals("Percent")
                            ||symbolTmp.equals("Plus")||symbolTmp.equals("Minus")||symbolTmp.startsWith("Ident"))
                        afterStack.push(symbolTmp);
                    else
                    {
                        symbolStack.push(symbolTmp);
                        break;
                    }
                }
                symbolStack.push(tmpi);
            }
            else if(tmpi.equals("Lt")||tmpi.equals("Gt")||tmpi.equals("Le")||tmpi.equals("Ge"))
            {
                while(!symbolStack.empty())
                {
                    String symbolTmp=symbolStack.pop();
                    if(symbolTmp.equals("LPar"))
                    {
                        symbolStack.push("LPar");
                        break;
                    }
                    else if(symbolTmp.equals("Mult")||symbolTmp.equals("Div")||symbolTmp.equals("Percent")
                            ||symbolTmp.equals("Plus")||symbolTmp.equals("Minus")||
                            symbolTmp.equals("Lt")||symbolTmp.equals("Gt")||symbolTmp.equals("Le")||symbolTmp.equals("Ge")
                            ||symbolTmp.startsWith("Ident"))
                        afterStack.push(symbolTmp);
                    else
                    {
                        symbolStack.push(symbolTmp);
                        break;
                    }
                }
                symbolStack.push(tmpi);
            }
            else if(tmpi.equals("Eq")||tmpi.equals("Ne"))
            {
                while(!symbolStack.empty())
                {
                    String symbolTmp=symbolStack.pop();
                    if(symbolTmp.equals("LPar"))
                    {
                        symbolStack.push("LPar");
                        break;
                    }
                    else if(symbolTmp.equals("Mult")||symbolTmp.equals("Div")||symbolTmp.equals("Percent")
                            ||symbolTmp.equals("Plus")||symbolTmp.equals("Minus")||
                            symbolTmp.equals("Lt")||symbolTmp.equals("Gt")||symbolTmp.equals("Le")||symbolTmp.equals("Ge")||
                            symbolTmp.equals("Eq")||symbolTmp.equals("Ne")||symbolTmp.startsWith("Ident"))
                        afterStack.push(symbolTmp);
                    else
                    {
                        symbolStack.push(symbolTmp);
                        break;
                    }
                }
                symbolStack.push(tmpi);
            }
            else if(tmpi.equals("And"))
            {
                while(!symbolStack.empty())
                {
                    String symbolTmp=symbolStack.pop();
                    if(symbolTmp.equals("LPar"))
                    {
                        symbolStack.push("LPar");
                        break;
                    }
                    else if(symbolTmp.equals("Mult")||symbolTmp.equals("Div")||symbolTmp.equals("Percent")
                            ||symbolTmp.equals("Plus")||symbolTmp.equals("Minus")||
                            symbolTmp.equals("Lt")||symbolTmp.equals("Gt")||symbolTmp.equals("Le")||symbolTmp.equals("Ge")||
                            symbolTmp.equals("Eq")||symbolTmp.equals("Ne")||
                            symbolTmp.equals("And")||symbolTmp.startsWith("Ident"))
                        afterStack.push(symbolTmp);
                    else
                    {
                        symbolStack.push(symbolTmp);
                        break;
                    }
                }
                symbolStack.push(tmpi);
            }
            else if(tmpi.equals("Or"))
            {
                while(!symbolStack.empty())
                {
                    String symbolTmp=symbolStack.pop();
                    if(symbolTmp.equals("LPar"))
                    {
                        symbolStack.push("LPar");
                        break;
                    }
                    else if(symbolTmp.equals("Mult")||symbolTmp.equals("Div")||symbolTmp.equals("Percent")
                            ||symbolTmp.equals("Plus")||symbolTmp.equals("Minus")||
                            symbolTmp.equals("Lt")||symbolTmp.equals("Gt")||symbolTmp.equals("Le")||symbolTmp.equals("Ge")||
                            symbolTmp.equals("Eq")||symbolTmp.equals("Ne")||
                            symbolTmp.equals("And")||symbolTmp.equals("Or")||symbolTmp.startsWith("Ident"))
                        afterStack.push(symbolTmp);
                    else
                    {
                        symbolStack.push(symbolTmp);
                        break;
                    }
                }
                symbolStack.push(tmpi);
            }

            else if(tmpi.equals("LPar"))
            {
                symbolStack.push("LPar");
                // System.out.println("LPar pushing!!! ");
            }
            else if(tmpi.equals("RPar"))
            {
                int tag=0;
                String symbolTmp=symbolStack.peek();
                if(symbolTmp.equals("LPar")) {
                    symbolStack.pop();
                    // System.out.println("PPPSJDAOSIJD");
                }
                while(!symbolTmp.equals("LPar"))
                {
                    tag=1;
                    symbolTmp=symbolStack.pop();
                    afterStack.push(symbolTmp);
                }
                if(tag==1)
                    afterStack.pop();
                //若是弹出括号后符号栈里面栈顶元素是函数名，就弹出到后缀栈
                if(!symbolStack.empty())
                {
                    if(symbolStack.peek().equals("Ident"))
                        afterStack.push(symbolStack.pop());

                }
            }
            else if(tmpi.equals("Comma"))
            {//遇见逗号弹出操作符，直到遇见左括号，左括号不弹出
                int tag=0;
                String symbolTmp=symbolStack.peek();
                while(!symbolTmp.equals("LPar"))
                {
                    tag=1;
                    symbolTmp=symbolStack.pop();
                    afterStack.push(symbolTmp);
                }
                if(tag==1) {
                    afterStack.pop();
                    symbolStack.push("LPar");
                }
                //若是弹出括号后符号栈里面栈顶元素是函数名，就弹出到后缀栈
                if(!symbolStack.empty())
                {
                    if(symbolStack.peek().equals("Ident"))
                        afterStack.push(symbolStack.pop());

                }
            }
            else
                System.exit(3);



        }
        while(!symbolStack.empty())
            afterStack.push(symbolStack.pop());
    }

    //生成ll代码后返回表达式生成结果存储位置,若是普通数字就直接返回数字
    public String generAnalysisExpll(Lexer tmpLexer,analysis tmpAnalysis,List<String> resllList)
    {
        //把后缀表达式栈中的内容倒过来，变成正序的后缀表达式放入临时栈中
        Exp tmpExp;
        System.out.println("后缀表达式！");
        while(!afterStack.empty())
        {
            System.out.println(afterStack.peek());
            tmpStack.push(afterStack.pop());
        }
        if(tmpStack.size()==1)
        {
            String retString=tmpStack.peek();
            // resllList.add("ret i32 "+retString.substring(7,retString.length()-1)+"\n");
            // System.out.println(resllList.get(0));
            if(retString.startsWith("%"))
                retString="i32 "+retString;
            else if(retString.startsWith("Ident"))
            {
                ArrayList<String> params=new ArrayList<>();
                RealFunction.generFunctionll(resllList,tmpLexer,retString,params);
                retString = "i32 %x"+(analysis.storeNum-1);
            }
            else if(retString.startsWith("Number"))
                retString="i32 "+retString.substring(7,retString.length()-1);

            String locate=analysis.generStoreLocate();
            resllList.add(locate+"= icmp ne "+retString+", 0\n");

            return locate;//若是只有一个数字，返回是否为零
        }

        while(!tmpStack.empty())
        {
            String tmp=tmpStack.pop();
            if(tmp.equals("Plus")||tmp.equals("Minus")||tmp.equals("Mult")||
                    tmp.equals("Div")||tmp.equals("Percent")||tmp.startsWith("Ident")||
                    tmp.equals("Lt")||tmp.equals("Le")||tmp.equals("Gt")||tmp.equals("Ge")||
                    tmp.equals("Eq")||tmp.equals("Ne")||
                    tmp.equals("And")||tmp.equals("Or"))
            {
                if(tmp.equals("Mult")||tmp.equals("Div")||tmp.equals("Percent"))
                {
                    String arg2=llStack.pop();
                    String arg1=llStack.pop();

                    tmpExp=tmpAnalysis.generStoreExp(tmp,arg1,arg2,"MultExp");
                    // System.out.println(tmpExp.arg1);
                    System.out.println(tmpExp.generExpll());
                    resllList.add(tmpExp.generExpll());
                    llStack.push(tmpExp.result);
                }
                else if(tmp.equals("Minus")||tmp.equals("Plus"))
                {
                    String arg2=llStack.pop();
                    String arg1=llStack.pop();

                    tmpExp=tmpAnalysis.generStoreExp(tmp,arg1,arg2,"AddExp");
                    System.out.println(tmpExp.generExpll());
                    resllList.add(tmpExp.generExpll());
                    llStack.push(tmpExp.result);
                }
                else if(tmp.equals("Lt")||tmp.equals("Le")||tmp.equals("Gt")||tmp.equals("Ge"))
                {
                    String arg2=llStack.pop();
                    String arg1=llStack.pop();

                    tmpExp=tmpAnalysis.generStoreExp(tmp,arg1,arg2,"RelExp");
                    System.out.println(tmpExp.generExpll());
                    resllList.add(tmpExp.generExpll());

                    //把比较大小式子结果转化为i32，加入resllList
                    String newResult=Exp.generZextll(tmpExp.result,resllList);
                    llStack.push(newResult);
                }
                else if(tmp.equals("Eq")||tmp.equals("Ne"))
                {
                    String arg2=llStack.pop();
                    String arg1=llStack.pop();

                    tmpExp=tmpAnalysis.generStoreExp(tmp,arg1,arg2,"EqExp");
                    System.out.println(tmpExp.generExpll());
                    resllList.add(tmpExp.generExpll());

                    //把比较大小式子结果转化为i32，加入resllList
                    String newResult=Exp.generZextll(tmpExp.result,resllList);
                    llStack.push(newResult);
                }
                else if(tmp.equals("And")||tmp.equals("Or"))
                {
                    String arg2=llStack.pop();
                    String arg1=llStack.pop();

                    tmpExp=tmpAnalysis.generStoreExp(tmp,arg1,arg2,"LAndExp");
                    System.out.println(tmpExp.generExpll());
                    resllList.add(tmpExp.generExpll());
                    llStack.push(tmpExp.result);
                    //把比较大小式子结果转化为i32，加入resllList
//                    String newResult=Exp.generZextll(tmpExp.result,resllList);
//                    llStack.push(newResult);
                }


                else{
                    IdentWord tmpIdent;
                    tmpIdent = tmpLexer.identer(tmp.substring(6, tmp.length() - 1));
                    ArrayList<String> params=new ArrayList<>();
                    for(int i=0;i<((NumFunction)tmpIdent.wordNumVar).length;i++)
                    {
                        if(!llStack.empty())
                            params.add(0, llStack.pop());

                        else
                        {
                            System.out.println("函数参数个数有误");
                            System.exit(3);
                        }

                    }//params形如【“Number（10）”,"%5","%2"】
                    //在这里生成函数调用ll代码
                    RealFunction.generFunctionll(resllList,tmpLexer,tmp,params);
                    if(((NumFunction)tmpIdent.wordNumVar).returnType.equals("int"))
                        llStack.push("%x"+(analysis.storeNum-1));//把函数结果放回栈中
                    else//要是函数结果为空，则看栈是否空，空则没问题
                    {
                        if(!llStack.empty()||!tmpStack.empty())
                        {
                            System.out.println("返回值为空的函数不得加减");
                            System.exit(3);
                        }
                    }
                }//已经把函数参数和函数名放入，generFunctionll会输出内容

            }
            else if(tmp.startsWith("Number")||tmp.startsWith("%"))
                llStack.push(tmp);
            else
                System.exit(3);
        }

        for(String tmp:resllList)
        {
            System.out.println(tmp);
        }
        //将结果转换为i1形式
        String arg="%x"+(analysis.storeNum-1);
        Exp.generI1ll(arg,resllList);

        return "%x"+(analysis.storeNum-1);//返回最后使用的寄存器
    }
    public String mainAnalysisExp(Lexer tmpLexer,List<String> expAnalysisList,analysis tmpAnalysis,List<String> resllList)
    {
        this.fixUnaryExp(expAnalysisList);
        this.generAfterStack(tmpLexer,expAnalysisList,resllList);
        return this.generAnalysisExpll(tmpLexer,tmpAnalysis,resllList);
    }

    public static int findRBracket(int startIndex,List<String> expAnalysisList)
    {
        Stack<String>bracketStack=new Stack<>();
        int i=0;
        for(i=startIndex;i<expAnalysisList.size();i++)
        {
            if(expAnalysisList.get(i).equals("LBracket"))
                bracketStack.push(expAnalysisList.get(i));
            if(expAnalysisList.get(i).equals("RBracket"))
            {
                if(!bracketStack.empty())
                    bracketStack.pop();
                if(bracketStack.empty())
                    break;

            }

        }
        return i;
    }
}
