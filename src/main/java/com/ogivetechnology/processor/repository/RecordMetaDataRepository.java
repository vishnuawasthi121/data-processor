package com.ogivetechnology.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ogivetechnology.processor.entities.RecordMetaData;

public interface RecordMetaDataRepository extends JpaRepository<RecordMetaData, Long> {

	@Query(value = "select counter_position from record_meta_data where created_date = (select  max(created_date) from record_meta_data where counter_position  IS NOT NULL)", nativeQuery = true)
	Integer getCounterPosition();
}
