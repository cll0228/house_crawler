package com.of.brm.mail.job;

import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;


public class VelocityEmailService {
	// 发件人邮箱
	private String ADDRESSER;
	// 邮件编码格式
	private String CODEFORMATS = "utf-8";
	// 邮件bean
	private JavaMailSender mailSender;
	// Velocity模板bean
	private VelocityEngine velocityEngine;

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	/**
	 * 发送邮件
	 * 
	 * @param subject
	 *            邮件主题
	 * @param model
	 *            邮件内容
	 * @param mailTo
	 *            邮件收件人
	 * @param vmfile
	 *            Velocity模板目录
	 */
	public void sendEmail(final String subject,
			final Map<String, Object> model, final String[] mailTo,
			final String vmfile) throws Exception {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			// 注意MimeMessagePreparator接口只有这一个回调函数
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage,
						true, CODEFORMATS);
				// 这是一个生成Mime邮件简单工具，如果不使用utf-8这个，中文会出现乱码
				// 设置接收方的email地址
				message.setTo(mailTo);
				// 设置邮件主题
				message.setSubject(subject);
				// 设置发送方地址
				message.setFrom(ADDRESSER);
				// 从模板中加载要发送的内容，vmfile就是模板文件的名字
				// 注意模板中有中文要加utf-8，model中存放的是要替换模板中字段的值
				String text = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, vmfile, CODEFORMATS, model);
				// 将发送的内容赋值给MimeMessageHelper,后面的true表示内容解析成html
				message.setText(text, true);
			}
		};

		mailSender.send(preparator);// 发送邮件
	}

	public String getADDRESSER() {
		return ADDRESSER;
	}

	public void setADDRESSER(String aDDRESSER) {
		ADDRESSER = aDDRESSER;
	}

	public String getCODEFORMATS() {
		return CODEFORMATS;
	}

	public void setCODEFORMATS(String cODEFORMATS) {
		CODEFORMATS = cODEFORMATS;
	}

}