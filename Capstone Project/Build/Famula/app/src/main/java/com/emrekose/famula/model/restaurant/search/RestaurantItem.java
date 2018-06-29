package com.emrekose.famula.model.restaurant.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RestaurantItem {

    @SerializedName("R")
    private ResId r;

    @SerializedName("apikey")
    private String apikey;

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    @SerializedName("location")
    private Location location;

    @SerializedName("switch_to_order_menu")
    private Integer switchToOrderMenu;

    @SerializedName("cuisines")
    private String cuisines;

    @SerializedName("average_cost_for_two")
    private Integer averageCostForTwo;

    @SerializedName("price_range")
    private Integer priceRange;

    @SerializedName("currency")
    private String currency;

    @SerializedName("offers")
    private List<Object> offers = null;

    @SerializedName("opentable_support")
    private Integer opentableSupport;

    @SerializedName("is_zomato_book_res")
    private Integer isZomatoBookRes;

    @SerializedName("mezzo_provider")
    private String mezzoProvider;

    @SerializedName("is_book_form_web_view")
    private Integer isBookFormWebView;

    @SerializedName("book_form_web_view_url")
    private String bookFormWebViewUrl;

    @SerializedName("book_again_url")
    private String bookAgainUrl;

    @SerializedName("thumb")
    private String thumb;

    @SerializedName("user_rating")
    private UserRating userRating;

    @SerializedName("photos_url")
    private String photosUrl;

    @SerializedName("menu_url")
    private String menuUrl;

    @SerializedName("featured_image")
    private String featuredImage;

    @SerializedName("has_online_delivery")
    private Integer hasOnlineDelivery;

    @SerializedName("is_delivering_now")
    private Integer isDeliveringNow;

    @SerializedName("include_bogo_offers")
    private Boolean includeBogoOffers;

    @SerializedName("deeplink")
    private String deeplink;

    @SerializedName("is_table_reservation_supported")
    private Integer isTableReservationSupported;

    @SerializedName("has_table_booking")
    private Integer hasTableBooking;

    @SerializedName("events_url")
    private String eventsUrl;

    @SerializedName("establishment_types")
    private List<Object> establishmentTypes = null;

    public ResId getR() {
        return r;
    }

    public void setR(ResId r) {
        this.r = r;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Integer getSwitchToOrderMenu() {
        return switchToOrderMenu;
    }

    public void setSwitchToOrderMenu(Integer switchToOrderMenu) {
        this.switchToOrderMenu = switchToOrderMenu;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public Integer getAverageCostForTwo() {
        return averageCostForTwo;
    }

    public void setAverageCostForTwo(Integer averageCostForTwo) {
        this.averageCostForTwo = averageCostForTwo;
    }

    public Integer getPriceRange() {
        return priceRange;
    }

    public void setPriceRange(Integer priceRange) {
        this.priceRange = priceRange;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Object> getOffers() {
        return offers;
    }

    public void setOffers(List<Object> offers) {
        this.offers = offers;
    }

    public Integer getOpentableSupport() {
        return opentableSupport;
    }

    public void setOpentableSupport(Integer opentableSupport) {
        this.opentableSupport = opentableSupport;
    }

    public Integer getIsZomatoBookRes() {
        return isZomatoBookRes;
    }

    public void setIsZomatoBookRes(Integer isZomatoBookRes) {
        this.isZomatoBookRes = isZomatoBookRes;
    }

    public String getMezzoProvider() {
        return mezzoProvider;
    }

    public void setMezzoProvider(String mezzoProvider) {
        this.mezzoProvider = mezzoProvider;
    }

    public Integer getIsBookFormWebView() {
        return isBookFormWebView;
    }

    public void setIsBookFormWebView(Integer isBookFormWebView) {
        this.isBookFormWebView = isBookFormWebView;
    }

    public String getBookFormWebViewUrl() {
        return bookFormWebViewUrl;
    }

    public void setBookFormWebViewUrl(String bookFormWebViewUrl) {
        this.bookFormWebViewUrl = bookFormWebViewUrl;
    }

    public String getBookAgainUrl() {
        return bookAgainUrl;
    }

    public void setBookAgainUrl(String bookAgainUrl) {
        this.bookAgainUrl = bookAgainUrl;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public UserRating getUserRating() {
        return userRating;
    }

    public void setUserRating(UserRating userRating) {
        this.userRating = userRating;
    }

    public String getPhotosUrl() {
        return photosUrl;
    }

    public void setPhotosUrl(String photosUrl) {
        this.photosUrl = photosUrl;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(String featuredImage) {
        this.featuredImage = featuredImage;
    }

    public Integer getHasOnlineDelivery() {
        return hasOnlineDelivery;
    }

    public void setHasOnlineDelivery(Integer hasOnlineDelivery) {
        this.hasOnlineDelivery = hasOnlineDelivery;
    }

    public Integer getIsDeliveringNow() {
        return isDeliveringNow;
    }

    public void setIsDeliveringNow(Integer isDeliveringNow) {
        this.isDeliveringNow = isDeliveringNow;
    }

    public Boolean getIncludeBogoOffers() {
        return includeBogoOffers;
    }

    public void setIncludeBogoOffers(Boolean includeBogoOffers) {
        this.includeBogoOffers = includeBogoOffers;
    }

    public String getDeeplink() {
        return deeplink;
    }

    public void setDeeplink(String deeplink) {
        this.deeplink = deeplink;
    }

    public Integer getIsTableReservationSupported() {
        return isTableReservationSupported;
    }

    public void setIsTableReservationSupported(Integer isTableReservationSupported) {
        this.isTableReservationSupported = isTableReservationSupported;
    }

    public Integer getHasTableBooking() {
        return hasTableBooking;
    }

    public void setHasTableBooking(Integer hasTableBooking) {
        this.hasTableBooking = hasTableBooking;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public void setEventsUrl(String eventsUrl) {
        this.eventsUrl = eventsUrl;
    }

    public List<Object> getEstablishmentTypes() {
        return establishmentTypes;
    }

    public void setEstablishmentTypes(List<Object> establishmentTypes) {
        this.establishmentTypes = establishmentTypes;
    }
}
