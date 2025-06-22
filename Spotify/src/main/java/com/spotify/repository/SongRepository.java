package com.spotify.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spotify.model.Song;

public interface SongRepository extends JpaRepository<Song, Long> {
}
