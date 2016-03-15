package com.forgus.rent.controller;

import com.forgus.rent.entity.ElectricRecord;
import com.forgus.rent.entity.Room;
import com.forgus.rent.repository.ElectricRecordRepository;
import com.forgus.rent.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;


/**
 * Created by cwb on 2016/3/15.
 */
@Controller
@RequestMapping("/records")
public class ElectricRecordController {

    @Autowired
    private ElectricRecordRepository recordRepository;
    @Autowired
    private RoomRepository roomRepository;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String findRecords(@RequestParam(required = false)Long roomId,Model model) {
        List<ElectricRecord> records = roomId==null ? recordRepository.findAll() : recordRepository.findByRoom_Id(roomId);
        model.addAttribute("records",records);
        return "recordList.html";
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public String createNewRecord(Integer readings,
                                  Long roomId) {
        Room room = roomRepository.findOne(roomId);
        ElectricRecord record = new ElectricRecord();
        record.setCreateDete(LocalDate.now());
        record.setReadings(readings);
        record.setRoom(room);
        recordRepository.save(record);
        return "redirect:/records?roomId="+roomId;
    }
}
