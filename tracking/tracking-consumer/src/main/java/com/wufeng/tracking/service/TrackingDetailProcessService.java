package com.wufeng.tracking.service;

public interface TrackingDetailProcessService {

    /**
     * 处理消息
     * 
     * @param messageInfo
     */
    void doProcess(String message);

    void saveData();

    void saveTrackingData();

}
