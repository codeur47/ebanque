package io.yoropapers.ebanque.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.yoropapers.ebanque.model.Appointment;
import org.springframework.stereotype.Repository;

/**
 * AppointmentDao
 */
@Repository
public interface AppointmentDao extends JpaRepository<Appointment, Long> {
    List<Appointment> findAll();
    List<Appointment> findAllByUserUsername(String username);
}
