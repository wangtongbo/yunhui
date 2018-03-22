package com.lakala.ls;

public class CONMSG {
	
	public static final String OTPMESSAGE_SUCCESS = "00";//00 :成功
	
	public static final String OTPMESSAGE_STATE_INVALID = "0";//0 :验证码失效或已验证
	
	public static final String OTPMESSAGE_STATE_OK = "1";//1 :验证码有效
	
	public static final String OTPMESSAGE_STATE_USED = "2";//2 :验证码已经使用

	/**
	 * 生成流水号数据库表名
	 */
	public static final String UNIQUE_ID_TABLE_NAME = "UniqueId";

	/**
	 * 商户状态
	 * 1:已启用
	 * 2:已停用
	 */
	public static final String MERCHANT_STATE_1 = "1";

	/**
	 * 分期还款单位
	 * 1:日
	 * 2:月
	 * 3:年
	 */
	public static final String PHASED_REPAY_UNIT_DAY = "1";
	public static final String PHASED_REPAY_UNIT_MONTH = "2";
	public static final String PHASED_REPAY_UNIT_YEAR = "3";

	/**
	 * 分期还款单位
	 * 1:等额本息
	 * 2:等额本金
	 * 3:先还息再还本
	 * 4:一次性还本付息
	 */
	public static final String REPAY_MODE_1 = "1";
	public static final String REPAY_MODE_2 = "2";
	public static final String REPAY_MODE_3 = "3";
	public static final String REPAY_MODE_4 = "4";

	/**
	 * 贷款记录状态
	 * 1:申请中
	 * 2:放款成功
	 * 3:放款失败
	 */
	public static final String LOAN_RECORD_STATE_1 = "1";
	public static final String LOAN_RECORD_STATE_2 = "2";
	public static final String LOAN_RECORD_STATE_3 = "3";

	/**
	 * 贷款记录状态(易分期/替你还)
	 * 1.审核中
	 * 2.放款失败
	 * 3.放款成功
	 * 4.已还清
	 * 5.已逾期
	 * 6.审核失败
	 * 7.待邮件确认
	 * 8.芝麻信用待授权
	 * 9.预进件待补件
	 * 10.预进件订单已取消
	 */
	public static final String YFQ_LOAN_RECORD_STATE_1 = "1";
	
	
	
	public static final String ISEXIST_0 = "0";//0-未注册
	
	public static final String ISEXIST_1 = "1";//1-已注册
	
	public static final String PWDLEVEL_001 = "001";//BAD
	
	public static final String PWDLEVEL_002 = "002";//GENERAL
	
	public static final String PWDLEVEL_003 = "003";//GOOD
	
	
	
	public static final String LOGINTYPE_1 = "1";//登录类型：手机号登录
	
	public static final String LOGINTYPE_2 = "2";//登录类型：邮箱登录
	
	public static final String LOGINTYPE_3 = "3";//登录类型：登录名登录
	
	public static final String USER_IDENTIFIER_0="0";//没有相同身份证
	public static final String USER_IDENTIFIER_1="1";//有相同身份证
	
	
	public static final String AUTH_STATE_0="0";//wei实名
	public static final String AUTH_STATE_1="1";//实名
	
	public static final String CPS_MODEL_0="0";
	public static final String CPS_MODEL_1="1";
	
	public static final String STEP_FLAG_0="0";//否
	public static final String SETP_FLAG_1="1";//有阶梯

	/**
	 * 对账相关常量参数
	 */
	public static final String BATCH_STATE_0 = "0"; // 初始化
	public static final String BATCH_STATE_1 = "1"; // 成功
	public static final String BATCH_STATE_2 = "2"; // 失败

	public static final String PHASE_STATE_1 = "1"; // 已对账
	public static final String PHASE_STATE_2 = "2"; // 供货商分润规则未维护
	public static final String PHASE_STATE_3 = "3"; // 渠道分润规则未维护
	public static final String PHASE_STATE_4 = "4"; // 渠道对账不做分润

	public static final String PHASE_1 = "1"; // 供货商对账
	public static final String PHASE_2 = "2"; // 渠道对账

	public static final String FIRST_SUCCESS_LOAN_FLAG_0 = "0"; // 非首次贷款成功
	public static final String FIRST_SUCCESS_LOAN_FLAG_1 = "1"; // 首次成功贷款

}

