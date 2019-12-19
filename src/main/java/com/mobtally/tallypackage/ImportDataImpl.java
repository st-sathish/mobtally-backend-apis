package com.mobtally.tallypackage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "IMPORTDATA", namespace = "http://tallypackage.mobtally.com")
public class ImportDataImpl extends AbstractTallyPackageElement implements ImportData {

    @XmlElement(name = "TALLYMESSAGE", namespace = "xmlns:UDF=TallyUDF")
    private String tallyMessage = "hello";

    @Override
    public void setTallyMessage(String tallyMessage) {
        this.tallyMessage = tallyMessage;
    }

    @Override
    public String getTallyMessage() {
        return this.tallyMessage;
    }

    public static class Adapter extends XmlAdapter<ImportDataImpl, ImportData> {
        @Override
        public ImportDataImpl marshal(ImportData mp) throws Exception {
            return (ImportDataImpl) mp;
        }

        @Override
        public ImportData unmarshal(ImportDataImpl mp) throws Exception {
            return mp;
        }
    }

}
