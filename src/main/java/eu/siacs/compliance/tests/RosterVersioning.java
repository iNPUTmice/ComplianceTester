package eu.siacs.compliance.tests;

import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.core.stream.model.StreamFeature;


public class RosterVersioning extends AbstractStreamFeatureTest {
    public RosterVersioning(XmppClient client) {
        super(client);
    }

    @Override
    Class<? extends StreamFeature> getStreamFeature() {
        return rocks.xmpp.im.roster.versioning.model.RosterVersioning.class;
    }

    @Override
    public String getName() {
        return "Roster Versioning";
    }
}
