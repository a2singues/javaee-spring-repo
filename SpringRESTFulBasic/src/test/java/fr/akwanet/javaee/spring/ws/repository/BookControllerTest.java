package fr.akwanet.javaee.spring.ws.repository;

import java.net.URI;

import javax.annotation.Resource;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import fr.akwanet.javaee.spring.ws.config.ContextConfig;
import fr.akwanet.javaee.spring.ws.config.WebMvcConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ContextConfig.class, WebMvcConfig.class})
@WebAppConfiguration
public class BookControllerTest {

    @Resource 
    private WebApplicationContext wac;
    
    @Resource
    private BookRepository bookRepositoryMock;
    
    private MockMvc mockMvc;
    
    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
   }
    
   @Test
   public void checkGetBookByIdUrl() throws Exception{
       mockMvc.perform(MockMvcRequestBuilders.get("/api/book/1"))
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andDo(System.out::print);
   }

   @Test
   public void checkGetBookByIdUrlKO() throws Exception{
//	   try {
       mockMvc.perform(MockMvcRequestBuilders.get("/api/book/999"))
              .andExpect(MockMvcResultMatchers.status().isNoContent())
              .andExpect(MockMvcResultMatchers.content().string(""));
       
//       String jsonResponse = this.mockMvc.perform(MockMvcRequestBuilders.get(new URI("/api/book/999"))
//               .contentType(MediaType.APPLICATION_JSON)
//               .accept(MediaType.APPLICATION_JSON_VALUE)
//       ).andExpect(MockMvcResultMatchers.status()
//    		   .isOk())
//    		   .andReturn()
//    		   .getResponse()
//    		   .getContentAsString();
//	   }catch (Exception e) {
//		e.printStackTrace();
//	   }
   }

   @Test
   public void checkGetBooksUrl() throws Exception{
       mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
              .andExpect(MockMvcResultMatchers.status().isOk());
   }

   @Test
   public void checkGetBooksUrlJson() throws Exception{
	   MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/books").contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$[0].isbn", Matchers.is("9782212124217")))
              .andReturn();
	   System.out.println("MvcResult="+result.getResponse().getContentAsString());
   }

   @Test
   public void getBooks() throws Exception{
       mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
              .andExpect(MockMvcResultMatchers.status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(11)));
    }
}
