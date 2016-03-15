package com.forgus.rent.controller;

import com.forgus.rent.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


/**
 * Created by cwb on 2016/3/15.
 */
@Controller
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;


}
