package com.forgus.rent.entity;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * 电表记录
 * Created by cwb on 2016/3/8.
 */
@Entity
@Table(name = "elecRecord")
public class ElecRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "createDate")
    private LocalDate createDate;

    @Column(name = "readings")
    private int readings;

    @ManyToOne
    @JoinColumn(name = "ammeterId")
    private Ammeter ammeter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public int getReadings() {
        return readings;
    }

    public void setReadings(int readings) {
        this.readings = readings;
    }

    public Ammeter getAmmeter() {
        return ammeter;
    }

    public void setAmmeter(Ammeter ammeter) {
        this.ammeter = ammeter;
    }
}
