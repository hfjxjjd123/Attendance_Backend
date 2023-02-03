package com.proj252.AIstopwatch.proj252.domain

import jakarta.persistence.*

@Entity
class User{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var userId: Long = 0
    @Column
    private var nickname: String? = null

    fun getUserId(): Long{
        return userId
    }
    fun getNickname(): String?{
        return nickname
    }
    fun setNickname(nickname:String?){
        this.nickname = nickname
    }


}