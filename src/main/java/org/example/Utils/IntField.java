package org.example.Utils;

import com.vaadin.data.HasValue;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.TextField;

public class IntField extends TextField implements HasValue.ValueChangeListener<String>{

    public String lastValue;

    public IntField() {
        setValueChangeMode(ValueChangeMode.EAGER);
        addValueChangeListener(this);
        lastValue="";
    }

    @Override
    public void valueChange(ValueChangeEvent<String> event) {
        String text = (String) event.getValue();
        try {
            new Integer(text);
            lastValue = text;
        } catch (NumberFormatException e) {
            if (text.equals("")){
                setValue("0");
            } else {
                setValue(lastValue);
            }
        }
    }
}