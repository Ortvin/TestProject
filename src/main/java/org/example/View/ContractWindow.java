package org.example.View;

import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.example.Utils.*;
import org.example.Buisness.Calculate;
import org.example.Dao.ContractEntityDAO;
import org.example.Entity.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import static java.math.BigDecimal.*;

public class ContractWindow extends Window {

    private FloatField contractSum = new FloatField();
    private DateField beginContractDate = new DateField();
    private DateField endContractDate = new DateField();
    private ComboBox<ObjecttypeEntity> objectTypeComboBox  = new ComboBox<>();
    private IntField constructionYear = new IntField();
    private FloatField sizeTextField = new FloatField();
    private TextField calculationDateTextField = new TextField();

    private TextField insuranceFeeTextField = new TextField();
    private TextField contractNumberTextField = new TextField();
    private DateField contractDateDateField = new DateField();
    private TextField fioTextField = new TextField();
    private DateField birthdayDateField = new DateField();
    private TextField passportSeria = new TextField();
    private TextField passportNumber = new TextField();
    private ComboBox<CountryEntity> countryComboBox  = new ComboBox<>("Страна");
    private TextField indexTextField = new TextField("Индекс");
    private TextField regionTextField = new TextField("Республика, край, область");
    private TextField districtTextField = new TextField("Район");
    private TextField townTextField = new TextField("Населенный пункт");
    private TextField streetTextField = new TextField("Улица");
    private TextField houseTextField = new TextField("Дом");
    private TextField building1TextField = new TextField("Корпус");
    private TextField building2TextField = new TextField("Строение");
    private TextField flatTextField = new TextField("Квартира");
    private TextArea commentTextArea = new TextArea("Комментарий к договору (не печатается на полисе)");

    Button editClientButton = new Button("Изменить");
    ClientEntity clientEntity = new ClientEntity();
    ContractEntity contractEntity = new ContractEntity();
    ObjectEntity objectEntity = new ObjectEntity();;
    NotNullConverter converter = new NotNullConverter();

    public ContractWindow(String caption, MainWindow mainWindow) {

        setCaption(caption);
        setWidth(70, Unit.PERCENTAGE);
        setHeightUndefined();
        setModal(true);
        center();

        Panel panelContent1 = new Panel("Расчет");
        panelContent1.setWidth(100, Unit.PERCENTAGE);
        panelContent1.setHeightUndefined();

            VerticalLayout vlContent = new VerticalLayout();
                vlContent.setWidth(100, Unit.PERCENTAGE);
                HorizontalLayout hlLeftContent1 = new HorizontalLayout();
                    Label label1 = new Label("Страховая сумма");
                    label1.setWidth("200px");
                    Label label2 = new Label("Срок действия с");
                    label2.addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
                    label2.setWidth("150px");
                    Label label3 = new Label("по");
                    contractSum.setRequiredIndicatorVisible(true);
                    beginContractDate.setRequiredIndicatorVisible(true);
                    endContractDate.setRequiredIndicatorVisible(true);
                    hlLeftContent1.addComponents(label1, contractSum, label2, beginContractDate, label3, endContractDate);
                HorizontalLayout hlLeftContent2 = new HorizontalLayout();
                    Label label4 = new Label("Тип недвижимости");
                    label4.setWidth("200px");
                    objectTypeComboBox.setRequiredIndicatorVisible(true);
                    Elements combobox = new Elements();
                    objectTypeComboBox.setItems(combobox.getObjectTypeList());
                    objectTypeComboBox.setItemCaptionGenerator(ObjecttypeEntity::getObjecTypeName);
                    hlLeftContent2.addComponents(label4, objectTypeComboBox);
                HorizontalLayout hlLeftContent3 = new HorizontalLayout();
                    Label label5 = new Label("Год постройки");
                    label5.setWidth("200px");
                    constructionYear.setRequiredIndicatorVisible(true);
                    hlLeftContent3.addComponents(label5, constructionYear);
                HorizontalLayout hlLeftContent4 = new HorizontalLayout();
                    Label label6 = new Label("Площадь, кв.м");
                    label6.setWidth("200px");
                    sizeTextField.setRequiredIndicatorVisible(true);
                    hlLeftContent4.addComponents(label6, sizeTextField);
                HorizontalLayout hlLeftContent5 = new HorizontalLayout();
                    Button calculateButton = new Button("Рассчитать");
                    calculateButton.addClickListener(e-> {

                    VerifyContract verify = new VerifyContract(this);

                    if (verify.getDataValidation()){

                        Calculate calculateObjectTypeCoef = new Calculate();
                        double coefObjectType1 = calculateObjectTypeCoef.getCoefObjectType(objectTypeComboBox.getValue().getObjecTypeName());
                        Calculate calculateConstructionYear = new Calculate();
                        double coefConstructionYear1 = calculateConstructionYear.getCoefConstructionYear(new Integer(constructionYear.getValue()));
                        Calculate calculateSizeCoef = new Calculate();
                        double coefSize1 = calculateSizeCoef.getCoefSize(new Double(sizeTextField.getValue()));
                        Calculate calculate = new Calculate(new Float(contractSum.getValue()), beginContractDate.getValue(),
                                endContractDate.getValue(), coefObjectType1, coefConstructionYear1, coefSize1);
                        insuranceFeeTextField.setValue(String.valueOf(new DecimalFormat( "#.##" ).format(calculate.getInsuranceFee())));
                        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = new Date();
                        calculationDateTextField.setValue(formater.format(date));
                      } else {
                        Notification.show(verify.getDataErrorNotification()).setStyleName(ValoTheme.NOTIFICATION_ERROR);
                    }
                    });
                    hlLeftContent5.setSizeFull();
                    hlLeftContent5.addComponents(calculateButton);
                    hlLeftContent5.setComponentAlignment(calculateButton, Alignment.MIDDLE_CENTER);
                    calculateButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
                HorizontalLayout hlLeftContent6 = new HorizontalLayout();
                    Label label7 = new Label("Дата расчета");
                    label7.setWidth("200px");
                    Label label8 = new Label("Премия");
                    label8.addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
                    label8.setWidth("150px");
                    calculationDateTextField.setReadOnly(true);
                    insuranceFeeTextField.setReadOnly(true);
                    hlLeftContent6.addComponents(label7, calculationDateTextField, label8, insuranceFeeTextField);
                HorizontalLayout hlLeftContent7 = new HorizontalLayout();
                    Label label9 = new Label("№ договора");
                    label9.setWidth("200px");
                    Label label10 = new Label("Дата заключения");
                    label10.addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
                    label10.setWidth("150px");
                    hlLeftContent7.addComponents(label9, contractNumberTextField, label10, contractDateDateField);
                Panel panelContent2 = new Panel("Страхователь");
                    panelContent2.setWidth(100, Unit.PERCENTAGE);
                    panelContent2.setHeightUndefined();
                HorizontalLayout hlLeftContent8 = new HorizontalLayout();
                    Button chooseClientButton = new Button("Выбрать");
                    chooseClientButton.addClickListener(e ->{
                            Fio fio = new Fio();
                            getUI().addWindow(new ChooseClientWindow(fio, this));
                            });
                    Label label11 = new Label("ФИО");
                    fioTextField.setWidth ("600px");
                    fioTextField.setReadOnly(true);

                    editClientButton.setEnabled(false);
                    editClientButton.addClickListener(edit -> {
                        ClientWindow clientWindow = new ClientWindow("Редактировать клиента", this);
                        getUI().addWindow(clientWindow);
                        clientWindow.setClientWindowInfo(clientEntity);
                    });
                    hlLeftContent8.addComponents(chooseClientButton, label11, fioTextField, editClientButton);
                HorizontalLayout hlLeftContent9 = new HorizontalLayout();
                    Label label12 = new Label("Дата рождения");
                    label12.setWidth("200px");
                    Label label13 = new Label("Паспорт  Серия");
                    label13.addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
                    label13.setWidth("150px");
                    Label label14 = new Label("№");
                    birthdayDateField.setReadOnly(true);
                    passportSeria.setReadOnly(true);
                    passportNumber.setReadOnly(true);
                    hlLeftContent9.addComponents(label12, birthdayDateField, label13, passportSeria, label14, passportNumber);
                Panel panelContent3 = new Panel("Адрес недвижимости");
                    panelContent2.setWidth(100, Unit.PERCENTAGE);
                    panelContent2.setHeightUndefined();
                HorizontalLayout hlLeftContent10 = new HorizontalLayout();
                    Elements cmbbx = new Elements();
                    countryComboBox.setItems(cmbbx.getCountryList());
                    countryComboBox.setItemCaptionGenerator(CountryEntity::getCountryName);

                    hlLeftContent10.addComponents(countryComboBox, indexTextField, regionTextField, districtTextField);
                HorizontalLayout hlLeftContent11 = new HorizontalLayout();
                    hlLeftContent11.addComponents(townTextField, streetTextField, houseTextField, building1TextField, building2TextField, flatTextField);

                    commentTextArea.setHeight("50px");
                    commentTextArea.setWidth(100, Unit.PERCENTAGE);

                HorizontalLayout hlLeftContent13 = new HorizontalLayout();
                hlLeftContent13.setSizeFull();
                    Button saveContractButton = new Button("Сохранить");
                        saveContractButton.addClickListener(save -> {
                            saveContractData();
                            close();
                            mainWindow.mainGrid.setItems(new ContractEntityDAO().getAllContracts());
                        });


                    Button returnToMainWindowButton = new Button("К списку договоров");
                        returnToMainWindowButton.addClickListener(close -> {
                            close();
                        });
                    hlLeftContent13.addComponents(saveContractButton);
                    hlLeftContent13.addComponents(saveContractButton, returnToMainWindowButton);
                    hlLeftContent13.setComponentAlignment(saveContractButton, Alignment.MIDDLE_RIGHT);
                    hlLeftContent13.setComponentAlignment(returnToMainWindowButton, Alignment.MIDDLE_LEFT);
        setContent(vlContent);
                vlContent.addComponents(panelContent1, hlLeftContent1,hlLeftContent2, hlLeftContent3, hlLeftContent4, hlLeftContent5, hlLeftContent6, hlLeftContent7, panelContent2,
                        hlLeftContent8, hlLeftContent9, panelContent3, hlLeftContent10, hlLeftContent11, commentTextArea, hlLeftContent13);
        }

    public void setContractWindowClientInfo(ClientEntity clientEntity) {

        this.clientEntity = clientEntity;
        fioTextField.setValue(clientEntity.getFIO());
        try {
            birthdayDateField.setValue(LocalDate.parse(clientEntity.getBirthdate().toString()));
        } catch (Exception e) {
            birthdayDateField.setValue(null);
        }
        passportSeria.setValue(clientEntity.getDocumentSeria());
           passportNumber.setValue(clientEntity.getDocumentNumber());

    }
    public void setContractWindowContractInfo(ContractEntity contractEntity) {

        this.contractEntity = contractEntity;
        contractSum.setValue(contractEntity.getContractSum().toString());
        beginContractDate.setValue(LocalDate.parse(contractEntity.getContractBeginDate().toString()));
        endContractDate.setValue(LocalDate.parse(contractEntity.getContractEndDate().toString()));
        calculationDateTextField.setValue(contractEntity.getCalculationFeeDate().toString());
        insuranceFeeTextField.setValue(contractEntity.getInsuranceFee().toString());
        contractNumberTextField.setValue(contractEntity.getContractSeriaNumber());
        contractDateDateField.setValue(LocalDate.parse(contractEntity.getContractDate().toString()));
        commentTextArea.setValue(converter.notNullField(contractEntity.getComment()));

    }
    public void setContractWindowObjectInfo(ObjectEntity objectEntity) {

        objectTypeComboBox.setValue(objectEntity.getObjecttypeEntity());
        constructionYear.setValue(String.valueOf(objectEntity.getConstructionYear()));
        sizeTextField.setValue(objectEntity.getObjectSize().toString());
        countryComboBox.setValue(objectEntity.getCountryEntity());
        indexTextField.setValue(converter.notNullField(objectEntity.getObjectIndex()));
        regionTextField.setValue(converter.notNullField(objectEntity.getRegion()));
        districtTextField.setValue(converter.notNullField(objectEntity.getDistrict()));
        townTextField.setValue(converter.notNullField(objectEntity.getTown()));
        streetTextField.setValue(converter.notNullField(objectEntity.getStreet()));
        houseTextField.setValue(converter.notNullField(objectEntity.getHouse()));
        building1TextField.setValue(converter.notNullField(objectEntity.getBuilding1()));
        building2TextField.setValue(converter.notNullField(objectEntity.getBuilding2()));
        flatTextField.setValue(converter.notNullField(objectEntity.getFlat()));
    }

    public void saveContractData(){

        setContractEntity();
        setObjectEntity();
        contractEntity.setClientEntity(clientEntity);
        contractEntity.setObjectEntity(objectEntity);
        new ContractEntityDAO().saveOrUpdateContract(contractEntity);
    }

    public void setContractEntity(){

        contractEntity.setContractDate(java.sql.Date.valueOf(contractDateDateField.getValue()));
        contractEntity.setContractSum(valueOf(new Float(contractSum.getValue())));
        contractEntity.setContractBeginDate(java.sql.Date.valueOf(beginContractDate.getValue()));
        contractEntity.setContractEndDate(java.sql.Date.valueOf(endContractDate.getValue()));
        contractEntity.setCalculationFeeDate(java.sql.Date.valueOf(calculationDateTextField.getValue()));
        contractEntity.setContractSeriaNumber(contractNumberTextField.getValue());
        contractEntity.setInsuranceFee(valueOf(new Float(insuranceFeeTextField.getValue().replaceAll(",", "."))));
        contractEntity.setComment(commentTextArea.getValue());

    }

    public void setObjectEntity(){

        contractEntity.setObjectEntity(objectEntity);
        objectEntity.setCountryEntity(countryComboBox.getValue());
        objectEntity.setObjectIndex(indexTextField.getValue());
        objectEntity.setRegion(regionTextField.getValue());
        objectEntity.setDistrict(districtTextField.getValue());
        objectEntity.setTown(townTextField.getValue());
        objectEntity.setStreet(streetTextField.getValue());
        objectEntity.setHouse(houseTextField.getValue());
        objectEntity.setBuilding1(building1TextField.getValue());
        objectEntity.setBuilding2(building2TextField.getValue());
        objectEntity.setObjectSize(valueOf(new Float(sizeTextField.getValue())));
        objectEntity.setConstructionYear(Integer.parseInt(constructionYear.getValue()));
        objectEntity.setFlat(flatTextField.getValue());
        objectEntity.setObjecttypeEntity(objectTypeComboBox.getValue());

    }


    public FloatField getContractSum() {
        return contractSum;
    }

    public void setContractSum(FloatField contractSum) {
        this.contractSum = contractSum;
    }

    public DateField getBeginContractDate() {
        return beginContractDate;
    }

    public void setBeginContractDate(DateField beginContractDate) {
        this.beginContractDate = beginContractDate;
    }

    public DateField getEndContractDate() {
        return endContractDate;
    }

    public void setEndContractDate(DateField endContractDate) {
        this.endContractDate = endContractDate;
    }

    public ComboBox<ObjecttypeEntity> getObjectTypeComboBox() {
        return objectTypeComboBox;
    }

    public void setObjectTypeComboBox(ComboBox<ObjecttypeEntity> objectTypeComboBox) {
        this.objectTypeComboBox = objectTypeComboBox;
    }

    public IntField getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(IntField constructionYear) {
        this.constructionYear = constructionYear;
    }

    public FloatField getSizeTextField() {
        return sizeTextField;
    }

    public void setSizeTextField(FloatField sizeTextField) {
        this.sizeTextField = sizeTextField;
    }

    public TextField getCalculationDateTextField() {
        return calculationDateTextField;
    }

    public void setCalculationDateTextField(TextField calculationDateTextField) {
        this.calculationDateTextField = calculationDateTextField;
    }

    public TextField getInsuranceFeeTextField() {
        return insuranceFeeTextField;
    }

    public void setInsuranceFeeTextField(TextField insuranceFeeTextField) {
        this.insuranceFeeTextField = insuranceFeeTextField;
    }

    public TextField getContractNumberTextField() {
        return contractNumberTextField;
    }

    public void setContractNumberTextField(TextField contractNumberTextField) {
        this.contractNumberTextField = contractNumberTextField;
    }

    public DateField getContractDateDateField() {
        return contractDateDateField;
    }

    public void setContractDateDateField(DateField contractDateDateField) {
        this.contractDateDateField = contractDateDateField;
    }

    public TextField getFioTextField() {
        return fioTextField;
    }

    public void setFioTextField(TextField fioTextField) {
        this.fioTextField = fioTextField;
    }

    public DateField getBirthdayDateField() {
        return birthdayDateField;
    }

    public void setBirthdayDateField(DateField birthdayDateField) {
        this.birthdayDateField = birthdayDateField;
    }

    public TextField getPassportSeria() {
        return passportSeria;
    }

    public void setPassportSeria(TextField passportSeria) {
        this.passportSeria = passportSeria;
    }

    public TextField getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(TextField passportNumber) {
        this.passportNumber = passportNumber;
    }

    public ComboBox<CountryEntity> getCountryComboBox() {
        return countryComboBox;
    }

    public void setCountryComboBox(ComboBox<CountryEntity> countryComboBox) {
        this.countryComboBox = countryComboBox;
    }

    public TextField getIndexTextField() {
        return indexTextField;
    }

    public void setIndexTextField(TextField indexTextField) {
        this.indexTextField = indexTextField;
    }

    public TextField getRegionTextField() {
        return regionTextField;
    }

    public void setRegionTextField(TextField regionTextField) {
        this.regionTextField = regionTextField;
    }

    public TextField getDistrictTextField() {
        return districtTextField;
    }

    public void setDistrictTextField(TextField districtTextField) {
        this.districtTextField = districtTextField;
    }

    public TextField getTownTextField() {
        return townTextField;
    }

    public void setTownTextField(TextField townTextField) {
        this.townTextField = townTextField;
    }

    public TextField getStreetTextField() {
        return streetTextField;
    }

    public void setStreetTextField(TextField streetTextField) {
        this.streetTextField = streetTextField;
    }

    public TextField getHouseTextField() {
        return houseTextField;
    }

    public void setHouseTextField(TextField houseTextField) {
        this.houseTextField = houseTextField;
    }

    public TextField getBuilding1TextField() {
        return building1TextField;
    }

    public void setBuilding1TextField(TextField building1TextField) {
        this.building1TextField = building1TextField;
    }

    public TextField getBuilding2TextField() {
        return building2TextField;
    }

    public void setBuilding2TextField(TextField building2TextField) {
        this.building2TextField = building2TextField;
    }

    public TextField getFlatTextField() {
        return flatTextField;
    }

    public void setFlatTextField(TextField flatTextField) {
        this.flatTextField = flatTextField;
    }

    public TextArea getCommentTextArea() {
        return commentTextArea;
    }

    public void setCommentTextArea(TextArea commentTextArea) {
        this.commentTextArea = commentTextArea;
    }
}