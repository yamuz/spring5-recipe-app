package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.exceptions.NotFoundException;

import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long l) throws NotFoundException;

    RecipeCommand findCommandById(Long l) throws NotFoundException;

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    void deleteById(Long idToDelete);
}
