package test.com.wani.talkplus

import com.wani.talkplus.TalkPlusClient
import com.wani.talkplus.request.body.user.CreateUserRequestBody
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TalkPlusClientTest {

    private lateinit var client: TalkPlusClient

    @BeforeEach
    fun setUp() {
        val appKey = "앱 키를 넣으세요"
        val appId = "앱 아이디를 넣으세요"
        client = TalkPlusClient(
            appId = appId,
            appKey = appKey
        )
    }

    @Test
    fun `유저를 생성합니다`() {
        val request = CreateUserRequestBody(
            userId = "testuser",
            password = "testpassword",
            username = "테스트유저",
            profileImageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6T4LUdLJ3e_hboP420w1M9uqeVyUnkeAGrQ&usqp=CAU",
            data = null
        )
        val response = client.createUser("application/json", request)

        assertThat(response).isNotNull
    }

}