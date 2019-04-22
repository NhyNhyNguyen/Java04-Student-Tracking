/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author NHI
 */
public class ConfigurationProviderImpl implements ConfigurationProvider{
    @Override
    public Properties getConfiguration() throws SQLException, FileNotFoundException, IOException {
        final Properties props = new Properties();
        final InputStream stream = new FileInputStream(new File("configuration/jdbc-config.properties"));
        props.load(stream);
        return props;
    }
}
