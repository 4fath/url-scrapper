package org.fatih.scrapurl;

/**
 * Hello world!
 *
 */
public class App {


    public static void main( String[] args ) {


        String rootUrl = "https://www.example.com/";

        if(args.length == 1 && !args[0].isEmpty()) {
            String urlFromArgs = args[0];
            rootUrl = urlFromArgs;
            System.out.println("args detected: " + urlFromArgs);

        }
        String rootUrlFromEnv = System.getenv("ROOTURL");
        if(rootUrlFromEnv != null && !rootUrlFromEnv.isEmpty()) {
            System.out.println("ENV var detected: " + rootUrlFromEnv);
            rootUrl = rootUrlFromEnv;
        }

        System.out.println("\n\nfollowing url and its sub-sites will be printed: " + rootUrl);


        LinkStack stack = new LinkStack();
        stack.setMainUrl(rootUrl);
        stack.add(new Link(rootUrl, null));
        stack.printSiteMap();
    }
}
