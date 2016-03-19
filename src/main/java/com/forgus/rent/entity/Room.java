package com.forgus.rent.entity;

import com.forgus.rent.common.PayInterval;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 房间
 * Created by cwb on 2016/3/8.
 */
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "ammeterId")
    private Ammeter ammeter;

    @OneToMany(mappedBy = "room",cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE})
    private Set<Tenant> tenants;

    @Column(name = "price")
    private int price;

    @Column(name = "deposit")
    private int deposit;

    @Column(name = "shareComMeter")
    private boolean shareComMeter;

    @Column(name = "payInterval")
    private PayInterval payInterval;

    @Column(name = "elecPrice")
    private double elecPrice;

    public double getElecPrice() {
        return elecPrice;
    }

    public void setElecPrice(double elecPrice) {
        this.elecPrice = elecPrice;
    }

    public boolean isShareComMeter() {
        return shareComMeter;
    }

    public PayInterval getPayInterval() {
        return payInterval;
    }

    public void setPayInterval(PayInterval payInterval) {
        this.payInterval = payInterval;
    }

    public void setShareComMeter(boolean shareComMeter) {
        this.shareComMeter = shareComMeter;
    }

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


    public void addTenant(Tenant tenant) {
        if(tenants==null || tenants.size()==0) {
            tenants = new HashSet<>();
        }
        tenant.setRoom(this);
        tenants.add(tenant);
    }

    public Set<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(Set<Tenant> tenants) {
        this.tenants = tenants;
    }

    public Ammeter getAmmeter() {
        return ammeter;
    }

    public void setAmmeter(Ammeter ammeter) {
        this.ammeter = ammeter;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }
}
