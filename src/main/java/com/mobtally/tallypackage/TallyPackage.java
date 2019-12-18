package com.mobtally.tallypackage;

import com.mobtally.tallypackage.base.Envelop;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(TallyPackageImpl.Adapter.class)
public interface TallyPackage extends Cloneable {

    void setTitle(String title);

    String getTitle();
}
