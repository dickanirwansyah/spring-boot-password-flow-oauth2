package com.spring.oauth2.springdemooauth2.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idrole;

    private String name;

    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Privilege> privileges = new HashSet<>(0);

    public Role(){}

    public int getIdrole(){
        return idrole;
    }

    public void setIdrole(int idrole){
        this.idrole=idrole;
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

    public Set<Privilege> getPrivileges(){
        return privileges;
    }

    public void setPrivilages(Set<Privilege> privileges){
        this.privileges = privileges;
    }
}
