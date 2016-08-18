/**
 *柏红春
 *Wed Nov 12 12:05:04 CST 2014
 */
package com.pj.bean;

import java.io.Serializable;
import com.base.ModelBase;
import java.lang.String;
import java.lang.Integer;
import java.util.Date;

@SuppressWarnings("serial")
public class QlyLine extends ModelBase implements Serializable {
	/**
	 * 主键编号
	 */
	private Integer id;
	/**
	 * 线路编号
	 */
	private String lineid;
	/**
	 * 路线价格
	 */
	private Integer lineprice;
	/**
	 * 儿童价格
	 */
	private Integer childprice;
	/**
	 * 房差价格
	 */
	private Integer roompricediff;
	/**
	 * 线路名称
	 */
	private String linename;
	/**
	 * 线路副标题
	 */
	private String titletuniu;
	/**
	 * 适用平台
	 */
	private String platformtype;
	/**
	 * 参团性质
	 */
	private String joinattribute;
	/**
	 * 目的地类型
	 */
	private String destinationtype;
	/**
	 * 集合地名称
	 */
	private String placename;
	/**
	 * 集合地编号
	 */
	private String placeid;
	/**
	 * 返回地名称
	 */
	private String backplacename;
	/**
	 * 返回地编号
	 */
	private String backplaceid;
	/**
	 * 途经城市
	 */
	private String passcity;
	/**
	 * 线路主题
	 */
	private String linesubject;
	/**
	 * 经路性质
	 */
	private String attribute;
	/**
	 * 经路推荐理由
	 */
	private String commreason;
	/**
	 * 节日活动
	 */
	private String festivalact;
	/**
	 * 线路标准
	 */
	private String linelevel;
	/**
	 * 来程大交通
	 */
	private String comeviehcle;
	/**
	 * 来程出发时间
	 */
	private String viehcletime;
	/**
	 * 回程大交通
	 */
	private String backviehcle;
	/**
	 * 回程出发时间
	 */
	private String backviehcletime;
	/**
	 * 几天
	 */
	private String daynum;
	/**
	 * 几晚
	 */
	private String nightnum;
	/**
	 * 线路图片
	 */
	private String journeypic;
	/**
	 * 线路特色
	 */
	private String introduce;
	/**
	 * 费用包含
	 */
	private String costinfo;
	/**
	 * 费用不含
	 */
	private String costnothave;
	/**
	 * 温馨提示
	 */
	private String attention;
	/**
	 * 预订须知
	 */
	private String prebook;
	/**
	 * 退订说明
	 */
	private String calbook;
	/**
	 * 状态
	 */
	private Integer state;
	/**
	 * 添加时间
	 */
	private Date credate;
	/**
	 * 提前报名
	 */
	private String sign;
	/**
	 * 自助设计
	 */
	private Integer isown;
	/**
	 * 记录添加人
	 */
	private String enman;
	/**
	 * 加了多少自定义城市
	 */
	private Integer customcount;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setLineid(String lineid) {
		this.lineid = lineid;
	}

	public String getLineid() {
		return this.lineid;
	}

	public void setLineprice(Integer lineprice) {
		this.lineprice = lineprice;
	}

	public Integer getLineprice() {
		return this.lineprice;
	}

	public Integer getChildprice() {
		return childprice;
	}

	public void setChildprice(Integer childprice) {
		this.childprice = childprice;
	}

	public Integer getRoompricediff() {
		return roompricediff;
	}

	public void setRoompricediff(Integer roompricediff) {
		this.roompricediff = roompricediff;
	}

	public void setLinename(String linename) {
		this.linename = linename;
	}

	public String getLinename() {
		return this.linename;
	}

	public String getTitletuniu() {
		return titletuniu;
	}

	public void setTitletuniu(String titletuniu) {
		this.titletuniu = titletuniu;
	}

	public String getPlatformtype() {
		return platformtype;
	}

	public void setPlatformtype(String platformtype) {
		this.platformtype = platformtype;
	}

	public void setJoinattribute(String joinattribute) {
		this.joinattribute = joinattribute;
	}

	public String getJoinattribute() {
		return this.joinattribute;
	}

	public void setDestinationtype(String destinationtype) {
		this.destinationtype = destinationtype;
	}

	public String getDestinationtype() {
		return this.destinationtype;
	}

	public void setPlacename(String placename) {
		this.placename = placename;
	}

	public String getPlacename() {
		return this.placename;
	}

	public void setPlaceid(String placeid) {
		this.placeid = placeid;
	}

	public String getPlaceid() {
		return this.placeid;
	}

	public String getBackplacename() {
		return backplacename;
	}

	public void setBackplacename(String backplacename) {
		this.backplacename = backplacename;
	}

	public String getBackplaceid() {
		return backplaceid;
	}

	public void setBackplaceid(String backplaceid) {
		this.backplaceid = backplaceid;
	}

	public void setPasscity(String passcity) {
		this.passcity = passcity;
	}

	public String getPasscity() {
		return this.passcity;
	}

	public void setLinesubject(String linesubject) {
		this.linesubject = linesubject;
	}

	public String getLinesubject() {
		return this.linesubject;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getAttribute() {
		return this.attribute;
	}

	public String getCommreason() {
		return commreason;
	}

	public void setCommreason(String commreason) {
		this.commreason = commreason;
	}

	public void setFestivalact(String festivalact) {
		this.festivalact = festivalact;
	}

	public String getFestivalact() {
		return this.festivalact;
	}

	public void setLinelevel(String linelevel) {
		this.linelevel = linelevel;
	}

	public String getLinelevel() {
		return this.linelevel;
	}

	public String getComeviehcle() {
		return comeviehcle;
	}

	public void setComeviehcle(String comeviehcle) {
		this.comeviehcle = comeviehcle;
	}

	public String getViehcletime() {
		return viehcletime;
	}

	public void setViehcletime(String viehcletime) {
		this.viehcletime = viehcletime;
	}

	public String getBackviehcle() {
		return backviehcle;
	}

	public void setBackviehcle(String backviehcle) {
		this.backviehcle = backviehcle;
	}

	public String getBackviehcletime() {
		return backviehcletime;
	}

	public void setBackviehcletime(String backviehcletime) {
		this.backviehcletime = backviehcletime;
	}

	public void setDaynum(String daynum) {
		this.daynum = daynum;
	}

	public String getDaynum() {
		return this.daynum;
	}

	public void setNightnum(String nightnum) {
		this.nightnum = nightnum;
	}

	public String getNightnum() {
		return this.nightnum;
	}

	public void setJourneypic(String journeypic) {
		this.journeypic = journeypic;
	}

	public String getJourneypic() {
		return this.journeypic;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getIntroduce() {
		return this.introduce;
	}

	public void setCostinfo(String costinfo) {
		this.costinfo = costinfo;
	}

	public String getCostinfo() {
		return this.costinfo;
	}

	public void setCostnothave(String costnothave) {
		this.costnothave = costnothave;
	}

	public String getCostnothave() {
		return this.costnothave;
	}

	public void setAttention(String attention) {
		this.attention = attention;
	}

	public String getAttention() {
		return this.attention;
	}

	public void setPrebook(String prebook) {
		this.prebook = prebook;
	}

	public String getPrebook() {
		return this.prebook;
	}

	public void setCalbook(String calbook) {
		this.calbook = calbook;
	}

	public String getCalbook() {
		return this.calbook;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getState() {
		return this.state;
	}

	public void setCredate(Date credate) {
		this.credate = credate;
	}

	public Date getCredate() {
		return this.credate;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSign() {
		return this.sign;
	}

	public void setIsown(Integer isown) {
		this.isown = isown;
	}

	public Integer getIsown() {
		return this.isown;
	}

	public void setEnman(String enman) {
		this.enman = enman;
	}

	public String getEnman() {
		return this.enman;
	}

	public Integer getCustomcount() {
		return customcount;
	}

	public void setCustomcount(Integer customcount) {
		this.customcount = customcount;
	}

	@Override
	public String toString() {
		return "QlyLine [id=" + id + ", lineid=" + lineid + ", lineprice=" + lineprice + ", childprice=" + childprice + ", roompricediff=" + roompricediff + ", linename=" + linename + ", joinattribute=" + joinattribute
				+ ", destinationtype=" + destinationtype + ", placename=" + placename + ", placeid=" + placeid + ", passcity=" + passcity + ", linesubject=" + linesubject + ", attribute=" + attribute + ", commreason="
				+ commreason + ", festivalact=" + festivalact + ", linelevel=" + linelevel + ", comeviehcle=" + comeviehcle + ", viehcletime=" + viehcletime + ", backviehcle=" + backviehcle + ", backviehcletime="
				+ backviehcletime + ", daynum=" + daynum + ", nightnum=" + nightnum + ", journeypic=" + journeypic + ", introduce=" + introduce + ", costinfo=" + costinfo + ", costnothave=" + costnothave + ", attention="
				+ attention + ", prebook=" + prebook + ", calbook=" + calbook + ", state=" + state + ", credate=" + credate + ", sign=" + sign + ", isown=" + isown + ", enman=" + enman + ", customcount=" + customcount
				+ "]";
	}

}
