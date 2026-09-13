import requests
from dotenv import load_dotenv
import os
from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI()

class Userequest(BaseModel):
    input:str

@app.post("/information")

def information(data:Userequest):
    load_dotenv("d.env")
    api_key = os.getenv("api")
    #Get input send by java
    query = data.input
    #NEws api url
    url = f"https://newsapi.org/v2/everything?q={query}&from=2026-09-11&sortBy=publishedAt&apiKey={api_key}"

    #request send to news api
    c=requests.get(url)

    #response to json
    data = c.json()

    #gets the article
    articles=data["articles"]

    #for article in articles:
    if not articles:
        result= "No news found"
        
    article=articles[0]

    result = f"Title:-\n{article['title']} \nContent:-\n{article['description']}"
    return{
        "result":result
    }
