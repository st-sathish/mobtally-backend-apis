package com.mobtally.tallypackage;

import com.mobtally.tallypackage.base.Envelop;

public interface TallyPackageElement extends Cloneable {

    Envelop getEnvelop();

    void setEnvelop(Envelop envelop);

    /**
     * Create a deep copy of this object.
     *
     * @return The copy
     */
    Object clone();
}
