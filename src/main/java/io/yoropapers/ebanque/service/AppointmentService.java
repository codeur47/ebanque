package io.yoropapers.ebanque.service;

import io.yoropapers.ebanque.model.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment create(Appointment appointment);
    List<Appointment> findAll();
    Appointment findAppointmentById(Long id);
    void confirmedAppointment(Long id);
    List<Appointment> findAllByUserUsername(String username);
    void delete(Appointment appointment);

}
