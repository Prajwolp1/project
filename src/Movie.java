public class Movie {

    private String name;
    private String actors;
    private String director;
    private String summary;
    private String runTime;
    private String rating;
    public Movie(String name, String actors, String director, String summary, String runTime, String rating)  {
        this.name = name;
        this.actors = actors;
        this.director = director;
        this.summary = summary;
        this.runTime = runTime;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getActors()   {
        return actors;
    }

    public String getDirector() {
        return director;
    }

    public String getSummary()  {
        return summary;
    }

    public String getRunTime()  {
        return runTime;
    }

    public String getRating()   {
        return rating;
    }

}
