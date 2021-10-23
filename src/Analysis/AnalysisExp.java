package Analysis;

import Exp.Exp;

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
                if(!tmp.startsWith("Number")&&!tmp.equals("RPar"))
                {
                    expAnalysisList.add(i , "Number(0)");
                    i++;
                }
            }
        }
//        for(String tmp:expAnalysisList)
//            System.out.println(tmp);
        return;
    }
    public void generAfterStack(List<String> expAnalysisList)
    {
        for(int i=0;i<expAnalysisList.size();i++)
        {
            String tmpi=expAnalysisList.get(i);
            if(afterStack.empty())
            {
                afterStack.push(tmpi);
                continue;
            }
            if(tmpi.startsWith("Number"))
                afterStack.push(tmpi);
            else if(tmpi.equals("Mult")||tmpi.equals("Div")||tmpi.equals("Percent"))
            {
                while(!symbolStack.empty())
                {
                    String symbolTmp=symbolStack.pop();
                    if(symbolTmp.equals("LPar"))
                    {
                        symbolStack.push("LPar");
                        break;
                    }
                    else if(symbolTmp.equals("Mult")||symbolTmp.equals("Div")||symbolTmp.equals("Percent"))
                       afterStack.push(symbolTmp);
                    else
                        tmpStack.push(symbolTmp);
                }
                while(!tmpStack.empty())
                    symbolStack.push(tmpStack.pop());
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
                        tmpStack.push(symbolTmp);
                }
                while(!tmpStack.empty())
                    symbolStack.push(tmpStack.pop());
               symbolStack.push(tmpi);
            }
            else if(tmpi.equals("LPar"))
                symbolStack.push("LPar");
            else if(tmpi.equals("RPar"))
            {
                String symbolTmp=symbolStack.peek();
                while(!symbolTmp.equals("LPar"))
                {
                    symbolTmp=symbolStack.pop();
                    afterStack.push(symbolTmp);
                }
                afterStack.pop();
            }
            else
            {
                //System.out.println(tmpi);
                System.exit(3);
            }


        }
        while(!symbolStack.empty())
            afterStack.push(symbolStack.pop());
    }
    public void generAnalysisExpll(analysis tmpAnalysis,List<String> resllList)
    {
        //把后缀表达式栈中的内容倒过来，变成正序的后缀表达式放入临时栈中
        Exp tmpExp;
        while(!afterStack.empty())
        {
           // System.out.println(afterStack.size());
            tmpStack.push(afterStack.pop());
        }
        if(tmpStack.size()==1)
        {
            String retString=tmpStack.peek();
            resllList.add("ret i32 "+retString.substring(7,retString.length()-1)+"\n");
            System.out.println(resllList.get(0));
            return;
        }

        while(!tmpStack.empty())
        {
            String tmp=tmpStack.pop();
            if(tmp.equals("Plus")||tmp.equals("Minus")||tmp.equals("Mult")||
                    tmp.equals("Div")||tmp.equals("Percent"))
            {
                String arg2=llStack.pop();
                String arg1=llStack.pop();
                if(tmp.equals("Mult")||tmp.equals("Div")||tmp.equals("Percent"))
                {
                    tmpExp=tmpAnalysis.generStoreExp(tmp,arg1,arg2,"MultExp");
                   // System.out.println(tmpExp.arg1);
                    System.out.println(tmpExp.generExpll());
                   resllList.add(tmpExp.generExpll());
                }
                else
                {
                    tmpExp=tmpAnalysis.generStoreExp(tmp,arg1,arg2,"AddExp");
                    System.out.println(tmpExp.generExpll());
                   resllList.add(tmpExp.generExpll());
                }
                llStack.push(tmpExp.result);
            }
            else if(tmp.startsWith("Number"))
                llStack.push(tmp);
            else
                System.exit(3);
        }
        resllList.add("ret i32 "+"%x"+(tmpAnalysis.storeNum-1)+"\n");

        for(String tmp:resllList)
        {
            System.out.println(tmp);
        }
    }
    public void mainAnalysisExp(List<String> expAnalysisList,analysis tmpAnalysis,List<String> resllList)
    {
        fixUnaryExp(expAnalysisList);
        generAfterStack(expAnalysisList);
        generAnalysisExpll(tmpAnalysis,resllList);
    }

}
