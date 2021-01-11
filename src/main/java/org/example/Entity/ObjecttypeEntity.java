package org.example.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "objecttype", schema = "testdb")
public class ObjecttypeEntity {
    @Id
    @Column(name = "ObjectTypeID", nullable = false)
    private int objectTypeId;
    @Basic
    @Column(name = "ObjecTypeName", nullable = false, length = 45)
    private String objecTypeName;
    @Basic
    @Column(name = "ObjecTypeRate", nullable = false, precision = 0)
    private double objecTypeRate;


    public int getObjectTypeId() {
        return objectTypeId;
    }

    public void setObjectTypeId(int objectTypeId) {
        this.objectTypeId = objectTypeId;
    }

    public String getObjecTypeName() {
        return objecTypeName;
    }

    public void setObjecTypeName(String objecTypeName) {
        this.objecTypeName = objecTypeName;
    }


    public double getObjecTypeRate() {
        return objecTypeRate;
    }

    public void setObjecTypeRate(double objecTypeRate) {
        this.objecTypeRate = objecTypeRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjecttypeEntity that = (ObjecttypeEntity) o;
        return objectTypeId == that.objectTypeId && Double.compare(that.objecTypeRate, objecTypeRate) == 0 && Objects.equals(objecTypeName, that.objecTypeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(objectTypeId, objecTypeName, objecTypeRate);
    }
}
