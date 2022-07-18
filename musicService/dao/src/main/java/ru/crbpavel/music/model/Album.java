package ru.crbpavel.music.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.crbpavel.music.enums.Genre;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Artist artist;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "album_song",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<Song> songs = new ArrayList<>();

    @Column(name = "title")
    private String title;

    @Column(name = "image_file_path")
    private String imageFilePath;

    @Column(name = "release_date")
    private Date releaseDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private Genre genre;

    public Album(Long id, Artist artist, List<Song> songs, String title, String imageFilePath, Date releaseDate, Genre genre) {
        this.id = id;
        this.artist = artist;
        this.songs = songs;
        this.title = title;
        this.imageFilePath = imageFilePath;
        this.releaseDate = releaseDate;
        this.genre = genre;
    }

    public Album() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return id.equals(album.id) && title.equals(album.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", artist=" + artist +
                ", songs=" + songs +
                ", title='" + title + '\'' +
                ", imageFilePath='" + imageFilePath + '\'' +
                ", releaseDate=" + releaseDate +
                ", genre=" + genre +
                '}';
    }
}
