package Utilities;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class ApplicationConfigReader {

    private static Configuration config;
    private final String PROPERTIES_FILENAME    = "SERENITY-CUCUMBER-TEST-";
    private final String PROPERTIES_SUFFIX      = ".properties";
    private final String ENV_SYSTEM_PROPERTY    = "env";

    private String getPropertiesFileName () {
        return PROPERTIES_FILENAME + System.getProperty(ENV_SYSTEM_PROPERTY) + PROPERTIES_SUFFIX;
    }
    private void load () {
        Parameters params = new Parameters();

        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties()
                                .setFileName(getPropertiesFileName())
                                .setBasePath("./src/test/resources/properties")
                        );

        try {
            this.config = builder.getConfiguration();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

    }

    private ApplicationConfigReader ()  {
        if (this.config == null) this.load();
    }

    private static class ApplicationConfigReaderSingleton{
        private static final ApplicationConfigReader INSTANCE = new ApplicationConfigReader();
    }

    public static ApplicationConfigReader getInstance(){
        return ApplicationConfigReaderSingleton.INSTANCE;
    }

    public String getPropAValue () {
        return config.getString("propA.value");
    }


}
