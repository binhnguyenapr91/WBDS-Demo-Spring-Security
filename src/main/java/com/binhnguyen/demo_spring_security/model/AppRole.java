package com.binhnguyen.demo_spring_security.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<AppUser> users;

    public AppRole(String name) {
        this.name = name;
    }

    public AppRole() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<AppUser> getUsers() {
        return users;
    }

    public void setUsers(Set<AppUser> users) {
        this.users = users;
    }
}
