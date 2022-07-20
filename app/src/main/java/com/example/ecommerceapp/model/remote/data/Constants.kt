package com.example.ecommerceapp.model.remote.data

object Constants {
    const val BASE_URL = "https://psmobitech.com/myshop/index.php/"
    const val BASE_IMAGE_URL = "https://psmobitech.com/myshop/images/"
    const val REGISTRATION_END_POINT = "User/register"
    const val LOGIN_END_POINT = "User/auth"
    const val CATEGORY_END_POINT = "Category"
    const val SUB_CATEGORY_LIST_END_POINT = "SubCategory?category_id="
    const val SUB_CATEGORY_PRODUCT_END_POINT = "SubCategory/products/"
    const val PRODUCT_DETAIL_END_POINT = "Product/details/"
    const val ADDRESS_END_POINT = "User/address"
    const val ADDRESS_LIST_END_POINT = "User/addresses/"
    const val ORDER_PLACE_END_POINT = "Order"
    const val ORDER_LIST_END_POINT = "Order/userOrders/"
    const val ORDER_DETAIL_END_POINT = "Order?order_id="
    const val SEARCH_END_POINT = "Product/search?query="

    const val CATEGORY_ID = "category_id"
    const val SUB_CATEGORY_ID ="subcategory_id"
    const val PRODUCT_ID = "product_id"

    const val TAG_DEV = "tag_dev"

    const val PREF_FILE_NAME = "login-details"
    const val PREF_EMAIL = "email_id"
    const val PREF_NAME = "full_name"
    const val PREF_MOBILE = "mobile_no"
    const val PREF_USER_ID = "user_id"
    const val PREF_CART = "cart"
    const val PREF_CHECKOUT_ADDRESS_TITLE = "pref_checkout_address_title"
    const val PREF_CHECKOUT_ADDRESS = "pref_checkout_address"
    const val PREF_CHECKOUT_PAYMENT = "pref_checkout_payment"

    const val PREF_CHECKOUT_FILE = "checkout-details"
}