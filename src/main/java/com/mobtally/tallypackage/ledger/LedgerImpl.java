package com.mobtally.tallypackage.ledger;

import com.mobtally.tallypackage.AbstractTallyPackageElement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "ledger", namespace = "http://tallypackage.mobtally.org")
@XmlRootElement(name = "ledger", namespace = "http://tallypackage.mobtally.com")
public class LedgerImpl extends AbstractTallyPackageElement implements Ledger {

    /** Needed by JAXB */
    public LedgerImpl() {

    }

    public static class Adapter extends XmlAdapter<LedgerImpl, Ledger> {
        @Override
        public Ledger unmarshal(LedgerImpl ledger) throws Exception {
            return ledger;
        }

        @Override
        public LedgerImpl marshal(Ledger ledger) throws Exception {
            return (LedgerImpl) ledger;
        }
    }
}
