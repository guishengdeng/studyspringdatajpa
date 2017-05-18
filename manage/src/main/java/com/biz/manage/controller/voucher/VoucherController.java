package com.biz.manage.controller.voucher;

import static java.lang.String.format;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.codelogger.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.biz.gbck.dao.mysql.po.voucher.VoucherPo;
import com.biz.gbck.dao.redis.repository.voucher.VoucherTypeRedisDao;
import com.biz.gbck.dao.redis.ro.org.ShopTypeRo;
import com.biz.gbck.dao.redis.ro.org.UserRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.enums.user.ShopTypeStatus;
import com.biz.manage.util.AuthorityUtil;
import com.biz.manage.util.POIUtil;
import com.biz.manage.vo.voucher.DispatcherVoucherVo;
import com.biz.manage.vo.voucher.VoucherBatchGrantReqVo;
import com.biz.service.org.interfaces.ShopTypeService;
import com.biz.service.org.interfaces.UserService;
import com.biz.service.voucher.VoucherService;
import com.biz.service.voucher.VoucherTypeService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.common.collect.Lists;

/**
 * @author Nian.Li
 * @ClassName: VoucherController 
 * @Description: 优惠券发放控制器
 * @date 2016年4月15日 下午3:04:03 
 *   
 */
@Controller
@RequestMapping("/manage/voucher")
//@Secured("ROLE_VOUCHER")
public class VoucherController {

    private static final Logger logger = LoggerFactory.getLogger(VoucherController.class);

    @Autowired
    private VoucherTypeRedisDao voucherTypeRedisDao;

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private ShopTypeService shopTypeService;

    @Autowired
    private UserService userService;

    @Autowired
    private VoucherTypeService voucherTypeService;

    /**
     * 根据商户Id给对应用户发放优惠券（跳转）
     *
     * @param shopId
     * @return
     */
    @RequestMapping("dispatcherToUser")
    public ModelAndView dispatcherToUser(@RequestParam("shopId") Long shopId) {
        ModelAndView view = new ModelAndView("/vouchers/dispatcherToUser");
        // 查询优惠券类型集合
        List<VoucherTypeRo> voucherTypeRos = voucherTypeService.allVoucherTypesInApp();
        List<VoucherTypeRo> voucherTypeRosResult = new ArrayList<VoucherTypeRo>();

        if (voucherTypeRos != null && voucherTypeRos.size() > 0) {
            for (VoucherTypeRo voucherTypeRo : voucherTypeRos) {
                if (voucherTypeRo.getStartTime() <= System.currentTimeMillis()
                        && voucherTypeRo.getExpireTime() >= System.currentTimeMillis()) {
                    voucherTypeRosResult.add(voucherTypeRo);
                }
            }
            Collections.sort(voucherTypeRosResult, new Comparator<VoucherTypeRo>() {
                public int compare(VoucherTypeRo arg0, VoucherTypeRo arg1) {
                    return arg0.getId().compareTo(arg1.getId());
                }
            });
            view.addObject("voucherTypes", voucherTypeRosResult);
        }
        List<Long> id = userService.findAdminUserIdsByShopId(shopId, true);
        if (id.size() != 0) {
            Long userId = id.get(0);
            view.addObject("userId", userId);
            List<VoucherPo> vouchers = voucherService.listAllVouchersByUserId(userId);
            view.addObject("vouchers", vouchers);
        }else {
            view.addObject("msg", "没有对应用户");
            view.addObject("err", 90000);
        }
        return view;


    }

    /**
     * @param id
     * @return
     * @Description: 到发放给某店铺类型下用户页面
     * @author Nian.Li
     * @date 2016年4月15日 下午3:11:00 
     */
    @RequestMapping("dispatcherToTypeUser")
    public ModelAndView dispatcherToTypeUser(@RequestParam("id") Long id) {
        ModelAndView view = new ModelAndView("/vouchers/dispatcherToTypeUser");

        // 根据ID查询出来的优惠券类型
        VoucherTypeRo voucherTypeRo = voucherTypeRedisDao.getVoucherTypeRoById(id);
        view.addObject("voucherTypeRo", voucherTypeRo);

        // 商户类型
        List<ShopTypeRo> shopTypes = shopTypeService.findAllShopTypeRo(ShopTypeStatus.NORMAL);
        view.addObject("shopTypes", shopTypes);

        return view;
    }

    /**
     * @param dispatcherVoucherVo
     * @return
     * @Description: 把优惠券发放给某用户处理
     * @author Nian.Li
     * @date 2016年4月15日 下午4:27:10 
     */
    @RequestMapping(value = "dispatcherSub", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String dispatcherSub(DispatcherVoucherVo dispatcherVoucherVo) {
        String result = "系统异常";
        try {
            dispatcherVoucherVo.fillUserIds();
            List<Long> userIds = dispatcherVoucherVo.getDispatcherUserIds();
            Long voucherTypeId = dispatcherVoucherVo.getVoucherTypeId();
            Long shopTypeId = dispatcherVoucherVo.getShopType();
            int dispatcherCnt = Integer.valueOf(dispatcherVoucherVo.getDispatchCount());

            VoucherTypeRo voucherTypeRo = voucherTypeRedisDao.getVoucherTypeRoById(voucherTypeId);
            if (voucherTypeRo.getStartTime() > System.currentTimeMillis()) {
                result = "优惠券未到发放时间";
            } else if (voucherTypeRo.getExpireTime() < System.currentTimeMillis()) {
                result = "优惠券发放时间已经过期";
            } else if (!voucherService.validateDispatcherAction(userIds, shopTypeId, voucherTypeId, dispatcherCnt)) {
                result = "优惠券数量不足";
            } else {
                if (shopTypeId != null) {
                    userIds = userService.findUserIdByShopType(shopTypeId);
                } else {
                    List<ShopTypeRo> shopTypes = shopTypeService.findAllShopTypeRo(ShopTypeStatus.NORMAL);
                    for (ShopTypeRo ro : shopTypes) {
                        userIds.addAll(userService.findUserIdByShopType(Long.parseLong(ro.getId())));
                    }
                }
                voucherService.dispatcherVoucher(userIds, voucherTypeRo, dispatcherCnt,
                    AuthorityUtil.getLoginUsername());
                result = "success";
            }
        } catch (Exception e) {
            logger.error("管理后台发放优惠券异常,voucherTypeId[" + dispatcherVoucherVo.getVoucherTypeId() + "],shopTypeId[" + dispatcherVoucherVo.getShopType() + "],dispatchcount[" + dispatcherVoucherVo.getDispatchCount() + "]，异常信息[" + e.getMessage() + "]", e);
        }
        return result;
    }



    @RequestMapping(value = "sendVoucherByUserId", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String sendVoucherByUserId(DispatcherVoucherVo dispatcherVoucherVo) {
        String result = "发送失败！";
        try {
            dispatcherVoucherVo.fillUserIds();
            List<Long> userId = dispatcherVoucherVo.getDispatcherUserIds();
            Long voucherTypeId = dispatcherVoucherVo.getVoucherTypeId();
            int dispatcherCnt = Integer.valueOf(dispatcherVoucherVo.getDispatchCount());

            VoucherTypeRo voucherTypeRo = voucherTypeRedisDao.getVoucherTypeRoById(voucherTypeId);
            if (voucherTypeRo.getStartTime() > System.currentTimeMillis()) {
                result = "优惠券未到发放时间";
            } else if (voucherTypeRo.getExpireTime() < System.currentTimeMillis()) {
                result = "优惠券发放时间已经过期";
            } else if (voucherService.findVoucherNumberById(voucherTypeId) < dispatcherCnt) {
                result = "优惠券数量不足";
            } else {
                voucherService.dispatcherVoucher(userId, voucherTypeRo, dispatcherCnt, AuthorityUtil.getLoginUsername());
                result = "success";
            }
        } catch (Exception e) {
            logger.error("管理后台发放优惠券异常,voucherTypeId[" + dispatcherVoucherVo.getVoucherTypeId() + "],shopTypeId[" + dispatcherVoucherVo.getShopType() + "],dispatchcount[" + dispatcherVoucherVo.getDispatchCount() + "]，异常信息[" + e.getMessage() + "]", e);
        }
        return result;
    }

    /**
     * 批量发放优惠券
     *
     * @param request
     * @return
     */
    @RequestMapping("batchGrant")
//    @PreAuthorize("hasAuthority('OPT_VOUCHER_BATCH')")
    public ModelAndView batchGrant(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("/vouchers/batchGrant");

        // 查询优惠券类型集合
        List<VoucherTypeRo> voucherTypeRos = voucherTypeService.allVoucherTypesInApp();
        List<VoucherTypeRo> voucherTypeRosResult = new ArrayList<VoucherTypeRo>();
        if (voucherTypeRos != null && voucherTypeRos.size() > 0) {
            for (VoucherTypeRo voucherTypeRo : voucherTypeRos) {
                if (voucherTypeRo.getStartTime() <= System.currentTimeMillis()
                        && voucherTypeRo.getExpireTime() >= System.currentTimeMillis()) {
                    voucherTypeRosResult.add(voucherTypeRo);
                }
            }
            Collections.sort(voucherTypeRosResult, new Comparator<VoucherTypeRo>() {
                public int compare(VoucherTypeRo arg0, VoucherTypeRo arg1) {
                    return arg0.getId().compareTo(arg1.getId());
                }
            });
            view.addObject("voucherTypes", voucherTypeRosResult);
        }


        return view;
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST)
//    @PreAuthorize("hasAuthority('OPT_VOUCHER_BATCH')")
    public ModelAndView uploadSendData(@RequestParam("data")MultipartFile file, VoucherBatchGrantReqVo vo)
            throws IOException, EncryptedDocumentException{
        String msg = "";
        String status = "success";

        if (file.getSize() == 0 || vo.getDispatcherCnt() == null || vo.getDispatcherCnt() == 0
                || vo.getVoucherTypeId() == null) {
            return new ModelAndView(format("redirect:/manage/voucher/batchGrant.do?status=failed&msg=%s",
                    status, URLEncoder.encode(msg, "UTF-8")));
        }

        int uploadCount;
        InputStream is;
        File tempFile = null;
        try {
            String loginUsername = AuthorityUtil.getLoginUsername();
            is =  file.getInputStream();
            tempFile = File.createTempFile("priceUploadExcel", "tmp");
            logger.debug("缓存{}上传的价格文件到本地。", loginUsername);
            FileUtils.write(is, tempFile);
            logger.debug("开始处理{}上传的价格文件.", loginUsername);
            Workbook workbook = WorkbookFactory.create(tempFile);
            try {

                List<Long> userIds =
                        handleDepotProductPriceWorksheet(loginUsername, workbook.getSheetAt(0), 1, 0);
                logger.debug("开始转换{}上传的价格数据为po文件", loginUsername);

                logger.debug("{}上传的价格文件解析完成.", loginUsername);


                VoucherTypeRo voucherTypeRo = voucherTypeRedisDao.getVoucherTypeRoById(vo.getVoucherTypeId());
                if (voucherTypeRo.getStartTime() > System.currentTimeMillis()) {
                    msg = "优惠券未到发放时间";
                } else if (voucherTypeRo.getExpireTime() < System.currentTimeMillis()) {
                    msg = "优惠券发放时间已经过期";
                } else if (voucherService.findVoucherNumberById(vo.getVoucherTypeId()) < vo.getDispatcherCnt() * userIds.size()) {
                    msg = "优惠券数量不足";
                } else {
                	if(userIds != null && userIds.size() > 0){
	                    voucherService.dispatcherVoucher(userIds, voucherTypeRo, vo.getDispatcherCnt(),
	                            AuthorityUtil.getLoginUsername());
                	}else{
                		status = "failed";
                	}

                }
            } catch (Exception e) {
                logger.warn("上传导入数据文件{}出错", file.getOriginalFilename(), e);
                msg = e.getMessage();
                status = "failed";
            }
        } catch (IOException e) {
            logger.warn("获取上传文件[{}]输入流出错", file.getOriginalFilename(), e);
            msg = format("获取上传文件[%s]输入流出错", file.getOriginalFilename());
            status = "failed";
        } catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e1) {
			e1.printStackTrace();
			status = "failed";
		} finally {
            FileUtils.delete(tempFile);
        }

        return new ModelAndView(format("redirect:/manage/voucher/batchGrant.do?status=%s&msg=%s",
                status, URLEncoder.encode(msg, "UTF-8")));
    }


    private List<Long> handleDepotProductPriceWorksheet(String loginUsername, Sheet sheet,
                                                        int startRow, int startCol) throws Exception {
        List<Long> userIds = Lists.newArrayList();
        Row row = sheet.getRow(startRow);
        while (row != null) {
            logger.debug("开始处理{}上传的价格文件的第{}行数据", loginUsername, startRow);
            try {
                String mobile = org.apache.commons.lang3.StringUtils.trim(POIUtil.getCellValue(row.getCell(startCol)));
                UserRo userRo = userService.findUserByMobile(mobile);
                if (userRo != null) {
                    userIds.add(Long.valueOf(userRo.getId()));
                }
                startRow = startRow + 1;
                row = sheet.getRow(startRow);
            } catch (Exception e) {
                logger.debug("Process price upload failed.", e);
                throw new Exception(
                        format("处理表格[%s]第[%d]行[%d]列时出错", sheet.getSheetName(), startRow, startCol));
            }
        }
        return userIds;
    }
}
