package com.forgus.rent.entity;

import javax.persistence.*;
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

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    private Set<ElectricRecord> records;

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
}
