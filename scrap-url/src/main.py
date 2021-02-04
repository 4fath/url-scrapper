import queue
import string
import sys

import requests
from bs4 import BeautifulSoup
from urllib.parse import urljoin


class Link(object):
    def __init__(self, url: string, previous):
        self.url = url
        self.previous = previous

    def print_path(self):
        print(self.create_path())

    def create_path(self) -> string:
        if self.previous is not None:
            return self.previous.create_path(), " => ", self.url
        return self.url

    def get_url(self) -> string:
        return self.url


class LinkStack(queue.Queue):

    def __init__(self, mainUrl: string) -> None:
        super().__init__()
        self.mainUrl = mainUrl
        self.processedUrls = []

    def add_to_queue(self, link_here: Link):
        if link_here.url not in self.processedUrls and link_here.url.startswith(self.mainUrl):
            self.processedUrls.append(link_here.url)
            self.put(link_here)

    def print_site_map(self):
        while not self.empty():
            current_link = self.get()
            doc = requests.get(current_link.get_url())
            soup = BeautifulSoup(doc.content, 'html.parser')
            rs = soup.find_all('a', href=True)
            if not rs:
                current_link.print_path()
            else:
                current_size = self.qsize()
                for a in soup.select('a[href]'):
                    candidate_url = Link(urljoin(current_link.get_url(), a['href']), current_link)
                    self.add_to_queue(candidate_url)
                if current_size != self.qsize():
                    current_link.print_path()


if __name__ == '__main__':
    root_url = "https://www.example.com/"
    if len(sys.argv) == 2:
        print("args detected:", sys.argv[1])
        root_url = sys.argv[1]

    print("following url and its sub-sites will be printed: ", root_url)

    link_stack = LinkStack(root_url)
    link_stack.add_to_queue(Link(root_url, None))
    link_stack.print_site_map()
