package ru.unirank.core.utils;
/**
 * Created by IntelliJ IDEA.
 * User: Evgeniy
 * Date: 08.10.11
 * Time: 21:32
 */

/**
 * Класс, представляющий запись в базе.
 */
public class DBResponse {
    private int id;
    private int rating;
    private String name;
    private String city;
//    private int universityId;
//    private String about;
//    private UnivDescription univ;
//    private FacultyDescription fac;
//
//    private UnivDescription desc;

    public DBResponse(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.rating = 1;
    }

//    public void setUniv(UnivDescription univ) {
//        this.univ = univ;
//    }
//
//    public void setFac(FacultyDescription fac) {
//        this.fac = fac;
//    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getCity() {
        return city;
    }

}