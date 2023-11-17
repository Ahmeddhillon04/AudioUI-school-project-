// Name: Ahmed Dhillon
// Stuent ID: 501176485
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; 
	private ArrayList<AudioBook> 	audiobooks;
	private ArrayList<Playlist> 	playlists; 
	
  	//private ArrayList<Podcast> 	podcasts;
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	String errorMsg = "";
	
	public String getErrorMessage()
	{
		return errorMsg;
	}

	public Library()
	{
		songs 			= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>();
	  	//podcasts		= new ArrayList<Podcast>(); ;
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	public boolean download(AudioContent content)
	{


		if (content == null) // if the content is null, then it is invalid, return false
		{
			errorMsg = " Invalid input"; // set the error message to invalid input
			return false; // retrun false
		}
		if ( content.getType().equals(Song.TYPENAME) ) // checks in the content is a song
		{
			if (songs.contains(content)) // if the song is already in the list, set error message, return false
			{
				errorMsg = "Song already in downloaded"; // error message set to song already in downloaded
				return false;
			}
			else // else add the song to the list, return true
			{
				songs.add((Song)content); // add the song to the list
				return true;
			}
		}
		else if ( content.getType().equals(AudioBook.TYPENAME) ) // checks if the content is an audiobook
		{
			if (audiobooks.contains(content)) // if the audiobook is already in the list, set error message, return false
			{
				errorMsg = "Audiobook already in downloaded"; // error message set to audiobook already in downloaded
				return false;
			}
			else // else add the audiobook to the list, return true
			{
				audiobooks.add((AudioBook)content); // add the audiobook to the list
				return true;
			}
		}
		else // if the content is not a song or audiobook, then it is invalid, return false
		{
			errorMsg = "Unknown content type"; // set the error message to unknown content type
			return false;
		}



	}
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		if ( songs.size() == 0){ //if songs is empty, print no songs downloaded

			System.out.println("No songs downloaded");
		}
		for (int i = 0; i < songs.size(); i++) // for loop to print all songs
		{
			int index = i + 1; // index is i + 1 since loops start at 0
			System.out.print("" + index + ". "); // print index with a . 
			songs.get(i).printInfo(); // print the info of the song
			System.out.println();	// print a new line
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		
		for (int i = 0; i < audiobooks.size(); i++) // loop to print all audiobooks
		{
			int ind = i + 1; // index is i + 1 since loops start at 0
			System.out.print("" + ind + ". "); // print index with a "."
			audiobooks.get(i).printInfo(); // print the info of the audiobook
			System.out.println(); // print a new line
		}
	}
	
  // Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{
		// not doing podcasts 
	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		for (int i = 0; i < playlists.size(); i++) // loop to print all playlists
		{
			int ind = i + 1; // index is i + 1 since loops start at 0
			System.out.print("" + ind + ". "); // print index with a "."
			System.out.println(playlists.get(i).getTitle()); // print the title of the playlist
		}
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists names
		ArrayList<String> artists = new ArrayList<String>(); // empty array list of strings called artists
		for (int i = 0; i < songs.size(); i++) // loop through songs array list
		{
			String Currartist = songs.get(i).getArtist(); // current artist is the artist of the song at index i
			if (!artists.contains(Currartist)) // if the artist is not already in the array list
			{
				artists.add(Currartist); // add the artist to the array list
			}
		}
		for (int i = 0; i < artists.size(); i++) // loop through the new artists array list
		{
			System.out.println(i+1 + ". " + artists.get(i)); // print the index of the artist followed by the artist name
		}
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public boolean deleteSong(int index)
	{
		if (index < 0 || index > songs.size()) // if the index is out of bounds, set error message, return false
		{
			errorMsg = "Song Not Found";
			return false;
		}
		Song song = songs.remove(index - 1); // remove the song at index - 1 since loops start at 0
		for (int i = 0; i < playlists.size(); i++) // loop through the playlists array list
		{	
			if (playlists.get(i).getContent().contains(song)) // if the playlist contains the song
			{
				playlists.get(i).deleteContent(playlists.get(i).getContent().indexOf(song)); // delete the song from the playlist
			}
			
		}
		return true; // return true
	}
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		// Use Collections.sort() 
		Collections.sort(songs, new SongYearComparator()); // sort the songs array list using the SongYearComparator class
	}
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song> 
	{
		public int compare(Song song1, Song song2) { // compare two songs based on year
			return song1.getYear() - song2.getYear(); // return the difference between the years of the two songs
			}
		
		
	}
	// Sort songs by length
	public void sortSongsByLength()
	{
	 // Use Collections.sort() 
		Collections.sort(songs, new SongLengthComparator()); // sort the songs array list using the SongLengthComparator class
	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song>
	{
		public int compare(Song song1, Song song2) { // compare two songs based on length
			return song1.getLength() - song2.getLength(); // return the difference between the lengths of the two songs
			}
	
	}

	// Sort songs by title 
	public void sortSongsByName()
	{
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		Collections.sort(songs, Comparator.comparing(Song::getTitle)); // sort the songs array list using the SongTitleComparator class
	}
	
	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public boolean playSong(int index)
	{
		if (index < 1 || index > songs.size()) // check if index is out of bounds
		{
			errorMsg = "Song Not Found"; // set error message
			return false; // return false
		}
		Song songToOutput = songs.get(index-1); // get the song at index - 1 since loops start at 0
		songToOutput.play(); // play the song
		return true; // return true
	}
	
	// Play podcast from list (specify season and episode)
	// Bonus
	public boolean playPodcast(int index, int season, int episode)
	{
		return false;

	}
	
	// Print the episode titles of a specified season
	// Bonus 
	public boolean printPodcastEpisodes(int index, int season)
	{
		return false;
	}
	
	// Play a chapter of an audio book from list of audiobooks
	public boolean playAudioBook(int index, int chapter)
	{
		if (index < 1 || index > audiobooks.size()) // check if index is out of bounds
		{	
			errorMsg = "AudioBook Not Found"; // set error message
			return false; // return false
		}
		AudioBook book = audiobooks.get(index - 1); // get the audiobook at index - 1 since loops start at 0
		book.selectChapter(chapter); // select the chapter
		book.play(); // play the audiobook
		return true; // return true
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public boolean printAudioBookTOC(int index)
	{
		if (index < 1 || index > audiobooks.size()) // check if index is out of bounds
		{
			errorMsg = "AudioBook Not Found"; // set error message
			return false; // return false
			
		}

		AudioBook audiobook = audiobooks.get(index - 1); // get the audiobook at index - 1 since loops start at 0
		audiobook.printTOC(); // print the table of contents
		return true;
	}
	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public boolean makePlaylist(String title)
	{
		for (int i = 0; i < playlists.size(); i++) // loop through the playlists array list
		{
			if (playlists.get(i).getTitle().equals(title)) // if the playlist title is the same as the title passed in
			{
				errorMsg = "Playlist already exists"; // set error message
				return false; // return false
			}
		}
		Playlist playlist = new Playlist(title); // create a new playlist with the title passed in
		playlists.add(playlist); // add the playlist to the playlists array list
		return true; // return true
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public boolean printPlaylist(String title)
	{
		for (Playlist playlist : playlists) { // loop through the playlists array list
			if (playlist.getTitle().equals(title)) { // if the playlist title is the same as the title passed in
				playlist.printContents(); // print the contents of the playlist
				return true; // return true
			}
		}
		errorMsg = "Playlist not found"; // otherwise set error message and return false
		return false;
	}
	
	// Play all content in a playlist
	public boolean playPlaylist(String playlistTitle)
	{
		for (Playlist playlist : playlists) { // loop through the playlists array list
			if (playlist.getTitle().equals(playlistTitle)) { // if the playlist title is the same as the title passed in
				playlist.playAll(); // play all the contents of the playlist
				return true; // return true
			}
		}
		errorMsg = "Playlist not found"; // otherwise set error message and return false
		return false;
	}
	
	// Play a specific song/audiobook in a playlist
	public boolean playPlaylist(String playlistTitle, int indexInPL)
	{
		
		for (Playlist playlist : playlists) { // loop through the playlists array list
			if (playlist.getTitle().equals(playlistTitle)) { // if the playlist title is the same as the title passed in
				playlist.play(indexInPL); // play the content at the index passed in
				return true; // return true
			}
		}
		errorMsg = "Playlist not found"; // otherwise set error message and return false
		return false;
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public boolean addContentToPlaylist(String type, int index, String playlistTitle)
	{
		if (type.equals(Song.TYPENAME)) { // if the type is a song
			if (index < 1 || index > songs.size()) { // check if index is out of bounds
				errorMsg = "Song Not Found"; // set error message
				return false; // return false
			}
			Song songToAdd = songs.get(index - 1); // get the song at index - 1 since loops start at 0
			for (Playlist playlist : playlists) { // loop through the playlists array list
				if (playlist.getTitle().equals(playlistTitle)) { // if the playlist title is the same as the title passed in	
					playlist.addContent(songToAdd); // add the song to the playlist
					return true; // return true
				}
			}
		} else if (type.equals(AudioBook.TYPENAME)) { // if the type is an audiobook
			if (index < 1 || index > audiobooks.size()) {  // check if index is out of bounds
				errorMsg = "AudioBook Not Found"; // set error message
				return false; // return false
			}
			AudioBook audioBookToAdd = audiobooks.get(index - 1); // get the audiobook at index - 1 since loops start at 0
			for (Playlist playlist : playlists) { // loop through the playlists array list
				if (playlist.getTitle().equals(playlistTitle)) { // if the playlist title is the same as the title passed in
					playlist.addContent(audioBookToAdd); // add the audiobook to the playlist
					return true; 		// return true
				}
			}
		} else { // otherwise set error message and return false
			errorMsg = "Invalid type"; 
			return false;
		}
	
		errorMsg = "Playlist Not Found"; // if the playlist is not found set error message and return false
		return false;
	}

  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public boolean delContentFromPlaylist(int index, String title)
	{
		for (Playlist playlist : playlists) { // loop through the playlists array list
			if (playlist.getTitle().equals(title)) { // if the playlist title is the same as the title passed in
				playlist.deleteContent(index); // delete the content at the index passed in
				return true; // return true
			}
		}
		errorMsg = "Playlist not found"; // otherwise set error message and return false
		return false;
	}
	// New download method for downloading a range of songs from a store, using a start and end index
    public void download(int fromIndex, int toIndex, AudioContentStore store) {
		try { // try to download the songs
			if (fromIndex < 0 || toIndex >= store.contents.size() + 1) { // check if the indexes are out of bounds
				throw new IndexOutOfBoundsException("Invalid index range"); // throw an exception if they are
			}
			if (fromIndex > toIndex) { // check if the from index is greater than the to index
				throw new IllegalArgumentException("fromIndex must be less than or equal to toIndex"); // throw an exception if it is
			}
			for (int i = fromIndex; i <= toIndex; i++) { // loop through the indexes
				AudioContent content = store.getContent(i); // get the content at the index
				if (content instanceof Song) { // if the content is a song
					Song song = (Song) content; // cast the content to a song
					if (songs.contains(song)) { // if the song is already in the library
						System.out.println("Song " + song.getTitle() + " already downloaded"); // print that it is already downloaded
					} else {
						songs.add(song); // otherwise add the song to the library
						System.out.println("Song " + song.getTitle() + " Added to Library"); // print that it was added to the library
					}
				} else if (content instanceof AudioBook) { // if the content is an audiobook
					AudioBook book = (AudioBook) content; // cast the content to an audiobook
					if (audiobooks.contains(book)) { // if the audiobook is already in the library
						System.out.println("AudioBook " + book.getTitle() + " already downloaded"); // print that it is already downloaded
					} else {
						audiobooks.add(book); // otherwise add the audiobook to the library
						System.out.println("AudioBook " + book.getTitle() + " Added to Library"); // print that it was added to the library
					}
				}
			}
		} catch (Exception e) { // catch any exceptions
			System.out.println(e.getMessage()); // print the error message
		}
    }
	// New downloada method for downloading all songs from a store by a given artist
	public void downloada(String artist, AudioContentStore store) {
		try { // try to download the songs
			boolean artistFound = false; // boolean to check if the artist was found
			for (AudioContent content : store.contents) { // loop through the contents in the store
				if (content instanceof Song) { // if the content is a song
					Song song = (Song) content; // cast the content to a song
					if (song.getArtist().equals(artist)) { // if the song artist is the same as the artist passed in
						artistFound = true; // set artistFound to true
						if (songs.contains(song)) { // if the song is already in the library
							System.out.println("Song " + song.getTitle() + " already downloaded"); // print that it is already downloaded
						} else {
							songs.add(song); // otherwise add the song to the library
							System.out.println("Song " + song.getTitle() + " Added to Library"); // print that it was added to the library
						}
					}
				} else if (content instanceof AudioBook) { // if the content is an audiobook
					AudioBook book = (AudioBook) content; // cast the content to an audiobook
					if (book.getAuthor().equals(artist)) { // if the audiobook author is the same as the artist passed in
						artistFound = true; // set artistFound to true
						if (audiobooks.contains(book)) { // if the audiobook is already in the library
							System.out.println("AudioBook " + book.getTitle() + " already downloaded"); // print that it is already downloaded
						} else {
							audiobooks.add(book); // otherwise add the audiobook to the library
							System.out.println("AudioBook " + book.getTitle() + " Added to Library"); // print that it was added to the library
						}
					}
				}
			}
			if (!artistFound) { // if the artist was not found
				throw new Exception("Artist not found " + artist); // throw an exception
			}
		} catch (Exception e) {	// catch any exceptions
			System.out.println(e.getMessage());	// print the error message
		}
	}
	// New search method for searching for a song/audiobook/podcast in the library by title
	public void search(String title, AudioContentStore store) { 
		try { // try to search for the content
			Integer index = store.getContentM().get(title); // get the index of the content
			if (index == null) { // if the index is null
				throw new Exception("No matches for " + title); // throw an exception
			}
			System.out.print(index + ". "); // print the index
			store.getContent(index).printInfo(); // print the content info
		} catch (Exception e) { // catch any exceptions
			System.out.println(e.getMessage()); // print the error message
		}
	}

	// New searcha method for searching for all songs/audiobooks/podcasts in the library by artist
	public void searcha(String artist, AudioContentStore store) { 
		try { // try to search for the content
			ArrayList<Integer> indices = store.getArtistM().get(artist);	// get the indices of the content
			if (indices == null) {	// if the indices are null
				throw new Exception("No matches for " + artist);	// throw an exception
			}
			for (int index : indices) {	// loop through the indices
				System.out.print(index + ". ");	// print the index
				store.getContent(index ).printInfo();	// print the content info
				System.out.println();	// print a new line
			}
		} catch (Exception e) {	// catch any exceptions
			System.out.println(e.getMessage());	// print the error message
		}
	}
	// New searchg method for searching for all songs/audiobooks/podcasts in the library by genre
	public void searchg(String genre, AudioContentStore store) {
		try { // try to search for the content
			ArrayList<Integer> indices = store.getGenreM().get(genre); // get the indices of the content
			if (indices == null) { // if the indices are null	
				throw new Exception("No matches for " + genre); // throw an exception 			
			}
			for (int index : indices) { // loop through the indices
				System.out.print(index + ". "); // print the index
				store.getContent(index ).printInfo(); // print the content info
				System.out.println(); // print a new line
			}
		} catch (Exception e) { // catch any exceptions
			System.out.println(e.getMessage()); // print the error message
		}
	}
	
	public void downloadg(String genre, AudioContentStore store) { // download all songs/audiobooks/podcasts in the store by genre
		try {
			ArrayList<Integer> indices = store.getGenreM().get(genre);
			if (indices == null) {
				throw new Exception("No matches for " + genre);
			}
			for (int index : indices) {
				AudioContent content = store.getContent(index);
				if (content instanceof Song) {
					Song song = (Song) content;
					if (songs.contains(song)) {
						System.out.println("Song " + song.getTitle() + " already downloaded");
					} else {
						songs.add(song);
						System.out.println("Song " + song.getTitle() + " Added to Library");
					}
				} else if (content instanceof AudioBook) {
					AudioBook book = (AudioBook) content;
					if (audiobooks.contains(book)) {
						System.out.println("AudioBook " + book.getTitle() + " already downloaded");
					} else {
						audiobooks.add(book);
						System.out.println("AudioBook " + book.getTitle() + " Added to Library");
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}