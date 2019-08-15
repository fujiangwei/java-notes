package com.notes.java.javassist;

public class Emp {
  
    private String ename;  
    private int eno;  
      
    public Emp(){  
        ename = "yy";
        eno = 001;
    }  
  
    public String getEname() {  
        return ename;  
    }  
  
    public void setEname(String ename) {  
        this.ename = ename;  
    }  
  
    public int getEno() {  
        return eno;  
    }  
  
    public void setEno(int eno) {  
        this.eno = eno;  
    }  
      
    public void printInfo(String ename, int eno){
        System.out.println("printInfo");
    }
} 