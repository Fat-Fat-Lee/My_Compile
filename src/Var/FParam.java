package Var;

import Lexer.IdentWord;
import Lexer.Lexer;
import Parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class FParam {
    public int pType;//0代表0维变量；1代表一维数组指针；2代表2二维数组指针
    public List<Integer>intAllIndex=new ArrayList<>();//总的行、lie
    public int numDimen;//二维数组长度
    public String pLocate;//参数地址
    public String pName;//参数名

    public FParam() {
        intAllIndex.add(0);
    }


    public String generFParam()
    {
        if(pType==0)
            return " i32 "+pLocate+" ";
        else
            return " i32* "+pLocate+" ";

    }


}
