package com.azoker.service;

import com.azoker.pojo.dto.MedicalRegistrationDto;


/**
 * Created by zxd on 2023/4/22
 */
public interface RegistrationService {


    /**
     * 预约挂号 TODO
     * @param medicalRegistrationDto
     * @return
     */
     String registerMedicalAppointment(MedicalRegistrationDto medicalRegistrationDto);


}
