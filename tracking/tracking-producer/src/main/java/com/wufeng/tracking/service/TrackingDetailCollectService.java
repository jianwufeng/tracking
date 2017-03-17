package com.wufeng.tracking.service;

import com.wufeng.tracking.model.SyncPojo;

public interface TrackingDetailCollectService {

    /**
     * 收集消息
     * 
     * @param messageInfo
     */
    void doBatchCollect(SyncPojo pojo, String topic);

}
