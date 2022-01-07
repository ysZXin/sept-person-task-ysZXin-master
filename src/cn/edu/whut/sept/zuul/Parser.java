/**
 * 该类是“World-of-Zuul”应用程序的解析类。
 *
 * parser类的实例，实现对命令的操作：读入命令，解析命令
 *
 * @author  ZYZ
 * @version 1.0
 */

package cn.edu.whut.sept.zuul;

import java.util.Scanner;

public class Parser
{
    private CommandWords commands;
    private Scanner reader;
    /**
     * 创建解析器并初始化命令.
     */
    public Parser()
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     *获取命令
     */
    public Command getCommand()
    {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> ");

        inputLine = reader.nextLine();

        try (Scanner tokenizer = new Scanner(inputLine)) {
            if(tokenizer.hasNext()) {
                word1 = tokenizer.next();   
                if(tokenizer.hasNext()) {
                    word2 = tokenizer.next();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }


        if(commands.isCommand(word1)) {
            return new Command(word1, word2);
        }
        else {
            return new Command(null, word2);
        }
    }

    /**
     * 调用显示命令的函数
     */
    public void showCommands()
    {
        commands.showAll();
    }
}
