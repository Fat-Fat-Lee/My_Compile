package Comment;

public class Comment {
    public static int fileLength=0;
    public static char[] delComment(char[] tmpFileChar,int tmpFileLength){
        char fileChar[]=new char[(int)(tmpFileLength+100)];
        int tag=0;
        int j=0;
        for(int i=0;i<tmpFileLength;i++)
        {
            if(tmpFileChar[i]=='/')
            {
                if(tmpFileChar[i+1]=='/')
                {
                    i+=2;
                    for(;tmpFileChar[i]!='\n'&&i<tmpFileLength;i++);
                    i--;
                    //System.out.println("测一下"+tmpFileChar[i+1]+tmpFileChar[i+2]+tmpFileChar[i+3]);
                }
                else if(tmpFileChar[i+1]=='*')
                {
                    i+=2;
                    for(;(tmpFileChar[i]!='*'||tmpFileChar[i+1]!='/')&&i<tmpFileLength;i++);
                    i++;
                }
                else
                    fileChar[j++]=tmpFileChar[i];
            }
            else
                fileChar[j++]=tmpFileChar[i];
        }
        fileLength=j;
//        for(char tmp:fileChar)
//            System.out.print(tmp);
//        System.out.println(j);
        return fileChar;
    }
}
