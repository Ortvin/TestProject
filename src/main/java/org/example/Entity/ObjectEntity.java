package org.example.Entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "object", schema = "testdb")
public class ObjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ObjectID", nullable = false)
    private int objectId;
    @Basic
    @Column(name = "ObjectIndex", nullable = true, length = 20)
    private String objectIndex;
    @Basic
    @Column(name = "Region", nullable = true, length = 45)
    private String region;
    @Basic
    @Column(name = "District", nullable = true, length = 45)
    private String district;
    @Basic
    @Column(name = "Town", nullable = true, length = 45)
    private String town;
    @Basic
    @Column(name = "Street", nullable = true, length = 45)
    private String street;
    @Basic
    @Column(name = "House", nullable = true, length = 8)
    private String house;
    @Basic
    @Column(name = "Building1", nullable = true, length = 5)
    private String building1;
    @Basic
    @Column(name = "Flat", nullable = true, length = 45)
    private String flat;
    @Basic
    @Column(name = "ObjectSize", nullable = false, precision = 2)
    private BigDecimal objectSize;
    @Basic
    @Column(name = "ConstructionYear", nullable = false)
    private int constructionYear;
    @Basic
    @Column(name = "Building2", nullable = true, length = 5)
    private String building2;

    @OneToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "ContryID")
    private CountryEntity countryEntity;

    @OneToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "ObjectTypeID")
    private ObjecttypeEntity objecttypeEntity;

    @OneToOne (mappedBy = "objectEntity")
    public ContractEntity contractEntity;

    public ObjecttypeEntity getObjecttypeEntity() {
        return objecttypeEntity;
    }

    public void setObjecttypeEntity(ObjecttypeEntity objecttypeEntity) {
        this.objecttypeEntity = objecttypeEntity;
    }

    public CountryEntity getCountryEntity() {
        return countryEntity;
    }

    public void setCountryEntity(CountryEntity countryEntity) {
        this.countryEntity = countryEntity;
    }

    public int getObjectId() {
        return objectId;
    }
    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getObjectIndex() {
        return objectIndex;
    }
    public void setObjectIndex(String objectIndex) {
        this.objectIndex = objectIndex;
    }

    public String getRegion() {
        return region;
    }
    public void setRegion(String region) {
        this.region = region;
    }


    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTown() {
        return town;
    }
    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }


    public String getHouse() {
        return house;
    }
    public void setHouse(String house) {
        this.house = house;
    }

    public String getBuilding1() {
        return building1;
    }
    public void setBuilding1(String building1) {
        this.building1 = building1;
    }

    public String getFlat() {
        return flat;
    }
    public void setFlat(String flat) {
        this.flat = flat;
    }

    public BigDecimal getObjectSize() {
        return objectSize;
    }
    public void setObjectSize(BigDecimal objectSize) {
        this.objectSize = objectSize;
    }

    public int getConstructionYear() {
        return constructionYear;
    }
    public void setConstructionYear(int constructionYear) {
        this.constructionYear = constructionYear;
    }


    public String getBuilding2() {
        return building2;
    }
    public void setBuilding2(String building2) {
        this.building2 = building2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectEntity that = (ObjectEntity) o;
        return objectId == that.objectId && constructionYear == that.constructionYear && Objects.equals(objectIndex, that.objectIndex) && Objects.equals(region, that.region) && Objects.equals(district, that.district) && Objects.equals(town, that.town) && Objects.equals(street, that.street) && Objects.equals(house, that.house) && Objects.equals(building1, that.building1) && Objects.equals(flat, that.flat) && Objects.equals(objectSize, that.objectSize) && Objects.equals(building2, that.building2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectId, objectIndex, region, district, town, street, house, building1, flat, objectSize, constructionYear, building2);
    }
}
