package ru.unirank.core.model;

/**
 * Created by IntelliJ IDEA.
 * User: natasha
 * Date: 14.11.11
 */
public class RankingMethod
{

    private int direction_id;
    private int coeff;
    private String implement_class;

    public RankingMethod(int direction_id, int coeff, String implement_class)
    {

        this.direction_id = direction_id;
        this.coeff = coeff;
        this.implement_class = implement_class;
    }
    public int getDerection_id()
    {
        return direction_id;
    }

    public int getCoeff()
    {
        return coeff;
    }

    public String getImplement_class()
    {
        return implement_class;
    }
}
