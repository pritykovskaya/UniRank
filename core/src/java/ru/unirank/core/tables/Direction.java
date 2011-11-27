package ru.unirank.core.tables;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 25.10.11
 * Time: 10:56
 */

public class Direction
{

    private int id;
    private String name;

    public Direction(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public Direction(String name)
    {
        this.id = 0;
        this.name = name;
    }
    public String getName()
    {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
    public int getId()
    {
        return id;
    }

}
