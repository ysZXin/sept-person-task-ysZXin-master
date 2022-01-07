package cn.edu.whut.sept.zuul;

import java.util.LinkedList;

public class player {
    private String username; //玩家名
    private int bagwg; //背包总重量
    private int res = 0; //剩余容量
    private LinkedList<item> list = new LinkedList<item>(); //背包项目列表
    private int pos; //所处位置

    public void setName(String username) {
        this.username = username;
    }

    public String getName() {
        return username;
    }

    public player(String a, int p) {
        username = a;
        pos = p;
        bagwg = 10;
    }

    public void lookitem() {

        System.out.println("you have " + list.size() + " items:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + ". " + list.get(i).getName() + " " + list.get(i).getWeight() + " "
                    + list.get(i).getDesc() + " ");
        }
        System.out.println("the rest capacity :"+(bagwg-res));
    }

    public void setbagWg(int bagwg) {
        this.bagwg = bagwg;
    }

    public void addbagWg() {
        this.bagwg += 3;
    }

    public int getbagWg()
    {
        return bagwg;
    }

    public void take(int i, Room r) {
        if (r.isitemExits(i)) {
            System.out.println("error command!");
            return;
        }
        int t = r.getitem(i).getWeight();
        if (res + t > bagwg) {
            System.out.print("too heavy!!!\n");
        } else {

            list.add(r.getitem(i));
            r.dropitem(i);
            res += t;
            r.showAllitem();
            lookitem();
        }
    }

    public void setRoomPosition(int position) {
        this.pos = position;
    }

    public int getRoomPosition() {
        return this.pos;
    }

    public void drop(int i, Room r) {
        if (i >= list.size() || i < 0) {
            System.out.println("error command!");
            return;
        }
        int t = r.getitem(i).getWeight();

        r.addtolist(list.get(i));
        list.remove(i);
        res += t;
        res -= list.get(i).getWeight();
        r.showAllitem();
        lookitem();
    }

}
