package org.jht.dbTest;

import org.jht.mapper.TimeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class TimeMapperTest {
	// private TimeMapper timeMapper = new TimeMapper
	@Autowired
	private TimeMapper timeMapper;
	
	@Test
	public void testGetTime() {
		System.out.println(timeMapper.getTime());
	}
}