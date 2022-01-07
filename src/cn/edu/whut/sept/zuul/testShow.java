package cn.edu.whut.sept.zuul;

public class testShow {
    /**
     * 测试显示房间物品
     * 
     */
    public static void testshow() {

        //查看房间的信息
        Command command = new Command("show", "room");
        Game game = new Game();
        game.processCommand(command);

        //走到有物品的房间查看房间的信息
        command =new Command("go", "south");
        game.processCommand(command);
        command =new Command("go", "east");
        game.processCommand(command);
        command =new Command("show", "item");
        game.processCommand(command);
    }

    public static void main(String[] args) {
        testshow();
    }
}
