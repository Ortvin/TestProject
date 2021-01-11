package org.example.View;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import org.example.Utils.Elements;
import org.example.Entity.ClientEntity;

import java.util.Set;

public class ChooseClientWindow extends Window {
    Grid<ClientEntity> clientGrid = new Grid<>();
    public ChooseClientWindow(Fio fio, ContractWindow contractWindow){

        setWidth(45, Unit.PERCENTAGE);
        setHeightUndefined();
        setModal(true);
        center();
        setResizable(false);
        VerticalLayout vlContent = new VerticalLayout();
            Panel panelContent1 = new Panel("Выбор клиентов");
                panelContent1.setWidth(100, Unit.PERCENTAGE);
                panelContent1.setHeightUndefined();
            HorizontalLayout fioHl = new HorizontalLayout();
                fioHl = fio.fio();
                Button findButton = new Button("Найти");
                findButton.setWidth("100px");
                findButton.setIcon(VaadinIcons.SEARCH);
                findButton.addClickListener(e -> {
                   getUI().addWindow(new ChooseClientWindow(fio,contractWindow));
                   super.close();
                });
                fioHl.addComponents(findButton);
                fioHl.setComponentAlignment(findButton, Alignment.BOTTOM_LEFT);
            HorizontalLayout buttonHl = new HorizontalLayout();
                buttonHl.setSizeFull();
                Button chooseButton = new Button("Выбрать");
                    chooseButton.addClickListener(e1 -> {
                        super.close();
                        ClientEntity ce = new ClientEntity();
                        Set<ClientEntity> selectedItems =  clientGrid.getSelectedItems();
                        for (ClientEntity s : selectedItems){
                            ce = s;
                        }
                        contractWindow.setContractWindowClientInfo(ce);
                        contractWindow.editClientButton.setEnabled(true);
                    });

                    chooseButton.setEnabled(false);

                Button newClientButton = new Button("Новый");
                    newClientButton.addClickListener(e -> {
                        getUI().addWindow(new ClientWindow("Новый клиент", contractWindow));
                        super.close();
                    });
                Button closeButton = new Button("Закрыть");
                closeButton.addClickListener(close ->{
                   close();
                });
                buttonHl.addComponents(chooseButton, newClientButton, closeButton );
                buttonHl.setComponentAlignment(chooseButton, Alignment.MIDDLE_CENTER);
                buttonHl.setComponentAlignment(newClientButton, Alignment.MIDDLE_CENTER);
                buttonHl.setComponentAlignment(closeButton, Alignment.MIDDLE_CENTER);
                clientGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
                clientGrid = new Elements().getClientGrid(fio);
                clientGrid.addSelectionListener(select -> {
                   chooseButton.setEnabled(true);
                });

        setContent(vlContent);
        vlContent.addComponents(panelContent1, fioHl, clientGrid, buttonHl);

    }




}
