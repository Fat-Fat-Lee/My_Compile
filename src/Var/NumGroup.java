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
//    public int numRow;//几行
//    public String strRow;//行号
//    public int numCol;//几列
//    public String strCol;//列号
    public List<String>strIndex=new ArrayList<>();//元素行、列
    public List<Integer>intAllIndex=new ArrayList<>();//总的行、lie
    public NumGroup() {
    }

    public static NumGroup identNumGroup(){
        NumGroup tmp=new NumGroup();
        tmp.locate= analysis.generStoreLocate();
        return tmp;
    }

    public static NumGroup identNumParamGroup(String pLocate){
        NumGroup tmp=new NumGroup();
        tmp.locate=pLocate;
        return tmp;
    }

    public static NumGroup identNumGroupGlobal(String wordName){
        NumGroup tmp=new NumGroup();
        tmp.locate= "@"+wordName;
       // tmp.numType="int";
        return tmp;
    }
    public static List<String> resStringDivider(Lexer tmpLexer,List<String> resllList, boolean canBian, List<String>groupList)
    {
        List<String>resStringList=new ArrayList<>();
        List<String> groupList_=new ArrayList();

        if(groupList.size()==2)
            return resStringList;

        for(int i=0;i<groupList.size();i++)
        {
            if(groupList.get(i).equals("Comma")||groupList.get(i).equals("RBrace")||groupList.get(i).equals("LBrace"))
            {
                groupList_.add(" ");
            }
            else
                groupList_.add(groupList.get(i));
        }

        List<List<String>>tmpList=new ArrayList<>();
        for(int i=0;i<groupList_.size();i++)
        {
            List<String>tmp=new ArrayList<>();
            int j;
            for(j=i;j<groupList_.size()&&!groupList_.get(j).equals(" ");j++)
            {
                tmp.add(groupList_.get(j));
            }
            if(j!=i)
            {
                i=j--;
                tmpList.add(tmp);
            }

        }
        for(int i=0;i<tmpList.size();i++)
        {
            AnalysisExp tmpAnalysisExp=new AnalysisExp();
            String resString=tmpAnalysisExp.mainAnalysisExp(tmpLexer,tmpList.get(i),new analysis(),resllList);
            if(!canBian&&tmpAnalysisExp.ifBian)
            {
                System.out.println("变量数组不可以赋值给常量数组");
                System.exit(3);
            }
            resStringList.add(resString);
        }


        return resStringList;
    }

}
