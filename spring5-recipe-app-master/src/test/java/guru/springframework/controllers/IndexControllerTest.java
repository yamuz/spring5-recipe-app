package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {
    @Mock
    private  RecipeService recipeService;
    @Mock
    private Model model;

    private IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test //web server get request test
    public void mockMVC() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/")).
                andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());
        //recipes.add(new Recipe()); not working as all properties are null.

        Recipe recipe2 = new Recipe();
        recipe2.setId(1L);
        recipes.add(recipe2);

        when(recipeService.getRecipes()).thenReturn(recipes); //make getRecipes return recipes

        ArgumentCaptor<Set<Recipe>> captor = ArgumentCaptor.forClass(Set.class);

        //when this is called
        String viewName = indexController.getIndexPage(model);


        //then
        assertEquals("index",  viewName);

        //captor.capture() gets recipes
        verify(model, times(1)).addAttribute(eq("recipes"), captor.capture());
        verify(recipeService, times(1)).getRecipes();//1 time

        Set<Recipe> setInController = captor.getValue();
        //assertEquals(1, setInController.size()); throws exception

        assertEquals(2, setInController.size());

    }
}