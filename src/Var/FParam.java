package Var;

import Lexer.IdentWord;
import Lexer.Lexer;
import Parser.Parser;

import java.util.List;

public class FParam {
    public int pType;//0代表0维变量；1代表一维数组指针；2代表2二维数组指针
    public int pArrayLen;//二维数组长度
    public String pLocate;//参数地址
    public String pName;//参数名

    public FParam() {
    }

    public FParam(int pType, int pArrayLen, String pLocate, String pName) {
        this.pType = pType;
        this.pArrayLen = pArrayLen;
        this.pLocate = pLocate;
        this.pName = pName;
    }

    public String generFParam()
    {
        if(pType==0)
            return " i32 "+pLocate+" ";
        else if(pType==1)
            return " i32* "+pLocate+" ";
        else
            return " [ "+pArrayLen+" x i32 ]* "+pLocate+" ";
    }


}
