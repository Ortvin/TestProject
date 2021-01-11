package org.example.View;

import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import org.example.Dao.ContractEntityDAO;
import org.example.Entity.ContractEntity;

import java.util.List;
import java.util.Set;

public class MainWindow extends VerticalLayout implements View {
    Grid<ContractEntity> mainGrid = new Grid<ContractEntity>();
    public MainWindow(){
        VerticalLayout mainWindow = new VerticalLayout();
        HorizontalLayout upHl = new HorizontalLayout();
        upHl.setHeight(10, Unit.PERCENTAGE);
        Button newContract = new Button("Создать договор");
        newContract.addClickListener(e -> {
            getUI().addWindow(new ContractWindow("Создать договор", this) );
        });
        Button openContract = new Button("Открыть договор");
        openContract.setEnabled(false);
        openContract.addClickListener(open -> {
            ContractEntity contractEntity = new ContractEntity();
            Set<ContractEntity> selectedItems =  mainGrid.getSelectedItems();
            for (ContractEntity s : selectedItems){
                contractEntity = s;
            }
            ContractWindow contractWindow = new ContractWindow("Договор № " + contractEntity.getContractSeriaNumber(), this);
            getUI().addWindow(contractWindow);
            contractWindow.editClientButton.setEnabled(true);
            contractWindow.setContractWindowContractInfo(contractEntity);
            contractWindow.setContractWindowClientInfo(contractEntity.getClientEntity());
            contractWindow.setContractWindowObjectInfo(contractEntity.getObjectEntity());
        });

        upHl.addComponents(newContract, openContract);
        VerticalLayout downVl = new VerticalLayout();
        downVl.setHeight(88, Unit.PERCENTAGE);
        Panel mainPanel = new Panel("Перечень договоров");
        mainGrid.setSizeFull();
        List list = new ContractEntityDAO().getAllContracts();
        mainGrid.setItems(list);
        mainGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        mainGrid.addColumn(ContractEntity::getContractSeriaNumber).setCaption("№№");
        mainGrid.addColumn(ContractEntity::getContractDate).setCaption("Дата контракта");
        mainGrid.addColumn(ContractEntity::getFIO).setCaption("Страхователь");
        mainGrid.addColumn(ContractEntity::getInsuranceFee).setCaption("Страховая премия");
        mainGrid.addColumn(ContractEntity::getContractBeginDate).setCaption("Дата начала действия договора");
        mainGrid.addColumn(ContractEntity::getContractEndDate).setCaption("Дата окончания договора");
        mainGrid.addSelectionListener(select -> {
            openContract.setEnabled(true);
            if (mainGrid.getSelectedItems().isEmpty()){
                openContract.setEnabled(false);
            }
        });

        downVl.addComponents(mainPanel, mainGrid);
        mainWindow.addComponents(upHl, downVl);
        addComponents(mainWindow);

    }


}

