package com.makingdevs.repositories;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.makingdevs.config.PersistenceConfig;
import com.makingdevs.model.Project;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations = { "classpath:/PersistenceAppCtx.xml" })
@ContextConfiguration(classes = { PersistenceConfig.class })
public class ProjectRepositoryTests {

  @Autowired
  ProjectRepository projectRepository;

  @Test
  public void test() {
    Project p = new Project();
    p.setCodeName("CODE");
    p.setDateCreated(new Date());
    p.setLastUpdated(new Date());
    p.setDescription("Descripcion");
    p.setName("Proyecto");
    projectRepository.save(p);
    Assert.assertTrue(p.getId() > 0L);
  }
}
