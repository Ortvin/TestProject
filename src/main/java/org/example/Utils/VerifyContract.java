package org.example.Utils;
import org.example.View.ContractWindow;

import java.time.Period;

public class VerifyContract {

    private Boolean dataValidation = true;
    private String dataErrorNotification;

    public Boolean getDataValidation() {
        return dataValidation;
    }

    public void setDataValidation(Boolean dataValidation) {
        this.dataValidation = dataValidation;
    }

    public String getDataErrorNotification() {
        return dataErrorNotification;
    }

    public void setDataErrorNotification(String dataErrorNotification) {
        this.dataErrorNotification = dataErrorNotification;
    }

    public VerifyContract(ContractWindow contractWindow){
        if (contractWindow.getContractSum().isEmpty() || contractWindow.getBeginContractDate().isEmpty()
                || contractWindow.getEndContractDate().isEmpty() || contractWindow.getObjectTypeComboBox().isEmpty() ||
                contractWindow.getConstructionYear().isEmpty() || contractWindow.getSizeTextField().isEmpty() ) {
            setDataErrorNotification("Не все обязательные поля заполнены");
            setDataValidation(false);
            System.out.println(dataValidation);
        }

        if (dataValidation) {
            if (new Float(contractWindow.getContractSum().getValue()) <= 0 || new Float(contractWindow.getSizeTextField().getValue()) <= 0 ||
                    new Float(contractWindow.getConstructionYear().getValue()) < 1800) {
                setDataErrorNotification("Введено некорректное значение");
                setDataValidation(false);
            }

            if ((Period.between(contractWindow.getBeginContractDate().getValue(), contractWindow.getEndContractDate().getValue()).getDays()) < 0) {
                setDataErrorNotification("Дата окончания договора не может быть меньше даты начала договора");
                setDataValidation(false);
            }
        }

    }

}
