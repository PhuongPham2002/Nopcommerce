package api;

import org.testng.annotations.Test;

public class SearchTests {
    private final SearchFeature searchSteps;

    public SearchTests() {
        this.searchSteps = new SearchFeature();
    }

    @Test(testName = "Check with data valid")
    public void checkWithDataValid() {
        searchSteps.searchProductById();
    }

    @Test(testName = "Wrong endpoint")
    public void checkWithDataInvalid() {
        searchSteps.wrongEndpoint();
    }

}
