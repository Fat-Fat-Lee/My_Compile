package Lexer;

public class IdentWord {
    public String wordName;
    public String wordSymbol;
    public String wordValue;

    public IdentWord(String wordName) {
        this.wordName = wordName;
        this.wordSymbol ="Ident("+wordName+")";
        this.wordValue=wordName;
    }

}
