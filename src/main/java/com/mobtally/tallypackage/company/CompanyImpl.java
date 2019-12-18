package com.mobtally.tallypackage.company;

import com.mobtally.tallypackage.AbstractTallyPackageElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;

//@XmlAccessorType(XmlAccessType.NONE)
//@XmlType(name = "COMPANY", namespace = "http://tallypackage.mobtally.org")
//@XmlRootElement(name = "COMPANY", namespace = "http://tallypackage.mobtally.org")
public class CompanyImpl extends AbstractTallyPackageElement {

    /** Needed by JAXB */
    public CompanyImpl() {

    }

    /*@XmlAttribute(name = "NAME")
    private String name = null;

    @XmlAttribute(name = "RESERVEDNAME")
    private String reservedName = null;

    @XmlElement(name = "CURENCYNAME")
    private String currencyName;

    @XmlElement(name = "EMAIL")
    private String email;

    @XmlElement(name = "PINCODE")
    private String pincode;

    @XmlElement(name = "WEBSITE")
    private String website;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setReservedName(String reservedName) {
        this.reservedName = reservedName;
    }

    @Override
    public String getReservedName() {
        return this.reservedName;
    }

    @Override
    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    @Override
    public String getCurrencyName() {
        return this.currencyName;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setPinCode(String pinCode) {
        this.pincode = pinCode;
    }

    @Override
    public String getPinCode() {
        return this.pincode;
    }

    @Override
    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String getWebsite() {
        return this.website;
    }

    public static class Adapter extends XmlAdapter<CompanyImpl, Company> {
        @Override
        public CompanyImpl marshal(Company mp) throws Exception {
            return (CompanyImpl) mp;
        }

        @Override
        public Company unmarshal(CompanyImpl mp) throws Exception {
            return mp;
        }
    }*/
}
