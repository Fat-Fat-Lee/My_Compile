package Analysis;

import Exp.Exp;
import Lexer.Lexer;
import Lexer.IdentWord;
import Var.NumFunction;
import Var.NumNormal;
import Var.RealFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AnalysisExp {
    public Stack<String> afterStack=new Stack<>();//后缀表达式栈
    public Stack<String> symbolStack=new Stack<>();//符号栈
    public Stack<String> tmpStack=new Stack<>();//临时栈
    public Stack<String> llStack=new Stack<>();//输出ll的运算栈
    public void fixUnaryExp(List<String> expAnalysisList)//把形如（++++1--+2）的式子转换为正常形式
    {
        //处理连续+，连续-
        for(int i=0;i<expAnalysisList.size();i++)
        {
            String tmp0=new String();
            int numStart=i;
            int tag=0;
            int numPlus=0,numMinus=0;
            tmp0=expAnalysisList.get(i);
            while(tmp0.equals("Plus")||tmp0.equals("Minus"))
            {
               tag++;
                if(tmp0.equals("Plus"))
                    numPlus++;
                else
                    numMinus++;
                i++;
                tmp0=expAnalysisList.get(i);
            }
            if(tag>1)
            {
                if(numMinus%2==0)
                {
                    for(int j=numStart;j<i;j++)
                        if(expAnalysisList.get(j).equals("Minus"))
                            expAnalysisList.set(j,"Plus");
                }
                else
                {
                    for(int j=numStart;j<i;j++)
                        expAnalysisList.set(j,"Plus");
                    expAnalysisList.set(i-1,"Minus");
                }
                i--;
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
                if(!tmp.startsWith("Number")&&!tmp.equals("RPar")&&!tmp.startsWith("Ident"))
                {
                    //System.out.println("WHY!!!"+tmp);
                    expAnalysisList.add(i , "Number(0)");
                    i++;
                }
            }
        }
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
                            IdentWord.generLoadNormal(tmpLexer,resllList,tmpi);
                            afterStack.push(((NumNormal) tmpIdent.wordNumVar).loadLocate);
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
                    else if(symbolTmp.equals("Mult")||symbolTmp.equals("Div")||symbolTmp.equals("Percent"))
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
                    ||symbolTmp.equals("Plus")||symbolTmp.equals("Minus"))
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
                retString = "i32 %"+(analysis.storeNum-1);
            }
            else if(retString.startsWith("Number"))
                retString="i32 "+retString.substring(7,retString.length()-1);

            return retString;//若是只有一个数字，直接返回，若是变量已经转化为寄存器形式
        }

        while(!tmpStack.empty())
        {
            String tmp=tmpStack.pop();
            if(tmp.equals("Plus")||tmp.equals("Minus")||tmp.equals("Mult")||
                    tmp.equals("Div")||tmp.equals("Percent")||tmp.startsWith("Ident"))
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
                        llStack.push("%"+(analysis.storeNum-1));//把函数结果放回栈中
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
        return "i32 %"+(analysis.storeNum-1);//返回最后使用的寄存器
    }
    public String mainAnalysisExp(Lexer tmpLexer,List<String> expAnalysisList,analysis tmpAnalysis,List<String> resllList)
    {
        fixUnaryExp(expAnalysisList);
        generAfterStack(tmpLexer,expAnalysisList,resllList);
        return generAnalysisExpll(tmpLexer,tmpAnalysis,resllList);
    }

}
