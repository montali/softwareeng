package it.unipr.bottimontali;

public class Helpers 
{
    // Using generic types here would be great but in Java we cannot create generic arrays
    public static Person[] appendPerson (Person[] array, Person elementToAppend)
    {
        Person [] newArray = new Person[array.length+1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = elementToAppend;
        return newArray;
    }
    public static Activity[] appendActivity (Activity[] array, Activity elementToAppend)
    {
        Activity [] newArray = new Activity[array.length+1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[array.length] = elementToAppend;
        return newArray;
    }

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
