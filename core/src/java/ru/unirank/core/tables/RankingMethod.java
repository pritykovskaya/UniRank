package ru.unirank.core.tables;

/**
 * Created by IntelliJ IDEA.
 * User: alex
 */

public class RankingMethod
{

    private int id;
    private int direction_id;
    private int raw_description_id;
    private int coeff;
    private String implement_class;

    public RankingMethod(int id, int direction_id, int raw_description_id, int coeff, String implement_class)
    {

        this.id = id;
        this.direction_id = direction_id;
        this.raw_description_id = raw_description_id;
        this.coeff = coeff;
        this.implement_class = implement_class;
    }
    public RankingMethod(int coeff, String implement_class, int raw_description_id)
    {

        this.id = 0;
        this.direction_id = 0;
        this.raw_description_id = raw_description_id;
        this.coeff = coeff;
        this.implement_class = implement_class;
    }

    public int getId()
    {
        return id;
    }

    public int getDerectionId()
    {
        return direction_id;
    }

    public int getCoeff()
    {
        return coeff;
    }

    public int getRawDescriptionId()
    {
        return raw_description_id;
    }

    public void setCoeff(int coeff){
        this.coeff = coeff;
    }

    public String getImplementClass()
    {
        return implement_class;
    }

    public void detImplementClass(String implement_class){
        this.implement_class = implement_class;
    }
}