package org.example.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "size", schema = "testdb")
public class SizeEntity {
    private int id;
    private double startValue;
    private double endValue;
    private double sizeCoefficient;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "StartValue", nullable = false, precision = 0)
    public double getStartValue() {
        return startValue;
    }

    public void setStartValue(double startValue) {
        this.startValue = startValue;
    }

    @Basic
    @Column(name = "EndValue", nullable = false, precision = 0)
    public double getEndValue() {
        return endValue;
    }

    public void setEndValue(double endValue) {
        this.endValue = endValue;
    }

    @Basic
    @Column(name = "SizeCoefficient", nullable = false, precision = 0)
    public double getSizeCoefficient() {
        return sizeCoefficient;
    }

    public void setSizeCoefficient(double sizeCoefficient) {
        this.sizeCoefficient = sizeCoefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SizeEntity that = (SizeEntity) o;
        return id == that.id && Double.compare(that.startValue, startValue) == 0 && Double.compare(that.endValue, endValue) == 0 && Double.compare(that.sizeCoefficient, sizeCoefficient) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startValue, endValue, sizeCoefficient);
    }
}
