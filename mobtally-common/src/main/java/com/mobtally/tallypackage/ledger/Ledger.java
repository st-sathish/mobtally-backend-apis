package com.mobtally.tallypackage.ledger;

import com.mobtally.tallypackage.TallyPackageElement;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlJavaTypeAdapter(LedgerImpl.Adapter.class)
public interface Ledger extends TallyPackageElement {
}
