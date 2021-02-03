package org.fatih.scrapurl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class LinkStack extends LinkedList<Link> {

	private static final long serialVersionUID = 9051786979718253017L;

	private final List<String> processedUrls = new ArrayList<>();
	private String mainUrl;

	@Override
	public synchronized boolean add(Link link) {

		if (! processedUrls.contains(link.getUrl()) && link.getUrl().startsWith(mainUrl)) {
			processedUrls.add(link.getUrl());
			return super.add(link);
		}

		return false;
	}


	public void printSiteMap() {

		while (! isEmpty()) {

			Link currentLink = pop();

			try {
				Document document = Jsoup.connect(currentLink.getUrl()).get();
				Elements links = document.select("a[href]");

				if (links.isEmpty()) {
					currentLink.printPath();
				}else {

					boolean atLeastOneAdded = false;
					for (Element link : links) {
						atLeastOneAdded = add(new Link(link.attr("abs:href"), currentLink));
					}
					if (!atLeastOneAdded) {
						currentLink.printPath();
					}

				}

			} catch (IOException e) {
				System.out.println("Error while getting: " + currentLink.getUrl());
			}
		}
	}


	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}
}
