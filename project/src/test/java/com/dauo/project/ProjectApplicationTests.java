package com.dauo.project;

import com.dauo.project.domain.authenticate.TokenDto;
import com.dauo.project.domain.channel.ChannelService;
import com.dauo.project.domain.user.User;
import com.dauo.project.domain.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class ProjectApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ChannelService channelService;
    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("인증키 없이 api 요청")
    public void getChannel() throws Exception {
        this.mockMvc.perform(get("/api/v1/channel"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("토큰값 가져오기")
    public void getToken() throws Exception {
        String content = objectMapper.writeValueAsString(new TokenDto.Request("dauo", "dauo"));

        this.mockMvc.perform(post("/api/v1/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andDo(print());
    }

    @Test
    @DisplayName("사용자정보 가져오기")
    void findUser() {
        Optional<User> match = userRepository.findByUserName("ejkim");
        if (match.isPresent()) {
            assertThat(match.get().getUserId()).isEqualTo("ejkim");
            assertThat(match.get().getEmail()).isEqualTo("ejkim@gmail.com");
        }
    }

}
