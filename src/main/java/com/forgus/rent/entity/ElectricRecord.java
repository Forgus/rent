package com.forgus.rent.entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * µç±í¼ÇÂ¼
 * Created by cwb on 2016/3/8.
 */
@Entity
@Table(name = "electricRecord")
public class ElectricRecord {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "createDate")
    private LocalDate createDete;

    @Column(name = "record")
    private int record;

    @ManyToOne
    @JoinColumn(name = "roomId")
    private Room room;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreateDete() {
        return createDete;
    }

    public void setCreateDete(LocalDate createDete) {
        this.createDete = createDete;
    }

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
