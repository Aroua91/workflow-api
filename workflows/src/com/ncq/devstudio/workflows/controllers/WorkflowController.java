package com.ncq.devstudio.workflows.controllers;

import com.ncq.devstudio.workflows.beans.NcqCategory;
import com.ncq.devstudio.workflows.beans.NcqWorkflow;
import com.ncq.devstudio.workflows.beans.Response;
import com.ncq.devstudio.workflows.conf.ServerConfig;
import com.ncq.devstudio.workflows.entities.Workflow;
import com.ncq.devstudio.workflows.services.WorkflowService;
import java.io.IOException;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aroua Souabni
 */
@RestController
@RequestMapping("/workflow")
@EnableConfigurationProperties(ServerConfig.class)
public class WorkflowController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(
            WorkflowController.class);

    private final WorkflowService workflowSrv;

    @Autowired
    public WorkflowController(WorkflowService workflowSrv) {
        this.workflowSrv = workflowSrv;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Response<NcqWorkflow>> createWorkflow(
        @RequestBody(required = true) NcqWorkflow ncqWorkflow) throws IOException {

        LOGGER.info("Adding new category;");
        Workflow workflow = workflowSrv.addNewWorkflow(ncqWorkflow);
        LOGGER.info("Category added successfully!");

        Response<NcqWorkflow> r = new Response();
        r.setObject(workflowSrv.toNcqWorkflow(workflow));
        return ResponseEntity.status(HttpStatus.OK).body(r);
    }
}
