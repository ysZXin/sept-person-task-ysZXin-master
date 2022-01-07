package cn.edu.whut.sept.zuul;

public class testPlayer {
    /**
     * 测试显示房间物品
     * 
     */
    public static void testplayer() {
        
        //初始化
        Command command = new Command("go", "south");
        Game game = new Game();
        game.processCommand(command);

        System.out.println("\n测试走到有物品的房间拿物品");
        command=new Command("go", "east");
        game.processCommand(command);
        command =new Command("take", "1");
        game.processCommand(command);

        System.out.println("\n测试物品拿太多了背包装不下");
        command =new Command("take", "1");
        game.processCommand(command);
        command =new Command("take", "0");
        game.processCommand(command);
        
        System.out.println("\n测试放下物品和吃魔法饼干升级背包");
        
        command =new Command("go", "west");
        game.processCommand(command);
        command =new Command("go", "north");
        game.processCommand(command);

        command =new Command("drop", "1");
        game.processCommand(command);
        command =new Command("drop", "0");
        game.processCommand(command);
    }

    public static void main(String[] args) {
        testplayer();
    }
}
