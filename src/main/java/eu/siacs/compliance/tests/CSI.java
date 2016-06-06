package eu.siacs.compliance.tests;

import eu.siacs.extensions.csi.ClientStateIndication;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.core.stream.model.StreamFeature;

public class CSI extends AbstractStreamFeatureTest {

    public CSI(XmppClient client) {
        super(client);
    }

    @Override
    Class<? extends StreamFeature> getStreamFeature() {
        return ClientStateIndication.class;
    }

    @Override
    public String getName() {
        return "XEP-0352: Client State Indication";
    }
}
