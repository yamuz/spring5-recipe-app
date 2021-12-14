package guru.springframework.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() throws Exception {
        category = new Category();
        Set h = Set.of(new Recipe());

        category.setRecipes(h);
        category.setDescription("Almaz");
    }

    @Test
    public void getDescription() {
        assertEquals("Almaz", category.getDescription());
        assertEquals(category.getRecipes().size(), 1);

    }

    @Test
    public void getRecipes() {
    }
}