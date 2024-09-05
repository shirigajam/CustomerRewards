package com.rewards.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rewards.service.CustomerRewardsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class CustomerRewardsServiceTest {
  
    @Autowired
    CustomerRewardsService rewardsService;
  
    /*@Test
    public void testPeople() throws Exception {
        // When
        //ResultActions actions = mockMvc.perform(get("/people"));
    }*/
}
