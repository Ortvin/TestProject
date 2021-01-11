package org.example.View;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.example.Dao.ClientEntityDAO;
import org.example.Utils.NotNullConverter;
import org.example.Entity.ClientEntity;
import java.sql.Date;
import java.time.LocalDate;

public class ClientWindow extends Window {

    private DateField birthday = new DateField();
    private TextField passportSeriaTextField = new TextField();
    private TextField passportNumberTextField = new TextField();
    private Fio fio = new Fio();
    private ClientEntity clientEntity = new ClientEntity();
    NotNullConverter converter = new NotNullConverter();

    public ClientWindow(String caption, ContractWindow contractWindow){

        setWidth(45, Unit.PERCENTAGE);
        setHeightUndefined();
        setModal(true);
        center();
        setResizable(false);
        VerticalLayout vlContent = new VerticalLayout();

        Panel panelContent = new Panel(caption);
        panelContent.setWidth(100, Unit.PERCENTAGE);
        panelContent.setHeightUndefined();

        HorizontalLayout fioHl = new HorizontalLayout();

            fioHl = fio.fio();

        HorizontalLayout birthdayHl = new HorizontalLayout();
            Label label1 = new Label("Дата рождения");
            label1.setWidth("200px");
            birthdayHl.addComponents(label1, birthday);
        HorizontalLayout passportHl = new HorizontalLayout();
            Label label2 = new Label("Паспорт  Серия");
       //     label2.addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
            label2.setWidth("200px");
            Label label3 = new Label("№");
            passportHl.addComponents(label2, passportSeriaTextField, label3, passportNumberTextField);
        HorizontalLayout buttonHl = new HorizontalLayout();
            buttonHl.setSizeFull();
            Button saveButton = new Button("Сохранить");
            saveButton.addClickListener(e1 ->{
                if (!birthday.isEmpty() && !passportNumberTextField.isEmpty() && !passportSeriaTextField.isEmpty()
                        && !fio.surname.isEmpty() && !fio.firstName.isEmpty()){
                    super.close();
                    setClientEntity();
                    new ClientEntityDAO().saveClient(this.clientEntity);
                    contractWindow.setContractWindowClientInfo(clientEntity);
                } else {
                    Notification.show("Не все обязательные поля заполнены").setStyleName(ValoTheme.NOTIFICATION_ERROR);
                }
            });
            Button cancelButton = new Button("Отменить");
            cancelButton.addClickListener(e->{
               close();
            });
            buttonHl.addComponents(saveButton, cancelButton);
            buttonHl.setComponentAlignment(saveButton, Alignment.MIDDLE_CENTER);
            buttonHl.setComponentAlignment(cancelButton, Alignment.MIDDLE_CENTER);
        setContent(vlContent);
        vlContent.addComponents(panelContent, fioHl, birthdayHl, passportHl, buttonHl);
    }

    public void setClientWindowInfo(ClientEntity clientEntity) {
        try {
            birthday.setValue(LocalDate.parse(clientEntity.getBirthdate().toString()));
        } catch (Exception e) {
            birthday.setValue(null);
        }
        this.clientEntity = clientEntity;
        passportSeriaTextField.setValue(clientEntity.getDocumentSeria());
        passportNumberTextField.setValue(clientEntity.getDocumentNumber());
        fio.setFirstNameInfo(clientEntity.getFirstname());
        fio.setLastNameInfo(clientEntity.getLastname());
        fio.setPatronymic(clientEntity.getPatronymic());
    }

    public void setClientEntity() {
        clientEntity.setDocumentSeria(converter.notNullField(passportSeriaTextField.getValue()));
        clientEntity.setDocumentNumber(converter.notNullField(passportNumberTextField.getValue()));
        clientEntity.setLastname(converter.notNullField(fio.getSurname().getValue()));
        clientEntity.setFirstname(converter.notNullField(fio.getFirstName().getValue()));
        clientEntity.setPatronymic(converter.notNullField(fio.getPatronimyc().getValue()));
        clientEntity.setBirthdate(Date.valueOf(birthday.getValue()));
    }


}
