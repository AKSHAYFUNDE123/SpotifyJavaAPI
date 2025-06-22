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

    public List<Song> getAllSongs() { return repo.findAll(); }

    public Song addSong(Song song) { return repo.save(song); }

    public void deleteSong(Long id) { repo.deleteById(id); }
}

