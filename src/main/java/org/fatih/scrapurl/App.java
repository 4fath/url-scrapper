package org.fatih.scrapurl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App {


    public static void main( String[] args ) {

        if (args.length != 1) {
            System.out.println("insufficient input. " + Arrays.toString(args));
            System.exit(-1);
        }

        String firstUrl = args[0];
        System.out.println("following url and its sub-sites will be printed: " + firstUrl);


        LinkStack stack = new LinkStack();
        stack.setMainUrl(firstUrl);
        stack.add(new Link(firstUrl, null, false));
        stack.printSiteMap();
    }
}
