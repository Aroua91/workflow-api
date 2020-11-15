package com.ncq.devstudio.workflows.services;

import com.ncq.devstudio.workflows.beans.NcqCategory;
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
 * This is workflow categories service component which contains @Service
 * annotation. This class is used to write business logic related to
 * {@link WorkflowCategory} separately.
 *
 * @author Aroua Souabni
 */
@Service
public class WorkflowCategoryService {

    private final WorkflowCategoryRepository categoryRepo;
    private final ServerConfig conf;

    /**
     * Construct {@link WorkflowCategoryService} and inject needed repositories.
     *
     * @param categoryRepo category repository
     * @param conf service configuration
     */
    @Autowired
    public WorkflowCategoryService(WorkflowCategoryRepository categoryRepo, ServerConfig conf) {
        this.categoryRepo = categoryRepo;
        this.conf = conf;
    }

    /**
     * Add new category.
     *
     * @param ncqCategory category 's information
     * @return saved category
     * @throws IOException when an error occurs while saving logo file
     */
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
                conf.getLogoRepo(), uuid, ncqCategory.getLogoExtension());
        category.setLogo(logoPath);
        category.setEnabled(ncqCategory.getEnabled());
        WorkflowCategory parentCategory = categoryRepo.findByUuid(ncqCategory.
                getParentCategoryUuid());
        category.setParentCategory(parentCategory);
        category = categoryRepo.save(category);
        return category;
    }

    /**
     * Read all categories present in the database.
     *
     * @return categories information
     * @throws IOException input output exception
     */
    public List<NcqCategory> getAllCategories() throws IOException {
        List<WorkflowCategory> categories = categoryRepo.findAll();
        List<NcqCategory> ncqCategories = new ArrayList();
        for (WorkflowCategory category : categories) {
            ncqCategories.add(toNcqCategory(category));
        }
        return ncqCategories;
    }

    /**
     * Find category by UUID. It may be {@code null}.
     *
     * @param uuid category 's UUID
     * @return workflow category or null
     */
    public WorkflowCategory getCategoryByUuid(String uuid) {
        return categoryRepo.findByUuid(uuid);
    }

    /**
     * Convert {@link WorkflowCategory} to {@link NcqCategory}.
     *
     * @param category category as saved in database
     * @return category 's information
     * @throws IOException when an error occurs while reading logo file
     */
    public static NcqCategory toNcqCategory(WorkflowCategory category) throws IOException {
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

}
