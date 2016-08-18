/**
 *柏红春
 *Sun Aug 09 17:08:16 CST 2015
 */
package com.pj.bean;

import java.io.Serializable;
import com.base.ModelBase;
import java.lang.String;
import java.lang.Integer;
import java.util.Date;

@SuppressWarnings("serial")
public class QlyFlyticket extends ModelBase implements Serializable {
	/**
	 * 自增主键
	 */
	private Integer id;
	/**
	 * 航班号
	 */
	private String fltno;
	/**
	 * 航空公司
	 */
	private String aireline;
	/**
	 * 机型
	 */
	private String airtype;
	/**
	 * 出发机场
	 */
	private String startairport;
	/**
	 * 到达机场
	 */
	private String arriveairport;
	/**
	 * 出发城市
	 */
	private String startcity;
	private String startcityjm;
	/**
	 * 到达城市
	 */
	private String arrivecity;
	private String arrivecityjm;
	/**
	 * 出发时间
	 */
	private Date starttime;
	/**
	 * 到达时间
	 */
	private Date arrivetime;
	/**
	 * 偏差率
	 */
	private String offsetrate;
	/**
	 * 偏差时间
	 */
	private String offsettime;
	/**
	 * 机票价格
	 */
	private Integer ticketprice;
	private Integer avgticketprice;
	/**
	 * 售票代理点
	 */
	private String agent;
	/**
	 * 机票日期
	 */
	private Date fetchdate;
	/**
	 * 操作时间
	 */
	private Date opttime;
	private String typetime;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setFltno(String fltno) {
		this.fltno = fltno;
	}

	public String getFltno() {
		return this.fltno;
	}

	public void setAireline(String aireline) {
		this.aireline = aireline;
	}

	public String getAireline() {
		return this.aireline;
	}

	public void setAirtype(String airtype) {
		this.airtype = airtype;
	}

	public String getAirtype() {
		return this.airtype;
	}

	public void setStartairport(String startairport) {
		this.startairport = startairport;
	}

	public String getStartairport() {
		return this.startairport;
	}

	public void setArriveairport(String arriveairport) {
		this.arriveairport = arriveairport;
	}

	public String getArriveairport() {
		return this.arriveairport;
	}

	public void setStartcity(String startcity) {
		this.startcity = startcity;
	}

	public String getStartcity() {
		return this.startcity;
	}

	public void setStartcityjm(String startcityjm) {
		this.startcityjm = startcityjm;
	}

	public String getStartcityjm() {
		return this.startcityjm;
	}

	public void setArrivecity(String arrivecity) {
		this.arrivecity = arrivecity;
	}

	public String getArrivecity() {
		return this.arrivecity;
	}

	public void setArrivecityjm(String arrivecityjm) {
		this.arrivecityjm = arrivecityjm;
	}

	public String getArrivecityjm() {
		return this.arrivecityjm;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getStarttime() {
		return this.starttime;
	}

	public void setArrivetime(Date arrivetime) {
		this.arrivetime = arrivetime;
	}

	public Date getArrivetime() {
		return this.arrivetime;
	}

	public void setOffsetrate(String offsetrate) {
		this.offsetrate = offsetrate;
	}

	public String getOffsetrate() {
		return this.offsetrate;
	}

	public void setOffsettime(String offsettime) {
		this.offsettime = offsettime;
	}

	public String getOffsettime() {
		return this.offsettime;
	}

	public void setTicketprice(Integer ticketprice) {
		this.ticketprice = ticketprice;
	}

	public Integer getTicketprice() {
		return this.ticketprice;
	}

	public void setAvgticketprice(Integer avgticketprice) {
		this.avgticketprice = avgticketprice;
	}

	public Integer getAvgticketprice() {
		return this.avgticketprice;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getAgent() {
		return this.agent;
	}

	public void setFetchdate(Date fetchdate) {
		this.fetchdate = fetchdate;
	}

	public Date getFetchdate() {
		return this.fetchdate;
	}

	public void setOpttime(Date opttime) {
		this.opttime = opttime;
	}

	public Date getOpttime() {
		return this.opttime;
	}

	public void setTypetime(String typetime) {
		this.typetime = typetime;
	}

	public String getTypetime() {
		return this.typetime;
	}

	@Override
	public String toString() {
		return ";id:" + id + ";fltno:" + fltno + ";aireline:" + aireline + ";airtype:" + airtype + ";startairport:" + startairport + ";arriveairport:" + arriveairport
				+ ";startcity:" + startcity + ";startcityjm:" + startcityjm + ";arrivecity:" + arrivecity + ";arrivecityjm:" + arrivecityjm + ";starttime:" + starttime
				+ ";arrivetime:" + arrivetime + ";offsetrate:" + offsetrate + ";offsettime:" + offsettime + ";ticketprice:" + ticketprice + ";avgticketprice:" + avgticketprice
				+ ";agent:" + agent + ";fetchdate:" + fetchdate + ";opttime:" + opttime + ";typetime:" + typetime;
	}
}
