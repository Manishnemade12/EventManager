package quantiLearn.notification_service.mapper;

import org.mapstruct.Mapper;
import quantiLearn.notification_service.dto.CategoryDto;
import quantiLearn.notification_service.entity.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    Category fromDto(CategoryDto categoryDto);
}
