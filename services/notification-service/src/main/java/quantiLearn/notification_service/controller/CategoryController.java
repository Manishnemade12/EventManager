package quantiLearn.notification_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quantiLearn.notification_service.dto.CategoryDto;
import quantiLearn.notification_service.dtoService.CategoryDtoService;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {

    private final CategoryDtoService service;

    public CategoryController(
            CategoryDtoService service
    ){
        this.service=service;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(service.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id){
        return  new ResponseEntity<>(service.getCategoryById(id),HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        return new ResponseEntity<>(service.getAllCategory(),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto){
        return new ResponseEntity<>(service.updateCategory(categoryDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id){
        service.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
