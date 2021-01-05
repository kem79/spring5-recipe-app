package guru.springframework.service;

import guru.springframework.domain.Recipe;
import guru.springframework.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Set<Recipe> getRecipes() {
        log.debug("this is my debug message with slf4j");
        Set<Recipe> result = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(result::add);
        return result;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if(!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe Not Found!");
        }
        return recipeOptional.get();
    }
}
