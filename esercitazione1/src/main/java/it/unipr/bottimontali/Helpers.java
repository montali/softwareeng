package it.unipr.bottimontali;

/**
 * Helpers contains helper methods to use with the software's arrays.
 * 
 * @author      Simone Montali <simone.montali1@studenti.unipr.it>
 * @author      Filippo Botti <filippo.botti2@studenti.unipr.it>
 * 
 * @version     1.0
 * @since       1.0
 */
public class Helpers 
{
    /** 
     * This method appends a Person object to an array
     * 
     * Note: using generic types here would be great but we cannot create generic arrays
     * 
     * @param Person[] array           the array to extend
     * @param Person   elementToAppend the element we want to append
     *
     * @return Person[] the extended array
     * 
     * @since 1.0
     */
    public static Person[] appendPerson (Person[] array, Person elementToAppend)
    {
        Person [] newArray = new Person[array.length+1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = elementToAppend;
        return newArray;
    }
    /** 
     * This method appends an Activity object to an array
     * 
     * Note: using generic types here would be great but we cannot create generic arrays
     * 
     * @param Activity[] array           the array to extend
     * @param Activity   elementToAppend the element we want to append
     *
     * @return Activity[] the extended array
     * 
     * @since 1.0
     */
    public static Activity[] appendActivity (Activity[] array, Activity elementToAppend)
    {
        Activity [] newArray = new Activity[array.length+1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = elementToAppend;
        return newArray;
    }
    /** 
     * This method pops a Person object from an array
     * 
     * Note: using generic types here would be great but we cannot create generic arrays
     * 
     * @param Person[] array           the array to extend
     * @param Person   elementToDelete the element we want to delete
     *
     * @return Person[] the shortened array
     * 
     * @since 1.0
     */
    public static Person[] popPerson(Person[]array, Person elementToDelete)
    {
        Person [] newArray = new Person[array.length-1];
        int j = 0;
        for (int i=0; i<array.length;i++){
            if (!array[i].equals(elementToDelete)){
                newArray[j] = array[i];
                j++;
            }
        }
        return newArray;
    }
    /** 
     * This method pops an Activity object from an array
     * 
     * Note: using generic types here would be great but we cannot create generic arrays
     * 
     * @param Activity[] array           the array to extend
     * @param Activity   elementToDelete the element we want to delete
     *
     * @return Activity[] the shortened array
     * 
     * @since 1.0
     */
    public static Activity[] popActivity (Activity[] array, Activity elementToDelete)
    {
        Activity [] newArray = new Activity[array.length-1];
        int j = 0;
        for (int i=0; i<array.length;i++){
            if (!array[i].equals(elementToDelete)){
                newArray[j] = array[i];
                j++;
            }
        }
        return newArray;
    }
    /** 
     * This method checks if an element exists in an array
     * 
     * @param T[] array         the array to extend
     * @param T   elementToFind the element we want to find
     *
     * @return boolean whether the element was found or not
     * 
     * @since 1.0
     */
    public static <T> boolean elementExists (T[] array, T elementToFind)
    {
        boolean result = false;
        for (T indexable: array){
            if (elementToFind.equals(indexable))
                result = true;
        }
        return result;
    }
}
