package com.forgus.rent.repository;

import com.forgus.rent.entity.Ammeter;
import com.forgus.rent.entity.ElecRecord;
import com.forgus.rent.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by cwb on 2016/3/19.
 */
public interface AmmeterRepository extends JpaRepository<Ammeter,Long> {
    Ammeter findByRoom(Room room);
}
