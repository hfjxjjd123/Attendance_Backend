package com.proj252.AIstopwatch.proj252.service

import org.springframework.stereotype.Service

@Service
class AuthService {
    public fun signin(){
        getAuthState()
        connectAuth()
    }
    public fun signout(){
        getAuthState()
        disconnectAuth()
    }
    public fun register(){
        getAuthState()
        validateDupId()
        registerMember()
    }
    public fun unregister(){
        getAuthState()
        unregisterMember()
    }


    private fun getAuthState(){

    }
    private fun connectAuth(){

    }
    private fun disconnectAuth(){

    }
    private fun registerMember(){

    }
    private fun unregisterMember(){

    }

    private fun validateDupId(){

    }

}