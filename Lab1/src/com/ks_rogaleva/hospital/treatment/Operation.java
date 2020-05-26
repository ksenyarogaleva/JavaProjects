package com.ks_rogaleva.hospital.treatment;

import com.ks_rogaleva.hospital.doctor.Doctor;

import java.util.Date;
import java.util.Objects;

public class Operation extends Treatment{
    private String operatingRoom;
    private Doctor operationDoctor;

    public Operation(){}

    public Operation(String name, Date startDate, Date endDate, double price) {
        super(name, startDate, endDate, price);
    }

    public String getOperatingRoom() {
        return operatingRoom;
    }

    public void setOperatingRoom(String operatingRoom) {
        this.operatingRoom = operatingRoom;
    }

    public Doctor getOperationDoctor() {
        return operationDoctor;
    }

    public void setOperationDoctor(Doctor operationDoctor) {
        this.operationDoctor = operationDoctor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Operation operation = (Operation) o;
        return Objects.equals(operatingRoom, operation.operatingRoom) &&
                Objects.equals(operationDoctor, operation.operationDoctor);
    }

    @Override
    public String toString() {
        return "\tНазвание - "+this.getName()+"\n\tСтоимость - "+this.getPrice();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), operatingRoom, operationDoctor);
    }
}
