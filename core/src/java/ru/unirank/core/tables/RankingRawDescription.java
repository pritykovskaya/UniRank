package ru.unirank.core.tables;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 */

public class RankingRawDescription
{

    private int id;
    private String description;

    public RankingRawDescription(int id, String description)
    {

        this.id = id;
        this.description = description;
    }

    public int getId()
    {
        return id;
    }

    public RankingRawDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
