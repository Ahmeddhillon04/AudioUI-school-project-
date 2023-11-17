// Name: Ahmed Dhillon
// Stuent ID: 501176485
import java.io.IOException;
import java.util.Scanner;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args) throws IOException
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;
			
			else if (action.equalsIgnoreCase("STORE"))	// List all songs
			{
				store.listAll(); 
			}
			else if (action.equalsIgnoreCase("SONGS"))	// List all songs
			{
				mylibrary.listAllSongs(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all songs
			{
				mylibrary.listAllAudioBooks(); 
			}
			else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
			{
				mylibrary.listAllPodcasts(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	// List all songs
			{
				mylibrary.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all play lists
			{
				mylibrary.listAllPlaylists(); 
			}
			
			
			// updated download method to take two indices as a range of the items you want downlaoded
			else if (action.equalsIgnoreCase("DOWNLOAD")) 
			{
				System.out.print("From Store Content #: ");
				int fromIndex = scanner.nextInt() ;
				System.out.print("To Store Content #: ");
				int toIndex = scanner.nextInt() ;
				scanner.nextLine();
				
				mylibrary.download(fromIndex, toIndex, store);

									
			}
			// DOWNLOADA: downloads all songs by a given artist from the store.
			else if (action.equalsIgnoreCase("DOWNLOADA")) 
			{
				System.out.print("Artist: ");
				String artist = scanner.nextLine();
				mylibrary.downloada(artist, store);

				
			}
			// DOWNLOADG: downloads all songs with a given genre from the store.
			else if (action.equalsIgnoreCase("DOWNLOADG")) 
			{
				System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]:  ");
				String genre = scanner.nextLine();
				mylibrary.downloadg(genre, store);

				
			}
			// SEARCH: Searches the store for a song with the specified title. 
			else if (action.equalsIgnoreCase("SEARCH")) 
			{
				
					System.out.print("Title: ");
					String title = scanner.nextLine();
					mylibrary.search(title, store);
			}
			
			// SEARCHA: Searches the store for a song with the specified artist.
			else if (action.equalsIgnoreCase("SEARCHA")) 
			{
				System.out.print("Artist: ");
				String artist = scanner.nextLine();
				mylibrary.searcha(artist, store);
			}
			// SEARCHG: Searches the store for a song with the specified genre.
			else if (action.equalsIgnoreCase("SEARCHG")) 
			{
				System.out.print("Genre [POP, ROCK, JAZZ, HIPHOP, RAP, CLASSICAL]:  ");
				String genre = scanner.nextLine();
				mylibrary.searchg(genre, store);
				
			}
			// Get the *library* index (index of a song based on the songs list)
			// of a song from the keyboard and play the song 
			else if (action.equalsIgnoreCase("PLAYSONG")) 
			{
				
				System.out.print("Song Number: "); // ask for the index of the song in the library
				int ind = -1; // index of the song in the library
				if (scanner.hasNextInt()) // if the input is an int then set the index to the input
				{
					ind = scanner.nextInt(); // set the index to the input
					scanner.nextLine(); // reads rest of the line
				}

				else { // if the input is not an int then print invalid input, continue to the next iteration of the loop
					System.out.println("Invalid Input");
					continue;
				}

				boolean song = mylibrary.playSong(ind); // create a boolean variable and set it to the result of the playSong method
				if (!song) { // if the song is not found then print the error message
					System.out.println(mylibrary.getErrorMessage());
				} 
				

			}
			// Print the table of contents (TOC) of an audiobook that
			// has been downloaded to the library. Get the desired book index
			// from the keyboard - the index is based on the list of books in the library
			else if (action.equalsIgnoreCase("BOOKTOC")) 
			{
			// Print error message if the book doesn't exist in the library

			System.out.print("Audiobook Number: "); // ask for the index of the audiobook in the library
			int index = -1; // index set to -1
			if (scanner.hasNextInt()) // if the input is an int then set the index to the input
			{
				index = scanner.nextInt();
				scanner.nextLine(); // reads rest of the line
			}

			else { // if the input is not an int then print invalid input, continue to the next iteration of the loop
				System.out.println("Invalid Input");
				continue; // continue to the next iteration of the loop
			}
			boolean audioBook = mylibrary.printAudioBookTOC(index); // create a boolean variable and set it to the result of the printAudioBookTOC method
			if (!audioBook) { // if the audiobook is not found then print the error message
				System.out.println(mylibrary.getErrorMessage());
			} 

			}
			// Similar to playsong above except for audio book
			// In addition to the book index, read the chapter 
			// number from the keyboard - see class Library
			else if (action.equalsIgnoreCase("PLAYBOOK")) 
			{
				System.out.print("Audiobook Number: "); // ask for the index of the audiobook in the library
				if (scanner.hasNextInt()) // if the input is an int then set the index to the input
				{
					int ind = scanner.nextInt(); // set the index to the input
					scanner.nextLine(); // reads rest of the line
					System.out.print("Chapter: "); // ask for the chapter number
					if (scanner.hasNextInt()) // if the input is an int then set the index to the input
					{
						int chapter = scanner.nextInt(); // set the chapter to the input
						scanner.nextLine(); // reads rest of the line
						boolean audioBook = mylibrary.playAudioBook(ind, chapter); // create a boolean variable and set it to the result of the playAudioBook method
						if (!audioBook) { // if the audiobook is not found then print the error message
							System.out.println(mylibrary.getErrorMessage());
						} 
					}
					else {  // if the input is not a chapter then print invalid chapter
						System.out.println("Invalid chapter");
				
					}
				}
				else { // if the input is not an int then print invalid input
					System.out.println("Invalid input");
			
				}

			}
			// Print the episode titles for the given season of the given podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PODTOC")) 
			{
				
			}
			// Similar to playsong above except for podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number and the episode number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPOD")) 
			{
				
			}
			// Specify a playlist title (string) 
			// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYALLPL")) 
			{
				System.out.print("Playlist Title: "); // ask for the playlist title
				String title = scanner.nextLine(); // set the title to the input
				System.out.println(title); // print the title
				boolean playlist = mylibrary.playPlaylist(title); // create a boolean variable and set it to the result of the playPlaylist method with title as the parameter
				if (!playlist) { // if the playlist is not found then print the error message
					System.out.println(mylibrary.getErrorMessage());
				}



			}
			// Specify a playlist title (string) 
			// Read the index of a song/audiobook/podcast in the playist from the keyboard 
			// Play all the audio content 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPL")) 
			{
				System.out.print("Playlist Title: "); // ask for the playlist title
				String title = scanner.nextLine(); // set the title to the input
				System.out.print("Content Number: "); // ask for the content number

				if ( scanner.hasNextInt() ) { // if the input is an int then set the index to the input
					int index = scanner.nextInt() ; // set the index to the input
					scanner.nextLine(); // reads rest of the line
					System.out.println(title); // print the title
					boolean playlist = mylibrary.playPlaylist(title, index); // create a boolean variable and set it to the result of the playPlaylist method with title and index as the parameters
					if (!playlist) { // if the playlist is not found then print the error message
						System.out.println(mylibrary.getErrorMessage());
					} 
				}
				else { // if the input is not an int then print invalid input
					System.out.println("Invalid input");
			
				}
			}
			// Delete a song from the list of songs in mylibrary and any play lists it belongs to
			// Read a song index from the keyboard
			// see class Library for the method to callstore
			else if (action.equalsIgnoreCase("DELSONG")) 
			{
				
				System.out.print("Playlist Title: "); // ask for the playlist title
					String title = scanner.nextLine(); // set the title to the input

				System.out.print("Playlist Content #: "); // ask for the content number
					if (scanner.hasNextInt()) { // if the input is an int then set the index to the input
    					int index = scanner.nextInt(); // set the index to the input
    					scanner.nextLine(); // reads rest of the line

    						if (mylibrary.delContentFromPlaylist(index, title)) { // if the content is deleted from the playlist then print the playlist
        							System.out.println(); // print empty line
   							 } else {
       						 System.out.println(mylibrary.getErrorMessage());
							  }
					} else {
    	System.out.println("Invalid index");
			}

			}
			// Read a title string from the keyboard and make a playlist
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("MAKEPL")) 
			{
				System.out.print("Playlist Title: "); // ask for the playlist title
				String title = scanner.nextLine(); // set the title to the input
				boolean playlist = mylibrary.makePlaylist(title); // create a boolean variable and set it to the result of the makePlaylist method with title as the parameter
				if (!playlist) { // if the playlist is false
					System.out.println("Playlist " + title + " already exists"); // print the playlist already exists
				}	
				else {
					System.out.print(""); 
				}

			}
			// Print the content information (songs, audiobooks, podcasts) in the playlist
			// Read a playlist title string from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
			{
				System.out.print("Playlist Title: "); // ask for the playlist title
				String title = scanner.nextLine(); // set the title to the input
				boolean playlist = mylibrary.printPlaylist(title); // create a boolean variable and set it to the result of the printPlaylist method with title as the parameter
				if (!playlist) { // if the playlist is not found then print the error message
					System.out.println(mylibrary.getErrorMessage());
				}
				
			}
			// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
			// Read the playlist title, the type of content ("song" "audiobook" "podcast")
			// and the index of the content (based on song list, audiobook list etc) from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("ADDTOPL")) 
			{
				System.out.print("Playlist Title: "); // ask for the playlist title
				String title = scanner.nextLine(); // set the title to the input
				System.out.print("Content Type[SONG, AUDIOBOOK]: "); // ask for the content type and gives the options
				String type = scanner.nextLine().toUpperCase(); // set the type to the input and makes it uppercase
				System.out.print("Library Content #: "); // ask for the content number
				int index = scanner.nextInt(); // set the index to the input
				scanner.nextLine(); // reads rest of the line
				boolean playlist = mylibrary.addContentToPlaylist(type, index, title); // create a boolean variable and set it to the result of the addContentToPlaylist method with type, index, and title as the parameters
				if (!playlist) { // if the playlist is not found then print the error message
					System.out.println(mylibrary.getErrorMessage());
				}
				
				else {
					System.out.print("");
				}
			}
			// Delete content from play list based on index from the playlist
			// Read the playlist title string and the playlist index
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("DELFROMPL")) 
			{
				System.out.print("Playlist Title: "); // ask for the playlist title
				String title = scanner.nextLine(); // reads input and sets it to title
				System.out.print("Playlist Content #: "); // ask for the content number
				if ( scanner.hasNextInt() ) { // if the input is an int then set the index to the input
					int index = scanner.nextInt();
					scanner.nextLine(); // reads rest of the line
					boolean playlist = mylibrary.delContentFromPlaylist(index, title); // create a boolean variable and set it to the result of the delContentFromPlaylist method with index and title as the parameters
					if (!playlist) { // if the playlist is not found then print the error message
						System.out.println(mylibrary.getErrorMessage());
					} 
		
				}
				else { 
					System.out.println("Invalid input");
			
				}
			}
			
			else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{
				mylibrary.sortSongsByYear();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{
				mylibrary.sortSongsByName();
			}
			else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{
				mylibrary.sortSongsByLength();
			}

			
			System.out.print("\n>");

			
		}
	}
}
