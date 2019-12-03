import jdk.javadoc.internal.doclets.formats.html.markup.Head;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class RandomDataGenerator {

    public static Person generatePerson (Headquarter hq){
        ArrayList<String> names = new ArrayList<String>(Arrays.asList(
                "ANDREA", "FRANCESCO", "MARIA", "MARCO", "GIUSEPPE", "LUCA", "ALESSANDRO",
                "ANNA", "ANTONIO", "GIOVANNI", "FRANCESCA", "PAOLO", "STEFANO", "ROBERTO",
                "MATTEO", "ELENA", "DAVIDE", "GIULIA", "LAURA", "SARA", "PAOLA", "CHIARA",
                "LUIGI", "LORENZO", "DANIELA", "DANIELE", "MASSIMO", "ALBERTO", "SALVATORE",
                "SILVIA", "SIMONE", "FRANCO", "GABRIELE", "RICCARDO", "CLAUDIO", "GIORGIO",
                "FEDERICO", "VALENTINA", "MONICA", "LUCIA", "ELISA", "CRISTINA", "FEDERICA",
                "FABIO", "GIOVANNA", "VINCENZO", "PATRIZIA", "MARIO", "GIUSEPPINA", "PIETRO"));
        ArrayList<String> surnames = new ArrayList<String>(Arrays.asList(
                "ROSSI","MONTANARI","FERRETTI","DAVOLI","CATELLANI","BONACINI","IOTTI","MENOZZI",
                "CORRADINI","IORI","FONTANESI","BARBIERI","BERTANI","MAGNANI","SPAGGIARI","SASSI",
                "BIGI","BERTOLINI","GUIDETTI","BORGHI","SALSI","FORNACIARI","BRAGLIA","ORLANDINI",
                "PRANDI","VEZZANI","BARTOLI","BONI","VECCHI","GRASSI","ESPOSITO","SONCINI",
                "CODELUPPI","FERRI","BEDOGNI","LASAGNI","FANTINI","PANCIROLI","RINALDI","BONINI",
                "BENASSI","SIMONAZZI","LUSETTI","BURANI","PICCININI","RUSSO","CAMPANI"));
        Random rand = new Random();
        String name = names.get(rand.nextInt(names.size()));
        String surname = surnames.get(rand.nextInt(surnames.size()));
        String vat = codeFromWord(name)+codeFromWord(surname)+"98R01G337I";
        Date startDate = generateDate();
        Date endDate = new Date();
        do {
            endDate = generateDate();
        }while (endDate.compareTo(startDate)<0);
        try {
            return new Person (name, surname, vat, hq, startDate, endDate);
        } catch (InvalidVATException e) {
            e.printStackTrace();
            return new Person();
        }
    }

    public static String codeFromWord (String inputWord){
        String consonants = inputWord.replaceAll("[AEIOU]", "");
        String vowels = inputWord.replaceAll("[QWRTYPSDFGHJKLZXCVBNM]", "");
        String code = "";
        if (consonants.length()<3) {
            code = consonants;
            code += vowels.substring(0,2-(consonants.length()-1));
        } else {
            code = consonants.substring(0, 3);
        }
        return code;
    }

    private static Date generateDate () {
        Random rand = new Random();
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        int dayNo = rand.nextInt(28) +1;
        int monthNo = rand.nextInt(11) +1;
        int year = rand.nextInt(99);
        int hour = rand.nextInt(23)+1;
        try {
            return df.parse(twoDigitsStringFromNumber(dayNo)+"/" + twoDigitsStringFromNumber(monthNo) + "/" + twoDigitsStringFromNumber(year) + " " + twoDigitsStringFromNumber(hour) + ":00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    private static String twoDigitsStringFromNumber (int number){
        return (number<10) ? Integer.toString(number) : "0"+Integer.toString(number);
    }

    public static Functionary generateUser(Headquarter hq){
        // Random 1-3 to decide user type
        Random rand = new Random();
        Person randomPerson = generatePerson(hq);

        try {
            switch (rand.nextInt(3)) {
                case 0:
                    return new Admin(randomPerson, randomPerson.getName() + "@" + randomPerson.getSurname() + ".it", generatePassword());
                case 1:
                    return new Manager(randomPerson, randomPerson.getName() + "@" + randomPerson.getSurname() + ".it", generatePassword());
                case 2:
                    return new Functionary(randomPerson, randomPerson.getName() + "@" + randomPerson.getSurname() + ".it", generatePassword());
            }
        } catch (InvalidVATException ive){
            ive.printStackTrace();
        }
        return new Functionary();
    }

    private static String generatePassword()
    {
        String password = "";
        Random rand = new Random();
        for (int i = 0;i<10;i++) {
            password += (char) rand.nextInt(26) + 65;
        }
        return password;
    }

    public static Headquarter generateHeadquarter ()
    {
        String headquarterName = "BANCA DI VIA ";
        Random rand = new Random();
        ArrayList<String> streets = new ArrayList<String>(Arrays.asList(
                "ROSSI","MONTANARI","FERRETTI","DAVOLI","CATELLANI","BONACINI","IOTTI","MENOZZI",
                "CORRADINI","IORI","FONTANESI","BARBIERI","BERTANI","MAGNANI","SPAGGIARI","SASSI",
                "BIGI","BERTOLINI","GUIDETTI","BORGHI","SALSI","FORNACIARI","BRAGLIA","ORLANDINI",
                "PRANDI","VEZZANI","BARTOLI","BONI","VECCHI","GRASSI","ESPOSITO","SONCINI",
                "CODELUPPI","FERRI","BEDOGNI","LASAGNI","FANTINI","PANCIROLI","RINALDI","BONINI",
                "BENASSI","SIMONAZZI","LUSETTI","BURANI","PICCININI","RUSSO","CAMPANI"));
        String streetName = streets.get(rand.nextInt(streets.size()));
        headquarterName += streetName;
        String streetAddress = "Via " + streetName + " " + Integer.toString(rand.nextInt(200)+1);
        return new Headquarter(headquarterName, streetAddress);
    }
    
    
}
