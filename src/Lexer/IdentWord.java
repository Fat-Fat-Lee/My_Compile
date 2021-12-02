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
import java.util.Stack;

public class IdentWord {
    public String wordName;
    public String wordSymbol;
     public int wordValue;
    public String wordType;//numNormal是数字，numGroup是数组，numFunction是函数
    public boolean ifConst=false;//是否为固定标识符，只能赋值一次
    public NumVar wordNumVar;//根据类型进行标识符存储
    public boolean ifParam=false;
    public int belongBlock;

    //生成函数标识符,声明函数
    public static IdentWord generIdentFunction(Lexer tmpLexer,List<String> resllList,String functionSymbol,NumFunction tmpNumFunction)
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
        tmp.wordNumVar=tmpNumFunction;
        tmp.belongBlock=0;
        tmpLexer.identWordList.add(tmp);

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

        resllList.add("store i32 "+resString+","+"i32* "+((NumNormal)tmp.wordNumVar).locate+"\n");//生成赋值语句

        System.out.println("store i32 "+resString+","+"i32* "+((NumNormal)tmp.wordNumVar).locate+"\n");//打印一下
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
        tmp.wordValue=Integer.parseInt(resString);
        resllList.add("store i32 "+resString+","+"i32* "+((NumNormal)tmp.wordNumVar).locate+"\n");//生成赋值语句

        System.out.println("store i32 "+resString+","+"i32* "+((NumNormal)tmp.wordNumVar).locate+"\n");//打印一下
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
            ,int numDimen,List<Integer>intAllIndex)
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

                ((NumGroup)tmp.wordNumVar).numDimen=numDimen;
                ((NumGroup)tmp.wordNumVar).intAllIndex=intAllIndex;
                int numAll=mulAllIndex(intAllIndex);
                resllList.add(((NumGroup)tmp.wordNumVar).locate+"=alloca ["+numAll+" x i32]\n");//生成声明语句
                System.out.println(((NumGroup)tmp.wordNumVar).locate+"=alloca ["+numAll+" x i32]\n");//打印一下
                generMemsetGroup(tmpLexer, resllList,tmp, ifConst,belongBlock,numDimen);


        }
        else
        {
            //完成多维数组声明语句
            ((NumGroup)tmp.wordNumVar).numDimen=numDimen;
            ((NumGroup)tmp.wordNumVar).intAllIndex=intAllIndex;

        }

        return tmp;
    }

    //生成变量标识符,声明变量，该变量已定下来存储位置
    public static IdentWord generIdentGlobalGroup(Lexer tmpLexer, List<String> resllList,String functionSymbol, boolean ifConst,int belongBlock
            ,int numDimen,List<Integer>intAllIndex)
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
            ((NumGroup)tmp.wordNumVar).numDimen=numDimen;
            ((NumGroup)tmp.wordNumVar).intAllIndex=intAllIndex;
            int numAll=mulAllIndex(intAllIndex);
            resllList.add(((NumGroup)tmp.wordNumVar).locate+"=alloca ["+numAll+" x i32]\n");//生成声明语句
            System.out.println(((NumGroup)tmp.wordNumVar).locate+"=alloca ["+numAll+" x i32]\n");//打印一下
            generMemsetGroup(tmpLexer, resllList,tmp, ifConst,belongBlock,numDimen);


        }
        else
        {
            //完成多维数组声明语句
            ((NumGroup)tmp.wordNumVar).numDimen=numDimen;
            ((NumGroup)tmp.wordNumVar).intAllIndex=intAllIndex;

        }

        return tmp;
    }



    //非const变量数组初始化赋值函数
    public static IdentWord generAssignGroup(Lexer tmpLexer, List<String> resllList,String functionSymbol,List<String> resStringList
            ,int numDimen,List<Integer>intAllIndex)
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
        groupPtrInit(tmp,resllList,resStringList,numDimen,intAllIndex);

        return tmp;
    }
    //非const global变量初始化赋值函数
    public static IdentWord generZeroGroupGlobal(Lexer tmpLexer, List<String> resllList,String functionSymbol
            ,int numDimen,List<Integer>intAllIndex)
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
        groupPtrGlobalZeroInit(tmp,resllList,numDimen,intAllIndex);
        return tmp;
    }

    //非const global变量初始化赋值函数
    public static IdentWord generAssignGroupGlobal(Lexer tmpLexer, List<String> resllList,String functionSymbol,List<String> resStringList
            ,int numDimen,List<Integer>intAllIndex)
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
        groupPtrGlobalInit(tmp,resllList,resStringList,numDimen,intAllIndex);
        return tmp;
    }
    //const变量初始化赋值函数
    public static IdentWord generAssignConstGroup(Lexer tmpLexer, List<String> resllList,String functionSymbol,List<String> resStringList
            ,int numDimen,List<Integer>intAllIndex)
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
        groupPtrInit(tmp,resllList,resStringList,numDimen,intAllIndex);
        return tmp;
    }

    //const  global变量初始化赋值函数
    public static IdentWord generAssignConstGroupGlobal(Lexer tmpLexer, List<String> resllList,String functionSymbol,List<String> resStringList
            ,int numDimen,List<Integer>intAllIndex)
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
        groupPtrGlobalConstInit(tmp,resllList,resStringList,numDimen,intAllIndex);
        return tmp;
    }

    //数组变量加载函数
    public static String generLoadGroup(Lexer tmpLexer, List<String> resllList,String functionSymbol,int numDimen,List<Integer>intAllIndex)
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
    public static void groupPtrInit(IdentWord tmp, List<String> resllList,List<String> resStringList
            ,int numDimen,List<Integer>intAllIndex)
    {

            List<String> resGroupString=resStringList;
            String headptr=analysis.generStoreLocate();
            int numAll=mulAllIndex(intAllIndex);
            resllList.add(headptr+" = getelementptr ["+numAll+" x i32], ["+numAll+" x i32]* "+((NumGroup)tmp.wordNumVar).locate+", i32 0, i32 0\n");
            System.out.println(headptr+" = getelementptr ["+numAll+" x i32], ["+numAll+" x i32]* "+((NumGroup)tmp.wordNumVar).locate+", i32 0, i32 0\n");
            for(int i=0;i<resGroupString.size();i++)
            {
                //结束初始化一维数组寻指针以及赋值
                String ptr=analysis.generStoreLocate();
                resllList.add(ptr+"=getelementptr i32,i32* "+headptr+", i32 "+i+"\n");
                System.out.println(ptr+"=getelementptr i32,i32* "+headptr+", i32 "+i+"\n");

                resllList.add("store i32 "+resGroupString.get(i)+", i32* "+ptr+"\n");
                System.out.println("store i32 "+resGroupString.get(i)+", i32* "+ptr+"\n");
            }


    }

    //非const全局数组指针加载初始赋值函数
    public static void groupPtrGlobalInit(IdentWord tmp, List<String> resllList,List<String> resStringList
            ,int numDimen,List<Integer>intAllIndex)
    {

            List<String> resGroupString=resStringList;
            int numAll=mulAllIndex(intAllIndex);
            String resllString=((NumGroup)tmp.wordNumVar).locate+" = dso_local global ["+numAll+" x i32][";
            int i;
            //结束初始化一维数组寻指针以及赋值
            for(i=0;i<resGroupString.size();i++)
                resllString+="i32 "+resGroupString.get(i)+",";

            //结束初始化一维数组寻指针以及赋值
            for(i=i;i<numAll;i++)
                resllString+="i32 0,";

            if(resllString.endsWith(","))
            {
                resllString=resllString.substring(0,resllString.length()-1);
            }

            resllString+="]\n";
            resllList.add(resllString);
            System.out.println(resllString);

    }

    //非const全局数组全赋值为零函数
    public static void groupPtrGlobalZeroInit(IdentWord tmp, List<String> resllList
            ,int numDimen,List<Integer>intAllIndex)
    {
        int numAll=mulAllIndex(intAllIndex);

        String resllString=((NumGroup)tmp.wordNumVar).locate+" = dso_local global ["+numAll+" x i32]zeroinitializer\n";
        int i;
        resllList.add(resllString);
        System.out.println(resllString);

    }

    //const全局数组指针加载初始赋值函数
    public static void groupPtrGlobalConstInit(IdentWord tmp, List<String> resllList,List<String> resStringList
            ,int numDimen,List<Integer>intAllIndex)
    {

        int numAll=mulAllIndex(intAllIndex);
        List<String> resGroupString=resStringList;
        String resllString=((NumGroup)tmp.wordNumVar).locate+" = dso_local constant ["+numAll+" x i32][";
        int i;
            //结束初始化一维数组寻指针以及赋值
        for(i=0;i<resGroupString.size();i++)
            resllString+="i32 "+resGroupString.get(i)+",";

            //结束初始化一维数组寻指针以及赋值
        for(i=i;i<numAll;i++)
            resllString+="i32 0,";

        if(resllString.endsWith(","))
        {
            resllString=resllString.substring(0,resllString.length()-1);
        }

        resllString+="]\n";
        resllList.add(resllString);
        System.out.println(resllString);

    }

    //计算在多维数组元素a[2][3][4]在a[4][4][4]中的一维位置
    public static String findOneLocate(List<String> resllList,List<String>strIndex,List<Integer>intAllIndex)
    {
        List<Integer>mulElem=new ArrayList<>();
        for(int i=0;i<intAllIndex.size();i++)
        {
            int tmp=1;
            for(int j=i+1;j<intAllIndex.size();j++)
            {
                tmp*= intAllIndex.get(j);
            }
            mulElem.add(tmp);
        }
        List<String>mulPtrList=new ArrayList<>();
        for(int i=0;i<strIndex.size();i++)
        {
            String mulptr=analysis.generStoreLocate();
            resllList.add(mulptr+" = mul i32 "+strIndex.get(i)+", "+mulElem.get(i)+"\n");
            mulPtrList.add(mulptr);
        }

        String addElem="0";
        String addptr=new String();
        for(int i=0;i<mulPtrList.size();i++)
        {
            addptr=analysis.generStoreLocate();
            resllList.add(addptr+" = add i32 "+mulPtrList.get(i)+", "+addElem+"\n");
            addElem=addptr;
        }
        return addptr;
    }


    //指定数组变量加载函数，式子右侧用
    public static String groupPtrLoad(IdentWord tmp, List<String> resllList,int numDimen,List<String>strIndex)
    {
        if(tmp.ifParam)
        {

            //  int allCol=((NumGroup)tmp.wordNumVar).numCol;
            //获取一维数组头指针
            String headptr=analysis.generStoreLocate();
            resllList.add(headptr+" = load i32* , i32* * "+((NumGroup)tmp.wordNumVar).locate+"\n");
            System.out.println(headptr+" = load i32* , i32* * "+((NumGroup)tmp.wordNumVar).locate+"\n");

            //获取a[n]的指针
            String ptr=analysis.generStoreLocate();
            String strCol=findOneLocate(resllList,strIndex,((NumGroup)tmp.wordNumVar).intAllIndex);
            resllList.add(ptr+" = getelementptr i32, i32* "+headptr+", i32 "+strCol+"\n");
            System.out.println(ptr+" = getelementptr i32, i32* "+headptr+", i32 "+strCol+"\n");

            //加载a[n]的数值并且存储
            String ptrValue=analysis.generStoreLocate();
            resllList.add(ptrValue+"=load i32,i32* "+ptr+"\n");
            System.out.println(ptrValue+"=load i32,i32* "+ptr+"\n");

            //返回加载出的a[n]存储位置
            return ptrValue;


        }
        else
        {

            int numAll=mulAllIndex(((NumGroup)tmp.wordNumVar).intAllIndex);
            //获取一维数组头指针
            String headptr=analysis.generStoreLocate();
            resllList.add(headptr+" = getelementptr ["+numAll+" x i32], ["+numAll+" x i32]* "+((NumGroup)tmp.wordNumVar).locate+", i32 0, i32 0"+"\n");
            System.out.println(headptr+" = getelementptr ["+numAll+" x i32], ["+numAll+" x i32]* "+((NumGroup)tmp.wordNumVar).locate+", i32 0, i32 0"+"\n");

            //获取a[n]的指针
            String ptr=analysis.generStoreLocate();
            String strCol=findOneLocate(resllList,strIndex,((NumGroup)tmp.wordNumVar).intAllIndex);
            resllList.add(ptr+"=getelementptr i32,i32* "+headptr+", i32 "+strCol+"\n");
            System.out.println(ptr+"=getelementptr i32,i32* "+headptr+", i32 "+strCol+"\n");

            //加载a[n]的数值并且存储
            String ptrValue=analysis.generStoreLocate();
            resllList.add(ptrValue+"=load i32,i32* "+ptr+"\n");
            System.out.println(ptrValue+"=load i32,i32* "+ptr+"\n");

            //返回加载出的a[n]存储位置
            return ptrValue;

        }


    }

    //加载二维数组头，二维数组行指针；加载一维数组头指针；式子右侧用。loadType:0是头；1是行
    public static String groupPtrHeadLoad(IdentWord tmp, List<String> resllList,int numDimen,List<String>strIndex,int loadType)
    {
        if(tmp.ifParam)
        {
            if(loadType==0)
            {
                //int allCol=((NumGroup)tmp.wordNumVar).numCol;
                //获取一维数组头指针
                String headptr=analysis.generStoreLocate();
                resllList.add(headptr+" = load i32* , i32* * "+((NumGroup)tmp.wordNumVar).locate+"\n");
                System.out.println(headptr+" = load i32* , i32* * "+((NumGroup)tmp.wordNumVar).locate+"\n");

                //返回加载出的a[n]存储位置
                return headptr;

            }
            else
            {
                //  int allCol=((NumGroup)tmp.wordNumVar).numCol;
                //获取一维数组头指针
                String headptr=analysis.generStoreLocate();
                resllList.add(headptr+" = load i32* , i32* * "+((NumGroup)tmp.wordNumVar).locate+"\n");
                System.out.println(headptr+" = load i32* , i32* * "+((NumGroup)tmp.wordNumVar).locate+"\n");

                //获取a[n]的指针
                String ptr=analysis.generStoreLocate();
                String strCol=findOneLocate(resllList,strIndex,((NumGroup)tmp.wordNumVar).intAllIndex);
                resllList.add(ptr+" = getelementptr i32, i32* "+headptr+", i32 "+strCol+"\n");
                System.out.println(ptr+" = getelementptr i32, i32* "+headptr+", i32 "+strCol+"\n");

                //加载a[n]的数值并且存储
                String ptrValue=analysis.generStoreLocate();
                resllList.add(ptrValue+"=load i32,i32* "+ptr+"\n");
                System.out.println(ptrValue+"=load i32,i32* "+ptr+"\n");

                //返回加载出的a[n]存储位置
                return ptrValue;
            }

        }
        else
        {
            if(loadType==0)
            {
                int numAll=mulAllIndex(((NumGroup)tmp.wordNumVar).intAllIndex);
                //获取一维数组头指针
                String headptr=analysis.generStoreLocate();
                resllList.add(headptr+" = getelementptr ["+numAll+" x i32], ["+numAll+" x i32]* "+((NumGroup)tmp.wordNumVar).locate+", i32 0, i32 0"+"\n");
                System.out.println(headptr+" = getelementptr ["+numAll+" x i32], ["+numAll+" x i32]* "+((NumGroup)tmp.wordNumVar).locate+", i32 0, i32 0"+"\n");

                return headptr;

            }
            else
            {
                int numAll=mulAllIndex(((NumGroup)tmp.wordNumVar).intAllIndex);
//                String headptr=analysis.generStoreLocate();
//                resllList.add(headptr+" = getelementptr ["+numAll+" x i32], ["+numAll+" x i32]* "+((NumGroup)tmp.wordNumVar).locate+", i32 0, i32 0"+"\n");
//                System.out.println(headptr+" = getelementptr ["+numAll+" x i32], ["+numAll+" x i32]* "+((NumGroup)tmp.wordNumVar).locate+", i32 0, i32 0"+"\n");

                String rowptr_=analysis.generStoreLocate();
                String rowAll=findOneLocate(resllList,strIndex,((NumGroup)tmp.wordNumVar).intAllIndex);
//                int rowAll=1;
//                for(int i=strIndex.size();i<((NumGroup)tmp.wordNumVar).intAllIndex.size();i++)
//                {
//                    rowAll*=((NumGroup)tmp.wordNumVar).intAllIndex.get(i);
//                }



                resllList.add(rowptr_+" = getelementptr ["+numAll+" x i32], ["+numAll+" x i32]* "+((NumGroup)tmp.wordNumVar).locate+", i32 0, i32 "+rowAll+"\n");
                System.out.println(rowptr_+" = getelementptr ["+numAll+" x i32], ["+numAll+" x i32]* "+((NumGroup)tmp.wordNumVar).locate+", i32 0, i32 "+rowAll+"\n");

                //返回加载出的a[n]存储位置
                return rowptr_;
            }

        }

    }



    //指定数组变量值加载函数,式子左侧用
    public static String groupPtrValue(IdentWord tmp, List<String> resllList,int numDimen,List<String>strIndex)
    {
        if(tmp.ifParam)
        {

            //int allCol=((NumGroup)tmp.wordNumVar).numCol;
            //获取一维数组头指针
            String headptr=analysis.generStoreLocate();
            resllList.add(headptr+" = load i32* , i32* * "+((NumGroup)tmp.wordNumVar).locate+"\n");
            System.out.println(headptr+" = load i32* , i32* * "+((NumGroup)tmp.wordNumVar).locate+"\n");

            //获取a[n]的指针
            String strCol=findOneLocate(resllList,strIndex,((NumGroup)tmp.wordNumVar).intAllIndex);
            String ptr=analysis.generStoreLocate();
            resllList.add(ptr+" = getelementptr i32, i32* "+headptr+", i32 "+strCol+"\n");
            System.out.println(ptr+" = getelementptr i32, i32* "+headptr+", i32 "+strCol+"\n");

            //加载a[n]的数值并且存储
//                String ptrValue=analysis.generStoreLocate();
//                resllList.add(ptrValue+"=load i32,i32* "+ptr+"\n");
//                System.out.println(ptrValue+"=load i32,i32* "+ptr+"\n");

                //返回加载出的a[n]存储位置
            return ptr;

        }
        else
        {
            int numAll=mulAllIndex(((NumGroup)tmp.wordNumVar).intAllIndex);
            //获取一维数组头指针
            String headptr=analysis.generStoreLocate();
            resllList.add(headptr+" = getelementptr ["+numAll+" x i32], ["+numAll+" x i32]* "+((NumGroup)tmp.wordNumVar).locate+", i32 0, i32 0"+"\n");
            System.out.println(headptr+" = getelementptr ["+numAll+" x i32], ["+numAll+" x i32]* "+((NumGroup)tmp.wordNumVar).locate+", i32 0, i32 0"+"\n");

                //获取a[n]的指针
            String ptr=analysis.generStoreLocate();
            String strCol=findOneLocate(resllList,strIndex,((NumGroup)tmp.wordNumVar).intAllIndex);
            resllList.add(ptr+"=getelementptr i32,i32* "+headptr+", i32 "+strCol+"\n");
            System.out.println(ptr+"=getelementptr i32,i32* "+headptr+", i32 "+strCol+"\n");

//            //加载a[n]的数值并且存储
//            String ptrValue=analysis.generStoreLocate();
//            resllList.add(ptrValue+"=load i32,i32* "+ptr+"\n");
//            System.out.println(ptrValue+"=load i32,i32* "+ptr+"\n");

                //返回加载出的a[n]存储位置
            return ptr;


        }

    }


    //计算数组的维度，行数，列数
    public  static  NumGroup caclGroupParam(Lexer tmpLexer,List<String> resllList,List<String>groupDef)
    {
        NumGroup tmp=new NumGroup();
        List<List<String>>dimenGroupDef=new ArrayList<>();
        List<Integer>paramList=new ArrayList<>();

        int startIndex=0;
        int endIndex=0;
        paramList.add(0);
        for(int i=0;i<groupDef.size();)
        {
            endIndex=findRBracket(startIndex,groupDef);
            paramList.add(endIndex);
           // System.out.print(endIndex+" ");
            startIndex=endIndex+1;
            if(startIndex>=groupDef.size()||!groupDef.get(startIndex).equals("LBracket"))
                break;
            paramList.add(startIndex);
           // System.out.print(startIndex+" ");
            i=startIndex;
        }
        for(int i=0;i<paramList.size();i+=2)
        {
            List<String>tmpList=new ArrayList<>();
            for(int j=paramList.get(i)+1;j<paramList.get(i+1);j++)
                tmpList.add(groupDef.get(j));
            dimenGroupDef.add(tmpList);
            //获得分离的二维数组赋值表
        }

//        if(dimenGroupDef.size()>2)
//        {
//            System.out.println("暂时不支持二维以上数组声明");
//            System.exit(3);
//        }
        tmp.numDimen=dimenGroupDef.size();

//        System.out.println("???????????????");
//        for(int i=0;i<dimenGroupDef.size();i++)
//        {
//            for(int j=0;j<dimenGroupDef.get(i).size();j++)
//                System.out.print(dimenGroupDef.get(i).get(j));
//            System.out.println("");
//        }
//        System.out.println("???????????????");

        for(int i=0;i<dimenGroupDef.size();i++)
        {
            AnalysisValueExp tmpAnalysisExp=new AnalysisValueExp();
            String resString=tmpAnalysisExp.mainAnalysisExp(tmpLexer,dimenGroupDef.get(i),new analysis(),resllList);
            if(tmpAnalysisExp.ifBian)
            {
                System.out.println("变量不可以赋值给常量");
                System.exit(3);
            }
            tmp.intAllIndex.add(Integer.parseInt(resString));

        }


        return tmp;
    }

   //计算数组元素的维度。行号。列号
   public  static  NumGroup caclGroupElemParam(Lexer tmpLexer,List<String> resllList,List<String>groupDef,int numDimen)
   {
       NumGroup tmp=new NumGroup();
       List<List<String>>dimenGroupDef=new ArrayList<>();
       List<Integer>paramList=new ArrayList<>();

       int startIndex=0;
       int endIndex=0;
       paramList.add(0);

       for(int i=0;i<numDimen;i++)
       {
           endIndex=findRBracket(startIndex,groupDef);
           paramList.add(endIndex);
            System.out.print(endIndex+" ");
           startIndex=endIndex+1;
           if(startIndex>=groupDef.size()||!groupDef.get(startIndex).equals("LBracket"))
               break;
           paramList.add(startIndex);
            System.out.print(startIndex+" ");

       }

       for(int i=0;i<paramList.size();i+=2)
       {
           List<String>tmpList=new ArrayList<>();
           for(int j=paramList.get(i)+1;j<paramList.get(i+1);j++)
               tmpList.add(groupDef.get(j));
           dimenGroupDef.add(tmpList);
               //获得分离的二维数组赋值表
       }


       tmp.numDimen=dimenGroupDef.size();

       for(int i=0;i<dimenGroupDef.size();i++)
       {

           AnalysisExp tmpAnalysisExp=new AnalysisExp();
           String resString=tmpAnalysisExp.mainAnalysisExp(tmpLexer,dimenGroupDef.get(i),new analysis(),resllList);
           tmp.strIndex.add(resString);

       }

       return tmp;
   }

    //非const数组元素赋值函数
    public static void generAssignElemNormal(Lexer tmpLexer, List<String> resllList,String ptrString,String resString)
    {
        resllList.add("store i32 "+resString+","+"i32* "+ptrString+"\n");//生成赋值语句
        System.out.println("store i32 "+resString+","+"i32* "+ptrString+"\n");//打印一下
        return;
    }

    //memset赋初值
    public static void generMemsetGroup(Lexer tmpLexer, List<String> resllList,IdentWord tmp, boolean ifConst,int belongBlock
            ,int numDimen)
    {
         String headptr=analysis.generStoreLocate();
            int numAll=1;
            for(int i=0;i<((NumGroup)(tmp.wordNumVar)).intAllIndex.size();i++)
            {
                numAll*=((NumGroup)(tmp.wordNumVar)).intAllIndex.get(i);
            }
            //二维数组总头指针

            resllList.add(headptr+" = getelementptr ["+numAll+" x i32],["+numAll+" x i32]* "+((NumGroup)tmp.wordNumVar).locate+", i32 0, i32 0"+"\n");
            System.out.println(headptr+" = getelementptr ["+numAll+" x i32],["+numAll+" x i32]* "+((NumGroup)tmp.wordNumVar).locate+", i32 0, i32 0"+"\n");



            resllList.add("call void @memset(i32* "+headptr+", i32 0, i32 "+numAll*4+")\n");
            System.out.println("call void @memset(i32* "+headptr+", i32 0, i32 "+numAll*4+")\n");

    }
    public static int mulAllIndex(List<Integer>intAllIndex)
    {
        int numAll=1;
        for(int i=0;i<intAllIndex.size();i++)
        {
            numAll*=intAllIndex.get(i);
        }
        return numAll;
        //二维数
    }
    public static int findRBracket(int startIndex,List<String> expAnalysisList)
    {
        Stack<String> bracketStack=new Stack<>();
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