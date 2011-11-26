package ru.unirank.core.model;

/**
 * Created by IntelliJ IDEA.
 * User: natasha
 * Date: 14.11.11
 */

public class Speciality
{
    private int direction_id;
    private String name;

    public Speciality(String name,int direction_id)
    {

        this.name = name;
        this.direction_id = direction_id;
    }
    public String getName()
    {
        return name;
    }

    public int getDirection_id()
    {
        return direction_id;
    }

}

