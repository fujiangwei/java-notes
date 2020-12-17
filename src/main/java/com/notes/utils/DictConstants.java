package com.notes.utils;

/**
 * 字典枚举
 */
public enum DictConstants {
	// 运维公司
    SYSTEM_COMPANY("9998"),

    // ============================= 市场字典 ===================================
    // 上海
    PERMIT_MARKET_SHANGHAI("1"),
    // 深圳
    PERMIT_MARKET_SHENZHEN("2"),
    // 上期所
    PERMIT_MARKET_SHANGQISUO("3"),
    // 郑商所
    PERMIT_MARKET_ZHENGSHANGSUO("4"),
    // 银行间
    PERMIT_MARKET_YINHANGJIAN("5"),
    // 场外
    PERMIT_MARKET_CHANGWAI("6"),
    // 中金所
    PERMIT_MARKET_ZHONGJINSUO("7"),
    // 大商所
    PERMIT_MARKET_DASHANGSUO("9"),
    // 股转市场
    PERMIT_MARKET_GUZHUANSHICHANG("10"),
    // 能源交易所
    PERMIT_MARKET_NENGYUANJIAOYISUO("34"),
    // 沪港股通
    PERMIT_MARKET_GANGGUTONG_HU("35"),
    // 深港股通
    PERMIT_MARKET_GANGGUTONG_SHEN("36"),
    // 银行间场外
    PERMIT_MARKET_YINHANGCHANGWAI("5,6"),


	/**现货交易市场编号列表**/
	XH_MARKET_NO_LIST("1,2,10,35,36"),
	/**期货交易市场编号列表**/
	QH_MARKET_NO_LIST("3,4,7,9,34"),
	/**期货交易市场编号列表**/
	QH_MARKET_LIST("3,4,7,9"),

    // ======================== 一些被迫视为魔法数据的字符串 ==========================

    /** 一些被迫视为魔法数据的字符串 */

    COMMA(","),
    /** 空字符串 */
    VOID(""),
    /** 空格 */
    SPACE(" "),
    /** 中划线（减号） */
	MINUS_SIGN("-"),
    /** &（表示and的符号） */
	AMPERSAND("&"),
    STATEMENT_ALL("全部"),
    ASTERISK("*"),
    NOT_DISTINGUISH("不区分"),
    EXCEL_XLSX(".xlsx"),
    EXCEL_XLS(".xls"),
    DESC("desc"),
    RELATIVEDIFF("relativediff"),
    WORKFLOW("workflow"),
    // ================================现货和期货 int =====================================

    /** 现货和期货 int */

    SPOT_PROPERTY("1"),
    // 期货类型
    FUTURES_PROPERTY("2"),

    /** 文件格式 */
    FILE_TYPE_DBF("dbf"),

    /** excel文件列头类型，0表示英文，1表示中文， */
    FILE_TYPE_ENGLISH("0"),
    FILE_TYPE_CHINESE("1"),


    /** 证券类型 int */

    BUYOUT_REPO("14"),

    /** 到期可用影响标识，0表示不影响，1表示影响 */
    MATURE_ENABLE_FLAG_NO("0"),
    MATURE_ENABLE_FLAG_YES("1"),


    /** 费用类型 int */

    TRADING_FEE("1"),
    CLEARING_FEE("10"),
    DELIVERY_FEE("11"),

    // 操作员
    DEVELOPMENT_OPERATOR("2"),
    // ========================= 产品状态 ==============================
    // 空状态
    FUND_BLANK_STATUS("0"),
	// 产品正常状态
	FUND_NORMAL_STATUS("1"),
	// 产品注销状态
	FUND_CANCEL_STATUS("2"),
	// 产品冻结状态
	FUND_FREEZE_STATUS("3"),
	// ======================== 公司状态 ===============================
	// 公司正常状态
	COMPANY_NORMAL_STATUS("1"),
	// 公司冻结状态
	COMPANY_FEEZE_STATUS("2"),
	// 公司注销状态
	COMPANY_CANCEL_STATUS("3"),
	// ========================== 组合状态 =================================
	// 组合空状态
	COMBI_BLANK_STATUS("0"),
	// 组合正常状态
	COMBI_NORMAL_STATUS("1"),
	// 组合冻结状态
	COMBI_FREEZE_STATUS("3"),
	// 组合注销状态
	COMBI_CANCEL_STATUS("4"),

	// ========================= 单元状态 ==================================
	// 单元空状态
	ASSET_BLANK_STATUS("0"),
	// 单元正常状态
	ASSET_NORAMAL_STATUS("1"),
	// 单元注销状态
	ASSET_CANCEL_STATUS("2"),
	// 单元冻结状态
	ASSET_FREEZE_STATUS("3"),

	// 资金账号正常状态
	CAPT_NORAMAL_STATUS("1"),
	// 资金账号冻结状态
	CAPT_FREZZE_STATUS("2"),
	// 资金账号注销状态
	CAPT_CANCEL_STATUS("3"),

	// ======================== 操作员类型 ================================
	// 运维人员
	OPERATOR_MAINTENANCE("2"),
	// 客户人员
	OPERATOR_GUEST("1"),

	// =============================== 是否 ================================
	// 是
	FLAG_YES("1"),
	// 否
	FLAG_NO("0"),
	// ================================ 通用选项 ===============================
	// 不区分选项
	SELECT_NOT_DISTINGUISH("0"),
	// 另一个不区分选项
	SELECT_ALL_SELECTED("-1"),
	// =============================== 产品的三个权限 ===================================
	// 查询权限
	QUERY_RIGHT("1"),
	// 操作权限
	OP_RIGHT("2"),
	// 审批权限
	APPROVE_RIGHT("4"),

	// ============================== 资金账号类型 ================================
	// 普通
	BUSINESS_PROPERTY_NORMAL("0"),
	// 个股期权
	BUSINESS_PROPERTY_STOCK("1"),
	// 融资融券
	BUSINESS_PROPERTY_RZRQ("2"),

	// =============================== 清算模式 ====================================
	// 正常清算
	PROCESS_NORMAL("1"),
	// 手动清算
	PROCESS_HANDS("3"),

	// 资金账号密码已验证
	PWD_VERIFIED("2"),

	// =============================== 股东指定状态 ===================================
	// 深交所托管
	BIND_SHENZHENG_TRUSTEESHIP("5"),
	// 深交所指定
	BIND_SHENZHENG_BINDED("6"),
	// 股转市场托管
	BIND_GUZHUAN_TRUSTEESHIP("7"),
	// 股转市场指定
	BIND_GUZHUAN_BINDED("8"),

	// 证券类型 3债券
	STOCK_BOND_TYPE("3"),
	// 证券类型9期货
	STOCK_FUTURE_TYPE("9"),
    /** 证券类别，60认购期权，61认沽期权 */
    STOCK_TYPE_60("60"),
    STOCK_TYPE_61("61"),

    /** 板块类型，证券类别，委托方向，产品等，-1不区分 */
    TYPE_SAME("-1"),

	// 债券利率类型 1浮动
	Interest_Rate_Type_Float("1"),
	// 债券利率类型2固定
	Interest_Rate_Type_Stand("2"),

	// 发行人选择类型： 利率调整选择权
	Publisher_Operation_Type_Interest_Rate("2"),

	// 系统参数
	SYSTEM_PARAMETER("1"),
	// 公司级系统参数
	COMPANY_PARAMETER("2"),
	// 产品级系统参数
	FUND_PARAMETER("4"),

	// 申报单位：1 张 2手
	REPORT_UNIT_ZHANG("1"),
	REPORT_UNIT_SHOU("2"),

    /** 请求类型 */
    METHOD_POST("POST"),

    /** 业务操作标识 */
    BUSINESS_OP_FLAG_301("301"),
    BUSINESS_OP_FLAG_302("302"),

    /** 菜单使用范围，1仅客户使用，2运维使用，3客户和运维使用 */
    MENU_USE_RANGE_CUSTOMER("1"),
    MENU_USE_RANGE_MANAGER("2"),
    MENU_USE_RANGE_ALL("3"),

    /** 是否开通组合层，1开启，0没有开启 */
    COMBI_ENABLE("1"),
    COMBI_DISABLE("0"),
    COMBI_NAME("combi"),
    /** 产品是否开启费率 */
    FUND_RATE_OPEN("1"),

    /** 产品、单元状态，1正常，2注销，3冻结 */
    FUND_ASSET_STATUS_NORMAL("1"),
    FUND_ASSET_STATUS_LOGOUT("2"),
    FUND_ASSET_STATUS_FREEZE("3"),

    /** 调整类型，“0”按产品，“1”按单元 */
    ADJUST_FLAG_FUND("0"),
    ADJUST_FLAG_ASSET("1"),
    ADJUST_FLAG_VOID(""),

    /** 净值核对标识，1核对，0/“”未核对 */
    NET_VALUE_CHECK_YES("1"),
    NET_VALUE_CHECK_VOID(""),
    NET_VALUE_CHECK_NO("0"),

    /** 证券核对标识，1核对，0/“”未核对 */
    STOCK_CHECK_YES("1"),
    STOCK_CHECK_VOID(""),
    STOCK_CHECK_NO("0"),

    /** 核对状态 */
    CHECK_STATUS_SUCCESS("成功"),
    CHECK_STATUS_FAIL("失败"),

    /** 复核状态，0表示无需复核 */
    CHECK_STATUS_NO_CHECK("0"),

    /** 是否导入标识,0未导入，1导入 */
    IMPORT_FLAG_YES("1"),
    IMPORT_FLAG_NO("0"),

    /** 是否信用股东，1是 */
    CREDIT_FLAG_YES("1"),

    /** 资产类别，A应收资产，B应付资产 */
    ASSET_TYPE_A("A"),
    ASSET_TYPE_B("B"),

    /** 券商类别：1现货，2期货 */
    BROKER_TYPE_SPOT("1"),
    BROKER_TYPE_FUTURE("2"),

    /** 操作类型，1普通操作员，2运维人员 */
    OPERATOR_TYPE_ONE("1"),
    OPERATOR_TYPE_TWO("2"),

    /** 资金账号的业务类型，2融资融券 */
    BUSINESS_PROPERTY_TWO("2"),

    /** 资金账号状态，1解冻，2冻结 */
    CAPITAL_ACCOUNT_ONE("1"),
    CAPITAL_ACCOUNT_TWO("2"),

    /** 处理标志,0未处理，1已处理，2正在处理,3 */
    PROCESS_FLAG_ZERO("0"),
    PROCESS_FLAG_ONE("1"),
    PROCESS_FLAG_TWO("2"),
    PROCESS_FLAG_THREE("3"),

    /** 营业部使用的柜台，柜台类型，1资管期货 */
    COUNTER_TYPE_FUTURE("1"),

    /** 是否是MOM产品，1是，0不是 */
    MOM_FUND("1"),
    MOM_FUND_NOT("0"),

    /** 多空类型 */
    POSITION_TYPE_TWO("2"),
    POSITION_TYPE_B("b"),

    /** 流程类型,0表示指令审批 */
    JUDGE_FLOW("0"),

	/**
	 * 不展示零持仓
	 */
	ZERO_POSITION_INVISIBLE("0"),
	/**
	 * 显示零持仓
	 */
	ZERO_POSITION_VISIBLE("1"),
	/**
	 * 仅显示零持仓
	 */
	ZERO_POSITION_ONLY("2"),

    /** uft发送消息 */
    UFT_INSTANCE_CLIENT("client"),

    /** 错误参数 */
    ERROR_INFO("error_info"),

	 /** 是否开通参数 */
	PARAMVALUE_ENABLE("1"),
	PARAMVALUE_DISABLE("0"),

    /**科目调整方式 1根据估值表调整 2手工调整*/
    SUBJECT_ADJUST_TYPE_BY_VALUATION("1"),
    SUBJECT_ADJUST_TYPE_MANUAL("2"),

    /**
     * 委托审批类型（0不审批 1手工审批 2自动审批）
     */
    ENTRUST_APPROVE_TYPE_NO("0"),
    ENTRUST_APPROVE_TYPE_MANUAL("1"),
    ENTRUST_APPROVE_TYPE_AUTO("2");

    private String value;

    /**
     * Msg theme enum
     *
     * @param value value
     */
	DictConstants(String value) {
        this.value = value;
    }

    /**
     * Get value string
     *
     * @return string
     */
    public String getValue() {
        return value;
    }
}
