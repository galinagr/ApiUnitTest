package junitXml;


public class SongNew {

    private String name;
    private String uri;
    private String subresourceUris;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SongNew songNew = (SongNew) o;

        if (name != null ? !name.equals(songNew.name) : songNew.name != null) return false;
        if (uri != null ? !uri.equals(songNew.uri) : songNew.uri != null) return false;
        return subresourceUris != null ? subresourceUris.equals(songNew.subresourceUris) : songNew.subresourceUris == null;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + uri.hashCode();
        result = 31 * result + subresourceUris.hashCode();
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        checkForEmpty(name);
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        checkForEmpty(uri);
        this.uri = uri;
    }

    public String getSubresourceUris() {
        return subresourceUris;
    }

    public void setSubresourceUris(String subresourceUris) {
        checkForEmpty(subresourceUris);
        this.subresourceUris = subresourceUris;
    }

    public SongNew(String name, String uri, String subresourceUris) {
        this.name = name;
        this.uri = uri;
        this.subresourceUris = subresourceUris;
    }

    public SongNew() {
    }

    private void checkForEmpty(String value) {
        if (value == null || value.length() == 0) {
            throw new IllegalArgumentException("Value shouldn't be empty");
        }
    }

}
