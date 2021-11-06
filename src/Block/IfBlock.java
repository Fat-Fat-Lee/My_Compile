package Block;

import Lexer.ReserveWord;

import java.util.List;

public class IfBlock extends Block{
//    public String type;//"if" "else" "elif"
    public String condLocate;//条件块所在的寄存器编号
    public String actionLocate;//动作块所在的寄存器编号
    public String actionBrLocate="%x";//动作块想跳转的块编号
    public String condBrLocate="%x";
    public String condJudgeLocate;//条件判断语句结果存储位置
    public int brCondIndex=0;//条件块跳转语句在resllList中的下标，后面一起修改
    public int brActionIndex=0;
    public int brInIndex=0;

    public IfBlock() {
    }

    public IfBlock(String type, String actionLocate, int brActionIndex) {
        this.type = type;
        this.actionLocate = actionLocate;
        this.brActionIndex = brActionIndex;
    }

    public IfBlock(String type, String condLocate, String actionLocate, String condJudgeLocate, int brCondIndex, int brActionIndex) {
        this.type = type;
        this.condLocate = condLocate;
        this.actionLocate = actionLocate;
        this.condJudgeLocate = condJudgeLocate;
        this.brCondIndex = brCondIndex;
        this.brActionIndex = brActionIndex;
    }
    public String setCondll(){
        return "br i1 "+condJudgeLocate+",label "+actionLocate+", label "+condBrLocate+"\n";
    }
    public String setActionll(){
        return "br label "+actionBrLocate+"\n";
    }
    public void setIfBlock(String type, String actionLocate, int brActionIndex) {
        this.type = type;
        this.actionLocate = actionLocate;
        this.brActionIndex = brActionIndex;
    }
    public void setIfBlock_(String type, String condLocate, String actionLocate, String condJudgeLocate, int brCondIndex, int brActionIndex) {
        this.type = type;
        this.condLocate = condLocate;
        this.actionLocate = actionLocate;
        this.condJudgeLocate = condJudgeLocate;
        this.brCondIndex = brCondIndex;
        this.brActionIndex = brActionIndex;
    }

}
