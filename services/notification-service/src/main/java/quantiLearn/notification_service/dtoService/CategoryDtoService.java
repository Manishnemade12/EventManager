package quantiLearn.notification_service.dtoService;

import quantiLearn.notification_service.dto.CategoryDto;

import java.util.List;

public interface CategoryDtoService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto getCategoryById(Long id);
    List<CategoryDto> getAllCategory();
    CategoryDto updateCategory(CategoryDto categoryDto);
    void deleteCategory(Long id);
}
