package com.mobtally.tallypackage;

import com.mobtally.tallypackage.base.Envelop;
import com.mobtally.tallypackage.base.TallyPackageElement;
import com.mobtally.util.IoSupport;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;

public abstract class AbstractTallyPackageElement implements TallyPackageElement, Serializable {

    /*@XmlElement(name = "ENVELOP")
    private Envelop envelop;

    @Override
    public Envelop getEnvelop() {
        return this.envelop;
    }

    @Override
    public void setEnvelop(Envelop envelop) {
        this.envelop = envelop;
    }*/

    /**
     * Attention: The media package reference is not being cloned so that calling <code>getMediaPackage()</code> on the
     * clone yields null.
     */
    @Override
    public Object clone() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        try {
            Marshaller marshaller = TallyPackageImpl.context.createMarshaller();
            marshaller.marshal(this, out);
            Unmarshaller unmarshaller = TallyPackageImpl.context.createUnmarshaller();
            in = new ByteArrayInputStream(out.toByteArray());
            return unmarshaller.unmarshal(in);
        } catch (JAXBException e) {
            throw new RuntimeException(e.getLinkedException() != null ? e.getLinkedException() : e);
        } finally {
            IoSupport.closeQuietly(in);
        }
    }

}
