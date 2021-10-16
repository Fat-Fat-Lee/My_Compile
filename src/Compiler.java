import Comment.Comment;
import Lexer.Lexer;
import Parser.Parser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Compiler {
    public static void main(String[] args) {
        //后面要把fileName改为args[0]
        String fileName=args[0];
        String targetFileName=args[1];
        //String fileName="C:\\Users\\惠普\\Desktop\\大三上ljw\\编译原理\\My_Compile\\aaa.txt";
        //String targetFileName="C:\\Users\\惠普\\Desktop\\大三上ljw\\编译原理\\My_Compile\\bbb.c";
        File testFile=new File(fileName);
        File targetFile=new File(targetFileName);
        long tmpFileLength=testFile.length();
        char tmpFileChar[]=new char[(int)(tmpFileLength+100)];
        List<String> resLexerList=new ArrayList<>();//词法成分表
        //将文件中所有字符读到fileChar数组中
        try {
            Reader in=new FileReader(testFile);
            in.read(tmpFileChar);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(fileLength);

//        char fileChar[]=Comment.delComment(tmpFileChar,(int)tmpFileLength);
//        long fileLength=Comment.fileLength;
          char fileChar[]=tmpFileChar;
          long fileLength=tmpFileLength;
        try {
            Writer out=new FileWriter(targetFile);
            out.write(fileChar,0,(int)fileLength);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

       Lexer lexer=new Lexer();
        lexer.lexerMain(fileChar,(int)fileLength,resLexerList);
        Parser parser=new Parser();
        parser.mainParser(resLexerList);


//        for(char tmp:fileChar)
//        {
//            System.out.print(tmp);
//        }

    }
}
