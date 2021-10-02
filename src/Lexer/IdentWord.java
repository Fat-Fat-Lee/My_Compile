package Lexer;

public class IdentWord {
    public String wordName;
    public String wordSymbol;
    public String wordValue;
    public int numType;

    public IdentWord(String wordName) {
        this.wordName = wordName;
        this.wordSymbol ="Ident("+wordName+")";
        this.wordValue=wordName;
        this.numType = -1;
    }

}
