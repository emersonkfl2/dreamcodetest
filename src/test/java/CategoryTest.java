import org.junit.Test;
import org.test.domain.Category;
import org.test.service.CategoryService;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CategoryTest {

    @Test
    public void testAddSubCategory() {
        Category root = new Category("Test1", Arrays.asList("Test kw 1", "Test kw 2"), null);
        Category homeAppliance = new Category("Test1", Arrays.asList("Test kw 1", "Test kw 2"), root);
        Category majorAppliance = new Category("Test1", Arrays.asList("Test kw 1", "Test kw 2"), homeAppliance);

        CategoryService homeApplianceService = new CategoryService(homeAppliance);
        homeApplianceService.addSubCategory(majorAppliance);

        assertEquals(1, homeAppliance.getSubCategory().size());
    }

    @Test
    public void testGetAllKeyWords() {
        Category root = new Category("Root", Arrays.asList("Test root kw 1", "Test root kw 2"), null);
        Category homeAppliance = new Category("Home", Arrays.asList("Test home kw 3", "Test home kw 4"), root);
        Category majorAppliance = new Category("Major", Arrays.asList("Test Major key 5", "Test Major key 6"), homeAppliance);

        List<String> expectedKeyWords = Arrays.asList("Test root kw 1", "Test root kw 2",
                "Test home kw 3", "Test home kw 4", "Test Major key 5", "Test Major key 6");

        CategoryService majorApplianceService = new CategoryService(majorAppliance);
        assertTrue(majorApplianceService.getAllKeyWords().containsAll(expectedKeyWords));
    }

    @Test
    public void testGetCategoryLevel() {
        Category root = new Category("Test1", Arrays.asList("Test kw 1", "Test kw 2"), null);
        Category homeAppliance = new Category("Test1", Arrays.asList("Test kw 1", "Test kw 2"), root);
        Category majorAppliance = new Category("Test1", Arrays.asList("Test kw 1", "Test kw 2"), homeAppliance);

        CategoryService majorApplianceService = new CategoryService(majorAppliance);
        assertEquals(2, majorApplianceService.getCategoryLevel());
    }

    @Test
    public void testGetRootCategoryLevel() {
        Category root = new Category("Root", Arrays.asList("root1"), null);
        CategoryService rootService = new CategoryService(root);
        assertEquals(0, rootService.getCategoryLevel());
    }

    @Test
    public void testEmptyKeyWords() {
        Category root = new Category("Root", Arrays.asList("root1"), null);
        Category categoryTest = new Category("CategoryTest", Arrays.asList(), root);

        CategoryService categoryTestService = new CategoryService(categoryTest);
        List<String> expectedKeyWords = Arrays.asList("root1");

        assertTrue(categoryTestService.getAllKeyWords().containsAll(expectedKeyWords));
    }

    @Test
    public void testGetMajorCategory() {
        Category root = new Category("Root", Arrays.asList("root1"), null);
        Category homeAppliance = new Category("Home", Arrays.asList("home1", "home2"), root);
        Category majorAppliance = new Category("Major", Arrays.asList("major1", "major2"), homeAppliance);

        Category expectedMajorCategory = homeAppliance;

        assertEquals(expectedMajorCategory, majorAppliance.getMajorCategory());
    }

    @Test
    public void testRemoveSubCategory() {
        Category root = new Category("Test1", Arrays.asList("Test kw 1", "Test kw 2"), null);
        Category homeAppliance = new Category("Test1", Arrays.asList("Test kw 1", "Test kw 2"), root);
        Category majorAppliance = new Category("Test1", Arrays.asList("Test kw 1", "Test kw 2"), homeAppliance);

        CategoryService homeApplianceService = new CategoryService(homeAppliance);
        homeApplianceService.addSubCategory(majorAppliance);
        assertEquals(1, homeAppliance.getSubCategory().size());

        homeAppliance.getSubCategory().remove(majorAppliance);
        assertEquals(0, homeAppliance.getSubCategory().size());
    }

    @Test
    public void testMajorCategoryNoKeyWords() {
        Category root = new Category("Root", Arrays.asList("root1"), null);
        Category homeAppliance = new Category("Home", Arrays.asList(), root);
        Category majorAppliance = new Category("Major", Arrays.asList("major1", "major2"), homeAppliance);

        CategoryService majorApplianceService = new CategoryService(majorAppliance);
        List<String> expectedKeyWords = Arrays.asList("root1", "major1", "major2");

        assertTrue(majorApplianceService.getAllKeyWords().containsAll(expectedKeyWords));
    }


}
