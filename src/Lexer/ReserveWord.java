package Lexer;

public class ReserveWord {
    public String wordName;
    public String wordSymbol;
    public int numType;

    public ReserveWord(String wordName, String wordSymbol) {
        this.wordName = wordName;
        this.wordSymbol = wordSymbol;
        this.numType = -1;
    }
}
