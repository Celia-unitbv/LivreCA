package com.example.livreca.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.livreca.data.User
import com.example.livreca.data.UserDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(private val userDao: UserDao) : ViewModel() {

    private val _currentUser = MutableLiveData<User?>()
    val currentUser: LiveData<User?> = _currentUser

    fun fetchCurrentUser() {
        viewModelScope.launch(Dispatchers.IO) {
            // Implement logic to fetch current user
            // For demonstration, assuming some user is logged in
            val user = userDao.getUserById(1) // Replace with actual user ID
            _currentUser.postValue(user)
        }
    }
}