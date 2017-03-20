
package com.johj.common.utils.ftp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import com.johj.common.model.FTPConfig;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FTPClientUtils {

	private static Logger logger = LoggerFactory.getLogger(FTPClientUtils.class);

	public static final int FTP_PORT = 21;
	
	public static final String DIR_SPLIT = "/";
	

	public static void uploadFile(InputStream input, String storeFileName,FTPConfig ftpCfg) throws IOException {

		FTPClient ftp = new FTPClient();
		int replyCode = 0;
		try {
			// 连接FTP服务端
			ftp.connect(ftpCfg.getIp(), FTP_PORT);
			replyCode = ftp.getReplyCode();
			if (replyCode != FTPReply.SERVICE_READY) {
				throw new Exception("连不上FTP服务端！");
			}

			// 登陆FTP服务端
			if (!ftp.login(ftpCfg.getUsername(), ftpCfg.getPassword())) {
				throw new Exception("登陆失败!");
			}
			logger.info("登陆FTP成功!");

			// 上传文件
			// 1、切换到上传的目录
			// 2、上传
			try {
				if (null != ftpCfg.getWorkdir() && !ftpCfg.getWorkdir().equals("")) {

					int end = 0;
					if (ftpCfg.getWorkdir().charAt(0) == '/') {
						end = 1;
					}
					int begin = end;
					String curDir = "";
					while ((end = ftpCfg.getWorkdir().indexOf(DIR_SPLIT, begin)) != -1) {
						curDir = ftpCfg.getWorkdir().substring(begin, end);
						if (!ftp.changeWorkingDirectory(curDir)) {
							ftp.makeDirectory(curDir);
							ftp.changeWorkingDirectory(curDir);
						}
						begin = end + 1;
					}
					if (begin < ftpCfg.getWorkdir().length()) {
						curDir = ftpCfg.getWorkdir().substring(begin);
						if (!ftp.changeWorkingDirectory(curDir)) {
							ftp.makeDirectory(curDir);
							ftp.changeWorkingDirectory(curDir);
						}
					}
				}
			} catch (Exception ex) {
				throw new Exception("切换到指定的上传目录失败！");
			}

			ftp.setFileType(FTP.BINARY_FILE_TYPE);// 以BINARY格式传送,解决乱码问题！
			boolean uploadRt = ftp.storeFile(storeFileName, input);

			if (!uploadRt) {
				throw new Exception("上传文件失败！");
			}
			if (logger.isInfoEnabled()) {
				logger.info("上传文件成功!");
			}

		} catch (Exception e) {
			logger.error("上传文件异常：", e);
		} finally {
			// 退出登陆、断开与服务端的连接
			if (ftp.isConnected()) {
				try {
					ftp.logout();
					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}


	public static void downloadFile(OutputStream output,String fileName,FTPConfig ftpCfg) {

		FTPClient ftp = new FTPClient();
		int replyCode = 0;
		try {
			// 连接FTP服务端
			ftp.connect(ftpCfg.getIp(), FTP_PORT);
			replyCode = ftp.getReplyCode();
			if (replyCode != FTPReply.SERVICE_READY) {
				throw new Exception("连不上FTP服务端！");
			}

			// 登陆FTP服务端
			if (!ftp.login(ftpCfg.getUsername(), ftpCfg.getPassword())) {
				throw new Exception("登陆失败!");
			}
			logger.info("登陆FTP成功!");

			// 下载文件
			// 1、切换到下载目录
			// 2、下载
			if (!ftp.changeWorkingDirectory(ftpCfg.getWorkdir())) {
				throw new Exception("切换到指定目录失败！");
			}

			ftp.setFileType(FTP.BINARY_FILE_TYPE);// 以BINARY格式传送,解决乱码问题！
			boolean downloadRt = ftp.retrieveFile(fileName, output);
			if (!downloadRt) {
				throw new Exception("下载文件失败！");
			}
			if (logger.isInfoEnabled()) {
				logger.info("下载文件成功!");
			}

		} catch (Exception e) {
			logger.error("下载文件异常：", e);
			
		} finally {
			// 退出登陆、断开与服务端的连接
			if (ftp.isConnected()) {
				try {
					ftp.logout();
					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}

	/**
	 * @Description:下载到指定的流中
	 * @param input 
	 * @param fileName
	 * @param ftpCfg
	 * 
	 */
	public static InputStream downloadFile(String fileName,FTPConfig ftpCfg) {

		InputStream input = null;
		FTPClient ftp = new FTPClient();
		int replyCode = 0;
		try {
			// 连接FTP服务端
			ftp.connect(ftpCfg.getIp(), 21);
			replyCode = ftp.getReplyCode();
			if (replyCode != FTPReply.SERVICE_READY) {
				throw new Exception("连不上FTP服务端！");
			}

			// 登陆FTP服务端
			if (!ftp.login(ftpCfg.getUsername(), ftpCfg.getPassword())) {
				throw new Exception("登陆失败!");
			}
			logger.info("登陆FTP成功!");

			// 下载文件
			// 1、切换到下载目录
			// 2、下载
			if (!ftp.changeWorkingDirectory(ftpCfg.getWorkdir())) {
				throw new Exception("切换到指定目录失败！");
			}

			ftp.setFileType(FTP.BINARY_FILE_TYPE);// 以BINARY格式传送,解决乱码问题！
			input = ftp.retrieveFileStream(fileName);
			
			if (logger.isInfoEnabled()) {
				logger.info("下载文件成功!");
			}

		} catch (Exception e) {
			logger.error("下载文件异常：", e);
		} finally {
			// 退出登陆、断开与服务端的连接
			if (ftp.isConnected()) {
				try {
					ftp.logout();
					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return input;
	}	
	
	public static void main(String[] args) throws SocketException, IOException {
		FTPClient ftp = new FTPClient();
		int replyCode = 0;		
		// 连接FTP服务端
		ftp.connect("120.24.251.230", 21);
		replyCode = ftp.getReplyCode();
		if (replyCode != FTPReply.SERVICE_READY) {
			System.out.println("连不上FTP服务端！");
		}
		boolean b = ftp.login("ftpfile","RZftpfile");
		System.out.println("login:"+b);
		
		System.out.println("列出用户登录后所在目录下的目录：");
		FTPFile[] ff = ftp.listDirectories();
		for (int i = 0; i < ff.length; i++) {
			System.out.println(ff[i].getName());
		}
		
		ftp.changeWorkingDirectory("/home/ftpfile/ruizhifile");
		System.out.println("after change workdir------------------：");
		FTPFile[] ff2 = ftp.listDirectories();
		for (int i = 0; i < ff2.length; i++) {
			System.out.println(ff2[i].getName());
		}
		ftp.logout();
	}
}
