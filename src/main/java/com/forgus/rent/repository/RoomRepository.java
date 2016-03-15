package com.forgus.rent.repository;

import com.forgus.rent.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by cwb on 2016/3/15.
 */
public interface RoomRepository extends JpaRepository<Room,Long> {
}
