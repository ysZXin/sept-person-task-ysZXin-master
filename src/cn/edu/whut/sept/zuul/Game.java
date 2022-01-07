/**
 * 该类是“World-of-Zuul”应用程序的主类。
 * 《World of Zuul》是一款简单的文本冒险游戏。用户可以在一些房间组成的迷宫中探险。
 * 你们可以通过扩展该游戏的功能使它更有趣!.
 *
 * 如果想开始执行这个游戏，用户需要创建Game类的一个实例并调用“play”方法。
 *
 * Game类的实例将创建并初始化所有其他类:它创建所有房间，并将它们连接成迷宫；它创建解析器
 * 接收用户输入，并将用户输入转换成命令后开始运行游戏。
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 1.0
 */
package cn.edu.whut.sept.zuul;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Parser parser; //命令解释器
    private Room currentRoom; //当前房间
    private ArrayList<Integer> position = new ArrayList<Integer>(); //位置列表
    private ArrayList<Room> AllRoom = new ArrayList<Room>(); //房间列表
    private player user; //玩家
    /**
     * 创建游戏并初始化内部数据和解析器.
     */
    public Game() {
        createRooms();
        parser = new Parser();
        this.user=new player("player",0);
    }

    /**
     * 创建所有房间对象并连接其出口用以构建迷宫.
     */
    private void createRooms() {
        Room outside, theater, pub, lab, office,transport;

        // create the rooms
        outside = new Room(1, "outside", "outside the main entrance of the university");//外部
        theater = new Room(2, "theater", "in a lecture theater");//剧院
        pub = new Room(3, "pub", "in the campus pub");//校园
        lab = new Room(4, "lab", "in a computing lab");//实验室
        office = new Room(5, "office", "in the computing admin office");//办公室
        transport =new Room(6,"transport","in Transfer room , You will be transferred to any room");//传送房间

        // initialise room exits
        outside.setExit("east", theater.getposition());
        outside.setExit("south", lab.getposition());
        outside.setExit("west", pub.getposition());
        outside.setExit("north",transport.getposition());

        theater.setExit("west", outside.getposition());
        pub.setExit("east", outside.getposition());
        lab.setExit("north", outside.getposition());
        lab.setExit("east", office.getposition());
        office.setExit("west", lab.getposition());

        outside.setCookie(1);

        office.additem("pen ", 1, ", u can use it to write");
        office.additem("book", 3, ", i like reading books");
        office.additem("cup ", 2, ", u can use it to drink");
        currentRoom = outside; // start game outside
        AllRoom.add(outside);
        AllRoom.add(theater);
        AllRoom.add(pub);
        AllRoom.add(lab);
        AllRoom.add(office);
        AllRoom.add(transport);
        position.add(1);
    }

    /**
     * 游戏主控循环，直到用户输入退出命令后结束整个程序.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop. Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * 向用户输出欢迎信息.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    public void show(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know show sth...
            System.out.println("show what");
            return;
        }

        String sth = command.getSecondWord();
        // look this room
        if (sth.equals("item")) {
            currentRoom.showAllitem();
        } else if (sth.equals("room")) {
            System.out.println(" " + currentRoom.getLongDescription());
            currentRoom.showAllitem();
        } else
            System.out.println("error command");
    }

    public void back() {

        if (position.size() == 1) {
            System.out.println("You're back to where you started");
        } else {
            position.remove(position.size() - 1);
            currentRoom = AllRoom.get(position.get(position.size() - 1) - 1);
            user.setRoomPosition(currentRoom.getposition());
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * 执行用户输入的游戏指令.
     * 
     * @param command 待处理的游戏指令，由解析器从用户输入内容生成.
     * @return 如果执行的是游戏结束指令，则返回true，否则返回false.
     */
    boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        } else if (commandWord.equals("go")) {
            goRoom(command);
        } else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        } else if (commandWord.equals("show")) {
            show(command);
        } else if (commandWord.equals("back")) {
            back();
        }else if (commandWord.equals("look")) {
            user.lookitem();
        }else if (commandWord.equals("take")) {
            String t=command.getSecondWord();
            user.take(Integer.parseInt(t) ,currentRoom);
        }else if (commandWord.equals("drop")) {
            String t=command.getSecondWord();
            user.drop(Integer.parseInt(t) ,currentRoom);
        }
        
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * 执行help指令，在终端打印游戏帮助信息.
     * 此处会输出游戏中用户可以输入的命令列表
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * 执行go指令，向房间的指定方向出口移动，如果该出口连接了另一个房间，则会进入该房间，
     * 否则打印输出错误提示信息.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = AllRoom.get(currentRoom.getExit(direction)-1);
        if (nextRoom == null) {
            System.out.println("There is no door!");
        } 
        else if(nextRoom.getposition()==6){
            final long l = System.currentTimeMillis();
            //System.out.println(l);
            int seed=(int)(l%57);
            currentRoom=AllRoom.get(seed%5);
            System.out.println(nextRoom.getLongDescription()+"any...\nwait a monent...");
            try {
				//睡眠1s
				Thread.sleep(1200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

            position.add(currentRoom.getposition());
            user.setRoomPosition(currentRoom.getposition());
            if(currentRoom.getCookie()==1)
            {
                System.out.println("Here is a cookie. Would you like to eat it?\nYes: Y");
                Scanner input=new Scanner(System.in);
                if(input.nextLine().equals("Y"))
                {
                    currentRoom.setCookie(0);
                    System.out.println("Your bagwg is larger!");
                    user.addbagWg();
                }
            }
            System.out.println(currentRoom.getLongDescription());
        }
        else {
            
            position.add(nextRoom.getposition());
            user.setRoomPosition(nextRoom.getposition());
            currentRoom = nextRoom;
            if(currentRoom.getCookie()==1)
            {
                System.out.println("Here is a cookie. Would you like to eat it?\nYes: Y");
                
                Scanner input=new Scanner(System.in);
                if(input.nextLine().equals("Y"))
                {
                    currentRoom.setCookie(0);
                    System.out.println("Your bagwg is larger!");
                    user.addbagWg();
                }
                //input.close();
            }
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * 执行Quit指令，用户退出游戏。如果用户在命令中输入了其他参数，则进一步询问用户是否真的退出.
     * 
     * @return 如果游戏需要退出则返回true，否则返回false.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true; // signal that we want to quit
        }
    }
}