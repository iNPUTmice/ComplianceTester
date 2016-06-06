package eu.siacs;

import rocks.xmpp.addr.Jid;

import java.io.*;
import java.util.Properties;

public class AccountStore {

    private static final File FILE = new File("accounts.xml");

    public static String getPassword(Jid jid) {
        Properties properties = new Properties();
        try {
            properties.loadFromXML(new FileInputStream(FILE));
            return properties.getProperty(jid.asBareJid().toString());
        } catch (IOException e) {
            return null;
        }
    }

    public static boolean storePassword(Jid jid, String password) {
        Properties properties = new Properties();
        try {
            properties.loadFromXML(new FileInputStream(FILE));
        } catch (FileNotFoundException e) {
            try {
                FILE.createNewFile();
            } catch (IOException e1) {
                //
            }
        } catch (IOException e) {
            //ignored
        }
        properties.put(jid.asBareJid().toString(),password);
        try {
            properties.storeToXML(new FileOutputStream(FILE),null);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
