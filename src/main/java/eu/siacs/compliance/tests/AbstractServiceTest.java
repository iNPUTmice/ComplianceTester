package eu.siacs.compliance.tests;


import eu.siacs.compliance.Result;
import rocks.xmpp.addr.Jid;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.extensions.disco.ServiceDiscoveryManager;
import rocks.xmpp.extensions.disco.model.items.Item;
import rocks.xmpp.extensions.disco.model.items.ItemNode;
import rocks.xmpp.util.concurrent.AsyncResult;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractServiceTest extends AbstractTest {

    public AbstractServiceTest(XmppClient client) {
        super(client);
    }

    @Override
    public Result run() {
        ServiceDiscoveryManager manager = client.getManager(ServiceDiscoveryManager.class);

        //manually iterating over items. ignoring the faulty ones
        AsyncResult<ItemNode> itemNode = manager.discoverItems(client.getDomain());
        try {
            List<Jid> items = new ArrayList<>();
            itemNode.getResult().getItems().stream().map(Item::getJid).forEach(items::add);
            items.add(client.getDomain());
            for(Jid jid : items) {
                try {
                    for(String feature : manager.discoverInformation(jid).getResult().getFeatures()) {
                        if (getNamespace().equals(feature)) {
                            return Result.PASSED;
                        }
                    }
                } catch (Exception e) {
                    //ignored
                }
            }
        } catch (Exception e) {
           return Result.FAILED;
        }
        return Result.FAILED;
    }

    public abstract String getNamespace();
}
