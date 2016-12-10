package com.example.android.posandroid.config;


import android.os.Handler;
import android.os.Message;

import java.util.Vector;

/**
 * Created by naravision on 14. 10. 30..
 */
public  class MessageHelper {
    //
    public static class ActivityType{
        public static final int MENUACTIVITY = 0;
        public static final int INGREDIENTACTIVITY = 1;
        public static final int INGREDIENTDETAILACTIVITY = 2;
    }
    public static class MessageType{
//        public static final int BOOKMARK_REFLASH = 101;
        public static final int REFLASH = 102;
    }

    //



    private static MessageHelper theInstance;
    private Vector<ActivityData> activityList;


    public static synchronized MessageHelper getInstance() {
        if(theInstance == null) {
            theInstance = new MessageHelper();
        }
        return theInstance;
    }

    public MessageHelper() {
        activityList = new Vector<ActivityData>();
    }

    public synchronized void addActivity(ActivityData activityData) {
        addActivity(activityData, true);
    }

    public synchronized void addActivity(ActivityData activityData, boolean override) {
        if(override) {
            Vector<ActivityData> willRemove = new Vector<ActivityData>();
            for(ActivityData activity : activityList) {
                if(activity.getType() == activityData.getType()) {
                    willRemove.add(activity);
                }
            }
            activityList.removeAll(willRemove);
            willRemove = null;
        }

        activityList.add(activityData);
    }

    public synchronized void removeActivity(int type) {
        ActivityData activity = getActivity(type);
        if(activity != null) {
            activityList.remove(activity);
        }
    }

    public synchronized void removeActivity(ActivityData activity) {
        if(activity != null) {
            activityList.remove(activity);
        }
    }


    public synchronized ActivityData getActivity(int type) {
        ActivityData result = null;
        if (activityList != null) {
            for(ActivityData activity : activityList) {
                if(activity.getType() == type) {
                    result = activity;
                    break;
                }
            }
        }
        return result;
    }

    public synchronized void sendMessage(int activityType, int messageType){
        sendMessage(activityType, messageType, null);
    }

    public synchronized void sendMessage(int activityType, int messageType, Object object){
        try {
            ActivityData activity = getActivity(activityType);

            if(activity != null) {
                Handler handler = activity.getHandler();
                if(handler != null) {
                    Message msg = Message.obtain(handler, messageType, object);
                    boolean isSend = handler.sendMessage(msg);
                }
            }
        }catch(Exception e) {
         //   Logger.error(LogType.MESSAGE_TAG, "sendMessage error : " + e.getMessage());
        }
    }

    public synchronized void sendMessageToAll(int messageType){
        sendMessageToAll(messageType, null);
    }

    public synchronized void sendMessageToAll(int messageType, Object object) {
        for(ActivityData activity : activityList) {
            Handler handler = activity.getHandler();
            Message msg = Message.obtain(handler, messageType, object);
            handler.sendMessage(msg);
        }
    }

    public synchronized Vector<ActivityData> getActivityList() {
        return activityList;
    }

    public synchronized void setActivityList(Vector<ActivityData> activityList) {
        this.activityList = activityList;
    }


    public void close() {
        if(activityList != null) {
            activityList.removeAllElements();
            activityList = null;
        }
        theInstance = null;
    }


    /**
     * Created by naravision on 14. 10. 30..
     */
    public static class ActivityData {
        private int type;
        private Handler handler;

        public ActivityData(int type, Handler handler) {
            this.type = type;
            this.handler = handler;
        }

        public int getType() {
            return type;
        }
        public void setType(int type) {
            this.type = type;
        }
        public Handler getHandler() {
            return handler;
        }
        public void setHandler(Handler handler) {
            this.handler = handler;
        }
    }
}