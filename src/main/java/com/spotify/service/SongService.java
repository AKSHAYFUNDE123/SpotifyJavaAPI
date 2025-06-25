package com.spotify.service;

import java.util.List;
import java.util.Optional;

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
   
    
 // Update All song By artist
    public String updateAllSongsByArtist(String artist, Song updatedSong) {
        List<Song> songs = repo.findByArtist(artist);
        
        if (songs.isEmpty()) {
            return "❌ No songs found for artist: " + artist;
        }

        for (Song song : songs) {
            song.setTitle(updatedSong.getTitle());
            song.setAlbum(updatedSong.getAlbum());
            song.setUrl(updatedSong.getUrl());
            // Add more fields as needed
        }

        repo.saveAll(songs);
        return "✅ Updated all songs by artist '" + artist + "'";
    }

   
    // update song by id
 
    public Optional<Song> updateSongById(Long id, Song updatedSong) {
        return repo.findById(id).map(song -> {
            song.setTitle(updatedSong.getTitle());
            song.setArtist(updatedSong.getArtist());
            song.setAlbum(updatedSong.getAlbum());
            song.setUrl(updatedSong.getUrl());
            return repo.save(song);
        });
    }




}
