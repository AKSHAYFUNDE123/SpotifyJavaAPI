package com.spotify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spotify.model.Song;
import com.spotify.service.SongService;

@RestController
@RequestMapping("/api/songs")
@CrossOrigin("*")
public class SongController {

	@Autowired
	private SongService service;

	@GetMapping
	public List<Song> all() {
		return service.getAllSongs();
	}

	@PostMapping
	public Song add(@RequestBody Song song) {
		return service.addSong(song);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.deleteSong(id);
	}
	
	@GetMapping("/search")
	public List<Song> searchSong(@RequestParam String title) {
	    return service.searchByTitle(title);
	}
	
	@GetMapping("/search-by-artist")
	public List<Song> searchByArtist(@RequestParam String artist) {
	    return service.searchByArtist(artist);
	}


}
