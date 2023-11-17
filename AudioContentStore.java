// Name: Ahmed Dhillon
// Stuent ID: 501176485
import java.util.ArrayList;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library

public class AudioContentStore
{
	ArrayList<AudioContent> contents; 
	private Map<String, Integer> ContentM; // map for content
	private Map<String,ArrayList<Integer>> ArtistM;//artist map
	private Map<String,ArrayList<Integer>> GenreM;//genre map
		
	private ArrayList<AudioContent> reader() throws IOException { //method to read from file
		ArrayList<AudioContent> output = new ArrayList<AudioContent>(); //create arraylist
		File file = new File("Store.txt"); //create file
		Scanner in = new Scanner(file); //create scanner
		while (in.hasNextLine()) { //while there is a next line
			String line = in.nextLine(); //read line
			if (line.equals("SONG")) { //if line is song
				String id = in.nextLine(); 		//read id
				String title = in.nextLine(); //read title
				int year = in.nextInt(); //read year
				int length = in.nextInt(); //read length
				in.nextLine(); //read next line
				String artist = in.nextLine(); //read artist
				String composer = in.nextLine(); //read composer
				Song.Genre genre = Song.Genre.valueOf(in.nextLine()); //read genre
				int lines = in.nextInt(); //read lines
				in.nextLine(); //read next line 
				StringBuilder lyricsBuilder = new StringBuilder(); //create string builder 
				for (int i = 0; i < lines; i++) { //for loop to read lines 
					lyricsBuilder.append(in.nextLine()).append("\r\n"); //append lines to string builder
				}
				String lyrics = lyricsBuilder.toString(); //convert string builder to string
				output.add(new Song(title, year, id, Song.TYPENAME, lyrics, length, artist, composer, genre, lyrics)); //add song to arraylist
			} else { //if line is audiobook
				String id = in.nextLine(); //read id
				String title = in.nextLine(); //read title 
				int year = in.nextInt(); //read year
				int length = in.nextInt(); //read length
				in.nextLine(); //read next line
				String author = in.nextLine(); //read author
				String narrator = in.nextLine(); //read narrator
				int chapters = in.nextInt(); //read chapters
				in.nextLine(); //read next line
				ArrayList<String> chaptertitles = new ArrayList<>(); //create arraylist for chapter titles
				for (int i = 0; i < chapters; i++) { //for loop to read chapters
					chaptertitles.add(in.nextLine()); //add chapter titles to arraylist 
				}
				ArrayList<String> chaptercontents = new ArrayList<>(); //create arraylist for chapter contents
				while (in.hasNextInt()) { //while there is a next int
					int lin = in.nextInt(); //read int
					StringBuilder contsBuilder = new StringBuilder(); //create string builder
					in.nextLine(); //read next line
					for (int i = 0; i < lin; i++) { //for loop to read lines
						contsBuilder.append(in.nextLine()).append("\r\n"); //append lines to string builder
					}
					chaptercontents.add(contsBuilder.toString()); //add string builder to arraylist
				} 
				output.add(new AudioBook(title, year, id, AudioBook.TYPENAME, "", length, author, narrator, chaptertitles, chaptercontents));
			} //add audiobook to arraylist
		}
		return output; //return arraylist
	}
		

			public AudioContentStore() 	
		{
			
			try{ 
				contents=reader();//create contents from method
			}
			catch(IOException e) // catch exception
			{
				e.getMessage(); //print message
			}

			ContentM =new TreeMap<String, Integer>();//initalize content map
			for(int i=0;i<contents.size();i++)  // for loop to add content to map
			{
				ContentM.put(contents.get(i).getTitle(),(i+1));//add content with index
			}
			ArtistM = new TreeMap<String, ArrayList<Integer>>(); //initalize artist map
			for (AudioContent content : contents) { //for loop to add artist to map
   				 if (content instanceof Song) { //if content is song
       				 Song song = (Song) content; //create song
       				 String artist = song.getArtist(); //get artist
       				 if (!ArtistM.containsKey(artist)) { //if artist is not in map
           				 ArtistM.put(artist, new ArrayList<>()); //add artist to map
       					 }
        				ArtistM.get(artist).add(contents.indexOf(song) + 1); //add song to map
   							 } else if (content instanceof AudioBook) { //if content is audiobook
       							 AudioBook book = (AudioBook) content; //create audiobook
      							 String author = book.getAuthor(); //get author
        							if (!ArtistM.containsKey(author)) { //if author is not in map
           								 ArtistM.put(author, new ArrayList<>()); //add author to map
      								  }
       		 ArtistM.get(author).add(contents.indexOf(book) + 1); //add audiobook to map
    				}
				}
				GenreM = new TreeMap<String, ArrayList<Integer>>(); //initalize genre map 
				for (AudioContent content : contents) { //for loop to add genre to map 
					if (content instanceof Song) { //if content is song 
						Song song = (Song) content; //create song 
						String genre = song.getGenre().name(); //get genre 
						if (!GenreM.containsKey(genre)) { //if genre is not in map 
							GenreM.put(genre, new ArrayList<>()); //add genre to map 
						}
						GenreM.get(genre).add(contents.indexOf(song) + 1); //add song to map 
					}
				}

		}
		public Map<String, Integer> getContentM()//access content map
		{
			return ContentM; 
		}
		public Map<String,ArrayList<Integer>> getArtistM()//access artist map
		{
			return ArtistM;
		}
		public Map<String,ArrayList<Integer>> getGenreM()//access genre map
		{
			return GenreM;
		}


		public AudioContent getContent(int index)
		{
			if (index < 1 || index > contents.size())
			{
				return null;
			}
			return contents.get(index-1);
		}
		
		public void listAll()
		{
			for (int i = 0; i < contents.size(); i++)
			{
				int index = i + 1;
				System.out.print("" + index + ". ");
				contents.get(i).printInfo();
				System.out.println();
			}
		}
		
		
		// Podcast Seasons
		/*
		private ArrayList<Season> makeSeasons()
		{
			ArrayList<Season> seasons = new ArrayList<Season>();
		  Season s1 = new Season();
		  s1.episodeTitles.add("Bay Blanket");
		  s1.episodeTitles.add("You Don't Want to Sleep Here");
		  s1.episodeTitles.add("The Gold Rush");
		  s1.episodeFiles.add("The Bay Blanket. These warm blankets are as iconic as Mariah Carey's \r\n"
		  		+ "lip-syncing, but some people believe they were used to spread\r\n"
		  		+ " smallpox and decimate entire Indigenous communities. \r\n"
		  		+ "We dive into the history of The Hudson's Bay Company and unpack the\r\n"
		  		+ " very complicated story of the iconic striped blanket.");
		  s1.episodeFiles.add("There is no doubt that the Klondike Gold Rush was an iconic event. \r\n"
		  		+ "But what did the mining industry cost the original people of the territory? \r\n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s1.episodeFiles.add("here is no doubt that the Klondike Gold Rush was an iconic event. \r\n"
		  		+ "But what did the mining industry cost the original people of the territory? \r\n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s1.episodeLengths.add(31);
		  s1.episodeLengths.add(32);
		  s1.episodeLengths.add(45);
		  seasons.add(s1);
		  Season s2 = new Season();
		  s2.episodeTitles.add("Toronto vs Everyone");
		  s2.episodeTitles.add("Water");
		  s2.episodeFiles.add("There is no doubt that the Klondike Gold Rush was an iconic event. \r\n"
		  		+ "But what did the mining industry cost the original people of the territory? \r\n"
		  		+ "And what was left when all the gold was gone? And what is a sour toe cocktail?");
		  s2.episodeFiles.add("Can the foundation of Canada be traced back to Indigenous trade routes?\r\n"
		  		+ " In this episode Falen and Leah take a trip across the Great Lakes, they talk corn\r\n"
		  		+ " and vampires, and discuss some big concerns currently facing Canada's water."); 
		  s2.episodeLengths.add(45);
		  s2.episodeLengths.add(50);
		 
		  seasons.add(s2);
		  return seasons;
		}
		*/
}
