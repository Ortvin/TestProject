package org.example.View;

import com.vaadin.ui.*;

public class Fio extends HorizontalLayout {
    TextField surname = new TextField("Фамилия");
    TextField firstName = new TextField("Имя");
    TextField patronimyc = new TextField("Отчество");

    public TextField getSurname() {
        return surname;
    }

    public void setSurname(TextField surname) {
        this.surname = surname;
    }

    public TextField getFirstName() {
        return firstName;
    }

    public void setFirstName(TextField firstName) {
        this.firstName = firstName;
    }

    public void setFirstNameInfo(String firstName) {
        this.firstName.setValue(firstName);
    }

    public void setLastNameInfo(String lastName) {
        this.surname.setValue(lastName);
    }

    public void setPatronymic(String patronymic) {
        this.patronimyc.setValue(patronymic);
    }

    public TextField getPatronimyc() {
        return patronimyc;
    }

    public void setPatronimyc(TextField patronimyc) {
        this.patronimyc = patronimyc;
    }

    public HorizontalLayout fio() {
        HorizontalLayout fioHl = new HorizontalLayout();
        fioHl.setSizeFull();
        //Label fiolabel = new Label("ФИО");
        fioHl.addComponents( surname, firstName, patronimyc);
        fioHl.setComponentAlignment(surname, Alignment.MIDDLE_CENTER);
        fioHl.setComponentAlignment(firstName, Alignment.MIDDLE_CENTER);
        fioHl.setComponentAlignment(patronimyc, Alignment.MIDDLE_CENTER);

        return fioHl;

    };
}