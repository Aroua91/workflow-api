package com.ncq.devstudio.workflows.services;

import com.ncq.devstudio.workflows.beans.NcqCategory;
import com.ncq.devstudio.workflows.beans.NcqWorkflow;
import com.ncq.devstudio.workflows.conf.ServerConfig;
import com.ncq.devstudio.workflows.entities.WorkflowCategory;
import com.ncq.devstudio.workflows.repositories.WorkflowCategoryRepository;
import com.ncq.devstudio.workflows.utils.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
                creationDate, conf.getLogoRepo(), uuid, ncqCategory.
                getLogoExtension());
        category.setLogo(logoPath);
        category.setEnabled(ncqCategory.getEnabled());
        WorkflowCategory parentCategory = categoryRepo.findByUuid(ncqCategory.
                getParentCategoryUuid());
        category.setParentCategory(parentCategory);
        category = categoryRepo.save(category);
        return category;
    }

    public NcqCategory toNcqCategory(WorkflowCategory category) throws IOException {
        NcqCategory ncqCategory = new NcqCategory();
        ncqCategory.setCreationDate(category.getCreationDate());
        ncqCategory.setName(category.getName());
        ncqCategory.setDescription(category.getDescription());
        ncqCategory.setEnabled(category.getEnabled());
        ncqCategory.setLogo(FileUtils.fromFileToBase64(category.getLogo()));
        ncqCategory.setLogoExtension(FileUtils.extractFileExtension(category.getLogo()));
        ncqCategory.setName(category.getName());
        ncqCategory.setUpdateDate(category.getUpdateDate());
        ncqCategory.setUuid(category.getUuid());
        if (category.getParentCategory() != null) {
            ncqCategory.setParentCategoryUuid(category.getParentCategory().getUuid());
        }
        return ncqCategory;
    }

    public List<NcqCategory> getAllCategories() throws IOException {
        List<WorkflowCategory> categories = categoryRepo.findAll();
        List<NcqCategory> ncqCategories = new ArrayList();
        for(WorkflowCategory category : categories) {
            ncqCategories.add(toNcqCategory(category));
        }
        return ncqCategories;
    }

}
