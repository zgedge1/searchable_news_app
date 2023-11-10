
import requests
from configparser import ConfigParser



print()
print("Welcome to the News App!")
print()

while True:
    print()
    search_news = input("Enter your news search or type exit ot quit ")
    if search_news.lower() == 'exit':
        exit(0)

    url = f"https://newsapi.org/v2/everything?q={search_news}&apiKey="#Enter your API Key"


    result = requests.get(url).json()

    title_0 = result['articles'][0]['title']
    title_1 = result['articles'][1]['title']
    title_2 = result['articles'][2]['title']

    author_0 = result['articles'][0]['author']
    author_1 = result['articles'][1]['author']
    author_2 = result['articles'][2]['author']

    description_0 = result['articles'][0]['description']
    description_1 = result['articles'][1]['description']
    description_2 = result['articles'][2]['description']

    url_0 = result['articles'][0]['url']
    url_1 = result['articles'][1]['url']
    url_2 = result['articles'][2]['url']


    print()
    print(f"Title: {title_0}")
    print()
    print(f"Author: {author_0}")
    print()
    print(f"{description_0}")
    print()
    print(f"{url_0}")
    print()
    print("*******************************************************************************")
    print(f"Title:{title_1}")
    print()
    print(f"Author: {author_1}")
    print()
    print(f"{description_1}")
    print()
    print(f"{url_1}")
    print()
    print("*******************************************************************************")
    print(f"Title: {title_2}")
    print()
    print(f"Author: {author_2}")
    print()
    print(f"{description_2}")
    print()
    print(f"{url_2}")
    