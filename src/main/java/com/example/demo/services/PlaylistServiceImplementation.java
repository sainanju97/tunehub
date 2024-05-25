package com.example.demo.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.PlayList;
import com.example.demo.repositories.PlaylistRepository;

@Service
public class PlaylistServiceImplementation implements PlaylistService {
	@Autowired
	PlaylistRepository playRepo;

	@Override
	public void addPlaylist(PlayList playlist) {
		playRepo.save(playlist);
		
		
	}

	@Override
	public List<PlayList> fetchAllPlaylist() {
		
		return playRepo.findAll();
	}

}
