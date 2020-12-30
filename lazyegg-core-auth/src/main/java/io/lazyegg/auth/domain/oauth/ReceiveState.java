package io.lazyegg.auth.domain.oauth;

/**
 * 接收state
 *
 * @author DifferentW  nsmeng3@163.com 2020/12/30 10:38 下午
 */
public interface ReceiveState {
    SendRedirectI receiveState(String state);
}
