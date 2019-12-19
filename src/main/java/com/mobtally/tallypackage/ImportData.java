package com.mobtally.tallypackage;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(ImportDataImpl.Adapter.class)
public interface ImportData extends TallyPackageElement {


    void setTallyMessage(String tallyMessage);

    String getTallyMessage();
}
