package util;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/local.properties"})
public interface Configuration extends Config {
    @Key("url")
    String getUrl();

    @Key("path")
    String getPath();
}
