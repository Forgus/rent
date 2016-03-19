package com.forgus.rent.controller;

import com.forgus.rent.entity.ElecRecord;
import com.forgus.rent.entity.Room;
import com.forgus.rent.entity.Tenant;
import com.forgus.rent.repository.AmmeterRepository;
import com.forgus.rent.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


/**
 * Created by cwb on 2016/3/15.
 */
@Controller
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private AmmeterRepository ammeterRepository;

    @RequestMapping("/computeRent")
    public String computeRent(Integer perReadings,Long roomId,Model model) {
        Room room = roomRepository.findOne(roomId);
        int payInterval = room.getPayInterval().getValue();
        int price = room.getPrice()*payInterval;
        int water = 10*room.getTenants().size()*payInterval;
        double electricity = calculateElec(room, perReadings);
        model.addAttribute("price",price);
        model.addAttribute("water",water);
        model.addAttribute("electricity",electricity);
        return "settlement";
    }

    @RequestMapping("/computeComplexRent")
    public String computeComplexRent(Integer perReadings,Integer comReadings,Long roomId,Model model) {
        Room room = roomRepository.findOne(roomId);
        int payInterval = room.getPayInterval().getValue();
        int price = room.getPrice()*payInterval;
        int water = 10*room.getTenants().size()*payInterval;
        double electricity = calculateElec(room,perReadings,comReadings);
        model.addAttribute("price",price);
        model.addAttribute("water",water);
        model.addAttribute("electricity",electricity);
        return "settlement";
    }

    private double calculateElec(Room room, Integer perReadings) {
         return calculatePersonalElecPrice(room,perReadings);
    }

    private double calculateElec(Room room,Integer perReadings,Integer comReadings) {
        return calculatePersonalElecPrice(room,perReadings) + calculateCommonConsumeElecPrice(room,comReadings);
    }

    private double calculateCommonConsumeElecPrice(Room room, Integer comReadings) {
        int lastValidComReadings = findLastValidComReadings(room);
        return room.getElecPrice() * (comReadings - lastValidComReadings)/4;
    }

    private double calculatePersonalElecPrice(Room room, Integer readings) {
        int lastValidReadings = findLastValidPerReadings(room);
        return room.getElecPrice() * (readings - lastValidReadings);
    }

    private int findLastValidComReadings(Room room) {
        Tenant payer = findPayer(room.getTenants());
        int checkInDay = payer.getCheckInDate().getDayOfMonth();
        LocalDate lastPayDate = LocalDate.now().minusMonths(1).withDayOfMonth(checkInDay);
        List<ElecRecord> recordList = ammeterRepository.findByRoom(null).getRecords();
        for(ElecRecord record : recordList) {
            if(record.getCreateDate().getDayOfMonth()-lastPayDate.getDayOfMonth()<30) {
                return record.getReadings();
            }
        }
        return 0;
    }

    private int findLastValidPerReadings(Room room) {
        Tenant payer = findPayer(room.getTenants());
        int checkInDay = payer.getCheckInDate().getDayOfMonth();
        LocalDate lastPayDate = LocalDate.now().minusMonths(1).withDayOfMonth(checkInDay);
        List<ElecRecord> recordList = room.getAmmeter().getRecords();
        for(ElecRecord record : recordList) {
            if(record.getCreateDate().getDayOfMonth()-lastPayDate.getDayOfMonth()<30) {
                return record.getReadings();
            }
        }
        return 0;
    }

    private Tenant findPayer(Set<Tenant> tenants) {
        for(Tenant tenant : tenants) {
            if(tenant.isPayer()) {
                return tenant;
            }
        }
        return null;
    }
}
