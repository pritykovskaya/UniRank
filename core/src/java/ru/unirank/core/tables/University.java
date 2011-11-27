package ru.unirank.core.tables;

//import org.webharvest.runtime.variables.Variable;

/**
 * User: pritykovskaya
 * Date: 26.09.11
 */

public class University {

    private int id;
    private String name;
    private String city;
    private String description;

    public University(int id, String name, String city, String description)
    {
        this.id = id;
        this.name = name;
        this.city = city;
        this.description = description;
    }

    public University(String name, String city, String description)
    {
        this.name = name;
        this.city = city;
        this.description = description;
    }
    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getCity()
    {
        return city;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}