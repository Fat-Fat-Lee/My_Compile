package Var;

import Analysis.AnalysisExp;
import Analysis.analysis;
import Lexer.Lexer;

import java.util.ArrayList;
import java.util.List;

public class NumGroup extends NumVar{
    public String locate;
    public String loadLocate;
    public int numDimen;//数组维度 1维、2维
    public int numRow;//几行
    public String strRow;//行号
    public int numCol;//几列
    public String strCol;//列号
    public NumGroup() {
    }

    public static NumGroup identNumGroup(){
        NumGroup tmp=new NumGroup();
        tmp.locate= analysis.generStoreLocate();
        return tmp;
    }
    public static NumGroup identNumGroupGlobal(String wordName){
        NumGroup tmp=new NumGroup();
        tmp.locate= "@"+wordName;
       // tmp.numType="int";
        return tmp;
    }
    public static List<List<String>> resStringDivider(Lexer tmpLexer,List<String> resllList, boolean canBian, int numDimen, int numCol, int numRow, List<String>groupList)
    {
        List<List<String>>resStringList=new ArrayList<>();
        if(numDimen==1)
        {
            if(groupList.size()==2)
                return resStringList;

            List <Integer>divideInt=new ArrayList<>();
            divideInt.add(0);
            List<String>oneStringList=new ArrayList<>();
            for(int i=0;i<groupList.size();i++)
            {
                if(groupList.get(i).equals("Comma")||groupList.get(i).equals("LBrace"))
                {
                    divideInt.add(i);
                    int endExp=divideInt.get(divideInt.size()-1)-1;
                    int startExp=divideInt.get(divideInt.size()-2)+1;

                    List<String>expAnalysisList=new ArrayList<>();
                    //System.out.println("表达式在这里！");
                    for(int j=startExp;j<=endExp;j++)
                        expAnalysisList.add(groupList.get(j));

                    AnalysisExp tmpAnalysisExp=new AnalysisExp();
                    String resString=tmpAnalysisExp.mainAnalysisExp(tmpLexer,expAnalysisList,new analysis(),resllList);
                    oneStringList.add(resString);
                    if(!canBian&&tmpAnalysisExp.ifBian)
                    {
                        System.out.println("变量数组不可以赋值给常量数组");
                        System.exit(3);
                    }
                }

            }
            int len=oneStringList.size();
            if(len>numCol)
            {
                System.out.println("赋值超过数组长度");
                System.exit(3);
            }

        }
        else if(numDimen==2)
        {
            if(groupList.size()==2)
                return resStringList;

            List <Integer>divideInt1=new ArrayList<>();
            for(int i=0;i<groupList.size();i++)
            {
                if(groupList.get(i).equals("LBrace")&&i!=0)
                    divideInt1.add(i);
                if(groupList.get(i).equals("RBrace")&&i!=groupList.size()-1)
                    divideInt1.add(i);
            }
            List<List<String>>groupDimenList=new ArrayList<>();
            for(int i=0;i<divideInt1.size();i+=2)
            {
                List<String>tmp=new ArrayList<>();
                for(int j=divideInt1.get(i);j<=divideInt1.get(i+1);j++)
                    tmp.add(groupList.get(j));
                groupDimenList.add(tmp);
            }//获得分离的二维数组赋值表
            if(groupDimenList.size()>numRow)
            {
                System.out.println("赋值超过二维数组行数");
                System.exit(3);
            }

            //根据分离的二维数组表进行赋值操作
            for(int k=0;k<groupDimenList.size();k++)
            {
                List <Integer>divideInt=new ArrayList<>();
                divideInt.add(0);
                List<String>oneStringList=new ArrayList<>();
                List<String>tmpGroupList=groupDimenList.get(k);
                for(int i=0;i<tmpGroupList.size();i++)
                {

                    if(tmpGroupList.get(i).equals("Comma")||tmpGroupList.get(i).equals("LBrace"))
                    {
                        divideInt.add(i);
                        int endExp=divideInt.get(divideInt.size()-1);
                        int startExp=divideInt.get(divideInt.size()-2);

                        List<String>expAnalysisList=new ArrayList<>();
                        //System.out.println("表达式在这里！");
                        for(int j=startExp;j<=endExp;j++)
                            expAnalysisList.add(tmpGroupList.get(j));

                        AnalysisExp tmpAnalysisExp=new AnalysisExp();
                        String resString=tmpAnalysisExp.mainAnalysisExp(tmpLexer,expAnalysisList,new analysis(),resllList);
                        oneStringList.add(resString);
                        //赋值到一维数组中

                        if(!canBian&&tmpAnalysisExp.ifBian)
                        {
                            System.out.println("变量数组不可以赋值给常量数组");
                            System.exit(3);
                        }
                    }

                }
                int len=oneStringList.size();
                if(len>numCol)
                {
                    System.out.println("赋值超过二维数组列数长度");
                    System.exit(3);
                }
                resStringList.add(oneStringList);
            }

        }
        else{
            System.out.println("暂时不支持分解二维以上数组");
            System.exit(3);
        }
        return resStringList;
    }

}
