//package com.kbtu.dukenapp.presentation.features.sign_in
//
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.viewModelScope
//import com.kbtu.dukenapp.domain.model.TextInput
//import com.kbtu.dukenapp.domain.repository.AuthorizationRepository
//import com.kbtu.dukenapp.domain.use_case.CreateAuthTokenUseCase
//import com.kbtu.dukenapp.domain.use_case.ValidateEmail
//import com.kbtu.dukenapp.domain.use_case.ValidateField
//import com.kbtu.dukenapp.presentation.model.ResourceUiState
//import com.kbtu.dukenapp.presentation.model.TextFieldUiState
//import com.kbtu.dukenapp.presentation.model.ValidationResult
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//
//class SigInViewModel(
//    private val validateEmail: ValidateEmail,
//    private val validatePassword: ValidateField,
//    private val repository: AuthorizationRepository,
//    private val createAuthTokenUseCase: CreateAuthTokenUseCase
//) :
//    BaseViewModel<SigInContract.State, SigInContract.Event, SigInContract.Effect>() {
//
//    override fun createInitialState(): SigInContract.State =
//        SigInContract.State(
//            email = TextFieldUiState(
//                input = mutableStateOf(""),
//                validationResult = mutableStateOf(ValidationResult(successful = true)),
//                onFieldChanged = {
//                    validateAllFieldsAndUpdateButtonState(
//                        currentState,
//                        TextInput.EMAIL_ADDRESS
//                    )
//                }
//            ),
//            password = TextFieldUiState(
//                input = mutableStateOf(""),
//                validationResult = mutableStateOf(ValidationResult(successful = true)),
//                onFieldChanged = {
//                    validateAllFieldsAndUpdateButtonState(
//                        currentState,
//                        TextInput.PASSWORD
//                    )
//                }
//            ),
//            loginState = ResourceUiState.Idle
//        )
//
//    override fun handleEvent(event: SigInContract.Event) {
//        when (event) {
//            SigInContract.Event.OnLoginClicked -> {
//                println("Login clicked")
//                loginUser(state.email.input.value, state.password.input.value)
//            }
//        }
//    }
//
//    private fun validateAllFieldsAndUpdateButtonState(
//        currentState: SigInContract.State,
//        fieldChanged: TextInput
//    ) {
//        with(currentState) {
//            val validateEmailResult = validateEmail(email.input.value, email.textInput)
//            val validatePasswordResult = validatePassword(
//                password.input.value,
//                password.textInput
//            )
//
//            when (fieldChanged) {
//                TextInput.EMAIL_ADDRESS -> email.validationResult.value = validateEmailResult
//                TextInput.PASSWORD -> password.validationResult.value = validatePasswordResult
//                else -> {}
//            }
//
//            setState {
//                copy(
//                    isButtonAvailable = validateEmailResult.successful
//                            && validatePasswordResult.successful
//                )
//            }
//        }
//    }
//
//    private fun loginUser(email: String, password: String) {
//        viewModelScope.launch {
//            setState {
//                copy(
//                    loginState = ResourceUiState.Loading
//                )
//            }
//            setEffect {
//                SigInContract.Effect.OnLoading
//            }
//            delay(2000)
//
//            val userId = repository.loginUser(
//                email,
//                password
//            )
//            setEffect {
//                if (userId != null) {
//                    createAuthToken(userId)
//                    return@setEffect SigInContract.Effect.OnUserFetched
//                } else {
//                    return@setEffect SigInContract.Effect.OnUserDoNotExist
//                }
//            }
//        }
//    }
//
//    private fun createAuthToken(userId: Int) {
//        createAuthTokenUseCase(userId)
//    }
//}