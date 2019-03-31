package com.sy.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sy.mapper.CarouselMapper;
import com.sy.pojo.Carousel;
import com.sy.service.CarouselService;
import com.sy.utils.PageModel;
@Service
public class CarouselServiceImpl  implements CarouselService{
	@Autowired
	private CarouselMapper carouselMapper;

	@Override
	public boolean addCarousel(Carousel c) {
		c.setCreatetime(new Date());
		Integer num =carouselMapper.insertSelective(c);
		if(num !=0){
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public boolean deleteCarousel(Integer id) {
		Integer num =carouselMapper.deleteByPrimaryKey(id);
		if(num !=0){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean updateCarousel(Carousel c) {
		Integer num =carouselMapper.updateByPrimaryKeySelective(c);
		if(num !=0){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public PageModel<Carousel> getusersone(Integer pageNo, String keyWord) {

		if(pageNo == null ||  pageNo.intValue() == 0){
			pageNo=1;
		}
		 //获取数据总数
		    Integer count=carouselMapper.getcount(keyWord);
		    Integer pageSize=10;
		    List<Carousel>Feedbacks = null;
		    Integer pageNo1 = ( pageNo - 1) * pageSize;
		    //获取页数
		    HashMap<String, Object> parameter = new HashMap<>();
		    parameter.put("pageNo", pageNo1);
		    parameter.put("keyWord", keyWord);
		    parameter.put("pageSize", pageSize);
		    Feedbacks = carouselMapper.list(parameter);
		    
		    PageModel<Carousel> pageModel = new PageModel<Carousel>(pageNo, pageSize,count, Feedbacks,"carousel/list");
		if(pageModel.getCount() !=0){
			pageModel.init();
		}
		return pageModel;
	}

	@Override
	public Carousel getCarousel(Integer id) {
		// TODO Auto-generated method stub
		return carouselMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Carousel> selectcarousel() {
		// TODO Auto-generated method stub
		return carouselMapper.selectcarousel();
	}
}
