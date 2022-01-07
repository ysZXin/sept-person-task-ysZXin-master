package cn.edu.whut.sept.zuul;

public class testRandomRoom {
    
    /**
     * 测试随机房间的效果
     *    
     *   */
    public static void testcase()
    {

        /**
         * 设定了初始房间的北面为随机传送房间
         * 传送5次查看是否真的随机
         */
        Command command=new Command("go", "north");
        for(int i=1;i<=5;i++)
        {
            System.out.print("the "+i+" transfer: ");
            Game game = new Game();
            game.processCommand(command);
        }
    }

    public static void main(String[] args) {
        testcase();
    }

}