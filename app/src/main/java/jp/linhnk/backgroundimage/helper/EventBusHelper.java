package jp.linhnk.backgroundimage.helper;

import android.os.Handler;

import org.greenrobot.eventbus.EventBus;

public class EventBusHelper {


    public static void register(Object subscriber) {
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(subscriber)) {
            eventBus.register(subscriber);
        }
    }

    public static void unregister(Object subscriber) {
        EventBus eventBus = EventBus.getDefault();
        if (eventBus.isRegistered(subscriber)) {
            eventBus.unregister(subscriber);
        }
    }

    public static void post(Object object) {
        EventBus.getDefault().post(object);
    }

    public static void postDelay(final Object object, int delayTime) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                post(object);
            }
        }, delayTime);
    }

}
