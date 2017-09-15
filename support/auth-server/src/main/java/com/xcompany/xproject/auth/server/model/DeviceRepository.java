package com.xcompany.xproject.auth.server.model;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface DeviceRepository extends CrudRepository<Device, Integer> {

	Device findByImei(String login);

	@Modifying
	@Query("delete from Device where id = ?1")
	void delete(Integer deviceId);
}