package com.pj.action;

import java.io.ByteArrayInputStream;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.base.ModelBase;
import com.opensymphony.xwork2.Action;
import com.pj.bean.QlyCitytocityprice;
import com.pj.bean.QlyCitytocitypricecustom;
import com.pj.bean.QlyFlyticket;
import com.pj.bean.QlyLine;
import com.pj.bean.QlyLinepic;
import com.pj.bean.QlyLinetravel;
import com.pj.bean.QlyRegulatepricecondition;
import com.pj.bean.QlyTarea;
import com.pj.bean.QlyTravel;
import com.pj.bean.QlyView;
import com.pj.bean.QlyViewpic;
import com.pj.bean.extend.ExtQlyCitytocityprice;
import com.pj.bean.extend.ExtQlyCitytocitypricecustom;
import com.pj.bean.extend.ExtQlyCustomviehcleprice;
import com.pj.bean.extend.ExtQlyFlyticket;
import com.pj.bean.extend.ExtQlyLine;
import com.pj.bean.extend.ExtQlyLinepic;
import com.pj.bean.extend.ExtQlyTravel;
import com.pj.bean.extend.ExtQlyView;
import com.pj.bean.extend.ExtQlyViewpic;
import com.pj.dao.QlyCitytocitypriceDao;
import com.pj.dao.QlyCitytocitypricecustomDao;
import com.pj.dao.QlyCustomviehclepriceDao;
import com.pj.dao.QlyFlyticketDao;
import com.pj.dao.QlyLineDao;
import com.pj.dao.QlyLinepicDao;
import com.pj.dao.QlyLinetravelDao;
import com.pj.dao.QlyRegulatepriceconditionDao;
import com.pj.dao.QlyTareaDao;
import com.pj.dao.QlyTravelDao;
import com.pj.dao.QlyViewDao;
import com.pj.dao.QlyViewpicDao;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;

@SuppressWarnings("serial")
public class PrintLineAsXml extends ActionBase {
	private String linecode;
	private String pname;
	private Integer id;
	private List<String> fromcityjmlist;
	private String fromcity;
	private String tocity;
	private List<ExtQlyCustomviehcleprice> flystravel = new ArrayList<ExtQlyCustomviehcleprice>();

	@SuppressWarnings("unchecked")
	public String execute() {
		// HttpServletRequest request = ServletActionContext.getRequest();
		// String rurl = "http://";
		// rurl += request.getServerName().toString();
		// rurl += ":" + request.getServerPort();
		// rurl += request.getContextPath().toString();

		sql = "extqlyline.bhc_getAllListForXml";
		ilist = QlyLineDao.getInstence().getList(sql);
		sql = "qlytarea.bhc_getAllList";
		fromcityjmlist = QlyLineDao.getInstence().getList(sql);
		return "lines";
	}

	public String ajaxLines() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String rurl = "http://";
		rurl += request.getServerName().toString();
		rurl += ":" + request.getServerPort();
		rurl += request.getContextPath().toString();

		Map<String, String> pmap = new HashMap<String, String>();
		String[] sfs = fromcity.split("\n");
		StringBuffer sb = new StringBuffer();
		if (sfs != null && sfs.length > 0) {
			for (String sf : sfs) {
				if (sf.trim().equals("")) {
					continue;
				}
				if (sb.length() > 0) {
					sb.append(",");
				}
				sb.append("\"" + sf + "\"");
			}
		}
		fromcity = sb.toString();
		sb.setLength(0);
		String[] sts = tocity.split("\n");
		if (sts != null && sts.length > 0) {
			for (String st : sts) {
				if (st.trim().equals("")) {
					continue;
				}
				if (sb.length() > 0) {
					sb.append(",");
				}
				sb.append("\"" + st + "\"");
			}
		}
		tocity = sb.toString();
		pmap.put("fromcity", fromcity.equals("") ? null : fromcity);
		pmap.put("tocity", tocity.equals("") ? null : tocity);
		pmap.put("lineid", linecode);
		sql = "extqlyline.bhc_ListByCityForXml";
		ilist = QlyLineDao.getInstence().getList(sql, pmap);
		JsonHierarchicalStreamDriver driver = new JsonHierarchicalStreamDriver() {
			public HierarchicalStreamWriter createWriter(Writer out) {
				return new JsonWriter(out, JsonWriter.DROP_ROOT_MODE);
			}
		};

		List<String> tlist = new ArrayList<String>();
		for (int i = 0; i < ilist.size(); i++) {
			ExtQlyLine q = (ExtQlyLine) ilist.get(i);
			String f = "F";
			if (q.getFromcityjm().trim().equals(q.getPlaceid().trim())) {
				f = "D";
			}
			tlist.add(rurl + "/px!lxml.do?id=" + q.getId() + "&linecode=" + q.getFromcityjm() + f + q.getLineid().toUpperCase() + "&pname=" + q.getFromcityjm());
		}

		XStream xp = new XStream(driver);
		sb.setLength(0);
		sb.append("{");
		sb.append("\"results\":").append(1);
		sb.append(",\"url\":").append("\"" + rurl + "\"");
		sb.append(",\"obj\":").append(xp.toXML(tlist));
		sb.append("}");

		try {
			inputStream = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ajax-success";
	}

	public String lines() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String rurl = "http://";
		rurl += request.getServerName().toString();
		rurl += ":" + request.getServerPort();
		rurl += request.getContextPath().toString();

		sql = "extqlyline.bhc_getAllListForXml";
		ilist = QlyLineDao.getInstence().getList(sql);
		StringBuffer sbs = new StringBuffer();
		if (ilist != null) {
			for (int i = 0; i < ilist.size(); i++) {
				QlyLine ql = (QlyLine) ilist.get(i);
				sbs.append("<a href=\"" + rurl + "/px!slines.do?id=" + ql.getId() + "\">");
				sbs.append(ql.getLinename());
				sbs.append("</a><br/><br/>");
			}
		}
		printHtml(sbs.toString());
		return Action.NONE;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String slines() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String rurl = "http://";
		rurl += request.getServerName().toString();
		rurl += ":" + request.getServerPort();
		rurl += request.getContextPath().toString();

		sql = "extqlyline.bhc_selectByPrimaryKey";
		QlyLine qlyLine = new QlyLine();
		qlyLine.setId(id);
		QlyLine qline = (QlyLine) QlyLineDao.getInstence().getInfo(sql, qlyLine);
		StringBuffer sbs = new StringBuffer();
		if (qline != null) {
			ExtQlyCitytocityprice qcp = new ExtQlyCitytocityprice();
			qcp.setTocityname(qline.getPlacename());
			qcp.setLineid(qlyLine.getId());

			sql = "extqlycitytocityprice.bhc_selectByPlacename";
			List<QlyCitytocityprice> cprices = (List) QlyCitytocitypriceDao.getInstence().getList(sql, qcp);
			for (QlyCitytocityprice qc : cprices) {
				sbs.append("<a href=\"" + rurl + "/px!lxml.do?id=" + qline.getId() + "&linecode=" + qc.getFromcityjm() + "F" + qline.getLineid() + "&pname=" + qc.getFromcityjm() + "\">");
				sbs.append(qline.getLinename()).append(qc.getFromcityname()).append("--").append(qline.getPlacename());
				sbs.append("</a><br/><br/>");
			}
		}
		printHtml(sbs.toString());
		return Action.NONE;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String lxml() {
		sql = "extqlyline.bhc_selectByPrimaryKey";
		QlyLine qline = new QlyLine();
		qline.setId(id);
		QlyLine ql = (QlyLine) QlyLineDao.getInstence().getInfo(sql, qline);

		sql = "extqlylinepic.bhc_getListByLineid";
		ExtQlyLinepic eqpic = new ExtQlyLinepic();
		eqpic.setLineid(ql.getId());
		List<QlyLinepic> qpics = (List) QlyLinepicDao.getInstence().getList(sql, eqpic);

		HttpServletRequest request = ServletActionContext.getRequest();
		String rurl = "http://";
		rurl += request.getServerName().toString();
		rurl += ":" + request.getServerPort();
		rurl += request.getContextPath().toString();

		ExtQlyCitytocitypricecustom qcp = new ExtQlyCitytocitypricecustom();
		qcp.setFromcityjm(pname);
		qcp.setLineid(ql.getId());
		sql = "extqlycitytocitypricecustom.bhc_selectByFromAndToCity";
		QlyCitytocitypricecustom cprice = (QlyCitytocitypricecustom) QlyCitytocitypricecustomDao.getInstence().getInfo(sql, qcp);

		StringBuffer sbs = new StringBuffer();
		sbs.append("<route>");
		sbs.append("<summary>");
		if (ql != null) {
			linecode = linecode.toUpperCase();
			if (linecode.startsWith("86")) {
				linecode = linecode.substring(2);
			}

			// 默认当地参团
			String strsb = "|" + ql.getPlacename() + "当地参团";
			if (!pname.trim().equals(ql.getPlaceid().trim())) {// 如果不是当地参团
				sql = "qlytarea.bhc_selectByAcode";
				QlyTarea qt = new QlyTarea();
				qt.setAcode(pname);
				qt = (QlyTarea) QlyTareaDao.getInstence().getInfo(sql, qt);
				strsb = "|含" + qt.getArea() + "到" + ql.getPlacename() + "的往返机票";
				strsb = "|" + cprice.getTraficaltype() + "去" + cprice.getVehiclebacktype() + "回";
			}

			sbs.append("\r\n<title>").append(ql.getLinename()).append(strsb).append("_" + ql.getLineid().toUpperCase()).append("</title>");
			sbs.append("\r\n<titletuniu>").append(ql.getTitletuniu()).append("</titletuniu>");
			sbs.append("\r\n<teamno>").append(linecode).append("</teamno>");
			sbs.append("\r\n<placeid>").append(ql.getPlaceid()).append("</placeid>");
			sbs.append("\r\n<recommendation>").append(ql.getCommreason()).append("</recommendation>");
			sbs.append("\r\n<resourceid>").append(ql.getId()).append("</resourceid>");
			if (ql.getJoinattribute().trim().equals("参团游")) {
				sbs.append("\r\n<pfunction>").append("group").append("</pfunction>");
			} else {
				sbs.append("\r\n<pfunction>").append("free").append("</pfunction>");
			}
			sbs.append("\r\n<pcomposition>").append("交通/酒店/接送机").append("</pcomposition>");
			sbs.append("\r\n<day>").append(ql.getDaynum()).append("</day>");
			sbs.append("\r\n<advanceday>").append(ql.getSign()).append("</advanceday>");
			sbs.append("\r\n<advancedaytype>").append("工作日").append("</advancedaytype>");
			sbs.append("\r\n<freetriptotraffic>").append(ql.getComeviehcle()).append("</freetriptotraffic>");
			sbs.append("\r\n<freetripbacktraffic>").append(ql.getBackviehcle()).append("</freetripbacktraffic>");
			sbs.append("\r\n<departure>").append(cprice.getFromcityname()).append("</departure>");
			sbs.append("\r\n<arrive>").append(ql.getPlacename()).append("</arrive>");
			// 这个参数后面有一个“－”号，要把它去掉
			if (ql.getPasscity() != null && ql.getPasscity().length() > 0) {
				ql.setPasscity(ql.getPasscity().substring(0, ql.getPasscity().length() - 1));
			}
			sbs.append("\r\n<arrivecity>").append(ql.getPasscity()).append("</arrivecity>");
			sbs.append("\r\n<linesubject>").append(ql.getLinesubject()).append("</linesubject>");
			sbs.append("\r\n<arrivetype>").append(ql.getDestinationtype()).append("</arrivetype>");

			if (ql.getComeviehcle() != null && ql.getComeviehcle().trim().equals("飞机")) {
				sbs.append("\r\n<distancetype>").append("长途").append("</distancetype>");
			} else {
				sbs.append("\r\n<distancetype>").append("短途").append("</distancetype>");
			}

			StringBuffer sb = new StringBuffer();
			for (QlyLinepic pic : qpics) {
				sb.append(rurl).append("/").append(pic.getVpicfix()).append(",");
			}
			if (sb.length() > 0) {
				sb.setLength(sb.length() - 1);
			}
			sbs.append("\r\n<image>").append(sb).append("</image>");
			sbs.append("\r\n<feature>").append(replaceCharacter(ql.getIntroduce())).append("</feature>");
			sbs.append("\r\n<feeinclude>").append(replaceCharacter(ql.getCostinfo())).append("</feeinclude>");
			sbs.append("\r\n<feeexclude>").append(replaceCharacter(ql.getCostnothave())).append("</feeexclude>");
			sbs.append("\r\n<attention>").append(replaceCharacter(ql.getAttention())).append("</attention>");
			sbs.append("\r\n<prebook>").append(replaceCharacter(ql.getPrebook())).append("</prebook>");
			sbs.append("\r\n<calbook>").append(replaceCharacter(ql.getCalbook())).append("</calbook>");
		}
		sbs.append("\r\n</summary>");

		// 记录这条线路是什么时候出发
		String timeflag = "上午";
		sbs.append("\r\n<days>");
		if (ql != null && ql.getLineid() != null && !"".equals(ql.getLineid().trim())) {
			sql = "qlylinetravel.bhc_getListByLineId";
			QlyLinetravel tqlt = new QlyLinetravel();
			tqlt.setLtid(ql.getId());
			List<QlyLinetravel> qlts = (List) QlyLinetravelDao.getInstence().getList(sql, tqlt);
			StringBuffer sb = new StringBuffer();
			for (QlyLinetravel qlt : qlts) {
				List<QlyView> views = null;
				if (qlt.getViewid() != null && qlt.getViewid().trim().length() > 0) {
					sql = "extqlyview.bhc_selectInKey";
					// 通过 in 查询，把这些景点全部取出来
					ExtQlyView tview = new ExtQlyView();
					tview.setCond(qlt.getViewid());
					// 这是当前这个行程里面的所有景点
					views = (List) QlyViewDao.getInstence().getList(sql, tview);
				}
				// 把景点的图片，根据景点的ＩＤ，存储在 vpics 里面
				Map<String, List<QlyViewpic>> vpics = new HashMap<String, List<QlyViewpic>>();
				StringBuffer sbt = new StringBuffer();
				// 记录所有当前行程里的所有景点的图片数量
				int i = 0;
				if (views != null && views.size() > 0) {
					for (QlyView view : views) {
						sql = "extqlyviewpic.bhc_getListByViewid";
						ExtQlyViewpic evp = new ExtQlyViewpic();
						evp.setViewid(view.getId());
						List<QlyViewpic> qvs = (List) QlyViewpicDao.getInstence().getList(sql, evp);
						if (qvs != null)
							i += qvs.size();// 得到当前这个景点的图片数量
						vpics.put(String.valueOf(view.getId()), qvs);
						if (qvs != null && qvs.size() > 0) {
							for (QlyViewpic vp : qvs) {
								if (vp.getPicurl() == null) {
									continue;
								}
								if (sbt.length() > 1) {
									// sbt 在第一次拼接的时候，长度是０，所以只要是大于０，那么就应该是有值的
									sbt.append(",");
								}
								sbt.append(rurl).append("/").append(vp.getPicurl());
							}
						}
					}
				} else {
					continue;
				}

				// c 表示当前取出的图片有多少张了， t 表示当前取到第几个行程，
				// v 表示要取出当前景点的第几个图片
				// 根据图片数量，确定怎么上传图片，如果小于６张，那么取出所有图片，如果大于６张，那么每个景点取一张，循环取，直到取出６张为止
				if (i > 6) {
					// 如果大于６，那图片要重新拼接
					sbt.setLength(0);
					int c = 0, t = 0, v = 0, b = 0;
					if (qlts != null && qlts.size() > 0) {
						o: while (true) {
							// 这里是为了防止出现死循环，设了一个大概的值
							if (b > 20) {
								break;
							}

							QlyLinetravel tql = qlts.get(t);
							String vids = tql.getViewid();
							if (vids != null) {
								String[] ids = vids.split(",");
								if (ids.length > 0) {
									for (int in = 0; in < ids.length; in++) {
										List qvc = vpics.get(ids[in].trim());
										if (qvc != null && qvc.size() > v) {
											QlyViewpic qvp = (QlyViewpic) qvc.get(v);
											if (qvp.getPicurl() == null) {
												continue;
											}
											if (c > 0) {
												sbt.append(",");
											}
											sbt.append(rurl).append("/").append(qvp.getPicurl());
											c++;
											if (c >= 6) {
												// 如果图片取满了６张，那么不再取
												break o;
											}
										}
									}
								}
							}
							t++;
							if (t == qlts.size()) {
								// 当取完景点的第一张图片后，取景点的下一张图片,只有取第二遍的时候，才取第二张
								v++;
								// 如果行程已经取完，那么重新循环
								t = 0;
							}
							b++;
						}
					}
				}

				sb.append("\r\n<day daytitle=").append("\"" + qlt.getTraveltitle() + "\"").append(" daynum=").append("\"" + qlt.getDaynum() + "\">");
				sb.append("\r\n<sightimage>").append(sbt).append("</sightimage>");
				sb.append("\r\n<daydescription>").append(qlt.getViewcontent()).append("</daydescription>");
				sb.append("\r\n<daydesc>").append(qlt.getMark()).append("</daydesc>");

				sql = "qlytravel.bhc_getTravelByLtid";
				ExtQlyTravel eqt = new ExtQlyTravel();
				String[] tstr = qlt.getTravelcode().split(",");

				if (tstr.length > 0) {
					List ts = new ArrayList();
					for (int j = 0; j < tstr.length; j++) {
						ts.add(tstr[j]);
					}
					eqt.setPlist(ts);
				}
				List<QlyTravel> qts = (List) QlyTravelDao.getInstence().getList(sql, eqt);
				StringBuffer sbh = new StringBuffer("");
				StringBuffer sbstay = new StringBuffer("");
				StringBuffer inn = new StringBuffer("");
				StringBuffer innmark = new StringBuffer("");
				StringBuffer shopping = new StringBuffer("");
				StringBuffer selfexpense = new StringBuffer("");
				boolean breakfirst = false, lunch = false, supper = false;
				String have = "提供", byself = "敬请自理";
				sb.append("\r\n<activities>");
				if (qts != null && qts.size() > 0) {
					for (QlyTravel qtl : qts) {
						sb.append("\r\n<activity>").append(replaceCharacter(qtl.getActivity())).append("</activity>");
						sbh.setLength(0);
						sbh.append(qlt.getHotel() == null ? "二星或同等级酒店" : qlt.getHotel());
						sbstay.append(qlt.getStay() == null ? "" : replaceCharacter(qlt.getStay()));
						inn.append(qlt.getInnfeature() == null ? "" : replaceCharacter(qlt.getInnfeature()));
						innmark.append(qtl.getMark() == null ? "" : replaceCharacter(qtl.getMark()));
						shopping.append(qtl.getShop() == null ? "" : qtl.getShop());
						selfexpense.append(qtl.getSelfexpense() == null ? "" : qtl.getSelfexpense());
						if (qtl.getBreakfast() != null && qtl.getBreakfast().trim().equals(have)) {
							breakfirst = true;
						}
						if (qtl.getLunch() != null && qtl.getLunch().trim().equals(have)) {
							lunch = true;
						}
						if (qtl.getSupper() != null && qtl.getSupper().trim().equals(have)) {
							supper = true;
						}
					}
				}
				sb.append("\r\n</activities>");

				sb.append("\r\n<shopping>").append(shopping).append("</shopping>");
				sb.append("\r\n<selfexpense>").append(selfexpense).append("</selfexpense>");
				sb.append("\r\n<daytraffic>").append(qlt.getVehicle()).append("</daytraffic>");

				// 取出中间的交通
				String vehicleids = qlt.getVehiclesprice();
				if (vehicleids != null) {
					sql = "extqlycustomviehcleprice.bhc_selectInId";
					List<ExtQlyCustomviehcleprice> tlist = QlyCustomviehclepriceDao.getInstence().getList(sql, vehicleids);
					sb.append("\r\n<vehiclesprice>");
					if (tlist != null && tlist.size() > 0) {
						for (ExtQlyCustomviehcleprice p : tlist) {
							p.setDayno(qlt.getDaynum());
							p.setVtime(qlt.getVehiclespricetitle());
							flystravel.add(p);
							sb.append("\r\n<v>");
							sb.append("\r\n<vprice>").append(p.getPrice()).append("</vprice>");
							sb.append("\r\n<vtype>").append(p.getTraficaltype()).append("</vtype>");
							sb.append("\r\n<fromcityname>").append(p.getFromcityname()).append("</fromcityname>");
							sb.append("\r\n<fromcityjm>").append(p.getFromcityjm()).append("</fromcityjm>");
							sb.append("\r\n<tocityname>").append(p.getTocityname()).append("</tocityname>");
							sb.append("\r\n<tocityjm>").append(p.getTocityjm()).append("</tocityjm>");
							sb.append("\r\n</v>");
						}
					}
					sb.append("\r\n</vehiclesprice>");
				}

				sb.append("\r\n<dayhotelstar starname=").append("\"" + sbh + "\" ").append(" stardesc=").append("\"" + sbstay + "\"").append(" innfeature=").append("\"" + inn + "\">").append("</dayhotelstar >");
				sb.append("\r\n<innmark>").append(innmark).append("</innmark>");
				sb.append("\r\n<meals>");
				if (breakfirst) {
					sb.append("\r\n<meal mealdesc=").append("\"" + have + "\"").append(" mealtype=").append("\"早\"/>");
				} else {
					sb.append("\r\n<meal mealdesc=").append("\"" + byself + "\"").append(" mealtype=").append("\"早\"/>");
				}
				if (lunch) {
					sb.append("\r\n<meal mealdesc=").append("\"" + have + "\"").append(" mealtype=").append("\"中\"/>");
				} else {
					sb.append("\r\n<meal mealdesc=").append("\"" + byself + "\"").append(" mealtype=").append("\"中\"/>");
				}
				if (supper) {
					sb.append("\r\n<meal mealdesc=").append("\"" + have + "\"").append(" mealtype=").append("\"晚\"/>");
				} else {
					sb.append("\r\n<meal mealdesc=").append("\"" + byself + "\"").append(" mealtype=").append("\"晚\"/>");
				}
				sb.append("\r\n</meals>");
				sb.append("\r\n</day>");
			}
			sbs.append(sb);
		}
		sbs.append("\r\n</days>");

		// tems的位置
		timeflag = combineTems(sbs, ql, cprice);

		// 得到航班信息
		sbs.append("<flights>");
		conbineFlights(sbs, ql, timeflag);
		sbs.append("</flights>");

		sbs.append("\r\n</route>");
		printXML(sbs.toString());
		return Action.NONE;
	}

	private void getFlies(String startcityjm, String arrivecityjm, Map<String, List<QlyFlyticket>> m) {
		sql = "extqlyflyticket.bhc_getAirlineByLine";
		ExtQlyFlyticket qft = new ExtQlyFlyticket();
		qft.setStartcityjm(startcityjm);
		qft.setArrivecityjm(arrivecityjm);
		List<ModelBase> plist = QlyFlyticketDao.getInstence().getList(sql, qft);
		// 取得航班，如果该城市没有机场，那么重新查找这个城市的预备航班
		if (plist == null || plist.size() == 0) {
			while (true) {// 通过循环取得当前城市的转定义机场
				QlyTarea qt = new QlyTarea();
				qt.setAcode(qft.getStartcityjm());
				sql = "qlytarea.bhc_selectByAcode";
				QlyTarea qta = (QlyTarea) QlyTareaDao.getInstence().getInfo(sql, qt);
				if (qta.getAirportcode() != null) {
					qft.setStartcityjm(qta.getAirportcode());
					sql = "extqlyflyticket.bhc_getAirlineByLine";
					plist = QlyFlyticketDao.getInstence().getList(sql, qft);
				} else {
					break;
				}
			}
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (plist != null && plist.size() > 0) {
			// 把航班按出发时间保存起来
			Long maxdate = 0L;
			Date mdate = null;
			for (int i = 0; i < plist.size(); i++) {
				QlyFlyticket qt = (QlyFlyticket) plist.get(i);
				if (maxdate < qt.getStarttime().getTime()) {
					maxdate = qt.getStarttime().getTime();
					mdate = qt.getStarttime();
				}
				List<QlyFlyticket> tlist = m.get(sdf.format(qt.getStarttime()));
				tlist = tlist == null ? new ArrayList<QlyFlyticket>() : tlist;
				tlist.add(qt);
				m.put(sdf.format(qt.getStarttime()), tlist);
			}

			// 计算航班的平均值
			int tfprice = 0;// 记录所有的价格；
			if (!m.isEmpty()) {
				for (List<QlyFlyticket> t : m.values()) {
					if (t != null && t.size() > 0) {
						int tp2 = 0;
						for (QlyFlyticket q : t) {
							tp2 += q.getTicketprice();
						}
						tfprice += tp2 / t.size();// 得到一天中的平均价
					}
				}
				tfprice = tfprice / m.size();// 得到整条线的平均价
			}

			// 把最大的日期记录下来
			List<QlyFlyticket> tlist = new ArrayList<QlyFlyticket>();
			QlyFlyticket qf = new QlyFlyticket();
			qf.setStarttime(mdate);
			qf.setTicketprice(tfprice);
			tlist.add(qf);
			m.put("maxdate", tlist);
		}
	}

	private String combineTems(StringBuffer sbs, QlyLine ql, QlyCitytocitypricecustom cprice) {

		String timeflag = "";
		// 取得这两个城市之间的 来程 的机票
		Map<String, List<QlyFlyticket>> m = new HashMap<String, List<QlyFlyticket>>();
		// 取得这两个城市之间的 返程 的机票
		Map<String, List<QlyFlyticket>> b = new HashMap<String, List<QlyFlyticket>>();

		// 标识交通的类型，目前只分为两种类型，飞机和其它，默认是其它,，标识为 false
		boolean cflag = false, bflag = false;

		if (cprice.getTraficaltype() != null && cprice.getTraficaltype().trim().equals("飞机")) {
			// 得到来程的航班
			getFlies(cprice.getFromcityjm(), ql.getPlaceid(), m);
			cflag = true;
		}

		if (cprice.getVehiclebacktype() != null && cprice.getVehiclebacktype().trim().equals("飞机")) {
			// 得到回程的航班
			getFlies(ql.getBackplaceid(), cprice.getFromcityjm(), b);
			bflag = true;
		}

		// 那么把预先定义好的交通费用取出来
		ExtQlyCustomviehcleprice ecome = null;
		ExtQlyCustomviehcleprice eback = null;

		sql = "extqlycustomviehcleprice.bhc_forinterface";
		ExtQlyCustomviehcleprice cc = new ExtQlyCustomviehcleprice();
		cc.setFromcityjm(cprice.getFromcityjm());
		cc.setTocityjm(ql.getPlaceid());
		cc.setTraficaltype(cprice.getTraficaltype());
		ecome = (ExtQlyCustomviehcleprice) QlyCustomviehclepriceDao.getInstence().getInfo(sql, cc);

		sql = "extqlycustomviehcleprice.bhc_forinterface";
		ExtQlyCustomviehcleprice cb = new ExtQlyCustomviehcleprice();
		cb.setFromcityjm(ql.getBackplaceid());
		cb.setTocityjm(cprice.getFromcityjm());
		cb.setTraficaltype(cprice.getVehiclebacktype());
		eback = (ExtQlyCustomviehcleprice) QlyCustomviehclepriceDao.getInstence().getInfo(sql, cb);

		// 设置出发时间
		Calendar cal = Calendar.getInstance();
		Calendar calb = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
		calb.setTime(cal.getTime());

		// 取得线路的浮动价格
		sql = "extqlyregulatepricecondition.bhc_getAllByLineid";
		QlyRegulatepricecondition qrd = new QlyRegulatepricecondition();
		qrd.setLineid(ql.getId());
		List<?> rlist = QlyRegulatepriceconditionDao.getInstence().getList(sql, qrd);

		int bprice = ql.getLineprice();// 线路价格
		int childrenprice = ql.getChildprice();// 儿童价格
		int marketprice = 0;
		Random r = new Random(new Date().getTime());

		Map<String, Map<String, Object>> pmap = new TreeMap<String, Map<String, Object>>();

		Map<String, Map<String, Integer>> avgm = new HashMap<String, Map<String, Integer>>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < 60; i++) {

			int fprice = 0;// 来时大交通费用
			int backprice = 0;// 回时大交通费用
			int cfprice = 0;// 来时儿童大交通费用
			int cbackprice = 0;// 回时儿童大交通费用

			if (ecome != null && ecome.getChildrenprice() != null) {
				cfprice = ecome.getChildrenprice().intValue();
			}
			if (eback != null && eback.getChildrenprice() != null) {
				cbackprice = eback.getChildrenprice().intValue();
			}
			if (!cflag) {
				if (ecome != null && ecome.getPrice() != null) {
					fprice = ecome.getPrice().intValue();
				}
			}
			if (!bflag) {
				if (eback != null && eback.getPrice() != null) {
					backprice = eback.getPrice().intValue();
				}
			}

			// 各出发城市设置的浮动价格
			int adultprice = 0, citychildrenprice = 0;
			if (cprice != null && cprice.getPrice() != null) {
				adultprice = cprice.getPrice().intValue();
			}
			if (cprice != null) {
				citychildrenprice = cprice.getChildrenprice();
			}

			// 生成市场随机浮动价
			int rseed = r.nextInt(8 - 5) + 5;
			marketprice = rseed * 100;
			int vehiclesprice = 0, flysprice = 0, vcprice = 0;
			Date fd = null;

			// 加上行程之间的价格
			if (flystravel.size() > 0) {
				for (ExtQlyCustomviehcleprice ep : flystravel) {
					// 儿童价格就填写固定的价格
					vcprice += ep.getChildrenprice();
					// 如果不是飞机，那么直接调用填写的价格
					if (!ep.getTraficaltype().trim().equals("飞机")) {
						vehiclesprice += ep.getPrice();
					} else {
						// 如果是飞机，那么调用相应城市的航班价格
						// 取得这两个城市之间的 来程 的机票
						Map<String, List<QlyFlyticket>> s = new HashMap<String, List<QlyFlyticket>>();
						// 取得这两个城市之间的 返程 的机票
						if (ep.getS() == null && ep.getD() == null) {
							// 如果在前面没有取到航班，那么到数据库，然后保存在内存中
							getFlies(ep.getFromcityjm(), ep.getTocityjm(), s);
							ep.setS(s);
						} else {
							// 如果已经有了数据，那么直接取用
							s = ep.getS();
						}
						// 把日期设置为当前循环到的日期
						Calendar ctime = Calendar.getInstance();
						ctime.setTime(cal.getTime());
						ctime.set(Calendar.DAY_OF_MONTH, ctime.get(Calendar.DAY_OF_MONTH) + ep.getDayno());// 按时间，把航班的时间设置为线路当天行程的航班时间

						// 把当天的航班分早中下晚四个时间段取出来
						Map<Integer, String> mk = new HashMap<Integer, String>();
						String vtitle = ep.getVtime();
						if (vtitle != null) {
							String[] vtitles = vtitle.split("#");
							if (vtitles.length > 0) {
								for (String sk : vtitles) {
									String[] k = sk.split(":");
									mk.put(Integer.parseInt(k[0]), k[1].trim());
								}
							}
						}
						// 来时的机票价格
						QlyFlyticket qt = null;
						qt = getFT(s, ctime, mk.get(ep.getId()));
						if (qt != null) {
							if (qt.getAvgticketprice() != null && qt.getAvgticketprice() > 0) {
								flysprice += qt.getAvgticketprice();
							} else {
								flysprice += qt.getTicketprice();
							}
							fd = qt.getStarttime();
						}
					}
				}
			}

			if (cflag) {// 如果是飞机来，从来程的飞机里取出相应的机票价格
				// 来时的机票价格
				QlyFlyticket qt = getFT(m, cal, ql.getViehcletime());
				if (qt != null) {
					timeflag = cprice.getVehiclecometime();
					if (qt.getAvgticketprice() != null && qt.getAvgticketprice() > 0) {
						fprice = qt.getAvgticketprice();
					} else {
						fprice = qt.getTicketprice();
					}
				}
			}

			// 如果来时的成人价小于儿童价，那么把儿童价设成成人价一致的价格
			if (fprice < cfprice) {
				cfprice = fprice;
			}

			if (bflag) {// 如果是飞机回，从回程的飞机里取出相应的机票价格
				// 回程的机票价格
				int linedays = Integer.parseInt(ql.getDaynum());
				calb.set(Calendar.DAY_OF_MONTH, calb.get(Calendar.DAY_OF_MONTH) + (linedays - 1));
				QlyFlyticket qt = getFT(b, calb, cprice.getVehiclebacktime());
				if (qt != null) {
					timeflag = qt.getTypetime();
					if (qt.getAvgticketprice() != null && qt.getAvgticketprice() > 0) {
						backprice = qt.getAvgticketprice();
					} else {
						backprice = qt.getTicketprice();
					}
				}
			}
			// 如果来时的成人价小于儿童价，那么把儿童价设成成人价一致的价格
			if (backprice < cbackprice) {
				cbackprice = backprice;
			}

			// 自定义的浮动价格
			// 成人价，儿童价，房差的调整价格
			int dadultprice = 0, dchildreprice = 0, droomdiffprice = 0;
			if (rlist != null && rlist.size() > 0) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				for (int f = 0; f < rlist.size(); f++) {
					QlyRegulatepricecondition qr = (QlyRegulatepricecondition) rlist.get(f);
					try {
						Date ld = df.parse(df.format(cal.getTime()));
						Date ds = df.parse(df.format(qr.getStartdate()));
						Date de = df.parse(df.format(qr.getEnddate()));
						if (ld.getTime() >= ds.getTime() && ld.getTime() <= de.getTime()) {
							if (qr.getDifftype().trim().equals("成人价")) {
								dadultprice += qr.getPrice();
							} else if (qr.getDifftype().trim().equals("儿童价")) {
								dchildreprice += qr.getPrice();
							} else if (qr.getDifftype().trim().equals("房差")) {
								droomdiffprice += qr.getPrice();
							}
						}
					} catch (ParseException e) {
					}
				}
			}

			Map<String, Object> pm = new HashMap<String, Object>();
			pm.put("flightprice", approximate(fprice + backprice));

			pm.put("adultprice", approximate(bprice + fprice + adultprice + backprice + dadultprice + vehiclesprice + flysprice));
			pm.put("marketprice", approximate(bprice + fprice + adultprice + backprice + dadultprice + marketprice + vehiclesprice + flysprice));
			pm.put("qunarprice", approximate(bprice + fprice + adultprice + backprice + dadultprice + vehiclesprice + flysprice));
			pm.put("vehiclesprice", vehiclesprice);
			pm.put("flysprice", flysprice);
			if (fd != null) {
				pm.put("fd", sdf.format(fd));
			}
			// childrenprice:线路儿童价，dchildreprice：自定义特殊日期浮动价，cfprice：来程的交通价，
			// cbackprice：回程的交通价，citychildrenprice：各出发城市浮动价，vcprice：行程之间的交通儿童价
			pm.put("JPCDprice", approximate(childrenprice + dchildreprice + cfprice + cbackprice + citychildrenprice + vcprice));
			pm.put("JPchildprice", approximate(childrenprice + dchildreprice + cfprice + cbackprice + citychildrenprice + vcprice));
			pm.put("childprice", approximate(childrenprice + citychildrenprice));
			System.out.println(
					"childrenprice:" + childrenprice + "\tdchildreprice:" + dchildreprice + "\tcfprice:" + cfprice + "\tcbackprice:" + cbackprice + "\tcitychildrenprice:" + citychildrenprice + "\tvcprice:" + vcprice);

			pm.put("roomnum", 2);
			pm.put("roomsendprice", approximate(ql.getRoompricediff() + droomdiffprice));
			pm.put("availablecount", 6);
			pmap.put(sdf.format(cal.getTime()), pm);
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
			calb.setTime(cal.getTime());
		}

		// 取平均价格，以５天为准
		Map<String, Map<String, Object>> resultMap = sortMapByValue(pmap); // 按Value进行排序
		int a = 0, s_a = 0, s_b = 0;
		List<String> tlist = new ArrayList<String>();
		for (Map.Entry<String, Map<String, Object>> entry : resultMap.entrySet()) {
			if ((a != 0 && a % 5 == 0) || a == 59) {
				s_a += Integer.parseInt(entry.getValue().get("qunarprice").toString());
				s_b += Integer.parseInt(entry.getValue().get("JPCDprice").toString());
				tlist.add(entry.getKey());

				Map<String, Integer> pm = new HashMap<String, Integer>();
				pm.put("qunarprice", approximate(s_a / tlist.size()));
				pm.put("JPCDprice", approximate(s_b / tlist.size()));
				for (String key : tlist) {
					avgm.put(key, pm);
				}
				s_a = 0;
				s_b = 0;
				tlist.clear();
			} else {
				s_a += Integer.parseInt(entry.getValue().get("qunarprice").toString());
				s_b += Integer.parseInt(entry.getValue().get("JPCDprice").toString());
				tlist.add(entry.getKey());
			}
			a++;
		}

		// 输出价格
		sbs.append("<teams>");
		cal.setTime(new Date());
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
		calb.setTime(cal.getTime());
		for (int i = 0; i < 60; i++) {
			Map<String, Object> mp = pmap.get(sdf.format(cal.getTime()));
			Map<String, Integer> pm = avgm.get(sdf.format(cal.getTime()));
			if (Integer.parseInt(mp.get("marketprice").toString()) < Integer.parseInt(pm.get("qunarprice").toString())) {
				mp.put("marketprice", pm.get("qunarprice") + 300);
			}
			mp.put("qunarprice", pm.get("qunarprice"));
			mp.put("JPCDprice", pm.get("JPCDprice"));

			sbs.append("<team takeoffdate=\"" + sdf.format(cal.getTime()) //
					+ "\" flightprice=\"" + mp.get("flightprice") //
					+ "\" childprice=\"" + mp.get("childprice") //
					+ "\" adultprice=\"" + mp.get("adultprice") //
					+ "\" marketprice=\"" + mp.get("marketprice") //
					+ "\" qunarprice=\"" + mp.get("qunarprice") //
					+ "\" JPCDprice=\"" + mp.get("JPCDprice") //
					+ "\" JPchildprice=\"" + mp.get("JPchildprice") //
					+ "\" roomnum=\"" + mp.get("roomnum") //
					+ "\" roomsendprice=\"" + mp.get("roomsendprice") //
					+ "\" availablecount=\"" + mp.get("availablecount") //
					+ "\" vehiclesprice=\"" + mp.get("vehiclesprice") //
					+ "\" flysprice=\"" + mp.get("flysprice") //
					+ "\" flydate=\"" + mp.get("fd") //
					+ "\" pricedesc=\"\">");
			sbs.append("</team>");
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + 1);
			calb.setTime(cal.getTime());
		}
		sbs.append("</teams>");
		return timeflag==null?"":timeflag;
	}

	/**
	 * 使用 Map按value进行排序
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, Map<String, Object>> sortMapByValue(Map<String, Map<String, Object>> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, Map<String, Object>> sortedMap = new LinkedHashMap<String, Map<String, Object>>();
		List<Map.Entry<String, Map<String, Object>>> entryList = new ArrayList<Map.Entry<String, Map<String, Object>>>(map.entrySet());
		Collections.sort(entryList, new Comparator<Map.Entry<String, Map<String, Object>>>() {
			public int compare(Entry<String, Map<String, Object>> me1, Entry<String, Map<String, Object>> me2) {
				return me1.getValue().get("qunarprice").toString().compareTo(me2.getValue().get("qunarprice").toString());
			}
		});
		Iterator<Map.Entry<String, Map<String, Object>>> iter = entryList.iterator();
		Map.Entry<String, Map<String, Object>> tmpEntry = null;
		while (iter.hasNext()) {
			tmpEntry = iter.next();
			sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
		}
		return sortedMap;
	}

	private void conbineFlights(StringBuffer sbs, QlyLine ql, String timeflag) {
		// 取得这两个城市之间的来回程的机票，同一天的
		sql = "extqlyflyticket.bhc_getAirlineByCityAndTime";
		ExtQlyFlyticket qft = new ExtQlyFlyticket();
		qft.setStartcityjm(pname);
		qft.setArrivecityjm(ql.getPlaceid());
		qft.setTypetime(timeflag.trim());
		List<?> plist = QlyFlyticketDao.getInstence().getList(sql, qft);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		if (plist != null && plist.size() > 0) {
			for (int i = 0; i < plist.size(); i++) {
				QlyFlyticket qt = (QlyFlyticket) plist.get(i);
				sbs.append("<flight ");
				if (qt.getStartcityjm().equals(ql.getPlaceid())) {
					sbs.append("flighttype=").append("\"去程\"");
				} else {
					sbs.append("flighttype=").append("\"回程\"");
				}
				sbs.append(" flightno=").append("\"" + qt.getFltno() + "\"");
				sbs.append(" price=").append("\"" + qt.getTicketprice() + "\"");
				sbs.append(" avgprice=").append("\"" + qt.getAvgticketprice() + "\"");
				sbs.append(" detairport=").append("\"" + qt.getStartairport() + "\"");
				sbs.append(" arrairport=").append("\"" + qt.getArriveairport() + "\"");
				sbs.append(" depcity=").append("\"" + qt.getStartcity() + "\"");
				sbs.append(" arrcity=").append("\"" + qt.getArrivecity() + "\"");
				sbs.append(" deptime=").append("\"" + sdf.format(qt.getStarttime()) + "\"");
				String atime = "";
				try {
					atime = sdf.format(qt.getArrivetime());
				} catch (Exception e) {
					atime = "0000-00-00";
				}
				sbs.append(" arrtime=").append("\"" + atime + "\"");

				sbs.append("></flight>");
			}
		}
	}

	private QlyFlyticket getFT(Map<String, List<QlyFlyticket>> m, Calendar c, String typetime) {
		if (m == null)
			return null;

		Date mdate = m.get("maxdate").get(0).getStarttime();
		if (c.getTime().getTime() > mdate.getTime()) {
			m.get("maxdate").get(0).setTypetime(typetime);
			return m.get("maxdate").get(0);
		}

		Calendar cal = Calendar.getInstance();
		Calendar calsub = Calendar.getInstance();
		cal.setTime(c.getTime());
		calsub.setTime(c.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		for (int i = 0; i < 60; i++) {
			boolean b = false;
			if (m.containsKey(sdf.format(cal.getTime()))) {
				// 如果当天没有，那么往前面找一天
				b = true;
			} else {
				// 如果前面一天没有，那么往后面找一天
				calsub.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) - (i + 1));
				if (m.containsKey(sdf.format(calsub.getTime()))) {
					cal.setTime(calsub.getTime());
					b = true;
				}
			}
			if (!b) {
				cal.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH) + (i + 1));
				continue;
			}
			List<QlyFlyticket> tlist = ((List<QlyFlyticket>) m.get(sdf.format(cal.getTime())));
			if (tlist != null && tlist.size() > 0) {
				if (typetime != null && typetime.trim().length() > 1) {
					for (Iterator<QlyFlyticket> it = tlist.iterator(); it.hasNext();) {
						QlyFlyticket qft = it.next();
						if (!typetime.contains(qft.getTypetime())) {
							it.remove();
						}
					}
				}
				Collections.sort(tlist, new Comparator<QlyFlyticket>() {
					public int compare(QlyFlyticket arg0, QlyFlyticket arg1) {
						if (arg0.getTicketprice() > arg1.getTicketprice()) {
							return 1;
						} else if (arg0.getTicketprice() < arg1.getTicketprice()) {
							return -1;
						}
						return 0;
					}
				});
				if (tlist != null && tlist.size() > 0)
					return tlist.get(0);
			}
		}
		return null;
	}

	private int approximate(int value) {
		value = new BigDecimal(String.valueOf(value / 10)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
		return value * 10;
	}

	private String replaceCharacter(String str) {
		if (str == null)
			return null;
		return str.replace("&nbsp;", "%20").replace("&mdash;", "%21").replace("&ldquo;", "%22").replace("&rdquo;", "%23").replace("&bull;", "%24").replace("&hellip;", "%25").replace("&rarr;", "%26")
				.replace("&barr;", "%27").replace("&larr;", "%28").replace("&tarr;", "%29");
	}

	public String getLinecode() {
		return linecode;
	}

	public void setLinecode(String linecode) {
		this.linecode = linecode;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<String> getFromcityjmlist() {
		return fromcityjmlist;
	}

	public void setFromcityjmlist(List<String> fromcityjmlist) {
		this.fromcityjmlist = fromcityjmlist;
	}

	public String getFromcity() {
		return fromcity;
	}

	public void setFromcity(String fromcity) {
		this.fromcity = fromcity;
	}

	public String getTocity() {
		return tocity;
	}

	public void setTocity(String tocity) {
		this.tocity = tocity;
	}

}
