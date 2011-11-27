package ru.unirank.core.tables;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 * Date: 25.10.11
 * Time: 10:58
 */

public class Faculty {

    private int id;
    private int university_id;
    private String name;
    private String description;

    public Faculty(int id, int university_id, String name, String description)
    {

        this.id = id;
        this.university_id = university_id;
        this.name = name;
        this.description = description;
    }
    public Faculty(String name, String description)
    {

        this.id = 0;
        this.university_id = 0;
        this.name = name;
        this.description = description;
    }
    public int getId()
    {
        return id;
    }

    public int getUniversityId()
    {
        return university_id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
