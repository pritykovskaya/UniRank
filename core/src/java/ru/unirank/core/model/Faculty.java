package ru.unirank.core.model;

/**
 * Created by IntelliJ IDEA.
 * User: natasha
 * Date: 14.11.11
 */
 public class Faculty {

    private int university_id;
    private String name;
    private String description;

    public Faculty(String name, String description)
    {

        //this.university_id = university_id;
        this.name = name;
        this.description = description;
    }

    public int getUniversity_id()
    {
        return university_id;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }
}

