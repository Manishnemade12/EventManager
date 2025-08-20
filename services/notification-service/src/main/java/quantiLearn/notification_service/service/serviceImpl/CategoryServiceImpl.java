package quantiLearn.notification_service.service.serviceImpl;

import org.springframework.stereotype.Service;
import quantiLearn.notification_service.entity.Category;
import quantiLearn.notification_service.exception.exceptions.CustomNotFoundException;
import quantiLearn.notification_service.repository.CategoryRepository;
import quantiLearn.notification_service.service.CategoryService;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(
            CategoryRepository repository
    ){
        this.repository=repository;
    }

    @Override
    public Category createCategory(Category category) {
        return repository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> categoryOptional=repository.findById(id);
        if(categoryOptional.isPresent()){
            return categoryOptional.get();
        }
        throw new CustomNotFoundException("Category not found");
    }

    @Override
    public List<Category> getAllCategory() {
        return repository.findAll();
    }

    @Override
    public Category updateCategory(Category category) {
        Optional<Category> categoryOptional=repository.findById(category.getId());
        if(categoryOptional.isPresent()){
            return repository.save(category);
        }
        throw new CustomNotFoundException("Category update failed!");
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> categoryOptional=repository.findById(id);
        if(categoryOptional.isPresent()){
            repository.delete(categoryOptional.get());
        }
        else {
            throw new CustomNotFoundException("Category update failed!");
        }
    }
}
