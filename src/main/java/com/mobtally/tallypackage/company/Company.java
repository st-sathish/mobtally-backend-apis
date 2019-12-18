package com.mobtally.tallypackage.company;

import com.mobtally.tallypackage.base.TallyPackageElement;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

//@XmlJavaTypeAdapter(CompanyImpl.Adapter.class)
public interface Company extends TallyPackageElement {

    void setName(String name);

    String getName();

    void setReservedName(String reservedName);

    String getReservedName();

    void setCurrencyName(String currencyName);

    String getCurrencyName();

    void setEmail(String email);

    String getEmail();

    void setPinCode(String pinCode);

    String getPinCode();

    void setWebsite(String website);

    String getWebsite();
}
