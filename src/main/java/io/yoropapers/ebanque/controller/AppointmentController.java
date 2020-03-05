package io.yoropapers.ebanque.controller;

import io.yoropapers.ebanque.model.Appointment;
import io.yoropapers.ebanque.model.User;
import io.yoropapers.ebanque.service.AppointmentService;
import io.yoropapers.ebanque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class AppointmentController {

    private AppointmentService appointmentService;
    private UserService userService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService, UserService userService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
    }

    @GetMapping(value = "/appointmentlist")
    public String createAppointment(Model model,Principal principal) {
        Appointment appointment = new Appointment();
        commonElement(model,principal);
        model.addAttribute("appointment", appointment);
        model.addAttribute("dateString", "");
        model.addAttribute("appointmentlist",appointmentService.findAllByUserUsername(userService.findUserByUsername(principal.getName()).getUsername())
                                                            .stream()
                                                            .sorted((ap1, ap2)->ap2.getDateCreation().compareTo(ap1.getDateCreation()))
                                                            .collect(Collectors.toList()));
        return "appointmentlist";
    }

    @GetMapping(value = "/appointmentlistAjax")
    public String forAjax(Model model,Principal principal) {
        model.addAttribute("appointmentlist",appointmentService.findAll()
                .stream()
                .sorted((ap1, ap2)->ap2.getDateCreation().compareTo(ap1.getDateCreation()))
                .collect(Collectors.toList()));
        return "results :: resultsList";
    }


    @PostMapping(value = "/createappointment")
    public String createAppointmentPost(@ModelAttribute("appointment") Appointment appointment,
                                        @ModelAttribute("dateString") String date,
                                        @ModelAttribute("timeString") String time,
                                        Model model,
                                        Principal principal) throws ParseException, InterruptedException {

        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        appointment.setDate(d1);
        appointment.setTimeAppointement(time);
        appointment.setStatus("pending");
        appointment.setDateCreation(new Date());
        appointment.setUser(userService.findUserByUsername(principal.getName()));

        appointmentService.create(appointment);
        Thread.sleep(1000);
        return "redirect:/appointmentlist";
    }

    @GetMapping("/deleteAppointment")
    public String deleteAppointment(Long appointmentId) {
        Appointment appointment = appointmentService.findAppointmentById(appointmentId);
        appointmentService.delete(appointment);
        return "redirect:/appointmentlist";
    }

    public void commonElement(Model model, Principal principal){
        User user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("primaryAccount", user.getPrimaryAccount());
        model.addAttribute("savingsAccount", user.getSavingsAccount());
    }
}
