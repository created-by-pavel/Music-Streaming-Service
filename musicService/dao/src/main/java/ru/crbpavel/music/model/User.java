package ru.crbpavel.music.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "username")
    String username;

    @OneToOne
    SecurityModel securityModel;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_song",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private Set<Song> songs = new HashSet<>();

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_album",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id")
    )
    private Set<Album> albums = new HashSet<>();

    public User(Long id, SecurityModel securityModel, String username, Set<Song> songs, Set<Album> albums) {
        this.id = id;
        this.securityModel = securityModel;
        this.username = username;
        this.songs = songs;
        this.albums = albums;
    }

    public User() {
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public void deleteSong(Song song) {
        songs.remove(song);
    }

    public void deleteAlbum(Album album) {
        songs.remove(album);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SecurityModel getSecurityModel() {
        return securityModel;
    }

    public void setSecurityModel(SecurityModel securityModel) {
        this.securityModel = securityModel;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && securityModel.equals(user.securityModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, securityModel);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", securityModel=" + securityModel +
                ", username=" + username +
                ", songs=" + songs +
                ", albums=" + albums +
                '}';
    }
}
