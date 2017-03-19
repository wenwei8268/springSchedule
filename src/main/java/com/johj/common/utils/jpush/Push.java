package com.johj.common.utils.jpush;

//import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.List;
import java.util.Properties;
//import java.util.UUID;

import com.johj.common.utils.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import cn.jpush.api.JPushClient;
import cn.jpush.api.common.ClientConfig;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
//import cn.jpush.api.schedule.ScheduleResult;

/**
 * 
 *
 * @Description: 使用极光第三方插件进行消息推送
 *
 */
public class Push {
	
	private static Logger logger = LoggerFactory.getLogger(Push.class);

	private static final String JPUSH_PROPERTIES_PATH = "/config/properties/jpush.properties";
	
	private static String appKey = "";
	private static String masterSecret = "";
	private static Boolean apnsProduction = false;
	private static JPushClient jpushClient;
    
	public Push(){
		logger.info("jpush init......");
		
		try{
			init();
		}catch(Exception e){
			logger.error("jpush init ERROR:{}", e);
		}
		
		logger.info("GidClientManager init success......");
		
		//dom4j读取方式
/*		InputStream configFile1 = Push.class.getResourceAsStream("jpush.config.xml");
		try{
			SAXReader reader = new SAXReader();
			Document doc = reader.read(configFile1);
			Element config = doc.getRootElement();
			appKey = config.elementTextTrim("appKey");
			masterSecret = config.elementTextTrim("masterSecret");
			apnsProduction = (config.elementTextTrim("apnsProduction").equals("true"))?true:false;
		}catch(Exception e){
			e.printStackTrace();
		}*/
	}	
	
	/**
	 * 
	 * @throws APIConnectionException
	 * @Description: 初始化
	 *
	 */
	private void init() throws APIConnectionException, APIRequestException{
		
		Properties properties = PropertiesUtils.loadProperties(JPUSH_PROPERTIES_PATH);//读取jpush配置
		
		appKey = properties.getProperty("appKey");
		
		masterSecret = properties.getProperty("masterSecret");
		
		apnsProduction = (properties.getProperty("apnsProduction").equals("true"))?true:false;
	}
	
/*	public static Object push(HttpServletRequest request,HttpServletResponse resp){
		
		resp.setContentType("text/html;charset=utf-8");
		//放开下面注释的代码就可以支持跨域
		if (resp instanceof javax.servlet.http.HttpServletResponse) {
			// "*"表明允许任何地址的跨域调用，正式部署时应替换为正式地址
			((javax.servlet.http.HttpServletResponse) resp).addHeader("Access-Control-Allow-Origin", "*"); 
		}
		 

		//String registrationId = params.getString("registrationId");
		try {
			sendPushMessage(appKey, masterSecret);
		} catch (APIConnectionException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (APIRequestException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return  null;
	}*/
	
	public static void main(String[] args) throws APIConnectionException, APIRequestException {
		//Push.apnsProduction = true;
		//Push.sendPushMessage("2f739823c064196be15fda08", "b1f9f2aca2d26ef17418eefd");
	}
	

	public PushResult sendPushMessage(List<String> tagValues, String msg){
		PushResult result = null;
		try {
		
		if(tagValues == null || tagValues.size() == 0){
			throw new Exception("推送目标不能为空");
		}
		
		ClientConfig config = ClientConfig.getInstance();
		jpushClient = new JPushClient(masterSecret, appKey, 3, null, config);
		
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(Platform.all())//所有平台
               //.setAudience(Audience.registrationId("0109f68e4b0"))//推送Id，可数组
                .setAudience(Audience.tag(tagValues))//推送目标(标签)，可数组
                //公告
                .setNotification(Notification.alert(msg))//推送内容
                //聊天
/*                .setMessage(Message.newBuilder()
                        .setMsgContent("测试1")
                        .addExtra("from", "JPush")
                        .build())*///自定义消息
                .build();
	        payload.resetOptionsTimeToLive(86400);
	        payload.resetOptionsApnsProduction(apnsProduction);
	        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//Calendar nowTime = Calendar.getInstance();
			//nowTime.add(Calendar.MINUTE, 1);
			//nowTime.add(Calendar.SECOND, 10);
			//String scheduleTime =sdf.format(nowTime.getTime());
			//result = jpushClient.createSingleSchedule(UUID.randomUUID().toString().replaceAll("-", ""), scheduleTime, payload);
			result = jpushClient.sendPush(payload);
		} catch (APIConnectionException e) {
			logger.error("jpush ERROR:{}", e);
		} catch (APIRequestException e) {
			logger.error("jpush ERROR:{}", e);
		} catch (Exception e) {
			logger.error("jpush ERROR:{}", e);
		}
		return result;
    }
	
	/**
	 * 
	 * @Description: 对话消息发送
	 * @param tagValues
	 * @param msg
	 * @return
	 *
	 */
	public PushResult sendDialogMessage(List<String> tagValues, String msg){
		PushResult result = null;
		try {
			
			if(tagValues == null || tagValues.size() == 0){
				throw new Exception("推送目标不能为空");
			}
			
			ClientConfig config = ClientConfig.getInstance();
			jpushClient = new JPushClient(masterSecret, appKey, 3, null, config);
			
			PushPayload payload = PushPayload.newBuilder()
					.setPlatform(Platform.all())//所有平台
					//.setAudience(Audience.registrationId("0109f68e4b0"))//推送Id，可数组
					.setAudience(Audience.tag(tagValues))//推送目标(标签)，可数组
					//公告
					//.setNotification(Notification.alert(msg))//推送内容
					//聊天
					                .setMessage(Message.newBuilder()
                        .setMsgContent(msg)
                        //.addExtra("from", "JPush")
                        .build())//自定义消息
					.build();
			payload.resetOptionsTimeToLive(86400);
			payload.resetOptionsApnsProduction(apnsProduction);
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//Calendar nowTime = Calendar.getInstance();
			//nowTime.add(Calendar.MINUTE, 1);
			//nowTime.add(Calendar.SECOND, 10);
			//String scheduleTime =sdf.format(nowTime.getTime());
			//result = jpushClient.createSingleSchedule(UUID.randomUUID().toString().replaceAll("-", ""), scheduleTime, payload);
			result = jpushClient.sendPush(payload);
		} catch (APIConnectionException e) {
			logger.error("jpush ERROR:{}", e);
		} catch (APIRequestException e) {
			logger.error("jpush ERROR:{}", e);
		} catch (Exception e) {
			logger.error("jpush ERROR:{}", e);
		}
		return result;
	}
}
