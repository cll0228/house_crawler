package com.of.brm.mail.job;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.of.brm.mail.utils.ApiService;
import com.of.brm.mail.utils.DateUtil;
import com.of.brm.mail.utils.JsonUtil;

@Service
public class BillOrderSummaryTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillOrderSummaryTask.class);
   
    @Autowired
	public VelocityEmailService velocityEmailService;
	
	@Autowired
	private ApiService apiService;
	
	public void sendMail() {
		ResourceBundle rb = ResourceBundle.getBundle("mail");
		String acceptor = rb.getString("brm.bill.order.summary.acceptor");
		String url = rb.getString("brm.bill.order.summary.url");
		String[] mailTo = acceptor.split(",");
        Map<String, Object> map = new HashMap<>();
        
        
        String jsonResult = "";
		try {
			jsonResult = apiService.doGet(url, map);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String date = DateUtil.dateToNumber(DateUtil.getDateBefore(new Date(), 1));;
        map = JsonUtil.getMapFromJsonObjStr(jsonResult);
        date = date.substring(0, 4) + "年" + date.substring(4, 6) + "月"
				+ date.substring(6) + "日";
        String subject = "账单系统支付信息统计" + date;
        map.put("date", date);
        map.put("subject", subject);
		LOGGER.info("开始发送支付统计邮件！收件人地址= " + acceptor);
		try {
			velocityEmailService.sendEmail(subject, map, mailTo, "everydayPayVerify.vm");
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.info("发送邮件结束");
	}
}
