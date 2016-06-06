package eu.siacs.compliance.tests;


import eu.siacs.compliance.Result;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.extensions.disco.ServiceDiscoveryManager;
import rocks.xmpp.extensions.disco.model.items.Item;
import rocks.xmpp.extensions.disco.model.items.ItemNode;
import rocks.xmpp.util.concurrent.AsyncResult;

import java.util.concurrent.TimeUnit;

public abstract class AbstractServiceTest extends AbstractTest {

    public AbstractServiceTest(XmppClient client) {
        super(client);
    }

    @Override
    public Result run() {
        ServiceDiscoveryManager manager = client.getManager(ServiceDiscoveryManager.class);

        //manually iterating over items. ignoring the faulty ones
        AsyncResult<ItemNode> items = manager.discoverItems(client.getDomain());
        try {
            for(Item item : items.getResult(10,TimeUnit.SECONDS).getItems()) {
                try {
                    for(String feature : manager.discoverInformation(item.getJid()).getResult(10, TimeUnit.SECONDS).getFeatures()) {
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

        //this is how it should be. but it throws an exeception if one of the items fails
        /*
        AsyncResult<List<Item>> result = manager.discoverServices(getNamespace());
        try {
            return result.getResult(10, TimeUnit.SECONDS).size() >= 1 ? Result.PASSED : Result.FAILED;
        } catch (Exception e) {
           return Result.FAILED;
        }
        */
    }

    public abstract String getNamespace();
}
