/**
 *柏红春
 *Thu Nov 20 22:59:21 CST 2014
 */
package com.pj.bean;

import java.io.Serializable;
import com.base.ModelBase;
import java.lang.String;
import java.lang.Integer;
import java.util.Date;

@SuppressWarnings("serial")
public class QlyView extends ModelBase implements Serializable {
	/**
	 *主键ＩＤ
	 */
	private Integer id;
	/**
	 *景点名称
	 */
	private String viewname;
	/**
	 *分类编号
	 */
	private String typeid;
	/**
	 *分类名称
	 */
	private String typename;
	/**
	 *副标题
	 */
	private String subviewname;
	/**
	 *所属城市编号
	 */
	private String cityid;
	/**
	 *城市名称
	 */
	private String cityname;
	/**
	 *景点编号
	 */
	private String viewid;
	/**
	 *景区地址
	 */
	private String adress;
	/**
	 *景区级别
	 */
	private String level;
	/**
	 *景区类型
	 */
	private String viewtype;
	/**
	 *适宜人群
	 */
	private String suitable;
	/**
	 *景区图片
	 */
	private String viewpic;
	/**
	 *简要描述
	 */
	private String vdescribe;
	/**
	 *景区介绍
	 */
	private String introduce;
	/**
	 *交通指南
	 */
	private String traffic;
	/**
	 *购票须知
	 */
	private String ticketnotice;
	/**
	 *特色购物
	 */
	private String speshop;
	/**
	 *特色美食
	 */
	private String spefood;
	/**
	 *状态
	 */
	private Integer state;
	/**
	 *是否推荐
	 */
	private Integer isrecommend;
	/**
	 *国内/国外
	 */
	private Integer inorout;
	/**
	 *是否热点
	 */
	private Integer ishot;
	/**
	 *首页推荐
	 */
	private Integer istop;
	/**
	 *联系方式
	 */
	private String linkway;
	/**
	 *排序
	 */
	private Integer ordernum;
	/**
	 *创建时间
	 */
	private Date cdate;
	/**
	 *用户编号
	 */
	private String userid;
	/**
	 *用户名称
	 */
	private String username;
	/**
	 *景点视频
	 */
	private String videourl;
	/**
	 *景点点击量
	 */
	private Integer clickcount;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setViewname(String viewname) {
		this.viewname = viewname;
	}

	public String getViewname() {
		return this.viewname;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getTypeid() {
		return this.typeid;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getTypename() {
		return this.typename;
	}

	public void setSubviewname(String subviewname) {
		this.subviewname = subviewname;
	}

	public String getSubviewname() {
		return this.subviewname;
	}

	public void setCityid(String cityid) {
		this.cityid = cityid;
	}

	public String getCityid() {
		return this.cityid;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public String getCityname() {
		return this.cityname;
	}

	public void setViewid(String viewid) {
		this.viewid = viewid;
	}

	public String getViewid() {
		return this.viewid;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getAdress() {
		return this.adress;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevel() {
		return this.level;
	}

	public void setViewtype(String viewtype) {
		this.viewtype = viewtype;
	}

	public String getViewtype() {
		return this.viewtype;
	}

	public void setSuitable(String suitable) {
		this.suitable = suitable;
	}

	public String getSuitable() {
		return this.suitable;
	}

	public void setViewpic(String viewpic) {
		this.viewpic = viewpic;
	}

	public String getViewpic() {
		return this.viewpic;
	}

	public void setVdescribe(String vdescribe) {
		this.vdescribe = vdescribe;
	}

	public String getVdescribe() {
		return this.vdescribe;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getIntroduce() {
		return this.introduce;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	public String getTraffic() {
		return this.traffic;
	}

	public void setTicketnotice(String ticketnotice) {
		this.ticketnotice = ticketnotice;
	}

	public String getTicketnotice() {
		return this.ticketnotice;
	}

	public void setSpeshop(String speshop) {
		this.speshop = speshop;
	}

	public String getSpeshop() {
		return this.speshop;
	}

	public void setSpefood(String spefood) {
		this.spefood = spefood;
	}

	public String getSpefood() {
		return this.spefood;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getState() {
		return this.state;
	}

	public void setIsrecommend(Integer isrecommend) {
		this.isrecommend = isrecommend;
	}

	public Integer getIsrecommend() {
		return this.isrecommend;
	}

	public void setInorout(Integer inorout) {
		this.inorout = inorout;
	}

	public Integer getInorout() {
		return this.inorout;
	}

	public void setIshot(Integer ishot) {
		this.ishot = ishot;
	}

	public Integer getIshot() {
		return this.ishot;
	}

	public void setIstop(Integer istop) {
		this.istop = istop;
	}

	public Integer getIstop() {
		return this.istop;
	}

	public void setLinkway(String linkway) {
		this.linkway = linkway;
	}

	public String getLinkway() {
		return this.linkway;
	}

	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}

	public Integer getOrdernum() {
		return this.ordernum;
	}

	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}

	public Date getCdate() {
		return this.cdate;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}

	public String getVideourl() {
		return this.videourl;
	}

	public void setClickcount(Integer clickcount) {
		this.clickcount = clickcount;
	}

	public Integer getClickcount() {
		return this.clickcount;
	}

	@Override
	public String toString() {
		return ";id:" + id + ";viewname:" + viewname + ";typeid:" + typeid
				+ ";typename:" + typename + ";subviewname:" + subviewname
				+ ";cityid:" + cityid + ";cityname:" + cityname + ";viewid:"
				+ viewid + ";adress:" + adress + ";level:" + level
				+ ";viewtype:" + viewtype + ";suitable:" + suitable
				+ ";viewpic:" + viewpic + ";vdescribe:" + vdescribe
				+ ";introduce:" + introduce + ";traffic:" + traffic
				+ ";ticketnotice:" + ticketnotice + ";speshop:" + speshop
				+ ";spefood:" + spefood + ";state:" + state + ";isrecommend:"
				+ isrecommend + ";inorout:" + inorout + ";ishot:" + ishot
				+ ";istop:" + istop + ";linkway:" + linkway + ";ordernum:"
				+ ordernum + ";cdate:" + cdate + ";userid:" + userid
				+ ";username:" + username + ";videourl:" + videourl
				+ ";clickcount:" + clickcount;
	}
}
