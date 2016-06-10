package eu.siacs.compliance.suites;

import eu.siacs.compliance.tests.*;
import rocks.xmpp.addr.Jid;
import rocks.xmpp.core.session.XmppClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Conversations extends AdvancedServerIM {
    public Conversations(XmppClient client, Jid jid, String password) {
        super(client, jid, password);
    }

    @Override
    List<Class<? extends AbstractTest>> getTests() {
        return Stream.concat(super.getTests().stream(), Arrays.asList(
                CSI.class,
                HttpUpload.class,
                Proxy65.class,
                Push.class
        ).stream()).collect(Collectors.toList());
    }

    @Override
    protected void preHook() {
        printServerVersion();
    }

    @Override
    public String getName() {
        return "Conversations Compliance Suite";
    }
}
