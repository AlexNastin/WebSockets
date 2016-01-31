package by.test.sockets.sort;

import java.util.Comparator;

import by.test.sockets.domain.Post;

public class SortedPostsByDate implements Comparator<Post>{

	@Override
	public int compare(Post obj1, Post obj2) {
		String date1 = obj1.getDate();
        String date2 = obj2.getDate();
        return date2.compareTo(date1);
	}

}
