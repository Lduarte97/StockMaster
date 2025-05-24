/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author lldua
 */
public class MantenhaConectado {
    private static final String CONFIG_FILE = "config.properties";

    public static void salvarLogin(String user, String password) {
        try (FileOutputStream fos = new FileOutputStream(CONFIG_FILE)) {
            Properties prop = new Properties();
            prop.setProperty("usuario", user);
            prop.setProperty("senha", password);
            prop.store(fos, "Configurações do usuário");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] carregarLogin() {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            prop.load(fis);
            String user = prop.getProperty("usuario");
            String password = prop.getProperty("senha");
            if (user != null && password != null) {
                return new String[]{user, password};
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void limparLogin() {
        new File(CONFIG_FILE).delete();
    }
}
