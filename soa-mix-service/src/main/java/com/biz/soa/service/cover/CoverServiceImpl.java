package com.biz.soa.service.cover;

import com.biz.service.AbstractBaseService;
import com.biz.service.cover.CoverService;
import com.biz.vo.cover.CoverReqVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newLinkedHashMap;

/**
 * CoverServiceImpl
 * <p/>
 * Created by defei on 2017/05/17 12:11.
 */
@Service
public class CoverServiceImpl extends AbstractBaseService implements CoverService {

	private static final Logger logger = LoggerFactory.getLogger(CoverServiceImpl.class);

	@Override
	public Object getHomePage(CoverReqVO reqVo) {

		return mockHomePageData();
	}

	private List<Map<String, Object>> mockHomePageData(){

		List<Map<String, Object>> homePageDataList = newArrayList();
		homePageDataList.add(mockSliderBar());
		homePageDataList.add(mockImageNavigation());
		homePageDataList.add(mockImageShowcase());
		homePageDataList.add(mockImageNavigation());
		homePageDataList.add(mockProductList());
		return homePageDataList;
	}

	private Map<String, Object> mockSliderBar() {

		Map<String, Object> topSliderBar = newLinkedHashMap();
		topSliderBar.put("type", "SlideBanner");
		List<Map<String, String>> items = newArrayList();
		items.add(mockItem("http://static.depotnextdoor.com/b2b/10.20update/new2.png",
		  "depotnearby://redirect?target=searchResult&postData={\"categoryId\":\"\",\"keyword\":\"超级新品\",\"sort\":\"salesVolume\"}"));
		items.add(mockItem("http://static.depotnextdoor.com/b2b/activity/11.16gaoxiaofeizheshu/banner%e5%91%8a%e6%b6%88%e8%b4%b9%e8%80%85.jpg",
		  "http://static.depotnextdoor.com/b2b/activity/2016-06-29/saleout-1.html"));
		items.add(mockItem("http://static.depotnextdoor.com/b2b/9.1/%e9%87%91%e8%9e%8dBANNER.jpg", null));
		items.add(mockItem("http://static.depotnextdoor.com/b2b/8.1/%e8%80%81%e5%b8%a6%e6%96%b0BANNER.jpg", null));
		topSliderBar.put("items", items);
		return topSliderBar;
	}

	private Map<String, Object> mockImageShowcase() {

		Map<String, Object> imageShowcase = newLinkedHashMap();
		imageShowcase.put("type", "ImageShowcase");
		imageShowcase.put("left", mockItem("https://static.depotnextdoor.com/b2b/7.25shouyecangkurexiao/1461801862.jpg", null));
		imageShowcase.put("rightTop", mockItem("https://static.depotnextdoor.com/b2b/8.15%e6%b4%8b%e9%85%92%e8%b6%b4/dujiashangpin.jpg", null));
		imageShowcase.put("rightBottom", mockItem("https://static.depotnextdoor.com/b2b/%e6%b7%98%e5%ae%9d%e5%8c%ba8.25/%e6%b7%98%e5%ae%9d%e5%8c%ba.png", null));
		return imageShowcase;
	}

	private Map<String, Object> mockImageNavigation() {

		Map<String, Object> imageNavigationList = newLinkedHashMap();
		imageNavigationList.put("type", "ImageNavigation");
		List<Map<String, String>> imageNavigationListItems = newArrayList();
		imageNavigationListItems.add(mockItem("https://static.depotnextdoor.com/b2b/7.25shouyecangkurexiao/%e9%a3%9e%e5%a4%a9%e8%8c%85%e5%8f%b0.png",
		  null));
		imageNavigationListItems.add(mockItem("https://static.depotnextdoor.com/b2b/9.1/1618.png", null));
		imageNavigationListItems.add(mockItem("https://static.depotnextdoor.com/b2b/7.25shouyecangkurexiao/keluona.png", null));
		imageNavigationList.put("items", imageNavigationListItems);
		return imageNavigationList;
	}

	private Map<String, Object> mockProductList() {

		Map<String, Object> productList = newLinkedHashMap();
		productList.put("type", "ProductList");
		List<Map<String, String>> productListItems = newArrayList();
		productListItems.add(mockTextAbleItem("https://static.depotnextdoor.com/b2b/7.25shouyecangkurexiao/%e9%a3%9e%e5%a4%a9%e8%8c%85%e5%8f%b0.png", null, "飞天茅台"));
		productListItems.add(mockTextAbleItem("https://static.depotnextdoor.com/b2b/9.1/1618.png", null, "五粮液1618"));
		productListItems.add(mockTextAbleItem("https://static.depotnextdoor.com/b2b/7.25shouyecangkurexiao/keluona.png", null, "科罗娜六瓶装"));
		productListItems.add(mockTextAbleItem("https://static.depotnextdoor.com/b2b/9.1/%e6%96%af%e8%92%82%e8%8a%ac%e5%b8%83%e6%9c%97%e9%bb%84.png", null, "斯蒂芬黄啤"));
		productList.put("items", productListItems);
		return productList;
	}

	private Map<String, String> mockItem(String imgUrl, String link){

		LinkedHashMap<String, String> item = newLinkedHashMap();
		item.put("imgUrl", imgUrl);
		item.put("link", link);
		return item;
	}

	private Map<String, String> mockTextAbleItem(String imgUrl, String link, String text){

		Map<String, String> mockItem = mockItem(imgUrl, link);
		mockItem.put("text", text);
		return mockItem;
	}
}
