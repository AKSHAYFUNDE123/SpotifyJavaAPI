package com.spotify.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spotify.model.Playlist;
import com.spotify.repository.PlaylistRepository;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository repo;

    public Playlist createPlaylist(Playlist playlist) {
        return repo.save(playlist);
    }

    public List<Playlist> getAllPlaylists() {
        return repo.findAll();
    }

    public Playlist getPlaylistById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void deletePlaylist(Long id) {
        repo.deleteById(id);
    }
}

