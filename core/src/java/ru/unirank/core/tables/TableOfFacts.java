package ru.unirank.core.tables;

/**
 * Created by IntelliJ IDEA.
 * User: Alex Zaleskiy
 * Date: 14.11.2011
 * Time: 14:42:29
 */
public class TableOfFacts {

    private int id;
    private int direction_id;
    private int speciality_id;
    private int faculty_id;
    private int university_id;
    private String city;
    private int method_id;
    private int rank_id;
    private int format_id;
    private int info_id;

    public TableOfFacts(int id, int direction_id, int speciality_id, int faculty_id, int university_id, String city, int method_id, int rank_id, int format_id, int info_id)
    {
        this.id = id;
        this.direction_id = direction_id;
        this.speciality_id = speciality_id;
        this.faculty_id = faculty_id;
        this.university_id = university_id;
        this.city = city;
        this.method_id = method_id;
        this.rank_id = rank_id;
        this.format_id = format_id;
        this.info_id = info_id;
    }
    public TableOfFacts(String city)
    {
        this.id = 0;
        this.direction_id = 0;
        this.speciality_id = 0;
        this.faculty_id = 0;
        this.university_id = 0;
        this.city = city;
        this.method_id = 0;
        this.rank_id = 0;
        this.format_id = 0;
        this.info_id = 0;
    }
    public int getId(){
        return id;
    }

    public int getDirectionId(){
        return  direction_id;
    }

    public int getSpecialityId(){
        return speciality_id;
    }

    public int getFacultyId(){
        return faculty_id;
    }

    public int getUniversityId(){
        return university_id;
    }

    public String getCity(){
        return city;
    }

    public int getMethodId(){
        return method_id;
    }

    public int getRankId(){
        return rank_id;
    }

    public int getFormatId(){
        return format_id;
    }

    public int getInfoId(){
        return info_id;
    }


    public void setId(int id){
         this.id = id;
    }
    
    public void setDirectionId(int direction_id){
        this.direction_id =  direction_id;
    }

    public void setSpecialityId(int speciality_id){
         this.speciality_id = speciality_id;
    }

    public void setFacultyId(int faculty_id){
         this.faculty_id = faculty_id;
    }

    public void setUniversityId(int university_id){
         this.university_id = university_id;
    }

    public void setCity(String city){
         this.city = city;
    }

    public void setMethodId(int method_id){
         this.method_id = method_id;
    }

    public void setRankId(int rank_id){
         this.rank_id = rank_id;
    }

    public void setFormatId(int format_id){
         this.format_id = format_id;
    }

    public void setInfoId(int info_id){
         this.info_id = info_id;
    }
}
