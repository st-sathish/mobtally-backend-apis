package com.mobtally.tallypackage.base;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(EnvelopImpl.Adapter.class)
public interface Envelop {

    ImportData getImportData();

    void setImportData(ImportData importData);
}
