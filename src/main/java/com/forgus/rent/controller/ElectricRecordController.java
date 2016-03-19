package com.forgus.rent.controller;

import com.forgus.rent.entity.Ammeter;
import com.forgus.rent.entity.ElecRecord;
import com.forgus.rent.repository.AmmeterRepository;
import com.forgus.rent.repository.ElecRecordRepository;
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
    private ElecRecordRepository recordRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private AmmeterRepository ammeterRepository;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String findRecords(@RequestParam(required = false)Long ammeterId,Model model) {
        List<ElecRecord> records = ammeterId==null ? recordRepository.findAll() : recordRepository.findByAmmeter_Id(ammeterId);
        model.addAttribute("records",records);
        return "recordList.html";
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public String createNewRecord(Integer readings,
                                  Long ammeterId) {
        Ammeter ammeter = ammeterRepository.findOne(ammeterId);
        ElecRecord record = new ElecRecord();
        record.setCreateDate(LocalDate.now());
        record.setReadings(readings);
        record.setAmmeter(ammeter);
        recordRepository.save(record);
        return "redirect:/records?ammeterId="+ammeterId;
    }
}
