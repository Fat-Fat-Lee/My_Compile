package Lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    public String tmpTokenString;
    public int tmpTokenNum;
    public int ptr=0;//指向字符数组的指针
    public List<ReserveWord> reserveWordList=new ArrayList<>();//保留字表
    public List<IdentWord> identWordList=new ArrayList<>();//标识符表
    public enum symbol{
        Ident,//标识符
        Number,//无符号整数

        //保留字
        If,
        Else,
        While,
        Break,
        Continue,
        Return,

        //分界符
        Assign,//=
        Semicolon,//;
        LPar,//(
        RPar,//)
        LBrace,//{
        RBrace,//}
        Plus,//+
        Mult,//*
        Div,// /除号
        Lt,//<
        Gt,//>
        Eq,//==
        Err//错误
    }
    //构造函数,把保留字表生成
    public Lexer() {

    }
    //lexer初始化
    public void lexerInit(){
        this.reserveWordList.add(new ReserveWord("if","If"));
        this.reserveWordList.add(new ReserveWord("else","Else"));
        this.reserveWordList.add(new ReserveWord("while","While"));
        this.reserveWordList.add(new ReserveWord("break","Break"));
        this.reserveWordList.add(new ReserveWord("continue","Continue"));
        this.reserveWordList.add(new ReserveWord("return","Return"));
    }


    //一系列set get函数
    public String getTmpTokenString() {
        return tmpTokenString;
    }

    public void setTmpTokenString(String tmpTokenString) {
        this.tmpTokenString = tmpTokenString;
    }

    public int getTmpTokenNum() {
        return tmpTokenNum;
    }

    public void setTmpTokenNum(int tmpTokenNum) {
        this.tmpTokenNum = tmpTokenNum;
    }

    public int getPtr() {
        return ptr;
    }

    public void setPtr(int ptr) {
        this.ptr = ptr;
    }


    //跳过空格、换行和tab,指针直接到达非空白字符位置
    public void isSpace(char[] fileChar){
        while(Character.isWhitespace(fileChar[this.ptr]))
        {this.ptr++;}
    }

    //清空token字符串
    public void clearToken(){
        this.tmpTokenString="";
        this.tmpTokenNum=0;

    }

    //getChar读取字符移动指针
    public char getChar(char[] fileChar){
        return  fileChar[this.ptr++];
    }

    //catToken把当前字符和Token联结
    public String catToken(char[] fileChar){
        this.tmpTokenString+=fileChar[this.ptr];
        return this.tmpTokenString;
    }

    //retract指针回退
    public void retract(){
        --this.ptr;
    }

    //reserver查找保留字
    public ReserveWord reserver(String tmpTokenString){
        for(ReserveWord tmp:reserveWordList)
        {
            if(tmpTokenString.equals(tmp.wordName))
                return tmp;
        }
        return null;
    }

    //identer查找已保存标识符
    public IdentWord identer(String tmpTokenString){
        for(IdentWord tmp:identWordList)
        {
            if(tmpTokenString.equals(tmp.wordName))
                return tmp;
        }
        return null;
    }

    //词法处理，一次处理一个单词
    public String getSym(char[] fileChar){
        this.clearToken();
        this.isSpace(fileChar);
        if(Character.isLetter(fileChar[this.ptr])||fileChar[this.ptr]=='_')
        {
            while(Character.isLetter(fileChar[this.ptr])
                    ||Character.isDigit(fileChar[this.ptr])
                    ||fileChar[this.ptr]=='_')
            {
                //当前字符若是字母就贴到token后面，然后指针后移
                this.catToken(fileChar);
                this.getChar(fileChar);
            }
            this.retract();
            //若是保留字，返回保留字symbol
            ReserveWord tmpReserveWord=this.reserver(this.tmpTokenString);
            if(tmpReserveWord!=null)
                return tmpReserveWord.wordSymbol;

            //否则为标识符，查看是否已在标识符表中，在则返回，不在则新建
            IdentWord tmpIdentWord=this.identer(this.tmpTokenString);
            if(tmpIdentWord!=null)
                return tmpIdentWord.wordSymbol;

            tmpIdentWord=new IdentWord(tmpTokenString);
            this.identWordList.add(tmpIdentWord);
            return tmpIdentWord.wordSymbol;
        }
        else if(Character.isDigit(fileChar[this.ptr]))
        {
            while(Character.isDigit(fileChar[this.ptr]))
            {
                //当前字符若是数字就贴到token后面，然后指针后移
                this.catToken(fileChar);
                this.getChar(fileChar);
            }
            this.retract();
            this.tmpTokenNum=Integer.parseInt(this.tmpTokenString);
            return "Number("+tmpTokenString+")";
        }
        else if(fileChar[this.ptr]=='=')
        {
            if(fileChar[this.ptr+1]=='=')
            {
                this.getChar(fileChar);
                return "Eq";
            }
           // this.getChar(fileChar);
            return "Assign";
        }
        else if(fileChar[this.ptr]==';')
        {
            //this.getChar(fileChar);
            return "Semicolon";
        }
        else if(fileChar[this.ptr]=='(')
        {
           // this.getChar(fileChar);
            return "LPar";
        }
        else if(fileChar[this.ptr]==')')
        {
            //this.getChar(fileChar);
            return "RPar";
        }
        else if(fileChar[this.ptr]=='{')
        {
            //this.getChar(fileChar);
            return "LBrace";
        }
        else if(fileChar[this.ptr]=='}')
        {
            //this.getChar(fileChar);
            return "RBrace";
        }
        else if(fileChar[this.ptr]=='+')
        {
            //this.getChar(fileChar);
            return "Plus";
        }
        else if(fileChar[this.ptr]=='*')
        {
            //this.getChar(fileChar);
            return "Mult";
        }
        else if(fileChar[this.ptr]=='/')
        {
           // this.getChar(fileChar);
            return "Div";
        }
        else if(fileChar[this.ptr]=='<')
        {
           // this.getChar(fileChar);
            return "Lt";
        }
        else if(fileChar[this.ptr]=='>')
        {
           // this.getChar(fileChar);
            return "Gt";
        }
        else if(Character.isWhitespace(fileChar[this.ptr]))
        {
            return "Space";
        }//若是空格字符就可以重新调用getSym了
        else{
            return "Err";
        }
    }
    public void lexerMain(char[] fileChar,int fileLength){
        this.lexerInit();
//        String res=this.getSym(fileChar);
//        System.out.println(res);
//        System.out.println(fileChar[this.ptr]);
//        System.out.println(this.ptr);
        while(this.ptr<fileLength)
        {
            String res=this.getSym(fileChar);
            if(res.equals("Space"))
                continue;
            else if(res.equals("Err"))
            {
                System.out.println(res);
                System.exit(0);
            }

            this.getChar(fileChar);
            System.out.println(res);
        }
    }

}
