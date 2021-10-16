package Parser;

import Lexer.Lexer;

import java.util.List;

public class Parser {
    public String tmpSym;
    public int resLexerIndex=0;
    public void mainParser(List<String> resLexerList){
        getSym(resLexerList);
        if(tmpSym.equals("END"))
            System.exit(0);
        compUnitParser(resLexerList);
    }
    public void compUnitParser(List<String> resLexerList){
      //  System.out.println("IN compunit");

        funcDefParser(resLexerList);
       // System.out.println("compUnit");
    }
    public void funcDefParser(List<String> resLexerList){
        //System.out.println("IN funcdef");

        funcTypeParser(resLexerList);
        while(tmpSym.startsWith("Ident")){
            getSym(resLexerList);
            if(tmpSym.equals("LPar")){
                getSym(resLexerList);
                if(tmpSym.equals("RPar")){
                    getSym(resLexerList);
                    blockParser(resLexerList);
                }
                else
                    System.exit(2);
            }
            else
                System.exit(2);
        }
      //  System.out.println("funcDef");
    }
    public void blockParser(List<String> resLexerList){
        System.out.println("IN block");

        if(tmpSym.equals("LBrace")){
            getSym(resLexerList);
            stmtParser(resLexerList);
            if(tmpSym.equals("RBrace"))
            {
                getSym(resLexerList);
                System.out.println("block");
            }
            else
                System.exit(2);
        }
        else
            System.exit(2);
    }
    public void funcTypeParser(List<String> resLexerList){
      //  System.out.println("IN functype");
        if(tmpSym.equals("Int"))
        {
            getSym(resLexerList);
          //  System.out.println("funcType");
        }
        else
            System.exit(2);
    }
    public void stmtParser(List<String> resLexerList){
      //  System.out.println("IN stmt");

        if(tmpSym.equals("Return"))
        {
            getSym(resLexerList);
            if(tmpSym.startsWith("Number"))
            {
                getSym(resLexerList);
                if(tmpSym.equals("Semicolon"))
                {
                    getSym(resLexerList);
                  //  System.out.println("stmt");
                }
                else
                    System.exit(2);
            }
            else
                System.exit(2);
        }
        else
            System.exit(2);

    }
    public void getSym(List<String> resLexerList){
        if(resLexerIndex<resLexerList.size())
            tmpSym=resLexerList.get(resLexerIndex++);
        else
            tmpSym="END";
        return;
    }
}
