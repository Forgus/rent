package com.forgus.rent.entity;

import javax.persistence.*;
import java.util.List;

/**
 * 电表
 * Created by cwb on 2016/3/19.
 */
@Entity
@Table(name = "ammeter")
public class Ammeter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "roomId")
    private Room room;

    @OneToMany(mappedBy = "ammeter",cascade = CascadeType.ALL)
    private List<ElecRecord> records;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public List<ElecRecord> getRecords() {
        return records;
    }

    public void setRecords(List<ElecRecord> records) {
        this.records = records;
    }
}
