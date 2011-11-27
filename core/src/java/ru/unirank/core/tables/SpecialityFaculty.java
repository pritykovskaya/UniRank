package ru.unirank.core.tables;

/**
 * Created by IntelliJ IDEA.
 * User: alex
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
    public SpecialityFaculty()
    {

        this.speciality_id = 0;
        this.faculty_id = 0;
    }
    public int getSpecialityId()
    {
        return speciality_id;
    }

    public int getFacultyId()
    {
        return faculty_id;
    }
}
