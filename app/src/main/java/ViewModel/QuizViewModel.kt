import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.*
import androidx.lifecycle.viewModelScope
import com.example.quickquiz.data.QuizCase
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import android.util.Log
import okhttp3.RequestBody.Companion.toRequestBody

class QuizViewModel : ViewModel() {
    private val _quizCases = mutableStateOf<List<QuizCase>>(emptyList())
    val quizCases: State<List<QuizCase>> = _quizCases

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun fetchQuestions() {
        viewModelScope.launch {
            _isLoading.value = true
            startSessionAndFetchQuestions()
            _isLoading.value = false
        }
    }


    private suspend fun startSessionAndFetchQuestions() {
        Log.d("QUIZ_DEBUG", "Starting session...")
        val sessionResponse = RetrofitClient.apiService.startSession()
        if (sessionResponse.isSuccessful) {
            Log.d("QUIZ_DEBUG", "Session started.")
            val requestBody = buildRequestBody()
            Log.d("QUIZ_DEBUG", "Sending request body: ${requestBody}")
            val quizResponse = RetrofitClient.apiService.getQuestions(requestBody)
            if (quizResponse.isSuccessful) {
                val responseData = quizResponse.body()
                Log.d("QUIZ_DEBUG", "Received quiz response: $responseData")
                _quizCases.value = quizResponse.body()?.data ?: emptyList()
            } else {
                Log.e("QUIZ_DEBUG", "Quiz request failed: ${quizResponse.errorBody()?.string()}")
            }
        } else {
            Log.e("QUIZ_DEBUG", "Session failed: ${sessionResponse.errorBody()?.string()}")
        }
    }

    private fun buildRequestBody(): RequestBody {
        val requestJson = """
        {
          "language": "en",
          "age": 12,
          "subject": "math",
          "difficulty": "easy",
          "question_type": "behavioral",
          "sub_type": "peer",
          "role": "student",
          "sex": "female",
          "allow_image": true,
          "answers": {}
        }
        """
        return requestJson.toRequestBody("application/json".toMediaTypeOrNull())
    }
}
