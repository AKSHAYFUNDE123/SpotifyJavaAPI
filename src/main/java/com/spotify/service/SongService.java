package com.spotify.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spotify.model.Song;
import com.spotify.repository.SongRepository;

@Service
public class SongService {
    
    @Autowired
    private SongRepository repo;

    public List<Song> getAllSongs() {
        return repo.findAll();
    }

    public Song addSong(Song song) {
        return repo.save(song);
    }

    public void deleteSong(Long id) {
        repo.deleteById(id);
    }

    // ✅ ADD THIS METHOD
    public List<Song> searchByTitle(String title) {
        return repo.findByTitleContainingIgnoreCase(title);
    }
    
    public List<Song> searchByArtist(String artist) {
        return repo.findByArtistContainingIgnoreCase(artist);
    }
    
 // 💗 Toggle like/unlike by ID
    public Song toggleLike(Long id) {
        Song song = repo.findById(id).orElse(null);
        if (song != null) {
            song.setLiked(!song.isLiked()); // Toggle liked flag
            return repo.save(song);
        }
        return null;
    }

    // 🧡 Get all liked songs
    public List<Song> getLikedSongs() {
        return repo.findByLikedTrue();
    }


}
