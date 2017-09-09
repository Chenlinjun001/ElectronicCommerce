package com.dlut.ecwebsite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.internal.matchers.Each;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.dlut.ecwebsite.entity.User;
import com.dlut.ecwebsite.service.GoodsService;
import com.dlut.ecwebsite.service.GoodsServiceImpl;
import com.dlut.ecwebsite.service.UserInfoService;
import com.dlut.ecwebsite.service.UserService;

@Controller
public class UserInfoController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserInfoService userInfoService;

	// �޸ı����û���Ϣ
	@RequestMapping(value = "data/saveUserInfo.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveUserInfo(@RequestBody Map<String, String> data, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();

		Integer user_id = (Integer) request.getSession().getAttribute("userID");
		if (user_id == null) {
			return map;
		}
		System.out.println(data.toString());
		User user = new User();
		user.setId(user_id);
		user.setName((String) data.get("user_name"));
		user.setEmail((String) data.get("user_email"));
		user.setTel((String) data.get("user_tel"));
		user.setRealName((String) data.get("user_realname"));
		user.setIdnum((String) data.get("user_idnum"));
		user.setQuestion((String) data.get("safe_question"));
		user.setAnswer((String) data.get("safe_answer"));

		int num = userService.updateUserInfo(user);
		if (num < 0) {
			map.put("state", "fail");
			map.put("data", "");
		} else {
			map.put("state", "success");
			user.setCarts(null);
			user.setOrders(null);
			map.put("data", user);
		}

		return map;
	}

	// ��ȡ�û���Ϣ
	@RequestMapping(value = "data/getUserInfo.do")
	@ResponseBody
	public User getUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String name = (String) request.getSession().getAttribute("username");
		User user = userService.findUserByName(name);
		user.setCarts(null);
		user.setOrders(null);

		return user;
	}

	// ��ȡ���ﳵ��Ϣ
	@RequestMapping(value = "data/getCart.do")
	@ResponseBody
	public List<Map<String, Object>> getCartInfo(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Integer user_id = (Integer) request.getSession().getAttribute("userID");
		if (user_id == null) {
			return null;
		}
		/*
		 * user_id=1; System.out.println("8888888");
		 */
		List<Map<String, Object>> list = userInfoService.getCartInfo(user_id);

		return list;
	}

	// �Թ��ﳵ����
	// 1.���ӻ���
	// 2.�޸Ĺ��ﳵ��������
	// 3.ɾ�����ﳵ����
	@RequestMapping(value = "data/operateCart.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> operateCartInfo(@RequestBody Map<String, Object> data, HttpServletRequest request,
			HttpServletResponse response) {
		Integer user_id = (Integer) request.getSession().getAttribute("userID");
		if (user_id == null || data == null || data.size() == 0) {
			return null;
		}
		System.out.println(data.toString());
		Map<String, Object> map = new HashMap<String, Object>();
		if (data.get("action").equals("add")) {
			String goods_id = (String) data.get("goods_id");
			String amount = (String) data.get("amount");
			int count = userInfoService.addCart(Integer.valueOf(user_id), Integer.valueOf(goods_id),
					Integer.valueOf(amount));

			if (count < 0) {
				map.put("state", "fail");
			} else {
				map.put("state", "success");
				map.put("data", count);
			}

		} else if (data.get("action").equals("modify")) {
			String cart_id = (String) data.get("shop_id");
			String amount = (String) data.get("amount");

			int count = userInfoService.updateCart(Integer.valueOf(cart_id), Integer.valueOf(amount));
			if (count <= 0) {
				map.put("state", "fail");
			} else {
				map.put("state", "success");
				map.put("data", amount);
			}

		} else if (data.get("action").equals("delete")) {
			String cart_id = (String) data.get("shop_id");
			int count = userInfoService.deleteCart(Integer.valueOf(cart_id), user_id);

			if (count < 0) {
				map.put("state", "fail");
			} else {
				map.put("state", "success");
				map.put("data", count);
			}
		} else {
			return null;
		}

		return map;
	}

	// ��ȡ������Ϣ
	@RequestMapping(value = "data/getRegion.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getRegion(@RequestParam(name = "reg_id", defaultValue = "0") int reg_id,
			HttpServletRequest request) throws Exception {

		Integer user_id = (Integer) request.getSession().getAttribute("userID");
		if (user_id == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(reg_id);
		Map<String, List<Map<String, String>>> data = userInfoService.getRegionInfo(reg_id);
		if (data != null) {
			map.put("state", "success");
			map.put("data", data);
		} else {
			map.put("state", "fail");
			map.put("data", "");
		}

		return map;
	}

	// ��ȡ�ջ���ַ�б�
	@RequestMapping(value = "data/getAddress.do", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getAddressList(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Integer user_id = (Integer) request.getSession().getAttribute("userID");
		if (user_id == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> data = userInfoService.getAddressList(user_id);

		if (data != null) {
			map.put("state", "success");
			map.put("data", data);
		} else {
			map.put("state", "fail");
			map.put("data", "");
		}

		return map;

	}

	// �����ַ��Ϣ
	// 1.�����µ��ջ���ַ
	// 2.�޸��ջ���ַ
	// 3.ɾ���ջ���ַ
	// 4.����ΪĬ��
	@RequestMapping(value = "data/operateAddr.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> operateAddr(@RequestBody Map<String, String> reqMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Integer user_id = (Integer) request.getSession().getAttribute("userID");
		if (user_id == null) {
			return null;
		}
		int num = -1;
		
		Map<String, String> map = new HashMap<String, String>();
		
		if (reqMap.get("action").equals("new")) {
			String addr_detail = (String) reqMap.get("addr_detail");
			String addr_zipcode = (String) reqMap.get("addr_zipcode");
			String reg_id = (String) reqMap.get("reg_id");

			num = userInfoService.addAddress(user_id, addr_detail, addr_zipcode, Integer.valueOf(reg_id));

		} else if (reqMap.get("action").equals("modify")) {

			String addr_id = (String) reqMap.get("addr_id");
			String addr_detail = (String) reqMap.get("addr_detail");
			String addr_zipcode = (String) reqMap.get("addr_zipcode");
			String reg_id = (String) reqMap.get("reg_id");

			num = userInfoService.modifyAddress(Integer.valueOf(addr_id), addr_detail, addr_zipcode,
					Integer.valueOf(reg_id));

		} else if (reqMap.get("action").equals("delete")) {

			String addr_id = (String) reqMap.get("addr_id");
			num = userInfoService.deleteAddress(Integer.valueOf(addr_id));

		} else if (reqMap.get("action").equals("default")) {

			String addr_id = (String) reqMap.get("addr_id");

			boolean num2 = userInfoService.setDefaultAddress(Integer.valueOf(addr_id));

			if (num2)
				num = 1;

		} else {

			return null;
		}

		if (num < 0) {
			map.put("state", "fail");
			map.put("data", "");
		} else {
			map.put("state", "success");
			map.put("data", String.valueOf(num));
		}

		return map;
	}

}
