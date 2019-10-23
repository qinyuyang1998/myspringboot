package com.sinosoft.myspringboot.constant;

import java.io.Serializable;

/**
 * 
 * @ClassName: Constants
 * @Description: TODO the constants of project
 * @author wudi
 * @date 2017年8月16日 下午4:43:28
 *
 */
public class Constants implements Serializable {

	private static final long serialVersionUID = 2954773989535645086L;

	public static final String fileMaxSize = "504800KB"; // 文件上传的最大文件大小

	public static final String SYS_ENCODING = "UTF-8";

	public static final String USER_INFO = "userInfo"; // 登陆用户信息
	
	public static final String AUTHENTICATION_MODIFYPASSWORD_URL="";
	
	public static final String	AUTHENTICATION_GETPRODUCTSBYUSERNAME_URL="";

	public static final String USER_INFO_JSON_OBJECT = "userInfoJsonObject"; // 登陆用户信息对应的JsonObject对象信息

	public static final String SYS_MODULE_LIST = "sys_module_list"; // 登陆用户对应的菜单列表

	public static final String SYS_MODULE_LIST_MAP = "sys_module_list_map"; // 菜单列表对应的hashmap(key值为功能集ID)

	public static final String URL_LIST_MAP = "url_list_map"; // 菜单列表对应的hashmap(key值为功能集的url)

	public static final Integer ADMIN_USER_ID = 0;

	// 单列市
	public static final String[] DANLIESHI_PINDEXS = new String[] { "2102",
			"3302", "3502", "3702", "4403" };

	// 初始化密码为888888
	public static final String INIT_PASSWORD = "21218CCA77804D2BA1922C33E0151105";

	public static final String sessionFactory = "sessionFactory"; // 全局的SqlSessionFactory，供每个controller使用，不需要每次都创建

	public static final Integer parCode = 300; // 只查询设备台账菜单

	public static final String ADD_SUCCESS = "success"; 

	public static final String UPDATE_SUCCESS = "success"; 

	public static final String DEL_SUCCESS = "success"; 
	public static final String ADD_FAIL = "success"; 

	public static final String UPDATE_FAIL = "success"; 
	public static final String DEL_FAIL = "success"; 
	// 错误信息
	public static final String MSG_NO_PERMISSIONS = "没有操作权限，不能访问";
	public static final String MSG_404 = "404：您访问的地址不存在";
	public static final String MSG_403 = "403：禁止访问";
	public static final String MSG_500 = "500：因为意外情况，服务器不能完成请求";
	public static final String MSG_Exception = "Exception：哇，报错喽！";
	public static final String MSG_SQLException = "数据库异常，请联系管理员。";
	public static final String MSG_NonExistent = "{\"msg\":\"这年还没有数据啊,换一年吧\"}";
	public static final String MSG_FzjcResult = "该路段范围内已存在决策结果，请先删除后再决策。";
	// 查询条件
	public static final String STRAND = " and ";// *#
	public static final String STROR = " or ";// #@
	public static final String STRLIKE = " like ";// $$
	public static final String STRNOTLIKE = " not like ";// @$
	public static final String STREQUAL = " = ";// &!
	public static final String STRNOTEQUAL = " ! ";// @@
	public static final String GREATER = " > ";// ?$
	public static final String LESSTHAN = " < ";// $#
	public static final String STRIN = " in ";//##
	public static final String STRNOTIN = " not in ";//@#
	public static final String STRGREATER_EQUAL = ">=";//?$&!
	public static final String STRLESSTHAN_EQUAL = "<=";//$#&!
	public static final String STRNOTE_EQUAL = "!=";//@@&!
	/**
	 * 返回错误信息 {\"msg\":\"error\"}
	 */
	public static final String MSG_ERROR="{\"msg\":\"error\"}";
	/**
	 * 返回true
	 */
	public static final String MSG_TRUE="{\"msg\":\"true\"}";
	/**
	 * 返回false
	 */
	public static final String MSG_FALSE="{\"msg\":\"false\"}";
	/**
	 * 数据查询失败
	 */
	public static final String MSG_DATAQUERY_ERROR="{\"msg\":\"数据查询失败\"}";
	/**
	 * 常量 0
	 */
	public static final String UNUSED_STATUS="0";
	/**
	 * 常量 1
	 */
	public static final String INUSE_STATUS="1";
	/**
	 * BM$_TABLECODE tableId 203
	 */
	public static final String TECHNICAL_CLASS_ID="203";
	/**
	 * TECHNICALGRADE
	 */
	public static final String TECHNICAL_GRADE="TECHNICALGRADE_CODE";
	/**
	 * 里程因子  3.75 用于计算路面伤害面积
	 */
	public static final String MILESTONE_FACTOR="3.75";
	/**
	 * 表名 ZCYH_HISTROY_INFO 
	 */
	public static final String ZCYH_HISTROY_INFO_NAME="ZCYH_HISTROY_INFO";
	/**
	 * 主键前缀 ZCYH_HISTROY_BIG_BUILD_年份 表的主键 PK_FD_OBJECTID前缀
	 */
	public static final String ZCYH_HISTROY_BIG_BUILD_PK_VALUE="PK_FD_OBJECTID_BIG_BUILD";
	/**
	 * 表名 ZCYH_HISTROY_BIG_BUILD
	 */
	public static final String ZCYH_HISTROY_BIG_BUILD_NAME="ZCYH_HISTROY_BIG_BUILD";
	/**
	 * 大修
	 */
	public static final String ZCYH_DAXIU="大修";
	/**
	 * 中修
	 */
	public static final String ZCYH_ZHONGXIU="中修";
	/**
	 * 时间线  1       当前时间减年份大于等于10 或者 当前时间减年份大于等于5 
	 */
	public static final String ZCYH_TIME_LINE_ONE="1";
	/**
	 * 时间线  2         当前时间减年份大于等于5小于10 或者 当前时间减年份大于等于3小于5
	 */
	public static final String ZCYH_TIME_LINE_TWO="2";
	/**
	 * 时间线  3         当前时间减年份小于5 或者 当前时间减年份小于3
	 */
	public static final String ZCYH_TIME_LINE_THREE="3";
	/**
	 * 时间线 5
	 */
	public static final String ZCYH_TIME_LINE_FIVE="5";
	/**
	 * 时间线 10
	 */
	public static final String ZCYH_TIME_LINE_TEN="10";
	/**
	 * 主键前缀 ZCYH_HISTROY_BETWEEN_BUILD_年份 表的主键 PK_HISTROY_BEW_BUILD前缀
	 */
	public static final String ZCYH_HISTROY_BETWEEN_BUILD_PK_VALUE="PK_HISTROY_BEW_BUILD";
	/**
	 * 表名 ZCYH_HISTROY_BETWEEN_BUILD
	 */
	public static final String ZCYH_HISTROY_BETWEEN_BUILD_NAME="ZCYH_HISTROY_BEW_BUILD";
	/**
	 * 表名 ZCYH_HISTROY_BUILD
	 */
	public static final String ZCYH_HISTROY_BUILD_NAME="ZCYH_HISTROY_BUILD";
	/**
	 * 主键前缀  ZCYH_HISTROY_BUILD_年份 表的主键PK_FD_BUILD前缀
	 */
	public static final String ZCYH_HISTROY_BUILD_PK_VALUE="PK_FD_BUILD";
	/**资产养护统计图通用颜色*/
	public static final String STATISTICS_COLOR="#0F0F0F";	
	/**
	 * BM$_TABLECODE tableid 666
	 */
	public static final String ZCYH_YANGHU_TABLE_ID="1401";
	/**
	 * BM$_TABLECODE type Nature
	 */
	public static final String ZCYH_YANGHU_TYPE="Nature";
	/**
	 * 养护历史menuId 806
	 */
	public static final String ZCYH_CONSERVE_HISTROY_MENUID="806";
	/**
	 * redis缓存失效时间 小时
	 */
	public static final Long NCGL_REDIS_EXPIRY_DATE=2L;
	/**
	 * success
	 */
	public static final String MSG_SUCCESS="{\"msg\":\"success\"}";
	/**
	 * fail
	 */
	public static final String MSG_FAIL="{\"msg\":\"fail\"}";
	/**
	 * exist
	 */
	public static final String MSG_NOT_EXIST="{\"msg\":\"notexist\"}";
	/**
	 * com.mysql.jdbc.Driver
	 */
	public static final String MYSQL="com.mysql.jdbc.Driver";
	/**
	 * BASE TABLE
	 */
	public static final String MS_TABLE_TYPE="BASE TABLE";
	/**
	 * 检评指标项
	 */
	public static final String[] ZCJP_INDEX = new String[] { "PCI",
			"PQI", "RDI", "RQI", "SRI" };
	
	/**
	 * 附件属性字段名称
	 */
	public static final String URL = "url";
	public static final String FILE_ID = "fileId";
	public static final String ORIGIN_NAME = "originName";
	public static final String NAME = "name";
	public static final String EXT = "fileext";
	public static final String SAVE_NAME = "saveName";
	public static final String FILE_PATH = "filePath";
	public static final String FILE_TYPE = "fileType";
	public static final String FILE_FLAG = "fileFlag";
	public static final String FILE_NAME = "fileName";
	public static final String FILE_EXT = "fileExt";
	public static final String FILE_KEY = "files";
	
	/**
     * yyyy/MM/dd HH:mm:ss
     */
    public static final String FORMATE_TIME = "yyyy/MM/dd HH:mm:ss";
}