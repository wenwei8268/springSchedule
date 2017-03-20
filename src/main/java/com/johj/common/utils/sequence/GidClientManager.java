
package com.johj.common.utils.sequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import com.johj.common.utils.JsonUtils;
import com.johj.common.utils.PropertiesUtils;
import com.johj.common.utils.datatype.DoubleUtils;
import com.johj.common.utils.datatype.IntegerUtils;
import com.johj.common.utils.datatype.LongUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 * GID 客户端管理
 * 
 *
 */
public class GidClientManager {

	private static Logger logger = LoggerFactory.getLogger(GidClientManager.class);

	private static final String GID_POOL_PROPERTIES_PATH = "/config/properties/gidpool.properties";
	private static final String GID_CLENT_PROPERTIES_PATH = "/config/properties/gidclient.properties";
	
	private static String[] serverUrlArr = null;//服务端地址数组
	private static Long timeOut = null;//超时时间
	private static Long sleepTime = null;//休眠时间
	private static Double scale = null;//水位

	private static GidClientManager inst = new GidClientManager();
	private static ConcurrentMap<String, BlockingQueue<Long>> gidPoolMap;
	private static ConcurrentMap<String, GidVo> gidVoMap;

	private static final String PARAM_NAME = "?gidCode=";

	private GidClientManager() {
		logger.info("GidClientManager init......");
		try{
			init();
		}catch(Exception e){
			logger.error("GidClientManager init ERROR",e);
		}
		
		logger.info("GidClientManager init success......");
	}

	static GidClientManager getInstance() {
		return inst;
	}

	/**
	 * 初始化
	 * 
	 * @throws InterruptedException
	 * @Description: 初始化
	 *
	 */
	private void init() throws InterruptedException {
		
		//初始化GidClient
		Properties gidClientProperties = PropertiesUtils.loadProperties(GID_CLENT_PROPERTIES_PATH);//读取GidClient配置
		
		//服务端地址
		String urls = gidClientProperties.getProperty("gid.server.url");
		serverUrlArr = urls.split(",");
		
		//超时时间
		String timeOutStr = gidClientProperties.getProperty("gid.timeout");
		timeOut = timeOutStr!=null? LongUtils.valueOf(timeOutStr):1000L;
		
		//水位
		String scaleStr = gidClientProperties.getProperty("gid.scale");
		scale = DoubleUtils.valueOf(scaleStr);
				
		//休眠时间
		String sleepTimeStr = gidClientProperties.getProperty("gid.sleeptime");
		sleepTime = LongUtils.valueOf(sleepTimeStr);

		logger.info("serverUrlArr:{},timeOut:{},scale:{}",serverUrlArr,timeOut,scale);
		
		//配置GidPool
		Properties gidPoolProperties = PropertiesUtils.loadProperties(GID_POOL_PROPERTIES_PATH);//读取GidPool配置

		gidPoolMap = new ConcurrentHashMap<String, BlockingQueue<Long>>();
		gidVoMap = new  ConcurrentHashMap<String, GidVo>();

		for (Object o : gidPoolProperties.keySet()) {
			String gidCode = o.toString();
			String[] tmpArr = gidPoolProperties.getProperty(gidCode).split(",");
			Integer cacheSize = IntegerUtils.valueOf(tmpArr[0]);
			Integer increamentBy = IntegerUtils.valueOf(tmpArr[1]);

			GidVo gidVo = new GidVo();
			gidVo.setGidCode(gidCode);
			gidVo.setCacheSize(cacheSize);
			gidVo.setIncreamentBy(increamentBy);
			
			gidVoMap.put(gidCode, gidVo);
			
			BlockingQueue<Long> queue = new ArrayBlockingQueue<Long>(cacheSize*2);
			gidPoolMap.put(gidCode, queue);
			
			this.fillGidPool(gidVo, queue);
			
			FillerThread fillerThread = new FillerThread(gidVo,queue);
			Thread thread = new Thread(fillerThread,gidVo.getGidCode());
			thread.start();

		}
		
		logger.info("GidClientManager init. gid:{}", JsonUtils.toJsonString(gidVoMap));
	}
	
	/**
	 * 
	 * @Description:
	 * @param gidCode
	 * @return
	 *
	 */
	Long getGidValue(String gidCode) {
		Long gidValue = null;
		try {
			BlockingQueue<Long> queue = gidPoolMap.get(gidCode);
			if(queue!=null){
				gidValue = queue.poll(timeOut,TimeUnit.MILLISECONDS);
				if(gidValue == null){
					this.fillGidPool(gidCode, queue);
				}
			}
		} catch (Exception e) {
			logger.error("getGidValue error. gidCode:{}",gidCode,e);
			e.printStackTrace();
		}
		return gidValue;
	}
	
	/**
	 * 填充GID缓冲池
	 * @Description: 填充GID缓冲池
	 * @param gidCode
	 * @param queue
	 * @throws InterruptedException 
	 *
	 */
	private void fillGidPool(String gidCode,BlockingQueue<Long> queue) throws InterruptedException{
		GidVo gidVo = gidVoMap.get(gidCode);
		this.fillGidPool(gidVo, queue);
	}
	
	
	
	/**
	 *  填充GID缓冲池
	 * @Description:  填充GID缓冲池
	 * @param gidVo
	 * @param queue
	 * @throws InterruptedException 
	 *
	 */
	private void fillGidPool(GidVo gidVo,BlockingQueue<Long> queue) throws InterruptedException{
		Long gidValue = this.getGidFromServer(gidVo.getGidCode());
		if(gidValue!=null && gidValue>0){
			for(int i=0;i<gidVo.getCacheSize();i++){
				queue.put(gidValue);
				gidValue = gidValue + gidVo.getIncreamentBy();
			}
		}else{
			//logger.error("GID getfrom server error.gidCode:{},gidValue:{}",gidVo.getGidCode(),gidValue);
		}
	}
	
	
	/**
	 * 通过服务器获取GID
	 * 
	 * @Description: 通过服务器获取GID
	 * @param gidCode
	 * @return
	 * @throws Exception
	 *
	 */
	private Long getGidFromServer(String gidCode) {
		String[] urlArr = serverUrlArr;
		Long gidValue = null;
		for (String url : urlArr) {
			try {
				gidValue = this.getGidFromServer(url, gidCode);
				if(gidValue != null){
					break;
				}
			} catch (Exception e) {
				logger.error("Get GID from server error. serverUrl:{},gidCode:{}", url, gidCode, e);
			}
		}
		return gidValue;
	}

	/**
	 * 根据指定URL获取GID
	 * 
	 * @Description: 根据指定URL获取GID
	 * @param url
	 * @param gidCode
	 * @return
	 * @throws Exception
	 *
	 */
	private Long getGidFromServer(String url, String gidCode) throws Exception {
		// 创建HttpClient实例
		String getURL = url + PARAM_NAME + gidCode;
		String result = null;
		URL getUrl = null;
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		try {
			getUrl = new URL(getURL);
			connection = (HttpURLConnection) getUrl.openConnection();
			connection.connect();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String lines;

			while ((lines = reader.readLine()) != null) {
				result = lines;
			}
		} catch (IOException e) {
			//e.printStackTrace();
			logger.error("connect error serverUrl:{},gidCode:{}", url, gidCode, e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					//e.printStackTrace();
					logger.error("close reader error serverUrl:{},gidCode:{}", url, gidCode, e);
				}
			}

			if (connection != null) {
				connection.disconnect();
			}
		}
		Long gidValue = null;
		if (result != null) {
			gidValue = LongUtils.valueOf(result);
		}
		return gidValue;
	}

	class FillerThread implements Runnable{
		
		private boolean flag = true;
		
		private GidVo gidVo;
		private BlockingQueue<Long> queue;
		
		FillerThread(GidVo gidVo,BlockingQueue<Long> queue){
			this.gidVo = gidVo;
			this.queue = queue;
		}
		
		@Override
		public void run() {
			while(flag){
				try{
					int size = queue.size();
					if(size<gidVo.getCacheSize()*scale){
						//logger.debug("装填一次，fillGidPool,gidCode:{}",gidVo.getGidCode());
						fillGidPool(gidVo,queue);
					}else{
						//logger.debug("无需装填，noneFillGidPool,gidCode:{},size:{}",gidVo.getGidCode(),size);
					}
				}catch(Exception e){
					logger.error("发生异常,gidCode:{}",gidVo.getGidCode(),e);
				}
				try{
					Thread.sleep(sleepTime);
				}catch(Exception e){
					logger.error("休眠异常,gidCode:{}",gidVo.getGidCode(),e);
				}
			}
		}
		

	}


	class GidVo {
		private String gidCode;
		private int cacheSize;
		private int increamentBy;

		String getGidCode() {
			return gidCode;
		}

		void setGidCode(String gidCode) {
			this.gidCode = gidCode;
		}

		int getCacheSize() {
			return cacheSize;
		}

		void setCacheSize(int cacheSize) {
			this.cacheSize = cacheSize;
		}

		int getIncreamentBy() {
			return increamentBy;
		}

		void setIncreamentBy(int increamentBy) {
			this.increamentBy = increamentBy;
		}

	}
	
	public static void main(String args[]){
		GidClientManager inst = GidClientManager.getInstance();
		logger.info("gidValue:{}",inst.getGidValue("TEST_SEQ"));
	}

}



