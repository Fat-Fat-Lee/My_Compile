package Block;

public class WhileBlock {
    public String jmp0;//while条件块
    public String jmp2;//while结束后要跳去的块

    public WhileBlock(String jmp0, String jmp2) {
        this.jmp0 = jmp0;
        this.jmp2 = jmp2;
    }
}
