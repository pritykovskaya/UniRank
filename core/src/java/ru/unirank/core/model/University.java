package ru.unirank.core.model;

/**
 * Created by IntelliJ IDEA.
 * User: natasha
 * Date: 14.11.11
 */

public class University {

    private String name;
    private String city;
    private String description;

    public University(String name, String city, String description)
    {

        this.name = name;
        this.city = city;
        this.description = description;
    }
    public String getName()
    {
        return name;
    }

    public String getCity()
    {
        return city;
    }

    public String getDescription()
    {
        return description;
    }
}