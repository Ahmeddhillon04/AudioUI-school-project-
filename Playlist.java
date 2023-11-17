// Name: Ahmed Dhillon
// Stuent ID: 501176485
import java.util.ArrayList;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
public class Playlist
{
	private String title;
	private ArrayList<AudioContent> contents; // songs, books, or podcasts or even mixture
	
	public Playlist(String title)
	{
		this.title = title;
		contents = new ArrayList<AudioContent>();
	}
	
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void addContent(AudioContent content)
	{
		contents.add(content);
	}
	
	public ArrayList<AudioContent> getContent()
	{
		return contents;
	}

	public void setContent(ArrayList<AudioContent> contents)
	{
		this.contents = contents;
	}
	
	/*
	 * Print the information of each audio content object (song, audiobook, podcast)
	 * in the contents array list. Print the index of the audio content object first
	 * followed by ". " then make use of the printInfo() method of each audio content object
	 * Make sure the index starts at 1
	 */
	public void printContents()
	{
		int ind = 1; // set index to 1 since it numbers from 1
		for (AudioContent content : contents) // for loop through the contents array list and prints the index and the content
		{
			System.out.print("" + ind + ". ");
			content.printInfo();
			System.out.println();
			ind++;
		}

	}

	// Play all the AudioContent in the contents list
	public void playAll()
	{
		for (AudioContent content : contents) // loop through the contents array list and plays the content
		{
			content.play();
		}
	}
	
	// Play the specific AudioContent from the contents array list.
	// First make sure the index is in the correct range. 
	public void play(int index)
	{
		if (contains(index)){ // if the index is in the correct range, play the content ( sub 1 from index since loop index starts at 0)
		contents.get(index-1).play();
		}
		else{ // if the index is not in the correct range, do nothing
			return;
		}
	}
	
	public boolean contains(int index)
	{
		return index >= 1 && index <= contents.size(); // if the index is in the correct range, return true
	}
	
	// Two Playlists are equal if their titles are equal
	public boolean equals(Object other)
	{
		
		
		Playlist otherPL = (Playlist) other; // cast the object to a playlist
		return this.title.equals(otherPL.title); // return true if the titles are equal
		
		
	}
	
	// Given an index of an audio content object in contents array list,
	// remove the audio content object from the array list
	// Hint: use the contains() method above to check if the index is valid
	// The given index is 1-indexed so convert to 0-indexing before removing
	public void deleteContent(int index)
	{
		if (!contains(index)) return; // if the index is not in the correct range, do nothing
		contents.remove(index-1); // other wise remove the content (sub 1 from index since loop index starts at 0)
	}
	
	
}
