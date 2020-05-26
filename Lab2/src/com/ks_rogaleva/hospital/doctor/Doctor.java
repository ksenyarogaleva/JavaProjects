package com.ks_rogaleva.hospital.doctor;

import com.ks_rogaleva.hospital.Person;
import com.ks_rogaleva.hospital.patient.PatientCard;
import com.ks_rogaleva.hospital.treatment.Treatment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Doctor extends Person implements IDiagnosisSetter, Serializable {

    private List<PatientCard> patients=new ArrayList<>();

    public Doctor(String firstName, String lastName, Date dateOfBirth, char gender, String phoneNumber) {
        super(firstName, lastName, dateOfBirth, gender, phoneNumber);
    }

    public List<PatientCard> getPatients() {
        return patients;
    }

    public void setPatients(List<PatientCard> patients) {
        this.patients = patients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(patients, doctor.patients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), patients);
    }

    @Override
    public String toString() {
        return new String(this.getFirstName()+" " +this.getLastName());
    }

    @Override
    public boolean setDiagnosisAndTreatment(PatientCard patientCard, String diagnosis,List<Treatment> treatmentList) {
        if(this.patients.contains(patientCard)){
            int index=this.patients.indexOf(patientCard);
            PatientCard card=this.patients.get(index);
            card.setDiagnosis(diagnosis);
            card.setTreatment(treatmentList);
            return true;
        }
        else{
            return false;
        }
    }
}
