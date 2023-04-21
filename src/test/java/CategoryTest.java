import org.junit.Test;
import org.test.Category;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        Category homeAppliance = new Category("Home", Arrays.asList("Test home kw 3", "Test home kw 4"), root);
        Category majorAppliance = new Category("Major", Arrays.asList("Test Major key 5", "Test Major key 6"), homeAppliance);

        List<String> expectedKeyWords = Arrays.asList("Test root kw 1", "Test root kw 2",
                "Test home kw 3", "Test home kw 4", "Test Major key 5", "Test Major key 6");

        assertTrue(majorAppliance.getAllKeyWords().containsAll(expectedKeyWords));

    }

    @Test
    public void testGetCategoryLevel() {
        Category root = new Category("Test1", Arrays.asList("Test kw 1", "Test kw 2"), null);
        Category homeAppliance = new Category("Test1", Arrays.asList("Test kw 1", "Test kw 2"), root);
        Category majorAppliance = new Category("Test1", Arrays.asList("Test kw 1", "Test kw 2"), homeAppliance);

        assertEquals(2, majorAppliance.getCategoryLevel());
    }

    @Test
    public void testDontGetAllKeyWords() {
        Category root = new Category("Root", Arrays.asList("Test root kw 1", "Test root kw 2"), null);
        Category homeAppliance = new Category("Home", Arrays.asList("Test home kw 1", "Test home kw 2"), root);
        Category majorAppliance = new Category("Major", Arrays.asList("Test Major key 3", "Test Major key 4"), homeAppliance);

        List<String> expectedKeyWords = majorAppliance.getAllKeyWords();
        List<String> distinctKeyWords = expectedKeyWords.stream().distinct().collect(Collectors.toList());

        assertEquals(expectedKeyWords.size(), distinctKeyWords.size());

    }

    @Test
    public void testGetRootCategoryLevel() {
        Category root = new Category("Root", Arrays.asList("root1"), null);
        assertEquals(0, root.getCategoryLevel());
    }

    @Test
    public void testEmptyKeyWords() {
        Category root = new Category("Root", Arrays.asList("root1"), null);
        Category categoryTest = new Category("CategoryTest", Arrays.asList(), root);

        List<String> expectedKeyWord = Arrays.asList("root1");
        assertTrue(categoryTest.getAllKeyWords().containsAll(expectedKeyWord));
    }
}
