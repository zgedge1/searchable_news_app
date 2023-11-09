
from tkinter import *
from configparser import ConfigParser
import requests
from urllib.request import urlopen
from PIL import ImageTk, Image
import io
import webbrowser


def open_link(url):
    webbrowser.open(url)



config_file = "config.ini"
config = ConfigParser()
config.read(config_file)
apiKey = config['api_key']['key']


root = Tk()
root.title("News App")
root.geometry('1100x900')
root.config(background='black')
root.resizable(0,0)



url = "https://newsapi.org/v2/everything?q={}&apiKey={}"


def get_news(q):
    result = requests.get(url.format(q, apiKey))
    json = result.json()

    title_0 = json['articles'][0]['title']
    title_1 = json['articles'][1]['title']
    title_2 = json['articles'][2]['title']
    
    description_0 = json['articles'][0]['description']
    description_1 = json['articles'][1]['description']
    description_2 = json['articles'][2]['description']

    image_0 = json['articles'][0]['urlToImage']
    raw_data_0 = urlopen(image_0).read()
    im_0 = Image.open(io.BytesIO(raw_data_0)).resize((250,150))
    photo_0 = ImageTk.PhotoImage(im_0)

    image_1 = json['articles'][1]['urlToImage']
    raw_data_1 = urlopen(image_1).read()
    im_1 = Image.open(io.BytesIO(raw_data_1)).resize((250,150))
    photo_1 = ImageTk.PhotoImage(im_1)

    image_2 = json['articles'][1]['urlToImage']
    raw_data_2 = urlopen(image_2).read()
    im_2 = Image.open(io.BytesIO(raw_data_2)).resize((250,150))
    photo_2 = ImageTk.PhotoImage(im_2)


    #Image Label

    image_lbl_0 = Button(root, image=photo_0, command=lambda: open_link(json['articles'][0]['url']))
    image_lbl_0.place(x=120, y=250)

    imaage_lbl_1 = Button(root, image=photo_1, command=lambda: open_link(json['articles'][0]['url']))
    imaage_lbl_1.place(x=120, y=460)

    image_lbl_2 = Button(root, image=photo_2, command=lambda: open_link(json['articles'][0]['url']))
    image_lbl_2.place(x=120, y=670)


    final = (title_0, title_1, title_2, description_0, description_1, description_2, photo_0, photo_1, photo_2)

    return final


def search_news():
    q = search_entry.get()
    news = get_news(q)

    if news:
        title_lbl_0['text'] = news[0]
        title_lbl_1['text'] = news[1]
        title_lbl_2['text'] = news[2]

        description_lbl_0['text'] = news[3]
        description_lbl_1['text'] = news[4]
        description_lbl_2['text'] = news[5]

        image_lbl_0['image'] = news[6]
        image_lbl_1['image'] = news[7]
        image_lbl_2['image'] = news[8]



def enter_press(q):
    search_btn.invoke()
root.bind("<Return>", enter_press)


heading_lbl = Label(root, text="Search Local News", font=('arial', 14, 'bold'), bg='black', fg='white')
heading_lbl.pack(pady=10)

search_entry = Entry(root, width=35, font=('arial', 14))
search_entry.pack(pady=10)

search_btn = Button(root, text="Search", font=('arial', 12), bd=2, cursor="hand2", command=search_news)
search_btn.pack(pady=10)

#Title Labels

title_lbl_0 = Label(root, text="Title_0", font=('arial', 16), bg='black', fg='white', wraplength=200)
title_lbl_0.place(x=450, y=250)

title_lbl_1 = Label(root, text="Title_1", font=('arial', 16), bg='black', fg='white', wraplength=200)
title_lbl_1.place(x=450, y=460)

title_lbl_2 = Label(root, text="Title_3", font=('arial', 16), bg='black', fg='white', wraplength=200)
title_lbl_2.place(x=450, y=670)


#Description Labels

description_lbl_0 = Label(root, text="Description 0", font=('arial', 14), bg='black', fg='white', wraplength=250)
description_lbl_0.place(x=730, y=250)

description_lbl_1 = Label(root, text="Description 1", font=('arial', 14), bg='black', fg='white', wraplength=250)
description_lbl_1.place(x=730, y=460)

description_lbl_2 = Label(root, text="Description 2", font=('arial', 14), bg='black', fg='white', wraplength=250)
description_lbl_2.place(x=730, y=670)



root.mainloop()