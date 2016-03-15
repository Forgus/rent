package com.forgus.rent.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * ·¿¼ä
 * Created by cwb on 2016/3/8.
 */
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    private Set<ElectricRecord> records;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ElectricRecord> getRecords() {
        return records;
    }

    public void setRecords(Set<ElectricRecord> records) {
        this.records = records;
    }

    public void addRecord(ElectricRecord record) {
        if(records==null || records.size()==0) {
            records = new HashSet<>();
        }
        record.setRoom(this);
        records.add(record);
    }
}
