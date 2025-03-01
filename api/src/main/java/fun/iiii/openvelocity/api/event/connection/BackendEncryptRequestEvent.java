package fun.iiii.openvelocity.api.event.connection;

import com.google.common.base.Preconditions;
import com.velocitypowered.api.event.ResultedEvent;
import com.velocitypowered.api.event.annotation.AwaitingEvent;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.util.GameProfile;
import net.kyori.adventure.text.Component;

/**
 * 后端服务器请求加密时触发.
 *
 * <p>
 *   Velocity typically fires this event asynchronously and does not wait for a response. However,
 *   it will wait for all {@link DisconnectEvent}s for every player on the proxy to fire
 *   successfully before the proxy shuts down. This event is the sole exception to the
 *   {@link AwaitingEvent} contract.
 * </p>
 */

@AwaitingEvent
public final class BackendEncryptRequestEvent implements ResultedEvent<ResultedEvent.ComponentResult> {
  private final String serverName;
  private final String serverId;
  private final GameProfile gameProfile;

  private boolean success = false;

  private ComponentResult result;
  private Throwable throwable;
  private Component disconnectComponent = Component.text("未知错误");

  /**
   * 主要构造方法.
   *
   * @param serverName 服务器名称
   * @param serverId 服务器ID
   * @param gameProfile 玩家信息
   */
  public BackendEncryptRequestEvent(String serverName, String serverId, GameProfile gameProfile) {
    this.serverName = serverName;
    this.serverId = serverId;
    this.gameProfile = gameProfile;
  }

  public String getServerName() {
    return serverName;
  }

  public GameProfile getGameProfile() {
    return gameProfile;
  }

  public Throwable getThrowable() {
    return throwable;
  }

  public String getServerId() {
    return serverId;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public void setThrowable(Throwable throwable) {
    this.throwable = throwable;
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
