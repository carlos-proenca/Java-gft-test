package br.com.gft.avaliation.product;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductControllerTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void getProductTest() throws Exception {
    	
    	   RequestBuilder request = MockMvcRequestBuilders
                   .get("/api/products/1");
                
           MvcResult result = mockMvc.perform(request)
                   .andExpect(status().isOk())
                   .andReturn();
    	
    }

}
