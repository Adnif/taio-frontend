package com.taio.taio.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taio.taio.R
import com.taio.taio.data.DataSource
import com.taio.taio.domain.model.User
import com.taio.taio.domain.model.UserRequested
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val _searchTextState: MutableState<String> = mutableStateOf("")
    val searchTextState: State<String> = _searchTextState

    fun updateSearchTextState(newValue: String){
        _searchTextState.value = newValue
    }

    fun searchList(searchQuery: String): List<Any> {
        val userList = searchUser(searchQuery)
        val docList = searchDocument(searchQuery)
        val searchList = userList + docList
        return searchList

    }
    fun searchUser(searchQuery: String): List<User> {
        val data = DataSource().loadUser()
        val newList = data.filter {
            it.name.contains(searchQuery, true) || it.userName.contains(searchQuery, true)
        }
        return newList
    }
    fun searchDocument(searchQuery: String): List<UserRequested>{
        val data = DataSource().loadRequested()
        val docList = data.filter{
            it.name.contains(searchQuery, true)
        }
        return docList
    }

//    private val _user: MutableState<User> = mutableStateOf(User(R.drawable.avatar, ""))
//    val user: State<User> = _user
//
//    fun otherProfile(newUser: User){
//        _user.value = newUser
//    }
//    var user by mutableStateOf<User?>(null)
//        private set
//    fun userProfile(newUser: User){
//        user = newUser
//    }
}