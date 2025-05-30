from flask import Flask, request, jsonify, json
from promp_handler import generate_quiz

app = Flask(__name__)

@app.route('/quiz', methods=["POST"])
def get_quiz():
    try:
        data = request.get_json()
       
        # assign general category and normal difficulty if none are given
        
        if not data or 'category' not in data or 'difficulty' not in data:
            return ({'error': 'Missing category or difficulty'}), 400
        
        category = data['category']
        difficulty = data['difficulty']
        
        result = generate_quiz(category, difficulty)
       
        # return jsonified response
        print(result)
        parsed_result = json.loads(result)
        print(parsed_result)
        return jsonify(parsed_result), 200
    
    except Exception as e:
        print("Gemini returned invalid JSON:\n", result)
        return jsonify({'error': 'Invalid response format from Gemini'}), 500


if __name__ == '__main__':
    app.run(debug=True)