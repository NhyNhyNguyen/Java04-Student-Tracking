/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.util.Properties;

/**
 *
 * @author NHI
 */
public interface ConfigurationProvider {
        Properties getConfiguration() throws Exception;

}
