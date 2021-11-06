package Block;

public class MainBlock extends Block{
    public String mainLocate;
//    String mainIfLocate;
//    int mainIfIndex;
    public int mainLastBrIndex;

    public MainBlock() {
    }

    public MainBlock(String type, String mainLocate) {
        this.type = type;
        this.mainLocate = mainLocate;

    }
    public void setMainBlock(String type, String mainLocate,int mainLastBrIndex) {
        this.type = type;
        this.mainLocate = mainLocate;
        this.mainLastBrIndex=mainLastBrIndex;
    }
}
