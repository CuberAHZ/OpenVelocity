package fun.iiii.openvelocity.api.event.connection;

import com.google.common.base.Preconditions;
import com.velocitypowered.api.event.ResultedEvent;
import com.velocitypowered.api.event.annotation.AwaitingEvent;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.util.GameProfile;
import java.util.UUID;
import net.kyori.adventure.text.Component;

/**
 * 触发PreLogin后触发，用于鉴别是否为离线玩家以及进行离线的验证.
 *
 * <p>
 *   Velocity typically fires this event asynchronously and does not wait for a response. However,
 *   it will wait for all {@link DisconnectEvent}s for every player on the proxy to fire
 *   successfully before the proxy shuts down. This event is the sole exception to the
 *   {@link AwaitingEvent} contract.
 * </p>
 */

@AwaitingEvent
public final class OpenPreLoginEvent implements ResultedEvent<ResultedEvent.ComponentResult> {
  private final UUID uuid;
  private final String userName;
  private final String host;

  private boolean online = true;
  private String serverId;

  private ComponentResult result;
  private Component disconnectComponent = Component.text("未知错误");
  private GameProfile gameProfile;

  @SuppressWarnings("checkstyle:MissingJavadocMethod")
  public OpenPreLoginEvent(UUID uuid, String userName, String host) {
    this.uuid = uuid;
    this.userName = userName;
    this.host = host;
  }

  public String getHost() {
    return host;
  }

  public UUID getUuid() {
    return uuid;
  }

  public boolean isOnline() {
    return online;
  }

  public void setOnline(boolean online) {
    this.online = online;
  }

  public String getServerId() {
    return serverId;
  }

  public void setServerId(String serverId) {
    this.serverId = serverId;
  }

  public GameProfile getGameProfile() {
    return gameProfile;
  }

  public void setGameProfile(GameProfile gameProfile) {
    this.gameProfile = gameProfile;
  }

  public String getUserName() {
    return userName;
  }


  @Override
  public ComponentResult getResult() {
    return result;
  }

  public Component getDisconnectComponent() {
    return disconnectComponent;
  }

  public void setDisconnectComponent(Component disconnectComponent) {
    this.disconnectComponent = disconnectComponent;
  }

  @Override
  public void setResult(ComponentResult result) {
    this.result = Preconditions.checkNotNull(result, "result");
  }

}
