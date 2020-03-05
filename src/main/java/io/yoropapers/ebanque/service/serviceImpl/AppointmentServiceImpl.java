package io.yoropapers.ebanque.service.serviceImpl;

import io.yoropapers.ebanque.dao.AppointmentDao;
import io.yoropapers.ebanque.model.Appointment;
import io.yoropapers.ebanque.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private AppointmentDao appointmentDao;

    @Autowired
    public AppointmentServiceImpl(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }

    @Override
    public Appointment create(Appointment appointment) {
        return appointmentDao.save(appointment);
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentDao.findAll();
    }

    @Override
    public Appointment findAppointmentById(Long id) {
        return appointmentDao.getOne(id);
    }

    @Override
    public void confirmedAppointment(Long id) {
        Appointment appointment =  findAppointmentById(id);
        appointment.setConfirmed(true);
        appointmentDao.save(appointment);
    }

    @Override
    public List<Appointment> findAllByUserUsername(String username) {
        return appointmentDao.findAllByUserUsername(username);
    }

    @Override
    public void delete(Appointment appointment) {
        appointmentDao.delete(appointment);
    }
}
