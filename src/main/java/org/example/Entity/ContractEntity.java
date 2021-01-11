package org.example.Entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "contract", schema = "testdb")
public class ContractEntity {
    @Id
    @Column(name = "ContractID", nullable = false)
    private int contractId;
    @Basic
    @Column(name = "ContractDate", nullable = false)
    private Date contractDate;
    @Basic
    @Column(name = "ContractBeginDate", nullable = false)
    private Date contractBeginDate;
    @Basic
    @Column(name = "ContractEndDate", nullable = false)
    private Date contractEndDate;
    @Basic
    @Column(name = "ContractSum", nullable = true, precision = 2)
    private BigDecimal contractSum;
    @Basic
    @Column(name = "InsuranceFee", nullable = true, precision = 2)
    private BigDecimal insuranceFee;
    @Basic
    @Column(name = "CalculationFeeDate", nullable = true)
    private Date calculationFeeDate;
    @Basic
    @Column(name = "Comment", nullable = true, length = 1000)
    private String comment;
 /*   @Basic
    @Column(name = "FIO", nullable = true, length = 255)
    private String fio;*/
    @Basic
    @Column(name = "ContractSeriaNumber", nullable = false, length = 45)
    private String contractSeriaNumber;

    @ManyToOne (fetch = FetchType.EAGER)
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "ClientID")
    private ClientEntity clientEntity;

    @OneToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@Cascade(value={org.hibernate.annotations.CascadeType.ALL})
 //   @PrimaryKeyJoinColumn
    @JoinColumn(name = "ObjectID")
    private ObjectEntity objectEntity;

    public ObjectEntity getObjectEntity() {
        return objectEntity;
    }

    public void setObjectEntity(ObjectEntity objectEntity) {
        this.objectEntity = objectEntity;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    public String getFIO(){
        return clientEntity.getFIO();
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }


    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }


    public Date getContractBeginDate() {
        return contractBeginDate;
    }

    public void setContractBeginDate(Date contractBeginDate) {
        this.contractBeginDate = contractBeginDate;
    }


    public Date getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }


    public BigDecimal getContractSum() {
        return contractSum;
    }

    public void setContractSum(BigDecimal contractSum) {
        this.contractSum = contractSum;
    }


    public BigDecimal getInsuranceFee() {
        return insuranceFee;
    }

    public void setInsuranceFee(BigDecimal insuranceFee) {
        this.insuranceFee = insuranceFee;
    }


    public Date getCalculationFeeDate() {
        return calculationFeeDate;
    }

    public void setCalculationFeeDate(Date calculationFeeDate) {
        this.calculationFeeDate = calculationFeeDate;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


 /*  public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }*/


    public String getContractSeriaNumber() {
        return contractSeriaNumber;
    }

    public void setContractSeriaNumber(String contractSeriaNumber) {
        this.contractSeriaNumber = contractSeriaNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractEntity that = (ContractEntity) o;
        return contractId == that.contractId && Objects.equals(contractDate, that.contractDate) && Objects.equals(contractBeginDate, that.contractBeginDate) && Objects.equals(contractEndDate, that.contractEndDate) && Objects.equals(contractSum, that.contractSum) && Objects.equals(insuranceFee, that.insuranceFee) && Objects.equals(calculationFeeDate, that.calculationFeeDate) && Objects.equals(comment, that.comment) && Objects.equals(contractSeriaNumber, that.contractSeriaNumber) && Objects.equals(clientEntity, that.clientEntity );
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractId, contractDate, contractBeginDate, contractEndDate, contractSum, insuranceFee, calculationFeeDate, comment, contractSeriaNumber, clientEntity);
    }
}
