package com.bottimontali;

/**
 * Order describes a wine order object.
 * 
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */


public class Order {

    private User orderer;
    private Wine orderedWine;
    private Integer quantity;
    private Integer year;
    private boolean shipped;


    /**
     * This constructor generates an empty order.
     * 
     */
    public Order () 
    {
        this.orderer = new User ();
        this.orderedWine = new Wine();
        this.quantity = Integer.valueOf(0);
        this.year = Integer.valueOf(1900);
        this.shipped = false;
    }
    /**
     * This constructor generates an order from its data.
     * 
     * @param orderer
     * @param orderedWine
     * @param year
     * @param quantity
     * @param shipped
     */
    public Order (final User orderer, final Wine orderedWine, final Integer year, final Integer quantity, final boolean shipped){
        this.orderer = orderer;
        this.orderedWine = orderedWine;
        this.quantity = quantity;
        this.year = year;
        this.shipped = shipped;
    }


    /**
     * This method returns the user that ordered the wine.
     * 
     * @return the user
     */
    public User getOrderer() {
        return this.orderer;
    }

    /**
     * This method sets the user that ordered the wine.
     * 
     * @param orderer
     */
    public void setOrderer(User orderer) {
        this.orderer = orderer;
    }

    /**
     * This method returns the ordered wine object.
     * 
     * @return the wine
     */
    public Wine getOrderedWine() {
        return this.orderedWine;
    }

    /**
     * This method sets the ordered wine.
     * 
     * @param orderedWine
     */
    public void setOrderedWine(Wine orderedWine) {
        this.orderedWine = orderedWine;
    }

    /**
     * This method gets the ordered quantity.
     * 
     * @return the quantity
     */
    public Integer getQuantity() {
        return this.quantity;
    }

    /**
     * This method sets a quantity for the order.
     * 
     * @param quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * This method gets the ordered year.
     * 
     * @return the year
     */
    public Integer getYear() {
        return this.year;
    }

    /**
     * This method sets the ordered year
     * 
     * @param year
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * This method returns whether the order was shipped.
     * 
     * @return whether the order is shipped
     */
    public boolean isShipped() {
        return this.shipped;
    }

    /**
     * This method sets whether the order was shipped or not
     * 
     * @param shipped
     */
    public void setShipped(boolean shipped) {
        this.shipped = shipped;
    }

}