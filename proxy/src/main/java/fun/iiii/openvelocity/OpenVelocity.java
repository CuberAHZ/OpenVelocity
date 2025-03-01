package fun.iiii.openvelocity;

import com.velocitypowered.proxy.VelocityServer;
import com.velocitypowered.proxy.config.PlayerInfoForwarding;
import java.io.IOException;
import java.nio.file.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("checkstyle:MissingJavadocType")
public class OpenVelocity {
  private final VelocityServer server;
  private OpenVelocityConfig openVelocityConfig;
  private static OpenVelocity instance;
  private static final Logger logger = LogManager.getLogger("OpenVelocity");

  public static OpenVelocity getInstance() {
    return instance;
  }

  public OpenVelocity(VelocityServer server) {
    instance = this;
    this.server = server;
  }

  public OpenVelocityConfig getMixedVelocityConfig() {
    return openVelocityConfig;
  }

  public PlayerInfoForwarding getForwardingMode(String serverName) {
    PlayerInfoForwarding playerInfoForwarding = openVelocityConfig.getForwardingMode(serverName);
    return playerInfoForwarding == null ? server.getConfiguration().getPlayerInfoForwardingMode() : playerInfoForwarding;
  }

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public void start() {
    Path configPath = Path.of("openvc.toml");
    try {
      openVelocityConfig = OpenVelocityConfig.read(configPath);
    } catch (IOException e) {
      e.printStackTrace();
    }
    logger.info("如有问题请提交issue或加QQ群反馈 群号：946864759");
    logger.info("made by 未冬(QQ:2388990095)");
  }
}
