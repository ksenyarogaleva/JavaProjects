package com.ks_rogaleva.hospital.registerOffice;

import com.ks_rogaleva.hospital.doctor.Doctor;
import com.ks_rogaleva.hospital.patient.Patient;
import com.ks_rogaleva.hospital.patient.PatientCard;
import com.ks_rogaleva.hospital.treatment.Treatment;

import java.util.List;

public interface IRegister {
    PatientCard createPatientCard(Patient patient, Doctor doctor);
    boolean removePatient(PatientCard patientCard);
}
