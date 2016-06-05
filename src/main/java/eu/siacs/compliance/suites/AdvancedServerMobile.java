package eu.siacs.compliance.suites;

import eu.siacs.compliance.tests.*;
import rocks.xmpp.addr.Jid;
import rocks.xmpp.core.session.XmppClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class AdvancedServerMobile extends AdvancedServerCore {

    public AdvancedServerMobile(XmppClient client, Jid jid, String password) {
        super(client, jid, password);
    }

    @Override
    List<Class<? extends AbstractTest>> getTests() {
        return Stream.concat(super.getTests().stream(), Arrays.asList(
                StreamManagement.class,
                CSI.class,
                Push.class
        ).stream()).collect(Collectors.toList());
    }

    @Override
    public String getName() {
        return "Advanced Server Mobile Compliance Suite";
    }
}
