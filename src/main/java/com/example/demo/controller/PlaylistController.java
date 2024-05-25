package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.entities.PlayList;
import com.example.demo.entities.Song;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.SongService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class PlaylistController {
	@Autowired
	SongService songService;
	@Autowired
	PlaylistService playService;
	@GetMapping("/createPlaylist")
	public String createPlaylist(Model model) {
		List<Song> songsList=songService.fetchAllSongs();
		model.addAttribute("songs", songsList);
		
		return "createPlaylist";
	}
	@PostMapping("/addPlaylist")
	public String addPlaylist(@ModelAttribute PlayList playlist) {
		//Upadating playlist table
		playService.addPlaylist(playlist);
		//Updating Song table
		List<Song> songList=playlist.getSongs();
		for(Song s:songList)
		{
			s.getPlayList().add(playlist);
			//updating song object in db
			songService.updateSong(s);
		}
	
		
		return "adminHome";
	}
	@GetMapping("/viewPlaylist")
	public String viewPlaylist(Model model) {
		List<PlayList> playlist=playService.fetchAllPlaylist();
		model.addAttribute("playlist", playlist);
		return "displayPlaylists";
	}
	
	
	

}
