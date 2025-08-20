package quantiLearn.notification_service.service;

import quantiLearn.notification_service.entity.Category;

import java.util.List;

public interface CategoryService {
    Category createCategory(Category category);
    Category getCategoryById(Long id);
    List<Category> getAllCategory();
    Category updateCategory(Category category);
    void deleteCategory(Long id);
}
