package com.johj.common.model;

public class FTPConfig {
	private String ip;// IP

	private String username;// 用户

	private String password;// 用户密码

	private String workdir;// 文件上传下载的目录

	public FTPConfig(){
		
	}
	
	public FTPConfig(String ip, String username, String password, String workdir) {
		super();
		this.ip = ip;
		this.username = username;
		this.password = password;
		this.workdir = workdir;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getWorkdir() {
		return workdir;
	}

	public void setWorkdir(String workdir) {
		this.workdir = workdir;
	}
	
}
