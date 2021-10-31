package Var;

import Lexer.IdentWord;
import Lexer.ReserveWord;

import java.util.ArrayList;
import java.util.List;

public class NumFunction extends NumVar{
   // public String locate;//函数结果存储位置
    public int length;//参数个数,无需存储参数类型，因为只会是int
    public String returnType;//返回值类型int或void

    public NumFunction() {
    }

    public NumFunction(int length, String returnType) {
        this.length = length;
        this.returnType = returnType;
    }
}
