package Analysis;

import Exp.Exp;

import Lexer.ReserveWord;

import java.util.ArrayList;
import java.util.List;

public class analysis {
    public int storeNum=0;

    //生成表达式存储位置
    public String generStoreLocate(){
        return "%x"+storeNum++;
    }

    //生成表达式的四元式
    public Exp generStoreExp(String op,String arg1,String arg2,String type)
    {
        String storeLocate=generStoreLocate();
        Exp tmpExp;
            tmpExp=new Exp(op,arg1,arg2,storeLocate,type);
        return tmpExp;
    }
}
