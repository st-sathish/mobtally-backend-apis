package com.mobtally.tallypackage.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ImportData {

    @XmlElement(name = "TALLYMESSAGE")
    private TallyMessage tallyMessage;

    public TallyMessage getTallyMessage() {
        return tallyMessage;
    }

    public void setTallyMessage(TallyMessage tallyMessage) {
        this.tallyMessage = tallyMessage;
    }
}
