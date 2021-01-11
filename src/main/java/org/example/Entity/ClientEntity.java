package org.example.Entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "client", schema = "testdb")
public class ClientEntity {
    private String lastname;
    private String firstname;
    private String patronymic;
    private Date birthdate;
    private String documentSeria;
    private String documentNumber;
    private int clientId;
    private String FIO;
    private String document;


    public String getFIO() {
        return getLastname() + " " + getFirstname() + " " + getPatronymic();
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getDocument() {
        return getDocumentSeria() + " " + getDocumentNumber() ;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    @Basic
    @Column(name = "Lastname", nullable = false, length = 50)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    @Basic
    @Column(name = "Firstname", nullable = false, length = 50)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "Patronymic", nullable = true, length = 50)
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Basic
    @Column(name = "Birthdate", nullable = true)
    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Basic
    @Column(name = "DocumentSeria", nullable = false, length = 5)
    public String getDocumentSeria() {
        return documentSeria;
    }

    public void setDocumentSeria(String documentSeria) {
        this.documentSeria = documentSeria;
    }

    @Basic
    @Column(name = "DocumentNumber", nullable = false, length = 20)
    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Id
    @Column(name = "ClientID", nullable = false)
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientEntity that = (ClientEntity) o;
        return clientId == that.clientId && Objects.equals(lastname, that.lastname) && Objects.equals(firstname, that.firstname) && Objects.equals(patronymic, that.patronymic) && Objects.equals(birthdate, that.birthdate) && Objects.equals(documentSeria, that.documentSeria) && Objects.equals(documentNumber, that.documentNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastname, firstname, patronymic, birthdate, documentSeria, documentNumber, clientId);
    }
}
