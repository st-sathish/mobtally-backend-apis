package com.mobtally.tallypackage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlAdapter;

@XmlType(name = "tallypackage", namespace = "http://tallypackage.mobtally.com", propOrder = { "title", "series",
        "seriesTitle", "creators", "contributors", "subjects", "license", "language", "tracks", "catalogs",
        "attachments", "publications" })
@XmlRootElement(name = "tallypackage", namespace = "http://tallypackage.tallypackage.com")
@XmlAccessorType(XmlAccessType.NONE)
public class TallyPackageImpl implements TallyPackage {

    /** Context for serializing and deserializing */
    static final JAXBContext context;

    @XmlElementWrapper(name = "ENVELOP")
    @XmlElement(name = "ENVELOP")
    Envelop envelop;

    static {
        try {
            context = JAXBContext.newInstance("com.mobtally.tallypackage", TallyPackageImpl.class.getClassLoader());
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * A JAXB adapter that allows the {@link TallyPackage} interface to be un/marshalled
     */
    public static class Adapter extends XmlAdapter<TallyPackageImpl, TallyPackage> {
        @Override
        public TallyPackageImpl marshal(TallyPackage mp) throws Exception {
            return (TallyPackageImpl) mp;
        }

        @Override
        public TallyPackage unmarshal(TallyPackageImpl mp) throws Exception {
            return mp;
        }
    }
}
