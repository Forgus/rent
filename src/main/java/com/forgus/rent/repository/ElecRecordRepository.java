package com.forgus.rent.repository;

import com.forgus.rent.entity.ElecRecord;
import com.forgus.rent.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by cwb on 2016/3/15.
 */
public interface ElecRecordRepository extends JpaRepository<ElecRecord,Long> {

    List<ElecRecord> findByAmmeter_Id(Long ammeterId);

}
