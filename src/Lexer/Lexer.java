package Lexer;
import Parser.Parser;
import Var.FParam;
import Var.NumFunction;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    public String tmpTokenString;
    public int tmpTokenNum;
    public int ptr=0;//指向字符数组的指针
    public boolean ifContinue=true;
    public List<ReserveWord> reserveWordList=new ArrayList<>();//保留字表
    public List<IdentWord> identWordList=new ArrayList<>();//标识符表
    //构造函数,把保留字表生成
    public Lexer() {

    }
    //lexer初始化
    public void lexerInit(List<String> resllList){
        this.reserveWordList.add(new ReserveWord("int","Int"));
        this.reserveWordList.add(new ReserveWord("void","Void"));
        this.reserveWordList.add(new ReserveWord("if","If"));
        this.reserveWordList.add(new ReserveWord("else","Else"));
        this.reserveWordList.add(new ReserveWord("while","While"));
        this.reserveWordList.add(new ReserveWord("break","Break"));
        this.reserveWordList.add(new ReserveWord("continue","Continue"));
        this.reserveWordList.add(new ReserveWord("const","Const"));
        this.reserveWordList.add(new ReserveWord("return","Return"));

//声明好库函数
//        this.identWordList.add(IdentWord.generIdentFunction(this,resllList,"Ident(getint)",0,"int"));
//        this.identWordList.add(IdentWord.generIdentFunction(this,resllList,"Ident(getch)",0,"int"));
//        this.identWordList.add(IdentWord.generIdentFunction(this,resllList,"Ident(putint)",1,"void"));
//        this.identWordList.add(IdentWord.generIdentFunction(this,resllList,"Ident(putch)",1,"void"));
//        this.identWordList.add(IdentWord.generIdentFunction(this,resllList,"Ident(memset)",3,"void"));


        //getint
        FParam getintFparam=new FParam();
        List<FParam>getintFParamList=new ArrayList<>();//参数列表
        NumFunction getintNumFunction=new NumFunction(0,"i32",getintFParamList);
        this.identWordList.add(IdentWord.generIdentFunction(this,resllList,"Ident(getint)",getintNumFunction));

        //getch
        FParam getchFparam=new FParam();
        List<FParam>getchFParamList=new ArrayList<>();//参数列表
        NumFunction getchNumFunction=new NumFunction(0,"i32",getchFParamList);
        this.identWordList.add(IdentWord.generIdentFunction(this,resllList,"Ident(getch)",getchNumFunction));

        //putint
        FParam putintFparam=new FParam();
        putintFparam.pType=0;
        List<FParam>putintFParamList=new ArrayList<>();//参数列表
        putintFParamList.add(putintFparam);
        NumFunction putintNumFunction=new NumFunction(1,"void",putintFParamList);
        this.identWordList.add(IdentWord.generIdentFunction(this,resllList,"Ident(putint)",putintNumFunction));

        //putch
        FParam putchFparam=new FParam();
        putchFparam.pType=0;
        List<FParam>putchFParamList=new ArrayList<>();//参数列表
        putchFParamList.add(putchFparam);
        NumFunction putchNumFunction=new NumFunction(1,"void",putchFParamList);
        this.identWordList.add(IdentWord.generIdentFunction(this,resllList,"Ident(putch)",putchNumFunction));


        //int getarray(int []);
        FParam getarrayFparam=new FParam();
        getarrayFparam.pType=1;
        List<FParam>getarrayFParamList=new ArrayList<>();//参数列表
        getarrayFParamList.add(getarrayFparam);
        NumFunction getarrayNumFunction=new NumFunction(1,"i32",getarrayFParamList);
        this.identWordList.add(IdentWord.generIdentFunction(this,resllList,"Ident(getarray)",getarrayNumFunction));


        //void putarray(int, int[]);
        FParam putarrayFparam1=new FParam();
        putarrayFparam1.pType=0;
        FParam putarrayFparam2=new FParam();
        putarrayFparam2.pType=1;
        List<FParam>putarrayFParamList=new ArrayList<>();//参数列表
        putarrayFParamList.add(putarrayFparam1);
        putarrayFParamList.add(putarrayFparam2);
        NumFunction putarrayNumFunction=new NumFunction(2,"void",putarrayFParamList);
        this.identWordList.add(IdentWord.generIdentFunction(this,resllList,"Ident(putarray)",putarrayNumFunction));


        resllList.add("declare i32 @getint()\n");
        resllList.add("declare i32 @getch()\n");
        resllList.add("declare i32 @getarray(i32*)\n");
        resllList.add("declare void @putint(i32)\n");
        resllList.add("declare void @putch(i32)\n");
        resllList.add("declare void @putarray(i32, i32*)\n");

        resllList.add("declare void @memset(i32*, i32, i32)\n");
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
    public void isSpace(char[] fileChar,int fileLength){
        while(Character.isWhitespace(fileChar[this.ptr]))
        {this.ptr++;}
        if(this.ptr>=fileLength)
            ifContinue=false;
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
        for(int i =Parser.blockStack.size()-1;i>=0;i--)
        {
            for(IdentWord tmp:identWordList)
            {
                if(tmpTokenString.equals(tmp.wordName)&&tmp.belongBlock==Parser.blockStack.get(i))
                    return tmp;
            }
        }

        return null;
    }
    //查找当前块能否声明此变量
    public IdentWord identerBlock(String tmpTokenString){
        for(IdentWord tmp:identWordList)
        {
            if(tmpTokenString.equals(tmp.wordName)&&tmp.belongBlock==Parser.blockStack.get(Parser.blockStack.size()-1))
                return tmp;
        }


        return null;
    }

    //词法处理，一次处理一个单词
    public String getSym(char[] fileChar,int fileLength){
        this.clearToken();
        this.isSpace(fileChar,fileLength);
        if(!ifContinue)
            return "END";
        if(Character.isLetter(fileChar[this.ptr])||fileChar[this.ptr]=='_')
        {
            while(Character.isLetter(fileChar[this.ptr])||fileChar[this.ptr]=='_'||Character.isDigit(fileChar[this.ptr]))
            {
                //当前字符若是字母就贴到token后面，然后指针后移
                this.catToken(fileChar);
                this.getChar(fileChar);
            }
            retract();
            //若是保留字，返回保留字symbol
            ReserveWord tmpReserveWord=this.reserver(this.tmpTokenString);
            if(tmpReserveWord!=null)
                return tmpReserveWord.wordSymbol;

//-----------------------------标识符部分---------------------------------------------------------
            //否则为标识符，查看是否已在标识符表中，在则返回，不在则新建
//            IdentWord tmpIdentWord=this.identer(this.tmpTokenString);
//            if(tmpIdentWord!=null)
//                return tmpIdentWord.wordSymbol;
//
//            tmpIdentWord=new IdentWord(tmpTokenString);
//            this.identWordList.add(tmpIdentWord);
//            return tmpIdentWord.wordSymbol;

            return "Ident("+tmpTokenString+")";//标识符建立到语法分析进行
        }
        else if(Character.isDigit(fileChar[this.ptr]))
        {
            int numType=10;
            //16进制
            if(fileChar[this.ptr]=='0'&&(fileChar[this.ptr+1]=='X'||fileChar[this.ptr+1]=='x'))
            {
                this.ptr+=2;
                numType=16;
                if(!(Character.isDigit(fileChar[this.ptr])||(fileChar[this.ptr]>='a'&&fileChar[this.ptr]<='f')
                        ||(fileChar[this.ptr]>='A'&&fileChar[this.ptr]<='F')))
                    return "Err";

                while(Character.isDigit(fileChar[this.ptr])||(fileChar[this.ptr]>='a'&&fileChar[this.ptr]<='f')
                        ||(fileChar[this.ptr]>='A'&&fileChar[this.ptr]<='F'))
                {
                    this.catToken(fileChar);
                    this.getChar(fileChar);
                }
                this.retract();
                this.tmpTokenNum=Integer.parseInt(this.tmpTokenString,numType);
                return "Number("+tmpTokenNum+")";
            }
            //8或10进制
            while(Character.isDigit(fileChar[this.ptr]))
            {
                //当前字符若是数字就贴到token后面，然后指针后移
                this.catToken(fileChar);
                this.getChar(fileChar);
            }
            this.retract();
            if(this.tmpTokenString.startsWith("0"))
                numType=8;
            this.tmpTokenNum=Integer.parseInt(this.tmpTokenString,numType);
            return "Number("+tmpTokenNum+")";
        }

        else if(fileChar[this.ptr]=='=')
        {
            if(fileChar[this.ptr+1]=='=')
            {
                this.getChar(fileChar);
                return "Eq";
            }
            //this.getChar(fileChar);
            return "Assign";
        }
        else if(fileChar[this.ptr]==',')
        {
            //this.getChar(fileChar);
            return "Comma";
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
        else if(fileChar[this.ptr]=='[')
        {
            // this.getChar(fileChar);
            return "LBracket";
        }
        else if(fileChar[this.ptr]==']')
        {
            //this.getChar(fileChar);
            return "RBracket";
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
        else if(fileChar[this.ptr]=='-')
        {
            //this.getChar(fileChar);
            return "Minus";
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
        else if(fileChar[this.ptr]=='%')
        {
            // this.getChar(fileChar);
            return "Percent";
        }
        else if(fileChar[this.ptr]=='<')
        {
            // this.getChar(fileChar);
            if(fileChar[this.ptr+1]=='=')
            {
                this.getChar(fileChar);
                return "Le";
            }
            return "Lt";
        }
        else if(fileChar[this.ptr]=='>')
        {
            // this.getChar(fileChar);
            if(fileChar[this.ptr+1]=='=')
            {
                this.getChar(fileChar);
                return "Ge";
            }
            return "Gt";
        }
        else if(fileChar[this.ptr]=='!')
        {
            // this.getChar(fileChar);
            if(fileChar[this.ptr+1]=='=')
            {
                this.getChar(fileChar);
                return "Ne";
            }
            return "Oppose";
        }
        else if(fileChar[this.ptr]=='&')
        {
            // this.getChar(fileChar);
            if(fileChar[this.ptr+1]=='&')
            {
                this.getChar(fileChar);
                return "And";
            }
            else
                System.exit(1);
        }
        else if(fileChar[this.ptr]=='|')
        {
            // this.getChar(fileChar);
            if(fileChar[this.ptr+1]=='|')
            {
                this.getChar(fileChar);
                return "Or";
            }
            else
                System.exit(1);
        }
        else if(Character.isWhitespace(fileChar[this.ptr]))
        {
            return "Space";
        }//若是空格字符就可以重新调用getSym了
        else{
            return "Err";
        }
        return "";
    }

    public void lexerMain(char[] fileChar,int fileLength,List<String> resLexerList,List<String> resllList){
        this.lexerInit(resllList);
//        String res=this.getSym(fileChar);
//        System.out.println(res);
//        System.out.println(fileChar[this.ptr]);
//        System.out.println(this.ptr);
        while(this.ptr<fileLength)
        {
            String res=this.getSym(fileChar,fileLength);
            if(res.equals("Space"))
                continue;
            else if(res.equals("Err"))
            {
                // System.out.println(res);
                System.exit(1);
            }
            else if(res.equals("END"))
            {
                resLexerList.add(res);
                break;
            }
            this.getChar(fileChar);

            System.out.println(res);

            resLexerList.add(res);

        }



    }

}