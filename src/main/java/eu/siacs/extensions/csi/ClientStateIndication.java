package eu.siacs.extensions.csi;

import rocks.xmpp.core.stream.model.StreamFeature;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="csi")
public class ClientStateIndication extends StreamFeature {

    public static final String NAMESPACE = "urn:xmpp:csi:0";
}
