package com.of.brm.mail.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

public class SendMail2 {
    private static Logger LOGGER = LoggerFactory.getLogger(SendMail2.class);

    /**
     * 发视邮件
     *
     * @param host    // smtp服务器
     * @param user    // 用户名
     * @param pwd     // 密码
     * @param to      // 收件人地址
     * @param affixs  // 附件
     * @param subject // 邮件标题
     */
    public static void send(String host, String user, String pwd, String to, String cc, String bcc,
                            List<File> affixs, String subject, String text) throws MessagingException {

        Properties props = new Properties();

        // 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
        props.put("mail.smtp.host", host);
        // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
        props.put("mail.smtp.auth", "true");

        // 用刚刚设置好的props对象构建一个session
        Session session = Session.getDefaultInstance(props);

        // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
        // 用（你可以在控制台（console)上看到发送邮件的过程）
        session.setDebug(true);

        // 用session为参数定义消息对象
        MimeMessage message = new MimeMessage(session);
        // 加载发件人地址
        message.setFrom(new InternetAddress(user));
        // 加载收件人地址
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        if (null != cc && cc.trim().length() > 0) {
            String[] ccArr = cc.trim().split(",");
            InternetAddress[] ccIa = new InternetAddress[ccArr.length];
            for (int i = 0; i < ccArr.length; i++) {
                ccIa[i] = new InternetAddress(ccArr[i]);
            }
            message.addRecipients(Message.RecipientType.CC, ccIa);
        }

        if (null != bcc && bcc.trim().length() > 0) {
            String[] bccArr = bcc.trim().split(",");
            InternetAddress[] bccIa = new InternetAddress[bccArr.length];
            for (int i = 0; i < bccArr.length; i++) {
                bccIa[i] = new InternetAddress(bccArr[i]);
            }
            message.addRecipients(Message.RecipientType.BCC, bccIa);
        }

        // 加载标题
        message.setSubject(subject);

        // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
        Multipart multipart = new MimeMultipart();


        int year = 0;
        int month = 0;
        int day = 0;
        Calendar c = Calendar.getInstance();//获得系统当前日期
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH) + 1;//系统日期从0开始算起
        day = c.get(Calendar.DAY_OF_MONTH);

//			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//			Date date = df.parse(df.format(new Date()));
//			//向数据库插入起始时间并返回id
//			EntityPlatformMail mailList = new EntityPlatformMail();
//			mailList.setStartTime(date);
//			tablePlatformMapper.insertPlatformMail(mailList);
//			// 设置邮件的文本内容
//			System.out.println(mailList);
        BodyPart contentPart = new MimeBodyPart();
        contentPart.setText(text);
        multipart.addBodyPart(contentPart);
        sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
        //添加附件
        for (File affix : affixs) {
            try {
                String fileNameUtf = affix.getName();
                String fileName = URLDecoder.decode(fileNameUtf, "UTF-8");
                String fileNameBase64 = enc.encode(fileName.getBytes("UTF-8"));
                LOGGER.info("Add File to mail, fileName(UTF-8): " + fileNameUtf + ", fileName: " + fileName + ", fileName(Base64): " + fileNameBase64);
                BodyPart messageBodyPart = new MimeBodyPart();
                DataSource source = new FileDataSource(affix);
                //添加附件的内容
                messageBodyPart.setDataHandler(new DataHandler(source));
                //添加附件的标题
                //这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
                messageBodyPart.setFileName("=?UTF-8?B?" + fileNameBase64 + "?=");
                multipart.addBodyPart(messageBodyPart);
            } catch (UnsupportedEncodingException e) {
                LOGGER.warn("Add file part error.", e);
            }
        }

        // 将multipart对象放到message中
        message.setContent(multipart);
        // 保存邮件
        message.saveChanges();
        // 发送邮件
        Transport transport = session.getTransport("smtp");
        // 连接服务器的邮箱
        transport.connect(host, user, pwd);
        // 把邮件发送出去
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();

    }

    public static void main(String[] args) throws MessagingException {
        SendMail2 cn = new SendMail2();
        //String host, String user, String pwd, String to, String affix, String affixName, String subject
        // 设置smtp服务器以及邮箱的帐号和密码
        List<File> list = new ArrayList<>();
        list.add(new File("X:\\export\\生活缴费财务收款_20160119.xlsx"));
        list.add(new File("E:\\work\\东方金融系统\\账单中心\\销账数据样例.txt"));
//        list.add("导出对账文件.txt");
        cn.send("smtp.mxhichina.com", "of-bill@oriental-finance.com", "DFJR2@15X", "jiangcq@oriental-finance.com",
                "jiangcq@oriental-finance.com,jiangcq@oriental-finance.com", "jiangcq@oriental-finance.com,jiangcq@oriental-finance.com",
                list, "-生活缴费财务收款-", "生活缴费财务收款报告");
    }

}