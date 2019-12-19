package com.mobtally.tallypackage.base;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ENVELOPE")
@XmlAccessorType(XmlAccessType.NONE)
public class Envelope {

    @XmlElement(name = "IMPORTDATA")
    private ImportData importData;

    public ImportData getImportData() {
        return importData;
    }

    public void setImportData(ImportData importData) {
        this.importData = importData;
    }
}
