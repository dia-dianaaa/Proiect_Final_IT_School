package ObjectData;

public class ProductsSearchData {

    private String productSearch;
    private String expectedFragment;
    private String shouldFindResults;

    public String getProductSearch() {
        return productSearch;
    }

    public String getExpectedFragment() {
        return expectedFragment;
    }

    public boolean getShouldFindResults() {
        return Boolean.parseBoolean(shouldFindResults);
    }
}
