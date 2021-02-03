package org.fatih.scrapurl;

public class Link {

	private final String url;
	private final Link previous;

	public Link(String url, Link previous) {
		this.url = url;
		this.previous = previous;
	}

	public String getUrl() {
		return url;
	}

	void printPath() {
		System.out.println(createPath());
	}

	String createPath() {
		if (previous != null) {
			return previous.createPath() + " => " + url;
		}
		return url;
	}
}
