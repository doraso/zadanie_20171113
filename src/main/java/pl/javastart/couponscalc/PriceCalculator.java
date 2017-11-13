package pl.javastart.couponscalc;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

public class PriceCalculator {

    public double calculatePrice(List<Product> products, List<Coupon> coupons) {

        Locale.setDefault(new Locale("en", "US"));
        DecimalFormat df = new DecimalFormat("#.##");

        double totalPrice = 0;

        //brak produktu
        if (products == null) {
            return 0;

            //brak kuponu
        } else {
            if (coupons == null) {
                for (Product product : products) {
                    totalPrice += product.getPrice();
                }
                return totalPrice;
            }
            //1 kupon
            else {
                if (coupons.size() == 1) {
                    for (Product product : products) {
                        for (Coupon coupon : coupons) {
                            totalPrice += product.getPrice() - (product.getPrice() * coupon.getDiscountValueInPercents() / 100);
                        }
                    }
                    totalPrice = Double.valueOf(df.format(totalPrice));
                    return totalPrice;
                }
                //kod rabatowy z kategorią
                else {
                    for (Product product : products) {
                        for (Coupon coupon : coupons) {
                            if (product.getCategory().equals(coupon.getCategory())) {
                                totalPrice += product.getPrice() - (product.getPrice() * coupon.getDiscountValueInPercents() / 100);
                            }
                        }
                        return totalPrice;
                    }
                }
                return 1;
            }
        }
    }
}