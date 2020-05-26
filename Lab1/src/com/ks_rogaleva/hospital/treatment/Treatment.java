package com.ks_rogaleva.hospital.treatment;

import java.util.Date;
import java.util.Objects;

public abstract class Treatment {
    private String name;
    private Date startDate;
    private Date endDate;
    private double price;

    public Treatment(){}

    public Treatment(String name, Date startDate, Date endDate, double price) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Treatment treatment = (Treatment) o;
        return Double.compare(treatment.price, price) == 0 &&
                Objects.equals(name, treatment.name) &&
                Objects.equals(startDate, treatment.startDate) &&
                Objects.equals(endDate, treatment.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startDate, endDate, price);
    }
}
