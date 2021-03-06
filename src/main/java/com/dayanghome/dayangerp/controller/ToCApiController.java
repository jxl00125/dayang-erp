package com.dayanghome.dayangerp.controller;

import com.dayanghome.dayangerp.form.AppointmentForm;
import com.dayanghome.dayangerp.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/api/toc/v1")
public class ToCApiController {
    private static final Logger Log = LoggerFactory.getLogger(ToCApiController.class);

    @Autowired
    AppointmentService appointmentService;

    @RequestMapping(value = "/appointment/add", method = RequestMethod.POST) @ResponseBody
    public Integer addAppointment(@RequestBody AppointmentForm appointmentForm) {
        try{
            appointmentForm.validate();
            return appointmentService.addAppointment(appointmentForm);
        }catch (Exception e){
            Log.error("failed to add appointment", e);
            return -1;
        }
    }
}
