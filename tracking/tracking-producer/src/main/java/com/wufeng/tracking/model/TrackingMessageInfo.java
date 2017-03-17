package com.wufeng.tracking.model;

import java.io.Serializable;
import java.util.Date;

/**
 * tracking的收集／处理的中间结果ＤＴＯ
 * 
 * @author luxiaoming
 *
 */

public class TrackingMessageInfo implements SyncPojo, Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 3944929769636155697L;
    // private Integer serviceMode; //服务模式(0=local,1=remote,2=both)
    private String random; // 随机数
    private String resolution; // 分辨率
    private String operatorSystem; // 操作系统
    private String browser;// 浏览器
    private String colorDepth;// 屏幕色彩
    private String flashVersion;// flash版本
    private Integer javaEnabled;// 浏览器是否支持java
    private String device; // 设备来源：如brower,iphone,ipad,android
    private String localIp;// 本地ip

    private String url;// 当前页链接
    private String referrer; // 上一页链接
    private String cookieId; // cookie编号

    private Integer logEvent; // 日志事件，记录是页面加载还是点击触发
    private String trackerCode;// 埋点信息
    private String categroy;// 类别
    private String theme;// 主题

    private String userId; // 用户id
    private String ip; // 用户ip
    private String region;// 地域
    private String sessionId;// session的id
    // private String firstLink ;// 第一次链接
    // private Integer sessionViewNo ;// 客户访问网站的顺序，从1开始递增

    private Date trackTime;// 跟踪时间
    // private Integer stayPeriod ;// 停留时长(秒)
    private String guid;// 全局唯一id
    // private String keyword ;// 当前关键字
    // private String searchKeyword ;// 搜索关键字

    private String trackValueJson;

    private String orderId;
    private String subOrderCode;

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getOperatorSystem() {
        return operatorSystem;
    }

    public void setOperatorSystem(String operatorSystem) {
        this.operatorSystem = operatorSystem;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getColorDepth() {
        return colorDepth;
    }

    public void setColorDepth(String colorDepth) {
        this.colorDepth = colorDepth;
    }

    public String getFlashVersion() {
        return flashVersion;
    }

    public void setFlashVersion(String flashVersion) {
        this.flashVersion = flashVersion;
    }

    public Integer getJavaEnabled() {
        return javaEnabled;
    }

    public void setJavaEnabled(Integer javaEnabled) {
        this.javaEnabled = javaEnabled;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getLocalIp() {
        return localIp;
    }

    public void setLocalIp(String localIp) {
        this.localIp = localIp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getCookieId() {
        return cookieId;
    }

    public void setCookieId(String cookieId) {
        this.cookieId = cookieId;
    }

    public Integer getLogEvent() {
        return logEvent;
    }

    public void setLogEvent(Integer logEvent) {
        this.logEvent = logEvent;
    }

    public String getTrackerCode() {
        return trackerCode;
    }

    public void setTrackerCode(String trackerCode) {
        this.trackerCode = trackerCode;
    }

    public String getCategroy() {
        return categroy;
    }

    public void setCategroy(String categroy) {
        this.categroy = categroy;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Date getTrackTime() {
        return trackTime;
    }

    public void setTrackTime(Date trackTime) {
        this.trackTime = trackTime;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getTrackValueJson() {
        return trackValueJson;
    }

    public void setTrackValueJson(String trackValueJson) {
        this.trackValueJson = trackValueJson;
    }

    // public String toString() {
    // return "" + (logEvent == null ? "1" : logEvent) + '|' + TrackingMessageUtil.escapse(resolution) + '|' +
    // TrackingMessageUtil.escapse(operatorSystem)
    // + '|' + TrackingMessageUtil.escapse(browser) + '|' + TrackingMessageUtil.escapse(colorDepth) + '|' +
    // TrackingMessageUtil.escapse(flashVersion)
    // + '|' + (javaEnabled == null ? "" : javaEnabled) + '|' + TrackingMessageUtil.escapse(device) + '|' +
    // TrackingMessageUtil.escapse(localIp) + '|'
    // + TrackingMessageUtil.escapse(trackerCode) + '|' + TrackingMessageUtil.escapse(categroy) + '|' +
    // TrackingMessageUtil.escapse(theme) + '|'
    // + (userId == null ? "" : userId) + '|' + TrackingMessageUtil.escapse(ip) + '|' +
    // TrackingMessageUtil.escapse(region) + '|'
    // + TrackingMessageUtil.escapse(sessionId) + '|' + (trackTime == null ? "" : DateUtils.format(trackTime,
    // "yyyyMMddHHmmss")) + '|'
    // + TrackingMessageUtil.escapse(guid) + '|' + TrackingMessageUtil.escapse(referrer) + '|' +
    // TrackingMessageUtil.escapse(url) + '|'
    // + (cookieId == null ? "" : TrackingMessageUtil.escapse(cookieId)) + '|'
    // + (trackValueJson == null ? "" : TrackingMessageUtil.escapse(trackValueJson)) + '|'
    // // + TrackingMessageUtil.escapse(orderId) + '|'
    // + TrackingMessageUtil.escapse(subOrderCode);
    // }

    public String getSubOrderCode() {
        return subOrderCode;
    }

    public void setSubOrderCode(String subOrderCode) {
        this.subOrderCode = subOrderCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}