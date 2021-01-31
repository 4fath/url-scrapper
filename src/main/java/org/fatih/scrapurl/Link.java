package org.fatih.scrapurl;

public class Link {

	private String url;
	private Link previous;

	private boolean processed;

	public Link(String url, Link previous, boolean processed) {
		this.url = url;
		this.previous = previous;
		this.processed = processed;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Link getPrevious() {
		return previous;
	}

	public void setPrevious(Link previous) {
		this.previous = previous;
	}

	public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}


	void printPath() {
		System.out.println(createPath());
	}

	String createPath() {
		if (previous != null) {
			return previous.createPath() + "->" + url;
		}
		return url;
	}
}
