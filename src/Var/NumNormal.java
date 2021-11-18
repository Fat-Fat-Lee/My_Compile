package Var;

import Analysis.analysis;

public class NumNormal extends NumVar{
    public String locate;//变量存储位置
    public String numType;
    public String loadLocate;
    public NumNormal() {
    }

    public static NumNormal identNumNormal(){
        NumNormal tmp=new NumNormal();
        tmp.locate= analysis.generStoreLocate();
        tmp.numType="int";
        return tmp;
    }
//    public static NumNormal identConstNumNormal(String wordName){
//        NumNormal tmp=new NumNormal();
//        tmp.locate= "@"+wordName;
//        tmp.numType="int";
//        return tmp;
//    }
}
