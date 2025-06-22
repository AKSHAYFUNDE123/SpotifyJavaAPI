package com.spotify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spotify.model.Playlist;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
