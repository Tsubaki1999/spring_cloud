package com.stopbullying.community.democommunity;

import com.stopbullying.community.democommunity.config.AlphaConfig;
import com.stopbullying.community.democommunity.dao.AlphaDao;
import com.stopbullying.community.democommunity.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes=DemocommunityApplication.class)
class DemocommunityApplicationTests implements ApplicationContextAware {

    private ApplicationContext applicationContext;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
          this.applicationContext=applicationContext;
	}
	@Test  //Dao的测试类
	public void testApplicationContext(){
		System.out.println(applicationContext);
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);    //测试类中依赖接口，替换技术时可以不用修改测试类
		System.out.println(alphaDao.select());
		alphaDao = applicationContext.getBean("AlphaMybatis",AlphaDao.class);   //通过命名方式调用
		System.out.println(alphaDao.select());
	}

	@Test  //Service的测试类
	public void testBeanManagement(){
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);

		alphaService = applicationContext.getBean(AlphaService.class);  //发送第二次请求
		System.out.println(alphaService);
	}

	@Test  //config即配置的测试类
	public void testBeanConfig() {
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	@Autowired  //将AlphaDao主动注入到alphaDao这个属性中
	@Qualifier("AlphaMybatis")
	private AlphaDao alphaDao;
	@Autowired
	private AlphaService alphaService;
	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Test  //测试依赖注入
	public void testDI(){
		System.out.println(alphaDao);
		System.out.println(alphaService);
		System.out.println(simpleDateFormat);
	}

}
