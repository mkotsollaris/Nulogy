import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Menelaos Kotsollaris on 2016-11-24.
 *
 * Class Explanation: Contains testing scenarios of the Product class' methods.
 */
public class ProductTest
{
    @Test public void computeFinalCost() throws Exception
    {
        Product
                product1 =
                new Product.Builder(1299.99, Product.ProductType.FOOD).build();
        Product
                product2 =
                new Product.Builder(5432.00, Product.ProductType.PHARMACEUTICAL)
                        .build();
        Product
                product3 =
                new Product.Builder(12456.95, Product.ProductType.OTHER)
                        .build();
        assertEquals(product1.computeFinalCost(3), 1591.58, 0);
        assertEquals(product2.computeFinalCost(1), 6199.81, 0);
        assertEquals(product3.computeFinalCost(4), 13707.63, 0);
        try
        {
            assertEquals(product3.computeFinalCost(-4), 13707.63, 0);
        }
        catch(Exception e)
        {
            assertEquals(Product.peopleNumberException, e.getMessage());
        }
    }

}