package quantiLearn.notification_service.dtoService.dtoServiceImpl;

import org.springframework.stereotype.Service;
import quantiLearn.notification_service.dto.CategoryDto;
import quantiLearn.notification_service.dtoService.CategoryDtoService;
import quantiLearn.notification_service.mapper.CategoryMapper;
import quantiLearn.notification_service.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryDtoServiceImpl implements CategoryDtoService {

    private final CategoryService service;
    private final CategoryMapper mapper;

    public CategoryDtoServiceImpl(
            CategoryService service,
            CategoryMapper mapper
    ){
        this.service=service;
        this.mapper=mapper;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        categoryDto.setId(null);
        return mapper.toDto(service.createCategory(mapper.fromDto(categoryDto)));
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        return mapper.toDto(service.getCategoryById(id));
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        return service.getAllCategory().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        return mapper.toDto(service.createCategory(mapper.fromDto(categoryDto)));
    }

    @Override
    public void deleteCategory(Long id) {
        service.deleteCategory(id);
    }
}
