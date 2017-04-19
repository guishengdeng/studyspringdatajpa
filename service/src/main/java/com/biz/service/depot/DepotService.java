package com.biz.service.depot;


import com.biz.core.page.PageResult;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.vo.depot.*;
import java.util.List;

/**
 * 门店service
 *
 * @author lei
 * @date 09/12/16
 * @reviewer
 * @see
 */
public interface DepotService {

    /**
     * 此接口用于后台管理系统需要列表展示时调用(分页)
     *
     * @return 门店分页信息集合
     */
    PageResult<DepotResponseVo> searchDepots(DepotQueryReqVo reqVo);

    /**
     * 根据门店id获取门店ro(ro --> vo 单个查询 by MySql)
     *
     * @return 单个门店信息
     */
    DepotResponseVo findById(Long depotId);

    /**
     * 根据门店中台门店编码获取单个门店信息(ro --> vo by redis)
     *
     * @return 单个门店信息
     */
    DepotResponseVo findDepotByDepotCode(String depotCode);

    /**
     * 根据门店中台编码获取单个门店信息(po --> vo by MySql)
     *
     * @return 单个门店信息
     */
    DepotResponseVo getDepotByDepotCode(String depotCode);

    /**
     * 获取所有门店的集合(by redis)
     *
     * @return redis中所有门店信息集合
     */
    List<DepotResponseVo> findAllDepots();

    /**
     * 根据门店id获取门店营业状态(单个查询)
     * 门店是否营业(True 营业, False 歇业)
     *
     * @return 该门店是否营业(true or false)
     */
    Boolean getDepotBusinessStatus(Long depotId);

    /**
     * 将Depot数据从数据库同步到redis
     */
    void syncMysql2Redis();

    /**
     * 根据市id获取到该城市所对应的省仓门店
     *
     * @return 单个省仓门店信息
     */
    DepotResponseVo findWarehouseDepotByCityId(Integer cityId);

    /**
     * 根据中台门店编码获取到省仓门店
     *
     * @return 单个省仓门店信息
     */
    DepotResponseVo findWarehouseDepotByDepotCode(String depotCode);

    /**
     * 根据门店编码的集合获取门店的集合
     *
     * @return 门店信息的集合
     */
    List<DepotResponseVo> findDepotsByDepotCodes(List<String> depotCodes);

    /**
     * 根据门店id集合查询门店的集合
     *
     * @return 门店信息的集合
     */
    List<DepotResponseVo> findDepotsByIds(List<Long> ids);

    /**
     * 查询出所有的门店及该门店所对应的省仓门店
     *
     * @return 所有门店及该门店所对应的省仓门店信息
     */
    List<DepotAndWarehouseDepotRespVo> findAllDepotsAndWarehouseDepots();

    /**
     * 根据页面传递过来的经纬度获取到最近的门店或者省仓门店
     *
     * @return 最近门店或者省仓门店的信息
     */
    List<SearchDepotResVo> searchNearbyDepot(SearchDepotConditionVo vo) throws DepotNextDoorException;

    /**
     * 根据销售区域获取到该区域下所有的门店以及其对应的省仓门店
     *
     * @return 该区域下的门店返回Vo
     */
    AreaDepotResVo findDepotsByArea(String areaNo);

    /**
     * 获取所有的区域编码
     *
     * @return 所有区域编码集合
     */
    List<String> listAllAreaNo();

    /**
     * 根据省id获取该省的省仓门店
     */
    DepotResponseVo getWarehouseDepotByProvinceId(Integer provinceId);

}
