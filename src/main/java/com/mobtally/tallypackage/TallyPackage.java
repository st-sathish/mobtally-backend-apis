package com.mobtally.tallypackage;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(TallyPackageImpl.Adapter.class)
public interface TallyPackage extends Cloneable {


}
