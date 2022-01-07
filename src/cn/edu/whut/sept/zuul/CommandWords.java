/**
 * 该类是“World-of-Zuul”应用程序的词法类。
 *
 *
 * Command类的实例将创建并实现对命令的操作：判断命令合法，显示所有的命令
 *
 * @author  ZYZ
 * @version 1.0
 */
package cn.edu.whut.sept.zuul;

public class CommandWords
{
	/**    
     *创建命令关键词并初始化
     */
    private static final String[] validCommands = {
            "go", "quit", "help","show","back","look","take","drop"
    };

    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**    
     *判断命令是否有效
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        return false;
    }

    
    /**    
     *显示所有命令
     */
    public void showAll()
    {
        for(String command: validCommands) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
