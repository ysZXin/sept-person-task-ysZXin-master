package cn.edu.whut.sept.zuul;

public class testGoAndBack {
    
    /**
     * 测试命令“go”和“back”
     */
    public static void testcase()
    {
        //测试能否正确到达地点，且在没有该方向的时候的正确性。
        Game game = new Game();
        Command command=new Command("go", "south");
        game.processCommand(command);
        command=new Command("go", "east");
        game.processCommand(command);
        command =new Command("go", "east");

        //测试能否正确回到原点
        command=new Command("back","");
        game.processCommand(command);
        command=new Command("back","");
        game.processCommand(command);
        command=new Command("back","");
        game.processCommand(command);
        command=new Command("back","");
        game.processCommand(command);
    }

    public static void main(String[] args) {
        testcase();
    }

}