package com.wufeng.tracking.utils;

import java.io.InputStream;
import java.util.Vector;

/**
* <p>Title: MailInfo.java</p>
* <p>Description: </p>
* @author yurui.zeng
* @date 2014年3月21日下午2:36:34
* @version 1.0
*/
public class MailInfo {
	// 发�?邮件的邮箱地�?��务器
	private String host;
	// 这个是你的邮箱用户名以及邮件显示发�?者邮�?
	private String sendEmail;
	// 你的邮箱密码
	private String sendPassword;
	// 邮件发�?者显示姓�?
	private String sendName;
	// 待接收的邮箱
	private String mail_to;
	// 邮件标题
	private String mail_subject;
	// 邮件内容
	private String mail_body;
	// 用于保存发�?附件的文件名的集�?
	private Vector<String> file;
	
	// 用于发�?附件的excel
	private InputStream excel;
	private String excelFileName;

	/**
	 * 建立发邮件实体时传入发件人的email信息
	 * 
	 * @param sendEmail
	 *            email地址
	 * @param sendPassword
	 *            email密码
	 * @param sendName
	 *            发�?邮件中显示发件人姓名
	 */
	public MailInfo() {	}

	/**
	 * 获得发�?邮件的邮箱地�?��务器
	 * 
	 * @return 地址服务�?
	 */
	public String getHost() {
		return host;
	}

	/**
	 * 设置发�?邮件的邮箱地�?��务器
	 * 
	 * @param host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * 设置发件人的email
	 * 
	 * @param sendEmail
	 *            email
	 */
	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}

	/**
	 * 得到发件人的email
	 * 
	 * @return sendEmail email
	 */
	public String getSendEmail() {
		return sendEmail;
	}

	/**
	 * 设置发件人的email密码
	 * 
	 * @param sendPassword
	 *            密码
	 */
	public void setSendPassword(String sendPassword) {
		this.sendPassword = sendPassword;
	}

	/**
	 * 得到发件人的email密码
	 * 
	 * @return sendPassword 密码
	 */
	public String getSendPassword() {
		return sendPassword;
	}

	/**
	 * 得到邮件中显示的发件人姓�?
	 * 
	 * @return 发件人姓�?
	 */
	public String getSendName() {
		return sendName;
	}

	/**
	 * 设置邮件中显示的发件人姓�?
	 * 
	 * @param sendName
	 *            发件人姓�?
	 */
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	/**
	 * 得到收件人email
	 * 
	 * @return 收件人email
	 */
	public String getMail_to() {
		return mail_to;
	}

	/**
	 * 设置收件人email
	 * 
	 * @param mail_to
	 *            收件人email
	 */
	public void setMail_to(String mail_to) {
		this.mail_to = mail_to;
	}

	/**
	 * 邮件标题
	 * 
	 * @return 邮件标题
	 */
	public String getMail_subject() {
		return mail_subject;
	}

	/**
	 * 设置邮件标题
	 * 
	 * @param mail_subject
	 *            邮件标题
	 */
	public void setMail_subject(String mail_subject) {
		this.mail_subject = mail_subject;
	}

	/**
	 * 得到邮件内容
	 * 
	 * @return 邮件内容
	 */
	public String getMail_body() {
		return mail_body;
	}

	/**
	 * 设置邮件内容
	 * 
	 * @param mail_body
	 *            邮件内容
	 */
	public void setMail_body(String mail_body) {
		this.mail_body = mail_body;
	}

	/**
	 * 得到附件�?��的绝对路�?
	 * 
	 * @return 绝对路径
	 */
	public Vector<String> getFile() {
		return file;
	}

	/**
	 * 天剑附件�?��的绝对路�?
	 * 
	 * @return 绝对路径
	 */
	public void setFile(Vector<String> file) {
		this.file = file;
	}

	public InputStream getExcel() {
		return excel;
	}

	public void setExcel(InputStream excel) {
		this.excel = excel;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}
	
	
}
