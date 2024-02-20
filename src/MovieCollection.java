import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class MovieCollection {

    ArrayList<Movie> Movies;
    public MovieCollection()  {
        Movies = new ArrayList<>();
        mainMenu();
    }

    public void mainMenu()  {
        saveData();
        reOrder();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";

        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    public void saveData()  {
        try {
            File myFile = new File("src\\movies_data.csv");
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

    public void reOrder()   {
        for (int i = 1; i < Movies.size(); i++)  {
            String word = Movies.get(i).getName();
            int loop = i;
            while (loop > 0 && word.compareTo(Movies.get(loop - 1).getName()) < 0) {
                loop--;
            }
            Movie temp = Movies.get(i);
            Movies.remove(i);
            Movies.add(loop, temp);
        }
    }


    public void searchTitles()  {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a title search term: ");
        String search = scanner.nextLine();
        int count = 0;
        for (int i = 0; i < Movies.size(); i++) {
            if (Movies.get(i).getName().toLowerCase().toLowerCase().contains(search))   {
                count++;
                System.out.println(count + ". " + Movies.get(i).getName());
            }
        }
        if (count == 0) {
            System.out.println("There are no movies with that search term!");
        }
        if (count > 0)  {
            System.out.print("What movie would you like to learn more about: ");
            int learnMore = scanner.nextInt();
            int countTwo = 0;
            for (int i = 0; i < Movies.size(); i++) {
                if (Movies.get(i).getName().toLowerCase().toLowerCase().contains(search))   {
                    countTwo++;
                }
                if (countTwo == learnMore)  {
                    System.out.println();
                    System.out.println("Title: " + Movies.get(i).getName() + "\nRuntime: " + Movies.get(i).getRunTime() + "\nDirected-By: " + Movies.get(i).getDirector() + "\nCast: " + Movies.get(i).getActors() + "\nOverview: " + Movies.get(i).getSummary() + "\nRating: " + Movies.get(i).getRating());
                    break;
                }
            }
        }
    }

    public void searchCast()    {
        ArrayList<String> correctName = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a person to search for (first or last name): ");
        String name = scanner.nextLine().toLowerCase();
        int count = 0;
        for (int i = 0; i < Movies.size(); i++) {
            ArrayList<String> cast = Movies.get(i).getActorsSeparated();
            for (int j = 0; j < cast.size(); j++)   {
                if (cast.get(j).toLowerCase().contains(name))   {
                    count++;
                    correctName.add(cast.get(j));
                }
            }
        }

        if (count == 0) {
            System.out.println("No results match your search.");
        }
        if (count > 0)  {
            for (int a = 0; a < correctName.size(); a++)    {
                String checkWord = correctName.get(a);
                for (int b = a + 1; b < correctName.size(); b++)    {
                    if (correctName.get(b).equals(checkWord))   {
                        correctName.remove(b);
                    }
                }
            }
            for (int i = 1; i < correctName.size(); i++)  {
                String word = correctName.get(i);
                int loop = i;
                while (loop > 0 && word.compareTo(correctName.get(loop - 1)) < 0) {
                    loop--;
                }
                String temp = correctName.get(i);
                correctName.remove(i);
                correctName.add(loop, temp);
            }

            for (int j = 1; j < correctName.size(); j++)    {
                System.out.println(j + ". " + correctName.get(j));
            }
        }



    }
}
