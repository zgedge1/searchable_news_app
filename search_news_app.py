

from tkinter import *
import requests
from urllib.request import urlopen
from PIL import ImageTk, Image
import io
from tkinter import messagebox
import webbrowser

url = 'https://newsapi.org/v2/everything?q={}&apiKey= <Enter API Key>

root = Tk()
root.geometry('700x800')
root.resizable(0,0)
root.title('News App')
root.config(background='black')


def open_link(url):
    webbrowser.open(url)


def get_news(q):
    result = requests.get(url.format(q))
    json = result.json()
    if result:
        title = json['articles'][0]['title']
        
        image_url = json['articles'][0]['urlToImage']
        raw_data = urlopen(image_url).read()
        im = Image.open(io.BytesIO(raw_data)).resize((250,140))
        photo = ImageTk.PhotoImage(im)
        
        description = json['articles'][0]['description']
        
        title_2 = json['articles'][1]['title']

        image_url_2 = json['articles'][1]['urlToImage']
        raw_data_2 = urlopen(image_url_2).read()
        im = Image.open(io.BytesIO(raw_data_2)).resize((250,140))
        photo_2 = ImageTk.PhotoImage(im)


        description_2 = json['articles'][1]['description']

        title_3 = json['articles'][2]['title']


        image_url_3 = json['articles'][2]['urlToImage']
        raw_data_3 = urlopen(image_url_3).read()
        im_3 = Image.open(io.BytesIO(raw_data_3)).resize((250,140))
        photo_3 = ImageTk.PhotoImage(im_3)

        description_3 = json['articles'][2]['description']


        image_lbl = Button(root, image= photo, wraplength=400, bg='black', fg='white', command = lambda: open_link(json['articles'][0]['url']))
        image_lbl.place(x=60, y=160)

        image_lbl_2 = Button(root, image= photo_2, wraplength=400, bg='black', fg='white', command = lambda:open_link(json['articles'][1]['url']))
        image_lbl_2.place(x=60, y=340)

        image_lbl_3 = Button(root, image = photo_3, wraplength=400, bg='black', fg='white', command= lambda:open_link(json['articles'][2]['url']))
        image_lbl_3.place(x=60, y=560)


        final = (title, photo, description, title_2, photo_2, description_2, title_3, photo_3, description_3)
        return final



def search_now():
    q = search_news.get()
    live = get_news(q)
    if live:
        title_lbl['text'] = live[0]
        description_lbl['text'] = live[2]
        
        title_lbl_2['text'] = live[3]
        description_lbl_2['text'] = live[5]

        title_lbl_3['text'] = live[6]
        description_lbl_3['text'] = live[8]

        image_lbl['image'] = live[1]
        image_lbl_2['image'] = live[5]
        image_lbl_3['image'] = live[7]
    
    else:
        messagebox.showerror("ERROR", "News Not Found")

        

    
def enter_press(q):
    search_btn.invoke()
root.bind("<Return>", enter_press)


heading_lbl = Label(root, text = "Search Breaking news", font = ('arial', 14, 'bold'), bg='black', fg='white')
heading_lbl.pack(pady=10)

search_news = Entry(root, width=30, font=('arial', 14))
search_news.pack(pady=5)

search_btn = Button(root, text = "Search", width=6, height=2, cursor='hand2' ,command=search_now)
search_btn.pack(pady=5)

title_lbl = Label(root, text="", wraplength=300, font=('arial', 12, 'bold'), bg='black', fg='white')
title_lbl.place(x=340, y=150)

description_lbl = Label(root, text="", wraplength=300, font=('arial', 12), bg='black', fg='white')
description_lbl.place(x=340, y=210)

title_lbl_2 = Label(root, text = "", wraplength=300, font=('arial', 12, 'bold'), bg='black', fg='white')
title_lbl_2.place(x=340, y=360)

description_lbl_2 = Label(root, text="", wraplength=300, font=('arial', 12), bg='black', fg='white')
description_lbl_2.place(x=340, y=410)

title_lbl_3 = Label(root, text = "", wraplength=300, font=('arial', 12, 'bold'), bg='black', fg='white')
title_lbl_3.place(x=340, y=540)

description_lbl_3 = Label(root, text = "", wraplength=300, font=('arial', 12), bg='black', fg='white')
description_lbl_3.place(x=340, y=620)


root.mainloop()