package Lexer;

import Analysis.AnalysisEqExp;
import Analysis.AnalysisExp;
import Analysis.AnalysisValueExp;
import Analysis.analysis;
import Parser.Parser;
import Var.NumFunction;
import Var.NumGroup;
import Var.NumNormal;
import Var.NumVar;

import java.util.ArrayList;
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

        resllList.add("store i32"+resString+","+"i32* "+((NumNormal)tmp.wordNumVar).locate+"\n");//生成赋值语句

        System.out.println("store i32"+resString+","+"i32* "+((NumNormal)tmp.wordNumVar).locate+"\n");//打印一下
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

        resllList.add("store i32"+resString+","+"i32* "+((NumNormal)tmp.wordNumVar).locate+"\n");//生成赋值语句

        System.out.println("store i32"+resString+","+"i32* "+((NumNormal)tmp.wordNumVar).locate+"\n");//打印一下
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

    //------------------------------------数组系列函数------------------------------------------------------
    //生成变量标识符,声明变量，该变量已定下来存储位置
    public static IdentWord generIdentGroup(Lexer tmpLexer, List<String> resllList,String functionSymbol, boolean ifConst,int belongBlock
            ,int numDimen,int numRow,int numCol)
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
        tmp.wordType="numGroup";
        tmp.ifConst=ifConst;
        tmp.belongBlock=belongBlock;
        if(!Parser.global)
            tmp.wordNumVar= NumGroup.identNumGroup();//指定存储位置
        else
            tmp.wordNumVar=NumGroup.identNumGroupGlobal(tmp.wordName);//指定全局变量存储位置


        tmpLexer.identWordList.add(tmp);

        if(!Parser.global)
        {
            //完成多维数组声明语句
           if(numDimen==1)
            {
                ((NumGroup)tmp.wordNumVar).numDimen=1;
                ((NumGroup)tmp.wordNumVar).numCol=numCol;
                ((NumGroup)tmp.wordNumVar).numCol=numRow;
                resllList.add(((NumGroup)tmp.wordNumVar).locate+"=alloca ["+numCol+"xi32] zeroinitializer\n");//生成声明语句
                System.out.println(((NumGroup)tmp.wordNumVar).locate+"=alloca ["+numCol+"xi32] zeroinitializer\n");//打印一下
            }
            else if(numDimen==2)
            {
                ((NumGroup)tmp.wordNumVar).numDimen=2;
                ((NumGroup)tmp.wordNumVar).numCol=numCol;
                ((NumGroup)tmp.wordNumVar).numCol=numRow;
                resllList.add(((NumGroup)tmp.wordNumVar).locate+"=alloca ["+numRow+"x["+numCol+"xi32]] zeroinitializer\n");//生成声明语句
                System.out.println(((NumGroup)tmp.wordNumVar).locate+"=alloca ["+numRow+"x["+numCol+"xi32]] zeroinitializer\n");//打印一下
            }
            else
            {
                System.out.println("暂时不支持更高维度数组");
                System.exit(3);
            }

        }

        return tmp;
    }
    //非const变量数组初始化赋值函数
    public static IdentWord generAssignGroup(Lexer tmpLexer, List<String> resllList,String functionSymbol,List<List<String>> resStringList
            ,int numDimen,int numRow,int numCol)
    {
        String tmpNormalName=functionSymbol.substring(6,functionSymbol.length()-1);
        if(tmpLexer.identer(tmpNormalName)==null) {
            System.out.println("该变量未声明过，无法赋值！！！");
            System.exit(3);//该变量声明过，报错
        }
        //该变量没有被声明过
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
        //生成指针寻找以及赋值语句
        groupPtrInit(tmp,resllList,resStringList,numDimen,numRow,numCol);

        return tmp;
    }

    //非const global变量初始化赋值函数
    public static IdentWord generAssignGroupGlobal(Lexer tmpLexer, List<String> resllList,String functionSymbol,List<List<String>> resStringList
            ,int numDimen,int numRow,int numCol)
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
        groupPtrGlobalInit(tmp,resllList,resStringList,numDimen,numRow,numCol);
        return tmp;
    }
    //const变量初始化赋值函数
    public static IdentWord generAssignConstGroup(Lexer tmpLexer, List<String> resllList,String functionSymbol,List<List<String>> resStringList
            ,int numDimen,int numRow,int numCol)
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

        //生成指针寻找以及赋值语句
        groupPtrInit(tmp,resllList,resStringList,numDimen,numRow,numCol);
        return tmp;
    }

    //const  global变量初始化赋值函数
    public static IdentWord generAssignConstGroupGlobal(Lexer tmpLexer, List<String> resllList,String functionSymbol,List<List<String>> resStringList
            ,int numDimen,int numRow,int numCol)
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
        groupPtrGlobalConstInit(tmp,resllList,resStringList,numDimen,numRow,numCol);
        return tmp;
    }

    //数组变量加载函数
    public static String generLoadGroup(Lexer tmpLexer, List<String> resllList,String functionSymbol,int numDimen,int numRow,int numCol)
    {

        String tmpNormalName=functionSymbol.substring(6,functionSymbol.length()-1);
        if(tmpLexer.identer(tmpNormalName)==null) {
            System.out.println("该变量未声明过，无法赋值！！！");
            System.exit(3);//该变量声明过，报错
        }
        //该变量没有被声明过
        IdentWord tmp=tmpLexer.identer(tmpNormalName);
        if(tmp.wordType.equals("numFunction"))
        {
            System.out.println("该变量为函数变量，无法用变量加载函数加载！！！");
            System.exit(3);//该变量声明过，报错
        }
        else
        {
            String retString=new String();
            ((NumGroup)tmp.wordNumVar).loadLocate= analysis.generStoreLocate();
            retString=((NumGroup)tmp.wordNumVar).loadLocate;
            String source=((NumGroup)tmp.wordNumVar).locate;
            resllList.add(retString+" = load i32, i32* "+source+"\n");//生成加载语句
            System.out.println(retString+" = load i32, i32* "+source+"\n");//打印一下
            return retString;
        }
        return "";
    }

//-------------------------------------------------数组指针ll代码函数-------------------------------------------
    //数组指针加载初始赋值函数
    public static void groupPtrInit(IdentWord tmp, List<String> resllList,List<List<String>> resStringList
            ,int numDimen,int numRow,int numCol)
    {
        if(numDimen==1)
        {
            List<String> resGroupString=resStringList.get(0);
            String headptr=analysis.generStoreLocate();
            resllList.add(headptr+" = getelementptr ["+numCol+" x i32], ["+numCol+" x i32]* "+((NumGroup)tmp.wordNumVar).locate+", i32 0, i32 0");
            System.out.println(headptr+" = getelementptr ["+numCol+" x i32], ["+numCol+" x i32]* "+((NumGroup)tmp.wordNumVar).locate+", i32 0, i32 0");
            for(int i=0;i<resGroupString.size();i++)
            {
                //结束初始化一维数组寻指针以及赋值
                String ptr=analysis.generStoreLocate();
                resllList.add(ptr+"=getelementptr i32,i32* "+headptr+", i32 "+i);
                System.out.println(ptr+"=getelementptr i32,i32* "+headptr+", i32 "+i);

                resllList.add("store i32 "+resGroupString.get(i)+", i32* "+ptr);
                System.out.println("store i32 "+resGroupString.get(i)+", i32* "+ptr);
            }
        }
        else if(numDimen==2)
        {
            //二维数组总头指针
            String headptr=analysis.generStoreLocate();
            resllList.add(headptr+" = getelementptr ["+numRow+" x ["+numCol+" x i32]],["+numRow+" x ["+numCol+" x i32]]* "+((NumGroup)tmp.wordNumVar).locate+", i32 0, i32 0");
            System.out.println(headptr+" = getelementptr ["+numRow+" x ["+numCol+" x i32]],["+numRow+" x ["+numCol+" x i32]]* "+((NumGroup)tmp.wordNumVar).locate+", i32 0, i32 0");

            resllList.add(headptr+" = getelementptr ["+numCol+" x i32], ["+numCol+" x i32]* "+headptr+", i32 0, i32 0");
            System.out.println(headptr+" = getelementptr ["+numCol+" x i32], ["+numCol+" x i32]* "+headptr+", i32 0, i32 0");
            int count=0;
            for(int j=0;j<resStringList.size();j++)
            {
                List<String> resGroupString=resStringList.get(j);
                for(int i=0;i<resGroupString.size();i++)
                {
                    //结束初始化一维数组寻指针以及赋值
                    String ptr=analysis.generStoreLocate();
                    resllList.add(ptr+"=getelementptr i32,i32* %4, i32 "+count);
                    System.out.println(ptr+"=getelementptr i32,i32* %4, i32 "+count);
                    count++;

                    resllList.add("store i32 "+resGroupString.get(i)+", i32* "+ptr);
                    System.out.println("store i32 "+resGroupString.get(i)+", i32* "+ptr);
                }
            }

        }
        else
        {
            System.out.println("暂时不支持更高维度数组");
            System.exit(3);
        }
    }

    //非const全局数组指针加载初始赋值函数
    public static void groupPtrGlobalInit(IdentWord tmp, List<String> resllList,List<List<String>> resStringList
            ,int numDimen,int numRow,int numCol)
    {
        if(numDimen==1)
        {
            //若为赋初值则全初始化为0
            if(resStringList.size()==0)
            {
                String resllString=((NumGroup)tmp.wordNumVar).locate+" = dso_local global["+numCol+" x i32] zeroinitializer";
                resllList.add(resllString);
                System.out.println(resllString);
                return;
            }
            List<String> resGroupString=resStringList.get(0);
            String resllString=((NumGroup)tmp.wordNumVar).locate+" = dso_local global["+numCol+" x i32][";

            for(int i=0;i<resGroupString.size();i++)
            {
                //结束初始化一维数组寻指针以及赋值
                resllString+="i32"+resGroupString.get(i)+",";
            }
            if(resllString.endsWith(","))
                resllString.substring(0,resllString.length()-1);
            resllString+="]\n";
            resllList.add(resllString);
            System.out.println(resllString);
        }
        else if(numDimen==2)
        {
            if(resStringList.size()==0)
            {
                //若为赋初值则全初始化为0
                String resllString=((NumGroup)tmp.wordNumVar).locate+" = dso_local global["+numRow+"x["+numCol+" x i32]] zeroinitializer";
                resllList.add(resllString);
                System.out.println(resllString);
                return;
            }
            String resllString=((NumGroup)tmp.wordNumVar).locate+" = dso_local global["+numRow+"x["+numCol+" x i32]][";

            for(int i=0;i<resStringList.size();i++)
            {
                List<String> resGroupString=resStringList.get(i);
                //结束初始化一维数组寻指针以及赋值
                resllString+="["+resGroupString.size()+"xi32][";
                for(int j=0;j<resGroupString.size();j++)
                {
                    resllString+="i32"+resGroupString.get(j)+",";
                }
                if(resllString.endsWith(","))
                    resllString.substring(0,resllString.length()-1);
                resllString+="],\n";
            }

            if(resllString.endsWith(","))
                resllString.substring(0,resllString.length()-1);
            resllString+="]\n";
            resllList.add(resllString);
            System.out.println(resllString);
        }
        else
        {
            System.out.println("暂时不支持更高维度数组");
            System.exit(3);
        }
    }

    //const全局数组指针加载初始赋值函数
    public static void groupPtrGlobalConstInit(IdentWord tmp, List<String> resllList,List<List<String>> resStringList
            ,int numDimen,int numRow,int numCol)
    {
        if(numDimen==1)
        {
            List<String> resGroupString=resStringList.get(0);
            String resllString=((NumGroup)tmp.wordNumVar).locate+" = dso_local constant["+numCol+" x i32][";

            for(int i=0;i<resGroupString.size();i++)
            {
                //结束初始化一维数组寻指针以及赋值
                resllString+="i32"+resGroupString.get(i)+",";
            }
            if(resllString.endsWith(","))
                resllString.substring(0,resllString.length()-1);
            resllString+="]\n";
            resllList.add(resllString);
        }
        else if(numDimen==2)
        {
            String resllString=((NumGroup)tmp.wordNumVar).locate+" = dso_local global["+numRow+"x["+numCol+" x i32]][";

            for(int i=0;i<resStringList.size();i++)
            {
                List<String> resGroupString=resStringList.get(i);
                //结束初始化一维数组寻指针以及赋值
                resllString+="["+resGroupString.size()+"xi32][";
                for(int j=0;j<resGroupString.size();j++)
                {
                    resllString+="i32"+resGroupString.get(j)+",";
                }
                if(resllString.endsWith(","))
                    resllString.substring(0,resllString.length()-1);
                resllString+="],\n";
            }

            if(resllString.endsWith(","))
                resllString.substring(0,resllString.length()-1);
            resllString+="]\n";
            resllList.add(resllString);
        }
        else
        {
            System.out.println("暂时不支持更高维度数组");
            System.exit(3);
        }
    }

    //指定数组变量加载函数
    public static String groupPtrLoad(IdentWord tmp, List<String> resllList,int numDimen,String strRow,String strCol)
    {
        if(numDimen==1)
        {
            int allCol=((NumGroup)tmp.wordNumVar).numCol;
            //获取一维数组头指针
            String headptr=analysis.generStoreLocate();
            resllList.add(headptr+" = getelementptr ["+allCol+" x i32], ["+allCol+" x i32]* "+((NumGroup)tmp.wordNumVar).locate+", i32 0, i32 0");
            System.out.println(headptr+" = getelementptr ["+allCol+" x i32], ["+allCol+" x i32]* "+((NumGroup)tmp.wordNumVar).locate+", i32 0, i32 0");

            //获取a[n]的指针
            String ptr=analysis.generStoreLocate();
            resllList.add(ptr+"=getelementptr i32,i32* "+headptr+", i32 "+strCol);
            System.out.println(ptr+"=getelementptr i32,i32* "+headptr+", i32 "+strCol);

            //加载a[n]的数值并且存储
            String ptrValue=analysis.generStoreLocate();
            resllList.add(ptrValue+"=load i32,i32* "+ptr);
            System.out.println(ptrValue+"=load i32,i32* "+ptr);

            //返回加载出的a[n]存储位置
            return ptrValue;

        }
        else if(numDimen==2)
        {
            int allCol=((NumGroup)tmp.wordNumVar).numCol;
            int allRow=((NumGroup)tmp.wordNumVar).numRow;
            //二维数组总头指针
            String headptr=analysis.generStoreLocate();
            resllList.add(headptr+" = getelementptr ["+allRow+" x ["+allCol+" x i32]],["+allRow+" x ["+allCol+" x i32]]* "+((NumGroup)tmp.wordNumVar).locate+", i32 0, i32 0");
            System.out.println(headptr+" = getelementptr ["+allRow+" x ["+allCol+" x i32]],["+allRow+" x ["+allCol+" x i32]]* "+((NumGroup)tmp.wordNumVar).locate+", i32 0, i32 0");
            //二维数组行指针
            resllList.add(headptr+" = getelementptr ["+allCol+" x i32], ["+allCol+" x i32]* "+headptr+", i32 0, i32 0");
            System.out.println(headptr+" = getelementptr ["+allCol+" x i32], ["+allCol+" x i32]* "+headptr+", i32 0, i32 0");

            //获取a[m][n]的指针
            String ptr=analysis.generStoreLocate();
            //---------计算指针部分语句--------
            String locateMul=analysis.generStoreLocate();
            resllList.add(locateMul+"=mul i32 "+strRow+","+allCol);
            String locateAdd=analysis.generStoreLocate();
            resllList.add(locateAdd+"=add i32 "+locateMul+","+strCol);
            resllList.add(ptr+"=getelementptr i32,i32* "+headptr+", i32 "+locateAdd);
            System.out.println(ptr+"=getelementptr i32,i32* "+headptr+", i32 "+locateAdd);

            //加载a[m][n]的数值并且存储
            String ptrValue=analysis.generStoreLocate();
            resllList.add(ptrValue+"=load i32,i32* "+ptr);
            System.out.println(ptrValue+"=load i32,i32* "+ptr);

            //返回加载出的a[n]存储位置
            return ptrValue;
        }
        else
        {
            System.out.println("暂时不支持更高维度数组");
            System.exit(3);
        }
        return "";
    }


    //计算数组的维度，行数，列数
    public  static  NumGroup caclGroupParam(Lexer tmpLexer,List<String> resllList,List<String>groupDef)
    {
        NumGroup tmp=new NumGroup();
        List<List<String>>dimenGroupDef=new ArrayList<>();
        List<Integer>paramList=new ArrayList<>();
        for(int i=0;i<groupDef.size();i++)
        {
            if(groupDef.get(i).equals("LBracket")||groupDef.get(i).equals("RBracket"))
                paramList.add(i);
        }
        for(int i=0;i<paramList.size();i+=2)
        {
            List<String>tmpList=new ArrayList<>();
            for(int j=paramList.get(i);j<=paramList.get(i+1);j++)
                    tmpList.add(groupDef.get(j));
            dimenGroupDef.add(tmpList);
            //获得分离的二维数组赋值表
        }
        if(dimenGroupDef.size()>2)
        {
            System.out.println("暂时不支持二维以上数组声明");
            System.exit(3);
        }
        tmp.numDimen=dimenGroupDef.size();
        Integer res0=0;
        Integer res1=0;
        for(int i=0;i<dimenGroupDef.size();i++)
        {
            List<String>oneGroupDef=dimenGroupDef.get(i);
            int endExp=oneGroupDef.size()-2;
            int startExp=1;

            List<String>expAnalysisList=new ArrayList<>();
            //System.out.println("表达式在这里！");
            for(int j=startExp;j<=endExp;j++)
            {
                expAnalysisList.add(oneGroupDef.get(j));
            }


            AnalysisValueExp tmpAnalysisExp=new AnalysisValueExp();
            String resString=tmpAnalysisExp.mainAnalysisExp(tmpLexer,expAnalysisList,new analysis(),resllList);

            if(i==0)
                res0=Integer.parseInt(resString);
            else if(i==1)
                res1=Integer.parseInt(resString);
        }
        if(dimenGroupDef.size()==1)
            tmp.numCol=res0;
        else
        {
            tmp.numRow=res0;
            tmp.numCol=res1;
        }

        return tmp;
    }

   //计算数组元素的维度。行号。列号
   public  static  NumGroup caclGroupElemParam(Lexer tmpLexer,List<String> resllList,List<String>groupDef)
   {
       NumGroup tmp=new NumGroup();
       List<List<String>>dimenGroupDef=new ArrayList<>();
       List<Integer>paramList=new ArrayList<>();
       for(int i=0;i<groupDef.size();i++)
       {
           if(groupDef.get(i).equals("LBracket")||groupDef.get(i).equals("RBracket"))
               paramList.add(i);
       }
       for(int i=0;i<paramList.size();i+=2)
       {
           List<String>tmpList=new ArrayList<>();
           for(int j=paramList.get(i);j<=paramList.get(i+1);j++)
               tmpList.add(groupDef.get(j));
           dimenGroupDef.add(tmpList);
           //获得分离的二维数组赋值表
       }
       if(dimenGroupDef.size()>2)
       {
           System.out.println("暂时不支持二维以上数组声明");
           System.exit(3);
       }
       tmp.numDimen=dimenGroupDef.size();

       String res0=new String();
       String res1=new String();
       for(int i=0;i<dimenGroupDef.size();i++)
       {
           List<String>oneGroupDef=dimenGroupDef.get(i);
           int endExp=oneGroupDef.size()-2;
           int startExp=1;

           List<String>expAnalysisList=new ArrayList<>();
           //System.out.println("表达式在这里！");
           for(int j=startExp;j<=endExp;j++)
               expAnalysisList.add(oneGroupDef.get(j));

           AnalysisExp tmpAnalysisExp=new AnalysisExp();
           String resString=tmpAnalysisExp.mainAnalysisExp(tmpLexer,expAnalysisList,new analysis(),resllList);

           if(i==0)
               res0=resString;
           else if(i==1)
               res1=resString;
       }
       if(dimenGroupDef.size()==1)
           tmp.strCol=res0;
       else
       {
           tmp.strRow=res0;
           tmp.strCol=res1;
       }
       return tmp;
   }

    //非const数组元素赋值函数
    public static void generAssignElemNormal(Lexer tmpLexer, List<String> resllList,String ptrString,String resString)
    {
        resllList.add("store i32"+resString+","+"i32* "+ptrString+"\n");//生成赋值语句
        System.out.println("store i32"+resString+","+"i32* "+ptrString+"\n");//打印一下
        return;
    }
}