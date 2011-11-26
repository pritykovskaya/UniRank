package ru.unirank.core.model;

/**
 * Created by IntelliJ IDEA.
 * User: natasha
 * Date: 14.11.11
 */
public class SpecialityFaculty
{
    private int speciality_id;
    private int faculty_id;

    public SpecialityFaculty(int speciality_id, int faculty_id)
    {

        this.speciality_id = speciality_id;
        this.faculty_id = faculty_id;
    }
    public int getSpeciality_id()
    {
        return speciality_id;
    }

    public int getFaculty_id()
    {
        return faculty_id;
    }
}
