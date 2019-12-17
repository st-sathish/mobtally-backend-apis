package com.mobtally.tallypackage;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.adapters.XmlAdapter;

public class EnvelopImpl implements Envelop {

    @XmlElementWrapper(name = "IMPORTDATA")
    @XmlElement(name = "IMPORTDATA")
    private ImportData importData;

    @Override
    public ImportData getImportData() {
        return importData;
    }

    @Override
    public void setImportData(ImportData importData) {
        this.importData = importData;
    }

    public static class Adapter extends XmlAdapter<EnvelopImpl, Envelop> {
        @Override
        public EnvelopImpl marshal(Envelop mp) throws Exception {
            return (EnvelopImpl) mp;
        }

        @Override
        public Envelop unmarshal(EnvelopImpl mp) throws Exception {
            return mp;
        }
    }
}
