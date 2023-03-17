package com.proj252.AIstopwatch.proj252.controller

import com.proj252.AIstopwatch.proj252.domain.User
import com.proj252.AIstopwatch.proj252.dto.user.SignupDto
import com.proj252.AIstopwatch.proj252.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("user")
//TODO auth를 설정할 때 토큰을 꼭 필요로해야하는.. 당연하지만
class UserController {

    private final var userService: UserService

    @Autowired
    constructor(userService: UserService) {
        this.userService = userService
    }

    @PostMapping("/modify-name")
    @ResponseBody
    fun modifyUsername(@RequestHeader("Authorization") authToken: String, @RequestBody newName: String): String {

        val base64Credentials = authToken.substring(6)
        val credentials = String(Base64.getDecoder().decode(base64Credentials))
        //TODO 0번째 항목에 username = uid가 오는지 확인해야한다.
        val uid: Long = credentials.split(":")[0].toLong()

        userService.modifyUsername(uid, newName)?.let {
            return "success - $it"
        } ?: run {
            return "failed"
        }

        //통신시 List를 통째로 보내고 client-side에서 해당 메시지(Stinrg)을 객체로 파싱하는 과정을 거쳐야한다.
    }
    @PostMapping("/create")
    @ResponseBody
    fun createUser(@RequestBody signupDto: SignupDto): String {

        userService.createUser(signupDto.id, signupDto.password, signupDto.name)?.let {
            //TODO 회원가입시 토큰을 넘겨줘야지? NO 회원가입과 로그인을 분리하자.
            return "success"
        } ?: kotlin.run {
            return "falied"
        }

        //통신시 List를 통째로 보내고 client-side에서 해당 메시지(Stinrg)을 객체로 파싱하는 과정을 거쳐야한다.
    }

}