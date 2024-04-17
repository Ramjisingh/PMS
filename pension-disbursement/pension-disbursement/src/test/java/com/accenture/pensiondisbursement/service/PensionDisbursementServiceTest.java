//package com.accenture.pensiondisbursement.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.when;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.accenture.pensiondisbursement.model.ProcessPensionOutput;
//import com.accenture.pensiondisbursement.repository.PensionDisbursementRepository;
//
//
//@SpringBootTest
//public class PensionDisbursementServiceTest {
//	    @Mock
//	    private PensionDisbursementRepository repo;
//	    @InjectMocks
//	    private PensionDisbursementService service;
//	    @Test
//		public void contextLoads() {
//			assertNotNull(service);
//		}
//
//	    @Test
//	    public void testSave() {
//	    	ProcessPensionOutput p =  new ProcessPensionOutput("123456789",50000.0,"Saalry is disbursed");
//	    	 when(repo.save(p)).thenReturn(p);
//			assertEquals(service.save(p),p);
//		}
//}