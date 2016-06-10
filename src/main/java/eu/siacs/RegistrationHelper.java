package eu.siacs;

import rocks.xmpp.addr.Jid;
import rocks.xmpp.core.XmppException;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.extensions.register.RegistrationManager;
import rocks.xmpp.extensions.register.model.Registration;

import java.math.BigInteger;
import java.security.SecureRandom;

class RegistrationHelper {
    static String register(Jid jid) throws RegistrationFailed, RegistrationNotSupported {
        XmppClient client = XmppClient.create(jid.getDomain());
        try {
            client.connect(jid);
        } catch(XmppException e) {
            throw new RegistrationFailed("unable to connect to server");
        }
        try {
            RegistrationManager registrationManager = client.getManager(RegistrationManager.class);
            if (registrationManager.isRegistrationSupported().getResult()) {
                String password = new BigInteger(64, new SecureRandom()).toString(36);
                Registration registration = Registration.builder()
                        .username(jid.getLocal())
                        .password(password)
                        .build();
                registrationManager.register(registration).getResult();
                return password;
            } else {
                throw new RegistrationNotSupported();
            }
        } catch (XmppException e) {
            throw new RegistrationFailed(e.getMessage());
        }
    }


    static class RegistrationNotSupported extends Exception {

    }

    static class RegistrationFailed extends Exception {
        RegistrationFailed(String message) {
            super(message);
        }
    }
}
