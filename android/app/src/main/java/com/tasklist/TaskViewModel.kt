package com.tasklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _successMessage = MutableStateFlow<String?>(null)
    val successMessage: StateFlow<String?> = _successMessage.asStateFlow()

    init {
        loadTasks()
    }

    fun loadTasks() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val result = RetrofitInstance.api.getTasks()
                _tasks.value = result
            } catch (e: Exception) {
                _errorMessage.value = "Erro ao carregar tarefas: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun createTask(title: String, description: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val newTask = Task(
                    id = 0, // Server will assign ID
                    title = title,
                    description = description,
                    completed = false
                )
                val result = RetrofitInstance.api.createTask(newTask)
                _tasks.value = _tasks.value + result
                _successMessage.value = "Tarefa criada com sucesso!"
            } catch (e: Exception) {
                _errorMessage.value = "Erro ao criar tarefa: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearMessages() {
        _errorMessage.value = null
        _successMessage.value = null
    }
}
