package com.spotify.controller;

import com.spotify.model.Playlist;
import com.spotify.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playlists")
@CrossOrigin("*")
public class PlaylistController {

    @Autowired
    private PlaylistService service;

    @PostMapping
    public Playlist create(@RequestBody Playlist playlist) {
        return service.createPlaylist(playlist);
    }

    @GetMapping
    public List<Playlist> getAll() {
        return service.getAllPlaylists();
    }

    @GetMapping("/{id}")
    public Playlist getOne(@PathVariable Long id) {
        return service.getPlaylistById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deletePlaylist(id);
    }

    @GetMapping("/search")
    public List<Playlist> searchByName(@RequestParam String name) {
        return service.searchByName(name);
    }

}
