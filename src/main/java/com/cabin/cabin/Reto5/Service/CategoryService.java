package com.cabin.cabin.Reto5.Service;

import com.cabin.cabin.Reto5.Interfaz.Category;
import com.cabin.cabin.Reto5.Repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategorys() {
        return (List<Category>) categoryRepository.getAll();
    }

    public Optional<Category> getCategory(int id) {
        return categoryRepository.getCategory(id);
    }
    
    public Category save(Category category){
        
        if(category.getId()==null){
            return categoryRepository.save(category);
        }else {
            Optional<Category> e = categoryRepository.getCategory(category.getId());
            if (e.isPresent()){
                return category;
            }else {
                return categoryRepository.save(category);
            }
        }
    }
       public Category update(Category category){
        if(category.getId()!=null){
            Optional<Category>g= categoryRepository.getCategory(category.getId());
            if(!g.isEmpty()){
                if(category.getDescription()!=null){
                    g.get().setDescription(category.getDescription());
                }
                if(category.getName()!=null){
                    g.get().setName(category.getName());
                }
                return categoryRepository.save(g.get());
            }
        }
        return category;
    }

    public boolean deleteCategory(int id){
        Boolean d = getCategory(id).map(category -> {
            categoryRepository.delete(category);
            return true;
        }).orElse(false);
        return d;
    }
}
