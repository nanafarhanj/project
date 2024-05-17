package MiniProject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Assignment {
    //Properties
    private String name;
    private LocalDate deadline;
    private boolean isActive;
    private TypeAssignment typeAssignment;
    public Assignment(String name, String deadline, TypeAssignment typeAssignment) {
        this.name = name;
        this.deadline =LocalDate.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.typeAssignment= typeAssignment;
        this.isActive = true;
    }
    //Setters, getters and other methods

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public LocalDate getDeadline() {
        return deadline;
    }

    public TypeAssignment getTypeAssignment() {
        return typeAssignment;
    }

    public void setTypeAssignment(TypeAssignment typeAssignment) {
        this.typeAssignment = typeAssignment;
    }

    public void setDeadline(String deadline) {
        this.deadline = LocalDate.parse(deadline, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public Boolean getIsActive(){
        int a = (deadline.getYear() - LocalDate.now().getYear());
        if(a<0)return false;
        if(a>0)return true;
        a=(deadline.getMonthValue()-LocalDate.now().getMonthValue());
        if(a<0)return false;
        if(a>0)return true;
        a=deadline.getDayOfMonth()-LocalDate.now().getDayOfMonth();
        if(a<=0)return false;
        return true;
    }
    public void howMuchTimeIsLeft(){
        int days=0,months=0,years=0;
        if(getIsActive()){
            years=deadline.getYear()-LocalDate.now().getYear();
            months=deadline.getMonthValue()-LocalDate.now().getMonthValue();
            if(months<0){
                years--;
                months+=12;
            }
            days=deadline.getDayOfMonth()-LocalDate.now().getDayOfMonth();
            if (days<0){
                months--;
                days+=30;
            }
            System.out.println("You have "+years+" years, "+months+" months and "+days+" days.");
        }
        else System.out.println("Your time is up :')");
    }

}


