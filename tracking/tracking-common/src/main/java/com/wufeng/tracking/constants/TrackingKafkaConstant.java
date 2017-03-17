package com.wufeng.tracking.constants;

/**
 * tracking系统kafka相关的常量
 * 
 * @author luxiaoming
 *
 */
public interface TrackingKafkaConstant {
    public static final String TOPIC_PREFIX = "tracking-detail-";// topic名前缀

    public static final String STRING_ENCODER = "kafka.serializer.StringEncoder";// kafka消息编码器

    public static final Integer PRODUCER_LENGTH = 1;// 批量写入
    // 异常邮件接收者
    // public static final String MAIL_TO_LIST="jinyishu@meitunmama.com"; //测试环境
    public static final String MAIL_TO_LIST = "bigdata@meitunmama.com"; // 生产环境

}
