package com.spotify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spotify.model.Playlist;
import com.spotify.model.Song;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {

    // ✅ Add this method for title search
    List<Song> findByTitleContainingIgnoreCase(String title);
    List<Song> findByArtistContainingIgnoreCase(String artist);
    List<Song> findByLikedTrue();
    List<Song> findByArtist(String artist);
    


   

}
