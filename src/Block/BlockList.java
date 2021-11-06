package Block;

import Analysis.analysis;
import Lexer.ReserveWord;

import java.util.ArrayList;
import java.util.List;

public class BlockList {
    public static List<Block> blockList=new ArrayList<>();//block列表
    public static void findBrLocate(List<String>resllList)
    {
        MainBlock mainBlock=new MainBlock("main","%0");
        blockList.add(0,mainBlock);
        if(blockList.size()-1>=0)
        {
            if(!blockList.get(blockList.size()-1).type.equals("main"))
            {
                String tmp=analysis.generStoreLocate();
                int intTmp=analysis.storeNum-1;
                resllList.add(intTmp+":\n");
                resllList.add("ret i32 0\n");
            }
        }
        for(int i=0;i<blockList.size();i++)
        {
            System.out.println(blockList.get(i).type);
        }
        for(int i=0;i<blockList.size();i++)
        {
            Block tmpBlock=blockList.get(i);
            if(tmpBlock.type.equals("if")||tmpBlock.type.equals("elif"))
            {
                int tag=0;
                //条件跳转，跳往下一块
                if((i+1)<blockList.size())
                {
                    Block tmpFindBlock_=blockList.get(i+1);
                    if(tmpFindBlock_.type.equals("main"))
                        ((IfBlock)tmpBlock).condBrLocate=((MainBlock)tmpFindBlock_).mainLocate;
                    else  if(tmpFindBlock_.type.equals("else"))
                        ((IfBlock)tmpBlock).condBrLocate=((IfBlock)tmpFindBlock_).actionLocate;
                    else  if(tmpFindBlock_.type.equals("if")||tmpFindBlock_.type.equals("elif"))
                        ((IfBlock)tmpBlock).condBrLocate=((IfBlock)tmpFindBlock_).condLocate;

                    resllList.set(((IfBlock)tmpBlock).brCondIndex,((IfBlock) tmpBlock).setCondll());
                }
                //动作跳转，跳去下一个if块，或者最近的main块
                for(int j=i;j<blockList.size();j++)
                {
                    if(resllList.get(((IfBlock)tmpBlock).brActionIndex).startsWith("don't"))
                        resllList.set(((IfBlock)tmpBlock).brActionIndex,"\n");
                    else
                        {
                            Block tmpFindBlock=blockList.get(j);
                            if(tmpFindBlock.type.equals("main"))
                            {
                                tag=1;
                                ((IfBlock)tmpBlock).actionBrLocate=((MainBlock)tmpFindBlock).mainLocate;
                                resllList.set(((IfBlock)tmpBlock).brActionIndex,((IfBlock) tmpBlock).setActionll());
                                break;
                            }
                        }

//                    else if(tmpFindBlock.type.equals("if")&&j==i+1)
//                    {
//                        tag=1;
//                        ((IfBlock)tmpBlock).actionBrLocate=((IfBlock)tmpFindBlock).condLocate;
//                        resllList.set(((IfBlock)tmpBlock).brActionIndex,((IfBlock) tmpBlock).setActionll());
//                        break;
//                    }
                }
                if(tag==0)
                {
                    System.out.println("程序没有返回值");
                    System.exit(3);
                }
            }
            else if(tmpBlock.type.equals("else"))
            {//动作块，跳去最近的main块或者下一个if块
                if(resllList.get(((IfBlock)tmpBlock).brActionIndex).startsWith("don't"))
                    resllList.set(((IfBlock)tmpBlock).brActionIndex,"\n");
                else
                {
                    int tag=0;
                    for(int j=i;j<blockList.size();j++)
                    {
                        Block tmpFindBlock=blockList.get(j);
                        if(tmpFindBlock.type.equals("main"))
                        {
                            tag=1;
                            ((IfBlock)tmpBlock).actionBrLocate=((MainBlock)tmpFindBlock).mainLocate;
                            resllList.set(((IfBlock)tmpBlock).brActionIndex,((IfBlock) tmpBlock).setActionll());
                            break;
                        }
//                    else if(tmpFindBlock.type.equals("if")&&j==i+1)
//                    {
//                        tag=1;
//                        ((IfBlock)tmpBlock).actionBrLocate=((IfBlock)tmpFindBlock).condLocate;
//                        if(resllList.get(((IfBlock)tmpBlock).brActionIndex).startsWith("don't"))
//                            resllList.set(((IfBlock)tmpBlock).brActionIndex,"\n");
//                        else
//                            resllList.set(((IfBlock)tmpBlock).brActionIndex,((IfBlock) tmpBlock).setActionll());
//                        break;
//                    }
                    }
                    if(tag==0)
                    {
                        System.out.println("程序没有返回值");
                        System.exit(3);
                    }
                }

            }
            else if(tmpBlock.type.equals("main"))
            {//动作块，跳去最近的main块或者下一个if块
                int tag=0;
                if(i+1<blockList.size()&&blockList.get(i+1).type.equals("if"))
                {}
                else
                {
                    int setIndex=0;
                    if(i+1<blockList.size())
                    {
                        Block mainThing=blockList.get(i+1);
                        if(mainThing.type.equals("elif")||mainThing.type.equals("else"))
                        {
                            setIndex=((IfBlock) mainThing).mainLastBrIndex;
                            System.out.println(setIndex);
                        }
                        else if(mainThing.type.equals("main"))
                        {
                            setIndex=((MainBlock) mainThing).mainLastBrIndex;
                            System.out.println("BBB");
                        }

                    }
                    for(int j=i+1;j<blockList.size();j++)
                    {
                        Block tmpFindBlock=blockList.get(j);
                        if(tmpFindBlock.type.equals("main"))
                        {
                            System.out.println("jdkcnjnsckjdn"+((MainBlock) tmpFindBlock).mainLocate);
                            resllList.set(setIndex,"br label "+((MainBlock) tmpFindBlock).mainLocate+"\n");
                            break;
                        }

                    }

                }

            }
            else
                System.exit(3);
        }
        for(int i=0;i<resllList.size();i++)
        {
            if(resllList.get(i).startsWith("ret")&&i+1<resllList.size()&&resllList.get(i+1).startsWith("br"))
                resllList.set(i+1,"\n");
            if(resllList.get(i).startsWith("br")&&i+1<resllList.size()&&resllList.get(i+1).startsWith("br"))
                resllList.set(i,"\n");
        }
    }
}
