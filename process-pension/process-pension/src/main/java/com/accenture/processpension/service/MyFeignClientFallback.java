//package com.accenture.processpension.service;
//
//import org.springframework.stereotype.Component;
//
//import com.accenture.processpension.Model.PensionerDetail;
//import com.accenture.processpension.client.PensionerDetailCleint;
//
//@Component
//public class MyFeignClientFallback implements PensionerDetailCleint {
//
//
//    @Override
//    public PensionerDetail fallbackMethod() {
////        return "Fallback response";
//    	PensionerDetail p =  new PensionerDetail();
//        return p;
//    }
//
//	@Override
//	public PensionerDetail pensionerDetailByAadhar(String token, String Aadhar) {
//		// TODO Auto-generated method stub
//		return fallbackMethod();
//	}
//}