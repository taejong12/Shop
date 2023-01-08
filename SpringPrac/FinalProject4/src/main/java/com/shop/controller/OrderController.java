package com.shop.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nimbusds.oauth2.sdk.Request;
import com.shop.dto.FilterSearchDTO;
import com.shop.dto.OrderItemDTO;
import com.shop.dto.OrderListDTO;
import com.shop.login.LoginService;
import com.shop.service.OrderService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import net.minidev.json.JSONObject;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/order/{username}")
	public String orderPageGET(@PathVariable("username") String username, OrderListDTO old, Model model) {
		
		System.out.println("username : " + username);
		System.out.println("orders : " + old.getOrders());
		
		model.addAttribute("orderList", orderService.getGoodsInfo(old.getOrders()));
		model.addAttribute("list",orderService.orderPage(username));
		return "order/orderPage2";
	}
	
	@RequestMapping("/orderForm")
	public String orderForm(HttpServletRequest req, Model model) {

		String orTotalAmount = req.getParameter("orTotalAmount");
//		System.out.println(orTotalAmount);
		String orTotalPrice = req.getParameter("orTotalPrice");
//		System.out.println(orTotalPrice);
		String alNum = req.getParameter("alNum");
//		System.out.println("test : "+alNum);
		String alOpic = req.getParameter("alOpic");
//		System.out.println(alOpic);
		String alName = req.getParameter("alName");
//		System.out.println(alName);
		String userName = req.getParameter("userName");
//		System.out.println(userName);
//		Date today = new Date();
//		String orPayDate = new SimpleDateFormat("yyyy년MM월dd일").format(today); 
//		System.out.println(orPayDate);
		
		model.addAttribute("list",orderService.orderPage(userName));
		
		Map<String, Object> orderList = new HashMap<>();
		orderList.put("orTotalAmount",orTotalAmount);
		orderList.put("orTotalPrice",orTotalPrice);
		orderList.put("alNum",alNum);
		orderList.put("alOpic",alOpic);
		orderList.put("alName",alName);
		
		model.addAttribute("orderList", orderList);
		
//		model.addAttribute("orTotalAmount",orTotalAmount);
//		model.addAttribute("orTotalPrice",orTotalPrice);
//		model.addAttribute("alNum", alNum);
//		model.addAttribute("alOpic",alOpic);
//		model.addAttribute("alName",alName);
//		model.addAttribute("orPayDate",orPayDate);
		
		return "/order/orderPage";
	}
	
	@RequestMapping("/payment/callback_receive")
	public ResponseEntity<?> callback_receive(@RequestBody Map<String, Object> model){
		String process_result="결제성공!";
	
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
		JSONObject responseObj = new JSONObject();

		try {
			String imp_uid = (String)model.get("imp_uid");
			String merchant_uid = (String)model.get("merchant_uid");
			boolean success = (Boolean)model.get("success");
			String error_msg = (String)model.get("error_msg");
			
			System.out.println("---callback receive---");
			System.out.println("------");
			System.out.println("imp_uid : "+imp_uid);
			System.out.println("merchant_uid : "+merchant_uid);
			System.out.println("success :" + success);

			if(success == true) {
				
				String api_key ="4642533202032342";
				//REST API Key
				String api_secret ="Icr8FLXyAgye21Cka5laPPTv8vYG6qA97zc17hq8Vq4tcgH2PViqCjHJOC7tVALXQohN5oJjfnXAVY8f";
				//REST API Secret
			
				IamportClient ic = new IamportClient(api_key, api_secret);
				IamportResponse<Payment> response = ic.paymentByImpUid(imp_uid); //결제
				
				BigDecimal iamport_amount = response.getResponse().getAmount();
				//계산 처리 -- 총 결제금액
				
				responseObj.put("process_result", "결제성공");
				
			} else {
				System.out.println("error_msg : " + error_msg);
				responseObj.put("process_result", "결제실패 : " + error_msg);
		
			}
		} catch(Exception e) {
			e.printStackTrace();
			responseObj.put("process_result", "결제실패 : 관리자에게 문의해 주세요.");
		}
		
		return new ResponseEntity<String>(responseObj.toString(), responseHeaders, HttpStatus.OK);

	}
	
	// 웹훅 수신 처리
	@RequestMapping("/payment/webhook_receive")
	public ResponseEntity<?> webhook_receive(@RequestBody Map<String, Object> model){
		HttpHeaders responHeaders = new HttpHeaders();
		responHeaders.add("Content-Type", "application/json; charset=UTF-8");
		
		try {
			String imp_uid = (String) model.get("imp_uid");
			String merchant_uid = (String)model.get("merchant_uid");
			String status = (String)model.get("status");
			
			System.out.println("webhook_receive");
			System.out.println("imp_uid:"+imp_uid);
			System.out.println("merchant_uid:"+merchant_uid);
			System.out.println("status:"+status);
			
			String api_key ="4642533202032342";
			String api_secret ="Icr8FLXyAgye21Cka5laPPTv8vYG6qA97zc17hq8Vq4tcgH2PViqCjHJOC7tVALXQohN5oJjfnXAVY8f";
			IamportClient ic = new IamportClient(api_key, api_secret);
			IamportResponse<Payment> response = ic.paymentByImpUid(imp_uid);
			
			BigDecimal iamport_amount = response.getResponse().getAmount();
			 
			} catch(Exception e) {
				e.printStackTrace();
				return new ResponseEntity(new CustomErrorType("서버처리 오류"), HttpStatus.INTERNAL_SERVER_ERROR); 
			}
			
			return new ResponseEntity<String>("결과반영 성공", responHeaders, HttpStatus.OK);
		
		}
	 
		@ResponseBody
		@RequestMapping("/order/insert")
		public int write(HttpServletRequest req, Model model) {
			String orTotalAmount = req.getParameter("orTotalAmount");
			System.out.println(orTotalAmount);
			String orPayStatus = req.getParameter("orPayStatus");
			System.out.println(orPayStatus);
			String orTotalPrice = req.getParameter("orTotalPrice");
			System.out.println(orTotalPrice);
//			String orPayDate = req.getParameter("orPayDate");
//			System.out.println(orPayDate);
			String orPostNum = req.getParameter("orPostNum");
			System.out.println(orPostNum);
			String orRoadAddress = req.getParameter("orRoadAddress");
			System.out.println(orRoadAddress);
			String orDetailAddress = req.getParameter("orDetailAddress");
			System.out.println(orDetailAddress);
			String alNum = req.getParameter("alNum");
			System.out.println("주류번호 : "+alNum);
			String userName = req.getParameter("userName");
			System.out.println(userName);
			
			Map<String, String> map = new HashMap<String, String>();
			map.put("orTotalAmount", orTotalAmount);
			map.put("orPayStatus", orPayStatus);
			map.put("orTotalPrice", orTotalPrice);
//			map.put("orPayDate", orPayDate);
			map.put("orPostNum", orPostNum);
			map.put("orRoadAddress", orRoadAddress);
			map.put("orDetailAddress", orDetailAddress);
			map.put("alNum", alNum);
			map.put("userName", userName);
			
			int res = orderService.orderInsert(map);
			
			return res;
		}
		
		@RequestMapping("/order/orderSuccess")
		public String form() {
			return "/order/orderSuccess";
		}
	
		@GetMapping("/orderList/{username}")
		public String cartPageGET(@PathVariable("username") String username, Model model) {
			
			model.addAttribute("orderInfo", orderService.getOrderList(username));
			
			return "/order/orderHistory";
		}
		
		
	}
	

