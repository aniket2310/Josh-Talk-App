package com.example.joshtalk.viewModel


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.joshtalk.platform.AudioPlayer
import com.example.joshtalk.platform.AudioRecorder
import com.example.joshtalk.repository.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.joshtalk.repository.TextTaskRepository
import kotlin.random.Random

class ImageTaskViewModel(
    private val repository: TaskRepository,
    private val audioRecorder: AudioRecorder,
    private val audioPlayer: AudioPlayer
) {

    private val scope = CoroutineScope(Dispatchers.IO)

    private val _isPlaying = mutableStateOf(false)
    val isPlaying: State<Boolean> = _isPlaying

    private val _playbackProgress = mutableStateOf(0f)
    val playbackProgress: State<Float> = _playbackProgress

    private var playbackJob: Job? = null


    private val _isRecording = mutableStateOf(false)
    val isRecording: State<Boolean> = _isRecording

    private val _recordingDuration = mutableStateOf(0) // In seconds
    val recordingDuration: State<Int> = _recordingDuration


    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    private val _isRecordingFinished = mutableStateOf(false) // successfully finished?
    val isRecordingFinished: State<Boolean> = _isRecordingFinished


    val checkboxNoise = mutableStateOf(false)
    val checkboxMistakes = mutableStateOf(false)
    val checkboxGalti = mutableStateOf(false)

    private var timerJob: Job? = null
    private var fileName: String = ""




    fun startRecording() {
        _errorMessage.value = null
        _isRecordingFinished.value = false
        _recordingDuration.value = 0
        _isRecording.value = true


        checkboxNoise.value = false
        checkboxMistakes.value = false
        checkboxGalti.value = false

        fileName = "recording_${Random.nextLong()}"

        audioRecorder.start(fileName)


        timerJob = scope.launch {
            while (true) {
                delay(1000)
                _recordingDuration.value += 1
            }

        }
    }

    fun stopRecording() {
        _isRecording.value = false
        timerJob?.cancel()
        audioRecorder.stop()

        val duration = _recordingDuration.value


        when {
            duration < 10 -> {
                _errorMessage.value = "Recording too short (min 10 s)"
                _isRecordingFinished.value = false
            }
            duration > 20 -> {
                _errorMessage.value = "Recording too long (max 20 s)"
                _isRecordingFinished.value = false
            }
            else -> {
                _errorMessage.value = null
                _isRecordingFinished.value = true
            }
        }
    }

    fun togglePlayback() {
        if (_isPlaying.value) {
            stopPlayback()
        } else {
            startPlayback()
        }
    }

    private fun startPlayback() {
        if (fileName.isNotEmpty()) {
            _isPlaying.value = true
            audioPlayer.play(fileName)
            playbackJob?.cancel()
            playbackJob = scope.launch {
                val totalDurationSec = _recordingDuration.value
                var currentSec = 0f


                while (currentSec < totalDurationSec && _isPlaying.value) {
                    _playbackProgress.value = currentSec / totalDurationSec.toFloat()


                    delay(100)
                    currentSec += 0.1f
                }


                _playbackProgress.value = 1f
                stopPlayback()
            }
        }
    }

    private fun stopPlayback() {
        _isPlaying.value = false
        audioPlayer.stop()
    }

    fun playRecording() {
        if (fileName.isNotEmpty()) {
            audioPlayer.play(fileName)
            playbackJob?.cancel()
            _playbackProgress.value = 0f
            audioPlayer.stop()
        }
    }

    fun resetRecording() {
        _isRecordingFinished.value = false
        _errorMessage.value = null
        _recordingDuration.value = 0
    }

    fun canSubmit(): Boolean {
        return _isRecordingFinished.value &&
                checkboxNoise.value &&
                checkboxMistakes.value &&
                checkboxGalti.value
    }

    fun submitTask() {
        scope.launch {
            try {
                repository.saveImageDescriptionTask(
                    imageUrl = "http/pixel/coder/guy",
                    audioPath = fileName,
                    durationSec = _recordingDuration.value
                )
                println("Image Description Task saved successfully")
            } catch (e: Exception) {
                _errorMessage.value = "Failed to save task: ${e.message}"
                println("Error saving task: ${e.message}")
            }
        }
    }
}