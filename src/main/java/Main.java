import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        //This class is not using and only needed
        //for hibernate debugging purpose
        Locale.setDefault(Locale.ENGLISH); // SQL Server throwing locale exception
        //Fast test run for exception search
        Test test = new Test();
        test.beginTest();
        System.exit(0);
    }
}
