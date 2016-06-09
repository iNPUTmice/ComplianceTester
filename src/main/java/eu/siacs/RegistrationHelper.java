package eu.siacs;

import rocks.xmpp.addr.Jid;
import rocks.xmpp.core.XmppException;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.extensions.register.RegistrationManager;
import rocks.xmpp.extensions.register.model.Registration;

import java.math.BigInteger;
import java.security.SecureRandom;

public class RegistrationHelper {
    public static String register(Jid jid) throws RegistrationFailed, RegistrationNotSupported {
        XmppClient client = XmppClient.create(jid.getDomain());
        try {
            client.connect(jid);
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
            throw new RegistrationFailed();
        }
    }

    private abstract static class RegistrationException extends Exception {

    }

    public static class RegistrationNotSupported extends RegistrationException {

    }

    public static class RegistrationFailed extends RegistrationException {

    }
}
