package com.mobtally.tallypackage;

import org.xml.sax.InputSource;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringWriter;

import static org.apache.commons.io.IOUtils.toInputStream;

public final class TallyPackageElementParser {

    /**
     * Private constructor to prohibit instances of this static utility class.
     */
    private TallyPackageElementParser() {
        // Nothing to do
    }

    /**
     * Serializes the media package element to a string.
     *
     * @param element
     *         the element
     * @return the serialized media package element
     * @throws TallyPackageException
     *         if serialization failed
     */
    public static String getAsXml(TallyPackageElement element) throws TallyPackageException {
        if (element == null)
            throw new IllegalArgumentException("Mediapackage element must not be null");
        StringWriter writer = new StringWriter();
        Marshaller m = null;
        try {
            m = TallyPackageImpl.context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);
            m.marshal(element, writer);
            return writer.toString();
        } catch (JAXBException e) {
            throw new TallyPackageException(e.getLinkedException() != null ? e.getLinkedException() : e);
        }
    }

    public static TallyPackageElement getFromXml(String xml) throws TallyPackageException {
        Unmarshaller m = null;
        try {
            m = TallyPackageImpl.context.createUnmarshaller();
            return (TallyPackageElement) m.unmarshal(new InputSource(toInputStream(xml)));
        } catch (JAXBException e) {
            throw new TallyPackageException(e.getLinkedException() != null ? e.getLinkedException() : e);
        }
    }
}
