package junitXml;


public class Song {

    private String title;
    private String artist;
    private String country;
    private String company;
    private String price;
    private String year;

    public Song(String title, String artist, String country, String company, String price, String year) {
        this.title = title;
        this.artist = artist;
        this.country = country;
        this.company = company;
        this.price = price;
        this.year = year;
    }

    public Song() {
    }

    public Song(String title) {
        this.title = title;
        this.artist = artist;
        this.country = country;
        this.company = company;
        this.price = price;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        checkForEmpty(title);
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        checkForEmpty(artist);
        this.artist = artist;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        checkForEmpty(country);
        this.country = country;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        checkForEmpty(company);
        this.company = company;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        checkForEmpty(price);
        this.price = price;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        checkForEmpty(year);
        this.year = year;
    }

    private void checkForEmpty(String value) {
        if (value == null || value.length() == 0) {
            throw new IllegalArgumentException("Value shouldn't be empty");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (artist != null ? !artist.equals(song.artist) : song.artist != null) return false;
        if (company != null ? !company.equals(song.company) : song.company != null) return false;
        if (country != null ? !country.equals(song.country) : song.country != null) return false;
        if (price != null ? !price.equals(song.price) : song.price != null) return false;
        if (title != null ? !title.equals(song.title) : song.title != null) return false;
        if (year != null ? !year.equals(song.year) : song.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        return result;
    }
}
