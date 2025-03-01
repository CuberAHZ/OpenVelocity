package fun.iiii.openvelocity;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.UnmodifiableConfig;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.google.common.collect.ImmutableMap;
import com.velocitypowered.proxy.config.PlayerInfoForwarding;
import com.velocitypowered.proxy.config.VelocityConfiguration;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("checkstyle:MissingJavadocType")
public class OpenVelocityConfig {

  private final ForwardingModes forwardingModes;

  private OpenVelocityConfig(ForwardingModes forwardingModes) {
    this.forwardingModes = forwardingModes;
  }

  public PlayerInfoForwarding getForwardingMode(String serverName) {
    return forwardingModes.modes.get(serverName);
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public static OpenVelocityConfig read(Path path) throws IOException {
    URL defaultConfigLocation = VelocityConfiguration.class.getClassLoader()
        .getResource("default-openvc.toml");
    if (defaultConfigLocation == null) {
      throw new RuntimeException("Default configuration file does not exist.");
    }

    try (final CommentedFileConfig config = CommentedFileConfig.builder(path)
        .defaultData(defaultConfigLocation)
        .autosave()
        .preserveInsertionOrder()
        .sync()
        .build()
    ) {
      config.load();
      final CommentedConfig forwardingModesConfig = config.get("server-forwarding-mode");
      ForwardingModes modes = new ForwardingModes(forwardingModesConfig);
      return new OpenVelocityConfig(modes);

    }
  }


  private static class ForwardingModes {

    private Map<String, PlayerInfoForwarding> modes = ImmutableMap.of(
        "login", PlayerInfoForwarding.MODERN
    );

    private ForwardingModes() {
    }

    private ForwardingModes(CommentedConfig config) {
      if (config != null) {
        Map<String, PlayerInfoForwarding> servers = new HashMap<>();
        for (UnmodifiableConfig.Entry entry : config.entrySet()) {
          if (entry.getValue() instanceof String) {
            servers.put(cleanServerName(entry.getKey()), PlayerInfoForwarding.valueOf(((String) entry.getValue()).toUpperCase()));
          }
        }
        this.modes = ImmutableMap.copyOf(servers);
      }
    }

    private ForwardingModes(Map<String, PlayerInfoForwarding> modes) {
      this.modes = modes;
    }

    private Map<String, PlayerInfoForwarding> getModes() {
      return modes;
    }

    public void setModes(Map<String, PlayerInfoForwarding> modes) {
      this.modes = modes;
    }

    private String cleanServerName(String name) {
      return name.replace("\"", "");
    }
  }

}
