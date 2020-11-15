//package com.ncq.devstudio.worklfows.dao.test;
//
//import com.ncq.devstudio.workflows.entities.WorkflowCategory;
//import com.ncq.devstudio.workflows.repositories.WorkflowCategoryRepository;
//import java.util.List;
//import static org.junit.Assert.assertTrue;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
///**
// *
// * @author Aroua Souabni
// */
//@SpringBootApplication
//@EnableAutoConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
//@EnableJpaRepositories(basePackages = { "com.ncq.devstudio.worklfows.repositories" })
//@ComponentScan(basePackages = { "com.ncq.devstudio.worklfows" })
//@EntityScan(basePackages = { "com.ncq.devstudio.worklfows.entities" })
//@PropertySource("file:application.properties")
//public class WorkflowCategoryRepositoryTest {
//    
//    @Autowired
//    WorkflowCategoryRepository categoryRepo;
//
//    @Test
//    public void saveTest() {
//        WorkflowCategory category = new WorkflowCategory();
//        category.setDescription("some decription");
//        category.setEnabled(1);
//        category.setLogo("./logo/image.png");
//        category.setName("category name");
//        categoryRepo.save(category);
//    }
//
//    @Test
//    public void listTest() {
//        WorkflowCategory category = new WorkflowCategory();
//        category.setDescription("some decription 2");
//        category.setEnabled(2);
//        category.setLogo("./logo/image.png");
//        category.setName("category name 2");
//        categoryRepo.save(category);
//        List<WorkflowCategory> categories = categoryRepo.findAll();
//        assertTrue(categories.size() > 0);
//
//    }
//}
