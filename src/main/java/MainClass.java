/**
 * Created by Menelaos Kotsollaris on 2016-11-24.
 *
 * Class Explanation: Contains the main method.
 */
public class MainClass
{
    public static void main(String[] args)
    {
        Product
                product =
                new Product.Builder(1299.99, Product.ProductType.FOOD)
                        .build();
        System.out.println(product.computeFinalCost(3));
        System.out.println(product);
    }
}