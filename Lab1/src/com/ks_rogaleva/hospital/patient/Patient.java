package com.ks_rogaleva.hospital.patient;

import com.ks_rogaleva.hospital.Person;

import java.util.Date;
import java.util.Objects;

public class Patient extends Person {

    private String address;

    public Patient(String firstName, String lastName, Date dateOfBirth, char gender, String phoneNumber) {
        super(firstName, lastName, dateOfBirth, gender, phoneNumber);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(address, patient.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public String toString() {
        return this.getFirstName()+" "+this.getLastName();
    }
}
