import Lexer.Lexer;

import java.io.*;

public class Compiler {
    public static void main(String[] args) {
        //后面要把fileName改为args[0]
        String fileName=args[2];
        File testFile=new File(fileName);
        long fileLength=testFile.length();
        char fileChar[]=new char[(int)(fileLength+100)];

        //将文件中所有字符读到fileChar数组中
        try {
            Reader in=new FileReader(testFile);
            in.read(fileChar);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(fileLength);
        Lexer lexer=new Lexer();
        lexer.lexerMain(fileChar,(int)fileLength);

//        for(char tmp:fileChar)
//        {
//            System.out.print(tmp);
//        }

    }
}
