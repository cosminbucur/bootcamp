package com.sda.hibernate.associations.one_to_many_uni;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Mother")
@Table(name = "mother")
public class Mother {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Daughter> daughters = new ArrayList<>();

    public Mother() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Daughter> getDaughters() {
        return daughters;
    }

    public void setDaughters(List<Daughter> daughters) {
        this.daughters = daughters;
    }
}
