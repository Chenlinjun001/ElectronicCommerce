package com.dlut.ecwebsite.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dlut.ecwebsite.entity.JsonGoods;

@Service
public interface GoodsService {

	// ȫ����Ʒչʾ
	public JsonGoods[] getGoodsAll(int start,int end,String ordersBy);

	// ��Ʒ��ҳ���أ����Ҽ۸������������Ʒ����˳��
	public JsonGoods[] getGoodsPageByOrderbyID(int start, int end, int cate_id);

	// ��Ʒ��ҳ���أ���������������������˳��
	public JsonGoods[] getGoodsPageByOrderBySale(int start, int end, int cate_id);

	// ��Ʒ��ҳ���أ��������۽�������˳��
	public JsonGoods[] getGoodsPageByOrderByComment(int start, int end, int cate_id);

	// ��Ʒ��ҳ���أ����Ҽ۸���������˳��
	public JsonGoods[] getGoodsPageByOrderByPrice(int start, int end, int cate_id, String ordersBy);

	// ����cate_id��path�����з���
	public Map<String, List<Map<String, String>>> findPathByCate(int id);
	
	//��Ʒ����ҳ
	public Map<String, Object> getGoodsDetail(int id);

}
