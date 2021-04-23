package com.example.recyclerviewallinone;

public class ModelClass extends BaseModelClass{

    private String name;
    private boolean isRowSelected;
    private boolean isRadioButtonSelected;
    private boolean isCheckboxSelected;

    public ModelClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRadioButtonSelected() {
        return isRadioButtonSelected;
    }

    public void setRadioButtonSelected(boolean radioButtonSelected) {
        isRadioButtonSelected = radioButtonSelected;
    }

    public boolean isCheckboxSelected() {
        return isCheckboxSelected;
    }

    public void setCheckboxSelected(boolean checkboxSelected) {
        isCheckboxSelected = checkboxSelected;
    }

    public boolean isRowSelected() {
        return isRowSelected;
    }

    public void setRowSelected(boolean rowSelected) {
        isRowSelected = rowSelected;
    }

    @Override public String toString() {
        return "ModelClass{" +
                "name='" + name + '\'' +
                ", isRowSelected=" + isRowSelected +
                ", isRadioButtonSelected=" + isRadioButtonSelected +
                ", isCheckboxSelected=" + isCheckboxSelected +
                '}';
    }
}