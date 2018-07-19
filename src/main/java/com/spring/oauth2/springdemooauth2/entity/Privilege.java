package com.spring.oauth2.springdemooauth2.entity;

import javax.persistence.*;

@Entity
@Table(name = "privilege")
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idprivilege;

    private String name;

    private String description;

    public Privilege(){}

    public int getIdprivilege(){
        return idprivilege;
    }

    public void setIdprivilege(int idprivilege){
        this.idprivilege = idprivilege;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
