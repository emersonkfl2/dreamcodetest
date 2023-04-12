import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class CategoryTest {

    @Test
    public void testAddSubCategory() {
        Category root = new Category("Test1", Arrays.asList("Test kw 1", "Test kw 2"), null);
        Category homeAppliance = new Category("Test1", Arrays.asList("Test kw 1", "Test kw 2"), root);
        Category majorAppliance = new Category("Test1", Arrays.asList("Test kw 1", "Test kw 2"), homeAppliance);

        homeAppliance.addSubCategory(majorAppliance);

        assertEquals(1, homeAppliance.getSubCategory().size());
    }

    @Test
    public void testGetAllKeyWords() {
        Category root = new Category("Root", Arrays.asList("Test root kw 1", "Test root kw 2"), null);
        Category homeAppliance = new Category("Home", Arrays.asList("Test home kw 1", "Test home kw 2"), root);
        Category majorAppliance = new Category("Major", Arrays.asList("Test Major key 3", "Test Major key 4"), homeAppliance);

        List<String> expectedKeyWords = Arrays.asList("Test root kw 1", "Test root kw 2",
                "Test home kw 1", "Test home kw 2", "Test Major key 3", "Test Major key 4");

        assertFalse(majorAppliance.getAllKeyWords().containsAll(expectedKeyWords));

    }

    @Test
    public void testGetCategoryLevel() {
        Category root = new Category("Test1", Arrays.asList("Test kw 1", "Test kw 2"), null);
        Category homeAppliance = new Category("Test1", Arrays.asList("Test kw 1", "Test kw 2"), root);
        Category majorAppliance = new Category("Test1", Arrays.asList("Test kw 1", "Test kw 2"), homeAppliance);

        assertEquals(2, majorAppliance.getCategoryLevel());
    }

}
