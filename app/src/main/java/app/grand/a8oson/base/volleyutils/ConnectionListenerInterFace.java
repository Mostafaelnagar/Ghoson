package app.grand.a8oson.base.volleyutils;

/**
 * Created by mohamedatef on 12/16/18.
 */

public interface ConnectionListenerInterFace {
    void onRequestSuccess(Object response);
    void onRequestError(Object error);
}
