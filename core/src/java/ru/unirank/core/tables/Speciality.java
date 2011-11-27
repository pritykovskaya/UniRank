package ru.unirank.core.tables;

public class Speciality
{
    private int id;
    private int direction_id;
    private String name;

    public Speciality(int id, String name,int direction_id)
    {
        this.id = id;
        this.name = name;
        this.direction_id = direction_id;
    }

    public Speciality(String name)
    {
        this.id = 0;
        this.name = name;
        this.direction_id = 0;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getDirectionId()
    {
        return direction_id;
    }

}
