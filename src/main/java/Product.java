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

    /** Error Message */
    static final String
            peopleNumberException =
            "peopleNumber must be equal or bigger than 0";

    /**
     * Private constructor that instantiates a Product
     *
     * @param builder the {@link Builder} object.
     */
    private Product(Builder builder)
    {
        basePrice = builder.basePrice;
        productType = builder.productType;
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
        if(peopleNumber < 0)
            throw new IllegalArgumentException(peopleNumberException);
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
        if(peopleNumber < 0)
            throw new IllegalArgumentException(peopleNumberException);
        double flatMarkup = computeFlatMarkup();
        double peopleMarkup = computePeopleMarkup(peopleNumber, flatMarkup);
        double productFiledMarkup = computeProductTypeMarkup(flatMarkup);
        return (double) Math
                .round((flatMarkup + peopleMarkup + productFiledMarkup +
                        basePrice) * 100) / 100;
    }

    static class Builder
    {
        //the base price
        private final double basePrice;
        //the product field
        private ProductType productType;

        Builder(double basePrice, ProductType productField)
        {
            this.basePrice = basePrice;
            this.productType = productField;
        }

        Product build()
        {
            return new Product(this);
        }
    }

    /**
     * The enum that contains the category of the product and its markup
     * percentage that its dependant on each case.
     */
    enum ProductType
    {
        PHARMACEUTICAL("Pharmaceutical", 0.075),
        FOOD("Food", 0.13),
        ELECTRONIC("Electronic", 0.02),
        OTHER("Other", 0);

        /** The category */
        private String category;
        /** The markup percentage */
        private double markup;

        ProductType(String category, double markup)
        {
            this.category = category;
            this.markup = markup;
        }
    }

    @Override public String toString()
    {
        return "This product has a category of: " + productType.category +
                ", a markup percentage of " + productType.markup * 100 +
                "% and a base price of: " + basePrice + " $.";
    }
}
