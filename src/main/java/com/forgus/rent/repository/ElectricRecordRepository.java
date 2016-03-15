package com.forgus.rent.repository;

import com.forgus.rent.entity.ElectricRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cwb on 2016/3/15.
 */
public interface ElectricRecordRepository extends JpaRepository<ElectricRecord,Long> {
    List<ElectricRecord> findByRoom_Id(long roomId);
}
