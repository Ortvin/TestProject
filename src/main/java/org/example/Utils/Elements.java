package org.example.Utils;

import com.vaadin.ui.Grid;
import org.example.Dao.ClientEntityDAO;
import org.example.Dao.CountryEntityDAO;
import org.example.Dao.ObjectTypeEntityDAO;
import org.example.Entity.ClientEntity;
import org.example.Entity.CountryEntity;
import org.example.Entity.ObjecttypeEntity;
import org.example.View.Fio;


import java.util.ArrayList;
import java.util.List;

public class Elements {

    public Elements() {
    }

    public List getObjectTypeList(){
        List<ObjecttypeEntity> list = new ObjectTypeEntityDAO().getAllObjectTypes();
        return new ArrayList<>(list);
    }

    public List getCountryList(){
        List<CountryEntity> list = new CountryEntityDAO().getAllCountries();
        return new ArrayList<>(list);
    }

    public Grid<ClientEntity> getClientGrid(Fio fio){

        Grid<ClientEntity> clientEntityGrid = new Grid<>();
        clientEntityGrid.setSizeFull();
        String lastName = fio.getSurname().getValue();
        String firstName = fio.getFirstName().getValue();
        String patronymic = fio.getPatronimyc().getValue();
        List<ClientEntity> list = new ClientEntityDAO().getClientEntityByFIO(lastName, firstName, patronymic);
        clientEntityGrid.setItems(list);
        clientEntityGrid.addColumn(ClientEntity::getFIO).setCaption("ФИО");
        clientEntityGrid.addColumn(ClientEntity::getBirthdate).setCaption("Дата рождения");
        clientEntityGrid.addColumn(ClientEntity::getDocument).setCaption("Серия и номер паспорта");
        return clientEntityGrid;
    }




}
