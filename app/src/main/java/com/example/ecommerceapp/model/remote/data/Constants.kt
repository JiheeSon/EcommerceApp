package com.example.ecommerceapp.model.remote.data

object Constants {
    const val BASE_URL = "https://psmobitech.com/myshop/index.php/"
    const val BASE_IMAGE_URL = "https://psmobitech.com/myshop/images/"
    const val REGISTRATION_END_POINT = "User/register"
    const val LOGIN_END_POINT = "User/auth"
    const val CATEGORY_END_POINT = "Category"
    const val SUB_CATEGORY_LIST_END_POINT = "SubCategory?category_id="
    const val SUB_CATEGORY_PRODUCT_END_POINT = "SubCategory/products/"

    const val CATEGORY_ID = "category_id"
    const val SUB_CATEGORY_ID ="subcategory_id"

    const val TAG_DEV = "tag_dev"

    const val PREF_FILE_NAME = "login-details"
    const val PREF_EMAIL = "email_id"
    const val PREF_NAME = "full_name"
    const val PREF_MOBILE = "mobile_no"
    const val PREF_USER_ID = "user_id"
}