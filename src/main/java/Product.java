/**
 * Created by Menelaos Kotsollaris on 2016-11-24.
 *
 * Class Explanation: The Product which contains the base price and the product
 * type.
 */
class Product
{
    //the base price
    private final double basePrice;

    //the product field
    private ProductType productType;

    //ensures non-instantiability
    private Product()
    {
        throw new AssertionError();
    }

    /**
     * Private constructor that instantiates a Product
     *
     * @param builder the {@link Builder} object.
     */
    private Product(Builder builder)
    {
        basePrice = builder.basePrice;
        productType = builder.productField;
    }

    /**
     * @return the flat markup price
     */
    private double computeFlatMarkup()
    {
        return 0.05 * basePrice;
    }

    /**
     * @param peopleNumber the number of people that need to work on the job.
     * @param flatMarkup   the flat markup price
     *
     * @return the People Markup price
     */
    private double computePeopleMarkup(int peopleNumber, double flatMarkup)
    {
        return peopleNumber * 0.012 * (flatMarkup + basePrice);
    }

    /**
     * @param flatMarkup the flat markup price
     *
     * @return the Product type markup price
     */
    private double computeProductTypeMarkup(double flatMarkup)
    {
        return productType.markup * (flatMarkup + basePrice);
    }

    /**
     * Computes the final cost by adding all the markup prices.
     *
     * @param peopleNumber the number of people that need to work on the job.
     *
     * @return the final cost after adding the markups
     */
    double computeFinalCost(int peopleNumber)
    {
        double flatMarkup = computeFlatMarkup();
        double peopleMarkup = computePeopleMarkup(peopleNumber, flatMarkup);
        double productFiledMarkup = computeProductTypeMarkup(flatMarkup);
        return (double) Math
                .round((flatMarkup + peopleMarkup + productFiledMarkup +
                        basePrice) * 100) / 100;
    }

    static class Builder
    {
        private final double basePrice;
        private final ProductType productField;

        Builder(double basePrice, ProductType productField)
        {
            this.basePrice = basePrice;
            this.productField = productField;
        }

        Product build()
        {
            return new Product(this);
        }
    }

    /**
     * The enum that contains the category of the product and its markup
     * percentile that its dependant on each case.
     *
     * TODO consider adding extra types such as "Food" with 0 percentile?
     */
    enum ProductType
    {
        PHARMACEUTICAL("Pharmaceutical", 0.075),
        FOOD("Food", 0.13),
        ELECTRONIC("Electronic", 0.02),
        OTHER("Other", 0);

        private String category;
        private double markup;

        ProductType(String type, double markup)
        {
            this.category = type;
            this.markup = markup;
        }

        String getCategory()
        {
            return category;
        }

        double getMarkup()
        {
            return markup;
        }
    }
}
