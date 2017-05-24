package com.biz.manage.controller.shop;


import com.biz.core.ali.oss.config.OssConfig;
import com.biz.core.ali.oss.util.OssUtil;
import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.mysql.po.org.ShopDetailPo;
import com.biz.gbck.dao.mysql.po.org.ShopPo;
import com.biz.gbck.dao.mysql.po.org.UserPo;
import com.biz.gbck.dao.mysql.po.security.Admin;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.enums.org.CompanyLevel;
import com.biz.gbck.enums.user.AuditRejectReason;
import com.biz.gbck.enums.user.AuditStatus;
import com.biz.gbck.enums.user.ShopTypeStatus;
import com.biz.gbck.vo.org.*;
import com.biz.gbck.vo.platform.PartnerSearchResVo;
import com.biz.gbck.vo.platform.PartnerSearchVo;
import com.biz.gbck.vo.spring.PageVO;
import com.biz.manage.controller.BaseController;
import com.biz.manage.util.AuthorityUtil;
import com.biz.service.org.interfaces.ShopService;
import com.biz.service.org.interfaces.ShopTypeService;
import com.biz.service.org.interfaces.UserService;
import com.biz.soa.feign.client.org.PlatformFeignClient;
import com.biz.soa.feign.client.org.ShopFeignClient;
import com.biz.soa.feign.client.org.UserFeignClient;
import org.apache.commons.lang.StringUtils;
import org.codelogger.utils.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.google.common.collect.Lists.newArrayList;

@Controller
@RequestMapping("/shops")
@Secured("ROLE_SHOP")
public class ShopController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);


    @Autowired
    private ShopFeignClient shopFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private OssConfig config;

    @Autowired
    private PlatformFeignClient platformFeignClient;


    /**
     * 列出所有未审核通过的商铺 ShopSearchVo vo
     */
    @GetMapping
    @RequestMapping(value = "auditList")
    @PreAuthorize("hasAuthority('OPT_SHOP_AUDITLIST')")
    public ModelAndView listShopOfWaitForAudit()
            throws CommonException {

        logger.debug("Received /shops/auditList GET request.");
      /*  vo.setAuditStatus( AuditStatus.NORMAL_AND_HAS_NEW_UPDATE_WAIT_FOR_AUDIT.getValue());
        vo.setAuditStatusTwo(AuditStatus.WAIT_FOR_AUDIT.getValue());*/
        ModelAndView mav = new ModelAndView("/org/shop/auditList");
        List<ShopDetailResVo> vos = shopFeignClient.findAllWaitForShop();
        mav.addObject("vos", vos);
        /*mav.addObject("vo", vo);*/
        return mav;
    }

    /**
     * 列出所以已审核的商户
     */
    @GetMapping
    @RequestMapping(value = "completeAuditList")
    @PreAuthorize("hasAuthority('OPT_SHOP_AUDITLIST')")
    public ModelAndView listShopOfCompleteForAudit(ShopSearchVo vo)
            throws CommonException {
        ModelAndView mav = new ModelAndView("/org/shop/completeAuditList");
        mav.addObject("vo", vo);
        logger.debug("Received /shops/auditList GET request.");
       if(vo.getAuditStatus() == null){
           vo.setAuditStatus( AuditStatus.NORMAL.getValue());
           vo.setAuditStatusTwo( AuditStatus.AUDIT_FAILED.getValue());
       }
        PageVO<ShopDetailResVo> shopSearchResVoPage = shopFeignClient.findShopAuditDataOfWaitForAudit(vo);
        mav.addObject("shopSearchResVoPage", shopSearchResVoPage);
        return mav;
    }

    /**
     * 进入商户审核界面
     * corporateIdPhoto; //法人身份证
     * liquorSellLicence; //酒类流通许可证
     * shopPhoto; //门头照片
     * businessLicence; //营业执照
     */
    @RequestMapping(value = "audit", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_SHOP_AUDIT')")
    public ModelAndView auditShopDetail(
            @RequestParam("shopId") Long shopId, HttpServletRequest request) throws Exception {

        logger.info("Received /shops/audit GET request with shopId:{}.", shopId);
        ModelAndView modelAndView = new ModelAndView("/org/shop/auditDetail");
        ShopDetailResVo shopDetailResVo =
                shopFeignClient.findShopAuditDataOfWaitForAuditByShopId(shopId);
        List<AuditRejectReason> auditRejectReasons = newArrayList();
        if (shopDetailResVo != null) {
            logger.info("Received /shops/audit GET request with shopDetailVo:{}.",shopDetailResVo);
                auditRejectReasons.add(AuditRejectReason.DETAIL_INVALID);
                for (AuditRejectReason auditRejectReason : AuditRejectReason.values()) {
                    if (auditRejectReason != AuditRejectReason.DETAIL_INVALID)
                        auditRejectReasons.add(auditRejectReason);
                }
            shopDetailResVo.setCorporateIdPhoto(this.findImgUrl(shopDetailResVo.getCorporateIdPhoto()));
            shopDetailResVo.setLiquorSellLicence(this.findImgUrl(shopDetailResVo.getLiquorSellLicence()));
            shopDetailResVo.setShopPhoto(this.findImgUrl(shopDetailResVo.getShopPhoto()));
            shopDetailResVo.setBusinessLicence(this.findImgUrl(shopDetailResVo.getBusinessLicence()));
        } else {
            logger.info("No audit data for shopId:{}.", shopId);
            return modelAndView;
        }
        modelAndView.addObject("shopDetailResVo", shopDetailResVo);
        List<AuditStatus> auditStatusList =
                newArrayList(AuditStatus.NORMAL, AuditStatus.AUDIT_FAILED);
        modelAndView.addObject("auditStatusList", auditStatusList).
                addObject("auditRejectReasons", auditRejectReasons);
        Admin admin=(Admin) AuthorityUtil.getLoginUser();
        if(admin != null && admin.getCompany() != null){
            if(admin.getCompany().getCompanyLevel() == CompanyLevel.ORG_PLATFORM){
                PageVO<PartnerSearchResVo> partners = platformFeignClient.findPartnerList(new PartnerSearchVo(admin.getCompany().getId()));
                modelAndView.addObject("partners", partners.getContent());
            }
        }
        /**-----下面为测试数据需要删除--------**/
        PageVO<PartnerSearchResVo> partners = new PageVO<PartnerSearchResVo>();
        List<PartnerSearchResVo> partnerSearchResVos=newArrayList();
        PartnerSearchResVo vo=new PartnerSearchResVo();
        vo.setId(359874944668536832l);vo.setName("测试公司");
        partnerSearchResVos.add(vo);
        PartnerSearchResVo vo2=new PartnerSearchResVo();
        vo2.setId(359884283051511808l);vo2.setName("茅台");
        partnerSearchResVos.add(vo2);
        partners.setContent(partnerSearchResVos);
        modelAndView.addObject("partners", partners.getContent());
        return modelAndView;
    }


    /**
     * 判断营业执照id是否存在
     * @param businessLicenceId
     * @param shopId
     * @return
     */
    @RequestMapping(value = "isBusinessLicenceIdExist", method = RequestMethod.GET)
    @ResponseBody
    public Boolean isBusinessLicenceIdExist(@RequestParam("businessLicenceId") String businessLicenceId,
                                            @RequestParam("shopId") Long shopId) {

        return shopFeignClient.isBusinessLicenceIdExist(businessLicenceId, shopId);
    }

    /**
     * 判断营业执照id是否存在 不限商户
     */
    @RequestMapping(value = "findShopByBusinessLicenceId", method = RequestMethod.GET)
    @ResponseBody
    public Boolean findShopByBusinessLicenceId(
            @RequestParam("businessLicenceId") String businessLicenceId) {

        return shopFeignClient.findShopByBusinessLicenceId(businessLicenceId);
    }

    /**
     * 保存审核信息
     */
    @RequestMapping(value = "audit", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('OPT_SHOP_AUDIT')")
    public ModelAndView saveAuditResult(
            ShopAuditReqVo shopAuditReqVo) throws CommonException {
        logger.debug("Received /shops/auditList GET request.");
        ModelAndView mav = new ModelAndView("redirect:/shops/auditList.do");
        shopAuditReqVo.setHandler(AuthorityUtil.getLoginUsername());
        shopFeignClient.auditShop(shopAuditReqVo);
        return mav;
    }

    /**
     * 进入商户修改详情页面
     */
    @RequestMapping(value = "updateDetail")
    @PreAuthorize("hasAuthority('OPT_SHOP_UPDATE')")
    public ModelAndView updateDetail( @RequestParam("shopId") Long shopId,Integer msg) {
        logger.debug("Received /shops/updateDetail GET request.");
        ModelAndView modelAndView = new ModelAndView("/org/shop/updateDetail");
        ShopDetailResVo shopDetailResVo =
                shopFeignClient.findShopAuditDataOfWaitForAuditByShopId(shopId);
        List<AuditRejectReason> auditRejectReasons = newArrayList();
        if (shopDetailResVo != null) {
            auditRejectReasons.add(AuditRejectReason.DETAIL_INVALID);
            for (AuditRejectReason auditRejectReason : AuditRejectReason.values()) {
                if (auditRejectReason != AuditRejectReason.DETAIL_INVALID)
                    auditRejectReasons.add(auditRejectReason);
            }
            shopDetailResVo.setCorporateIdPhoto(this.findImgUrl(shopDetailResVo.getCorporateIdPhoto()));
            shopDetailResVo.setLiquorSellLicence(this.findImgUrl(shopDetailResVo.getLiquorSellLicence()));
            shopDetailResVo.setShopPhoto(this.findImgUrl(shopDetailResVo.getShopPhoto()));
            shopDetailResVo.setBusinessLicence(this.findImgUrl(shopDetailResVo.getBusinessLicence()));
        } else {
            logger.debug("No audit data for shopId:{}.", shopId);
            return modelAndView;
        }
        modelAndView.addObject("shopDetailResVo", shopDetailResVo);
        List<AuditStatus> auditStatusList =
                newArrayList(AuditStatus.NORMAL, AuditStatus.AUDIT_FAILED,AuditStatus.WAIT_FOR_AUDIT);
        modelAndView.addObject("auditStatusList", auditStatusList).
                addObject("auditRejectReasons", auditRejectReasons);
        Admin admin=(Admin) AuthorityUtil.getLoginUser();
        if(admin != null && admin.getCompany() != null){
            if(admin.getCompany().getCompanyLevel() == CompanyLevel.ORG_PLATFORM){
                PageVO<PartnerSearchResVo> partners = platformFeignClient.findPartnerList(new PartnerSearchVo(admin.getCompany().getId()));
                modelAndView.addObject("partners", partners.getContent());
            }
        }
        if(msg != null){
            modelAndView.addObject("msg",msg==1?"修改成功":"修改失败");
        }
        return modelAndView;
    }

    /**
     * 保存修改信息
     */
    @RequestMapping(value = "saveUpdateDetail",method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('OPT_SHOP_UPDATE')")
    public ModelAndView saveUpdateDetail(ShopAuditReqVo shopAuditReqVo) {
        logger.debug("Received /shops/saveUpdateDetail POST request.");
        shopAuditReqVo.setHandler(AuthorityUtil.getLoginUsername());
        Integer msg= 1 ;
        Boolean judge=shopFeignClient.saveUpdateDetail(shopAuditReqVo);
        if(!judge){
            msg=0;
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/shops/updateDetail.do?shopId="+shopAuditReqVo.getShopId()+"&msg="+msg+"");
        return modelAndView;
    }

    /**
     * 进入店铺创建页面
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_SHOP_SAVE')")
    public ModelAndView forwardToShopCreatePage() {
        logger.debug("Received /shops GET request.");
        return new ModelAndView("org/shop/new");
    }



    /**
     * 店铺搜索
     */
   /* @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_SHOP_SEARCH')")
    public ModelAndView searchShop(SearchShopReqVo searchShopReqVo) throws CommonException {
        logger.debug("Received /shops POST request.");
        List<SearchShopRespVo> list = shopService.findShopAndUserShop(searchShopReqVo);
        return new ModelAndView("shop/list", "shops", list);
    }

    @RequestMapping(value = "goSearch", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_SHOP_SEARCH')")
    public ModelAndView goSearch()
            throws CommonException {
        return new ModelAndView("shop/list");
    }*/

    /**
     * 创建店铺
     */
   /* @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('OPT_SHOP_SAVE')")
    public ModelAndView createShop(ShopEditVo shopEditVo) throws CommonException {
        logger.debug("Received /shops POST request.");
        ShopRo shopRo = shopService.createShopByAdmin(shopEditVo, AuthorityUtil.getLoginUsername());
        return new ModelAndView(format("redirect:/shops/%s.do", shopRo.getId()));
    }*/



    /**
     * 查看店铺详情
     */
   /* @RequestMapping(value = "{shopId}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_SHOP_DETAIL')")
    public ModelAndView forwardToShopEditPage(
            @PathVariable("shopId") Long shopId) throws CommonException {

        logger.debug("Received /shops/{} GET request.", shopId);
        ShopPo shop = shopService.findShopPo(shopId);
        ShopStatus[] shopStatusList = ShopStatus.values();
        return new ModelAndView("shop/edit", "shop", new ShopPoToShopEditVo().apply(shop))
                .addObject("shopStatusList", shopStatusList)
                .addObject("emp", depotEmployeeService.getDepotEmployeeById(shop.getInviterCode()));
    }*/

    /**
     * 更新店铺
     */
   /* @RequestMapping(value = "{shopId}", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('OPT_SHOP_UPDATE')")
    public ModelAndView updateShop(
            @PathVariable Long shopId, ShopEditVo shopEditVo) throws CommonException {

        logger.debug("Received /shops/{} POST request.", shopId);
        ShopRo shopRo = shopService.updateShop(shopEditVo, AuthorityUtil.getLoginUsername());
        return forwardToShopEditPage(shopRo.getId());
    }*/

    /**
     * 更新店铺状态(单个)
     */
    @RequestMapping(value = "toggleShopStatus", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('OPT_SHOP_UPDATE')")
    @ResponseBody
    public Boolean updateShopStatus( Long shopId) {
        Integer status = shopFeignClient.findShopRoById(shopId).getStatus();
        return shopFeignClient.updateShopStatus(shopId,
                Objects.equals(status,CommonStatusEnum.ENABLE.getValue()) ?
                        CommonStatusEnum.DISABLE :
                        CommonStatusEnum.ENABLE);
    }

    /**
     * 更新店铺状态(多个)
     */
    @RequestMapping(value = "toggleShopsStatus", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('OPT_SHOP_UPDATE')")
    @ResponseBody
    public Boolean updateShopsStatus( @RequestParam("shopIds") ArrayList<Long> shopIds,
                                      @RequestParam("status") CommonStatusEnum status) {
        if(CollectionUtils.isNotEmpty(shopIds)){
            for(Long shopId:shopIds){
                shopFeignClient.updateShopStatus(shopId,status);
            }
            return true;
        }
        return false;
    }

    /**
     * 某门店管理的所有店铺
     */
   /* @RequestMapping(value = "listByDepot", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_SHOP_LIST')")
    public ModelAndView listByDepot(
            @RequestParam(value = "depotId", required = false) String depotId) throws CommonException {
        if(ManageServlet.getAdmin().getAdminLevel() == AdminLevel.ADMIN_DEPOT){
            depotId = ManageServlet.getDepotIdOfCurrentSelected();
        }
        DepotRo depotRo = depotService.findDepotRo(depotId);
        List<ShopPo> shops = shopService.findShopByDepotId(depotId);
        return new ModelAndView("shop/listForDepot", "shops", shops).addObject("depot", depotRo);
    }
*/

    /**
     * 某门店开发的所有店铺
     */
    @RequestMapping(value = "listByAssartDepot", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_SHOP_LIST')")
    public ModelAndView listByAssartDepot(
            @RequestParam("depotId") String depotId) throws CommonException {
//        List<ShopPo> shops = shopService.findShopByDepotId(depotId);
        return new ModelAndView("shop/list", "shops", null);
    }

    @RequestMapping(value = "destroy", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('OPT_SHOP_DESTROY')")
    public Boolean destroyShop(@RequestParam("shopId") Long shopId) {

        try {
            shopFeignClient.destroyShopAndItUsers(shopId, AuthorityUtil.getLoginUsername());
            return true;
        } catch (Exception e) {
            logger.info("Destroy shop[{}] failed.", shopId, e);
            return false;
        }
    }

    @RequestMapping(value = "enableShopByIds", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('OPT_SHOP_UPDATE')")
    public Map enableShopByIds(@RequestParam("ids") String ids) {
        String[] idList = ids.split(",");
        Integer succesCount = shopFeignClient.enableShopByIds(idList);
        Integer failedCount = idList.length - succesCount;
        Map map = new HashMap();
        map.put("successCount", succesCount);
        map.put("failedCount", failedCount);
        return map;
    }

    @RequestMapping(value = "disableShopByIds", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('OPT_SHOP_UPDATE')")
    public Map disableShopByIds(@RequestParam("ids") String ids) {
        String[] idList = ids.split(",");
        Integer succesCount = shopFeignClient.disableShopByIds(idList);
        Integer failedCount = idList.length - succesCount;
        Map map = new HashMap();
        map.put("successCount", succesCount);
        map.put("failedCount", failedCount);
        return map;
    }

    /**
     * 分页查找店铺
     */
   /* @RequestMapping(value = "page", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_SHOP_LIST')")
    public ModelAndView listOrders(ShopQueryReqVo reqVo) {

        logger.debug("Received /shops GET request.");
        ModelAndView mv = new ModelAndView("shop/list");
        PageControl pc = new PageControl(reqVo.getPageSize(), reqVo.getPage());
        List<ShopPo> shops = shopService.searchPageShops(reqVo, pc);
        List<SearchShopRespVo> voList = Lists.transform(shops, new ShopPoToSearchShopRespVo());
        mv.addObject("shops", voList);
        mv.addObject("pageControl", pc);
        mv.addObject("reqVo", reqVo);
        return mv;
    }*/

    /**
     * 验证手机号码是否存在接口
     */
    @RequestMapping(value = "findMobileIsIn", method = RequestMethod.GET)
    @ResponseBody
    public ModelMap findMobileIsIn(String mobile, Long shopId) {
        ModelMap mm = new ModelMap();
        mm.put("data", false);
        if (StringUtils.isNotBlank(mobile)) {
            List<ShopPo> po = shopFeignClient.findShopPoByMobileAndHaveNotShopId(mobile, shopId);
            if (CollectionUtils.isNotEmpty(po)) {
                UserPo existUser = userFeignClient.findUserPoByMobile(mobile);
                if (existUser == null) {
                    existUser = userFeignClient.findUserPoByAccount(mobile);
                }
                mm.put("data", existUser != null);
            }
        } else {
            mm.put("data", "手机号码不能为空");
        }
        return mm;
    }

    @RequestMapping(value = "goImportShop", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_ORDER_LIST')")
    public ModelAndView goImportShop() {
        return new ModelAndView("shop/importShop");
    }

    /**
     *导入商户
     */
   /* @PreAuthorize("hasAuthority('OPT_SHOP_UPDATE')")
    @RequestMapping("saveImportUser")
    public ModelAndView saveImportUser(@RequestParam("uploadDataField") MultipartFile file)
            throws InvalidFormatException {
        String errMsg = null;
        int uploadCount = 0;
        InputStream is=null;
        String status = "success";
        try {
            is =file.getInputStream();
            Workbook workbook =WorkbookFactory.create(is);
            try {
                List<UserVo> userVos= handleUserWorksheet(workbook.getSheetAt(0), 1, 0);
                uploadCount=shopService.saveImportUser(userVos);

                logger.debug("upload success {} ", uploadCount);
                errMsg = String.format("成功由文件[%s]导入%d条数据", file.getOriginalFilename(), uploadCount);
            } catch (CommonException e) {
                logger.warn("上传导入数据文件{}出错", file.getOriginalFilename());
                errMsg = e.getMessage();
                status = "failed";
            }
        } catch (IOException e) {
            logger.error("获取上传文件[{}]输入流出错", file.getOriginalFilename(), e);
            errMsg = String.format("获取上传文件[%s]输入流出错", file.getOriginalFilename());
            status = "failed";
        }finally{
            if(is!=null){
                try{
                    is.close();
                }catch (IOException e) {

                }
            }
        }

        return new ModelAndView("shop/importShop").addObject("errMsg", errMsg).addObject("status", status);
    }*/


    /**
     *导入20倍会员
     */
   /* @PreAuthorize("hasAuthority('OPT_SHOP_UPDATE')")
    @RequestMapping("saveImportUser20VIP")
    public ModelAndView import20VIP(@RequestParam("uploadDataField") MultipartFile file)
            throws InvalidFormatException {
        String errMsg = null;
        int uploadCount = 0;
        InputStream is=null;
        String status = "success";
        List<FailDetail> failDetails = newArrayList();
        try {
            is =file.getInputStream();
            Workbook workbook =WorkbookFactory.create(is);
            try {
                List<User20VIPVo> user20VIPVos= handleUser20VIPWorksheet(workbook.getSheetAt(0), 4, 0);

                uploadCount=shopService.saveImportUser20VIP(user20VIPVos,failDetails);

                logger.debug("upload success {} ", uploadCount);
                errMsg = String.format("成功由文件[%s]导入%d条数据", file.getOriginalFilename(), uploadCount);
            } catch (CommonException e) {
                logger.warn("上传导入数据文件{}出错", file.getOriginalFilename());
                errMsg = e.getMessage();
                status = "failed";
            }
        } catch (IOException e) {
            logger.error("获取上传文件[{}]输入流出错", file.getOriginalFilename(), e);
            errMsg = String.format("获取上传文件[%s]输入流出错", file.getOriginalFilename());
            status = "failed";
        }finally{
            if(is!=null){
                try{
                    is.close();
                }catch (IOException e) {

                }
            }
        }

        return new ModelAndView("shop/importShop").addObject("errMsg", errMsg).addObject("status", status).addObject("failDetails",failDetails);
    }
*/

    /**
     *解析
     */
   /* private List<UserVo> handleUserWorksheet(Sheet sheet, int startRow, int startCol)
            throws CommonException {
        Row row = sheet.getRow(startRow);
        List<UserVo> userVos = Lists.newArrayList();
        while (row != null) {
            try {
                UserVo userVo =new UserVo();
                userVo.setMobile(POIUtil.getCellValue(row.getCell(startCol)));
                userVo.setName(POIUtil.getCellValue(row.getCell(startCol+1)));
                userVo.setDepotId(POIUtil.getCellValue(row.getCell(startCol+2)));
                userVo.setShopName(POIUtil.getCellValue(row.getCell(startCol+3)));
                startRow = startRow + 1;
                userVos.add(userVo);
                logger.debug("a new line name={}", userVo.getName());
                row = sheet.getRow(startRow);
            } catch (Exception e) {
                e.printStackTrace();
                throw new CommonException(String
                        .format("处理表格[%s]第[%d]行[%d]列时出错", sheet.getSheetName(), startRow, startCol));
            }
        }
        return userVos;
    }*/


    /**
     *解析20VIP
     */
   /* private List<User20VIPVo> handleUser20VIPWorksheet(Sheet sheet, int startRow, int startCol)
            throws CommonException {
        Row row = sheet.getRow(startRow);
        List<User20VIPVo> user20VIPVos = Lists.newArrayList();
        while (row != null) {
            try {
                User20VIPVo user20VIPVo = new User20VIPVo();
                user20VIPVo.setNum(POIUtil.getCellValue(row.getCell(startCol)));
                user20VIPVo.setProvinceName(POIUtil.getCellValue(row.getCell(startCol+1)));
                user20VIPVo.setPartnerName(POIUtil.getCellValue(row.getCell(startCol+2)));
                user20VIPVo.setMobile(POIUtil.getCellValue(row.getCell(startCol+3)));
                user20VIPVo.setDepotId(POIUtil.getCellValue(row.getCell(startCol+4)));
                user20VIPVo.setShopName(POIUtil.getCellValue(row.getCell(startCol+5)));
                user20VIPVo.setReceiveProvince(POIUtil.getCellValue(row.getCell(startCol+6)));
                user20VIPVo.setReceiveCity(POIUtil.getCellValue(row.getCell(startCol+7)));
                user20VIPVo.setReceiveCountry(POIUtil.getCellValue(row.getCell(startCol+8)));
                user20VIPVo.setToAddress(POIUtil.getCellValue(row.getCell(startCol+9)));
                user20VIPVo.setIsMatch(POIUtil.getCellValue(row.getCell(startCol+10)));

                startRow = startRow + 1;
                user20VIPVos.add(user20VIPVo);
                logger.debug("a new line name={}",user20VIPVo.getShopName());
                row = sheet.getRow(startRow);
            } catch (Exception e) {
                e.printStackTrace();
                throw new CommonException(String
                        .format("处理表格[%s]第[%d]行[%d]列时出错", sheet.getSheetName(), startRow, startCol));
            }
        }
        return user20VIPVos;
    }*/


    /**
     * 合伙人查看发展的商户
     */
   /* @RequestMapping(value = "findShop", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_SHOP_FIND_BY_DEPOTID')")
    public ModelAndView findDepotfoShopPo(ShopQueryReqVo reqVo) throws CommonException {
        ModelAndView mav = new ModelAndView("shop/findShop");
//        List<ShopPo> shopPos = shopService.findShopFodepotId();
        PageControl pc = new PageControl(reqVo.getPageSize(), reqVo.getPage());
        List<ShopPo> shops = shopService.findShopFodepotId(reqVo,pc);
        List<SearchShopRespVo> voList = Lists.transform(shops, new ShopPoToSearchShopRespVo());
        mav.addObject("shops", voList);
        mav.addObject("pageControl", pc);
        mav.addObject("reqVo", reqVo);
        return mav;
    }*/
    @RequestMapping(value = "updateBlackList", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('OPT_SHOP_UPDATE_BLACK_LIST')")
    public ModelAndView updateBlackList(String blackList) {
        ModelAndView view = new ModelAndView("redirect:/shops/blacklist.do");
        if (org.codelogger.utils.StringUtils.isBlank(blackList)) {
            view.addObject("result", "黑名单不能为空");
            return view;
        }
        String re = shopFeignClient.updateBlackList(blackList);
        if (re.length() > 0) {
            view.addObject("result", re);
            return view;
        } else {
            view.addObject("result", "黑名单设置成功");
            return view;
        }

    }

    @RequestMapping(value = "blacklist", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_SHOP_BLACK_LIST')")
    public ModelAndView getBlackList() {
        logger.debug("received list blackList GET");
        ModelAndView mav = new ModelAndView("shop/blackList");
        List<ShopPo> backShopPoList = shopFeignClient.getShopPoBackList();
        mav.addObject("blackList", backShopPoList);
        return mav;
    }

    @RequestMapping(value = "deleteBlackList", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_SHOP_UPDATE_BLACK_LIST')")
    public ModelAndView deleteBlackList(@RequestParam("shopId") String shopId) {
        shopFeignClient.deleteBlackList(newArrayList(shopId));
        return new ModelAndView("redirect:/shops/blacklist.do?result=true");
    }

   /* @RequestMapping(value = "deleteBlackLists", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('OPT_SHOP_UPDATE_BLACK_LIST')")
    @ResponseBody
    public ResponseEntity<String> deleteBlackLists(@RequestParam("ids[]") List<Long> ids) {
        shopService.deleteBlackList(RedisUtil.longListToStrList(ids));
        return new ResponseEntity<String>(HttpStatus.OK);
    }*/

    /**
     * 省公司查看所属线上合作者开发商户情况
     *
     * @param reqVo
     * @return
     */
  /*  @RequestMapping(value = "listOnlineDepotShop", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_ONLINE_DEPOT_SHOP_LIST')")
    public ModelAndView listOnlineDepotShop(ShopQueryReqVo reqVo) {

        logger.debug("Received /shops GET request.");
        ModelAndView mv = new ModelAndView("shop/companyOnlineDepotShop");

        CompanyPo companyPo = ManageServlet.getCompanyOfCurrentSelected();
        reqVo.setDepotType(DepotType.DEPOT_TYPE_ONLINE.getValue());
        if (org.codelogger.utils.StringUtils.isBlank(reqVo.getDepotId())) {
            reqVo.setCompanyId(companyPo.getId());
        }

        PageControl pc = new PageControl(reqVo.getPageSize(), reqVo.getPage());

        if (companyPo == null) {
            mv.addObject("shops", Lists.newArrayList());
        } else {
            List<ShopPo> shops = shopService.searchPageShops(reqVo, pc);
            List<SearchShopRespVo> voList = Lists.transform(shops, new ShopPoToSearchShopRespVo());
            mv.addObject("shops", voList);
        }

        mv.addObject("pageControl", pc);
        mv.addObject("reqVo", reqVo);
        return mv;
    }*/

    /**
     * 省公司查看店铺详情
     */
  /*  @RequestMapping(value = "/view/{shopId}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_ONLINE_DEPOT_SHOP_LIST')")
    public ModelAndView viewShopDetail(
            @PathVariable("shopId") Long shopId) throws CommonException {

        logger.debug("Received /shops/{} GET request.", shopId);
        ShopPo shop = shopService.findShopPo(shopId);
        ShopStatus[] shopStatusList = ShopStatus.values();
        return new ModelAndView("shop/shopView", "shop", new ShopPoToShopEditVo().apply(shop))
                .addObject("shopStatusList", shopStatusList)
                .addObject("emp", depotEmployeeService.getDepotEmployeeById(shop.getInviterCode()));
    }*/

    /**
     * 查询图片地址
     * @param imageName
     * @return
     */
    private String findImgUrl(String imageName){
        if(StringUtils.isNotBlank(imageName)){
        try{
            return OssUtil.getOssResourceUri(config.getProductBucketName(), config.getRemoteEndpoint(), imageName);
        }catch (Exception e){
          e.printStackTrace();
        }
        }
        return "";
    }

}
