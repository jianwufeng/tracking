package com.wufeng.tracking.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wufeng.tracking.constants.TrackingKafkaConstant;
import com.wufeng.tracking.kafka.KafkaProducer;
import com.wufeng.tracking.kafka.KafkaProducersFactory;
import com.wufeng.tracking.model.SyncPojo;
import com.wufeng.tracking.service.TrackingDetailCollectService;
import com.wufeng.tracking.utils.MailService;

@Service(value = "trackingDetailCollectService")
public class TrackingDetailCollectServiceImpl implements TrackingDetailCollectService {

    Logger log = LoggerFactory.getLogger(this.getClass());
    private static boolean ifSendMail = true;// 判断是否发送邮件

    @Override
    public void doBatchCollect(SyncPojo pojo, String topic) {
        List<String> list = KafkaProducersFactory.msgList.get(topic);
        list.add(pojo.toString());
        log.info(topic + " 缓存数量  ： " + list.size() + "条.");
        if (list.size() >= TrackingKafkaConstant.PRODUCER_LENGTH) {
            try {
                KafkaProducer kafkaProducer = findKafkaProducer(topic);
                if (null != kafkaProducer) {

                    long startTime = System.currentTimeMillis();

                    kafkaProducer.produceBatchedMsg(list);

                    long endTime = System.currentTimeMillis();
                    double duration = (endTime - startTime) / 1000.0;
                    log.info(topic + " : 写完数据" + list.size() + "条， 用时" + duration + "秒。 ");
                    list.clear();
                    kafkaProducer.dispose();
                    KafkaProducersFactory.kafkaProducers.put(topic, null);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                try {
                    if (ifSendMail = true) {
                        ifSendMail = false;
                        MailService.submit(TrackingKafkaConstant.MAIL_TO_LIST, "tracking-collector : " + topic + " 消息发送失败！", e.getMessage());
                    }
                } catch (Exception e1) {
                    log.error(e1.getMessage());
                }
            }
        }

    }

    private KafkaProducer findKafkaProducer(String topic) {
        KafkaProducer kafkaProducer = KafkaProducersFactory.kafkaProducers.get(topic);
        if (kafkaProducer == null) {
            try {
                KafkaProducer producer = new KafkaProducer(topic);
                KafkaProducersFactory.kafkaProducers.put(topic, producer);
                ifSendMail = true;
                return producer;
            } catch (Exception e) {
                log.error(topic + " producer获取失败！");
                log.error(e.getMessage());
                try {
                    if (ifSendMail = true) {
                        ifSendMail = false;
                        MailService.submit(TrackingKafkaConstant.MAIL_TO_LIST, "tracking-collector : " + topic + " producer获取失败！", e.getMessage());
                    }
                } catch (Exception e1) {
                    log.error(e1.getMessage());
                }
            }
        }
        return kafkaProducer;
    }

    // private String getTime(Date begin, Date end) {
    //
    // if(begin == null || end ==null){
    // return "0天0小时0分0秒";
    // }
    // long l=end.getTime()-begin.getTime();
    // long day=l/(24*60*60*1000);
    // long hour=(l/(60*60*1000)-day*24);
    // long min=((l/(60*1000))-day*24*60-hour*60);
    // long s=(l/1000-day*24*60*60-hour*60*60-min*60);
    // return ""+day+"天"+hour+"小时"+min+"分"+s+"秒";
    // }

    public static void main(String[] args) {
        // try {
        // MailService.submit(TrackingKafkaConstant.MAIL_TO_LIST,"tracking-collector : "+""+" producer获取失败！","");
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date now = df.parse("2004-03-26 13:31:40");
            java.util.Date date = df.parse("2004-01-02 11:30:24");
            long l = now.getTime() - date.getTime();
            long day = l / (24 * 60 * 60 * 1000);
            long hour = (l / (60 * 60 * 1000) - day * 24);
            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            System.out.println("" + day + "天" + hour + "小时" + min + "分" + s + "秒");
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
