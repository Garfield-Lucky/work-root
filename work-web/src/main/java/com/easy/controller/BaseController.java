package com.easy.controller;

import lombok.Data;
import org.springframework.stereotype.Controller;

import java.util.logging.Logger;


/**
 * @Description: 父类Action,包括一些通用的方法
 * @param
 * @author Created by wuzhangwei on 2018/9/11 8:53
 */
@Data
@Controller
public abstract class BaseController {
//    private static final Logger log = Logger.getLogger(BaseController.class);

	protected Integer page;//表第几页
	protected Integer limit;//表每页显示几条数据
	protected String sortname;//每排序的字段名
	protected String sortorder;//指排序的方式,如desc,asc


	
}
