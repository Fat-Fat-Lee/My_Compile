package Var;

import Lexer.IdentWord;
import Lexer.Lexer;
import Lexer.ReserveWord;

import java.util.ArrayList;
import java.util.List;

public class NumFunction extends NumVar{
   // public String locate;//函数结果存储位置
    public int length;//参数个数
    public String returnType;//返回值类型i32或void
    public List<FParam>FParamList=new ArrayList<>();//参数列表


    public NumFunction() {
    }

    public NumFunction(int length, String returnType) {
        this.length = length;
        this.returnType = returnType;
    }

    public NumFunction(int length, String returnType, List<FParam> FParamList) {
        this.length = length;
        this.returnType = returnType;
        this.FParamList = FParamList;
    }

    public void generParamIdenword(Lexer tmpLexer, List<String> resllList, int belongBlock)
    {
        for(int i=0;i<FParamList.size();i++)
        {
            FParam tmpFParam=FParamList.get(i);
            if(tmpFParam.pType==0)
            {
                String tmpNormalName=tmpFParam.pName;
                if(tmpLexer.identerBlock(tmpNormalName)!=null) {
                    System.out.println("该函数参数变量声明过");
                    System.exit(3);//该变量声明过，报错
                }
                //该变量没有被声明过，加入
                IdentWord tmp=new IdentWord();
                tmp.wordName=tmpFParam.pName;
                tmp.wordSymbol="Ident("+tmpFParam.pName+")";
                tmp.wordType="numNormal";
                tmp.ifConst=false;
                tmp.belongBlock=belongBlock;
                tmp.wordNumVar=NumNormal.identNumNormal();//指定存储位置

                resllList.add(((NumNormal)tmp.wordNumVar).locate+" = alloca i32 \n");
                System.out.println(((NumNormal)tmp.wordNumVar).locate+" = alloca i32 \n");
                resllList.add("store i32 "+tmpFParam.pLocate+", i32* "+((NumNormal)tmp.wordNumVar).locate+"\n");
                System.out.println("store i32 "+tmpFParam.pLocate+", i32* "+((NumNormal)tmp.wordNumVar).locate+"\n");
                tmpLexer.identWordList.add(tmp);

            }
            else
            {
                String tmpNormalName=tmpFParam.pName;
                if(tmpLexer.identerBlock(tmpNormalName)!=null) {
                    System.out.println("该变量声明过");
                    System.exit(3);//该变量声明过，报错
                }
                //该变量没有被声明过，加入
                IdentWord tmp=new IdentWord();
                tmp.wordName=tmpFParam.pName;
                tmp.wordSymbol="Ident("+tmpFParam.pName+")";
                tmp.wordType="numGroup";
                tmp.ifConst=false;
                tmp.belongBlock=belongBlock;
                tmp.wordNumVar= NumGroup.identNumGroup();//指定存储位置
                tmp.ifParam=true;//数组参数，函数中取出方式不同
                tmpLexer.identWordList.add(tmp);

                //完成多维数组声明语句
                if(tmpFParam.pType==1)
                {
                    ((NumGroup)tmp.wordNumVar).numDimen=1;
                    ((NumGroup)tmp.wordNumVar).intAllIndex=tmpFParam.intAllIndex;

                    resllList.add(((NumGroup)tmp.wordNumVar).locate+" = alloca i32* \n");
                    System.out.println(((NumGroup)tmp.wordNumVar).locate+" = alloca i32* \n");
                    resllList.add("store i32* "+tmpFParam.pLocate+", i32* * "+((NumGroup)tmp.wordNumVar).locate+"\n");
                    System.out.println("store i32* "+tmpFParam.pLocate+", i32* * "+((NumGroup)tmp.wordNumVar).locate+"\n");

                }


            }
        }



    }
}
