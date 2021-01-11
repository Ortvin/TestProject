package org.example.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "constructionyear", schema = "testdb")
public class ConstructionyearEntity {
    private int id;
    private int beginPeriod;
    private int endPeriod;
    private double constructionYearCoefficient;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "BeginPeriod", nullable = false)
    public int getBeginPeriod() {
        return beginPeriod;
    }

    public void setBeginPeriod(int beginPeriod) {
        this.beginPeriod = beginPeriod;
    }

    @Basic
    @Column(name = "EndPeriod", nullable = false)
    public int getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(int endPeriod) {
        this.endPeriod = endPeriod;
    }

    @Basic
    @Column(name = "ConstructionYearCoefficient", nullable = false, precision = 2)
    public double getConstructionYearCoefficient() {
        return constructionYearCoefficient;
    }

    public void setConstructionYearCoefficient(double constructionYearCoefficient) {
        this.constructionYearCoefficient = constructionYearCoefficient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConstructionyearEntity that = (ConstructionyearEntity) o;
        return id == that.id && beginPeriod == that.beginPeriod && endPeriod == that.endPeriod && Objects.equals(constructionYearCoefficient, that.constructionYearCoefficient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, beginPeriod, endPeriod, constructionYearCoefficient);
    }
}
