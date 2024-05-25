package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Song;
import com.example.demo.repositories.SongRepository;

@Service
public class SongServiceInplementation implements SongService {
	@Autowired
	SongRepository repo;
	
	@Override
	public void addSong(Song song) {
		repo.save(song);
		
	}

	@Override
	public List<Song> fetchAllSongs() {
		repo.findAll();
		return repo.findAll();
	}

	@Override
	public boolean songExixts(String name) {
		Song song=repo.getByName(name);
		if(song==null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public void updateSong(Song s) {
		repo.save(s);
		
	}

}
