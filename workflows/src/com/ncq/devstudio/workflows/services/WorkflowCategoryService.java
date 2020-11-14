package com.ncq.devstudio.workflows.services;

import com.ncq.devstudio.workflows.beans.NcqCategory;
import com.ncq.devstudio.workflows.conf.ServerConfig;
import com.ncq.devstudio.workflows.entities.WorkflowCategory;
import com.ncq.devstudio.workflows.repositories.WorkflowCategoryRepository;
import com.ncq.devstudio.workflows.utils.FileUtils;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Aroua Souabni
 */
@Service
public class WorkflowCategoryService {

    private final WorkflowCategoryRepository categoryRepo;
    private final ServerConfig conf;

    @Autowired
    public WorkflowCategoryService(WorkflowCategoryRepository categoryRepo, ServerConfig conf) {
        this.categoryRepo = categoryRepo;
        this.conf = conf;
    }

    public WorkflowCategory addNewCategory(NcqCategory ncqCategory)
            throws IOException {
        WorkflowCategory category = new WorkflowCategory();
        Date creationDate = new Date();
        String uuid = UUID.randomUUID().toString();
        category.setUuid(uuid);
        category.setCreationDate(creationDate);
        category.setDescription(ncqCategory.getDescription());
        category.setEnabled(ncqCategory.getEnabled());
        String logoPath = FileUtils.saveFile(ncqCategory.getLogo(),
                creationDate, conf.getLogoRepo(), uuid,
                ncqCategory.getName(), ncqCategory.getLogoExtension());
        category.setLogo(ncqCategory.getLogo());
        category.setEnabled(ncqCategory.getEnabled());
        WorkflowCategory parentCategory = categoryRepo.findByUuid(ncqCategory.
                getParentCategoryUuid());
        category.setParentCategory(parentCategory);
        return category;
    }

}
