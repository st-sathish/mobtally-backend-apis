package com.mobtally.tallypackage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "TALLYMESSAGE", namespace = "http://tallypackage.mobtally.com")
public class TallyMessageImpl extends AbstractTallyPackageElement implements TallyMessage {

    public static class Adapter extends XmlAdapter<TallyMessageImpl, TallyMessage> {
        @Override
        public TallyMessageImpl marshal(TallyMessage mp) throws Exception {
            return (TallyMessageImpl) mp;
        }

        @Override
        public TallyMessage unmarshal(TallyMessageImpl mp) throws Exception {
            return mp;
        }
    }
}
