package org.example.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "country", schema = "testdb")
public class CountryEntity {
    private int contryId;
    private String countryName;

    @Id
    @Column(name = "ContryID", nullable = false)
    public int getContryId() {
        return contryId;
    }

    public void setContryId(int contryId) {
        this.contryId = contryId;
    }

    @Basic
    @Column(name = "CountryName", nullable = false, length = 60)
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryEntity that = (CountryEntity) o;
        return contryId == that.contryId && Objects.equals(countryName, that.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contryId, countryName);
    }
}
