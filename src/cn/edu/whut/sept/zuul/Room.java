/**
 * 该类是“World-of-Zuul”应用程序的房间类。
 *
 * Room类的实例将创建并实现对房间的所有操作：房间的相连，各个方向，描述等等
 *
 * @author  ZYZ
 * @version 1.0
 */

 package cn.edu.whut.sept.zuul;

import java.util.Set;
import java.util.HashMap;
import java.util.LinkedList;

public class Room
{
    private int cookie; //魔法饼干
    private String name; //房间名
    private Integer pos; //房间位置
    private String description; //描述
    private HashMap<String, Integer> exits; //出口
    private LinkedList<item> list=new LinkedList<item>(); //房间物品列表
    /**
     * 创建房间并初始化内部数据
     */
    public Room(int position,String name,String description)
    {
        this.pos=position;
        this.name=name;
        this.description = description;
        this.cookie=0;
        exits = new HashMap<>();
    }

    public void setCookie(int i)
    {
        this.cookie=i;
    } 

    public int getCookie()
    {
        return cookie;
    } 

    public String getname()
    {
        return this.name;
    }


    public Integer getposition()
    {
        return this.pos;
    }

    /**
     * 添加房间物品
     */
    public void additem(String itemname,int weight,String description)
    {
        list.add(new item(itemname,weight,description));
    }


    public item getitem(int i)
    {
        return list.get(i);
    }

    public void addtolist(item a)
    {
        list.add(a);
    }

    public void dropitem(int i)
    {
        list.remove(i);
    }

    public boolean isitemExits(int i)
    {
        return list.size()<i&&i>=0;
    }

    /**
     * 显示房间物品
     */
    public void showAllitem()
    {
        System.out.println("there are "+list.size()+" itemes:");
        for(int i=0;i<list.size();i++)
        {
            System.out.println(i+". "+list.get(i).getName()+" "+list.get(i).getWeight()+" "+list.get(i).getDesc()+" ");
        }
    }

    /**
     * 设置房间的相邻关系
     */
    public void setExit(String direction, Integer neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * 获取短描述
     */
    public String getShortDescription()
    {
        return description;
    }

    
    /**
     * 获取长描述
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    
    /**
     *获取所有的出口
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    
    /**
     * 获取对应出口的房间
     */
    public Integer getExit(String direction)
    {
        return exits.get(direction);
    }
}


