package com.ks_rogaleva.hospital.registerOffice;

import com.ks_rogaleva.hospital.doctor.Doctor;
import com.ks_rogaleva.hospital.patient.Patient;
import com.ks_rogaleva.hospital.patient.PatientCard;
import com.ks_rogaleva.hospital.treatment.Treatment;

import java.util.List;

public class RegisterOffice implements IRegister{

    @Override
    public PatientCard createPatientCard(Patient patient, Doctor doctor) {
        PatientCard patientCard=new PatientCard();
        patientCard.setPatient(patient);
        patientCard.setDoctor(doctor);
        patientCard.setHealing(true);

        this.setPatientCardToDoctor(patientCard,doctor);
        return patientCard;
    }

    @Override
    public boolean removePatient(PatientCard patientCard) {
        if(patientCard.isHealing()){
            patientCard.setHealing(false);
            return true;
        }
        else{
            return false;
        }

    }

    private void setPatientCardToDoctor(PatientCard patientCard, Doctor doctor){
        List<PatientCard> patientCards=doctor.getPatients();
        patientCards.add(patientCard);
        doctor.setPatients(patientCards);
    }

}
