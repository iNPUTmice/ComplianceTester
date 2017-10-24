package eu.siacs.compliance.tests;

import eu.siacs.compliance.Result;
import eu.siacs.utils.TestUtils;
import rocks.xmpp.core.XmppException;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.extensions.disco.ServiceDiscoveryManager;
import rocks.xmpp.extensions.muc.ChatRoom;
import rocks.xmpp.extensions.muc.ChatService;
import rocks.xmpp.extensions.muc.MultiUserChatManager;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MamMuc extends AbstractTest {
    public MamMuc(XmppClient client) {
        super(client);
    }

    @Override
    public Result run() {
        final ServiceDiscoveryManager serviceDiscoveryManager = client.getManager(ServiceDiscoveryManager.class);
        final MultiUserChatManager multiUserChatManager = client.getManager(MultiUserChatManager.class);
        try {
            List<ChatService> chatServices = multiUserChatManager.discoverChatServices().getResult();
            if (chatServices.size() < 1) {
                return Result.FAILED;
            }
            ChatService chatService = chatServices.get(0);
            ChatRoom room = chatService.createRoom(UUID.randomUUID().toString());
            room.enter("test");
            final Set<String> features = serviceDiscoveryManager.discoverInformation(room.getAddress()).getResult().getFeatures();
            final boolean mam = TestUtils.hasAnyone(MAM.NAMESPACES,features);
            room.destroy().getResult();
            return mam ? Result.PASSED : Result.FAILED;
        } catch (XmppException e) {
            return Result.FAILED;
        }
    }

    @Override
    public String getName() {
        return "XEP-0313: Message Archive Management (MUC)";
    }
}
