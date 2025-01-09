package com.codegalaxy.mock8janss.viewmodel

import NoInternetException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codegalaxy.mock8janss.UiState
import com.codegalaxy.mock8janss.model.dto.User
import com.codegalaxy.mock8janss.model.repository.IRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: IRepository
): ViewModel() {


    private val _uiState = MutableStateFlow<UiState<List<User>>>(UiState.Loading)
    val uiState=_uiState.asStateFlow()

    init {
        fetchUsers()
    }

    fun fetchUsers() {

        viewModelScope.launch {

            _uiState.value=UiState.Loading

            try{
                repository.getUsers().collect{result->
                    result.fold(
                        onSuccess = {users->
                            _uiState.value=UiState.Success(users)
                        },
                        onFailure = {exception ->
                            _uiState.value=UiState.Error(
                                when(exception)
                                {
                                    is NoInternetException->"No internet connection"
                                    else -> exception.message?:"Unknown error"
                                }
                            )

                        }
                    )
                }

            }catch (e:Exception)
            {
                _uiState.value=UiState.Error(e.message?:"Unknown Error occured")
            }

        }

    }
}