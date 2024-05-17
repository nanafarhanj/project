package MiniProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Term {
    private long termID;
    private ArrayList<Course> courses=new ArrayList<>();
    Term(long termID){
        this.termID=termID;
    }
    public void addCourse(Course course){
        if(!courses.contains(course)) courses.add(course);
    }
    public void removeCourse(Course course){
        if(courses.contains(course)) courses.remove(course);
    }

    public long getTermID() {
        return termID;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
}
