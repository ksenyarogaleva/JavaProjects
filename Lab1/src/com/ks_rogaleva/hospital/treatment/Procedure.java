package com.ks_rogaleva.hospital.treatment;


import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Procedure  extends Treatment{
    private String cabinet;
    private List<DayOfWeek> procedureDays;

    public Procedure(){}

    public Procedure(String name, Date startDate, Date endDate, double price) {
        super(name, startDate, endDate, price);
    }

    public String getCabinet() {
        return cabinet;
    }


    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    public List<DayOfWeek> getProcedureDays() {
        return procedureDays;
    }

    public void setProcedureDays(List<DayOfWeek> procedureDays) {
        this.procedureDays = procedureDays;
    }

    @Override
    public String toString() {
        return "\tНазвание - "+this.getName()+"\n\tСтоимость - "+this.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Procedure procedure = (Procedure) o;
        return Objects.equals(cabinet, procedure.cabinet) &&
                Objects.equals(procedureDays, procedure.procedureDays);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cabinet, procedureDays);
    }
}
