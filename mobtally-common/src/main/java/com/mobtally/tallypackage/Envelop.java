package com.mobtally.tallypackage;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(EnvelopImpl.Adapter.class)
public interface Envelop {

    ImportData getImportData();

    void setImportData(ImportData importData);
}
