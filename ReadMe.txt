-------------------------------------------------------------
一、项目管理：基础库管理
	编码格式：UTF-8 
	项目简介：
		管理品杰公司基础库信息库
	  目的
	  1、品杰公司基础库统一管理
	  2、统一规范的指导性规范与标准，提高代码质量，提高开发效率
	  3、衍化为通用基础类

	项目直接负责人：
 	项目直接参与人员：
 
	 项目开始日期：2013年12月17日
	 项目结束日期：
------------------------------------------------------------- 

二、数据库信息
	库名	                                               简称                  注释
	PJBaseInfoDB	      Pbase	           品杰基础库
	PJBusinessInfoDB	  Pbiz	           品杰业务库
	Food_regulation	      Freg	           餐饮业务库
	Food_declare	      Fdec	           餐饮填报数据库
	Tim	                  Dtim	           药品终端机管理库
	Food_certManage	      Fcert	           餐饮证照管理库
	OA	                  Oa	           办公自动化库
	PJCertManageDB        Pcert     品杰证照管理库
------------------------------------------------------------- 
 
 三、包说明
	com.pj.action            action 包
	com.pj.base              基础类
	com.pj.bean              java对象POJO 
	com.pj.bean.extend       POJO继承类
	com.pj.bean.xml          数据库操作映射文件   
	com.pj.dao               数据库访问Dao层文件包   
	com.pj.util              本项目工具类
	com.pj.servlet           servlet 包
	com.pj.sqlconfigclient   读取库连接并初始化
	com.pj.test              测试类
	com.pj.webservice        网络服务类
	com.pj.api               应用程序接口类
	com.util                 全局工具类
	com.config               配置文件包
 -------------------------------------------------------------

四、类和XML命名规则
	命名规则：按部分命名 第一部分第一个字母大写 其他的均为小写 
		例如：ACTION：PbizTonoticeinfoAction.java 
			第一部分为数据库简称（Pbiz） 
			第二部分为表名（Tonoticeinfo） 
			第三部分为Action
			
	1、ACTION:数据库简称+表名+Action.java
	2、BEAN：数据库简称+表名.java
	3、EXTEND：Ext+数据库简称+表名.java
	4、XML：对应表(bean)的操作xml 数据库简称+表名+Sqlmap.xml
	5、SQLCONFIGCLIENT：对应数据库的初始化 库名简称+Sqlconfigclient.java
	6、CONFIG：常用的配置 使用默认的例如web.config 其定义配置文件命名为 库名简称+Sqlmapconfig.xml
	7、UTIL：所有项目均可用的util命名：自定义名称+Util.java 当前项目util命名：自定义名称+项目名称+Util.java
	8、BASE：相应的类别+Base.java 例如：ActionBese.java
    9、API：数据库简称+表名+Api.java
    10、DAO：数据库简称+表名+Dao.java
    11、SERVLET：
    12、WEBSERVICE： 
    13、TEST：测试类名+Test.java
 ----------------------------------------------------------------

五、常用说明 
	1、每个方法有注释 注释内容为传入参数 返回参数 类或方法功能的描述
	2、基本的缩进要有 代码缩进风格统一 
	3、注释代码要有说明 注释代码的功能	为什么注释掉 
	4、通用的字段 命名要一致 以后会逐渐完善到readMe.txt文件内
	5、方法与方法之间 间隔一行即可 不可随意增加间隔行数
	6、每个人允许有自己的风格 自己的风格要统一 并在ReadMe.txt有描述信息 

 ----------------------------------------------------------------
 
 六、通用字段命名
 	首字母大小写根据具体情况而定 每个人使用到的可添加到此处
 	
 	1、description：desc
 	2、business：biz
 	3、picture：pic
 	4、Initialization：init
	
 ----------------------------------------------------------------
	页面验证示例地址:
	http://code.ciaoca.com/jquery/validation_engine/
 ----------------------------------------------------------------
 自由行
 http://yn.gayosite.com/kuxun_api/CityLineToXmlQnTTL.asp?q=2&f=3&t=3001&id=525&QunarpriceDay=6
 跟团游
 http://yn.gayosite.com/kuxun_api/CityLineToXmlQnTTL.asp?q=2&f=3&t=3001&id=1166&QunarpriceDay=6
 
 211.149.235.153:3306
 andyvieye4310526+_
 andyvieye431052