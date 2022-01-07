/**
 * 该类是“World-of-Zuul”应用程序的命令类。
 *
 * 如果想开始执行这个游戏，用户需要创建Game类的一个实例并调用“play”方法。
 *
 * Command类的实例将创建并实现对命令的操作：判断命令合法，读取命令
 *
 * @author  ZYZ
 * @version 1.0
 */

package cn.edu.whut.sept.zuul;

public class Command
{
    private String commandWord;
    private String secondWord;

	/**    
     *创建命令并初始化
     */
    public Command(String firstWord, String secondWord)
    {
        commandWord = firstWord;
        this.secondWord = secondWord;
    }
    /**    
     *获取命令1
     */
    public String getCommandWord()
    {
        return commandWord;
    }

    /**    
     *获取命令2
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**    
     *判断命令1的有效性
     */
    public boolean isUnknown()
    {
        return (commandWord == null);
    }

    /**    
     *判断命令2的有效性
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}
