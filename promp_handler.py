from google import genai
import os
from dotenv import load_dotenv
import google.generativeai as genai

load_dotenv()
#api_key=os.getenv("GEMINI_API_KEY")
genai.configure(api_key=os.getenv("GEMINI_API_KEY"))

model = genai.GenerativeModel("gemini-1.5-flash")
#client = genai.Client(api_key=api_key)


def generate_quiz(category, difficulty):
    prompt = f"""
Generate 1 multiple choice quiz question in the category "{category}" with "{difficulty}" difficulty.

Respond ONLY with valid JSON using this format:
{{
  "question": "...",
  "options": ["A", "B", "C", "D"],
  "answer": "A"
}}

Do NOT include any explanation, markdown, or text outside the JSON.
"""

    #response = client.models.generate_content(    model="gemini-2.0-flash", contents=prompt)
    response = model.generate_content(prompt)
    return response.text
