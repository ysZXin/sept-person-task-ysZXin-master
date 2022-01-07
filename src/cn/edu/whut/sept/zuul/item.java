package cn.edu.whut.sept.zuul;

public class item {
    private String itemname;//物品名
    private int  weight;//物品重量
    private String description;//物品描述

    public  item(String itemname, int weight, String description) {
        this.itemname=itemname; 
        this.weight=weight; 
        this.description=description; 
    }

    public void  setName(String itemname)
    {
        this.itemname=itemname;
    }
    public void  setWeight(int weight)
    {
        this.weight=weight;
    }
    public void  setDesc(String description)
    {
        this.description=description;
    }

    public String getName()
    {
    	return this.itemname;
    }
    
    public int getWeight()
    {
    	return weight;
    }
    
    public String getDesc()
    {
    	return this.description;
    }
    
    
}
