package com.ks_rogaleva.hospital.patient;

import com.ks_rogaleva.hospital.doctor.Doctor;
import com.ks_rogaleva.hospital.treatment.Treatment;

import java.io.Serializable;
import java.util.List;

public class PatientCard implements Serializable {
    private Patient patient;
    private Doctor doctor;
    private List<Treatment> treatment;

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    private String diagnosis;
    private boolean isHealing;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public List<Treatment> getTreatment() {
        return treatment;
    }

    public void setTreatment(List<Treatment> treatment) {
        this.treatment = treatment;
    }

    public boolean isHealing() {
        return isHealing;
    }

    public void setHealing(boolean healing) {
        isHealing = healing;
    }


}
