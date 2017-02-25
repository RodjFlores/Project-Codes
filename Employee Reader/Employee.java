package com.example.bigrodpussymaster.lab2;


public class Employee {
    private String name;
    private String id;
    private double hourlyWage;
    private int weeklyHours;
    private String office;
    private double yearsOfService;

    public Employee(String name, String id, double  hourlyWage,int weeklyHours, String office,
                    double yearsOfService){
        this.name = name;
        this.id = id;
        this.hourlyWage = hourlyWage;
        this.weeklyHours = weeklyHours;
        this.office=office;
        this.yearsOfService=yearsOfService;
    }
    public String getName(){
        return name;
    }
    public String getId(){
        return id;
    }
    public double getHourlyWage(){
        return hourlyWage;
    }
    public int getWeeklyHours(){
        return weeklyHours;
    }
    public String getOffice(){
        return office;
    }
    public double getYearsOfService(){
        return yearsOfService;
    }
    public String getAll(){
        return (name+"\n"+id+"\n"+Double.toString(hourlyWage)+"\n"+
                Integer.toString(getWeeklyHours())+"\n"+office+"\n"+Double.toString(yearsOfService)
                +"\n");
    }
}




