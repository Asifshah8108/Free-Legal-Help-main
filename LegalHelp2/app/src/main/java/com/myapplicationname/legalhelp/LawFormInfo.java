package com.myapplicationname.legalhelp;

public class LawFormInfo {
    private String personName;
    private String personEmailId;
    private String personMobileNo;
    private String personSelection;
    private String personDescription;

    public LawFormInfo(String personName, String personEmailId, String personMobileNo, String personSelection, String personDescription) {
        this.personName = personName;
        this.personEmailId = personEmailId;
        this.personMobileNo = personMobileNo;
        this.personSelection = personSelection;
        this.personDescription = personDescription;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonEmailId() {
        return personEmailId;
    }

    public void setPersonEmailId(String personEmailId) {
        this.personEmailId = personEmailId;
    }

    public String getPersonMobileNo() {
        return personMobileNo;
    }

    public void setPersonMobileNo(String personMobileNo) {
        this.personMobileNo = personMobileNo;
    }

    public String getPersonSelection() {
        return personSelection;
    }

    public void setPersonSelection(String personSelection) {
        this.personSelection = personSelection;
    }

    public String getPersonDescription() {
        return personDescription;
    }

    public void setPersonDescription(String personDescription) {
        this.personDescription = personDescription;
    }


}
