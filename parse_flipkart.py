from bs4 import BeautifulSoup
from selenium import webdriver

from category_tree import *

URL = 'http://flipkart.com'


def split_url(url):
    return url.split('/pr')[0]


#this function parses the webpage received this way:
#at level 0 is the main menu item name like electronics, men, women, books, etc
#we go to the menu items of each of these to get the items like women->clothing, women->clothing->ethnic
#we are not storing the above ('women->clothing->ethnic') are a relationship,
#instead we are storing the path as described in rediect url that clicking on 'ethnic' will take us to,
#eq for url
#'http://www.flipkart.com/womens-footwear/~the-499-store/pr?sid=osp,iko&otracker=hp_nmenu_sub_women_0_499%20Store'
#the path becomes 'women' (from level 1) + '/womens-footwear/~the-499-store'
#so final path is 'women/womens-footwear/~the-499-store'
#we are storing such paths in a set for uniqueness, which we return
@timeit
def parse_page(soup):
    menu_links = soup.find('ul', class_='menu-links')
    l0_list = menu_links.find_all('li', class_='menu-l0')
    path_strs = set()
    for l0_item in l0_list:
        l0 = l0_item['data-key']
        l1_list = l0_item.find_all('ul', class_='unit size1of5 menu-column even ')
        for l1_item in l1_list:
            l2_list = l1_item.find_all('li', class_='menu-item')
            for l2_item in l2_list:
                next_url = l2_item.find('a')['href']
                dir = split_url(next_url)
                path_strs.add(l0+dir)
    return path_strs


#this function starts the site on browser.
#this is done to get dynamically loaded dropdown, which is not available by normal url parsing
#this function is slow, takes around 40s
@timeit
def start_sel():
    driver = webdriver.Firefox()
    driver.get(URL)
    html = driver.page_source
    soup = BeautifulSoup(html)
    return soup


#this function calls category_tree module functions to create and print the tree as described in that module
@timeit
def categorize(strings):
    print 'here'
    levels, children = parse(strings)
    print_pretty(children, 'home', 0)


def main():
    soup = start_sel()
    strings = parse_page(soup)
    categorize(strings)

if __name__ == '__main__':
    main()