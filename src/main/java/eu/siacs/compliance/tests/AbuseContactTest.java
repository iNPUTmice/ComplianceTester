package eu.siacs.compliance.tests;

import eu.siacs.compliance.Result;
import rocks.xmpp.addr.Jid;
import rocks.xmpp.core.XmppException;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.extensions.data.model.DataForm;
import rocks.xmpp.extensions.disco.ServiceDiscoveryManager;

import java.util.List;

public class AbuseContactTest extends AbstractTest {
    public AbuseContactTest(XmppClient client) {
        super(client);
    }

    @Override
    public Result run() {
        Jid target = Jid.of(client.getConnectedResource().getDomain());
        final ServiceDiscoveryManager serviceDiscoveryManager = client.getManager(ServiceDiscoveryManager.class);
        try {
            List<DataForm> extensions = serviceDiscoveryManager.discoverInformation(target).getResult().getExtensions();
            for(DataForm extension: extensions) {
                final DataForm.Field addr = extension.findField("abuse-addresses");
                if(addr != null && addr.getValues() != null && addr.getValues().size() > 0) {
                    return Result.PASSED;
                }
            }
            return Result.FAILED;
        } catch (XmppException e) {
            return Result.FAILED;
        }
    }
    @Override
    public String getName() {
        return "XEP-0157: Contact Addresses for XMPP Services";
    }
}
