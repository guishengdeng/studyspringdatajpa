package com.biz.soa.vo.sap;

/**
 * Created by defei on 5/20/17.
 */
public class CompanyVO {

	/**
	 * 接口标识	CHAR	1	0为创建，1为修改。
	 */
	private String ZFLAG;

	/**
	 * 智能终端客户号	CHAR	10	智能终端传输的客户号。
	 */
	private String REMARK;

	/**
	 * SAP客户编号	CHAR	10	SAP系统自动生成客户号，再返回给智能终端。
	 */
	private String KUNNR;

	/**
	 * 客户帐户组 	CHAR	4	Z018为省级平台商，城市合伙人为Z019。
	 * require
	 */
	private String KTOKD;

	/**
	 * 客户名称	CHAR	30
	 * require
	 */
	private String NAME1;

	/**
	 * 营业执照号	CHAR	20	营业执照号。
	 * require
	 */
	private String STCEG;

	/**
	 * 增值税登记号 	CHAR	20	税务登记证。
	 * require
	 */
	private String STCD1;

	/**
	 * 组织机构代码	CHAR	20	组织机构代码。
	 * require
	 */
	private String STCD2;

	/**
	 * 客户地址	CHAR	60
	 * require
	 */
	private String STRAS;

	/**
	 * 邮政编码	CHAR	10	默认为999999
	 */
	private String PSTLZ;

	/**
	 * 搜索字段	CHAR	10
	 * require
	 */
	private String SORTL;

	/**
	 * REGIO	地区	CHAR	3	SAP提供地区编码文档。
	 * require
	 */
	private String REGIO;

	/**
	 * 国家	CHAR	3	默认CN。
	 */
	private String LAND1;

	/**
	 * 公司代码	CHAR	4	默认为4056
	 */
	private String BUKRS;

	/**
	 * 总帐中的统驭科目	CHAR	10	默认为"1122002001"。
	 */
	private String AKONT;

	/**
	 * 排序码	CHAR	3	可以默认为031。
	 */
	private String ZUAWA;

	/**
	 * 销售组织 	CHAR	4	默认为4056
	 */
	private String VKORG;

	/**
	 * 分销渠道	CHAR	2	默认为20
	 */
	private String VTWEG;

	/**
	 * 产品组 	CHAR	2	默认10
	 */
	private String SPART;

	/**
	 * 创建人	CHAR	12	客户创建人。
	 */
	private String ERNAM;

	/**
	 * 账户分配组	CHAR	2	默认为Z2。
	 */
	private String KTGRD;

	/**
	 * 付款条件代码	CHAR	4	默认001
	 */
	private String ZTERM;

	/**
	 * 税分类码	CHAR	1	默认1
	 */
	private String TAXKD;

	/**
	 * 联系人名称	CHAR	35	联系人名称(新增)。
	 */
	private String CONTACT;

	/**
	 * 电话号码	CHAR	30	电话号码。
	 */
	private String TEL_NUMBER;

	/**
	 *  银行国家代码	CHAR	3	默认CN。
	 */
	private String BANKS;

	/**
	 * 银行编号	CHAR	15	银行编号。每个银行的编码。
	 */
	private String BANKL;

	/**
	 * 银行账号	CHAR	18
	 */
	private String BANKN;

	/**
	 * 帐户持有人姓名 	CHAR	60
	 */
	private String KOINH;

	/**
	 * 银行名称	CHAR	60
	 */
	private String BANKA;

	/**
	 * 到站	CHAR	25
	 */
	private String ABLAD;

	/**
	 * 工厂日历	CHAR	2	默认为CN。
	 */
	private String KNKAL;

	/**
	 * 预留1	CHAR	50	预留1
	 */
	private String ZTEXT1;

	/**
	 * 预留2	CHAR	50	预留2
	 */
	private String ZTEXT2;

	/**
	 * 预留3	CHAR	50	预留3
	 */
	private String ZTEXT3;

	/**
	 * 预留4	CHAR	50	预留4
	 */
	private String ZTEXT4;

	/**
	 * 预留5	CHAR	50	预留5
	 */
	private String ZTEXT5;
}
