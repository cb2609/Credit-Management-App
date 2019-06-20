package com.example.creditmanagement;

public class DummyDatabase {
    String name ,  email ;
    int id, current_credits;
    int t_id,credits;

    public DummyDatabase(String name, String email , int id, int current_credits,int t_id,int credits){
        this.current_credits = current_credits;
        this.email = email;
        this.id = id;
        this.name = name;
        this.t_id = t_id;
        this.credits = credits;
    }

    public DummyDatabase()
    {
    }

    public String getName()
    {
        return name;
    }
    public String getEmail()
    {
        return email;
    }
    public int getId()
    {
        return id;
    }
    public int getCurrent_credits()
    {
        return current_credits;
    }
    public int getT_id()
    {
        return t_id;
    }
    public int getCredits()
    {
        return credits;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public void setCurrent_credits(int current_credits)
    {
        this.current_credits = current_credits;
    }
    public void setT_id(int t_id)
    {
        this.t_id = t_id;
    }
    public void setCredits(int credits)
    {
        this.credits = credits;
    }
}
