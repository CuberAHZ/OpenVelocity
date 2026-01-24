/*
 * Copyright (C) 2025 Velocity Contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
