import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection {

    private ArrayList<Movie> Movies;
    public MovieCollection()  {
        try {
            File myFile = new File("src\\shoppinglist.txt");
            Scanner fileScanner = new Scanner(myFile);
            while (fileScanner.hasNext()) {
                String data = fileScanner.nextLine();
                String[] splitData = data.split(",");
                String movieName = splitData[0];
                String actors = splitData[1];
                String director = splitData[2];
                String summary = splitData[3];
                String runTime = splitData[4];
                String rating = splitData[5];
                Movie movie = new Movie(movieName, actors, director, summary, runTime, rating);
                Movies.add(movie);
            }
        } catch(IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
