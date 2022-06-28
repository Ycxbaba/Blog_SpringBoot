package com.example.blog.scheduled;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.blog.entity.bean.Blog;
import com.example.blog.entity.bean.Stranger;
import com.example.blog.service.BlogService;
import com.example.blog.service.StrangerService;
import com.example.blog.utils.ApiUtils;
import com.example.blog.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Set;

@Async
@Component
@Slf4j
public class RedisScheduled {

	@Resource(name = "redisUtils")
	private RedisUtils redisUtils;

	@Resource(name = "strangerService")
	private StrangerService strangerService;

	@Resource(name = "blogService")
	private BlogService blogService;

	@Resource(name = "ApiUtils")
	private ApiUtils apiUtils;

	/**
	 * 每天23：00 将 ip统计入数据库
	 */
	@Scheduled(cron = "0 0 23 * * ?")
	public void saveIpToMySql(){
		Set<ZSetOperations.TypedTuple<Object>> ipAddress = redisUtils.zsGet("ipAddress");
		UpdateWrapper<Stranger> wrapper = new UpdateWrapper<>();
		if(ipAddress != null && ipAddress.size() != 0){
			for (ZSetOperations.TypedTuple<Object> address : ipAddress) {
				//取值
				Double score = address.getScore();
				String ip = (String) address.getValue();
				//查询地址
				String realAddress = apiUtils.getRealAddress(ip);
				String[] split = realAddress.split(",");
				String province = split[0];
				String city = split[1];
				//封装到Stranger类
				Stranger stranger = new Stranger();
				assert score != null;
				stranger.setCount(score.intValue());
				stranger.setIp(ip);
				stranger.setProvince(province);
				stranger.setCity(city);
				//wrapper
				wrapper.eq("ip",ip);
				wrapper.setSql("count = count + " + score);
				strangerService.saveOrUpdate(stranger,wrapper);
				wrapper.clear();
				log.info("日期:{}--ip:{}--访问了{}次网站",new Date(),ip,score.intValue());
			}
			redisUtils.del("ipAddress");
		}
	}

	/**
	 * 每2个小时执行一次
	 */
	@Scheduled(cron = "0 0 */2 * * ?")
	public void saveViewToMySql(){
		Set<ZSetOperations.TypedTuple<Object>> blogView = redisUtils.zsGet("blogView");
		if(blogView != null && blogView.size() != 0){
			for (ZSetOperations.TypedTuple<Object> view : blogView) {
				Double score = view.getScore();
				int id = (int) view.getValue();
				assert score != null;
				int count = score.intValue();
				blogService.updateView(id,count);
				log.info("日期:{}--博客:{}--被访问了{}次",new Date(),id,score.intValue());
			}
			redisUtils.del("blogView");
		}
	}

	/**
	 * 统计点赞数
	 */
	@Scheduled(cron = "0 0 */2 * * ?")
	public void saveLikeToMySql(){
		Set<ZSetOperations.TypedTuple<Object>> blogLike = redisUtils.zsGet("blogLike");
		UpdateWrapper<Blog> updateWrapper = new UpdateWrapper<>();
		if(blogLike != null && blogLike.size() != 0){
			for (ZSetOperations.TypedTuple<Object> like : blogLike) {
				Double score = like.getScore();
				int id = (int) like.getValue();
				assert score != null;
				int count = score.intValue();
				updateWrapper.eq("id",id).setSql("likes = likes + " + count);
				blogService.update(updateWrapper);
				updateWrapper.clear();
				log.info("日期:{}--博客:{}--被访问了{}次",new Date(),id,score.intValue());
			}
			redisUtils.del("blogLike");
		}
	}

}
