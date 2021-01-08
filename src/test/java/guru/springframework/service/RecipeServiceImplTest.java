package guru.springframework.service;

import guru.springframework.converter.RecipeCommandToRecipe;
import guru.springframework.converter.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repository.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipesData);

        Set<Recipe> actualRecipes = recipeService.getRecipes();
        assertEquals(actualRecipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }


    @Test
    public void getRecipeById() {
        final Long ID = 1L;

        Optional<Recipe> recipeOptional = Optional.of(Recipe.builder().id(ID).build());
        when(recipeRepository.findById(ID)).thenReturn(recipeOptional);

        Recipe result = recipeService.findById(ID);
        assertNotNull(result);
        assertEquals(ID, result.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test(expected = RuntimeException.class)
    public void getRecipeByIdNotFound() {
        when(recipeRepository.findById(any())).thenReturn(Optional.empty());

        recipeService.findById(1L);
    }
}