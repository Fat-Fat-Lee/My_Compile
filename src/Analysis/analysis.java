package Analysis;

import Exp.Exp;

import Lexer.ReserveWord;
import Parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class analysis {
    public static int storeNum=1;

    //生成表达式存储位置
    public static String generStoreLocate(){
//        if(storeNum==1)
//            System.out.println("-----------------------JASLKDHLASJDHJASHD-----------------------------------");
        return "%"+storeNum++;
    }

    //生成表达式的四元式
    public Exp generStoreExp(String op,String arg1,String arg2,String type)
    {
        String storeLocate=new String();
        if(!Parser.global)
            storeLocate=generStoreLocate();
        Exp tmpExp;
            tmpExp=new Exp(op,arg1,arg2,storeLocate,type);
        return tmpExp;
    }
}
