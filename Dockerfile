FROM python:3.11

ADD searchable_news_app.py .

COPY requirements.txt .

RUN pip install -r requirements.txt

CMD ["python", "./searchable_news_app.py"]
