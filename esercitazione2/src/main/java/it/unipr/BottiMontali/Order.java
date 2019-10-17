package it.unipr.BottiMontali;

public class Order {

    private User orderer;
    private Wine orderedWine;
    private Integer quantity;
    private Integer year;
    private boolean shipped;


    public Order () 
    {
        this.orderer = new User ();
        this.orderedWine = new Wine();
        this.quantity = Integer.valueOf(0);
        this.year = Integer.valueOf(1900);
        this.shipped = false;
    }
    public Order (final User orderer, final Wine orderedWine, final Integer year, final Integer quantity, final boolean shipped){
        this.orderer = orderer;
        this.orderedWine = orderedWine;
        this.quantity = quantity;
        this.year = year;
        this.shipped = shipped;
    }


    public User getOrderer() {
        return this.orderer;
    }

    public void setOrderer(User orderer) {
        this.orderer = orderer;
    }

    public Wine getOrderedWine() {
        return this.orderedWine;
    }

    public void setOrderedWine(Wine orderedWine) {
        this.orderedWine = orderedWine;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public boolean isShipped() {
        return this.shipped;
    }

    public void setShipped(boolean shipped) {
        this.shipped = shipped;
    }

}