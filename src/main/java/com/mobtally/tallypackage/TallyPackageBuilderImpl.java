package com.mobtally.tallypackage;

import org.w3c.dom.Node;

import java.io.InputStream;

public class TallyPackageBuilderImpl implements TallyPackageBuilder {

    @Override
    public TallyPackage createNew() throws TallyPackageException {
        return null;
    }

    @Override
    public TallyPackage loadFromXml(InputStream is) throws TallyPackageException {
        return null;
    }

    @Override
    public TallyPackage loadFromXml(String xml) throws TallyPackageException {
        return null;
    }

    @Override
    public TallyPackage loadFromXml(Node xml) throws TallyPackageException {
        return null;
    }

    @Override
    public void setSerializer(TallyPackageSerializer serializer) {

    }

    @Override
    public TallyPackageSerializer getSerializer() {
        return null;
    }
}
