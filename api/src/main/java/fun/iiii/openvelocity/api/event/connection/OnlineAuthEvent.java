package fun.iiii.openvelocity.api.event.connection;

import com.google.common.base.Preconditions;
import com.velocitypowered.api.event.ResultedEvent;
import com.velocitypowered.api.event.annotation.AwaitingEvent;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.util.GameProfile;
import net.kyori.adventure.text.Component;

/**
 * 进行正版验证类似操作时触发.
 *
 * <p>
 *   Velocity typically fires this event asynchronously and does not wait for a response. However,
 *   it will wait for all {@link DisconnectEvent}s for every player on the proxy to fire
 *   successfully before the proxy shuts down. This event is the sole exception to the
 *   {@link AwaitingEvent} contract.
 * </p>
 */

@AwaitingEvent
public final class OnlineAuthEvent implements ResultedEvent<ResultedEvent.ComponentResult> {
  private final String userName;
  private final String serverId;
  private final String playerIp;
  private final boolean isOnline;

  private boolean success = false;
  private boolean ignoreKey = false;

  private ComponentResult result;
  private Throwable throwable;
  private Component disconnectComponent = Component.text("未知错误");
  private GameProfile gameProfile;

  /**
   * 主要构造方法.
   *
   * @param userName 玩家名称
   * @param serverId 服务器ID
   * @param playerIp 玩家IP
   * @param isOnline 是否为正版
   */
  public OnlineAuthEvent(String userName, String serverId, String playerIp, boolean isOnline) {
    this.userName = userName;
    this.serverId = serverId;
    this.playerIp = playerIp;
    this.isOnline = isOnline;
  }

  public String getPlayerIp() {
    return playerIp;
  }

  public boolean isOnline() {
    return isOnline;
  }

  public boolean isIgnoreKey() {
    return ignoreKey;
  }

  public void setIgnoreKey(boolean ignoreKey) {
    this.ignoreKey = ignoreKey;
  }

  public GameProfile getGameProfile() {
    return gameProfile;
  }

  public void setGameProfile(GameProfile gameProfile) {
    this.gameProfile = gameProfile;
  }

  public Throwable getThrowable() {
    return throwable;
  }

  public String getUserName() {
    return userName;
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
