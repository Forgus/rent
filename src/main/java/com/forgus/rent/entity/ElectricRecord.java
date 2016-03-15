package com.forgus.rent.entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * ����¼
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

    @Column(name = "readings")
    private int readings;

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

    public int getReadings() {
        return readings;
    }

    public void setReadings(int readings) {
        this.readings = readings;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
