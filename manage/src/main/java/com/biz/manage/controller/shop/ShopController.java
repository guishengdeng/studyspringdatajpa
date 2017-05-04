package com.biz.manage.controller.shop;


import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.mysql.po.org.ShopDetailPo;
import com.biz.gbck.dao.mysql.po.org.ShopPo;
import com.biz.gbck.dao.mysql.po.org.UserPo;
import com.biz.gbck.enums.user.AuditRejectReason;
import com.biz.gbck.enums.user.AuditStatus;
import com.biz.gbck.enums.user.ShopTypeStatus;
import com.biz.gbck.vo.org.ShopSearchVo;
import com.biz.manage.controller.BaseController;
import com.biz.manage.util.AuthorityUtil;
import com.biz.service.org.interfaces.ShopService;
import com.biz.service.org.interfaces.ShopTypeService;
import com.biz.service.org.interfaces.UserService;
import com.biz.vo.org.ShopAuditDataMap;
import com.biz.vo.org.ShopAuditReqVo;
import com.biz.vo.org.ShopAuditVo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

@Controller
@RequestMapping("/shops")
@Secured("ROLE_SHOP")
public class ShopController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private ShopService shopService;

    /* @Autowired
     private DepotEmployeeService depotEmployeeService;*/

    /*@Autowired
    private DepotService depotService;*/

    @Autowired
    private ShopTypeService shopTypeService;

   /* @Autowired
    private GeoService geoService;*/

    /**
     * 列出所有未审核通过的商铺
     */
    @GetMapping
    @RequestMapping(value = "auditList")
    @PreAuthorize("hasAuthority('OPT_SHOP_AUDITLIST')")
    public ModelAndView listShopOfWaitForAudit(ShopSearchVo vo)
            throws CommonException {

        logger.debug("Received /shops/auditList GET request.");
        ModelAndView mav = new ModelAndView("/org/shop/auditList");
        Page<ShopPo> shopSearchResVoPage = shopService.findShopAuditDataOfWaitForAudit(vo);
        mav.addObject("shopSearchResVoPage", shopSearchResVoPage);
        return mav;
    }

    /**
     * 进入商户审核界面
     */
    @RequestMapping(value = "audit", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_SHOP_AUDIT')")
    public ModelAndView auditShopDetail(
            @RequestParam("shopId") Long shopId, HttpServletRequest request) throws Exception {

        logger.debug("Received /shops/audit GET request with shopId:{}.", shopId);
        ModelAndView modelAndView = new ModelAndView("/org/shop/auditDetail");
        ShopAuditDataMap shopAuditDataMap =
                shopService.findShopAuditDataOfWaitForAuditByShopId(shopId);
        ShopAuditVo shopAuditVo = CollectionUtils.getFirstOrNull(shopAuditDataMap.values());
        List<AuditRejectReason> auditRejectReasons = newArrayList();
        if (shopAuditVo != null) {
            if (shopAuditVo.getShopDetail() != null) {
                auditRejectReasons.add(AuditRejectReason.DETAIL_INVALID);
            }
            if (shopAuditVo.getShopQualification() != null) {
                for (AuditRejectReason auditRejectReason : AuditRejectReason.values()) {
                    if (auditRejectReason != AuditRejectReason.DETAIL_INVALID)
                        auditRejectReasons.add(auditRejectReason);
                }
            }
        } else {
            logger.debug("No audit data for shopId:{}.", shopId);
            return modelAndView;
        }
        modelAndView.addObject("auditData", shopAuditVo);
        List<AuditStatus> auditStatusList =
                newArrayList(AuditStatus.NORMAL, AuditStatus.AUDIT_FAILED);
        modelAndView.addObject("auditStatusList", auditStatusList)
                .addObject("auditRejectReasons", auditRejectReasons).addObject("emp", null)
                .addObject("shopTypes", shopTypeService.findAllShopTypeRo(ShopTypeStatus.NORMAL));
/*shopAuditVo.getShop()==null? null: depotEmployeeService.getDepotEmployeeById(shopAuditVo.getShop().getInviterCode())*/

         /** 查找商户经纬度*/
        if (shopAuditVo.getShopDetail() != null) {
            String depotId=getNearDepot(shopAuditVo.getShopDetail());
            modelAndView.addObject("depotId", depotId);
        }

        return modelAndView;
    }

    private String getNearDepot(ShopDetailPo shopDetailPo){
        String depotId = "";
           /* String address = geoService
                    .buildAddress(shopDetailPo.getProvince(), shopDetailPo.getCity(), shopDetailPo.getDistrict(), shopDetailPo.getDeliveryAddress());
            String longitude; //经度
            String latitude ; //纬度
            try {
                Pair<String, String> o = GetLatAndLongByBaidu.getCoordinate(address);
                longitude = o.getKey();
                latitude = o.getValue();
            } catch (Exception e) {
                latitude = null;
                longitude = null;
            }
            modelAndView.addObject("longitude", longitude);
            modelAndView.addObject("latitude", latitude);

            DistrictPo district = shopDetailPo.getDistrict();
            if (district != null) {
                DepotPo onlineDepot = shopService.getOnlineDepotByShopId(shopId, district.getId());
                if (onlineDepot != null) {
                    modelAndView.addObject("depotId", onlineDepot.getId());
                } else {
                    //筛选最近门店
                    List<DepotPo> depots = depotService.findAll();
                    Double distance = 0.00;
                    if (depots.size() != 0) {
                        if (longitude != null && latitude != null) {
                            for (DepotPo depotPo : depots) {
                                if (depotPo.getDepotType() == DepotType.DEPOT_TYPE_OFFLINE) {
                                    if (depotPo.getDepotLongitude() != null && depotPo.getDepotLatitude() != null) {
                                        Double distanceOne = GetLatAndLongByBaidu
                                                .Distance(Double.parseDouble(longitude), (Double.parseDouble(latitude)),
                                                        depotPo.getDepotLongitude().doubleValue(), depotPo.getDepotLatitude().doubleValue());
                                        if (distance == 0.00) {
                                            distance = distanceOne;
                                        }
                                        if (distanceOne < distance) {
                                            distance = distanceOne;
                                            depotId = depotPo.getId();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }*/
        return depotId;
    }

    @RequestMapping(value = "isBusinessLicenceIdExist", method = RequestMethod.GET)
    @ResponseBody
    public Boolean isBusinessLicenceIdExist(@RequestParam("businessLicenceId") String businessLicenceId, @RequestParam("shopId") Long shopId) {

        return shopService.isBusinessLicenceIdExist(businessLicenceId, shopId);
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
        shopService.auditShop(shopAuditReqVo);
        return mav;
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
     * 进入店铺创建页面
     */
    @RequestMapping(value = "new", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_SHOP_SAVE')")
    public ModelAndView forwardToShopCreatePage() {

        logger.debug("Received /shops GET request.");
        return new ModelAndView("shop/new");
    }

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
     * 更新店铺状态
     */
   /* @RequestMapping(value = "toggleShopStatus", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('OPT_SHOP_UPDATE')")
    @ResponseBody
    public Boolean updateShopStatus(
            @RequestParam("shopId") Long shopId) {
        Integer status = shopService.findShopPo(shopId).getStatus();
        return shopService.updateShopStatus(shopId,
                Objects.equals(status, ShopStatus.NORMAL.getValue()) ?
                        ShopStatus.DISABLED :
                        ShopStatus.NORMAL);
    }*/

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
        List<ShopPo> shops = shopService.findShopByDepotId(depotId);
        return new ModelAndView("shop/list", "shops", shops);
    }

    @RequestMapping(value = "destroy", method = RequestMethod.POST)
    @ResponseBody
    @PreAuthorize("hasAuthority('OPT_SHOP_DESTROY')")
    public Boolean destroyShop(@RequestParam("shopId") Long shopId) {

        try {
            shopService.destroyShopAndItUsers(shopId, AuthorityUtil.getLoginUsername());
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
        Integer succesCount = shopService.enableShopByIds(idList);
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
        Integer succesCount = shopService.disableShopByIds(idList);
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
            List<ShopPo> po = shopService.findShopPoByMobileAndHaveNotShopId(mobile, shopId);
            if (CollectionUtils.isNotEmpty(po)) {
                UserPo existUser = userService.findUserPoByMobile(mobile);
                if (existUser == null) {
                    existUser = userService.findUserPoByAccount(mobile);
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
        String re = shopService.updateBlackList(blackList);
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
        List<ShopPo> backShopPoList = shopService.getShopPoBackList();
        mav.addObject("blackList", backShopPoList);
        return mav;
    }

    @RequestMapping(value = "deleteBlackList", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('OPT_SHOP_UPDATE_BLACK_LIST')")
    public ModelAndView deleteBlackList(@RequestParam("shopId") String shopId) {
        shopService.deleteBlackList(newArrayList(shopId));
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

}
