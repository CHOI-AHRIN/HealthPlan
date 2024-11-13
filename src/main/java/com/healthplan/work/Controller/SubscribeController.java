package com.healthplan.work.Controller;

import com.healthplan.work.service.SubscribeService;
import com.healthplan.work.vo.ImageDTO;
import com.healthplan.work.vo.PageMaker;
import com.healthplan.work.vo.SearchCriteria;
import com.healthplan.work.vo.SubscribeVO;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SubscribeController : 구독 > 구독정보 / 전문가구독 / 강의수강
 * The type Subscribe controller.
 */
@RestController
@Log4j2
@RequestMapping("/api/subscribe")
public class SubscribeController {

    @Autowired
    private SubscribeService subscribeService;

    /**
     * subscribeList : 전문가구독 list
     *
     * @param cri the cri
     * @return the map
     * @throws Exception the exception
     */
    @GetMapping("/subscribeList")
    public Map<String, Object> list(@NotNull SearchCriteria cri) throws Exception {
        Map<String, Object> result = new HashMap<>();

        //전체검색 onchange x
        if ("".equals(cri.getSearchType())) {
            cri.setSearchType("total");
        }
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        pageMaker.setTotalCount(subscribeService.selectSubscribeCount(cri));

        List<SubscribeVO> list = subscribeService.selectSubscribeList(cri);
        result.put("list", list);
        result.put("pageMaker", pageMaker);

        log.info("cri	-> " + cri);
        log.info("subscribeList result-> " + result);
        return result;
    }

    /**
     * Insert string.
     *
     * @param subscribeVO the subscribe vo
     * @return the string
     * @throws Exception the exception
     */
    @PostMapping("/subscribeInsert")
    public String insert(@RequestBody SubscribeVO subscribeVO) throws Exception {
        log.info("subscribeInsert -> " + subscribeVO);
        subscribeService.subscribeInsert(subscribeVO);

        return "success";
    }

    /**
     * Read map.
     *
     * @param sno the sno
     * @return the map
     * @throws Exception the exception
     */
    @GetMapping("/subscribeRead/{sno}")
    public SubscribeVO read(@PathVariable("sno") int sno) throws Exception {
        SubscribeVO vo = subscribeService.selectSubscribeRead(sno);

        log.info("sno -> " + sno);
        log.info("subscribeRead result -> " + vo.toString());

        //이미지 정보 가져오기
        List<ImageDTO> imageDTOList = subscribeService.selectImageList(sno);
        log.info("imageDTOList -> " + imageDTOList.toString());

        vo.setImageDTOList(imageDTOList);
        return vo;
    }

    /**
     * Update string.
     *
     * @param subscribeVO the subscribe vo
     * @return the string
     * @throws Exception the exception
     */
    @PutMapping("/subscribeUpdate")
    public String update(@RequestBody SubscribeVO subscribeVO) throws Exception {
        log.info("subscribeUpdate subscribeVO -> " + subscribeVO);

        subscribeService.selectSubscribeUpdate(subscribeVO);
        return "success";
    }

    /**
     * Delete string.
     *
     * @param sno the sno
     * @return the string
     * @throws Exception the exception
     */
    @DeleteMapping("/subscribeDelete/{sno}")
    public String delete(@PathVariable("sno") int sno) throws Exception {
        log.info("subscribeDelete -> " + sno);
        subscribeService.subscribeDelete(sno);

        return "success";
    }

    /**
     * subscribeLessionList : 강의수강 select list
     *
     * @param cri the cri
     * @return the map
     * @throws Exception the exception
     */
    @GetMapping("/subscribeLessionList")
    public Map<String, Object> lessionList(@NotNull SearchCriteria cri) throws Exception {
        Map<String, Object> result = new HashMap<>();

        //전체검색 onchange x
        if ("".equals(cri.getSearchType())) {
            cri.setSearchType("total");
        }
        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(cri);
        pageMaker.setTotalCount(subscribeService.selectSubscribeLessionCount(cri));

        List<SubscribeVO> list = subscribeService.selectSubscribeLessionList(cri);
        result.put("list", list);
        result.put("pageMaker", pageMaker);

        log.info("cri	-> " + cri);
        log.info("subscribeLessionList result-> " + result);
        return result;
    }

    /**
     * subscribeLessionInsert : 강의등록 insert
     * Lession insert string.
     *
     * @param subscribeVO the subscribe vo
     * @return the string
     * @throws Exception the exception
     */
    @PostMapping("/subscribeLessionInsert")
    public String lessionInsert(@RequestBody SubscribeVO subscribeVO) throws Exception {
        log.info("subscribeInsert -> " + subscribeVO);
        subscribeService.subscribeLessionInsert(subscribeVO);

        return "success";
    }

    /**
     * subscribeLessionRead : 강의상세 select one
     * Lession read subscribe vo.
     *
     * @param sno the sno
     * @return the subscribe vo
     * @throws Exception the exception
     */
    @GetMapping("/subscribeLessionRead/{sno}")
    public SubscribeVO lessionRead(@PathVariable("sno") int sno) throws Exception {
        SubscribeVO vo = subscribeService.selectSubscrLessionibeRead(sno);

        log.info("sno -> " + sno);
        log.info("subscribeLessionRead result -> " + vo.toString());

        //이미지 정보 가져오기
        List<ImageDTO> mainImageList = subscribeService.selectMainImage(sno);
        List<ImageDTO> imageDTOList = subscribeService.selectImageList(sno);
        log.info("mainImage -> " + mainImageList.toString());
        log.info("imageDTOList -> " + imageDTOList.toString());

        vo.setMainImage(mainImageList);
        vo.setImageDTOList(imageDTOList);
        return vo;
    }

    /**
     * subscribeLessionRead : 강의수정 update one
     * Lession update subscribe vo.
     *
     * @param subscribeVO the subscribe vo
     * @return the subscribe vo
     * @throws Exception the exception
     */
    @PutMapping("/subscribeLessionUpdate")
    public String lessionUpdate(@RequestBody SubscribeVO subscribeVO) throws Exception {

        log.info("lessionUpdate subscribeVO -> " + subscribeVO);
        subscribeService.selectSubscribeUpdate(subscribeVO);

        return "success";
    }

    /**
     * subscribeLessionDelete : 강의삭제 delete
     * Lession delete string.
     *
     * @param sno the sno
     * @return the string
     * @throws Exception the exception
     */
    @DeleteMapping("/subscribeLessionDelete/{sno}")
    public String lessionDelete(@PathVariable("sno") int sno) throws Exception {

        log.info("subscribeDelete -> " + sno);
        subscribeService.subscribeLessionDelete(sno);

        return "success";
    }
}