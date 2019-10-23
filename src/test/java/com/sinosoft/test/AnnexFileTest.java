package com.sinosoft.test;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.sinosoft.MyApplication;
import com.sinosoft.myspringboot.dao.upload.AnnexFileDao;
import com.sinosoft.myspringboot.pojo.upload.AnnexFile;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyApplication.class)
@SpringBootTest
@WebAppConfiguration
public class AnnexFileTest {

	@Autowired
	AnnexFileDao annexFileDao;

	/**
	 * 开始分割线
	 */
	@Before
	public void beforeTest() {
		System.out.println("beforeTest=================================");
	}

	/**
	 * 结束分割线
	 */
	@After
	public void afterTest() {
		System.out.println("afterTest=================================");
	}

	@Test
	public void testInsert() {
		AnnexFile annexFile=new AnnexFile();
		annexFile.setFdObjectid("602ce6a7632249cf878ae673d865e53c");
		annexFile.setBusinessType("维修上传的照片");
		annexFile.setDataId("dsasd");
		annexFile.setOldName("ads");
		annexFile.setNewName("da");
		annexFile.setPath("sda");
		annexFile.setCreatePerson("dsa");
		annexFile.setCreateDate(new Date());
		annexFile.setIsDel("0");
		Integer insert = annexFileDao.insert(annexFile);
		System.out.println(insert);
	}
	
	@Test
	public void testQuery() {
		AnnexFile annexFile=new AnnexFile();
		annexFile.setFdObjectid("783a71a9ad894d22a9895fbc3cbbfe00");
		AnnexFile insert = annexFileDao.query(annexFile);
		System.out.println(insert);
	}
}
