package org.selenium.constants;

public enum Coupon {

    FREESHIP("freeship"),
    OFFCART5("offcart5"),
    OFF25("off25");

    private final String coupon;

    public String getCoupon() {
        return coupon;
    }

    Coupon(String coupon) {
        this.coupon = coupon;
    }

}
