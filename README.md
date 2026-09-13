# News_Extractor

A lightweight news-lookup application with a **Python (FastAPI) backend** that queries the [NewsAPI.org](https://newsapi.org) API, and a **Java client** that consumes it over a simple REST endpoint.

---

## Features

- Search for the latest news articles by keyword
- Fast, minimal FastAPI backend with a single `/information` endpoint
- Java `HttpClient`-based frontend that calls the backend via HTTP/JSON
- API key managed through environment variables (`.env`), kept out of version control

---

## Architecture

```
Java Frontend  --HTTP POST-->  FastAPI Backend  --HTTPS-->  NewsAPI.org
 (Main.java)   {"input":...}     (api.py)      /v2/everything
               <----------------              <------------
               {"result": "..."}
```

1. The Java client sends a search term to the backend.
2. The FastAPI backend forwards the query to NewsAPI.org and fetches the most recent matching article.
3. The backend returns the article's title and description as a formatted string.

---

## Project Structure

```
NEws/
├── backend/
│   ├── api.py          # FastAPI app exposing the /information endpoint
│   ├── d.env            # Environment file holding the NewsAPI key (not committed)
│   └── .gitignore
└── frontend/
    └── Main.java         # Java client that calls the backend
```

---

## Prerequisites

| Requirement | Version |
|---|---|
| Python | 3.10+ |
| Java (JDK) | 11+ (uses `java.net.http.HttpClient`) |
| NewsAPI.org account | [Get a free API key](https://newsapi.org/register) |

---

## Getting Started

### 1. Clone and enter the project

```bash
git clone <your-repo-url>
cd NEws
```

### 2. Backend setup

```bash
cd backend
pip install fastapi uvicorn requests python-dotenv
```

Create a `d.env` file in the `backend/` directory with your NewsAPI key:

```env
api=YOUR_NEWSAPI_KEY_HERE
```

Start the server:

```bash
uvicorn api:app --reload --port 8000
```

The API will now be running at `http://127.0.0.1:8000`.

### 3. Frontend setup

Compile and run the Java client (make sure the backend is running first):

```bash
cd frontend
javac Main.java
java Main
```

By default, `Main.java` sends the query `"artificial intelligence"` — edit the `input` variable to search for something else.

---

## API Reference

### `POST /information`

**Request Body**

```json
{
  "input": "artificial intelligence"
}
```

**Response**

```json
{
  "result": "Title:-\nSample headline\nContent:-\nSample article description"
}
```

---

## Security Note

- Never commit `d.env` (or any file containing your API key) to version control. It's already listed in `.gitignore`.
- If a key has ever been shared or exposed, rotate it from your [NewsAPI dashboard](https://newsapi.org/account) immediately.

---

## Roadmap

- [ ] Return multiple articles instead of just the first result
- [ ] Add error handling for missing/invalid API keys and empty query results
- [ ] Add a proper frontend UI (CLI args, GUI, or web client)
- [ ] Add unit tests for the backend endpoint

---

## License

This project is available under the [MIT License](LICENSE).
