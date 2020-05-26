package com.ks_rogaleva.hospital.treatment;

import java.util.Date;
import java.util.Objects;

public class Medicine extends Treatment{
    private double dose;
    private int amountPerDay;

    public Medicine(){}

    public Medicine(String name, Date startDate, Date endDate, double price) {
        super(name, startDate, endDate, price);
    }

    public double getDose() {
        return dose;
    }

    public void setDose(double dose) {
        this.dose = dose;
    }

    public int getAmountPerDay() {
        return amountPerDay;
    }

    public void setAmountPerDay(int amountPerDay) {
        this.amountPerDay = amountPerDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Medicine medicine = (Medicine) o;
        return Double.compare(medicine.dose, dose) == 0 &&
                amountPerDay == medicine.amountPerDay;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dose, amountPerDay);
    }

    @Override
    public String toString() {
        return new String("\tНазвание - "+this.getName()+"\n\tСтоимость - "+this.getPrice());
    }
}
