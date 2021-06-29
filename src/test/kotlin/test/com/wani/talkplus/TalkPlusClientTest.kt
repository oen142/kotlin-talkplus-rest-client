package test.com.wani.talkplus

import com.wani.talkplus.TalkPlusClient
import org.junit.jupiter.api.BeforeEach

class TalkPlusClientTest {

    lateinit var client: TalkPlusClient

    @BeforeEach
    fun setUp() {
        val appKey = ""
        val appId = ""
        client = TalkPlusClient(
            appId = appId,
            appKey = appKey
        )
    }
}