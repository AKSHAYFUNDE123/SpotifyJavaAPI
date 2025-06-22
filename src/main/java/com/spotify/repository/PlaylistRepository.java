package com.spotify.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spotify.model.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
	
	
	 List<Playlist> findByNameContainingIgnoreCase(String name);
}
