package com.ks_rogaleva.hospital.doctor;

import com.ks_rogaleva.hospital.patient.PatientCard;
import com.ks_rogaleva.hospital.treatment.Treatment;

import java.util.List;

public interface IDiagnosisSetter {
    boolean setDiagnosisAndTreatment(PatientCard patientCard, String diagnosis, List<Treatment> treatmentList);
}
