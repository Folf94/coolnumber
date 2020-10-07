<<<<<<< HEAD
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Loader implements Runnable {
    private int region;

    Loader(int regionCode) {
        this.region = regionCode;
    }

    @Override
    public void run() {
        Path targetPath = Paths.get("res/numbersRegion" + region + ".txt");

        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

        StringBuilder builder = new StringBuilder();
        for (char firstLetter : letters) {
            for (char secondLetter : letters) {
                for (char thirdLetter : letters) {
                    for (int number = 1; number < 1000; number++) {
                        builder.append(firstLetter).append(padNumber(number, 3)).append(secondLetter).append(thirdLetter).append(padNumber(region, 2)).append("\n");
=======
import java.io.FileOutputStream;

public class Loader
{
    public static void main(String[] args) throws Exception
    {
        long start = System.currentTimeMillis();

        FileOutputStream writer = new FileOutputStream("res/numbers.txt");

        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        for(int number = 1; number < 1000; number++)
        {
            int regionCode = 199;
            for (char firstLetter : letters)
            {
                for (char secondLetter : letters)
                {
                    for (char thirdLetter : letters)
                    {
                        String carNumber = firstLetter + padNumber(number, 3) +
                            secondLetter + thirdLetter + padNumber(regionCode, 2);
                        writer.write(carNumber.getBytes());
                        writer.write('\n');
>>>>>>> origin/master
                    }
                }
            }
        }
<<<<<<< HEAD
        try {
            Files.write(targetPath, builder.toString().getBytes(), StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String padNumber(int number, int numberLength) {
        StringBuilder numberStr = new StringBuilder(Integer.toString(number));
        int padSize = numberLength - numberStr.length();
        for (int i = 0; i < padSize; i++) {
            numberStr.insert(0, '0');
        }
        return numberStr.toString();
=======

        writer.flush();
        writer.close();

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }

    private static String padNumber(int number, int numberLength)
    {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();
        for(int i = 0; i < padSize; i++) {
            numberStr = '0' + numberStr;
        }
        return numberStr;
>>>>>>> origin/master
    }
}
